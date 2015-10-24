/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Room;


import Audio.Sound;
import Level.Level;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Map;


/**
 * clase que es la escena o cuarto donde hay fondos, enemigos, jugadores, puntajes, etc.
 * este viene siendo el canvas
 * @author pavulzavala
 */
public class Room extends Canvas implements 
        Runnable
{
    private static final long serialVersionUID = -6489796311509601114L;

    //numeros que va a tener el buffer
    protected final int BUFFER_NUMBER=3;
    
    //thread principal del juego
    protected Thread gameThread;
    
    //variable indica si el juego esta corriendo o no
    protected boolean running;
    
    //variable para pausar el juego
    protected boolean pause;
    
    //variable que sirve para contar cuantos frames por segundo hay en la aplicacion
   protected int frames; //contador de FPS
   
   protected int offsetX;  //x_ofset del room para que se renderize el area del dibujo
   
//   protected GameState gameState; //estado del juego
   
   //variable que guarda el nivel o pantalla en que se esta jugando
   protected Level currentLevel;
   
   //stack que mantiene todos los niveles
   Map<String, Level> levelStack;
   
   //objeto para hacer las transiciones del room
   protected Transition transition;
    
   //objeto que tiene la camara
//   protected Camera cam;
    
    //para saber los frames count
   protected int frameCount=1; //30 fps
 
   int count;//
    
    /**
     * constructor 1 este constructor crea un room por default con
     * width 640 y heigth 480, se toma la referencia de la ventana que se ve
     * para poder asi cambiar de rooms
     * @param lvltoLoad
     * @param levelStack
     */
    public Room(String lvltoLoad, Map<String, Level>levelStack)
    {
        
        this.levelStack = levelStack;
        
//        addKeyListener(this);
//    imgbg = new ArrayList<>();
//    tileMaps = new ArrayList<>();
    }//
    
    /**
     * constructor 2 este constructor crea un room por default con
     * width 640 y heigth = width /12 * 9, se toma la referencia de la ventana que se ve
     * para poder asi cambiar de rooms
     * @param window 
     * @param width 
     */
//    public Room(int width)
//    {
////        this.window=window;
//        this();
//    this.roomWidth=width;
//    this.roomHeight = roomWidth / 12 * 9;
//    }//
    
    /**
     * constructor 1 este constructor crea un room por default con
     * width y heigth definidos por el usuario, se toma la referencia de la ventana que se ve
     * para poder asi cambiar de rooms
     * @param window 
     * @param width 
     * @param height 
     */
//    public Room(int width, int height)
//    {
//        this(width);
//        this.roomHeight = height;
//    }//
    
    
    //este debe de tener el view y el offset
    
    /**
     * inicia el thread del juego
     */
    public synchronized void start() 
    {
//        gameState=Config.STATE_LOADING;
        
//        try
//        {
//            loadLvl(lvltoLoad);
//            
//        }
//        catch(Exception e)
//        {
////        e.printStackTrace();
//        }
//        finally
//        {
        
        
            //se carga el nivel 
            loadLvl("first");
        
            running=true;  
            gameThread = new Thread(this);
            gameThread.start();
//            gameState = GameState.PLAYING;
//              ./,
//        }
  
    }//start
    
    
    /**
     * detiene y termina el thread del juego
     */
    public synchronized void stop()
    {
        try
        {
            running=false;
            gameThread.join();
        }
        catch(Exception e)
        {}
    }//stop
    
    
    public synchronized void update()
    {   
        frameCount++;
        if(frameCount ==60)frameCount=0;
 
       //aqui se hace el update del level
       currentLevel.update();
    }//
    
    
    /**
     * funcion que renderiza todo el game play, esta es la funcion mas importante,
     * pues es la que tiene la interaccion del usuario con el juego
     */
    public synchronized void render()
    {
        //se obtienen los graficos
        Graphics g = this.getGraphicsBufferStrategy();
        
        
        if(g != null)
        {
           try
           {
              currentLevel.render(g);  
           }catch(Exception e)
           {  }

        this.closeGraphicsBufferStrategy(g, this.getBufferStrategy());
        
        }//if !=null
        
    }//renderGameState

//    public void renderPause()
//    {
//    
//        Graphics g = this.getGraphicsBufferStrategy();
//        
//        if(g != null)
//        {
//        g.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
//        
////        
//        g.setColor(Color.white);
////        g.drawRoundRect(10, 10, Config.SCREEN_WIDTH-20, Config.SCREEN_HEIGHT - 20, 10, 10);
////        
////        g.setColor(Color.BLUE);
////        g.fillRoundRect(10, 10, Config.SCREEN_WIDTH-20, Config.SCREEN_HEIGHT - 20, 10, 10);
////        
//        
////        g.setColor(Util.getRandomColor());
//        g.drawString(" PAUSE ", Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
//        }
//        
//        this.closeGraphicsBufferStrategy(g, this.getBufferStrategy());
//        
//    }//render pause

//    public GameState getGameState() {
//        return gameState;
//    }
//
//    public void setGameState(GameState gameState) {
//        this.gameState = gameState;
//    }
    
    
    
    /**
     * funcion que renderiza lo que sucede en las pantallas de cargado de 
     * recursos (LOAD SCREEN)
     */
//    public void renderLoadState()
//    {
//    Graphics g = this.getGraphicsBufferStrategy();
//    
//    this.drawBgColor((Graphics2D)g, Color.black);
//    g.setColor(Color.white);
//    g.drawString("Cargando..." , roomWidth/2, roomHeight/2);
//    
//        this.closeGraphicsBufferStrategy(g, this.getBufferStrategy());
//    }//
//    
    

    @Override
    public void run() 
    {
     
    long lastTime= System.nanoTime();
    double amountOfTicks=60.0;
    double ns= 1000000000 / amountOfTicks;
    double delta=0;
    long timer= System.currentTimeMillis();
//    int frames=0;
    while(running)
    {
    
        if(!pause)
        {
        
                 long now= System.nanoTime();
                delta+=(now - lastTime)/ns;
                lastTime = now;
                while(delta >= 1)
                {
                    
                update();
//                 render();
                delta--;
                
                }
                
                if(running)
                    render();
                //codigo para checar los frames
                frames++;
                if(System.currentTimeMillis() - timer > 1000)
                {
                timer +=1000;
//                System.out.println("FPS: "+frames);
                frames=0; 
                }
             
                
        }//pause
//        else
//        {
//        renderPause();
//        }
               
    }//uail
        
        
    }//run

   
    /**
     * funcion que muestra los frames por segundo del juego
     */
    public void showFPS(Graphics2D g2)
    {
        
//    g2.setColor(Color.yellow);
//    g2.drawString("FPS: "+frames, cam.getOffsetX() + 10, 10);
//    g2.drawString("offsetX: "+cam.getOffsetX(), cam.getOffsetX() + 10, 30);
//    g2.drawString("offsetX+WIDTH: "+(cam.getOffsetX()+cam.getViewXPort()), cam.getOffsetX() + 10, 50);
//    
//    g2.drawString("offsetX+213: "+(cam.offsetX+213), cam.getOffsetX() + 10, 70);
//    g2.drawString("offsetX+426: "+(cam.offsetX+426), cam.getOffsetX() + 10, 90);
//    g2.drawString("playerX: "+(player.getX()), cam.getOffsetX() + 10, 110);
//    g2.drawString("playerY: "+(player.getY()), cam.getOffsetX() + 10, 130);
    
    
    }
    
    /**
     * funcion auxiliar para poner en los render de los estados de juego,
     * regresa el objeto graphics
     * @return 
     */
    protected Graphics getGraphicsBufferStrategy()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(BUFFER_NUMBER);
            return null;
        }
         Graphics g = bs.getDrawGraphics();   
         return g;
         
    }//
    
    /**
     * funcion que cierra y libera los recursos de los objetos Graphics y
     * BufferStrategy, que se usan en los render
     * @param g
     * @param bs 
     */
    protected void closeGraphicsBufferStrategy(Graphics g, BufferStrategy bs)
    {
        g.dispose();
        bs.show();
    }

