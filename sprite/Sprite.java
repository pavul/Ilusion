/** Description of MyClass 
 *
 * @author P4u1
 * @author P4u1
 * @version prueba
 */


package com.example.cameratest.donkey;

import android.graphics.*;
import android.view.View;
import android.content.Context;

//clase que me crea Sprites de Bitmaps por Frames

/*IMPORTANTE, PARA LA PROXIMA CAMBIAR currentFrame A FRAME currentFrame
 * PARA UTILIZAR currentFrame PARA PONERLO EN ALGUNA COORDENADA
 * */

public class Sprite implements Control {

	Matrix mtx = new Matrix();
	// boolean isCollision=false;

	
	/**
	 * @param FIRST_POSITION<br />
	 * Constant who defines the first Frame of the sprite the value is cero
	 */
	public final int FIRST_POSITION = 0;
	public final int FOWARD = 1; // muestra la animacion hacia adelante
	public final int BACKWARD = 2; // muesta la animacion hacia atras
	public final int BYFRAMES = 3; // muestra una animacion dependiendo de lo
									// establesido por setFrameAnimation

	// VARIABLES PARA MANEJAR LA GRAVEDAD
	public final int GRAVITY = 10; // la constante de la fuerza de gravedad
	public final float GFORCE = 9.8f;
	public int gInc = 0; // la variable que incrementara la gravedad
	public float grav = GRAVITY - GFORCE;

	public int direccionX = 1;
	public int direccionY = 1;
	// direccionx 1 - derecha, 2 - izquierda
	// direcciony 1 - arriba, 2 - abajo
	// en cero no hay direccion

	
	// Variables para sprites en general, Frames y posicion
	protected Bitmap[] frames;
	/**
	 * @param currentFrame<br />
	 * is the index of the sprite array in what we are,
	 * if my animation have 6 frames and the currentFrame = 3,
	 * that means that the sprite is showing the bitmap of the index 2
	 */
	private int currentFrame = 0;
	
	private float x = 0, y = 0, width = 0, height = 0;
	private float speedx = 0, speedy = 0;

