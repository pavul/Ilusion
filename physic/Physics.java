package com.example.cameratest.donkey;

public class Physics 
{
	
	
	public boolean hitTestPoint(float pointx, float pointy,Sprite sprite)
	{
		boolean hit=false;
		
		if(pointx>sprite.getLeft() && pointx<sprite.getRight() && pointy>sprite.getTop() && pointy<sprite.getBottom())//checks if (pointx,pointy) lie inside Sprite
		{
			hit=true;
		}

		return hit;
	}//colision entre un punto y el sprite dado

	
	public boolean hitTestPoint(int pointx, int pointy, Sprite sprite)
	{
		boolean hit=pointx>sprite.getLeft() && pointx<sprite.getRight() && pointy>sprite.getTop() && pointy<sprite.getBottom();
		return hit;
	}//misma que la anterior pero otro metodo de checar la colision
	
	
	public boolean hitTestCircle(Sprite circle1,Sprite circle2)
	{
		
		float vx=circle1.getCenterX()-circle2.getCenterX(); //vx is distance between center of 2 circles along x axis
		float vy=circle1.getCenterY()-circle2.getCenterY(); //vy is distance between center of 2 circles along y axis 

		float magnitude=(float)Math.sqrt((vx*vx)+(vy*vy)); //in case vx&&vy>0, there exists a hypotenuse whose length is == magnitude with base vx and height vy (triangle) 


		float totalRadii=circle1.getHalfWidth()+circle2.getHalfWidth(); //sum of radius of 2 circles
		
		
		boolean hit=magnitude<totalRadii;
		
		return hit;
	}//coliciones entre circulos
	
	
	public void blockCircle(Sprite circle1, Sprite circle2, boolean bounce)
	{
		float vx=circle1.getCenterX()-circle2.getCenterX();
		float vy=circle1.getCenterY()-circle2.getCenterY();
		
		float magnitude=(float)Math.sqrt((vx*vx)+(vy*vy));
		
		float totalRadii=circle1.getHalfWidth()+circle2.getHalfWidth();
		
		if(magnitude<totalRadii)
		{
			
			float overlap=totalRadii-magnitude;
			
			
		float	dx=vx/magnitude;
		float	dy=vy/magnitude;
			
		circle1.setX(circle1.getX()+overlap*dx);
		circle2.setY(circle2.getY()+overlap*dy);
			
		
		if(bounce)
		{
			/*Surface s=new Surface();
			
			s.vx=vy;
			s.vy=-vx;  POR LO PRONTO NO REBOTA*/
			
			
		}//bounce
		
		
		}//if
		
		//return hit;
		
	}//colision entre circulos pero sin sobreponerse el uno del otro
	
	
	public void bounceOffSurface(Sprite circle, Surface s)
	{
		s.lx=s.vy;
		s.ly=-s.vx;
		
		s.dx=s.vx/(float)Math.sqrt(s.vx*s.vx+s.vy+s.vy);
		s.dy=s.vy/(float)Math.sqrt(s.vx*s.vx+s.vy+s.vy);
		
		//le falta mas codigo hay que checar en el libro como hacerlo bien
		
		
		
	}//funcion auxiliar para el rebote circular
	
	
	
	public class Surface
	{
		float vy=0;
		float vx=0;
		float ly=0;
		float lx=0;
		float dy=0;
		float dx=0;
		public Surface(){}//const
	}//clase de physics
	
	
	/////////////////////////COLISIONES CON RECTANGULOS
	public boolean hitTestRectangle(Sprite rec1, Sprite rec2)
	{
		boolean hit=false;
		
		float vx=rec1.getCenterX()-rec2.getCenterX();
		float vy=rec1.getCenterY()-rec2.getCenterY();
		
		float combinedHalfWIdths=rec1.getHalfWidth()+rec2.getHalfWidth();
		float combinedHalfHeights=rec1.getHalfHeight()+rec2.getHalfHeight();
		
		
		//chear las colisiones en el eje x
		if(Math.abs(vx)<combinedHalfWIdths)
		{
			
			//puede ocurrir colision, checar el eje Y
			if(Math.abs(vy)<combinedHalfHeights)
				hit =true;
			else
			    hit=false;	
		}//if -> widths
		else
		{
			hit=false;
		}
		return hit;
	}//colisiones rectangulares
	
	
	
	public String blockRectangle(Sprite rec1, Sprite rec2, boolean moveSecond, boolean bounce )
	{
		
		String collisionSide="";

		float vx=rec1.getCenterX()-rec2.getCenterX();
		float vy=rec1.getCenterY()-rec2.getCenterY();
		
		float combinedHalfWIdths=rec1.getHalfWidth()+rec2.getHalfWidth();
		float combinedHalfHeights=rec1.getHalfHeight()+rec2.getHalfHeight();
		
		
		//chear las colisiones en el eje x
		if(Math.abs(vx)<combinedHalfWIdths)
		{
			
			//puede ocurrir colision, checar el eje Y
			if(Math.abs(vy)<combinedHalfHeights)
			{
				float overlapX=combinedHalfWIdths-Math.abs(vx);
				float overlapY=combinedHalfHeights-Math.abs(vy);
				
				if(overlapX>=overlapY)
				{
					if(vy>0)
					{
						collisionSide="TOP";
						if(moveSecond)rec2.setY(rec2.getY()-1);//empujar hacia arriba
						rec1.setY(rec1.getY()+overlapY);
					}
					else
					{
						collisionSide="Bottom";
						if(moveSecond)rec2.setY(rec2.getY()+1);//empujamos hacia abajo
						rec1.setY(rec1.getY()-overlapY);
					}//else
					if(bounce)rec1.setSpeedy(rec1.getSpeedy()*1);
				}
				else
				{
					if(vx>0)
					{
						collisionSide="LEFT";
						if(moveSecond)rec2.setX(rec2.getX()-1);//empujar hacia la izquierda
						rec1.setX(rec1.getX()+overlapX);
					}
					else
					{
						collisionSide="RIGHT";
						if(moveSecond)rec2.setX(rec2.getX()+1);//empujar hacia la derecha
						rec1.setX(rec1.getX()-overlapX);
					}
					if(bounce)rec1.setSpeedx(rec1.getSpeedx()*1);
				}//else 2
				
				
				
			}	//if heights
			else
			{
				collisionSide="NONE";
			}
		}//if -> widths
		else
		{	collisionSide="NONE";
		}
		return collisionSide;
	}//colision con rectangulos
	
	
	
	
	
	
	
	
	
}//Physics
