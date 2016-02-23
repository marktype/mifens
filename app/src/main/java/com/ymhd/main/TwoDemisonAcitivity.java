package com.ymhd.main;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.enums.PathPlanningStrategy;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.view.RouteOverLay;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.example.mifen.R;
import com.yhd.mifen.navi.util.TTSController;

import java.util.ArrayList;
import java.util.List;

public class TwoDemisonAcitivity extends Activity
		implements LocationSource, AMapLocationListener, AMapNaviListener, AMapNaviViewListener {
	private AMap aMap;
	private MapView mapView;
	private Button startgo_btn;
	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient = null;
	private AMapLocationClientOption mLocationOption = null;
	// 起点终点坐标
	private NaviLatLng mNaviStart;
	private NaviLatLng mNaviEnd = new NaviLatLng(30.651865197817393, 104.18533690345772);
	// 起点终点列表
	private ArrayList<NaviLatLng> mStartPoints = new ArrayList<NaviLatLng>();
	private ArrayList<NaviLatLng> mEndPoints = new ArrayList<NaviLatLng>();

	// 规划线路
	private RouteOverLay mRouteOverLay;
	private TTSController ttsManager;
	private AMapNavi aMapNavi;
	private AMapNaviView mAMapNaviView;
	private NaviLatLng end = new NaviLatLng(30.651865197817393, 104.18533690345772);
	List<NaviLatLng> mStartList = new ArrayList<NaviLatLng>();
    List<NaviLatLng> mEndList = new ArrayList<NaviLatLng>();
    List<NaviLatLng> mWayPointList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearbyformapnavi);
		mapView = (MapView) findViewById(R.id.map2);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		mlocationClient = new AMapLocationClient(this.getApplicationContext());
		mLocationOption = new AMapLocationClientOption();
		// 设置定位监听
		mlocationClient.setLocationListener(this);
		// 设置为高精度定位模式
		mLocationOption.setLocationMode(AMapLocationMode.Battery_Saving);
		// 设置定位参数
		mlocationClient.setLocationOption(mLocationOption);
		// 启动定位
		mlocationClient.startLocation();

		//确定位置
//		mNaviStart = new NaviLatLng()

		initid(savedInstanceState);
		init();     //初始化AMap对象
		initts();
		inittype();
	}
	public void initid(Bundle savedInstanceState)
	{
		startgo_btn = (Button) findViewById(R.id.startgo);
		mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
		mAMapNaviView.onCreate(savedInstanceState);
	}
	public void inittype() {
		String i = getIntent().getStringExtra("stype");
		String text = getIntent().getStringExtra("NaviLatLng");
		mNaviStart = parseEditText(text);
		switch (i) {
		case "0":// busline

			break;
		case "1":// driveline
			calculateDriveRoute();
			break;
		case "2":// walkline
			calculateFootRoute();
			break;
		default:
			break;
		}
	}

	public void initts() {
		ttsManager = TTSController.getInstance(this);
		ttsManager.init();
		ttsManager.startSpeaking();
		aMapNavi = AMapNavi.getInstance(this);
		aMapNavi.setAMapNaviListener(this);
		aMapNavi.setAMapNaviListener(ttsManager);
		aMapNavi.setEmulatorNaviSpeed(300);
		noStartCalculate() ;
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
		mRouteOverLay = new RouteOverLay(aMap, null);
		startgo_btn.setOnClickListener(onclick);
	}

	/**
	 * 设置一些amap的属性
	 */
	private void setUpMap() {
		// 自定义系统定位小蓝点
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));// 设置小蓝点的图标
		myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
		// myLocationStyle.anchor(int,int)//设置小蓝点的锚点
		myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
		aMap.setMyLocationStyle(myLocationStyle);
		aMap.setLocationSource(this);// 设置定位监听
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		// aMap.setMyLocationType()
		aMap.moveCamera(CameraUpdateFactory.zoomTo(12));
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
		mStartPoints.add(mNaviStart);
		mEndPoints.add(mNaviEnd);
		mEndList.add(end);
		ttsManager.stopSpeaking();
		aMapNavi.calculateDriveRoute(mEndPoints, null, PathPlanningStrategy.DRIVING_DEFAULT);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
		aMapNavi.destroy();
		ttsManager.destroy();
		aMapNavi.stopNavi();
	}

	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
