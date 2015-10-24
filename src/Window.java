
import Particle.Particle;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pavulzavala
 */
public class Window extends JFrame
{
    private static final long serialVersionUID = 3861444957045422616L;
     
    private ArrayList<Particle> particles = new ArrayList<Particle>(500);
 
    private int x = 0;
    private int y = 0;
    private BufferStrategy bufferstrat = null;
    private Canvas render;
    
     public static void main(String[] args)
    {
        Window window = new Window(450, 280, "Particles: ");
        window.pollInput();
        window.loop();
    }//main
     
     
     
     public void pollInput()
    {
        render.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                addParticle(true);addParticle(false);addParticle(true);
                addParticle(false);addParticle(true);addParticle(false);
             }//

            @Override
            public void mousePressed(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
 
 
        });
    }
 
    public Window( int width, int height, String title){
        super();
        setTitle(title);
        setIgnoreRepaint(true);
        setResizable(false);
 
        render = new Canvas();
        render.setIgnoreRepaint(true);
        int nHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int nWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        nHeight /= 2;
        nWidth /= 2;
 
        setBounds(nWidth-(width/2), nHeight-(height/2), width, height);
        render.setBounds(nWidth-(width/2), nHeight-(height/2), width, height);
 
        add(render);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        render.createBufferStrategy(2);
        bufferstrat = render.getBufferStrategy();
    }//
     
    
    
    //This is a bad game loop example but it is quick to write and easy to understand
    //If you want to know how to do a good one use the all knowing google.
    public void loop(){
        while(true){
 
            update();
            render();
 
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 
    public void update(){
        Point p = render.getMousePosition();
        if(p !=null ){
            x = p.x;
            y = p.y;
        }
        for(int i = 0; i <= particles.size() - 1;i++){
            if(particles.get(i).update())
                particles.remove(i);
        }
    }
 
    public void render(){
        do{
            do{
                Graphics2D g2d = (Graphics2D) bufferstrat.getDrawGraphics();
                g2d.fillRect(0, 0, render.getWidth(), render.getHeight());
 
                renderParticles(g2d);
 
                g2d.dispose();
             }while(bufferstrat.contentsRestored());
              bufferstrat.show();
        }while(bufferstrat.contentsLost());
    }
 
    public void renderParticles(Graphics2D g2d){
        for(int i = 0; i <= particles.size() - 1;i++){
            particles.get(i).draw(g2d);
        }
    }//
    
    
    
    public void addParticle(boolean bool){
                int dx,dy;
                if(bool){
                    dx = (int) (Math.random()*5);
                    dy = (int) (Math.random()*5);
                }
                else{
                    dx = (int) (Math.random()*-5);
                    dy = (int) (Math.random()*-5);
                }
                int size = (int) (Math.random()*12);
                int life = (int) Math.random()*(120)+380;
                particles.add(new Particle(x,y,dx,dy,size,life,Util.Util.getRandomColor()));
            }//
    
    
    
}//class
