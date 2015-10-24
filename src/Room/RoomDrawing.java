/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import Exception.DrawException;
import java.awt.Graphics;

/**
 *
 * @author pavulzavala
 */
public interface RoomDrawing 
{
    /**
     * funcion que sirve para organizar el pintado del fondo
     * @param g
     * @throws DrawException 
     */
    public void drawBackground(Graphics g) throws DrawException;
    
    /**
     * funcion que sirve para renderizar los enemigos, jugadores, balas y demas objetos
     * @param g
     * @throws DrawException 
     */
    public void drawForeground(Graphics g) throws DrawException;
    
    /**
     * funcion que sirve para mostrar todos los iconos y datos de Heads Up Display
     * @param g
     * @throws DrawException 
     */
    public void drawHud(Graphics g) throws DrawException;  
    
}//interface
