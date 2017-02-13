/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Control.KeyControl;
import Room.Camera;
import Room.GameState;
import Room.ImageBackground;
import Room.Room;
import Sprite.Sprite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * clase que crea un nivel predeterminado, este contiene el fondo de 
 * pantalla, jugadores, tiles, etc. 
 * 
 * this class create a new level, in this class programmer can define
 * all sprites ( player, enemies, etc ), background image, tiles, etc
 * colliisons , controlls, and all logic
 * 
 * NOTE: this is where the gameplay for certain level is defined
 * 
 * @author pavulzavala
 */
public abstract class GameLevel 
{
    //medidas del room
    protected int roomWidth; //largo total del rooom
    protected int roomHeight; //alto total del room
    protected int viewWidth; //porcion de ancho del room que se ve en pantalla
    protected int viewHeight; //porcion de alto del room que se ve en pantalla
    
//    protected Sprite player;
    
    //almacena las imagenes de background que se pueden utilizar
    //this stores all images used for background
    protected ArrayList<ImageBackground> imgbg; 
   
    //almacena los arrelgos de entero que idica cuales tiles se muestran ene l fondo
    //stores int arrays used to indicate what tiles will be shown on the screen
    protected ArrayList<int[]> tileMaps; //listado de mapas de tiles
    
    //almacena los arrelgos de entero que indica en que tiles se deben de checar colisiones
    //stores int arrays who are used to check collisions betwen players/enemies
    //against solid tiles
    protected ArrayList<int[]> colisionTileMaps; //listado de mapas de colisiones de tiles
    
    //pool de enemigos, almacena todos los sprites de enemigos
    //pool for enemies, to handle all enemies by group, not one by one
   protected ArrayList<Sprite> enemyPool;
    
   //camara del room
   //Camera Object, used to control what part of the level is
   //shown on the screen
   protected Camera cam;
   
   //instancia del room, para obtenersus propiedades
   //se necesita para tomar el keylistener del control del jugador
   //tambien para cambiar a diferentes niveles desde la pantalla actual
   //
   //this is the room instance, is very useful because we can obtain
   //some properties like keylistener for player controller
   //and if we want to change from one level to another, we can do that
   //using this instance
   protected Room room;
   
   //control del teclado
   //player control
   protected KeyControl keyControl;
   
   //medidas de los tiles, ancho y alto y numero de columnas y renglones
   //if the level uses tiles, then is usefull to know how many:
   //tile colums and tile rows we have, also what are the heigth and with 
   //of those tiles
   int tileColumns;
   int tileRows;
   int tileWidth;
   int tileHeight;
   
   //objeto para el archivo de propiedades
   //we can use a properties file to store text values used in the game
   protected Properties properties;
   
   //objeto reproductor de musica de fondo
   //intance to play music
//   protected MP3Player mp3Player;
   
   //estado del nivel en el juego
   //variable who holds the current state of the game
   protected GameState gameState;
   
   /**
    * constructor 1, este permite setear todos los valores del room por medio
    * de sus accesors
    * 
    * this constructor allows settings al values of this class through
    * setters/getters
    */
   public GameLevel()
   {
       // al iniciar el nivel se pone el gamestate en cargando
       //when level starts the state is LOADING
       gameState = GameState.LOADING;
       
       //# se crean los array list
       //we create all objects level will use
       imgbg= new ArrayList<>();
       tileMaps =  new ArrayList<>();
       colisionTileMaps= new ArrayList<>();
       enemyPool = new ArrayList<>();
       
//       mp3Player = new MP3Player();
       
   init();
   }//const
   
   /**
    * constructor 2, que permite establecer la configuracion inicial del room, segun 
    * sus parametros
    * 
    * this constructor, allow stablish configuration of the room, heigth, widht
    * tilerows, tilecolums, etc.
    * @param roomWidth
    * @param roomHeight
    * @param viewWidth
    * @param viewHeight
    * @param tileColumns
    * @param tileRows
    * @param tileWidth
    * @param tileHeight 
    */
   public GameLevel(int roomWidth, int roomHeight, int viewWidth, int viewHeight,
                int tileColumns, int tileRows ,int tileWidth, int tileHeight)
   {
       this();
       
    //primero se configura la camara con los valores que debe de llegar
    //if the level is a big one, then we instantiate the camera, the 
    //camera give some data like if player is in the edge of screen
    //or if player is advancing in the level
        cam=new Camera( tileColumns * tileWidth,
                        tileRows * tileHeight, 
                        viewWidth,
                        viewHeight);
        
        cam.setMarginLeft( viewWidth / 3 );
        cam.setMarginRight( ( viewHeight / 3 )  * 2 );
   
        //se setean las variables del room
        this.roomWidth= roomWidth;
        this.roomHeight=roomHeight;
        this.viewWidth=viewWidth;
        this.viewHeight=viewHeight;
        this.tileWidth=tileWidth;
        this.tileHeight=tileHeight;
        this.tileColumns=tileColumns;
        this.tileRows=tileRows;
        
        
   }//
   
