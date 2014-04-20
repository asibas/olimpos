package com.me.pruebaFiguras.menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
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

/**
 * Screen que mostrara el menu inicial
 * @author aibanez
 *
 */
public class menuInicio implements Screen {
	private SpriteBatch batch;
	private PruebaFiguras juego;
	private Stage stage;
	private Skin skin;
	
	/**
	 * Como en todos los casos un constructor al que le pasamos el juego como parametro
	 * @param juego base del juego
	 */
	public menuInicio(PruebaFiguras juego){
		this.juego = juego;
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
		stage.setViewport(width, height);
	}

	/**
	 * aqui creamos las variables que nos interesan
	 */
	@Override
	public void show() {
		//batch es la herrammienta para dibujar
		batch = new SpriteBatch();
		//stage sera el boton
		stage = new Stage();
		//creo que esto le da las propiedades de los imputs (click y demas)
		Gdx.input.setInputProcessor(stage);
		
		//en skin se disenia graficamente el boton
		skin = new Skin();
		
		//tendra un tamanio de 100x100 con un color cyan (lo del color no me queda claro por que despues se le  da otro)
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
		
		//Boton para acceder al modo historia
		final TextButton btnModoHistoria = new TextButton("Modo historia", textButtonStyle);
		//Aparecerá en la parte superior izquierda
		btnModoHistoria.setPosition(0, Gdx.graphics.getHeight() - btnModoHistoria.getHeight());
		stage.addActor(btnModoHistoria);
//		stage.addActor(textButton);
//		stage.addActor(textButton);
		
		//Boton para acceder al modo aventura
		final TextButton btnModoAventura = new TextButton("Modo aventura", textButtonStyle);
		//Aparecerá en la parte superior derecha
		btnModoAventura.setPosition(Gdx.graphics.getWidth() - btnModoAventura.getWidth(), Gdx.graphics.getHeight() - btnModoHistoria.getHeight());
		stage.addActor(btnModoAventura);
		
		//Boton para acceder al menu de opciones
		final TextButton btnOpciones = new TextButton("Opciones", textButtonStyle);
		//Aparecerá en la parte inferior izquierda
		btnOpciones.setPosition(0, 0);
		stage.addActor(btnOpciones);
		
		//Boton para acceder al menu de opciones
		final TextButton btnSalir = new TextButton("Salir", textButtonStyle);
		//Aparecerá en la parte inferior derecha
		btnSalir.setPosition(Gdx.graphics.getWidth() - btnSalir.getWidth(), 0);
		stage.addActor(btnSalir);
		

		//Similar a un onclick
		btnModoHistoria.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				btnModoHistoria.setText("Starting new game");
				//Se carga la primera pantalla ( aun que estaria bien cargar un menu de niveles para elejir uno)
				juego.setScreen( new NivelPrueba1(juego));
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
		stage.dispose();
		skin.dispose();
	}
	
}
