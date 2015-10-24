/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * clase que muestra la transicion de una escena, esta puede ser una pared negra que va de derecha
 * a izquierda y sirve para hacer los cambios de nivel, o cuando se termina un juego, etc.
 * @author pavulzavala
 */
public class Transition 
{
    
    //tipos de transicion que se pueden hacer
    public final static int RECT_LEFTTORIGTH=0; //un rect de izq a der
    public final static int RECT_RIGTHTOLEFT=1; //un rec de der a izq   
    public final static int RECT_TOPTODOWN=2; //un rec de arriba a abajo
    public final static int RECT_CENTERTOROOM=3; //un rect desde el centro a los limites del room
    public final static int RECT_SQUAREGRID=4; //un grid de cuadros del room
    public final static int RECT_FADING=5; //un rectangulo que esta invisible y se va haciendo opaco 
    
   //estados que puede tener la transicion
    public final static int STATE_HIDING=0;
    public final static int STATE_HIDE=1;
    public final static int STATE_SHOWING=2;
    public final static int STATE_SHOWED=3;
    
    //valores maximo y minimo de la variable alpha
    public final static int ALPHA_MAXVALUE=255;
    public final static int ALPHA_MINVALUE=0;
    
    
    private float alpha; //transparencia de la transicion
    private int increment; //valor de incremento de las cortinas
    private boolean on; //indica si se esta llevando a cabo o no la transicion ON / OFF
    private int currentState; //estado actual en que se encuentra la transicion
    private int transitionX;
    private int transitionY;
    private int transitionWidth;
    private int transitionHeight;
    private int transitionType; //tipo de animacion que se hace
    private int currentIncrement;
    private int currentDuration;
    private int hideDuration; //tiepo que dura la transicion en estado escondida ( HIDE )
    private Color color; //color que debe de tener la transicion
    
    
    /**
     * constructor 1, establece los valores de la transicion
     * @param transitionX
     * @param transitionY
     * @param increment
     * @param transitionWidth
     * @param transitionHeight
     * @param transitionType
     * @param color
     * @param hideDuration
     */
public Transition(int transitionX, int transitionY, int transitionWidth, int transitionHeight,
                  int increment,int transitionType, Color color, int hideDuration)
{
this.alpha=Transition.ALPHA_MINVALUE;
this.transitionX=transitionX;
this.transitionY=transitionY;
this.increment=increment;
this.transitionWidth=transitionWidth;
this.transitionHeight=transitionHeight;
this.transitionType=transitionType;
this.color=color;
this.hideDuration=hideDuration;
}//
    
    
    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getTransitionWidth() {
        return transitionWidth;
    }

    public void setTransitionWidth(int transitionWidth) {
        this.transitionWidth = transitionWidth;
    }

    public int getTransitionHeight() {
        return transitionHeight;
    }

    public void setTransitionHeight(int transitionHeight) {
        this.transitionHeight = transitionHeight;
    }

    public int getTransitionType() {
        return transitionType;
    }

    public void setTransitionType(int transitionType) {
        this.transitionType = transitionType;
    }

    public int getTransitionX() {
        return transitionX;
    }

    public void setTransitionX(int transitionX) {
        this.transitionX = transitionX;
    }

    public int getTransitionY() {
        return transitionY;
    }

    public void setTransitionY(int transitionY) {
        this.transitionY = transitionY;
    }

    public int getCurrentIncrement() {
        return currentIncrement;
    }

    public void setCurrentIncrement(int currentIncrement) {
        this.currentIncrement = currentIncrement;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCurrentDuration() {
        return currentDuration;
    }

    public void setCurrentDuration(int currentDuration) {
        this.currentDuration = currentDuration;
    }

    public int getHideDuration() {
        return hideDuration;
    }

    public void setHideDuration(int hideDuration) {
        this.hideDuration = hideDuration;
    }

  
    
    
    
    /**
     * dibuja la transicion en pantalla, ideal para poner una especie de cortina
     * al querer cambiar de un room a otro
     * @param g2
     * @param roomWidth
     * @param roomHeight 
     */
    public void drawTransition( Graphics2D g2 )
    {
        //si la transicion esta apagada no se procesa el renderizado en pantalla
     if(!on)
     {return;}
         
        switch(transitionType)
        {
            case RECT_LEFTTORIGTH:
                leftToRightTransition( g2 );
                break;
            case RECT_RIGTHTOLEFT:
                break;
            case RECT_TOPTODOWN:
                break;
            case RECT_CENTERTOROOM:
                break;
            case RECT_SQUAREGRID:
                break;
            case RECT_FADING:
                break;
            default:
                leftToRightTransition( g2 );
                break;
        }//suich
        
        
    }//drawTransition
    
    
    
    
    /**
     * metodo que establece los valores de la transicion
     */
    public void reset()
    {
    this.on=false;
    this.currentIncrement=0;
    this.currentState=Transition.STATE_HIDING;
    this.currentDuration=0;
    }
 
    
    
    /**
     * METODOS DE TRANSICION 
     */
   
/**
 *metodo de transicion de izquierda a derecha
 * @param g2
 * @param roomWidth
 * @param roomHeight 
 */
private void leftToRightTransition(Graphics2D g2)
{
    //se establece el color de la transicion
    g2.setColor(color);
    
    switch(currentState)
    {
        case Transition.STATE_HIDING:
             currentIncrement += increment;
        if(currentIncrement >= transitionWidth)
        {
            currentIncrement=transitionWidth;
            currentState=Transition.STATE_HIDE;
        }
            break;
        case Transition.STATE_HIDE:
            currentDuration += 1;
            if(currentDuration >= hideDuration)
            {
            currentState = Transition.STATE_SHOWING;
            }
            break;
        case Transition.STATE_SHOWING:
            currentIncrement -= increment;
        if(currentIncrement <= 0)
        {
//            currentIncrement=0;
            currentState=Transition.STATE_SHOWED;
        }
            break;
        case Transition.STATE_SHOWED:
//            on=false;
            reset();//aqui ya se detiene la animacion
            break;
    }//suich
    
    //finalmente se dibuja la transicion
    g2.fillRect(transitionX,transitionY, currentIncrement, transitionHeight);

    

}//


/**
 * metodo de transicion de derecha a izquierda
 * @param g2
 * @param roomWidth
 * @param roomHeight 
 */
private void rightToLeftTransition(Graphics2D g2,  int roomWidth, int roomHeight)
{
     increment--;
    
g2.setColor(Color.black);
g2.fillRect(0,0,roomWidth+increment, roomHeight);

if(increment <= 0)
{
increment =0;
//complete=true;
}


}//


   /**
     * /METODOS DE TRANSICION 
     */


}//class