   /**
    * consstructor 3 con configuracion inicial de fondos de imagenes, no de tiles
    * this constructor its usable if the level will use background screns, 
    * not tiles
     * @param roomWidth
     * @param roomHeight
     * @param viewWidth
     * @param viewHeight
     * @param imgbg
    */
   public GameLevel(int roomWidth, int roomHeight, int viewWidth, int viewHeight,
           ArrayList<ImageBackground> imgbg)
   {
        this();
        
        cam=new Camera( roomWidth,
                        roomHeight,
                        viewWidth,
                        viewHeight);
        cam.setMarginLeft( viewWidth / 3 );
        cam.setMarginRight( ( viewHeight / 3 )  * 2 );
        
        //se setean las variables del room
        this.roomWidth= roomWidth;
        this.roomHeight=roomHeight;
        this.viewWidth=viewWidth;
        this.viewHeight=viewHeight;
        this.imgbg = imgbg;
       
        
   }//
    
   
   /**
    * metodos de clase
    */
   
   /**
    * metodo que ejecuta la actualizacion de estado del juego
    * this method need to be implemented by the concret level
    */
   public abstract void update();
//   {}//
   
   /**
    * funcion que renderiza en pantalla el fondo, el frente y el HUD del juego, 
    * tienen que ir en ese orden, de lo contrario solo se mostraria el fondo al final
     
     * this function renders background, midleground, foreground, and HUD of the game
     * NOTE: you have to respect that order otherwise only blackground will be render
     * @param g
    */
   public void render(Graphics g)
   {
   
       switch(gameState)
               {
                   case LOADING:
                       break;
                   case PLAYING:
       
                    //variable de la camara
                    //this will translating the view port
                    //to cam coordinates this makes the camera
                    //show other parts of the entire level
                    g.translate(cam.getCamx(), cam.getCamy());

                    //this function is user to render background
                    renderBackground(g);
                    
//                    this function us user to render foreground
                    renderForeground(g);
                    
//                    this function is user to render HUD
                    renderHUD(g);
       
                       break;
                   case GAMEOVER:
                       break;
                   case COMPLETED:
                       break;
                   case PAUSED:
                       break;
                   case STOPPED:
                       break;
                   case DIALOGUING:
                    renderBackground(g);
                    renderForeground(g);
                    renderHUD(g);
                       break;
               }//such
       
            
   }//renderiza fondo, foreground y HUD del juego
   
   /**
    * funcion que se utiliza para inicializar todos los recursos que se utilizan en el nivel
    * sonidos
    * background ( mapas, imagenes de background, etc )
    * sprites
    * HUD
    * 
    * this function is used to init all resources of game, 
    * sounds, background, sprites, HUD, etc
    * 
    * it first will call initData, then initsound, 
    * initBg, initSprite and lastlty initHud, finally when all
    * is loaded then the game state changes to PLAYING
    * 
    */
   public void init()
   {   
        initData();
        initSound();
        initBg();
        initSprite();
        initHud();
        gameState =  GameState.PLAYING;
   }//
   
   /**
    * metodo para inicializar los sprites del nivel del juego
    * user for init sprites
    * @return 
    */
   public abstract boolean initSprite();
   
   /**
    * funcion para iniciar el fondo de pantalla
    * 
    * user for init backgrounds
    * @return 
    */
   public abstract boolean initBg();
   
   /**
    * funcion para iniciar el HUD del juego
    * user to init HUD
    * @return 
    */
   public abstract boolean initHud();
   
   /**
    * funcion para iniciar el Sonido del juego
    * user to init sounds
    * @return 
    */
   public abstract  boolean initSound();
   