	public static float TO_RADIANS = (1 / 180.0f) + (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;

	// Variables para saber si la animacion esta visible o si se llega al final
	protected boolean isVisible = true, isEnded = false; // saber si el objeto es
														// visible o no

	// Variables para mostrar una animacion, desde algunos frames que se tienen
	boolean sinceFirstFrame;
	int firstFrame = 0;
	int lastFrame = 0;
	
	public int imgspdConunter=0;  //--> contador de frames del juego, cuando igual a imageSpeed, se cambia el frame
	public int imageSpeed=0; //depende de tantos frames se cambia la animacion
	                         //sirve para alentar la animacion

	public boolean on = false;// -->variable para activar o desactivar el sprite
								// ->es similar a la variable de SpriteButton
								// activate

	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isEnded() {
		return isEnded;
	}

	public void setEnded(boolean isEnded) {
		this.isEnded = isEnded;
	}

	
	/**
	*Example: if(sprite.isVisible())
	*@return true if the sprite is visible and false if not
	*/
	public boolean isVisible() {
		return isVisible;
	}


	/**
	*Example: sprite.getSpeedx(); 
	*@return the speed of the sprite in the X axis
	*/
	public float getSpeedx() {
		return speedx;
	}

	/**
	*Example: sprite.setSpeedx(4);
	*@param speedx
	*set the speed in the X axis especified by the param
	*/
	public void setSpeedx(float speedx) {
		this.speedx = speedx;
	}

	
	/**
	*Example: sprite.getSpeedy(); 
	*@return the speed of the sprite in the Y axis
	*/
	public float getSpeedy() {
		return speedy;
	}

	
	//asi se pone la documentacion del javadoc
	/**set the speed in the y axis
	 * @param float speedy       <b>the amount of velocity</b>
	 * */
	public void setSpeedy(float speedy) {
		this.speedy = speedy;
	}

	/**
	*Example: Sprite sprite=new Sprite(10,10,100,100); 
	*@param x  the x position of the sprite
	*@param y the y position of the sprite
	*@param width  the width of the sprite
	*@param height the height of the sprite
	*Instanciate an Sprite with in the coordiantes y and y
	*with a width defined by 'width' param and a height defined by 'height' param
	*/
	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	*Example: Sprite sprite=new Sprite(BitmapFactory.decodeStream(this.con.getAssets().open("bitmap.png"))); 
	*@param a bitmap
	*Instanciate an Sprite with only an image
	*this sprite can be used to show static graphics among the screen
	*the properties X and Y are cero
	*and the properties Width and Heigth are taken of the bitmap passed
	*/
	public Sprite(Bitmap b) {
		this.frames = new Bitmap[1];
		this.frames[0] = b;
		this.currentFrame = this.frames.length - 1;
		width = this.frames[FIRST_POSITION].getWidth();
		height = this.frames[FIRST_POSITION].getHeight();
	}

	
	/**
	*Example: Sprite sprite=new Sprite(new []Bitmap{bmp1, bmp2}); 
	*@param an array of bitmaps
	*Instanciate an Sprite with an array of bitmaps
	*if the bitmaps arent of the same width and height, the object will take
	*the width and height of the bitmap in the first index (position cero).
	*/
	public Sprite(Bitmap[] frames) {
		int l = frames.length;
		this.frames = new Bitmap[l];
		this.frames = frames;
		this.currentFrame = this.frames.length - 1;
		width = this.frames[FIRST_POSITION].getWidth();
		height = this.frames[FIRST_POSITION].getHeight();
	}// const

	/**
	*Example: Sprite sprite=new Sprite(new int{R.2,R.3,R.4}); 
	*@param ResourceId an array of resources of the drawable folder
	*@param context, the context of the activity where we can search for the drawable resources
	*Instanciate an Sprite with an array of resources of the 
	*drawable folder
	*/
	public Sprite(int[] ResourceId, Context context) {// llenar los drames con
														// los id de drawable
		int l = ResourceId.length;
		this.frames = new Bitmap[l];

		for (int i = 0; i < l; i++) {
			this.frames[i] = BitmapFactory.decodeResource(
					context.getResources(), ResourceId[i]);
		}// for
		this.currentFrame = this.frames.length - 1;
		width = this.frames[FIRST_POSITION].getWidth();
		height = this.frames[FIRST_POSITION].getHeight();
	}// const 2

	
	/**
	*Example: Sprite sprite=new Sprite(5,32,32,mySrip); 
	*@param numFrames, the number of frames of the sprite
	*@param w, the width of the frame of the strip (of the bitmap)
	*@param h, the height of the frame of the srtip(of the bitmap)
	*@param strip, this is the bitmap with several frames of the animation (is the strip)
	*Instanciate an sprite object, defining the number of frames, with a certain
	*width and height and passing the bitmap source where we can make the diferent frames 
	*from that strip source
	*I hope not confuse you!!
	*NOTE: the width and height must be mayor or equal than 1
	*/
	public Sprite(int numFrames, int w, int h, Bitmap strip) 
	{  
		Bitmap[] b = new Bitmap[numFrames];

		for (int i = 0; i < numFrames; i++) 
		{
			b[i] = Bitmap.createBitmap(strip, i * w, 0, w, h);
		}// for
		this.frames = b;
		this.currentFrame = this.frames.length - 1;
		width = this.frames[FIRST_POSITION].getWidth();
		height = this.frames[FIRST_POSITION].getHeight();
	}// const 3
		// remodelar este constructor, es parecido al de abajo solo que con
		// limite de frames

	
	/**
	*Example: Sprite sprite=new Sprite(mySrip,32,32); 
	*@param strip, the bitmap source where we can make the diferent frames
	*@param frameWidth, the width of the frame of the strip (of the bitmap)
	*@param frameHeight, the height of the frame of the srtip(of the bitmap
	*Instanciate an sprite object defining the bitmap strip source and
	*the width and height of your frames, this constructor, will make
	*all frames for you, be careful dont let frames in blank space
	*because it will take like valid one
	**NOTE: the width and height must be mayor or equal than 1
	*/
	public Sprite(Bitmap strip, int frameWidth, int frameHeight) 
	{
		int bmpRows = strip.getHeight() / frameHeight;
		int bmpCols = strip.getWidth() / frameWidth;

		if (bmpRows < 1)
			bmpRows = 1;
		if (bmpCols < 1)
			bmpCols = 1;

		int totalFrames = bmpRows * bmpCols;

		this.frames = new Bitmap[totalFrames];

		int contx = 0, conty = 0;
		for (int x = 0; x < totalFrames; x++) {
			this.frames[x] = Bitmap.createBitmap(strip, frameWidth * contx,
					frameHeight * conty, frameWidth, frameHeight);
			contx++;
			if (contx == bmpCols) {
				contx = 0;
				if (conty < bmpRows)
					conty++;
				// while(conty<bmpRows)conty++;
			}

		}// for
		this.currentFrame = this.frames.length - 1;
		width = frameWidth;
		height = frameHeight;
	}// Const 4
		// generar los frames desde un strip definiendo el ancho y alto de los
		// frames

	/**
	*Example: sprite.getNumFrames
	*@return an int with the value of the total frames of the sprite
	*/
	public int getNumFrames() {
		return frames.length;
	}// regresar el numero de frames

	
	/**
	*Example: sprite.nextFrame();
	*set the sprite to the next frame of the animation
	*if the frame shown is the last frame, the currentFrame is then
	*reset to cero (the first frame)
	*@return the bitmap of the current frame<br />
	*EXAMPLE;
	*c.drawBitmap(sprite.nextFrame(),sprite.getX(),sprite.getY(),null);
	*/
	public Bitmap nextFrame() {// ir al siguiente frame
		this.currentFrame++;
		if (this.currentFrame > frames.length - 1) 
		{
			isEnded = true;
			this.currentFrame = FIRST_POSITION;
		} else if (isEnded)
			isEnded = false;
		return frames[this.currentFrame];
	}// regresa un Bitmap con el frame siguiente al que se encuentra el prite
	
	public void nextFrame(boolean stopInLast) 
	{
		
		if(imageSpeed!=0)//
		{
			imgspdConunter++;
			if(imgspdConunter==imageSpeed)
			{
				if (stopInLast) {
					if (this.currentFrame < frames.length - 1)
						this.currentFrame++;
				}// if
				else {
					this.currentFrame++;
					if (this.currentFrame >= this.frames.length - 1)
						this.currentFrame = this.FIRST_POSITION;
				}// else
				imgspdConunter=0;
			}
			
		}//
		else
		{
			if (stopInLast) {
				if (this.currentFrame < frames.length - 1)
					this.currentFrame++;
			}// if
			else {
				this.currentFrame++;
				if (this.currentFrame >= this.frames.length - 1)
					this.currentFrame = this.FIRST_POSITION;
			}// else
		}//
		
	}// incremetar el cuadro, este no regresa ningun bitmap, es para
	 // cambiar la animacion en el THREAD del juego
	
	
	public void nextFrame(boolean stopInLast, int imgspd) 
	{
		
		if(imgspd!=0)//
		{
			imgspdConunter++;
			if(imgspdConunter==imgspd)
			{
				if (stopInLast) {
					if (this.currentFrame < frames.length - 1)
						this.currentFrame++;
				}// if
				else {
					this.currentFrame++;
					if (this.currentFrame >= this.frames.length - 1)
						this.currentFrame = this.FIRST_POSITION;
				}// else
				imgspdConunter=0;
			}
			
		}//
		else
		{
			if (stopInLast) {
				if (this.currentFrame < frames.length - 1)
					this.currentFrame++;
			}// if
			else {
				this.currentFrame++;
				if (this.currentFrame >= this.frames.length - 1)
					this.currentFrame = this.FIRST_POSITION;
			}// else
		}//
		
	}// incremetar el cuadro, este no regresa ningun bitmap, es para
	 // cambiar la animacion en el THREAD del juego
	 //  imgspd especifica el numero de steps antes de cambiar el frame del sprite
	
	

	/**
	*Example: sprite.prevFrame();
	*set the sprite to the previous frame of the animation
	*if the frame shown is the first frame, the currentFrame is then
	*reset to the last frame
	*@return the bitmap of the current frame<br />
	*EXAMPLE;
	*c.drawBitmap(sprite.prevFrame(),sprite.getX(),sprite.getY(),null);
	*/
	public Bitmap prevFrame() {// ir al frame previo
		this.currentFrame--;
		if (this.currentFrame < 0) {
			this.currentFrame = frames.length - 1;
		}
		return frames[this.currentFrame];

	}// prev frame

	
	/**
	*Example: sprite.setFrame(int currentFrame);
	*@param currentFrame, set the currentFrame of the sprite to the specified index
	*if the current frame is less than cero, then the currentFrame of the sprite is reset to cero
	*if the current frame is more than the number of frames, then de current frame will be equal
	*to the last frame
	*@return the bitmap of the current frame<br />
	*EXAMPLE;
	*c.drawBitmap(sprite.setFrame(2),sprite.getX(),sprite.getY(),null);
	*/
	public Bitmap setFrame(int currentFrame) {
		if (currentFrame > frames.length - 1) {
			this.currentFrame = frames.length - 1;
		} else if (currentFrame < 0) {
			this.currentFrame = FIRST_POSITION;
		} else {
			this.currentFrame = currentFrame;
		}
		return frames[this.currentFrame];
	}// setFrame

	public int getcurrentFrame() {
		return this.currentFrame;
	}// regresa el cuadro en el que se encuentra la animacion

	public boolean getAnimationEnd() {
		return isEnded;
	}

	public void setAnimationEnd(boolean AnimationEnd) {
		isEnded = AnimationEnd;
	}

	/**
	 * Its doesnt work properly, so dont use for the moment
	 * */
	public void changeAnimation(Bitmap[] b) {
		this.frames = null;
		int len = b.length - 1;
		this.frames = new Bitmap[len];
		for (int i = 0; i < len; i++) {
			this.frames[i] = b[i];
		}// for
		this.currentFrame = FIRST_POSITION;
		width = this.frames[FIRST_POSITION].getWidth();
		height = this.frames[FIRST_POSITION].getHeight();
	}// cambiar el strip de sprites del objeto, desde un arreglo de bitmap

	/**
	 * Its doesnt work properly, so dont use for the moment
	 * */
	public void changeAnimation(Bitmap b, int numFrames, int x, int y, int w,
			int h) {
		this.frames = null;
		this.frames = new Bitmap[numFrames];
		for (int i = 0; i < numFrames; i++) {
			this.frames[i] = Bitmap.createBitmap(b, i * w, y, w, h);
		}// for
		this.currentFrame = FIRST_POSITION;
		width = this.frames[FIRST_POSITION].getWidth();
		height = this.frames[FIRST_POSITION].getHeight();
	}// cambiar el strip de sprites del objeto desde un strip sheet

	
	/**
	 * Example: Bitmap [] bmparr=sprite.getBitmapFrames();
	 * @return all the frames of the sprite in an array of bitmaps
	 * i, personally use this function to make tilesets
	 * */
	public Bitmap[] getBitmapFrames() {
		return this.frames;
	}// obtiene el arreglo de frames del tipo bitmap del Sprite

	/*
	 * public void addFrame(Bitmap b) { int l=this.frames.length-1;
	 * this.frames[l+1]=b;
	 * 
	 * int l=this.frames.length-1; Bitmap [] newFrames=new Bitmap[l+1]; for(int
	 * i=0;i<=l;i++) { newFrames[i]=this.frames[i]; }//for newFrames[l+1]=b;
	 * this.frames=null; this.frames=new Bitmap[newFrames.length];
	 * this.frames=newFrames; this.currentFrame=this.FIRST_currentFrame;
	 * newFrames=null; }//metodo para agregar un frame mas de bitmap
	 * 
	 * public void addFrame(Bitmap []b) { int cont=0; int
	 * l=this.frames.length-1; Bitmap[] newFrames=new Bitmap[l+b.length];
	 * for(int i=0;i<=l;i++) { newFrames[i]=this.frames[i]; }//for for(int
	 * i=l+1;i<=l+b.length;i++) { newFrames[i]=b[cont]; cont++; }//for
	 * //this.frames=null; this.frames=newFrames;
	 * this.currentFrame=this.FIRST_currentFrame; newFrames=null; }//agregar mas
	 * Frames de bitmap al sprite
	 */

	// metodos de la interfaz
	@Override
	public void move(float speedX, float speedY) {
		this.x += speedX;
		this.y += speedY;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Example:  sprite.setPosition(100,100);
	 * set the x and y position of the sprite in the screen
	 * I, use this function when the sprite is beign created
	 * */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Example:  c.drawText(sprite.getX(),10,100,p);
	 * @return the x value of the sprite
	 * */
	public float getX() {
		return this.x;
	}

	/**
	 * Example:  c.drawText(sprite.getY(),10,100,p);
	 * @return the y value of the sprite
	 * */
	public float getY() {
		return this.y;
	}

	// Para sacar las posiciones de un cierto porcentaje de la pantalla
	public float setXPorcent(float f, int width) {
		return f * width;
	}

	public float setYPorcent(float f, int height) {
		return f * height;
	}

	@Override
	public void moveRandom() {
	}

	public void moveDireccion(int direccionX, int direccionY, int speedx,
			int speedy, int room_left, int room_right, int room_top,
			int room_bottom) {
		if (direccionX == 1 && direccionY == 1) {
			move(speedx, -speedy);
		}// derecha arriba

		if (direccionX == 1 && direccionY == 2) {
			move(speedx, speedy);
		}// derecha abajo

		if (direccionX == 2 && direccionY == 1) {
			move(-speedx, -speedy);
		}// izquierda arriba

		if (direccionX == 2 && direccionY == 2) {
			move(-speedx, speedy);
		}// izquierda abajo

		if (direccionX == 1 && direccionY == 0) {
			move(speedx, 0);
		}// derecha

		if (direccionX == 2 && direccionY == 0) {
			move(-speedx, 0);
		}// izquierda

		if (direccionX == 0 && direccionY == 1) {
			move(0, -speedy);
		}// arriba

		if (direccionX == 0 && direccionY == 2) {
			move(0, speedy);
		}// abajo

		if (this.x + this.width >= room_right) {
			this.x = room_right - this.width;
			this.direccionX = 2;
		}

		if (this.x <= room_left) {
			this.x = room_left;
			this.direccionX = 1;
		}

		if (this.y <= room_top) {
			this.y = room_top;
			this.direccionY = 2;
		}

		// falta hcaer la iteracion para que cheque si se sale de la pantalla
		// por la parte del fondo

	}// mover hacia una direccion

	/**
	 * Example: sprite.stretch(200,300,false)
	 * this function transform all the frames of the sprite to the new
	 * width and the new height 
	 * I, use when i have a sprite with a single frame and i want to
	 * expand it o shrink it
	 * NOTE: the width or the Height must be mayor or equal than 1
	 * 
	 * */
	public void stretch(int width, int height, boolean filter) {
		int l = this.frames.length - 1;
		if (width <= 0)
			width = 1;
		if (height <= 0)
			height = 1;

		for (int i = 0; i <= l; i++) {
			this.frames[i] = Bitmap.createScaledBitmap(this.frames[i], width,
					height, filter);
		}// for

	}

	// METODOS PARA HACER UNA ANIMACION ENTRE LOS FRAMES QUE SE TIENEN
	public void setFramesAnimation(int lastFrametoShow, boolean FromFirstFrame) {
		lastFrame = lastFrametoShow;
		sinceFirstFrame = FromFirstFrame;
	}// especificar hasta que frame se hara la animacion

	public void setFramesAnimation(int firstFrametoShow, int lastFrametoShow,
			boolean FromFirstFrame) {
		sinceFirstFrame = FromFirstFrame;
		firstFrame = firstFrametoShow;
		lastFrame = lastFrametoShow;
	}// especifica desde que frames se hara la animacion

	public Bitmap showFramesAnimation() {

		if (sinceFirstFrame == true || this.currentFrame >= lastFrame) {
			currentFrame = firstFrame;
			sinceFirstFrame = false;
			if (getAnimationEnd())
				setAnimationEnd(false);
		} else {
			this.currentFrame++;
			if (this.currentFrame >= lastFrame) {
				setAnimationEnd(true);
			}
		}

		return this.frames[this.currentFrame];
	}// muestra una animacion desde un frame inicial a otro final

	
	/**
	 * Example:  //on the 
	 * public void onDraw(Canvas c){sprite.draw(c); }
	 * this function draw the currentFrame of sprite if is visible in the coordinates X and Y of the sprite
	 *
	 */
	public void draw(Canvas c) {
		if (isVisible)
			c.drawBitmap(this.frames[currentFrame], x, y, null);
	}// metodo para dibujar el Sprite

	public void draw(Canvas c, int rep) {
		if (isVisible) {
			switch (rep) {
			case 1:
				currentFrame++;
				if (currentFrame >= frames.length - 1)
					currentFrame = FIRST_POSITION;
				break;
			case 2:
				currentFrame--;
				if (currentFrame < 0)
					currentFrame = frames.length - 1;
				break;
			case 3:
				if (sinceFirstFrame == true || this.currentFrame > lastFrame) {
					currentFrame = firstFrame;
					sinceFirstFrame = false;
					if (getAnimationEnd())
						setAnimationEnd(false);
				} else {
					this.currentFrame++;
					if (this.currentFrame > lastFrame) {
						setAnimationEnd(true);
						currentFrame = firstFrame;
					}
				}
				break;
			}// switch
			c.drawBitmap(this.frames[currentFrame], x, y, null);
		}// ifVisible
	}// draw 2

	
	
	/**
	 * Example:  c.drawBitmap(sprite.getBitmapFrame(2),10,100,null);
	 * @param the frame to return
	 * @return the bitmap of the position defined by frame param
	 * NOTE:this function dont know the number of the frames, so, 
	 * if the frame is less than first frame or if the frame is mayor than
	 * the last frame, it will be an error, so be carefull, i will fix it in later versions
	 * */
	public Bitmap getBitmapFrame(int frame) {
		return this.frames[frame];
	}// regresa el bitmap del frame en cuestion

	
	/**
	 * Example:  sprite.moveSpeed();
	 * move the sprite with the X speed and the Y speed
	 * */
	@Override
	public void moveSpeed() {
		this.x += speedx;
		this.y += speedy;
	}// mover el sprite con la velocidad que tenemos

	
	/**
	 * Example:  sprite.moveXSpeed();
	 * move the sprite int the X axis with the value of xspeed property
	 * */
	@Override
	public void moveXSpeed() {
		this.x += speedx;
	}// mover el sprite con la velocidad que se tiene en x

	/**
	 * Example:  sprite.moveYSpeed();
	 * move the sprite int the Y axis with the value of yspeed property
	 * */
	@Override
	public void moveYSpeed() {
		this.y += speedy;
	}// mover el sprite con la velocidad que se tiene en y

	// METODOS PARA LA GRAVEDAD

	public void setGravity(float g) {
		grav = g;
	}// igualamos la gravedad con el valor g

	public void setGravity(float g, float f) {
		grav = g - f;
	}// gravedad menos fuerza, para darme valor de gravedad

	public void moveWithGrav() {
		setSpeedy(this.speedy + grav * ++gInc);
		moveYSpeed();
	}// movemos el sprite con la gravedad determinada

	public void moveWithGrav(int limit) {
		if (gInc < limit)
			gInc++;
		setSpeedy(speedy + grav * gInc);
		moveYSpeed();
	}// movemos el sprite con la gravedad determinada hasta el limite definido
		// por limit

	public void moveWithGrav(float g) {
		setSpeedy(speedy + g * ++gInc);
		moveYSpeed();
	}// movemos el sprite con una gravedad especificada por g

	public void moveWithGrav(float g, float f) {
		float ng = g - f;
		setSpeedy(speedy + ng * ++gInc);
		moveYSpeed();
	}// movemos el sprite con una gravedad especificada por g y por la fuerza

	public void moveWithGrav(float g, float f, int limit) {
		float ng = g - f;
		if (gInc < limit)
			gInc++;
		setSpeedy(speedy + ng + gInc);
		moveYSpeed();
	}// movemos el sprite con una gravedad especificada por g y por la fuerza
		// hasta que llegue al limite

	public float getGravity() {
		return grav;
	}

	public void resetGInc() {
		gInc = 0;
	}// gInc= incremento de gravedad


	/**
	 * Example:  sprite.isTouched(xx,yy); //of the MotionEvent
	 * check if the user touch some point inside the sprite
	 * @return true if is touched, false if not
	 * I use this function to know if the user is pressing the sprite
	 * this function is in the MotionEvent if is pressed or released or moving
	 * */
	public boolean isTouched(float x, float y) {
		boolean isTouched = false;

		if (x >= this.x && x <= this.x + this.width && y >= this.y
				&& y <= this.y + this.height)
			isTouched = true;
		return isTouched;
	}//istouched

	
	/**
	 * Example:  sprite.setVisible(false);
	 * set the isVisible property of the sprite
	 * true if you want to make the sprite visible
	 * or false if you want to make the sprite invisible
	 * */
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	public boolean getVisible() {
		return this.isVisible;
	}

	public void setOn(boolean on) {
		this.on = on;
	}// activar o desactivar el sprite

	public boolean getOn() {
		return this.on;
	}// saber el stado del sprite si esta On u Off
	
	
	///////////////////////-------->
	
	public float getLeft()
	{return this.x;}//regresar la izquierda, osea x
	
	public float getRight()
	{return this.x+this.width;}//regresar la derecha osea x mas su ancho
	
	public float getTop()
	{return this.y;}
	
	public float getBottom()
	{return this.y+this.height;}
	
	public float getCenterX()
	{
		return this.x+(this.width/2);
	}
	
	public float getCenterY()
	{
		return this.y+(this.height/2);
	}
	
	public float getHalfWidth()
	{
		return this.width/2;	
	}
	
	public float getHalfHeight()
	{return this.height/2;}
	
	
	
	///////////////FUNCIONES DE COLISIONES
	/**
	 * Example:  sprite.circleColision(enemy);
	 * @param sprite, the another sprite to check the colision
	 * checks if the sprite is in colision with another sprite 
	 * the colision is with a circle shape
	 * @return true if there is a colision and false if not
	 * */
	public boolean circleColision(Sprite sprite)
	{
		float cx=this.getCenterX()-sprite.getCenterX();
		float cy=this.getCenterY()-sprite.getCenterY();
		
		float magnitude=(float)Math.sqrt((cx*cx)+(cy*cy));
		
		float totalRadii=this.getHalfWidth()-sprite.getHalfWidth();
		
		boolean hit=magnitude<totalRadii;
		
		return hit;
		
	}//funcion para checar colision circular con 
	
	
	/**
	 * Example:  blockCircle(enemy);
	 * @param sprite, the another sprite to check the colision
	 * @param bounce, true if you want the sprite to bounce (not implemented yet)
	 * checks if the sprite is in colision with another sprite 
	 * the colision is with a circle shape
	 * @return true if there is a colision and false if not
	 * NOTE: this function make the another sprite SOLID, that means
	 * that this sprite is blocked when there is a colision with another sprite
	 * 
	 * */
	public void blockCircle(Sprite sprite, boolean bounce)
	{
		
		float cx=this.getCenterX()-sprite.getCenterX();
		float cy=this.getCenterY()-sprite.getCenterY();
		
		float magnitude=(float)Math.sqrt((cx*cx)+(cy*cy));
		
		float totalRadii=this.getHalfWidth()-sprite.getHalfWidth();
		
		if(magnitude<totalRadii)
		{
			
			float overlap=totalRadii-magnitude;
			
			float dx=cx/magnitude;
			float dy=cy/magnitude;
			
			this.x+=overlap*dx;
			this.y+=overlap*dy;
		
			if(bounce)
			{
				//codigo para rebotar el sprite en caso de
			}
			
			
		}//if
		
	}//funcion que hace colision con un circulo pero lo hace solido, 
	
	/**
	 * Example:  sprite.rectangleColision(enemy);
	 * @param sprite, the another sprite to check the colision
	 * checks if the sprite is in colision with another sprite 
	 * the colision is with a rectangle shape
	 * @return true if there is a colision and false if not
	 * */
	public boolean rectangleColision(Sprite sprite)
	{
		boolean hit=false;
		float vx=this.getCenterX()-sprite.getCenterX();
		float vy=this.getCenterY()-sprite.getCenterY();
		
		
		float combinedHalfWidth=this.getHalfWidth()+sprite.getHalfWidth();
		float combinedHalfHeight=this.getHalfHeight()+sprite.getHalfHeight();
		
		if(Math.abs(vx)<combinedHalfWidth)
		{
			
			if(Math.abs(vy)<combinedHalfHeight)
			{
				hit=true;
			}//height
			else
			{
				hit=false;
			}
		}//width
		else
		{
			hit=false;
		}
		return hit;
		
	}//colision rectangular
	
	/**
	 * Example:  sprite.blockRectangle(enemy);
	 * @param sprite, the another sprite to check the colision
	 * @param move, if this is set to true, the sprite will push the colision sprite
	 * @param bouncing, true if you want to make that the sprite bounce when colide with colision sprite
	 * checks if the sprite is in colision with another sprite 
	 * the colision is with a rectangle shape
	 * @return true if there is a colision and false if not
	 * 
	 * */
	public String blockRectangle(Sprite sprite,boolean move ,boolean bouncing)
	{
		String side="";
		float vx=this.getCenterX()-sprite.getCenterX();
		float vy=this.getCenterY()-sprite.getCenterY();
		
		float combinedHalfWidth=this.getHalfWidth()+sprite.getHalfWidth();
		float combinedHalfHeight=this.getHalfHeight()+sprite.getHalfHeight();
		
		if(Math.abs(vx)<combinedHalfWidth)
		{
			
			if(Math.abs(vy)<combinedHalfHeight)
			{
				float overlapX=combinedHalfWidth-Math.abs(vx);
				float overlapY=combinedHalfHeight-Math.abs(vy);
				
				
				if(overlapX>=overlapY)
				{
					if(vy>0)
					{
						side="TOP";
						if(move)sprite.setY(sprite.getY()-1);
						this.setY(this.getY()+overlapY);
			        }
					else
					{
						side="BOTTOM";
						if(move)sprite.setY(sprite.getY()+1);
					}
					if(bouncing)this.setSpeedy(this.getSpeedy()*-1);
				}
				else
				{
					
					if(vx>0)
					{
						side="LEFT";
						if(move)sprite.setX(sprite.getX()-1);
						this.setX(this.getX()+overlapX);
					}
					else
					{
						side="RIGHT";
						if(move)sprite.setX(sprite.getX()+1);
						this.setX(this.getX()-overlapX);
					}
					if(bouncing)this.setSpeedx(this.getSpeedx()*-1);
		         }//
			}//height
			else
			{
				side="NONE";
			}
			
		}//width
		else
		{
			side="NONE";
		}
		return side;
		
	}//colision con rectangulos, pero 
	
	
	/**moveTo(Sprite sprite, int speed)
	 * EXAMPLE: sprite.moveTo(enemy,5);<br /> 
	 * function that move the sprite to the direction of the sprite parameter
	 * @param this is the direction we want to move our sprite
	 * @param the velocity of with we move the sprite
	 * <p>WatchOut!!, if the speed is less than 1 this function does nothing
	 * */
	public void moveTo(Sprite sprite, int speed)//float x, float y)
	{
		if(speed>0)
		{
			float vx=sprite.getCenterX() - this.getCenterX();
			float vy=sprite.getCenterY() - this.getCenterY();
			float mag=(float)Math.sqrt((vx*vx)+(vy*vy));
			float spdx=vx/mag; //saco la direccion de x
	        float spdy=vy/mag; //saco la direccion de y
			this.setSpeedx(spdx*speed);
			this.setSpeedy(spdy*speed);
		}
	}//moveto
	
	/**moveTo(float x, float y, int speed) 
	 * EXAMPLE: sprite.moveTo(100,100,5);<br />
	 * function that set the speed in X and Y axis to move toward a specific point
	 * @param  <b >X coordinate to move towars</b>
	 * @param  <b >Y coordinate to move towars</b>
	 * @param  <b >the velocity of with we move the sprite</b>
	* <p>WatchOut!!, if the speed is less than 1 this function does nothing
	 * */
	public void moveTo(float x, float y, int speed)
	{
		if(speed>0)
		{
			float vx=x - this.getCenterX();
			float vy=y - this.getCenterY();
			float mag=(float)Math.sqrt((vx*vx)+(vy*vy));
			float spdx=vx/mag; //saco la direccion de x
	        float spdy=vy/mag; //saco la direccion de y
			this.setSpeedx(spdx*speed);
			this.setSpeedy(spdy*speed);
		}
		
	}//move to 2
	
	

}// class