//		if (mListener != null && amapLocation != null) {
//			if (amapLocation != null && amapLocation.getErrorCode() == 0) {
//				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
//				String errText = "定位成功," + amapLocation.getLongitude() + ": " + amapLocation.getErrorInfo();
//				Log.e("AmapErr", errText);
//			} else {
//				String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
//				Log.e("AmapErr", errText);
//			}
//		}

		if (mListener != null && amapLocation != null) {
			if (amapLocation != null
					&& amapLocation.getErrorCode() == 0) {
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点

				Log.e("amapLocation----------tag-----",amapLocation.getLatitude()+"------------"+amapLocation.getLatitude());
			} else {
				String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
				Log.e("AmapErr",errText);
			}
		}
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(this.getApplicationContext());
			mLocationOption = new AMapLocationClientOption();
			// 设置定位监听
			mlocationClient.setLocationListener(this);
			// 设置为高精度定位模式
			mLocationOption.setLocationMode(AMapLocationMode.Battery_Saving);
			// 设置定位参数
			mlocationClient.setLocationOption(mLocationOption);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用onDestroy()方法
			// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
			mlocationClient.startLocation();
		}
	}

	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
	}

	// 计算驾车路线
	private void calculateDriveRoute() {
		mStartPoints.clear();
		mEndPoints.clear();
		mStartPoints.add(mNaviStart);
		mEndPoints.add(mNaviEnd);

		boolean isSuccess = aMapNavi.calculateDriveRoute(mStartPoints, mEndPoints, null,
				PathPlanningStrategy.DRIVING_DEFAULT);
		if (!isSuccess) {
			showToast("路线计算失败,检查参数情况");
		}

	}

	private NaviLatLng parseEditText(String text) {
		try {
			double latD = Double.parseDouble(text.split(",")[0]);
			double lonD = Double.parseDouble(text.split(",")[1]);

			return new NaviLatLng(latD, lonD);

		} catch (Exception e) {
			Toast.makeText(this, "e:" + e, Toast.LENGTH_SHORT).show();
			Toast.makeText(this, "格式:[lat],[lon]", Toast.LENGTH_SHORT).show();
		}

		return null;
	}
	OnClickListener onclick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.startgo:
				mAMapNaviView.setVisibility(View.VISIBLE);
				mapView.setVisibility(View.GONE);
				break;

			default:
				break;
			}
		}
	};
	// 计算步行路线
	private void calculateFootRoute() {
		boolean isSuccess = aMapNavi.calculateWalkRoute(mNaviStart, mNaviEnd);
		if (!isSuccess) {
			showToast("路线计算失败,检查参数情况");
		}
	}

	private void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	/**
     * 如果使用无起点算路，请这样写
     */
    private void noStartCalculate() {
        //无起点算路须知：
        //AMapNavi在构造的时候，会startGPS，但是GPS启动需要一定时间
        //在刚构造好AMapNavi类之后立刻进行无起点算路，会立刻返回false
        //给人造成一种等待很久，依然没有算路成功 算路失败回调的错觉
        //因此，建议，提前获得AMapNavi对象实例，并判断GPS是否准备就绪


        if (aMapNavi.isGpsReady())
            aMapNavi.calculateDriveRoute(mEndList, mWayPointList, PathPlanningStrategy.DRIVING_DEFAULT);
    }
	@Override
	public void onLockMap(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onNaviBackClick() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onNaviCancel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNaviMapMode(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNaviSetting() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNaviTurnClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNextRoadClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScanViewButtonClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnUpdateTrafficFacility(TrafficFacilityInfo arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hideCross() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hideLaneInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onArriveDestination() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onArrivedWayPoint(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCalculateMultipleRoutesSuccess(int[] arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCalculateRouteFailure(int arg0) {
		// TODO Auto-generated method stub
		showToast("路径规划出错" + arg0);
	}

	@Override
	public void onCalculateRouteSuccess() {
		// TODO Auto-generated method stub
		AMapNaviPath naviPath = aMapNavi.getNaviPath();
		if (naviPath == null) {
			return;
		}
		// 获取路径规划线路，显示到地图上
		mRouteOverLay.setAMapNaviPath(naviPath);
		mRouteOverLay.addToMap();
		aMapNavi.startNavi(AMapNavi.GPSNaviMode);
	}

	@Override
	public void onEndEmulatorNavi() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetNavigationText(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGpsOpenStatus(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInitNaviFailure() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "init navi Failed", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onInitNaviSuccess() {
		// TODO Auto-generated method stub
		aMapNavi.calculateDriveRoute(mStartPoints,mEndList, mWayPointList, PathPlanningStrategy.DRIVING_DEFAULT);
	}

	@Override
	public void onLocationChange(AMapNaviLocation arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNaviInfoUpdate(NaviInfo arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNaviInfoUpdated(AMapNaviInfo arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReCalculateRouteForTrafficJam() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReCalculateRouteForYaw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartNavi(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTrafficStatusUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showCross(AMapNaviCross arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showLaneInfo(AMapLaneInfo[] arg0, byte[] arg1, byte[] arg2) {
		// TODO Auto-generated method stub

	}

}
