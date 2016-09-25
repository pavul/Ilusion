
import Particle.Particle2;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
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
public class Window2 extends JFrame {
    private static final long serialVersionUID = -2320451743489595531L;
 
    private ArrayList<Particle2> particles = new ArrayList<Particle2>(500);
 
    private int x = 0;
    private int y = 0;
    private BufferStrategy bufferstrat = null;
    private Canvas render;
 
    private Random rnd = new Random(); //used to generate random numbers
    private Color c = Color.blue; // the default particle color
    private boolean running = true; // should we update?
 
    public static void main(String[] args)
    {
        Window window = new Window(450, 280, "Particles: ");
        window.pollInput();
        window.loop();
    }
 
    public Window2( int width, int height, String title){
        super();
        setTitle(title);
        setIgnoreRepaint(true);
        setResizable(true);
 
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
    }
 
    public void pollInput()
    {
        render.addMouseListener(new MouseListener(){
 
            public void mouseClicked(MouseEvent e) {
                addParticle();addParticle();addParticle();
                addParticle();addParticle();addParticle();
            }
 
            
            //METODO 1
//            public void addParticle(){//play with this method
//                Particle2 p = new Particle2(x,y,0,0,0,0,c);
//                p.setVel(random(7),random(7));
//                p.setAcc(random(.02),random(.02));
//                p.setLife(randomPlus(150)+150);
//                p.setSize(randomPlus(25)+25, randomPlus(25)+25);
//                p.setMaxSize(50,50);
//                p.setGrowth(random(2), random(2));
//                p.setUltSize(true);
//                particles.add(p);
//            }
 
 // METODO #2           
//            public void addParticle(){//play with this method
//                Particle2 p = new Particle2(x,y,0,0,0,0,c);
//                p.setVel(random(4),random(4));
//                p.setAcc(0,randomPlus(.2)+.1);
//                p.setLife(randomPlus(150)+150);
//                p.setSize(25, 25);
//                p.setMaxSize(25,25);
//                p.setGrowth(-randomPlus(.2)-.5, -randomPlus(.2)-.5);
//                p.setSizeDeault(true);
//                //p.setUltSize(false);
//                particles.add(p);
//            }
           
            //MEETODO 3
            public void addParticle(){//play with this method
            Particle2 p = new Particle2(x+random(32),y+random(32),0,0,0,0,c);
            p.setVel(random(1),random(1));
            p.setAcc(0,-randomPlus(.04)-.02);
            p.setLife(randomPlus(150)+550);
            p.setSize(16, 16);
            p.setMaxSize(25,25);
            p.setGrowth(-.1, -.1);
            p.setSizeDeault(true);
            //p.setUltSize(false);
            particles.add(p);
        }
            
            
            @Override
            public void mouseEntered(MouseEvent e) {
 
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
 
            }
 
            @Override
            public void mousePressed(MouseEvent e) {
 
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
 
            }

 
        });
 
        render.addKeyListener(new KeyListener(){//keylistener
 
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if(code == 'P'){
                    if(running)
                        running = false;
                    else
                        running = true;
                }
                if(code == ' '){//new random color
                    c = new Color((int)randomPlus(255),(int)randomPlus(255),(int) randomPlus(255));
                }
            }
 
            public void keyReleased(KeyEvent e) {
 
            }
 
            public void keyTyped(KeyEvent e) {
 
            }
 
        });
    }
 
    public double random( double num ){//random method may not be the best
        return (num * 2)  * rnd.nextDouble() - num;
    }
 
    public double randomPlus( double num ){//return only a positive number
        double temp = ((num * 2)  * rnd.nextDouble()) - num;
        if( temp < 0 )
            return temp * -1;
        else
            return temp;
    }
 
    //This is a bad game loop example but it is quick to write and easy to understand
    //If you want to know how to do a good one use the all knowing google.
    public void loop(){
        while(true){
            if(running)
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
            Particle2 part = particles.get(i);
            if(part.update())
                particles.remove(i);
            if(part != null){
                if(part.getLoc().x <= 0){
                    part.getLoc().x = 0;
                    part.getVel().x *= -1;
                }
                if(part.getLoc().x >= render.getWidth()){
                    part.getLoc().x = render.getWidth();
                    part.getVel().x *= -1;
                }
                if(part.getLoc().y <= 0){
                    part.getLoc().y = 0;
                    part.getVel().y *= -1;
                }
                if(part.getLoc().y >= render.getHeight()){
                    part.getLoc().y = render.getHeight();
                    part.getVel().y *= -1;
                }
            }
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
            particles.get(i).render(g2d);
        }
    }
}