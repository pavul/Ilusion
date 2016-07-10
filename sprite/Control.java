package com.example.cameratest.donkey;

public interface Control 
{

	/*
	 * esta floaterfaz proporciona los metodos para
	 * que el objeto pueda ser controlado 
	 * */
	
	
	public void move(float speedX, float speedY);
	
	public void setX(float x);
	
	public void setY(float y);
	
	public void setPosition(float x, float y);
	
	public float getX();
	
	public float getY();
	
	public void moveRandom();
	
	public void moveSpeed();
	
	public void moveXSpeed();
	
	public void moveYSpeed();
	
	
	
}//para controlar los movimientos
