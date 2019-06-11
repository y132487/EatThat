package com.example.dogsteal;

import com.example.dogstealdummy.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class SingleActivity extends Activity{

	SingleView vw;
	private int m_difficulty = 0;
	private boolean m_ingame = true;
	private boolean m_win=false;
	private boolean m_lose=false;
	private boolean m_onTouch=true;
	private MediaPlayer m_playbgm;
	private MediaPlayer m_barking;
	private MediaPlayer m_eating;
	private MediaPlayer m_caution;
	private MediaPlayer m_hungry;
	private MediaPlayer m_pangpare;
	private MediaPlayer m_winbgm;
	private MediaPlayer m_losebgm;
	private boolean runnable = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		vw = new SingleView(this);
		setContentView(vw);

		m_playbgm=MediaPlayer.create(this, R.raw.play_bgm);
		m_barking=MediaPlayer.create(this, R.raw.barking);
		m_eating=MediaPlayer.create(this, R.raw.eating);
		m_caution=MediaPlayer.create(this, R.raw.caution);
		m_hungry=MediaPlayer.create(this, R.raw.hungry);
		m_pangpare=MediaPlayer.create(this, R.raw.pangpare);
		m_winbgm=MediaPlayer.create(this, R.raw.winner_bgm);
		m_losebgm=MediaPlayer.create(this, R.raw.loser_bgm);
		
		Intent intent = getIntent();
		m_difficulty = intent.getIntExtra("difficulty", 0);
		
		m_playbgm.start();

	}
	
	protected void onDestroy(){
		super.onDestroy();
		if(m_playbgm!=null){
			m_playbgm.release();
			m_playbgm=null;
		}
		if(m_losebgm!=null){
			m_losebgm.release();
			m_losebgm=null;
		}
		if(m_winbgm!=null){
			m_winbgm.release();
			m_winbgm=null;
		}
		runnable=false;
	}
	
	public void mOnClick(View v){
		Intent single_intent = new Intent(this, SingleActivity.class);
		Intent menu_intent=new Intent(this,MainActivity.class);
		switch(v.getId()){
		
		case R.id.l_retry_btn:
			if(m_losebgm!=null){
				m_losebgm.release();
				m_losebgm=null;
			}
			single_intent.putExtra("difficulty",m_difficulty);
			startActivity(single_intent);	
			finish();
			break;
		case R.id.l_menu_btn:
			if(m_losebgm!=null){
				m_losebgm.release();
				m_losebgm=null;
			}
			startActivity(menu_intent);
			finish();
			break;
		case R.id.w_retry_btn:
			if(m_winbgm!=null){
				m_winbgm.release();
				m_winbgm=null;
			}
			single_intent.putExtra("difficulty",m_difficulty);
			startActivity(single_intent);	
			finish();
			break;
		case R.id.w_menu_btn:
			if(m_winbgm!=null){
				m_winbgm.release();
				m_winbgm=null;
			}
			startActivity(menu_intent);
			finish();
			break;
		}
	}

	class SingleView extends View{

		Bitmap background = BitmapFactory.decodeResource(getResources(),
				R.drawable.background);
		Bitmap dog[] = {
				BitmapFactory.decodeResource(getResources(), R.drawable.dog1),
				BitmapFactory.decodeResource(getResources(), R.drawable.dog2) };
		Bitmap human[] = {
				BitmapFactory.decodeResource(getResources(), R.drawable.human1),
				BitmapFactory.decodeResource(getResources(), R.drawable.human2) };
		Bitmap caution[] = {
				BitmapFactory.decodeResource(getResources(),
						R.drawable.caution1),
				BitmapFactory.decodeResource(getResources(),
						R.drawable.caution2) };

		Canvas canvas;
		
		Paint pnt = new Paint();
		Paint shade_pnt=new Paint();

		private BarThread barThread = new BarThread();
		private DogThread dogThread = new DogThread();

		private Thread aThread1;
		private Thread aThread2;

		private int hungrybar=550;
		private int feedbar=550;

		private boolean state = false;
		
		
		private int dogRotate = 0;
		private int humanRotate = 0;
		private int cautionRotate = 0;

		

		public SingleView(Context context) {
			super(context);
			
		}

		private float dp(int unit, float logic) {
			Resources res = getResources();
			DisplayMetrics dm = res.getDisplayMetrics();
			float pixel;
			pixel = TypedValue.applyDimension(unit, logic, dm);
			return pixel;
		}

		public boolean onTouchEvent(MotionEvent event) {

			if (m_onTouch) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					humanRotate = 1;
					if (state) {
						try {
							m_onTouch=false;
							if(m_playbgm!=null){
								m_playbgm.release();
								m_playbgm=null;
							}
							m_barking.start();
							runnable=false;
							Thread.sleep(2000);

						} catch (Exception a) {
						}
						m_ingame=false;
						m_lose=true;
						
					}
					return true;
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					m_eating.start();
					humanRotate = 0;
					hungrybar += 10;
					feedbar -= 5;
					return true;
				}
			}
			return false;
		}

		public void onDraw(Canvas _canvas) {

			canvas = _canvas;

			if (m_ingame) {
				
				drawIngame(canvas);
				
			} else if(m_win){
				runnable=false;
				winInflate();
			}else if(m_lose){
				runnable=false;
				loseInflate();
			}

		}
		
		public void winInflate(){
			LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			RelativeLayout relative=(RelativeLayout)inflater.inflate(R.layout.activity_win, null);
			setContentView(relative);
			m_winbgm.start();
			
		}
		
		public void loseInflate(){
			LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			RelativeLayout relative=(RelativeLayout)inflater.inflate(R.layout.activity_lose, null);
			setContentView(relative);
			m_losebgm.start();
		}
		

		
		

		public void drawIngame(Canvas _canvas) {
			
			canvas = _canvas;
			canvas.drawBitmap(background, 0, 0, null);
			drawHuman(humanRotate);
			drawDog(dogRotate);
			drawCaution(cautionRotate);

			
			drawHungryBar(hungrybar);
			drawFeedBar(feedbar);
			
			
			canvas.rotate(90);
			drawHungrytxt();
			drawFeedtxt();

			canvas.rotate(-90);
			

			dogThread.start();
			barThread.start();

			
			
			if (hungrybar >= 550) {
				hungrybar = 550;
			}

			if (dogRotate == 1) {
				state = true;
			}

			if (dogRotate == 0) {
				state = false;
			}
			
			if (hungrybar <=0) {

				try {
					m_onTouch=false;
					if(m_playbgm!=null){
						m_playbgm.release();
						m_playbgm=null;
					}
					m_hungry.start();
					runnable=false;
					Thread.sleep(2000);

				} catch (Exception e) {
				}

				m_ingame=false;
				m_lose=true;

			}

			if (feedbar <=0) {

				try {
					m_onTouch=false;
					if(m_playbgm!=null){
						m_playbgm.release();
						m_playbgm=null;
					}
					m_pangpare.start();
					runnable=false;
					Thread.sleep(2000);

				} catch (Exception e) {
				}

				m_ingame=false;
				m_win=true;

			}
			postInvalidate();
		}

		public void drawDog(int i) {
			canvas.drawBitmap(dog[i], dp(TypedValue.COMPLEX_UNIT_DIP, 10),
					dp(TypedValue.COMPLEX_UNIT_DIP, 350), pnt);
		}

		public void drawHuman(int i) {
			canvas.drawBitmap(human[i], dp(TypedValue.COMPLEX_UNIT_DIP, 10),
					dp(TypedValue.COMPLEX_UNIT_DIP, 80), pnt);
		}

		public void drawCaution(int i) {
			canvas.drawBitmap(caution[i], dp(TypedValue.COMPLEX_UNIT_DIP, 150),
					dp(TypedValue.COMPLEX_UNIT_DIP, 300), pnt);
		}

		public void drawHungrytxt() {
			pnt.setColor(Color.RED);
			pnt.setTextSize(dp(TypedValue.COMPLEX_UNIT_DIP, 16));
			canvas.drawText("HUNGRY", dp(TypedValue.COMPLEX_UNIT_DIP, 4),
					dp(TypedValue.COMPLEX_UNIT_DIP, -343), pnt);
		}

		public void drawFeedtxt() {
			pnt.setColor(Color.RED);
			pnt.setTextSize(dp(TypedValue.COMPLEX_UNIT_DIP, 16));
			canvas.drawText("FEED", dp(TypedValue.COMPLEX_UNIT_DIP, 4),
					dp(TypedValue.COMPLEX_UNIT_DIP, -319), pnt);
		}

		public void drawHungryBar(int i) {
			
			shade_pnt.setStyle(Paint.Style.FILL);
			shade_pnt.setShader(new LinearGradient(0,300,100,0,Color.BLUE,Color.BLACK,TileMode.CLAMP));
			canvas.drawRect(dp(TypedValue.COMPLEX_UNIT_DIP, 340),
					dp(TypedValue.COMPLEX_UNIT_DIP, 0),
					dp(TypedValue.COMPLEX_UNIT_DIP, 356),
					dp(TypedValue.COMPLEX_UNIT_DIP, i), shade_pnt);
			
		}

		public void drawFeedBar(int i) {
			shade_pnt.setStyle(Paint.Style.FILL);
			//shade_pnt.setColor(Color.BLUE);
			canvas.drawRect(dp(TypedValue.COMPLEX_UNIT_DIP, 317),
					dp(TypedValue.COMPLEX_UNIT_DIP, 0),
					dp(TypedValue.COMPLEX_UNIT_DIP, 333),
					dp(TypedValue.COMPLEX_UNIT_DIP, i), shade_pnt);
		}

		public void drawBar() {
			hungrybar -= 1;
			try {
				Thread.sleep(m_difficulty);
			} catch (Exception e) {
			}

		}

		class BarThread implements Runnable {

			public void start() {

				if (aThread1 == null) {
					aThread1 = new Thread(this);
					aThread1.start();

				}

			}

			public void stop() {
				if (aThread1 != null) {
					aThread1 = null;

				}
			}

			@Override
			public void run() {
				while (runnable) {

					drawBar();
				}
			}
		}

		class DogThread implements Runnable {

			public void start() {

				if (aThread2 == null) {
					aThread2 = new Thread(this);
					aThread2.start();

				}

			}

			public void stop() {
				if (aThread2 != null) {
					aThread2 = null;

				}
			}

			@Override
			public void run() {
				while (runnable) {
					for (int i = 0; i <= 1; i++) {

						int add = 1600;
						int random = (int) (Math.random() * 2900);
						int time = random + add;
						int ready = 1000;
						cautionRotate = i;
						if (cautionRotate == 1) {
							m_caution.start();
						}

						try {
							Thread.sleep(ready);
						} catch (Exception e) {
						}

						cautionRotate = 0;
						dogRotate = i;

						try {
							Thread.sleep(time - 1000);
						} catch (Exception e) {
						}

					}
				}

			}

		}
	}

}
