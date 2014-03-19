package org.ros.android.android_tutorial_teleop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.ros.android.android_tutorial_teleop.geocam.GeoCamMobile;
import org.ros.internal.message.MessageBuffers;
import org.ros.message.MessageListener;
import org.ros.message.Time;
import org.ros.namespace.GraphName;
import org.ros.namespace.NameResolver;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;
import org.ros.node.topic.Subscriber;
import org.ros.rosjava_geometry.FrameTransform;
import org.ros.rosjava_geometry.FrameTransformTree;
import org.ros.rosjava_geometry.Transform;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class NIFTIImagePublisher implements NodeMain{

	private Publisher<sensor_msgs.CompressedImage> publisher;
	private Publisher<std_msgs.String> publisherMetaData;
	        
	private ConnectedNode connectedNode;
	private ChannelBufferOutputStream stream;
	
	private final NameResolver nameResolver = NameResolver.newRoot();
	private final FrameTransformTree frameTransformTree = new FrameTransformTree(nameResolver);
	
	public static final String TAG = "NIFTIImagePublisher";
	public static final String SOURCE_FRAME = "base_footprint";
	public static final String TOPIC_SCREENSHOT = "robot_camera/snapshot";
	public static final String TOPIC_METADATA = "robot_camera/metadata";
	
	public Uri saveImage(Context context, Bitmap mImage)
	{
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		mImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		byte[] mImageByte = byteArray;
		  
		ContentValues values = new ContentValues();
	    String name = String.valueOf(System.currentTimeMillis());
	    values.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpg");
	    values.put(MediaStore.Images.Media.TITLE, name);
	    values.put(MediaStore.Images.Media.DESCRIPTION, "");
	    values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
	    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
	    values.put(MediaStore.Images.Media.SIZE, mImage.getByteCount());
	    values.put(MediaStore.Images.Media.LATITUDE, 0.0);
	    values.put(MediaStore.Images.Media.LONGITUDE, 0.0);		
	    
	    
	    Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
	    
	    try {
	          OutputStream outStream = context.getContentResolver().openOutputStream(uri);
	          outStream.write(mImageByte);
	          outStream.close();
	          Log.d(GeoCamMobile.DEBUG_ID, "Saved image data into mediastore");
	    }
	    catch (Exception e) {
	          Log.d(GeoCamMobile.DEBUG_ID, "Exception while writing image: ", e);
	    }
	    
	    mImage = null;
	    Log.d(GeoCamMobile.DEBUG_ID, "Trying to force a GC");
	    System.gc();
	    
	    return uri;
	    
	}
	
	
	
	
	public void saveImageMetadata()
	{
		// TO DO
		/**
		String filename = "NIFTIImageMetadata.txt";
		File file = new File(Environment.getExternalStorageDirectory(), filename);
		FileOutputStream fos;
		byte[] data = new String(this.getMetadata()).getBytes();
		try {
		    fos = new FileOutputStream(file);
		    fos.write(data);
		    fos.flush();
		    fos.close();
		} catch (FileNotFoundException e) {
		    // handle exception
		} catch (IOException e) {
		    // handle exception
		}
		*/
	}
	
	public void publishImageMetadata()
	{
		String metaDataString = this.getMetadata();
		Log.d(TAG, "meta data = " + metaDataString);
		std_msgs.String str = publisherMetaData.newMessage();
        str.setData(metaDataString);
        publisherMetaData.publish(str);
        Log.d(TAG, "Image MetaData Published");
	}
	
	public String getMetadata()
	{
		String metadata = "";
		if(frameTransformTree != null)
		{
			FrameTransform ft = frameTransformTree.get(SOURCE_FRAME);
			Log.d(TAG, "is ft null? " + (ft == null));
			if(ft != null)
			{
				Log.d(TAG, "source frame = " + ft.getSourceFrame().toString());
				Log.d(TAG, "target frame = " + ft.getTargetFrame().toString());
				Time time = ft.getTime();
				Transform transform = ft.getTransform();
				metadata = "Time: " + time.toString() + "\n" + transform.toString();
			}
		
			
		}
		
		return metadata;
	}
	
	public void publishImage(byte[] image){
		sensor_msgs.CompressedImage img = publisher.newMessage();
		
		Time currentTime = connectedNode.getCurrentTime();
	    String frameId = "android/screenshot";
	    
		img.setFormat("jpeg");
		img.getHeader().setStamp(currentTime);
	    img.getHeader().setFrameId(frameId);
		
		stream = new ChannelBufferOutputStream(MessageBuffers.dynamicBuffer());
		try {
			stream.write(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		img.setData(stream.buffer().copy());
		stream.buffer().clear();
		
		
		publisher.publish(img);
		Log.d(TAG, "Image Published");
	}
	
	private void startTransformListener() {
	    Subscriber<tf.tfMessage> tfSubscriber = connectedNode.newSubscriber("tf", tf.tfMessage._TYPE);
	    tfSubscriber.addMessageListener(new MessageListener<tf.tfMessage>() {
	      @Override
	      public void onNewMessage(tf.tfMessage message) {
	        for (geometry_msgs.TransformStamped transform : message.getTransforms()) {
	          frameTransformTree.update(transform);
	        }
	      }
	    });
	  }

	@Override
	public void onError(Node arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShutdown(Node arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShutdownComplete(Node arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ConnectedNode connectedNode) {
		// TODO Auto-generated method stub
		this.connectedNode = connectedNode;
		publisher = connectedNode.newPublisher(TOPIC_SCREENSHOT, sensor_msgs.CompressedImage._TYPE);
		publisherMetaData = connectedNode.newPublisher(TOPIC_METADATA, std_msgs.String._TYPE);
		startTransformListener();
		
	}

	@Override
	public GraphName getDefaultNodeName() {
		// TODO Auto-generated method stub
		return GraphName.of("android/screenshot");
	}

}
