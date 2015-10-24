/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * clase que contiene al sprite del juego, cada sprite es un objeto con sus
 * respectivas cordenadas, puntaje, vida y movimientos, esta clase no esta
 * terminada por lo que se tiene que extender en otra que vaya a ser la que sea
 * definitiva, por ejemplo un jugador
 *
 * @author Ilusion
 */
public class Sprite implements Movement {
    
    
//    public static enum State
//    {
//    MOVELEFT, MOVERIGHT, MOVEUP, MOVEDOWN,
//    STANDLEFT, STANDRIGHT, CRAWLEFT, CRAWRIGHT,
//    ATTACKINGLEFT, ATTACKINGRIGHT,  ATTACKINGUP,  ATTACKINGDOWN,
//    DEFENDINGLEFT, DEFENDINGRIGHT, DEFENDINGUP, DEFENDINGDOWN,
//    CLIMB,DIYING, DIED, 
//    EATINGLEFT, EATINGRIGHT, EATINGUP, EATINGDOWN
//    }
//    

    public static final int FRAME_FIRSTFRAME = 0; //valor del primer frame de 1 animacion
    public static final int ANIM_FOWARD = 10; //valor de la animacion hacia adelante
    public static final int ANIM_BACKWARD = 11; //valor de la animacion hacia atras
    public static final int ANIM_FRAME = 12; //valor de la animacion por ciertos frames

    private float x; //coordenada x del sprite
    private float y; //coordenada y del sprite
    private float w; //coordenada x del sprite
    private float h; //coordenada y del sprite

    public float speedX = 0; //velocidad x que tiene el sprite
    public float speedY = 0; //velocidad y que tiene el sprite

  
    private float friction; //valor para friccion

    private boolean visible; //indica si se renderiza el sprite o no en el room
    private boolean animationEnd; //indica si se ha llegado al fin de la animacion

    private int imageSpeed; //velocidad de animacion de los frames del sprite
    private int degrees; //variable que tiene los grados a ls que se ve el sprite
    
    private boolean jump; //variable para indicar si el usuario ha brincado
    private boolean executeJump; //variable para indicar si el usuario ha brincado
    private float jumpForce; //fuerza a la que brinca el sprite
    private float jumpValue;
    private float gravity; //valor para gravedad
    
       //limites a los que es permitido llegar el sprite, es decir, el sprite no puede salir de
   //esos limites, por lo general son los limites del room en el que se encuentra
   //estas variables se checan en los metodos move, cuando tienen valor diferente de -1
   private int roomBoundLeft=-1; //limite izquierdo del room hasta donde puede llegar el sprite
   private int roomBoundRight=-1; //limite derecho del room hasta donde puede llegar el sprite
   private int roomBoundTop=-1; //limite de cima del room hasta donde puede llegar el sprite
   private int roomBoundBottom=-1; //limite de fondo del room hasta donde puede llegar el sprite
   
   private int animationSpeed; //velocidad a la que se debe de mostrar la animacion 
                               //en el loop de proceso
   private int animationSpeedLimit; //limite para
      
   //variables para las subanimaciones, es decir, un sprite se puede animar solamente
   //con mostrar algunos frames de las imagenes que se tienen
   private int iniFrame;
   private int endFrame;
      
    /*
     *funcionalidad para sprites
     */
    private Image[] frames;
    private int currentFrame;
    private int lastFrame;
//    pricate Map<String, Image[]>animation;

    //animacion actual del sprite
    private AnimationState currentState;
    
    //stack de subanimaciones
    private Map<AnimationState, int[] > SubAnimationStack;
    
    
    /* 
     * CONSTRUCTORES
     */
    /**
     * constructor que crea la instancia, hace falta definir todos los
     * parametros del script
     */
    public Sprite() {
        this.executeJump=true;
        this.gravity=0.37f;
        this.jumpForce=8;
        this.animationSpeedLimit=10;
        this.currentState = AnimationState.STANDRIGHT;
    }//cont 1