   /**
    * funcion para iniciar algunos datos como el nivel inicial
    * texto iniciales u otras configuraciones
    * 
    * user to init certain useful data, some levels can init
    * text variables, or diferent configurations for each level
    * @return 
    */
   public abstract boolean initData();
   
   /**
    * funcion que tiene toda la logica para dibujar el fondo del nivel
    * 
    * used to render all background, the implementation can draw
    * imageBackgrounds or tileBackgrounds, or both
    * @param g 
    */
   public abstract void renderBackground(Graphics g);
   
   /**
    * funcion que tiene la logica para dibujar todo el frente (sprites, otros objetos que no
    * son parte del fondo y objetos destruibles) del nivel dej juego
    * 
    * the implementation of this functions may render sprites , or other
    * objects who are used in gameplay
    * @param g 
    */
   public abstract void renderForeground(Graphics g);
//   {}
   
   /**
    * funcion que tiene la logica para dibujar todo el HUD del juego
    * 
    * the implementation must have all loginc to render HUD of the game
    * @param g 
    */
   public abstract void renderHUD(Graphics g);
//   {}
   
   
   /**
    * funciones de dibujados de fondos
    */
   
    /**
     * metodo que renderiza en pantalla el fondo, del color especificado
     * 
     * this metod is used to render a background of certain color
     * @param g2
     * @param color 
     */
    public void drawBgColor(Graphics2D g2,Color color)
    {
        g2.setColor(color);
        g2.fillRect(0, 0, roomWidth, roomHeight);
    }//
    
    
     /**
     * metodo que renderiza en pantalla un color, en la posicion X e Y con
     * un ancho y alto definido
     * 
     * this metod is used to render a background of certain color
     * defining coordinates X e y, with an specific heigth and width
     * 
     * @param g2
     * @param color
     * @param x
     * @param y
     * @param w
     * @param h 
     */
    public void drawBgColor(Graphics2D g2,Color color, int x, int y, int w, int h)
    {
        g2.setColor(color);
        g2.fillRect(x, y, w, h);
    }//
    
    
      /**
     * metodo que renderiza en pantalla un color, en la posicion X e Y con
     * un ancho y alto definido y con transparencia definida por "alpha"
     * 
     * * this metod is used to render a background of certain color
     * defining coordinates X e y, with an specific heigth and width
     * but also you can stablish alpha value
     * 
     * @param g2
     * @param color
     * @param x
     * @param y
     * @param w
     * @param h 
     * @param alpha 
     */
    public void drawBgColor(Graphics2D g2,Color color, int x, int y, int w, int h,float alpha)
    {
        g2.setColor(color);
        g2.fillRect(x, y, w, h);
    }//
    
    
    /**
     * metodo que renderiza en pantalla alguna imagen, en la posicion X e Y
     * 
     * this functions draws an image, in x e Y coordinates
     * 
     * @param g2
     * @param img
     * @param x
     * @param y 
     */
    public void drawBgImage(Graphics2D g2,Image img, int x, int y)
    {
    g2.drawImage(img, x, y, null);
    }//
    
    
     /**
     * metodo que renderiza un tilemap
     * 
     * this method render a background made of tiles
     * 
     * @param g2
     * @param img
     * @param map
     * @param cols
     * @param rows
     * @param tileWidth
     * @param tileHeight
     */
    public void drawBgTile(Graphics2D g2,Image[] img, int[] map,int cols, int rows, 
            int tileWidth, int tileHeight)
    {
        if(cols <=0 || rows <= 0)
        {return;}
        
        int mapIndex=0;
        int totalTiles= cols*rows;
        
        int width=tileWidth;   //img[0].getWidth(this);
        
        int height=tileHeight; //img[0].getHeight(this);
        
        int offsetx = cam.getOffsetX();
        int offsety = cam.getOffsetY();
//        int tilewidth=Config.TILE_WIDTH;
//        int nodib=0;
        
        //renglones
        //rows
        for(int i=0;i < rows;i++)
                {
                        //columnas
                    //columns
                        for(int j=0;j < cols;j++)
                        {
                         //checamos si el tile esta dentro de los limites de la camara
                         //si no lo esta, entonces no lo dibujamos
                            
                         //if tile is inside camera limits then is rendered otherwise
                         //not , this save a lot of process
                         if(j * tileWidth  > offsetx - tileWidth 
                           && (j * tileWidth) + tileWidth < offsetx + cam.getViewXPort() + tileWidth 
                           && i * tileHeight > offsety - tileHeight
                           && (i * tileHeight) + tileHeight < offsety + cam.getViewYPort() + tileHeight)
                         {
                         if(map[mapIndex] != -1)
                          g2.drawImage(img[map[mapIndex]], width * j, height * i, null);
                         }//if validacion si se dibuja
                             
                          if(mapIndex < totalTiles)
                          mapIndex++;
                        }//for cols
                }//for rows

        
    }//
   
   
   /**
    * /funciones de dibujados de fondos
    */
   
