package com.example.cameratest.donkey;

import android.content.Context;
import android.graphics.Bitmap;

public class SpriteRpg extends Sprite
{

	//Variables de status
	private int fuerza=0, 
	            defenza=0, 
	            velocidad=0,
	            destreza=0,
	            inteligencia=0,
	            suerte=0;
	            
	
	public SpriteRpg(Bitmap b) {
		super(b);
		// TODO Auto-generated constructor stub
	}//const 1
	
	
	
	
	public SpriteRpg(Bitmap strip, int frameWidth, int frameHeight) {
		super(strip, frameWidth, frameHeight);
		// TODO Auto-generated constructor stub
	}//const 2




	public SpriteRpg(Bitmap[] frames) {
		super(frames);
		// TODO Auto-generated constructor stub
	}//const 3




	public SpriteRpg(int numFrames, int w, int h, Bitmap strip) {
		super(numFrames, w, h, strip);
		// TODO Auto-generated constructor stub
	}//const 4




	public SpriteRpg(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}//const 5




	public SpriteRpg(int[] ResourceId, Context context) {
		super(ResourceId, context);
		// TODO Auto-generated constructor stub
	}//const 6




	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getDefenza() {
		return defenza;
	}

	public void setDefenza(int defenza) {
		this.defenza = defenza;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getDestreza() {
		return destreza;
	}

	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public int getSuerte() {
		return suerte;
	}

	public void setSuerte(int suerte) {
		this.suerte = suerte;
	}

	
	
	
}//SpriteRpg
