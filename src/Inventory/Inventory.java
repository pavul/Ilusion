/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;



/**
 * clase que guarda una serie de items 
 * @author pavulzavala
 */
public class Inventory 
{
    boolean visible; //indica si se debe de dibujar el inventario o no
    private List<Item> slots; //slots que tiene o items que va a tener
    int x; //posicion x del inventario
    int y; //posicion y del inventario
    int cursor; //indica que parte del inventario esta seleccionado
    BufferedImage bgSlot; //imagen de fondo del slot, los items van sobrepuestos de esta foto
    
    /**
     * constructor donde se le pasan los items que va a contener y las
     * posiciones X e y donde se va a dibujar en pantalla
     * @param slots
     * @param x
     * @param y
     */
    public Inventory(List<Item> slots, int x, int y)
    {
        this.slots=slots;
        this.x=x;
        this.y=y;   
    }//
    
    
    /**
     * funcion que dibuja el inventario en forma de grid
     * @param g 
     * @param rows 
     * @param columns 
     * @param width 
     * @param height 
     */
    public void drawGrid(Graphics g, int rows, int columns, int width, int height)
    {
        Graphics2D g2 = (Graphics2D)g;
        
        if(columns <=0 || rows <= 0 || slots.isEmpty())
        {return;}
        
        int index=0;
        int totalSlots= slots.size()-1;
        
        //renglones
        for(int i=0;i < rows;i++)
                {
                        //columnas
                        for(int j=0;j < columns;j++)
                        {
                         if(slots.get(index).getIcon() != null)
                         {
                             //dibujar imagen
                                  g2.drawImage(slots.get(index).getIcon(), 
                                  width * j, 
                                  height * i, 
                                  null);
                                  //cantidad que se tiene
                                  //etiqueta
                                  
                                  
                         }//if validacion si se dibuja
                             
                          if(index < totalSlots)
                          index++;
                        }//for cols
                }//for rows

    }//drawGrid
    
    
    /**
     * funcion que dibuja el inventario de manera de lista vertical
     * @param g 
     */
    public void drawLine(Graphics g)
    {
    
        if(slots.isEmpty())return;
        
        Graphics2D g2 = (Graphics2D)g;
        int padding=20;
        
        
        int len= slots.size();
        
        for( int i = 0 ; i < len; i++ )
        {
            int width = x + slots.get(i).getIcon().getWidth() + padding;
            int heigth = y + (slots.get(i).getIcon().getHeight() * i) + padding;
        
            g2.drawString(slots.get(i).getLabel(), width,heigth);
            if(slots.get(i).getIcon() != null)
            {
            g2.drawImage(slots.get(i).getIcon(), width + slots.get(i).getIcon().getWidth(),
                          heigth , null);
            }    
        }//for
        
    }//
    
}//class
