/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * clase que para controlar al usuario con las teclas
 * @author pavulzavala
 */
public class KeyInputControl extends KeyAdapter
{

    @Override
    public void keyReleased(KeyEvent e) {
    int key= e.getKeyCode();
        System.out.println(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    int key= e.getKeyCode();
        System.out.println(key);  
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
}//class
