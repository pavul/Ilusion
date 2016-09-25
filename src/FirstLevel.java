
import Dialog.Dialog;
import Inventory.Inventory;
import Inventory.Item;
import Sprite.Sprite;
import Util.Util;
import Level.GameLevel;
import Physics.Collision;
import Room.GameState;
import static Room.GameState.DIALOGUING;
import Room.ImageBackground;
import Sprite.AnimationState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pavulzavala
 */
public class FirstLevel extends GameLevel
{
    
//sprtite del jugador    
    Sprite player;
    //sub animaciones del jugador
//    int [] walkRightAnim =;
//    int [] walkLeftAnim =;
//    int [] walkUpAnim =;
//    int [] walkDownAnim =;
//    
Collision colision;

    Sprite imgMap;
    
    int countstep = 5000;
    Logger logger;
    
    boolean moveDown = true;
    
    int []map =
  {
  6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,6,6,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,6,6,6,6,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,6,0,6,
  6,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,6,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,6,0,0,6,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,6,6,6,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,6,6,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,6,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,6,6,6,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,6,6,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,6,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
  6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6
  };
    
    //propiedades para los dialogos
    Dialog dialog;
    String[] messages = {"mensaje 1","mensaje 2","mensaje 3"};
    
    
    //objetos para el inventario
    List<Item> inventory;
    Inventory inventoryContainer;
    
    
    
