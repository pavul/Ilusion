
import Level.Level;
import Room.ImageBackground;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pavulzavala
 */
public class SecondLevel extends Level
{
    public SecondLevel(int roomWidth, int roomHeight, int viewWidth, int viewHeight,
          ArrayList<ImageBackground> imgbg)
  {
  super(roomWidth, roomHeight, viewWidth, viewHeight, imgbg);
  }//
    

    @Override
    public void update() {
    }

    @Override
    public boolean initSprite() {
   return false; }

    @Override
    public boolean initBg() {
   return false; }

    @Override
    public boolean initHud() {
    return false;  }

    @Override
    public void renderBackground(Graphics g) {
        
        System.out.println(" RENDER : "+this.getClass().getName());
//        drawBgColor((Graphics2D)g, Color.GREEN);
        drawBgColor((Graphics2D)g, Color.GREEN, 0,0,
                640, 480);
        
    }

    @Override
    public void renderForeground(Graphics g) {
    }

    @Override
    public void renderHUD(Graphics g) {
    }

    @Override
    public boolean initSound() {
    return false;
    }

    @Override
    public void updateControl() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}//class
