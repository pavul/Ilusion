/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import Config.Config;
import Sprite.Sprite;

/**
 * clase que tiene los metodos para checar colisiones entre los diferentes sprites
 * 
 * this class have few methos to check collisions between sprites, or tiles,
 * or shapes
 * 
 * @author pavulzavala
 */
public class Collision 
{
    
    
     /**
      * metodo que checa si hay colision circular entre 2 sprites
      * 
      * check if there is collision between two sprites
      * this collission is circular
     *
     * @param s1
     * @param s2
	 
	 * @return true if there is a colision and false if not
	 * @example:   colision.circleColision(player, enemy);
         */
	public static boolean circleColision(Sprite s1,  Sprite s2)
	{
		float cx=s1.getCenterX()-s2.getCenterX();
		float cy=s1.getCenterY()-s2.getCenterY();
		
		float magnitude=(float)Math.sqrt((cx*cx)+(cy*cy));
		
		float totalRadii=s1.getHalfWidth()-s2.getHalfWidth();
		
		boolean hit=magnitude<totalRadii;
		
		return hit;
		
	}//funcion para checar colision circular
        
        
        /**
	 * Example: colision.blockCircle(player,enemy,true);
	 * checks if the sprite is in colision with another sprite 
	 * the colision is with a circle shape
     * @param s1
     * @param s2
     * @param bounce
	 * 
	 * */
        public static void blockCircle(Sprite s1,Sprite s2, boolean bounce)
	{
		
		float cx=s1.getCenterX()-s2.getCenterX();
		float cy=s1.getCenterY()-s2.getCenterY();
		
		float magnitude=(float)Math.sqrt((cx*cx)+(cy*cy));
		
		float totalRadii=s1.getHalfWidth() - s2.getHalfWidth();
		
		if(magnitude < totalRadii)
		{
			
			float overlap=totalRadii-magnitude;
			
			float dx=cx/magnitude;
			float dy=cy/magnitude;
			
                        
			s1.setX(s1.getX() + overlap*dx);  
			s1.setY(s1.getY() + overlap*dy);
		
			if(bounce)
			{
				//codigo para rebotar el sprite en caso de
			}
			
		}//if
		
	}//funcion que hace colision con un circulo pero lo hace solido, 
    
    
        
        /**
         * funcion que checa si hay una colision rectangular entre 2 sprites
	 * Example:  sprite.rectangleColision(enemy);
         * 
         * checks if there is rectangle collision  between 2 sprites
         * 
         * @param s1
         * @param s2
	 * @return true if there is a colision and false if not
	 * */
	public static  boolean rectangleColision(Sprite s1, Sprite s2)
	{
		boolean hit=false;
		float vx=s1.getCenterX()-s2.getCenterX();
		float vy=s1.getCenterY()-s2.getCenterY();
		
		float combinedHalfWidth=s1.getHalfWidth()+s2.getHalfWidth();
		float combinedHalfHeight=s1.getHalfHeight()+s2.getHalfHeight();
		
		if(Math.abs(vx)<combinedHalfWidth)
		{
			
		return ((Math.abs(vy) < combinedHalfHeight));
                       
		}//width
//		else
//		{
//			return false;
//		}
		return hit;
		
	}//colision rectangular
        
        
     
