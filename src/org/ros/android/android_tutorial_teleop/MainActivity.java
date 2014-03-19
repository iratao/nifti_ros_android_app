/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.ros.android.android_tutorial_teleop;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.ros.address.InetAddressFactory;
import org.ros.android.BitmapFromCompressedImage;
import org.ros.android.RosActivity;
import org.ros.android.android_tutorial_teleop.geocam.GeoCamMobile;
import org.ros.android.view.visualization.VisualizationView;
import org.ros.android.view.visualization.layer.CameraControlLayer;
import org.ros.android.view.visualization.layer.LaserScanPointLayer;
import org.ros.android.view.visualization.layer.RobotLayer;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;
import org.ros.node.topic.Publisher;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;

/**
 * An app that can be used to control a remote robot. This app also demonstrates
 * how to use some of views from the rosjava android library.
 * 
 * @author munjaldesai@google.com (Munjal Desai)
 * @author moesenle@google.com (Lorenz Moesenlechner)
 */
public class MainActivity extends RosActivity {

	private static final int BIG_MAP = 0;
	private static final int SMALL_MAP = 1;
//  private VirtualJoystickView virtualJoystickView;
  private VisualizationView visualizationView;
  private NIFTIRosImageView<sensor_msgs.CompressedImage> imageView;
  private NIFTIImagePublisher imagePublisher;
  private ImageButton shutterButton;
  private Bitmap mImage;
  private byte[] mImageByte;
  private Uri mImageUri;
  protected static final Uri MEDIA_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
  private static final int DIALOG_SAVE_PROGRESS = 991;
  
  private int exchangeState = BIG_MAP;
  
  private ProgressDialog mSaveProgressDialog;


  public MainActivity() {
    super("Teleop", "Teleop");
  }

  //
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.settings_menu, menu);
    return true;
  }

  //
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_exchange:
    	  Display display = getWindowManager().getDefaultDisplay();
    	  Point size = new Point();
    	  display.getSize(size);
    	  int width = size.x;
    	  int height = size.y;
    	  if(this.exchangeState == MainActivity.BIG_MAP) {
        	  LayoutParams lp = new LayoutParams(300, 300);
        	  lp.leftMargin=width-300;
        	  lp.topMargin=height-300;
        	  visualizationView.setLayoutParams(lp);
        	  imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        	  this.exchangeState = MainActivity.SMALL_MAP;
    	  }else{
    		  LayoutParams lp = new LayoutParams(300, 300);
    		  lp.leftMargin=width-300;
        	  lp.topMargin=height-300;
        	  imageView.setLayoutParams(lp);
        	  visualizationView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        	  this.exchangeState = MainActivity.BIG_MAP;
    	  }

      default:
        return super.onOptionsItemSelected(item);
    }
  }

  //
  @SuppressWarnings("unchecked")
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.robotview);
//    virtualJoystickView = (VirtualJoystickView) findViewById(R.id.virtual_joystick);
    imageView = (NIFTIRosImageView<sensor_msgs.CompressedImage>)findViewById(R.id.image);
    visualizationView = (VisualizationView) findViewById(R.id.visualization);
    visualizationView.getCamera().setFrame("map");
    shutterButton = (ImageButton)findViewById(R.id.camera_shutter_button);
    imagePublisher = new NIFTIImagePublisher();
  }
  
  private void saveImage() {
	  
      //mImageUri = saveImage(values);
      mImageUri = imagePublisher.saveImage(this, mImage);
      imagePublisher.saveImageMetadata();
      
      imagePublisher.publishImage(mImageByte);
      imagePublisher.publishImageMetadata();
      
      //dismissDialog(DIALOG_SAVE_PROGRESS);
  }
  
  
  /**
  private int getMediaStoreNumEntries() {
      Cursor cur = managedQuery(MainActivity.MEDIA_URI, null, null, null, null);
      cur.moveToFirst();
      Log.d(GeoCamMobile.DEBUG_ID, "Retrieving list of photos");
      int count = 0;
      while (cur.moveToNext()) {
          count++;
      }
      Log.d(GeoCamMobile.DEBUG_ID, "Found " + count + " photos");
      return count;
  }
  */
  
  protected Dialog onCreateDialog(int id) {
      switch(id) {
      case DIALOG_SAVE_PROGRESS:
          mSaveProgressDialog = new ProgressDialog(this);
          mSaveProgressDialog.setMessage(getResources().getString(R.string.camera_saving));
          mSaveProgressDialog.setIndeterminate(true);
          mSaveProgressDialog.setCancelable(false);
          return mSaveProgressDialog;
      default:
          break;
      }
      return null;
  }

  //
  protected void init(NodeMainExecutor nodeMainExecutor) {
	  imageView.setTopicName("/viz/ptz/image/compressed");
	  imageView.setMessageType(sensor_msgs.CompressedImage._TYPE);
	  imageView.setMessageToBitmapCallable(new BitmapFromCompressedImage());
	  
	  shutterButton.setOnClickListener(new OnClickListener(){
		  @Override
		  public void onClick(View arg0) {
			  // TODO Auto-generated method stub
			  mImage = imageView.getImage();
			  ByteArrayOutputStream stream = new ByteArrayOutputStream();
			  mImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			  byte[] byteArray = stream.toByteArray();
			  mImageByte = byteArray;
			  Thread t = new Thread() {
				  public void run() {
					  saveImage();
			      }
			  };
			  t.start();
		   }
	   });
	    
	   visualizationView.addLayer(new CameraControlLayer(this, nodeMainExecutor
			   .getScheduledExecutorService()));
	   visualizationView.addLayer(new NIFTIOccupancyGridLayer("/map"));
//	   visualizationView.addLayer(new PathLayer("move_base/NavfnROS/plan"));
//	   visualizationView.addLayer(new PathLayer("move_base_dynamic/NavfnROS/plan"));
	   visualizationView.addLayer(new NIFTILaserScanPointLayer("/scan2d"));
//	   visualizationView.addLayer(new PoseSubscriberLayer("simple_waypoints_server/goal_pose"));
//	   visualizationView.addLayer(new PosePublisherLayer("simple_waypoints_server/goal_pose", this));
	   visualizationView.addLayer(new RobotLayer("base_footprint"));
//	   visualizationView.setLayoutParams(new LayoutParams(300, 300));
	    
    NodeConfiguration nodeConfiguration =
        NodeConfiguration.newPublic(InetAddressFactory.newNonLoopback().getHostAddress(),
            getMasterUri());
//    nodeMainExecutor
//        .execute(virtualJoystickView, nodeConfiguration.setNodeName("virtual_joystick"));
    nodeMainExecutor.execute(visualizationView, nodeConfiguration.setNodeName("android/map_view"));
    nodeMainExecutor.execute(imageView, nodeConfiguration.setNodeName("android/image_view"));  
    nodeMainExecutor.execute(imagePublisher, nodeConfiguration.setNodeName("android/snapshot"));
    }
  
}