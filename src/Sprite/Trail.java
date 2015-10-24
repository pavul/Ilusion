/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * clase que deja un rastro de algun sprite, por lo general es algo asi como sombra
 * cuando un sprite esta en movimiento
 * @author pavulzavala
 */
public class Trail 
{
 
    
    private int x, y; //posiciones donde se debe de mostrar la imagen
    private float alpha; //indicador alpha que muestra la opacidad
    private Image img; //imagen que se debe de mostrar ( un frame del sprite )

//    private int lifespan; //tiempo en que dura de vida
//    private int maxLifespan;//tiempo total de vida que debe de durar
    private boolean visible;
    
    
    /**
     * constructor 1 
     */
    public Trail()
    {
//    this.lifespan=0;
//    this.maxLifespan=100;
    this.img=null;
    }

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

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

//    public int getLifespan() {
//        return lifespan;
//    }
//
//    public void setLifespan(int lifespan) {
//        this.lifespan = lifespan;
//    }
//
//    public int getMaxLifespan() {
//        return maxLifespan;
//    }
//
//    public void setMaxLifespan(int maxLifespan) {
//        this.maxLifespan = maxLifespan;
//    }
    
    public void setPosition(int x, int y)
    {
    this.x = x;
    this.y = y;
    }
 
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    /**
     * funcion que establece las variables con un valor inicial
     */
    public void reset()
    {
        this.x=0;
        this.y=0;
        this.img=null;
//        this.lifespan=0;
        this.alpha=1;
//        this.maxLifespan=100;
    }//
    
    /**
     * este metodo renderiza la imagen del sprite
     * @param g
     */
    public void draw(Graphics g)
    {
        if( visible && img != null )
        {
            Graphics2D g2 = (Graphics2D) g; //se obtiene el objeto g2 para renderizar
            
            //se obtiene el composite para restaurar el render despues
            Composite originalComposite= g2.getComposite(); 
            
            
        int type= AlphaComposite.SRC_OVER;
        g2.setComposite(AlphaComposite.getInstance(type, alpha));
        g2.drawImage(img, x, y,null);
        
        //por ultimo se establece el render para que siga la animacion igual
        g2.setComposite(originalComposite);
        }//
        
    }//
    
    /**
     * funcion que se usa para modificar el valor de alpha que va a estar dibujando
     * el trail
     * @param decrement 
     * @param spr 
     */
    public void decrementAlpha(float decrement, Sprite spr)
    {
    alpha-=decrement;
    
//        alpha=0.5f;
//    System.out.println("VALOR DE ALPHA: "+alpha );
    if(alpha < 0.5f && alpha > 0.3f  && img == null)
    {
    this.x = (int)spr.getX();
    this.y = (int)spr.getY();
    this.img = spr.getCurrentImageFrame();
    visible = true;
    }
    
    
        if(alpha <= 0)
        {
          alpha = 0;
          visible = false;
          reset();
        }
        
    }//
    
    
//    private  int numTrails;
//    private TrailEntity[] trails;
//    private Sprite spr;
// 
 /**
  * constructor que indica cuantas trazas habra del sprite dado
     * @param numTrails
     * @param spr
  */
// public Trail(int numTrails,Sprite spr )
// {
//     this.numTrails=numTrails;
//     this.spr=spr;
//     
//     if()
//     trails= new TrailEntity[this.numTrails];
//     
// }//const1
 
 
 
 
 
 
//    /**
//     * clase privada que tendra todos los datos necesarios para 
//     * mostrar los trails
//     */
//    private class TrailEntity
//    {
//   
//        
//    }//
//    
}//class