    /**
     * constructor que crea el sprite, pero solo con las dimensiones de W y H,
     * en la posicion x = 0 e y = 0
     *
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public Sprite(int x, int y, int w, int h) {
        this();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }//const 2

    /**
     * constructor 2 donde se crea un sprite con una imagen,
     *
     * @param img
     */
    public Sprite(Image img) {
        this();
        this.frames = new Image[1];
        this.frames[0] = img;
        this.currentFrame = this.frames.length - 1;
        this.lastFrame = this.frames.length - 1;
        this.w = this.frames[FRAME_FIRSTFRAME].getWidth(null);
        this.h = this.frames[FRAME_FIRSTFRAME].getHeight(null);
    }//const 3

    /**
     * constructor 4 donde se crea el sprite con un arreglo de imagenes, estos
     * son tratados como frames de una animacion
     *
     * @param img
     */
    public Sprite(Image[] img) {
        this();
        this.frames = new Image[img.length];
        this.frames = img;
        this.currentFrame = this.frames.length - 1;
        this.lastFrame = this.frames.length - 1;
        this.w = this.frames[FRAME_FIRSTFRAME].getWidth(null);
        this.h = this.frames[FRAME_FIRSTFRAME].getHeight(null);
    }//const 4

    /**
     * constructor 5 se crean los frames de animacion con una imagen como stip
     *
     * @param numFrames
     * @param w
     * @param h
     * @param imgRoute
     */
    public Sprite(int numFrames, int w, int h, String imgRoute) {
        this();
        BufferedImage bigImg = null;
        try {
//            bigImg= ImageIO.read(new File("sheet.png"));
            bigImg = ImageIO.read(this.getClass().getResource(imgRoute));
        } catch (IOException ioe) {
        }

        Image[] im = new Image[numFrames];

        for (int i = 0; i < numFrames; i++) {
            im[i] = bigImg.getSubimage(i * w, 0, w, h);
        }// for
        this.frames = im;
        this.currentFrame = this.frames.length - 1;
        this.lastFrame = this.frames.length - 1;
        this.w = this.frames[FRAME_FIRSTFRAME].getWidth(null);
        this.h = this.frames[FRAME_FIRSTFRAME].getHeight(null);

                 //ESTE CODIGO DEBE DE SERVIR PARA LOS OTROS CONSTRUCTORES
//    BufferedImage bigImg = ImageIO.read(new File("sheet.png"));
//// The above line throws an checked IOException which must be caught.
//
//final int width = 10;
//final int height = 10;
//final int rows = 5;
//final int cols = 5;
//BufferedImage[] sprites = new BufferedImage[rows * cols];
//
//for (int i = 0; i < rows; i++)
//{
//    for (int j = 0; j < cols; j++)
//    {
//        sprites[(i * cols) + j] = bigImg.getSubimage(
//            j * width,
//            i * height,
//            width,
//            height
//        );
//    }
//}
//     
//                puede servir este codigo tambien para el bg
//                void drawSpriteFrame(Image source, Graphics2D g2d, int x, int y,
//                     int columns, int frame, int width, int height)
//{
//    int frameX = (frame % columns) * width;
//    int frameY = (frame / columns) * height;
//    g2d.drawImage(source, x, y, x+width, y+height,
//                  frameX, frameY, frameX+width, frameY+height, this);
//}
//
//Toolkit tk = Toolkit.getDefaultToolkit();    
//Image pacman = tk.getImage(getURL("pacman.png"));
//...
//drawFrame(pacman, g2d, x, y, 15, 19, 25, 25);
    }// const 5

