package com.me.pruebaFiguras.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.me.pruebaFiguras.NivelPrueba1;
import com.me.pruebaFiguras.PruebaFiguras;
import com.me.pruebaFiguras.nivelesFormas.NivelFormasPrueba1;
import com.me.pruebaFiguras.nivelesFormas.NivelFormasPrueba2;

/**
 * Screen que mostrará el menu para elegir el tipo de juego en el modo aventura
 * @author tukan
 *
 */
public class MenuAventura implements Screen {
	private SpriteBatch batch;
	private PruebaFiguras juego;
	private MenuAventura menu;
	private Stage stage;
	private Skin skin;
	
	/**
	 * Constructor menu aventura
	 * @param juego
	 */
	public MenuAventura(PruebaFiguras juego){
		this.juego = juego;
		this.menu = this;
	}
	
	@Override
	public void render(float delta) {
		//Las 2 siguientes lineas se encargan de limpiar la pantalla,
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//Fin limpiar la pantalla
		
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		
		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.CYAN);
		pixmap.fill();
		
		//se le aniade al skin
		skin.add("white", new Texture(pixmap));
		
		//el contructor por defecto de fuentes genera la fuente arial 15, creo que en otro caso hay que pasarle el mapa de caracteres
		BitmapFont bFont = new BitmapFont();
		//el tamanio creo
		bFont.scale(1);
		//aniadimos la fuente al skin
		skin.add("default", bFont);
		
		//estilos para el boton
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white",  Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white",  Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white",  Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white",  Color.LIGHT_GRAY);
		
		textButtonStyle.font = skin.getFont("default");
		
		skin.add("default", textButtonStyle);
		
		//Boton para acceder al juego de colores
		final TextButton btnAvenColores = new TextButton("Colores", textButtonStyle);
		//Aparecerá en la parte superior izquierda
		btnAvenColores.setPosition(0, Gdx.graphics.getHeight() - btnAvenColores.getHeight());
		stage.addActor(btnAvenColores);
		
		//Boton para acceder al juego de formas
		final TextButton btnAvenFormas = new TextButton("Formas", textButtonStyle);
		//Aparecerá en la parte superior derecha
		btnAvenFormas.setPosition(Gdx.graphics.getWidth() - btnAvenFormas.getWidth(), Gdx.graphics.getHeight() - btnAvenFormas.getHeight());
		stage.addActor(btnAvenFormas);
		
		//Boton para acceder al juego de sumas
		final TextButton btnAvenSumas = new TextButton("Sumas", textButtonStyle);
		//Aparecerá en la parte inferior izquierda
		btnAvenSumas.setPosition(0, 0);
		stage.addActor(btnAvenSumas);
		
		//Boton para acceder al juego de lógica
		final TextButton btnAvenLogica = new TextButton("Logica", textButtonStyle);
		//Aparecerá en la parte inferior derecha
		btnAvenLogica.setPosition(Gdx.graphics.getWidth() - btnAvenLogica.getWidth(), 0);
		stage.addActor(btnAvenLogica);
		

		//Similar a un onclick
		btnAvenFormas.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				btnAvenFormas.setText("Starting new game");
				//Se carga la primera pantalla ( aun que estaria bien cargar un menu de niveles para elejir uno)
				juego.setScreen( new NivelFormasPrueba1(juego));
			}
			
		});
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		skin.dispose();
		stage.dispose();
	}

}
