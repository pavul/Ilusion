/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * clase que se usa principalmente para mostrar algunos datos como:
 * el dano provocado a los enemigos o jugadores, dinero recabado, experiencia,etc
 * es el tipico mensaje que sale cuando se golpea a un enemigo y sale un numero
 * indicando el dano hecho, este se mueve hacia arriba y despues de un tiempo desaparece
 * @author pavulzavala
 */
public class SpriteMessage 
{
    
    /**
     * direcciones de movimiento
     */
    public static final int RIGTH =1;
    public static final int LEFT =2;
    public static final int UP =3;
    public static final int DOWN =4;
    
    /**
     * COLORES
     * 
     * BLANCO: exp | dano 
     * ROJO: 
     * NEGRO:
     * NARANJA: dano x fuego |
     * VERDE: reccuperar mp
     * AZULCIELO: dano x hielo |
     * AZULMARINO: dano por agua | recuperar MP 
     * AMARILLO: dano x paralisis | dinero |
     * GRIS OSCURO: dano x undead |
     * ROSA: magias de proteccion |
     * PURPURA: dano x veneno |
     */
    
    int x; //coordenada x
    int y; //coordenada y
    int duration; //tiempo que va a durar visible este objeto
    int spd; //velocidad a la que se mueve este objeto
    String msg; //mensaje que se mostrara
    Color color; //color del mensaje
    boolean visible;
    
    /**
     * constructor 1, objeto sin valor alguno, salvo el color que por
     * defecto es blanco
     */
    public SpriteMessage()
    {
    color = Color.WHITE;
    }//const #1
    
    /**
     * constructor 2, crea el mensaje definiendo todos sus valores al instanciarlos
     * @param x
     * @param y
     * @param duration
     * @param msg
     * @param color 
     */
    public SpriteMessage(int x, int y, int duration, String msg, Color color)
    {
    
        this.x=x;
        this.y=y;
        this.duration=duration;
        this.msg=msg;
        this.color = color;
    
    }//const #2

    
     /**
     * GETTERS & SETTERS
     */
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    } 
    /**
     * /GETTERS & SETTERS
     */
    
    
    public void setPosition(int x, int y)
    {
    this.x=x;
    this.y=y;
    }
    
    /**
     * funcion que dibuja el mensje si esta visible
     * @param g2 
     */
    public void draw(Graphics2D g2)
    {
    if (visible) {
        g2.setColor(color);
        g2.drawString(msg, x, y);
        }
    }//draw
      
    /**
     * fucnion para decrementar la duracion del objeto, cuando la duracion
     * llege a 0 ( cero ), esta desaparece de pantalla ( se invisibiliza )
     * NOTA: este metodo debe de ponerse en el metodo update del nivel
     * @param decrement 
     */
    public void decremenetDuration(int decrement)
    {
        duration -= decrement;
        if(duration <=0)
        {
        duration = 0;
        visible = false;
        }
        
    }
    
    /**
     * funcion que mueve el sprite segun la velocidad de los ejes en las
     * coordenadas X e Y
     * por ejemplo move(5,5); // se movera 5 pixeles a la derecha y 5 pixeles hacia abajo
     * @param spdx
     * @param spdy 
     */
    public void move(int spdx, int spdy)
    {
    this.x+=spdx;
    this.y+=spdy;
    }//move
    
    /**
     * funcion para mover el objeto segun la direccion que se le indique
     * la velocidad a la que se mueve es la que esta predefinida en el objeto,
     * esta se setea en el constructor o con su setter
     * por ejemplo move(SpriteMessage.RIGTH);
     * @param direction 
     */
    public void move(int direction)
    {
    
        switch(direction)
        {
        
            case RIGTH:
                move(spd,0);
                break;
            case LEFT:
                move(-spd,0);
                break;
            case UP:
                move(0,spd);
                break;
            case DOWN:
                move(0,-spd);
                break;
//            case RIGTH:
//                break;
            
            
        }//
        
    }//move #2
    
    
}//class
