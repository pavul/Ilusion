
package Room;


import Audio.Sound;
import Level.GameLevel;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;
import net.ClientSocket;
import net.Server;


/**
 * clase que es la escena o cuarto donde hay fondos, enemigos, jugadores, puntajes, etc.
 * este viene siendo el canvas
 * 
 * this class manages all levels of the game and is the class that
 * handle the process under the hood
 * 
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
    //indicate if the game is running or not
    protected boolean running;
    
    
    //variable para pausar el juego
    //this variable is used to pause the game
    protected boolean pause;
    
    //variable que sirve para contar cuantos frames por segundo hay en la aplicacion
   //we store FPS count
    protected int frames; //contador de FPS
   
   protected int offsetX;  //x_ofset del room para que se renderize el area del dibujo
   
//   protected GameState gameState; //estado del juego
   
//   this variable saves the reference to current level that we can
//   use when we wan to change lvl or use tome util methods
   protected GameLevel currentLevel;
   
   //esta estructura se utiliza para guardar aqui todos los objetos que
   //necesitan estar en todo el juego, pueden ser datos como el usuario
   //, puntajes, upgrades, etc/
   //this structure is used to store all those data that are persistent
   //between levels, like, score, upgrades, items, general HUD, etc, because each
   //level can set new ammount of data when it starts
   protected Map< String, ? >persistentData;
   
   
   //stack que mantiene todos los niveles
   //this is the stack where we have all levels for the game
   Map<String, GameLevel> levelStack;
   
   //this variable is to stablish first level to load for default
   String firsLvlToLoad;
   
   
   //objeto para hacer las transiciones del room
   //this object help room to make transition animations, when
   //a level change to another
   protected Transition transition;
    
   //para saber los frames count
   //these variables are used for testing purposes, to know
   //the ammount of frames counted each cicle
   protected int frameCount=1; //30 fps
   int count;//
   int fps=60;
   
   //variables para cliente servidor
   //these variables are only useful if we are using
   //server/client architecture, i mean, an online game
   //@TODO still need a lot of work
   Server gameServer;
   ClientSocket gameClient;
   
   /**
    * indica si el room va a fungir como servidor, es decir
    * no procesara interfaz grafica
    * 
    *true: indicate if this game is an online game and is the server
    * false: by default and indicate this game is client that connect
    * to a server, in case of online game
    */
   private boolean serverApplication; 
   
    /**
     * constructor 1 este constructor crea un room por default con
     * width 640 y heigth 480, se toma la referencia de la ventana que se ve
     * para poder asi cambiar de rooms
     * 
     * creates a room with width = 640, heigth = 480, fps = 60
     * by default and it accept a list of levels
     * 
     * @param lvltoLoad primer nivel a cargar
     * @param levelStack listado de niveles que hay en el juego
     */
    public Room(String lvltoLoad, Map<String, GameLevel>levelStack)
    {
        fps=60;//setea por default a 60 frames por segundo
        this.levelStack = levelStack;
        this.firsLvlToLoad = lvltoLoad;
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
     * 
     * start the thread of the game
     */
    public final synchronized void start() 
    {
            //nivel a cargar por default, este se establece en el constructor 
            loadLvl( firsLvlToLoad );
            running = true;  
            gameThread = new Thread( this );
            gameThread.start();       
    }//start
    
    
    /**
     * detiene y termina el thread del juego
     * 
     * stop the thread of the game
     * wachout with this method
     */
    public final synchronized void stop()
    {
        try
        {
            running=false;
            gameThread.join();
        }
        catch(Exception e)
        {}
    }//stop
    
    
    /**
     * this method calls all update process of levels, 
     * i mean, inside this method the update of the logic
     * of the game will be taken, at the same time updates
     * for logic and render of the game will be taken
     */
    public final synchronized void update()
    {   
        frameCount++;
        if( frameCount == fps )frameCount = 0;
 
       //aqui se hace el update del level
       currentLevel.update();
    }//
    
    
    /**
     * funcion que renderiza todo el game play, esta es la funcion mas importante,
     * pues es la que tiene la interaccion del usuario con el juego
     * 
     * this function call render methods of al levels
     * 
     */
    public final synchronized void render()
    {
        //si el room es de un servidor, no se muestra interfaz grafica
        if(serverApplication) return;
           
        
        if(this.getGraphicsBufferStrategy() ==null )
          return;
        
                //se obtienen los graficos
                Graphics g = this.getGraphicsBufferStrategy();


                if(g != null)
                {
                   try
                   {
                      currentLevel.render(g);  
                   }
                   catch(Exception e)
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
    
/**
 * here is where the main process of the threas is taken
 */
    @Override
    public final void run() 
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
     * 
     * an auxiliar function to show some data of FPS that are
     * processed
     */
    public final void showFPS(Graphics2D g2)
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
    protected final Graphics getGraphicsBufferStrategy()
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
    protected final void closeGraphicsBufferStrategy(Graphics g, BufferStrategy bs)
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
       * 
       * this functions is very important, is used when a player want to go
       * from certain level to another, then the current level is then eraded
       * from memory and the listeners attached to it, too
       * 
     * @param levelToLoad
       * @return 
       */
      public synchronized boolean loadLvl(String levelToLoad)
      {
          System.out.println(" SE CARGA NIVEL: "+levelToLoad);
          if(currentLevel != null)
          {
              //si hay nivel se elimina y se pone a null para liberar recursos 
          currentLevel.disposeLevel();
          currentLevel.removeKeyListener(this);
          
          //se quitan los sonidos de fondo del nivel
//          currentLevel.getMp3Player().pause();
//          currentLevel.getMp3Player().removeAll();
          
          currentLevel = null;
          }//
          
          //se establece el nuevo nivel actual
          currentLevel =  levelStack.get( levelToLoad );
          
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
      
      /**
       * funcion que inicia el servidor del juego, este es un socket
       * 
       * inits this room as a server for an online game
       * 
       * @param port 
       */
      public void setGameServer(int port)
      {
        try {
            gameServer = new Server(port);
        } catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      }//
      
      
      public Server getGameServer()
      {
          return gameServer;
      }
      
      /**
       * funcion que establece o instancia el socket cliente que se conecta al
       * servidor
       * 
       * init the game as a client in case this game is online
       * 
       * @param port
       * @param ip 
       */
      public void setGameClient(int port, String ip)
      {
        try {
            gameClient = new ClientSocket(new Socket(ip, port));
        }
        catch(ConnectException conex)
        {
        //excepcion cuando se corre el cliente sin que este prendido el servidor
            
        } 
        catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      }//
      
      
      public ClientSocket getGameClient()
      {
          return gameClient;
      }//getgameclient
      
      
       public Map<String, GameLevel> getLevelStack() {
        return levelStack;
    }

    public void setLevelStack(Map<String, GameLevel> levelStack) {
        this.levelStack = levelStack;
    }

    /**
     * establece los FPS a los que corre el juego
     * @return 
     */
    public int getFps() {
        return fps;
    }

    /**
     * regresa el valor de los FPS a los que corre el juego
     * @param fps
     */
    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isServerApplication() {
        return serverApplication;
    }

    /**
     * si true, indica que esta es una aplicacion de servidor, por enede
     * no se muestra la interfaz grafica
     * @param serverApplication 
     */
    public void setServerApplication(boolean serverApplication) {
        this.serverApplication = serverApplication;
    }

    
    
    
    
    
}//class

