package com.example.cameratest.donkey;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.Toast;



public class Room 
{
	public static final int TOP_LEFT=0;
	public static final int TOP_RIGTH=1;
	public static final int MIDDLE=2;
	public static final int BOTTOM_LEFT=3;
	public static final int BOTTOM_RIGHT=4;
	//public static final int =5;
		
float roomLeft, roomTop, roomWidth, roomHeight;	
float viewLeft, viewRight, viewTop, viewBottom;
float viewWidth, viewHeight;
float xpos=0; //guardar la posicion x del Bitmap usado de fondo
float ypos=0; //guardar la posicion y del Bitmap usado de fondo

Rect [] margin=null;
int align=0;

String roomName="";
	
int [] map;//el arrego de enteros de frames del mapa, en caso de que sea un TiledLayer
int cols,rows;//las columnas y renglones que tendra ese, mapa de TiledLayer
	
Bitmap [] visualMap;   //el strip de donde se tomaran las imagenes a poner en el mapa
Bitmap singlebg;       //en caso que solo se quiera poner una sola imagen como background, este se usa

Paint p;

	public Room(float roomLeft, float roomTop, float roomWidth, float roomHeight)
	{
		this.roomLeft=roomLeft;
		this.roomTop=roomTop;
		this.roomWidth=roomWidth;
		this.roomHeight=roomHeight;
		this.viewLeft=this.roomLeft;
		this.viewRight=this.viewLeft+this.roomWidth;
		this.viewTop=this.roomTop;
		this.viewBottom=this.viewTop+this.roomHeight;
		this.viewWidth=roomWidth-roomLeft;
		this.viewHeight=roomHeight-roomTop;
		this.xpos=this.viewLeft;
		this.ypos=this.viewTop;
		
		p=new Paint();
		p.setColor(Color.BLACK);//por defaul se pinta negro el paint
		//align=1; //se alinea a la derecha por default
	}//constructor simple para el room, este no alinea
	
	
	public Room(float roomLeft, float roomTop, float roomWidth, float roomHeight, int screenWidth, int screenHeight, Activity con)
	{
	this.roomLeft=roomLeft;
	this.roomTop=roomTop;
	this.roomWidth=roomWidth;
	this.roomHeight=roomHeight;
	this.viewLeft=this.roomLeft;
	this.viewRight=this.viewLeft+this.roomWidth;
	this.viewTop=this.roomTop;
	this.viewBottom=this.viewTop+this.roomHeight;
	this.viewWidth=roomWidth-roomLeft;
	this.viewHeight=roomHeight-roomTop;
	this.xpos=this.viewLeft;
	this.ypos=this.viewTop;
	align=1;
	p=new Paint();
	p.setColor(Color.BLACK);//por defaul se pinta negro el paint
	this.setAlign(align, screenWidth, screenHeight, con);
	}//constructor 2, este alinea por default a la izquierda, align=1
	
	public float getRoomLeft() {
		return roomLeft;
	}


	public void setRoomLeft(float roomLeft) {
		this.roomLeft = roomLeft;
	}


	public float getRoomTop() {
		return roomTop;
	}


	public void setRoomTop(float roomTop) {
		this.roomTop = roomTop;
	}


	public float getRoomWidth() {
		return roomWidth;
	}


	public void setRoomWidth(float roomWidth) {
		this.roomWidth = roomWidth;
	}


	public float getRoomHeight() {
		return roomHeight;
	}


	public void setRoomHeight(float roomHeight) {
		this.roomHeight = roomHeight;
	}


	public float getViewLeft() {
		return viewLeft;
	}


	public void setViewLeft(float viewLeft) {
		this.viewLeft = viewLeft;
	}


	public float getViewRight() {
		return viewRight;
	}


	public void setViewRight(float viewRight) {
		this.viewRight = viewRight;
	}


	public float getViewTop() {
		return viewTop;
	}


	public void setViewTop(float viewTop) {
		this.viewTop = viewTop;
	}


	public float getViewBottom() {
		return viewBottom;
	}


	public void setViewBottom(float viewBottom) {
		this.viewBottom = viewBottom;
	}


	public float getViewWidth() {
		return viewWidth;
	}


	public void setViewWidth(float viewWidth) {
		this.viewWidth = viewWidth;
	}


	public float getViewHeight() {
		return viewHeight;
	}


