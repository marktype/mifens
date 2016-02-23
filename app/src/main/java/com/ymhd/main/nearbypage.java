package com.ymhd.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.mifen.R;
import com.ymhd.mifei.listadapter.nearbylistadapter;

import java.util.ArrayList;
import java.util.List;


//本页具体操作方法，详见高德地图api
public class nearbypage extends baseActivity
		implements OnMapLoadedListener, OnMarkerClickListener, LocationSource, AMapLocationListener {
	private LinearLayout nearby_lin, nearby_line;
	private View view_list, view_map;
	private ListView nearbyforlistinfo;
	private nearbylistadapter nearbylistadapter;
	private List<String> list;
	private LayoutInflater minflater;
	private RadioButton rad_list, rad_map;
	private Button busline_btn, walkline_btn, driveline_btn;
	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;
	private Marker marker;
	private AMapLocation amapLocation;
	private LatLng start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby);
		minflater = LayoutInflater.from(this);
		init();     //初始化控件界面
		initlist();   //初始化界面中列表
		mapView = (MapView) view_map.findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写

		initMapLoction();    //初始化地图定位

//		mlocationClient = new AMapLocationClient(this.getApplicationContext());
//		mLocationOption = new AMapLocationClientOption();
//		// 设置定位监听
//		mlocationClient.setLocationListener(this);
//		// 设置低精度定位
//		mLocationOption.setLocationMode(AMapLocationMode.Battery_Saving);
//		// 设置定位参数
//		mlocationClient.setLocationOption(mLocationOption);
//		// 启动定位
//		mlocationClient.startLocation();

//		addMarkersToMap();
	}

	/*
	* 初始化地图显示
	* */
	public void initMap(){

	}




	public void init() {
		view_list = minflater.inflate(R.layout.nearbyforlist, null);
		view_map = minflater.inflate(R.layout.nearbyformap, null);



		nearby_line = (LinearLayout) view_map.findViewById(R.id.near_line);
		nearby_line.setVisibility(View.GONE);     // 隐藏导航方式
		nearby_lin = (LinearLayout) findViewById(R.id.nearby_lin);
		nearbyforlistinfo = (ListView) view_list.findViewById(R.id.nearby_listinfo);
		busline_btn = (Button) view_map.findViewById(R.id.busline);
		driveline_btn = (Button) view_map.findViewById(R.id.driveline);
		walkline_btn = (Button) view_map.findViewById(R.id.walkline);

		busline_btn.setOnClickListener(itemsOnClick);
		driveline_btn.setOnClickListener(itemsOnClick);
		walkline_btn.setOnClickListener(itemsOnClick);
		nearby_lin.addView(view_list);
		rad_list = (RadioButton) findViewById(R.id.radio_list);
		rad_map = (RadioButton) findViewById(R.id.radio_map);


		RadioGroup group = (RadioGroup) this.findViewById(R.id.radioGroup1);

		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				// 获取变更后的选中项的ID
				int radioButtonId = group.getCheckedRadioButtonId();
				// 根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton) nearbypage.this.findViewById(radioButtonId);
				switch (radioButtonId) {
				case R.id.radio_list:
					nearby_lin.removeAllViews();
					nearby_lin.addView(view_list);
					break;
				case R.id.radio_map:
					nearby_lin.removeAllViews();
					nearby_lin.addView(view_map);
					break;
				default:
					break;
				}
			}
		});

	}

	public void initlist() {
		list = new ArrayList<String>();
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		nearbylistadapter = new nearbylistadapter(list, this, itemsOnClick);
		nearbyforlistinfo.setAdapter(nearbylistadapter);
	}

	public void initMapLoction() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
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
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		// CameraUpdateFactory.zoomTo(15);
		aMap.moveCamera(CameraUpdateFactory.zoomTo(12));
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
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

	}

	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {
			if (amapLocation != null && amapLocation.getErrorCode() == 0) {
				this.amapLocation = amapLocation;

				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
				start = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());


				Log.e("onLocationChanged: ",amapLocation+"");
//				LatLng end = new LatLng(30.651865197817393, 104.18533690345772);
//				float x = AMapUtils.calculateLineDistance(start, end);
//				Log.e("Location", amapLocation.getProvince() + amapLocation.getAccuracy() + amapLocation.getLongitude()
//						+ amapLocation.getLatitude() + "xxx" + x);
			} else {
				String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
				Log.e("AmapErr", errText);
			}
		}
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		Log.d("tag", "activate: 1111111111111111");
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(this);
			mLocationOption = new AMapLocationClientOption();
			// 设置定位监听
			mlocationClient.setLocationListener(this);
			// 设置为高精度定位模式
			mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
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

	// 实现监听类
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			Log.d("tag", "onClick: 222222222222222");
			Intent in;
			switch (v.getId()) {
			case R.id.btngohere:
				nearby_lin.removeAllViews();
				nearby_lin.addView(view_map);
				rad_list.setChecked(false);
				rad_map.setChecked(true);
				break;
			case R.id.busline:
				in = new Intent(nearbypage.this, TwoDemisonAcitivity.class);
				in.putExtra("stype", "0");
				in.putExtra("NaviLatLng", start.latitude + "," + start.longitude );
				startActivity(in);
				break;
			case R.id.driveline:
				in = new Intent(nearbypage.this, TwoDemisonAcitivity.class);
				in.putExtra("stype", "1");
				in.putExtra("NaviLatLng", start.latitude + "," + start.longitude );
				startActivity(in);
				break;
			case R.id.walkline:
				in = new Intent(nearbypage.this, TwoDemisonAcitivity.class);
				in.putExtra("stype", "2");
				in.putExtra("NaviLatLng", start.latitude + "," + start.longitude );
				startActivity(in);
				break;
			// case R.id.btn_pick_photo:
			// break;
			default:
				break;
			}

		}
	};
	LatLng end = new LatLng(30.651865197817393, 104.18533690345772);

	/**
	 * 在地图上添加marker
	 */
	private void addMarkersToMap() {

		drawMarkers(end);// 添加n个带有系统默认icon的marker

	}

	/**
	 * 绘制系统默认的1种marker背景图片
	 */
	public void drawMarkers(LatLng end) {

		marker = aMap.addMarker(new MarkerOptions().position(end).snippet("")
				.icon(BitmapDescriptorFactory.defaultMarker(R.drawable.localtion)).draggable(false));
	}

	/**
	 * 对marker标注点点击响应事件
	 */
	int temp = 0;

	@Override
	public boolean onMarkerClick(final Marker marker) {
		if (temp == 0) {
			marker.hideInfoWindow();
			temp = 1;
			nearby_line.setVisibility(View.GONE);
		} else {
			temp = 0;
			marker.showInfoWindow();
			nearby_line.setVisibility(View.VISIBLE);
		}
		return true;
	}

	/**
	 * 监听amap地图加载成功事件回调
	 */
	@Override
	public void onMapLoaded() {
		// 设置中心点和缩放比例
		aMap.moveCamera(CameraUpdateFactory.changeLatLng(start));
	}

}
