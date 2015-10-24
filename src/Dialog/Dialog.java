/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialog;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * clase para mostrar dialogos entre personajes o para introduccion de texto del juego
 * al inicio o final del juego
 * @author pavulzavala
 */
public class Dialog 
{
    
    private boolean visible;
    private int x;
    private int y;
    private int w;
    private int h;
    
    private Color color;
    private Color textcolor;
    
    private float alpha;
    private BufferedImage img;
    
    private String[] messages; //string con los mensajes a mostrar
    private int currentIndex; //indice del mensaje a mostrar
    
    private Dialog()
    {
    currentIndex=0;
    visible=false;
    alpha = 1f;
    color=Color.BLUE;
    textcolor=Color.WHITE;
    }//const 1
    
    /**
     * constructor que crea un cuadro de dialogo
     * @param x posicion x del dialogo
     * @param y posicion y del dialogo
     * @param w ancho del cuadro de dialogo
     * @param h alto del cuadro de dialogo
     * @param color color del dialogo
     */
    public Dialog(int x, int y, int w, int h, Color color)
    {
        this();
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.color=color;
    }//const 2
    
    
    /**
     * constructor que crea un cuadro de dialogo
     * @param x posicion x del dialogo
     * @param y posicion y del dialogo
     * @param w ancho del cuadro de dialogo
     * @param h alto del cuadro de dialogo
     * @param color color del dialogo
     * @param alpha
     */
    public Dialog(int x, int y, int w, int h, Color color, float alpha)
    {
        this(x,y,w,h,color);
        this.alpha=alpha;
    }//const 3
    
    
      /**
     * constructor que crea un cuadro de dialogo
     * @param x posicion x del dialogo
     * @param y posicion y del dialogo
     * @param w ancho del cuadro de dialogo
     * @param h alto del cuadro de dialogo
     * @param color color del dialogo
     * @param alpha
     */
    public Dialog(int x, int y, int w, int h, BufferedImage img)
    {
        this();
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.img=img;
    }//const 3
    
    
    /**
     * funcion para dibujar el dialog en pantalla
     * @param g 
     */
    public void draw(Graphics g)
    {
        if(!visible)return;
        
        Graphics2D g2 =(Graphics2D)g;
       
        g2.setColor(color);
        
        if(img == null)
        g2.fillRect(x, y, w, h);
        else
        g2.drawImage(img, x, y, null);
        
//        Color originalColor = g2.getColor();
        
        g2.setColor(textcolor);
        g2.drawString(messages[currentIndex], x, y+15);//cambiar ese 15 despues
    }//
    
    public void drawWithAlpha(Graphics g)
    {
        
        if(!visible)return;
        
        Graphics2D g2 =(Graphics2D)g;

        //se hace un nuevo composite con el alpha
        Composite originalComposite = g2.getComposite();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f);
        g2.setComposite(c);
        
        if(img == null)
        g2.fillRect(x, y, w, h);
        else
        g2.drawImage(img, x, y, null);
        
        
         Color originalColor = g2.getColor();
        
        g2.setColor(textcolor);
        g2.drawString(messages[currentIndex], x, y);
        
        g2.setColor(originalColor);
        
        //se restablece el composite
        g2.setComposite(originalComposite);
        
    }//
    
    public boolean nextMessage()
    {
    currentIndex++;
    return (currentIndex == messages.length);
    }
    
    
    /*
    getters and setters
    */
    
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

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

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        currentIndex = 0;
        this.messages = messages;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
    
    
    /*
    /getters and setters
    */
    
}//class
