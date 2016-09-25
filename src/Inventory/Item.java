/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.awt.image.BufferedImage;

/**
 * esta es una clase generica que puede indicar un item, un skill, 
 * alguna magia o cualquier otra forma que se puede mostrar en 
 * pantalla
 * 
 * 
 * this is a generic class who can be item, skill, spell, etc, 
 * to show that data in an inventory
 * 
 * NOTE: if you want to crate a grid of skills, then your items
 * need to be pictures and/or data of skills
 * 
 * @author pavulzavala
 */
public class Item 
{
    int itemId; //item id usefull when we click on it
    int currentQty; //current quantity of this item, skill, etc
    int minQty; //minimal quantity who is required for this item
    int maxQty; //maximal quantity who is required for this item
    
    BufferedImage icon;//icon of the item
    String label; //label, or text of this item
    String desc; //description of this item, skill, spell, etc
    String attr; //attribute of this item, skill, spell, etc
    
    /**
     * constructor 1, este deja libre al usuario de establecer los valores con los
     * setters
     * 
     * this contrusctor allow the programmer stablish all the values
     * using setters
     */
    public Item(){}//
    
    
    /**
     * constructor 2, crea el item y le establece sus valores iniciales
     * 
     * this constructor creates a new item and stablish its default 
     * values
     * 
     * @param itemId
     * @param currentQty
     * @param minQty
     * @param maxQty
     * @param icon
     * @param label 
     * @param desc 
     * @param attr 
     */
    public Item(
            int itemId ,
            int currentQty ,
            int minQty ,
            int maxQty ,
            BufferedImage icon ,
            String label, 
            String desc ,
            String attr)
    {
    this.itemId = itemId ;
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
