package org.ros.android.android_tutorial_teleop;


import org.ros.android.android_tutorial_teleop.geocam.GeoCamMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.start);
		final Button robotViewButton= (Button)findViewById(R.id.main_robot_view_button);
		robotViewButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplication(), MainActivity.class);
				startActivity(i);
			}
			
		});
		final Button geoCamButton = (Button)findViewById(R.id.main_geocam_button);
		geoCamButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplication(), GeoCamMobile.class);
				startActivity(i);
			}
			
		});
		
		
		
		super.onCreate(savedInstanceState);
	}
	
}