    /**
     * constructor #1
     * @param roomWidth
     * @param roomHeight
     * @param viewWidth
     * @param viewHeight
     * @param tileColumns
     * @param tileRows
     * @param tileWidth
     * @param tileHeight 
     */
    public FirstLevel
  (
          int roomWidth, int roomHeight, int viewWidth, int viewHeight,
          int tileColumns, int tileRows ,int tileWidth, int tileHeight
  )
    {
        super(roomWidth, roomHeight, viewWidth, viewHeight, tileColumns, tileRows, tileWidth, tileHeight);
         logger = Logger.getLogger(FirstLevel.class.getName());
    
         
         colision = new Collision();  
         
  //here we add the map array to the level instance
  tileMaps.add(map);
  //System.out.println(" CONST TILEMAP SIZE: "+tileMaps.size());
  
        
        
  int []colmap =
  {
  1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,
  1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
  1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
  };
  
  //here we add the colision map to the level instance
  this.colisionTileMaps.add(colmap);
  
  
  //se establecen los margenes de arriba y abajo para que
  //se mueva la camara a lo alto cuando el jugador se mueva
         this.cam.setMarginTop( 480 / 3  );
         this.cam.setMarginBottom( (480 / 3) * 2 );
         
         
         //se crea el dialog
         dialog = new Dialog(0,cam.getViewYPort()-200,cam.getViewXPort(),200, Color.GREEN);
         dialog.setVisible( true );
         
         //inventario
         inventory = new ArrayList<>();
    
         
        try 
        {
            inventory.add(new Item(0 , 0,0,99, (BufferedImage) Util.getImage("/res/32x32feather.png"),"feather","",""));
            inventory.add(new Item(0 , 0,0,99, (BufferedImage) Util.getImage("/res/32x32beef.png"),"beef","",""));
            inventory.add(new Item(0 , 0,0,99, (BufferedImage) Util.getImage("/res/32x32rod.png"),"rod","",""));
        
        
        
        } catch (IOException ex) {
            Logger.getLogger(FirstLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         
        
        inventoryContainer = new Inventory(inventory, 0,0);
        
        
        
    }//const 1
    
  
  
  /**
   * constructor 2
   * @param roomWidth
   * @param roomHeight
   * @param viewWidth
   * @param viewHeight
   * @param imgbg 
   */
  public FirstLevel(int roomWidth, int roomHeight, int viewWidth, int viewHeight,
          ArrayList<ImageBackground> imgbg)
  {
  super(roomWidth, roomHeight, viewWidth, viewHeight, imgbg);
  
   logger = Logger.getLogger(FirstLevel.class.getName());
  
//  properties = Util.getPropertyFile("/en.properties");
//  
//  for(String key : properties.stringPropertyNames()) {
//  String value = properties.getProperty(key);
//  System.out.println(key + " => " + value);
//  
//}
  

  
  }//
    
    

    @Override
    public void update() 
    {
        
          switch( gameState )
               {
                   case LOADING:
                       //process to show loading screen
                       break;
                   case PLAYING:
                    player.updateSubanimation();
                    updateControl(); 

                    colision.checkColsionTile(player, colisionTileMaps.get(0),
                    20, 50, 32, 32);
    
                    if(player.getY() >= 500)
                    {
                    player.setY(player.getY()-player.getH());
                    dialog.setMessages(messages);
                    gameState = GameState.DIALOGUING;
                    }
                       
                       break;
                   case GAMEOVER:
                       break;
                   case COMPLETED:
                       break;
                   case PAUSED:
                       
                       //show here the PAUSE message or screen
                       
                       break;
                   case STOPPED:
                       break;
                   case DIALOGUING:
                       
                       player.updateSubanimation();
                       
                    //si hay dialogo
                    if(keyControl.isKeyPress(KeyEvent.VK_D))
                    {
                        if(dialog.nextMessage())
                        {
                        //si se terminan los menajes se cambia a playing
                        gameState = GameState.PLAYING;
                        }
                    } //
                       
                       break;
               }//such
        
        
    
//    if(moveDown)
//    {
//        player.moveSpeedY(3);
//        if(player.getY() >= 1600 -64)moveDown=false;
//      
//      if(player.getY()+player.getH() > cam.getOffsetY()  +cam.getMarginBottom())
//            { cam.moveY(- 3); }  
//    }
//    else
//    {
//        player.moveSpeedY(-3);
//        if(player.getY() <= 0 )moveDown=true;
//        
//        System.out.println("valor de MARGINTOP: "+cam.getMarginTop());
//        
//       if(player.getY() < cam.getOffsetY()  +cam.getMarginTop())
//            { cam.moveY(3); }  
//      
//    }
//    
          
          
                
         
              
          
          
    
    
    
    }//update

    @Override
    public boolean initSprite() {
    
        try 
        {
            player= new Sprite(32,32,"/res/32x32player.png");
            //player= new Sprite(Toolkit.getDefaultToolkit().getImage("/res/player.png"));
            player.setVisible(true);
            player.setPosition(100, 100);
            player.setRoomBoundLeft(0);
            player.setRoomBoundRight(roomWidth);
            player.setRoomBoundTop(0);
            player.setRoomBoundBottom(roomHeight);
            
            ///se establecen las animaciones
            int[] right ={6,7,8};
            int[] left ={3,4,5};
            int[] up ={9,10,11};
            int[] down ={0,1,2};
            
            HashMap<AnimationState,int[]> subAnimationStack = new HashMap<>();
            subAnimationStack.put(AnimationState.MOVERIGHT, right);
            subAnimationStack.put(AnimationState.MOVELEFT, left);
            subAnimationStack.put(AnimationState.MOVEUP, up);
            subAnimationStack.put(AnimationState.MOVEDOWN, down);
            
            //finalmente se agrega el stack de animaciones al sprite
            player.setSubAnimationStack(subAnimationStack);
            player.setSubanimation(AnimationState.MOVERIGHT);
            
//            player.setSubanimation(viewWidth, countstep);
            
            
            //System.out.println(" PLAUYER: "+player);
            
                this.enemyPool.add(player);
            
//            imgMap = new Sprite(6, 32,32,"/res/32x32_map_tile.png");
            
//            imgMap = new Sprite(32, 32, "/res/32x32_map_tile.png");
            imgMap = new Sprite(32, 32, "/res/tiles4.png");
            imgMap.setVisible(true);
            System.out.println(" IMGMAP : "+imgMap.getLastFrame());
//            logger.log( java.util.logging.Level.INFO ,imgMap.toString());
            
            return true;
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
//             logger.log( java.util.logging.Level.SEVERE ,"ERROR EX");
//            System.out.println(" NO SE CREA EL PLAUYER ");
            return false;
        }
    
    
    }//

    @Override
    public boolean initBg() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   return false;
    }//

    @Override
    public boolean initHud() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return false;
    }//
    
    
    @Override
    public boolean initSound() {
        
            //UTILIZANDO JACO una libreria MP3
//        try {
//            this.mp3Player.removeAll();
//            this.mp3Player.addToPlayList( new File( java.lang.String.class.getClass().getResource("/res/st.mp3").toURI() ) );
//            this.mp3Player.setRepeat(true);
//            this.mp3Player.play();
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        return false;
//        }
    return true;
    }//
    
    
    