	public void setViewHeight(float viewHeight) {
		this.viewHeight = viewHeight;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	
	public int[] getMap() {
		return map;
	}


	public void setMap(int[] map) {
		this.map = map;
	}
	
	public int getCols() {
		return cols;
	}


	public void setCols(int cols) {
		this.cols = cols;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}

	public Bitmap[] getVisualMap() {
	return visualMap;
}

public void setVisualMap(Bitmap[] visualMap) {
	this.visualMap = visualMap;
}


public void setxPos(float xpos)
{this.xpos=xpos;}

public float getxPos()
{return this.xpos;}

public void setyPos(float ypos)
{this.ypos=ypos;}

public float getyPos()
{return this.ypos;}


//NOTA PARA HACER EL arreglo de BITMAP 	uso un sprite, transformo el strip en diferentes frames de bitmap
//que son los indices que conoce este contrusctor para hacer el mapa


	public void drawBackground(Canvas c, Bitmap [] b, int [] map, int cols, int rows)
	{
		
		if(rows<1 || cols<1)
		{
			System.out.println("No Hay Valores de Renglon o de Columna\n");
			System.out.println("\nNumero de Columnas: "+this.cols+"\n");
			System.out.println("\nNumero de Renglones: "+this.rows+"\n");
		return;
		}
		
		if( b.length==0)
		{
			System.out.println("No Hay Valores en Room.VisualMap\n");
		return;
		}
		
		if( map.length==0)
		{
			System.out.println("No Hay Valores en Room.map\n");
			return;
		}
		
		int mapIndex=0;
		float bmpwidth=b[0].getWidth(), bmpheight=b[0].getHeight();
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				c.drawBitmap(b[map[mapIndex]], viewLeft+(bmpwidth*j),viewTop+(bmpheight*i),null);
				mapIndex++;
			}//for cols
			
		}//for rows
		
		
	}//poner el background definido por los parametros
	
	public void drawBackground(Canvas c)
	{
		if(this.rows<1 || this.cols<1)
		{
			System.out.println("No Hay Valores de Renglon o de Columna\n");
			System.out.println("\nNumero de Columnas: "+this.cols+"\n");
			System.out.println("\nNumero de Renglones: "+this.rows+"\n");
		return;
		}
		
		if( visualMap.length==0)
		{
			System.out.println("No Hay Valores en Room.VisualMap\n");
		return;
		}
		
		if( map.length==0)
		{
			System.out.println("No Hay Valores en Room.map\n");
			return;
		}
		
		int mapIndex=0;
		float bmpwidth=visualMap[0].getWidth(), bmpheight=visualMap[0].getHeight();
		
		for(int i=0;i<this.rows;i++)
		{
			for(int j=0;j<this.cols;j++)
			{
				c.drawBitmap(visualMap[map[mapIndex]], viewLeft+(bmpwidth*j),viewTop+(bmpheight*i),null);
				
			//	System.out.println("index del mapa: "+mapIndex);
				if(mapIndex<this.cols*this.rows)
				mapIndex++;
				
			}//for cols
			
		}//for rows
		
	}//drawBackgorund definidos por los valores de la clase
	
	
	public void drawBackground(Canvas c, int bgcolor)
	{
	Paint p=new Paint();
	p.setColor(bgcolor);
    c.drawRect(roomLeft, roomTop, roomLeft+roomWidth, roomTop+roomHeight, p);
	}//drawbackground with a color in all the screen
	
	public void drawBackground(Canvas c, int bgcolor, boolean onlyview)
	{
		Paint p=new Paint();
		p.setColor(bgcolor);
		c.drawRect(viewLeft, viewTop, viewRight, viewBottom, p);
	}//draw a baclground only in the part of the view
	
	public void drawBackground(Canvas c, Bitmap b)
	{
		c.drawBitmap(b, this.xpos, this.ypos, null);
	}//dibujamos en pantalla el bitmap que se pasa como parametro
	
	public void drawBackground(Canvas c, Bitmap b, Paint p)
	{
		c.drawBitmap(b, this.xpos, this.ypos, p);
	}//dibujar un bitmap pero el paint que pasamos podemos usarlo para hacer splashscreen
	
	public void drawBitmapBackground(Canvas c)
	{
		c.drawBitmap(singlebg, this.xpos,this.ypos, null);
	}//dibujar el background que esta especificado en singlebg
	
	public Bitmap stretchBackground(Bitmap b)
	{
		return Bitmap.createScaledBitmap(b, (int)this.viewWidth, (int)this.viewHeight, true);
	}//modificar las dimensiones del Bitmap usado de background, el ancho y alto deben de ser mayor a cero
	
	public void drawMargin(Canvas c)
	{
		if(margin.length!=0)
		{
			for(Rect r:margin)
			{
				c.drawRect(r, this.p);
			}//for
			
		}//si tenemos algun rectangulo
		
	}//dibujar los margenes del view del juego, siempre y cuando la pantalla del dispositivo sea mayor
	 // al view del juego, y se tenga que tapar lo que esta fuera de la view, pero que queda dentro de la pantalla
	
	public void setMarginColor(int color)
	{
	this.p.setColor(color);
	}//ponerle color a los rectangulos del margen
	
	
	public Bitmap getSinglebg()
	{
	return singlebg;
    }


public void setSinglebg(Bitmap singlebg) 
{
	this.singlebg = singlebg;
}
	/*NOT CONCLUDED YET*/
