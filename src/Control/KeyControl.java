/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * clase que tiene la 
 * @author pavulzavala
 */
public class KeyControl implements KeyListener
{
    private final int NUMKEYS=256;

    private boolean []keys;
    private boolean []keyPress;
    
    /**
     * constructor 1, este solamente crea el arreglo de NUMKEYS,
     * no setea ningun keyListener
     */
     public KeyControl() 
        { 
            //se crea el areglo que contendra las teclas presionadas
            keys = new boolean[NUMKEYS];
            keyPress = new boolean[NUMKEYS];
            for(int i=0;i<NUMKEYS;i++)
            {
            keyPress[i]=true;
            }
//                c.addKeyListener(this); 
        }/// 
     
     
    /**
     * constructor 2 , agrega el componente que tiene el keyListener
     * @param c 
     */
      public KeyControl(Component c) 
        { 
                this();
                c.addKeyListener(this); 
        }/// 
        
        /** 
         * Checks whether a specific key is down 
         * @param keyCode The key to check 
         * @return Whether the key is pressed or not 
         */ 
        public boolean isKeyDown(int keyCode) 
        { 
                if (keyCode > 0 && keyCode < NUMKEYS) 
                { 
                        return keys[keyCode]; 
                } 
                
                return false; 
        }//
    
        /** 
         * Called when a key is pressed while the component is focused 
         * @param e KeyEvent sent by the component 
         */ 
        @Override
        public void keyPressed(KeyEvent e) 
        { 
                if (checkKeyCode(e)) 
                { 
                        keys[e.getKeyCode()] = true; 
                } 
        }//

    @Override
   public void keyReleased(KeyEvent e) 
        { 
                if (checkKeyCode(e))
                { 
                        keys[e.getKeyCode()] = false; 
                        keyPress[e.getKeyCode()] = true; 
                } 
        }//

   
    @Override
    public void keyTyped(KeyEvent e) {
        }//
    
    
    /**
     * funcion que regresa true si solo se presiona una sola vez una 
     * @param keyCode
     * @return 
     */
    public boolean isKeyPress(int keyCode)
    {
      if(isKeyDown(keyCode) && keyPress[keyCode])
      {
      keyPress[keyCode]=false;
      return true;
      }
    return false;
    }//
    
    
    /**
     * funcion que checa si la tecla presionada esta dentro del rango
     * @param e
     * @return 
     */
    private boolean checkKeyCode(KeyEvent e)
    {
    return (e.getKeyCode() > 0 && e.getKeyCode() < NUMKEYS);
    }//
    
    
    
    
}//class
