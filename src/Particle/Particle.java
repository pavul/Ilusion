/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Particle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author pavulzavala
 */
public class Particle {
    
    int x; //coor x
    int y; //coor y
    int sx; //speed x
    int sy; //speed y
    int size; //tamano
    int life; //tiempo de vida
    Color color;
    
    /**
     * constructor que define todos los parametros de la particula
     * @param x
     * @param y
     * @param sx
     * @param sy
     * @param size
     * @param life
     * @param c 
     */
    public Particle(int x, int y, int sx, int sy, int size, int life, Color c){
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        this.size = size;
        this.life = life;
        this.color = c;
    }//
    
          
    /**
     * metodo que hace la actualizacion de la posicion o algun otro dato
     * de la particula
     * @return 
     */
    public boolean update()
    {
        x += sx;
        y += sy;
        life--;
        return life <= 0;
    }//
    
    /**
     * metodo que renderiza la particula por pantalla
     * @param g 
     */
    public void draw(Graphics g)
    {
      Graphics2D g2d = (Graphics2D) g.create();
 
        g2d.setColor(color);
        g2d.fillRect(x-(size/2), y-(size/2), size, size);
 
        g2d.dispose();
    }//
    
}//class
