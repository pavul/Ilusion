/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite;

/**
 *
 * @author Ilusion
 */
public interface Movement 
{
    
    /**
     * se mueve el sprite en el eje X segun  speedX
     * @param speedX 
     */
    public void moveSpeedX( float speedX );
    
    /**
     * se mueve el sprite en el eje Y segun speedY
     * @param speedY 
     */
    public void moveSpeedY( float speedY );
    
    /**
     * se mueve el sprite segun las velocidades que se tengan
     * @param speedX
     * @param speedY 
     */
    public void move(float speedX, float speedY);
    
    /**
     * se establece la posicion del sprite
     * @param speedX
     * @param speedY
     */
    public void setPosition(float speedX, float speedY);
    
    
    /**
     * moveTo(Sprite sprite, int speed)
	 * EXAMPLE: sprite.moveTo(enemy,5);<br /> 
	 * function that move the sprite to the direction of the sprite parameter
         * this function dont move the sprite on his own, thou must use 
         * sprite.movespeed();
     * @param sprite
     * @param speed
	 * */
    public void moveTo(Sprite sprite, int speed);
    
    
    /**
     * moveTo(float x, float y, int speed) 
	 * EXAMPLE: sprite.moveTo(100,100,5);<br />
	 * function that set the speed in X and Y axis to move toward a specific point
     * @param x
     * @param y
     * @param speed
	 * */
    public void moveTo(float x, float y, int speed);
    
    
    
}//interface