/*
public void align(short align)
{
	if()
	
	
}//alinear el view al sitio especificado en la pantalla, con los valores determinados
*/
public void setAlign(int align, int w, int h,Context context)
	{
	System.out.println("entro al ALIGN");
		if(viewWidth>w || viewHeight>h)
		{
			System.out.println("\nLos valoes de del View son mayores a los de la pantalla del dispositivo\n");
			Toast.makeText(context, "ViewWidth or ViewHeight are Bigger Than Screen Measures: w="+w+ "h="+h, Toast.LENGTH_LONG).show();
			
		}
		else
		{
		switch(align)
				{
		case 0: //left
			this.viewLeft=0;
			this.viewTop=0;
			this.xpos=viewLeft;
			this.ypos=viewTop;
			
			margin=null;
			margin=new Rect[2];
			margin[0]=new Rect((int)this.viewLeft, (int)this.viewBottom, (int)this.viewWidth, (int)(h-this.viewHeight));
			margin[1]=new Rect((int)this.viewRight,(int)(w-this.viewWidth),w,h);
		//	margin[0].set
			
			break;
		
		case 1: // right
			this.viewLeft=w-this.viewWidth;
			this.viewTop=0;
			this.viewRight=w;
			this.xpos=viewLeft;
			this.ypos=viewTop;
			
			margin=null;
			margin=new Rect[2];
			margin[0]=new Rect(0,0,(int)(w-this.viewWidth),h);
			margin[1]=new Rect((int)(w-this.viewWidth),w,(int)(h-this.viewBottom),h);
			break;
			
		case 2: //midle
			this.viewLeft=(w-viewWidth)/2;
			this.viewRight=viewLeft+viewWidth;
			this.viewTop=(h-viewHeight)/2;
			this.viewBottom=viewTop+viewBottom;
			this.xpos=viewLeft;
			this.ypos=viewTop;
			margin=null;
			margin=new Rect[4];
			margin[0]=new Rect(0,0,(int)((w-this.viewWidth)/2),h);
			margin[1]=new Rect((int)(this.viewLeft+this.viewWidth),0,w,h);
			margin[2]=new Rect((int)this.viewLeft, 0, (int)this.viewRight, (int)this.viewTop);
			margin[3]=new Rect((int)this.viewLeft,(int)this.viewBottom, (int)this.viewRight,h );
			
			/*los parametros de los rectangulos son los siguientes  rect(inicio x,inicio y,hasta donde llega, coordenada hasta donde llega lo alto)*/
			break;
			
		case 3: //bottom left
			
			this.viewLeft=0;
			this.viewRight=viewWidth;
			this.viewTop=h-viewWidth;
			viewBottom=h;
			this.xpos=viewLeft;
			this.ypos=viewTop;
			break;
			
		case 4: //bottom right
			this.viewLeft=w-viewWidth;
			this.viewRight=w;
			this.viewTop=h-viewWidth;
			this.viewBottom=h;
			this.xpos=viewLeft;
			this.ypos=viewTop;
			break;
				}//suich
		}//else
	}//alinear el view
	
	
	
	/*
	 * public Sprite(Bitmap strip, int frameWidth, int frameHeight)
	{
		int bmpRows= strip.getHeight()/frameHeight;
		int bmpCols=strip.getWidth()/frameWidth;
		
		if(bmpRows<1)bmpRows=1;
		if(bmpCols<1)bmpCols=1;
		
		int totalFrames=bmpRows*bmpCols;
		
		this.frames=new Bitmap[totalFrames];
		
		int contx=0,conty=0;
		for(int x=0;x<totalFrames;x++)
		{
			this.frames[x]=Bitmap.createBitmap(strip, frameWidth*contx, frameHeight*conty, frameWidth, frameHeight);
			contx++;
			if(contx==bmpCols)
			{
				contx=0;
				while(conty<bmpRows)conty++;
			}
			
		}//for
		this.currentFrame=this.frames.length-1;
		width=frameWidth;
		height=frameHeight;
		radio=new Radio(this);
	}//Const 4
	 * */
	
	
	public void moveBitmapBgUp(float up)
	{
		this.ypos-=up;
	}//mover para arriba el bitmap background
	
	public void moveBitmapBgDwn(float down)
	{
	this.ypos+=down;
	}//mover para abajo el bitmap background
	
	public void moveBitmapBgLeft(float left)
	{
		this.xpos-=left;
	}//mover para izquierda el bitmap background
	
	public void moveBitmapBgRight(float right)
	{
	this.xpos+=right;	
	}//mover para derecha el bitmap background
	
	
	
	//FUNCION PARA DIBUJAR UN TILESHEET
	//ES PARECIDA A LA FUNCION QUE YA TENGO SOLO QUE ESTE SE HACE CON UN ARRAY DE 2 DIMENSIONES
/*	public void drawTiledMap()
	{	
	}//draw tiledmap
*/	
	
	
	
}//Room
