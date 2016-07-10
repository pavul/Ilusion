package com.example.cameratest.donkey;


import com.example.cameratest.donkey.Room;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;




public class ViewSample extends View 
{
	
	//Variables default for the class
	Activity act;
	boolean once=true;
	Paint p;
	
	
	public ViewSample(Activity act)
	{
		super(act);
        this.act=act;
		
		
    }//const

	
	
	public void onDraw(Canvas c)
	{
		if(once)
		{
			//CREATE HERE THE RESOURCES--> SPRITES< BACKGROUND, ETC...
			try 
			{
				createResources();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			
			
			
			once=false;
		}//inciar datos
		
		
		
		
	}//ondraw
	
	
	
	public boolean onTouchEvent(MotionEvent event)
	{
		float xx=event.getX();
		float yy=event.getY();
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_UP:
		break;
		
		case MotionEvent.ACTION_DOWN:
			break;
			
		case MotionEvent.ACTION_MOVE:
			break;
		
		}//suich
		
		
		return true;
	}//ontouch event
	
	
	
	//METODS HERE BELOW
	public void createResources() throws Exception
	{
		
	
		
		
	}//creando los recursos graficos
	
	
	
	
}//main class