//    @Override
//    public void keyTyped(KeyEvent e) 
//    {
//        
//    }//

//    @Override
//    public void keyPressed(KeyEvent e) {
        
//        int key=e.getKeyCode();
        
//        mapeo de controles
//        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
//        {moveLeft=true;}
//        else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
//        {moveRight=true;}
//        else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
//        {moveUp=true;}
//        else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
//        {moveDown=true;}
//        
//         if(key == KeyEvent.VK_X || key == KeyEvent.VK_K)
//        {
//          jumpBtn=true;    
//        }
    
//    }//

//    @Override
//    public void keyReleased(KeyEvent e) {
       
//        int key=e.getKeyCode();
//        
////        mapeo de controles
//        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
//        {moveLeft=false;}
//        else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
//        {moveRight=false;}
//        else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
//        {moveUp=false;}
//        else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
//        {moveDown=false;}
//        else if(key == KeyEvent.VK_P)
//        {pause= !pause;}
//    
//        
//        if(key == KeyEvent.VK_X || key == KeyEvent.VK_K)
//        {
//          jumpBtn=false;    
//        }
//        
//        if(key == KeyEvent.VK_O )
//        {
////            if(transition != null)
////            {
////                transition.setOn( !transition.isOn() );
////                if(transition.getCurrentState() == Transition.STATE_HIDE)
////                {transition.setCurrentState(Transition.STATE_SHOWING);}
////            }
//          
////            changeLvl();
//        }
        
        
//    }

   
    
    
      /**
       * metodo que sirve para cambiar el nivel o la pantalla, cambia el fondo
       * y vuelve a crear el pool de enemigos, se pueden setear valores al jugador
       * o del puntaje o HUD
     * @param levelToLoad
       * @return 
       */
      public synchronized boolean loadLvl(String levelToLoad)
      {
          System.out.println(" SE CARGA NIVEL: "+levelToLoad);
//          System.out.println(" gameState: "+gameState);
          
          //se cambia el estado a cargando
//          gameState = GameState.LOADING;
          
          if(currentLevel != null)
          {
              //si hay nivel se elimina y se pone a null para liberar recursos 
          currentLevel.disposeLevel();
          currentLevel.removeKeyListener(this);
          
          //se quitan los sonidos de fondo del nivel
          currentLevel.getMp3Player().pause();
          currentLevel.getMp3Player().removeAll();
          
          currentLevel = null;
          }//
          
          //se establece el nuevo nivel actual
          currentLevel =  levelStack.get(levelToLoad);
          
          //se establece el keyListener
          currentLevel.addKeyListener(this);
          
          //
          currentLevel.setRoom(this);
          
          try
          {
              currentLevel.init();
//              gameState = GameState.PLAYING;
              return true;
          }
          catch(Exception e)
          {
              return false;
          }
          
          
          
          
      }
      
}//class

