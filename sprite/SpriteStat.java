package com.example.cameratest.donkey;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class SpriteStat extends Sprite
{

	String text="";
	Paint p=new Paint();
	

	public SpriteStat(Bitmap strip, int frameWidth, int frameHeight) {
		super(strip, frameWidth, frameHeight);
		// TODO Auto-generated constructor stub
	}//const1

	public SpriteStat(Bitmap b) {
		super(b);
		// TODO Auto-generated constructor stub
	}//const2

	public SpriteStat(Bitmap[] frames) {
		super(frames);
		// TODO Auto-generated constructor stub
	}//const3

	public SpriteStat(int numFrames, int w, int h, Bitmap strip) {
		super(numFrames, w, h, strip);
		// TODO Auto-generated constructor stub
	}//const4

	public SpriteStat(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}//const5

	public SpriteStat(int[] ResourceId, Context context) {
		super(ResourceId, context);
		// TODO Auto-generated constructor stub
	}//const6

	
	//METODOS 

	public void setText(String text)
	{
		this.text=text;
	}
	public String getText()
	{return this.text;}
	
	
	
	public void drawBitmapTimes(Canvas c,int times)
	{
		for(int i=0;i<times;i++)
		{
			c.drawBitmap(this.frames[this.getcurrentFrame()], this.getX()+(this.getWidth()*i), this.getY(), null);
		}//for
		
	}//mostrar el bitmap tantos numeros de veces
	
	public void drawBitmapText(Canvas c)
	{
    c.drawBitmap(frames[this.getcurrentFrame()], this.getX(),this.getY(),null);
	c.drawText(this.text, this.getX()+this.getWidth()+10,this.getY()+this.getHeight()+10, this.p);	
	}//dibujar el estatus
	
	public void drawBitmapText(Canvas c, boolean textfirst)
	{
	c.drawText(this.text, this.getX(),this.getY(), this.p);	
	c.drawBitmap(frames[this.getcurrentFrame()], this.getX()+getStringWidth(this.text),this.getY(),null);
	}//dibujar el estatus
	
	public void drawBitmapText(Canvas c, boolean textfirst, int separation)
	{
	c.drawText(this.text, this.getX(),this.getY(), this.p);	
	c.drawBitmap(frames[this.getcurrentFrame()], this.getX()+getStringWidth(this.text)+separation,this.getY(),null);
	}//dibujar el estatus
	
	public void drawBitmapText(Canvas c, String text)
	{
    c.drawBitmap(frames[this.getcurrentFrame()], this.getX(),this.getY(),null);
	c.drawText(text, this.getX()+this.getWidth()+10,this.getY()+this.getHeight()+10, this.p);	
	}//dibujar el estatus
	
	public void drawBitmapText(Canvas c, int color)
	{	setColor(color);
	c.drawBitmap(frames[this.getcurrentFrame()], this.getX(),this.getY(),null);
	c.drawText(this.text, this.getX()+this.getWidth()+10,this.getY()+this.getHeight()+10, this.p);	
	}//dibujar el estatus
	
	public void drawBitmapText(Canvas c, int color, String text)
	{	setColor(color);
	c.drawBitmap(frames[this.getcurrentFrame()], this.getX(),this.getY(),null);
	c.drawText(text, this.getX()+this.getWidth()+10,this.getY()+this.getHeight()+10, this.p);	
	}//dibujar el estatus
	
	public void drawBitmapText(Canvas c, int color, String text, float x, float y)
	{setColor(color);
	c.drawBitmap(frames[this.getcurrentFrame()], x,y,null);
	c.drawText(text, x+this.getWidth()+10,y+this.getHeight()+10, this.p);	
	}//dibujar el estatus
	
	public void drawBitmapText(Canvas c, int color, String text, float x, float y, boolean textfirst)
	{
		setColor(color);
		
		if(textfirst)
		{
			c.drawText(text, x,y, this.p);	
			c.drawBitmap(frames[this.getcurrentFrame()], getStringWidth(text)+10,y,null);
		}
		else
		{

			c.drawBitmap(frames[this.getcurrentFrame()], x,y,null);
			c.drawText(text, x+this.getWidth()+10,y+this.getHeight()+10, this.p);	
				
		}
		}//dibujar el estatus
    	
	
	
	public void drawStaticText(Canvas c)
	{
		c.drawText(text, this.getX(), this.getY(), this.p);
	}//dibujar texto estatico
	
	public void drawStaticText(Canvas c,int color)
	{
		
		setColor(color);
		c.drawText(text, this.getX(), this.getY(), this.p);
	}//dibujar texto estatico
	
	public void drawStaticText( String t ,Canvas c)
	{
		c.drawText(t, this.getX(), this.getY(), this.p);
	}//dibujar texto estatico
	
	public void drawStaticText( String t ,Canvas c,int color)
	{
		setColor(color);
		c.drawText(t, this.getX(), this.getY(), this.p);
	}//dibujar texto estatico
	
	
	
	public void setColor(int color)
	{
		p.setColor(color);
	}
	
	public void setFont(Context c, String fontname)
	{
		p.setTypeface(Typeface.createFromAsset(c.getAssets(), fontname));
	}
	
	public void setSize(float size)
	{
		p.setTextSize(size);
	}
	
	public float getStringWidth(String s)
	{
		return p.measureText(s);
	}//saber el ancho de la cadena
	
}//SpriteStat
