package com.me.pruebaFiguras.splashscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.me.pruebaFiguras.PruebaFiguras;
import com.me.pruebaFiguras.menus.menuInicio;

/**
 * Splash screen inicial para antes de llegar al menu.
 * Implementa Screen
 * @author aibanez
 *
 */
public class SplashScreenInicio implements Screen{
	private SpriteBatch batch;
	private PruebaFiguras juego;
	private Texture splash;
	private OrthographicCamera camera;
	private long tiempoInicio;
	
	private int splashH;
	private int splashW;
	
	/**
	 * Constructor de esta pantalla
	 * @param juego le pasamos la clase juego por si nos interesa coger informacion de ella (en todas las screen he seguido este paso)
	 */
	public SplashScreenInicio(PruebaFiguras juego){
		this.juego = juego; //Se guarda juego
		camera = new OrthographicCamera(); //instanciamos una camara
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//512, 512);//Le damos las medidas a la camara pasandole la altura y anchura
	}
	
	/**
	 * El bucle de juego donde devemos imprimir por pantalla lo que nos interesa, en este caso mostramos el splash screen asta pasados 5 segundos
	 */
	@Override
	public void render(float delta) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujamos la imagen copiando la anchura y altura de la pantalla ( lo que hace deformar la imagen por lo que hay que pensar en algo)
		batch.draw(splash, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		
		//En caso de que hayan pasado 5 segundos pasamos al Screen del menu
		if ( TimeUtils.millis() > (tiempoInicio + 5000)) juego.setScreen(new menuInicio(juego));
	}

	/**
	 * En caso de que se cambie el tamanio de la pantalla ( supongo que para cuando tumbas el movil)
	 * por el momento las variables que hay dentro no han sido usadas
	 */
	@Override
	public void resize(int width, int height) {
		splashW = width;
		splashH = height;
	}
	/**
	 * Aqui es donde se deberian cargar todas las variables que nos interesan
	 */
	@Override
	public void show() {
		batch = new SpriteBatch();
		
		splash = new Texture(Gdx.files.internal("data/OlimposScreen.png"));
		tiempoInicio = TimeUtils.millis();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Comandos a lanzar cuando se cierra este Screen
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		splash.dispose();
	}

}
