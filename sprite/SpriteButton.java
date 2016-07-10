package com.example.cameratest.donkey;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SpriteButton extends Sprite
{
public static int idCount=0;
	private int id;
//Variables para Botones
 boolean active=false;
	


	public SpriteButton(Bitmap strip, int frameWidth, int frameHeight) {
	super(strip, frameWidth, frameHeight);
	//idCount++;
	//id=idCount;
}//const 1


public SpriteButton(Bitmap[] frames) {
	super(frames);
	//idCount++;
	//id=idCount;
}//const 2


public SpriteButton(int numFrames, int w, int h, Bitmap strip) 
{
	super(numFrames, w, h, strip);
	//idCount++;
	//id=idCount;
}//const 3


public SpriteButton(int x, int y, int width, int height) {
	super(x, y, width, height);
	//idCount++;
	//id=idCount;
}//const 4


public SpriteButton(int[] ResourceId, Context context) {
	super(ResourceId, context);
	//idCount++;
	//id=idCount;
}//const 5


	public SpriteButton(Bitmap b) {
		super(b);
		//idCount++;
		//id=idCount;
	}//const 6

	@Override
public void draw(Canvas c)
{
	if(isVisible)
	{
if(active)
	{
	c.drawBitmap(frames[1], this.getX(),this.getY(),null);
	}
else
	{
	c.drawBitmap(frames[0], this.getX(),this.getY(),null);
	}
	}//visible
}//dibujar el boton

	

	//METODO QUE REGRESA LA POSICION SI SE ACTIVA O NO EL SPRITE, USADO PARA BOTONES DE 2 BITMAP
/*public	void setSelectedFrame()
	{
	 //(active)?this.currentFrame=0:this.currentFrame=1;
	if(active)
			currentFrame=0;
		else
			currentFrame=1;*/
	//}// Regresa el bitmap del BotonSprite, dependiendo si esta activado o no
     // el esprite debe de contener por lo menos 2 frames
	
	
public void Activate(boolean activate)
{
	this.active=activate;
}

public boolean getActivate()
{
	return this.active;
}

@Override
public boolean isTouched(float x, float y)
{
	boolean isTouched=false;
	
	if(x>=this.getX() && x<=this.getX()+this.getWidth() && y>=this.getY() && y<=this.getY()+this.getHeight())
		   isTouched=true;
	return isTouched;
}
	

public int getId()
{
	return id;
}


}//SpriteButton
