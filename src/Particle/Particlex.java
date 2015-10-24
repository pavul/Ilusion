/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Particle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author pavulzavala
 */
public class Particlex 
{

    public static final int DRAW_IMAGE=1;
    public static final int DRAW_RECT=2;
    public static final int DRAW_OVAL=3;
    
    int x; //posicion x de la particula
    int y; //posicion y de la particula
    int w; //ancho 
    int h; //alto
    int currentDraw; //forma a dibujar
    
    float velX; //velocidad X
    float velY; //velocidad Y
    float acelX; //aceleracion en X
    float acelY; //aceleracion en Y
    float life;
    float lifespan; //vida 
    float decrementLife; //valor en que se decrementa la vida
    
    Color color;
    
    Image[] img;
    int currentFrame;
    
    
    /**
     * constructor 1, este solamente instancia la particula, los valores se
     * ponen con los getters y setters
     */
    public Particlex()
    {}
    
    
    /**
     * * constructor 2, este instancia la particula con los valores determinados
     * por default si no se establece que es lo que se va a dibujar, serenderiza 
     * la forma del ovalo
     * @param x
     * @param y
     * @param w
     * @param h
     * @param velX
     * @param velY
     * @param acelX
     * @param acelY
     * @param life
     * @param decrementLife
     * @param color 
     */
    public Particlex(int x, int y, int w, int h, 
            float velX, float velY, float acelX, float acelY, 
            float life, float decrementLife, Color color)
    {
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
    
    this.velX=velX;
    this.velY=velY;
    this.acelX=acelX;
    this.acelY=acelY;
    
    this.lifespan=life;
    this.decrementLife=decrementLife;
    this.color=color;
    
    this.currentDraw=Particlex.DRAW_OVAL;
    
    }//
    
    /**
     * metodo que actualiza el estado de la particula
     */
    public void update()
    {
        //se ejecuta el update solo cuando la particula tiene vida
//        if(isDead())
//            return;
    
//        System.out.println(" UPDATE PARTICLE ");
        lifespan -= decrementLife;
        velX += acelX;
        velY += acelY;
        x += velX;
        y += velY;
    }//
    
    
    /**
     * metodo que renderiza la particula por pantalla
     * @param g 
     */
    public void draw(Graphics g)
    {
    Graphics2D g2 =  (Graphics2D)g;
    
        switch(currentDraw)
        {
            case DRAW_OVAL:
                System.out.println(" DRAW OVAL ");
                g2.fillOval(x, y, w, h);
                break;
            case DRAW_RECT:
                g2.fillRect(x, y, w, h);
                break;
            case DRAW_IMAGE:
                g2.drawImage(img[currentFrame], x, y, null);
                break;
        }//
        
    }//
    
    /**
     * si el lifespan es menor a cero, entonces regresa true
     * @return 
     */
    public boolean isDead()
    {
        return (lifespan <=0);
    }//
    
    public void reset()
    {
        lifespan=life;
    }//reset

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getCurrentDraw() {
        return currentDraw;
    }

    public void setCurrentDraw(int currentDraw) {
        this.currentDraw = currentDraw;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getAcelX() {
        return acelX;
    }

    public void setAcelX(float acelX) {
        this.acelX = acelX;
    }

    public float getAcelY() {
        return acelY;
    }

    public void setAcelY(float acelY) {
        this.acelY = acelY;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }

    public float getLifespan() {
        return lifespan;
    }

    public void setLifespan(float lifespan) {
        this.lifespan = lifespan;
    }

    public float getDecrementLife() {
        return decrementLife;
    }

    public void setDecrementLife(float decrementLife) {
        this.decrementLife = decrementLife;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Image[] getImg() {
        return img;
    }

    public void setImg(Image[] img) {
        this.img = img;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }
    
}//class
