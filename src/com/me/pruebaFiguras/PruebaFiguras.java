package com.me.pruebaFiguras;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.pruebaFiguras.splashscreens.SplashScreenInicio;
/**
 * Clase que representa el centro de juego, apartir de aqui se iran llamando a las diferentes pantallas.
 * 
 * @author aibanez
 *
 */
public class PruebaFiguras extends Game {
	public float h, w;
	
	public NivelPrueba1 pJuego;
	
	/**
	 * Este metodo es llamado al crearse el juego, aqui damos valor a todas la variables que nos interesa y llamaremos a las pantallas
	 */
	@Override
	public void create() {		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		//pJuego = new NivelPrueba1(this);
		//llamamos a la pantalla que seria el splash screen
		this.setScreen(new SplashScreenInicio(this));
	}

	/**
	 * Este metodo es llamado cuando se destruye el juego, aqui es donde hay que destruir (dispose) todo lo que tengamos en memoria
	 */
	@Override
	public void dispose() {
		//pJuego.dispose();
	}
}