    /**
     * comstructor 6 este crea los sprites pasandole una imagen que contiene
     * todos los frames, especificandole el ancho y alto de los mismos, este
     * saca el numero de frames automaticamente
     *
     * @param frameWidth
     * @param imgRoute
     * @param frameHeight
     */
    public Sprite(int frameWidth, int frameHeight, String imgRoute) {
        this();
        BufferedImage bigImg = null;
        try {
//            bigImg= ImageIO.read(new File("sheet.png"));
            bigImg = ImageIO.read(this.getClass().getResource(imgRoute));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        int bmpRows = bigImg.getHeight() / frameHeight;
        int bmpCols = bigImg.getWidth() / frameWidth;

        if (bmpRows < 1) {
            bmpRows = 1;
        }
        if (bmpCols < 1) {
            bmpCols = 1;
        }

        //por ejemplo si 4 columnas y 5 renglones, son 20 frames
        int totalFrames = bmpRows * bmpCols;

        this.frames = new Image[totalFrames];

        int contx = 0, conty = 0;
        for (int xx = 0; xx < totalFrames; xx++) {
            this.frames[xx] = bigImg.getSubimage(frameWidth * contx,
                    frameHeight * conty, frameWidth, frameHeight);
            contx++;
            if (contx == bmpCols) {
                contx = 0;
                if (conty < bmpRows) {
                    conty++;
                }
                // while(conty<bmpRows)conty++;
            }

        }// for
        this.currentFrame = this.frames.length - 1;
        this.lastFrame = this.frames.length - 1;
        this.w = frameWidth;
        this.h = frameHeight;
    }// Const 6

    /* 
     * /CONSTRUCTORES
     */
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    //FUNCIONES PARA SACAS LAS MEDICIONES DEL SPRITE,
    //DENTRO DE SUS COORDENADAS
    /**
     * el valor de la izquierda del sprite, por lo general la coordenada x
     *
     * @return
     */
    public float getLeft() {
        return x;
    }

    /**
     * el valor la coordenada x+width
     *
     * @return
     */
    public float getRight() {
        return this.x + this.w;
    }//regresar la derecha osea x mas su ancho

    /**
     * el valor de la coordenada y
     *
     * @return
     */
    public float getTop() {
        return this.y;
    }

    /**
     * el valor de la coordenada y+heigth
     *
     * @return
     */
    public float getBottom() {
        return this.y + this.h;
    }

    /**
     * el valor de la coordenada x+ width/2
     *
     * @return
     */
    public float getCenterX() {
        return this.x + (this.w / 2);
    }

    /**
     * el valor de la coordenada y+heigth/2
     *
     * @return
     */
    public float getCenterY() {
        return this.y + (this.h / 2);
    }

    /**
     * el valor de width/2 del sprite
     *
     * @return
     */
    public float getHalfWidth() {
        return this.w / 2;
    }

    /**
     * el valor de heigth/2 del sprite
     *
     * @return
     */
    public float getHalfHeight() {
        return this.h / 2;
    }

    /**
     * para saber si es visible el sprite
     *
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isAnimationEnd() {
        return animationEnd;
    }

    public void setAnimationEnd(boolean animationEnd) {
        this.animationEnd = animationEnd;
    }

    public int getImageSpeed() {
        return imageSpeed;
    }

    public void setImageSpeed(int imageSpeed) {
        this.imageSpeed = imageSpeed;
    }

    public Image[] getFrames() {
        return frames;
    }

    public void setFrames(Image[] frames) {
        this.frames = frames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getLastFrame() {
        return lastFrame;
    }

    public void setLastFrame(int lastFrame) {
        this.lastFrame = lastFrame;
    }

    //_____________________//
    /**
     * funcion que regresa el numero total de frames del sprite
     *
     * @return
     */
    public int getNumberOfFrames() {
        return frames.length;
    }

    /**
     * funcon que regresa la imagen del siguiente frame del sprite, se usa
     * principalmente cuando se quiere dibujar la secuencia de imagenes
     *
     * @return
     */
    public Image getNextImageFrame() {
        currentFrame++;
        if (currentFrame > frames.length) {
            setAnimationEnd(true);
            currentFrame = FRAME_FIRSTFRAME;
        } else if (animationEnd) {
            setAnimationEnd(false);
        }
        return frames[currentFrame];
    }//

    /**
     * metodo que regresa la imagen actual ( frame ), en la que se encuentra
     * el sprite
     * @return 
     */
    public Image getCurrentImageFrame()
    {
    return frames[currentFrame];
    }
    
    
    /**
     * funcion que salca el porcentage en el que se encuentra el sprite a lo
     * ancho de la pantalla
     *
     * @param f
     * @param width
     * @return
     */
    public float setXPorcent(float f, int width) {
        return f * width;
    }

    /**
     * funcion que saca el porcentaje en el que se encuentra el sprite a lo alto
     * de la pantalla
     *
     * @param f
     * @param height
     * @return
     */
    public float setYPorcent(float f, int height) {
        return f * height;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public int getAnimationSpeedLimit() {
        return animationSpeedLimit;
    }

    public void setAnimationSpeedLimit(int animationSpeedLimit) {
        this.animationSpeedLimit = animationSpeedLimit;
    }

    public AnimationState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(AnimationState currentState) {
        this.currentState = currentState;
    }

    public Map<AnimationState, int[]> getSubAnimationStack() {
        return SubAnimationStack;
    }

    public void setSubAnimationStack(Map<AnimationState, int[]> SubAnimationStack) {
        this.SubAnimationStack = SubAnimationStack;
    }


   
    
    
    

    /**
     * funcion que renderiza la imagen de sprite en el frame actual, si la
     * variable visible es falsa, entonces no se muestra la imagen, pero el
     * sprite seguira existiendo y posiblemente haciendo colisiones
     *
     * @param g2
     */
    public void draw(Graphics2D g2) {
        if (visible) {
            g2.drawImage(frames[currentFrame], (int) x, (int) y, null);
        }
    }//

    
    /**
     * metodo que cambialos frames del sprite, esta funcion es para que se utilice en el
     * metodo de update, no en el render, loop indica si la animacion se repite hacia adelante 
     * o al reverso
     * @param loop
     * @return 
     */
    public boolean updateAnimation(int loop)
    {
        animationSpeed++;
        if( animationSpeed >= animationSpeedLimit)
        {     
            animationSpeed=0;
            
        
        switch (loop) {
			case 1:
				currentFrame++;
                                    if (currentFrame >= lastFrame)
                                    {
                                        currentFrame = FRAME_FIRSTFRAME;
                                        animationEnd = true;
                                    }
				break;
			case 2:
				currentFrame--;
				if (currentFrame <= FRAME_FIRSTFRAME)
                                    {
                                        currentFrame = lastFrame;
                                        animationEnd = false;
                                    }
				break;
                            default:
                                currentFrame++;
                                    if (currentFrame >= lastFrame)
                                    {
                                        currentFrame = FRAME_FIRSTFRAME;
                                        animationEnd = true;
                                    }
                                break;
        }
//                if(currentFrame != lastFrame)
//                { animationEnd = false; }
//                else
//                {animationEnd = true;}
        
        
        }//animspeed
        return animationEnd;
    }//
    
    
    public void updateSubanimation()
    {
     animationSpeed++;
        if( animationSpeed >= animationSpeedLimit)
        {     
             animationSpeed=0;
            currentFrame++;
            if(currentFrame >= endFrame)
            {
            currentFrame=iniFrame;
            }
        }
        
    }//
    
    
    //-- falta otro metodo draw
    /**
     * funcion que regresa la imagen segun el frame especificado si el frame
     * especificado es menor o mayor al numero total de frames del sprite, este
     * regresa el primer frame
     *
     * @param frame
     * @return
     */
    public Image getImageFrame(int frame) {
        if (frame < 0 || frame > frames.length - 1) {
            return frames[FRAME_FIRSTFRAME];
        }

        return frames[frame];
    }

    /**
     * funcion para detectar cuando el puntero esta por encima o dentro del area
     * del sprite funciona para eventos de mouse
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isTouched(float x, float y) {
        return (x >= this.x && x <= this.x + this.w
                && y >= this.y && y <= this.y + this.h);
    }//is touched

    /**
     * para mover el sprite sobre el eje X
     *
     * @param speedX
     */
    @Override
    public void moveSpeedX(float speedX) {
        x += speedX;
        
        if(roomBoundLeft != -1)setBoundLeft();
        if(roomBoundRight != -1)setBoundRight();
            
    }//movespeedx

    /**
     * para mover el sprite sobre el eje Y
     *
     * @param speedY
     */
    @Override
    public void moveSpeedY(float speedY) {
        y += speedY;
        
        if(roomBoundTop != -1)setBoundTop();
        if(roomBoundBottom != -1)setBoundBottom();
        
    }

    /**
     * para mover sobre el eje X e Y
     *
     * @param speedX
     * @param speedY
     */
    @Override
    public void move(float speedX, float speedY) {
        x += speedX;
        y += speedY;
        
        if(roomBoundLeft != -1)setBoundLeft();
        if(roomBoundRight != -1)setBoundRight();
        if(roomBoundTop != -1)setBoundTop();
        if(roomBoundBottom != -1)setBoundBottom();
        
    }//move

    /**
     * para posicionar el sprite sobre alguna coordenada X e Y en especifico,
     * este no es mover, aunque se puede usar para lo mismo
     *
     * @param x
     * @param y
     */
    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * moveTo(Sprite sprite, int speed) EXAMPLE: sprite.moveTo(enemy,5);<br />
     * function that move the sprite to the direction of the sprite parameter
     * this function dont move the sprite on his own, thou must use
     * sprite.movespeed();
     *
     * @param sprite
     * @param speed
     *
     */
    @Override
    public void moveTo(Sprite sprite, int speed) {
        if (speed > 0) {
            float vx = sprite.getCenterX() - this.getCenterX();
            float vy = sprite.getCenterY() - this.getCenterY();
            float mag = (float) Math.sqrt((vx * vx) + (vy * vy));
            float spdx = vx / mag; //saco la direccion de x
            float spdy = vy / mag; //saco la direccion de y
            this.setSpeedX(spdx * speed);
            this.setSpeedY(spdy * speed);
        }
    }

    /**
     * moveTo(float x, float y, int speed) EXAMPLE:
     * sprite.moveTo(100,100,5);<br />
     * function that set the speed in X and Y axis to move toward a specific
     * point
     *
     * @param x
     * @param y
     * @param speed
     *
     */
    @Override
    public void moveTo(float x, float y, int speed) {
        if (speed > 0) {
            float vx = x - this.getCenterX();
            float vy = y - this.getCenterY();
            float mag = (float) Math.sqrt((vx * vx) + (vy * vy));
            float spdx = vx / mag; //saco la direccion de x
            float spdy = vy / mag; //saco la direccion de y
            this.setSpeedX(spdx * speed);
            this.setSpeedY(spdy * speed);
        }
    }

    @Override
    public String toString() {
        return "Sprite{" + "x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + ", speedX=" + speedX + ", speedY=" + speedY + ", gravity=" + gravity + ", friction=" + friction + ", visible=" + visible + ", animationEnd=" + animationEnd + ", imageSpeed=" + imageSpeed + ", frames=" + frames + ", currentFrame=" + currentFrame + ", lastFrame=" + lastFrame + '}';
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
        setJumpValue(jumpForce);
    }

    public float getJumpForce() {
        return jumpForce;
        
    }

    public void setJumpForce(float jumpForce) {
        this.jumpForce = jumpForce;
        setJumpValue(jumpForce);
    }
    
    public void setJumpValue(float jumpValue)
    {
    this.jumpValue=jumpForce * -1;
    }

    public boolean isExecuteJump() {
        return executeJump;
    }

    public void setExecuteJump(boolean executeJump) {
        this.executeJump = executeJump;
    }

    public int getRoomBoundLeft() {
        return roomBoundLeft;
    }

    public void setRoomBoundLeft(int roomBoundLeft) {
        this.roomBoundLeft = roomBoundLeft;
    }

    public int getRoomBoundRight() {
        return roomBoundRight;
    }

    public void setRoomBoundRight(int roomBoundRight) {
        this.roomBoundRight = roomBoundRight;
    }

    public int getRoomBoundTop() {
        return roomBoundTop;
    }

    public void setRoomBoundTop(int roomBoundTop) {
        this.roomBoundTop = roomBoundTop;
    }

    public int getRoomBoundBottom() {
        return roomBoundBottom;
    }

    public void setRoomBoundBottom(int roomBoundBottom) {
        this.roomBoundBottom = roomBoundBottom;
    }
    
    /**
     * funcion que establece los frames inicial y final de la subanimacion 
     * que se quiere mostrar
     * @param iniFrame
     * @param endFrame
     */
    public void setSubanimation(int iniFrame, int endFrame)
    {
        if(this.iniFrame == iniFrame && this.endFrame == endFrame)
            return;
        
        this.iniFrame=iniFrame;
        this.endFrame=endFrame;
        this.currentFrame=iniFrame;
        this.lastFrame=endFrame;
        
    }//

    /**
     * metodo que toma un arreglo de enteros como subanimacion
     * 
     * @param subanimation 
     */
        public void setSubanimation(int [] subanimation)
    {
        setSubanimation(subanimation[0], subanimation[ subanimation.length-1 ]);
    }//
        
/**
 * metodo que establece la subanimacion por medio de un estado de animacion,
 * este estado debe de estar cargado en el stack de subanimacion
 * @param subanimation 
 */        
        public void setSubanimation(AnimationState subanimation)
    {
        if(SubAnimationStack == null)
            return;
        
        setSubanimation(SubAnimationStack.get(subanimation));
    }//
    
        
        
        
    
    /**
     * renderiza al sprite rotado segun los grados establecidos
     * @param g2
     * @param degrees
     */
    public void drawRotate(Graphics2D g2, int degrees)
    {
        AffineTransform oldTransform =  g2.getTransform();
//        Graphics2D g2d = (Graphics2D)g;
             g2.translate( x , y );
             AffineTransform trans = new AffineTransform();
             trans.setToIdentity();
             
//             trans.setTransform(identity);
             trans.rotate( Math.toRadians(degrees), this.getHalfWidth()  , this.getHalfHeight() );
             g2.drawImage(frames[currentFrame], trans, null);
             
    g2.setTransform(oldTransform);
             
    }//
  
    
    public void processJump()
    {
        if(jump)
        {
        
//             jumpForce -= Config.Config.GRAVITY;
//            float j= (jumpForce * -1);
//                  jumpValue += 0.098;
                  jumpValue += gravity; //este valor define que tan largo brinca
            if(jumpValue >= jumpForce+2)
            {jumpValue = jumpForce+2;}
            
            y+=jumpValue;
            
            
//            if(y>=350) 
//            {
//                y=350; 
//                jump=false;
//                setJumpValue(jumpForce);
//            }
        }//
    
    }//
    
    
    /**
     * checa si el sprite se va a salir por el limite izquierdo y no lo deja
     */
    private void setBoundLeft()
    {
      //no se permite salir del bound izquierdo del room
           if(getX() <= roomBoundLeft)
           {
           setX( roomBoundLeft );
           }
    }
    
     /**
     * checa si el sprite se va a salir por el limite derecho y no lo deja
     */
    private void setBoundRight()
    {
     //no se permite salir del bound derecho del room
           if(getX()+getW() >= roomBoundRight)
           {
           setX(  roomBoundRight - getW() );
           }
    }
    
     /**
     * checa si el sprite se va a salir por el limite de la cima y no lo deja
     */
    private void setBoundTop()
    {
    //no se permite salir del bound de la cima del room
           if(getY() <= roomBoundTop)
           {
           setY( roomBoundTop );
           }
    }//
    
     /**
     * checa si el sprite se va a salir por el limite del fondo y no lo deja
     */
     private void setBoundBottom()
    {
     //no se permite salir del bound derecho del room
           if(getY()+getH() >= roomBoundBottom)
           {
           setY(  roomBoundBottom - getH() );
           }
    }
    
     
}//class
