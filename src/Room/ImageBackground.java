/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;

import java.awt.image.BufferedImage;

/**
 *clase que tiene una imagen con coordenadas X e Y
 * 
 * this class can have an image at certain coordinate X and Y
 * 
 */
public class ImageBackground 
{
   
    BufferedImage img;
    int x;
    int y;
    int w;
    int h;
    
    
    public ImageBackground(BufferedImage img, int x, int y)
    {
    this.img=img;
    this.x=x;
    this.y=y;
    this.w = img.getWidth();
    this.h=img.getHeight();
    }//

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
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
    
    
    /**
     * estanlece la posicion X e Y de la imagen de fondo
     * 
     * set initial posicion where image will be ( on X and Y axis )
     * 
     * @param x
     * @param y 
     */
    public void setPosition(int x, int y)
    {
    this.x=x;
    this.y=y;
    }
    
    
    
}//class