    /**
     * funcion que checa si hay una colision entre sprite1 y sprite2 e indica si se debe de mover
     * el sprite que se empuja o si se debe de revotar
     * 
     * checks if there is a collision between two sprites and if the sprite 1
     * is pushing sprite 2
     *
     * Example:  sprite.blockRectangle(enemy);
     * @param s1
     * @param s2
	 * @param move, if this is set to true, the sprite will push the colision sprite
	 * @param bouncing, true if you want to make that the sprite bounce when colide with colision sprite
	 * checks if the sprite is in colision with another sprite 
	 * the colision is with a rectangle shape
	 * @return true if there is a colision and false if not
	 * 
	 * */
	public static String blockRectangle(Sprite s1,Sprite s2,boolean move ,boolean bouncing)
	{
		String side="";
		float vx=s1.getCenterX()-s2.getCenterX();
		float vy=s1.getCenterY()-s2.getCenterY();
		
		float combinedHalfWidth=s1.getHalfWidth()+s2.getHalfWidth();
		float combinedHalfHeight=s1.getHalfHeight()+s2.getHalfHeight();
		
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
						if(move)s2.setY(s2.getY()-1);
						s1.setY(s1.getY()+overlapY);
                                         }
					else
					{
						side="BOTTOM";
						if(move)s2.setY(s2.getY()+1);
                                                s1.setY(s1.getY()-overlapY);
                                                
					}
					if(bouncing)s1.setSpeedY(s1.getSpeedY()*-1);
				}
				else
				{
					
					if(vx>0)
					{
						side="LEFT";
						if(move)s2.setX(s2.getX()-1);
						s1.setX(s1.getX()+overlapX);
					}
					else
					{
						side="RIGHT";
						if(move)s2.setX(s2.getX()+1);
						s1.setX(s1.getX()-overlapX);
					}
					if(bouncing)s1.setSpeedX(s1.getSpeedX()*-1);
		         }//
			}//height
			else
			{
				side="";
//				side="NONE";
			}
			
		}//width
		else
		{
			side="";
//			side="NONE";
		}
		return side;
		
	}//colision con rectangulos, pero     
        
        
         /**
     * funcion que checa si hay una colision entre sprite1 y coordenadas X e Y e indica si se debe de mover
     * el sprite que se empuja o si se debe de rebotar
     * 
     * checks if there is a collision between sprite 1 and certain coordinate
     * and check if the sprite mush be pushed the other or if it must bounce
     * 
     * @param s1
     * @param x
     * @param y
     * @param width
     * @param height
	 * @return true if there is a colision and false if not
	 * 
	 * */
	public static String blockRectangle(Sprite s1,int x, int y, int width,int height)
	{
            int halfWidth = width / 2;
            int halfHeigth = height / 2;
            int centerX = x + halfWidth;
            int centerY = y + halfHeigth;
            
		String side=Config.COLISION_NONE;         
		float vx=s1.getCenterX()-centerX;
		float vy=s1.getCenterY()-centerY;
		
		float combinedHalfWidth=s1.getHalfWidth()+halfWidth;
		float combinedHalfHeight=s1.getHalfHeight()+halfHeigth;
            
                if(Math.abs(vx) < combinedHalfWidth)
		{
			if(Math.abs(vy) < combinedHalfHeight)
			{
				float overlapX=combinedHalfWidth-Math.abs(vx);
				float overlapY=combinedHalfHeight-Math.abs(vy);
		
				if(overlapX>overlapY)
				{

                                    if(vy>0)
					{
                                               s1.setY(s1.getY()+overlapY);
                                                return Config.COLISION_TOP;
                                        }
					else
					{

                                                s1.setY(s1.getY()-overlapY-2);   
                                                return Config.COLISION_BOTTOM;
					}

				}
                                else 
				{
					
					if(vx>0)
					{

                                            s1.setX(s1.getX()+overlapX);
                                                return Config.COLISION_LEFT;
					}
					else
					{
                                                s1.setX(s1.getX()-overlapX);
                                                return Config.COLISION_RIGHT;
					}

		         }//
			}//height

                        
		}//width
                
		return side;
		
	}//colision con rectangulos, pero     
        
        
        /**
         * funcion que checa la colision del sprite dado, con todos los tiles que hay,
         * como algunos tiles solo son para mostrar fondo, solo son pocos los que
         * se pudieran tomar como solidos, la funcion checa si el sprite hace una
         * colision con algun tile que debe de ser solido, estos tiles solidos se
         * establecen con el mapa de colision  int []COLISIONMAP
         * 
         * 
         * check the collisions of the sprite with all tiles who are in 
         * COLISIONMAP array, that means those tiles are solids and are not 
         * just to show them as background
         * 
     * @param spr
     * @param colisionMap
         * @param cols
         * @param rows 
     * @param tileWidth 
     * @param tileHeight 
     * @return  
         */
        public static  String checkColsionTile(Sprite spr,int[] colisionMap,int cols, int rows, int tileWidth, int tileHeight)
        {
            String side= Config.COLISION_NONE;
            String aux="";
            if(cols <=0 || rows <= 0)
            {
                //arrojar excepcion aqui
            return side;
            }
        
        int mapIndex=0;
        int totalTiles= cols*rows;
        
        int tilewidth = tileWidth;
        int tileHeigth = tileHeight;
      
      //for de renglones
         for(int i=0;i < rows;i++)
            {
            int tiley= i * tileHeigth;
            
                    //for de columnas
                    for(int j=0;j < cols;j++)
                    {
                        
                        int tilex= j * tilewidth;
                     //checamos si el tile es cercano al sprite
                     //si no lo esta, se continua con el que si este
//                 if()
//                 {
//                     if(mapIndex < totalTiles)
//                     {mapIndex++;}
//                     continue;
//                 }
//                 else
//                 if(tilex  >= spr.getX() - tilewidth 
//                    && (tilex + tilewidth) < (spr.getX() + spr.getW() + tilewidth ))
                    if( colisionMap[mapIndex] == 1)
                     //@TODO hacer la validacion para el eje Y
                 {
                     //checar la colision
//                     System.out.println("Tile Index: "+mapIndex);
//                     System.out.println("Antes de checar la colision: tilex: "+
//                     tilex+" tiley: "+tiley+" twidth: "+tilewidth+" theigh: "+tileHeigth);
                    side = blockRectangle(spr, tilex, tiley, tilewidth,tileHeigth);
                    if (side.equals(Config.COLISION_BOTTOM))
                    {
                    aux=Config.COLISION_BOTTOM;
                    }
                    
//                    return side; 
//                    if(!side.equals(Config.COLISION_NONE)) 
//                    {
//                    break ;
//                    }//
//                    else
//                    {
//                     if(mapIndex < totalTiles)
//                     {mapIndex++;}
//                    }
//                    
                 }//if validacion si se checa colision
                 
                      if(mapIndex < totalTiles)
                      mapIndex++;
                    }//for cols
            }//for rows
      
//          System.out.println("-checkColsionTile se regreso SIDE: "+side);
        
          if(!aux.equals(""))
          {
          side=aux;
          }
          
          return side;
        }//checkcolisiontile
        
        
        
        

//        System.out.println("se DIBUJAN TILES: "+nodib);
        
//    }//
    
}//collision
