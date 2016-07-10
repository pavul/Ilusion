package com.example.cameratest.donkey;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Music 
{

public static	SoundPool sp;
public static	MediaPlayer [] mp;
public static   AudioManager am;	

	public float volumeLeft=1;
	public float volumeRight=1;
	
	boolean isPlaying=false;
	boolean isMuted=false;
	
	int [] sfx;
	

	public Music(int backgroundsongs, int sfxsoundsAtSameTime, Context context)
	{
	
		mp=new MediaPlayer[backgroundsongs];
		sp=new SoundPool(sfxsoundsAtSameTime, AudioManager.STREAM_MUSIC,0);
		try{
			am=(AudioManager)context.getSystemService(context.AUDIO_SERVICE);
		}catch(Exception e){e.printStackTrace();}
		
	}//const
	
	/*METODOS PARA MUSICA DE FONDO*/
	
	public int getSongsNumber()
	{return mp.length;}
	
	public void setBgSong(int index, Context context, int songId)
	{
	mp[index]=MediaPlayer.create(context, songId);	
	}
	
	public void playBgSong(int index)
	{
		mp[index].start();
	}
	
	public void setLoopingBgSong(int index, boolean looping)
	{
		mp[index].setLooping(looping);
	}
	
	public void stopBgSong(int index)
	{
		mp[index].stop();
	}
	
	public void stopAll()
	{
		int l=getSongsNumber();
		for(int x=0;x<l;x++)
		{
			if(mp[x].isPlaying())
			mp[x].stop();
		}//for
		
		int lsfx=sfx.length;
		for(int y=0;y<lsfx;y++)
		{
			sp.stop(sfx[y]);
		}
		
	}//
	
	public void releaseBgSong(int index)
	{
		if(mp[index].isPlaying())
		{mp[index].stop();}
		mp[index].release();
	}
	
	public float getDeviceVolume()
	{
		float vol=am.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxvol=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		return vol/maxvol;
	}

	public float getVolumeLeft() {
		return volumeLeft;
	}

	public void setVolumeLeft(float volumeLeft) {
		this.volumeLeft += volumeLeft;
	}

	public float getVolumeRight() {
		return volumeRight;
	}

	public void setVolumeRight(float volumeRight) {
		this.volumeRight += volumeRight;
	}
	
	public void setBgSongVolume(int index)
	{
		mp[index].setVolume(getDeviceVolume(), getDeviceVolume());
		
	}
	
	public void setBgSongVolume(int index, float volumeLeft, float volumeRight)
	{
		mp[index].setVolume(volumeLeft,volumeRight );
	}
	
	public void muteBgSong(int index)
	{
		if(isMuted)
		{
		mp[index].setVolume(getDeviceVolume(),getDeviceVolume());
		isMuted=false;
		}
		else
		{
			mp[index].setVolume(0,0);
			isMuted=true;
		}
	}
	
	public void pauseBgSong(int index)
	{
		mp[index].pause();
	}
	
	public boolean isMuted() {
		return isMuted;
	}

	public void setMuted(boolean isMuted) {
		this.isMuted = isMuted;
	}
	
	public boolean isPlayingBgSong(int index)
	{
		return mp[index].isPlaying();
	}
	
	
	/*METODOS PARA LOS EFECTOS DE SONIDO*/
	
	public void loadSfx(int sfxNumber, Context context, int []sfxResourceId)
	{
		sfx=new int[sfxNumber];
		for(int i=0;i<sfxNumber;i++)
		{
			sfx[i]=sp.load(context, sfxResourceId[i], 1);
		}//for
	}//cargar los sonidos
	
	public void playSfx(int index, boolean looping)
	{
		int loop;
		if(looping)
			loop=-1;
		else
			loop=0;
	
		sp.play(sfx[index], getDeviceVolume(), getDeviceVolume(), 1, loop, 1);
	}//tocar el sonido
	
	public int getSfxNumber()
	{return sfx.length;}
	
	public void stopSfx(int index)
	{
		sp.stop(sfx[index]);
	}
	
	
	public void setSfxVolume(int index)
	{
		sp.setVolume(sfx[index], getDeviceVolume(), getDeviceVolume());
	}

	public void setSfxVolume(int index, float volumeLeft, float volumeRight)
	{
		sp.setVolume(sfx[index], volumeLeft, volumeRight);
	}
	
	public void releaseSfx()
	{
		sp.release();
	}
	
	
	public void pauseSfx(int index)
	{
		sp.pause(sfx[index]);
	}
	
	public void resumeSfx(int index)
	{
		sp.resume(sfx[index]);
	}
	
	public void releaseAll()
	{
		for(int i=0;i<mp.length;i++)
		{
			mp[i].release();
		}//for
		
		sp.release();
	}//RELEASE ALL RESOURCES
	
	
}//class
