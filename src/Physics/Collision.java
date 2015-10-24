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
 * @author pavulzavala
 */
public class Collision 
{
    
    
     /**
      * metodo que checa si hay colision circular entre 2 sprites
     *
     * @param s1
     * @param s2
	 
	 * @return true if there is a colision and false if not
	 * @example:   colision.circleColision(player, enemy);
         */
	public boolean circleColision(Sprite s1,  Sprite s2)
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
        public void blockCircle(Sprite s1,Sprite s2, boolean bounce)
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
     * @param s1
     * @param s2
	 * @return true if there is a colision and false if not
	 * */
	public boolean rectangleColision(Sprite s1, Sprite s2)
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
	public String blockRectangle(Sprite s1,Sprite s2,boolean move ,boolean bouncing)
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
				side="NONE";
			}
			
		}//width
		else
		{
			side="NONE";
		}
		return side;
		
	}//colision con rectangulos, pero     
        
        
         /**
     * funcion que checa si hay una colision entre sprite1 y sprite2 e indica si se debe de mover
     * el sprite que se empuja o si se debe de rebotar
     * @param s1
     * @param x
     * @param y
     * @param width
     * @param height
	 * @return true if there is a colision and false if not
	 * 
	 * */
	public String blockRectangle(Sprite s1,int x, int y, int width,int height)
                //,boolean move ,boolean bouncing)
	{
            int halfWidth = width / 2;
            int halfHeigth = height / 2;
            int centerX = x + halfWidth;
            int centerY = y + halfHeigth;
            
            
//		String side="";
		String side=Config.COLISION_NONE;
//                System.out.println("vx = player.cx - centery: "+s1.getCenterX()+" - "+centerX);
//                System.out.println("vy = player.cy - centery: "+s1.getCenterY()+" - "+centerY);
                
		float vx=s1.getCenterX()-centerX;
		float vy=s1.getCenterY()-centerY;
		
		float combinedHalfWidth=s1.getHalfWidth()+halfWidth;
		float combinedHalfHeight=s1.getHalfHeight()+halfHeigth;
                
//		System.out.println(" combhalwid: "+combinedHalfWidth);
//                System.out.println(" comhalheig: "+combinedHalfHeight);
                
		if(Math.abs(vx) < combinedHalfWidth)
		{
			if(Math.abs(vy) < combinedHalfHeight)
			{
				float overlapX=combinedHalfWidth-Math.abs(vx);
				float overlapY=combinedHalfHeight-Math.abs(vy);
				
//                                System.out.println("overlapX: "+overlapX);
//                                System.out.println("overlapY: "+overlapY);
                                
                                
				if(overlapX>overlapY)
				{
//                                    System.out.println("ENtra a overlap Y ");
					if(vy>0)
					{
//						side=Config.COLISION_TOP;
//						if(move)s2.setY(s2.getY()-1);
						s1.setY(s1.getY()+overlapY);
                                                return Config.COLISION_TOP;
                                        }
					else
					{
//						side=Config.COLISION_BOTTOM;
//						if(move)s2.setY(s2.getY()+1);
                                                s1.setY(s1.getY()-overlapY-2);   
                                                return Config.COLISION_BOTTOM;
					}
//					if(bouncing)s1.setSpeedY(s1.getSpeedY()*-1);
				}
                                else 
				{
					
					if(vx>0)
					{
//						side=Config.COLISION_LEFT;
						
//						if(move)s2.setX(s2.getX()-1);
						s1.setX(s1.getX()+overlapX);
                                                return Config.COLISION_LEFT;
					}
					else
					{
//						side=Config.COLISION_RIGHT;
//						if(move)s2.setX(s2.getX()+1);
						s1.setX(s1.getX()-overlapX);
                                                return Config.COLISION_RIGHT;
					}
//					if(bouncing)s1.setSpeedX(s1.getSpeedX()*-1);
		         }//
			}//height
//			else
//			{
//				side=Config.COLISION_NONE;
//			}
			
		}//width
//		else
//		{
//			side=Config.COLISION_NONE;
//		}
		return side;
		
	}//colision con rectangulos, pero     
        
        
        /**
         * funcion que checa la colision del sprite dado, con todos los tiles que hay,
         * como algunos tiles solo son para mostrar fondo, solo son pocos los que
         * se pudieran tomar como solidos, la funcion checa si el sprite hace una
         * colision con algun tile que debe de ser solido, estos tiles solidos se
         * establecen con el mapa de colision  int []COLISIONMAP
     * @param spr
     * @param colisionMap
         * @param cols
         * @param rows 
     * @param tileWidth 
     * @param tileHeight 
     * @return  
         */
        public String checkColsionTile(Sprite spr,int[] colisionMap,int cols, int rows, int tileWidth, int tileHeight)
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