    /**
     * fucion que elimina todo lo relacionado con el nivel
     * 
     * this functions erase all related to current level,
     * used to free memory
     */
    public void disposeLevel()
    {
    
    imgbg = null; //listado de background de imagenes
   
    tileMaps= null; //listado de mapas de tiles
    
    colisionTileMaps = null; //listado de mapas de colisiones de tiles
    
    //pool de enemigos
    enemyPool = null;
    
    cam = null;
    
    }//dispose
    
    
    /**
     * metodod que se encarga de tener toda la logica del juego en 
     * lo que refiere a la ejecucion del control del juego, puede ser
     * teclado, mouse o joystick
     * 
     * the implementation of this metod must have how the player
     * can control the main character of the game
     * 
     */
    public abstract void updateControl();
    
    // <getters and setters>

    public int getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(int roomWidth) {
        this.roomWidth = roomWidth;
    }

    public int getRoomHeight() {
        return roomHeight;
    }

    public void setRoomHeight(int roomHeight) {
        this.roomHeight = roomHeight;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public ArrayList<ImageBackground> getImgbg() {
        return imgbg;
    }

    public void setImgbg(ArrayList<ImageBackground> imgbg) {
        this.imgbg = imgbg;
    }

    public ArrayList<int[]> getTileMaps() {
        return tileMaps;
    }

    public void setTileMaps(ArrayList<int[]> tileMaps) {
        this.tileMaps = tileMaps;
    }

    public ArrayList<int[]> getColisionTileMaps() {
        return colisionTileMaps;
    }

    public void setColisionTileMaps(ArrayList<int[]> colisionTileMaps) {
        this.colisionTileMaps = colisionTileMaps;
    }

    public ArrayList<Sprite> getEnemyPool() {
        return enemyPool;
    }

    public void setEnemyPool(ArrayList<Sprite> enemyPool) {
        this.enemyPool = enemyPool;
    }

    public Camera getCam() {
        return cam;
    }

    public void setCam(Camera cam) {
        this.cam = cam;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public KeyControl getKeyControl() {
        return keyControl;
    }

    public void setKeyControl(KeyControl keyControl) {
        this.keyControl = keyControl;
    }

    public int getTileColumns() {
        return tileColumns;
    }

    public void setTileColumns(int tileColumns) {
        this.tileColumns = tileColumns;
    }

    public int getTileRows() {
        return tileRows;
    }

    public void setTileRows(int tileRows) {
        this.tileRows = tileRows;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

//    public MP3Player getMp3Player() {
//        return mp3Player;
//    }
//
//    public void setMp3Player(MP3Player mp3Player) {
//        this.mp3Player = mp3Player;
//    }
    
    
    
    // </getters and setters>
   
     /**
     * se agrega el keyListener del room ( que es un CANVAS ),
     * para poder manejar los eventos de teclas,
     * con este metodo se podra utilizar las entradas de teclado para el nivel
     * ya que esa clase no puede agregar esa propiedad solo la canvas
     * @param component
     */
    public void addKeyListener(Component component)
    {
        
        if(keyControl == null)
        {
            keyControl = new KeyControl();
        }
        //
         component.addKeyListener(keyControl);
    }//
    
    /**
     * funcion que remueve del componente el key listener,
     * esto es asi porque al cambiar de nivel, se agrega otro keylistener
     * y se tiene que liberar memoria
     * @param component 
     */
    public void removeKeyListener(Component component)
    {
    component.removeKeyListener(keyControl);
    }    
    
    /**
     * funcion donde va la logica que debe de tener el nivel para manejar los
     * diferentes estados del juego o datos que envia y reciben los clientes o servidores
     * @throws java.io.IOException
     * 
     * this implementation is only used when we are using networking
     * all process of the level and network must be made here
     * 
     */
    public abstract void manageNetworkData() throws IOException ;
    
}//class
