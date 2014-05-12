package com.me.pruebaFiguras.nivelesFormas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.pruebaFiguras.Figura;
import com.me.pruebaFiguras.HuecoFigura;
import com.me.pruebaFiguras.PruebaFiguras;
import com.me.pruebaFiguras.Figura.TipoFigura;
import com.me.pruebaFiguras.motor.Motor;

public class NivelFormasPrueba1 implements Screen {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shrend;
	
	public PruebaFiguras juego;
	private Motor motor;
	
	public NivelFormasPrueba1(PruebaFiguras juego){
		this.juego = juego;
		motor = new Motor();
	}
	@Override
	public void render(float delta) {
		//Limpieza de pantalla
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//Fin de limpieza de pantalla
		
		//esto creo que es para ajustar el batch a la camara
		batch.setProjectionMatrix(camera.combined);
		
		//Calculamos todos los cambios
		motor.actualizarEstado();
		//Dibujamos
		motor.dibujar(shrend, batch);
		
		//Inicio comprobacion de cubo con bordes (para que no desaparezca por las esquinas)
//		if (cuadrado.getPosX() < 0)
//			cuadrado.setPosX(0);
//			
//		if(cuadrado.getPosX() + (64 * cuadrado.getMedida()) > juego.w)
//			cuadrado.setPosX(juego.w - (64 * cuadrado.getMedida()));
//		
//		if(cuadrado.getPosY() < 0)
//			cuadrado.setPosY(0);
//			
//		if (cuadrado.getPosY() + (64 * cuadrado.getMedida()) > juego.h)
//			cuadrado.setPosY(juego.h - (164 * cuadrado.getMedida()));
			
		//Fin de comprobacion de cubo con bordes
		
//		//Se empieza a dibujar
//		batch.begin();
//		//fuente por defecto en color negro
//		BitmapFont font = new BitmapFont();
//		font.setColor(Color.BLACK);
//		
//		//mostrar posicion X e Y de donde se a pulsado
//		font.draw(batch, "X=" + Gdx.input.getX() + ", Y=" + Gdx.input.getY(), 10, 20);
//		
//		//Dibujamos el hueco donde hay que insertar el cubo
//		//shrend es la herramienta para dibujar formas
//		//queremos dibujar relleno
//		shrend.begin(ShapeType.Filled);
//		//del color de la propiedad del objeto hueco
//		shrend.setColor(hueco.getColor());
//		//dibujamos un rectangulo
//		shrend.rect(hueco.getPosX(), hueco.getPosY(), 68 * hueco.getMedida(), 68 * hueco.getMedida());
//		//acabamos con el hueco y se dibuja
//		shrend.end();
//		
//		//Dibujamos el cubo
//		//relleno
//		shrend.begin(ShapeType.Filled);
//		// color
//		shrend.setColor(cuadrado.getColor());
//		//rectangulo
//		shrend.rect(cuadrado.getPosX(), cuadrado.getPosY(), 64 * cuadrado.getMedida(), 64 * cuadrado.getMedida());
//		//dibujar
//		shrend.end();
//		
//		//En caso de que se inserte el cubo
//		if (hueco.isInside(cuadrado)){
//			//Se escribe bien echo
//			font.draw(batch, "Bien echo", 50, 50);
//		}
//		//fin del batch y se dibuja creo(la posicion de click y el texto "bien echo" si se da la condicion.
//		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		shrend = new ShapeRenderer();
		
		juego.w = Gdx.graphics.getWidth();
		juego.h = Gdx.graphics.getHeight();
		
		motor.agregarFigura(new Figura(100, 50, TipoFigura.CUADRADO, Color.GREEN));
		motor.agregarHueco(new HuecoFigura(10, 50, new Figura(10, 10, TipoFigura.CUADRADO, Color.GREEN)));
		
		motor.agregarFigura(new Figura(100, 200, TipoFigura.CIRCULO, Color.GREEN));
		motor.agregarHueco(new HuecoFigura(10, 200, new Figura(100, 200, TipoFigura.CIRCULO, Color.GREEN)));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, juego.w, juego.h);
		
		batch = new SpriteBatch();
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
		batch.dispose();
		shrend.dispose();
	}

}
