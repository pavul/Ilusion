/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.awt.image.BufferedImage;

/**
 * clase de items, este puede ser items, skills, spells, etc
 * @author pavulzavala
 */
public class Item 
{
    int currentQty; //cantidad que hay actualmente de este item
    int minQty; //cantidad minima que acepta este item
    int maxQty; //cantidad maxima que puede haber de este item
    
    BufferedImage icon;//el icono del item
    String label; //etiqueta de texto de este item, por ejemplo pocion
    
    
    /**
     * constructor 1, este deja libre al usuario de establecer los valores con los
     * setters
     */
    public Item(){}//
    
    
    /**
     * constructor 2, crea el item y le establece sus valores iniciales
     * @param currentQty
     * @param minQty
     * @param maxQty
     * @param icon
     * @param label 
     */
    public Item(int currentQty, int minQty, int maxQty, BufferedImage icon, String label)
    {
    this.currentQty=currentQty;
    this.minQty=minQty;
    this.maxQty=maxQty;
    this.icon=icon;
    this.label=label;
    }//

    public int getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(int currentQty) {
        this.currentQty = currentQty;
    }

    public int getMinQty() {
        return minQty;
    }

    public void setMinQty(int minQty) {
        this.minQty = minQty;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(int maxQty) {
        this.maxQty = maxQty;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
    
    
}//
