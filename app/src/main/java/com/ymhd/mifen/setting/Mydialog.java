package com.ymhd.mifen.setting;

import com.example.mifen.R;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Mydialog extends AlertDialog{

	private Button  butn;
	private TextView num;
	int i;
	public Mydialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		
	}

	public Mydialog(Context context) {
	    super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.dialog_mifen_share);
	   
	}
	
}




























