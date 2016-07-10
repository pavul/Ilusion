package com.example.cameratest.donkey;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SpriteLoadingBar extends Sprite
{
	
	public int maxvalue=100;
	public int loadValue=1;

	
	
	public SpriteLoadingBar(Bitmap[] frames) 
	{
		super(frames);
    frames[0]=Bitmap.createScaledBitmap(frames[0], maxvalue, frames[0].getHeight(), true);
	}//tiene que ser un arreglo de bitmap de 2 dimensiones, uno para el frente y el otro para el fondo

	public SpriteLoadingBar(Bitmap[] frames, int barWidth, int barHeight)
    {
		super(frames);
		maxvalue=barWidth;
		frames[0]=Bitmap.createScaledBitmap(frames[0], barWidth, barHeight, true);
	}//tiene que ser un arreglo de bitmap de 2 dimensiones, uno para el frente y el otro para el fondo

	
	
	
	public void setLoading(int increment)
	{
		if(increment<1)
			{System.out.println("el incremento no puede ser menor a 1");
			return;
			}
		
     
      if(loadValue<frames[0].getWidth())
      {
    	loadValue+= increment;
    	if(loadValue>=frames[0].getWidth()){loadValue=frames[0].getWidth();}
	//int wid=frames[1].getWidth();
	int hei=frames[1].getHeight();
	frames[1]=Bitmap.createScaledBitmap(frames[1],loadValue, hei, true);
      }//if
    }//aqui es donde se muestra 
	
	public void drawLoadging(Canvas c)
	{
		c.drawBitmap(this.frames[0], this.getX(), this.getY(), null);
		if(loadValue>0)
		c.drawBitmap(this.frames[1], this.getX(), this.getY(), null);
	}
	
	
	public void setMaxValue(int value)
	{
		maxvalue=value;
		frames[0]=Bitmap.createScaledBitmap(frames[0], value, frames[0].getHeight(), true);
	}//mavalue
	
	
}//class