    @Override
    public void renderBackground( Graphics g ) 
    {
    
//        System.out.println(" RENDER : "+this.getClass().getName());
        
//        drawBgColor((Graphics2D)g, Color.DARK_GRAY);
//    drawBgColor((Graphics2D)g, Color.PINK, 0,0, 640, 480);
    
//        System.out.println("cam: "+this.cam.toString());
        
        drawBgTile((Graphics2D)g, imgMap.getFrames(), 
                tileMaps.get(0), 20, 50, 32, 32);
        
//        g.setColor(Color.red);
//        g.drawRect(0, 0, 128, 96);
        
    }//

    @Override
    public void renderForeground(Graphics g) 
    {
        
    player.draw((Graphics2D)g);
//    imgMap.draw((Graphics2D)g);
    }//

    @Override
    public void renderHUD(Graphics g) {
  
        if(gameState == GameState.DIALOGUING)
        {
        dialog.draw(g);
        }
   
        //mostrar el inventario como una linea
        inventoryContainer.drawLine( g , true); 
        
        //mostrar el inventario como un grid
//        inventoryContainer.drawGrid(g,1,3,32,32);
        
    }//

    @Override
    public void updateControl() {
        
//        if(keyControl != null)
//        {
             if(keyControl.isKeyDown(KeyEvent.VK_RIGHT))
            {
                player.setSubanimation(AnimationState.MOVERIGHT);
                
            player.move(3,0);
            }
            else if(keyControl.isKeyDown(KeyEvent.VK_LEFT))
            {
                player.setSubanimation(AnimationState.MOVELEFT);
            player.move(-3, 0);
            } 
            else if(keyControl.isKeyDown(KeyEvent.VK_UP))
            {
                player.setSubanimation(AnimationState.MOVEUP);
            player.move(0, -3);
                 if(player.getY() < cam.getOffsetY()  +cam.getMarginTop())
                { cam.moveY(3); }  
            } 
            else if(keyControl.isKeyDown(KeyEvent.VK_DOWN))
            {
                player.setSubanimation(AnimationState.MOVEDOWN);
            player.move(0, 3);
                if(player.getY()+player.getH() > cam.getOffsetY()  +cam.getMarginBottom())
                { cam.moveY(- 3); }  
            } 
             
//               else if(keyControl.isKeyDown(KeyEvent.VK_D))
//            {
//                if(dialog.nextMessage())
//                {
//                    //si se terminan los menajes se cambia a playing
//                    gameState = GameState.PLAYING;
//                }
//            } //
             
             
//             if(player.getY() >=500)
//             {
//                 room.loadLvl("second");
//             
//             }
             
//        }//
//        else
//        {
//            System.out.println(" KEYCONTROL ES NULO ");
//        }
        
       
        
        }//

    @Override
    public void manageNetworkData() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean initData() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return false;
    }
   
}//



//  if(moveDown)
//    {
//        player.moveSpeedY(3);
//        if(player.getY() >= 1600 -64)moveDown=false;
//      
//      if(player.getY()+player.getH() > cam.getOffsetY()  +cam.getMarginBottom())
//            { cam.moveY(- 3); }  
//    }
//    else
//    {
//        player.moveSpeedY(-3);
//        if(player.getY() <= 0 )moveDown=true;
//        
//        System.out.println("valor de MARGINTOP: "+cam.getMarginTop());
//        
//       if(player.getY() < cam.getOffsetY()  +cam.getMarginTop())
//            { cam.moveY(3); }  
//      
//    }