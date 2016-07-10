package com.example.cameratest.donkey;

import android.graphics.Canvas;

public class Camera 
{

	private float x=0;
	private float y=0;
	private float width=0;
	private float height=0;
	
	/**
	 * constructor of the camera, x and y is the posicion of the camera
	 * width and height is the width in pixels of the camera, and height
	 * is the Height in pixels of the camera
	 * i recomend to use the measures of the screen for width and height
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Camera(float x, float y, float width, float height)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		
	}//const


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	public float getWidth() {
		return width;
	}


	public void setWidth(float width) {
		this.width = width;
	}


	public float getHeight() {
		return height;
	}


	public void setHeight(float height) {
		this.height = height;
	}
	
	
	//FUNCIONES PARA LOS LIMITES INTERIORES
	private float rightInnerBoundary()
	{
		return this.x+(this.width*0.75f);
	}//limite de la derecha
	
	private float leftInnerBoundary()
	{
		return this.x+(this.width*0.25f);
	}//limite de la izquierda
	
	private float topInnerBoundary()
	{
		return this.y+(this.height*0.25f);
	}//limite de arriba
	
	private float bottomInnerBoundary()
	{
		return this.y+(this.height*0.75f);
	}//limite de abajo
	
	
	/**
	 * Example: camera.setBoundaries(room);
	 * @param r, is the room object who defines the limits the camera 
	 * I use this function in the update Loop after camera follow something,
	 * to set the boundaries in the limits of the room
	 * room limits
	 */
	public void setBoundaries(Room r)
	{
		if(this.x<r.getRoomLeft())
		{
			this.x=r.getRoomLeft();
		}
		
		if(this.y<r.getRoomTop())
		{this.y=r.getRoomTop();}
		
		if(this.x+this.width>r.getRoomLeft()+r.getRoomWidth())
		{
			this.x=r.getRoomLeft()+r.getRoomWidth()-this.width;
		}
		
		if(this.y+this.height>r.getRoomTop()+r.getRoomHeight())
		{
			this.y=r.getRoomTop()+r.getRoomHeight()-this.height;
		}
		
	}//mantener la camara dentro de los limites del room
	
	/**Example:  //ondraw(Canvas c){ camera.translate(c);}
	 * this function move the camera inside the room (or the game world or the stage)
	 * @param c, the canvas where we draw everything
	 */
	public void cameraTranslate(Canvas c)
	{
		c.translate(-this.x, -this.y);
	}
	
	
	/**
	 * Exampple: camera.follosSprite(hero);
	 * @param sq, sq is the Sprite that will be followed by the camera
	 * this function follows an sprite if the sprite is outside the boundaries of
	 * the camera.
	 * NOTE: the camera limits must be defined after this function
	 * 
	 * updateloop()
	 * {
	 * camera.followSprite(hero);
	 * camera.setBoundaries(room);//this check the limits of the camera and the room
	 * }
	 */
	public void followSprite(Sprite sq)
	{
		if(sq.getX()<this.leftInnerBoundary())
		{this.setX((float)Math.floor(sq.getX()-this.getWidth()*0.25));}
		
		if(sq.getY()<this.topInnerBoundary())
		{
			this.setY((float)Math.floor(sq.getY()-this.getHeight()*0.25));
		}
		
		if(sq.getX()+sq.getWidth()>this.rightInnerBoundary())
		{
			this.setX((float)Math.floor(sq.getX()+sq.getWidth()-this.getHeight()*0.75));
		}
		
		if(sq.getY()+sq.getHalfHeight()>this.bottomInnerBoundary())
		{
			this.setY((float)Math.floor(sq.getY()+sq.getHeight()-this.getHeight()*0.75));
		}
	}//followSprite
	
	
}//class
