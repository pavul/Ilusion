package com.example.cameratest.donkey;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewSample extends SurfaceView implements SurfaceHolder.Callback, Runnable
{

	Context con;
	SurfaceHolder sh;
	Thread gameThread;
	boolean isRunning;
	Paint p;
	
	
	Vector<Sprite> sprites=new Vector<Sprite>();
	
	
	
	public SurfaceViewSample(Activity context) 
	{
	super(context);
	this.con=context;
	getHolder().addCallback(this);
	}//const

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height){}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		 p=new Paint();
		 p.setColor(Color.WHITE);
		/* ---------- SET ALL DATA ---------- */
		//Background
		//Sprites
		//Music
	
		
		
		
	gameThread=new Thread(this);
	gameThread.start();
	isRunning=true;
	}//SUR Created

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		boolean retry=true;
		isRunning=false;
		
		while(retry)
		{
			try{
				gameThread.join();
				retry=false;
			}catch(InterruptedException e)
			{}
		}//while
	}//SUrface Destroyed
	
	public void onDraw(Canvas c)
	{
		c.drawColor(Color.BLACK);
    /* ---------- DRAW ALL SPRITES AND BACKGROUND HERE ---------- */
		//First the back ground
		//Then Sprites
		
		for(int i=0;i<sprites.size();i++)
		{
			Sprite sp=sprites.elementAt(i);
			//if(sp.isVisible)
				//sp.draw(c);
			
		}//draw all sprites
		
		
		//And Then Static Display
		
		
	}//ondraw

	@Override
	public void run() 
	{
		Canvas canvas;
		while(isRunning)
		{
			/*UPDATE HERE*/
			
			
			
			canvas=null;
			try{
				canvas=getHolder().lockCanvas(null);
				synchronized(getHolder())
				{
					this.onDraw(canvas);
				}//syn
			Thread.sleep(15);
			}
			catch(InterruptedException e)
			{}
			finally{
				    if(canvas !=null)
					getHolder().unlockCanvasAndPost(canvas);
			       }//finaly
		}//while
	 }//Run

	/* ---------- TOUCH EVENTS ---------- */
	public boolean onTouchEvent(MotionEvent event)
	{
		float xx=event.getX();
		float yy=event.getY();
		boolean handled=false;
		
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}//suich
		
		return handled;
	}//ontouch
	
	
	
	/* ---------- METHODS ---------- */
	
	
	
}//surfaceview