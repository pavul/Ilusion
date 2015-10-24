/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * clase que dibuja un mapa en pantalla, este puede ser de tiles o de una imagen
 */
public class Map 
{
    
    boolean visible;
    BufferedImage img;
    int[] map;
    int x;
    int y;
    int cols;
    int rows;
    int side; //lado del cuadrado del tile, siempre sera cuadrado
    
    /**
     * constructor 1, para setear los valores del mapa despues
     */
    public Map()
    {}
    
    /**
     * constructor 2, donde se establece el mapa con
     * todas las variables
     */
    public Map(int side, int x, int y, int cols, int rows, int[] map)
    {
    this.x=x;
    this.y=y;
    this.cols=cols;
    this.rows=rows;
    this.map=map;
    }//
    
    /**
     * funcion para dibujar el mapa en pantalla
     * @param g 
     */
    public void draw(Graphics g)
    {
    if(!visible)return;
    
    Graphics2D g2 = (Graphics2D)g;
        
        
    }//
    
    /**
     * funcion que dibuja un minimap en la pantalla del nivel
     * @param g 
     */
    public void drawMiniMap(Graphics g)
    {
    if(!visible)return;
    
    Graphics2D g2 = (Graphics2D)g;  
    }//
    
    
}//class
