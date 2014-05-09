package com.me.pruebaFiguras.motor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.pruebaFiguras.Figura;
import com.me.pruebaFiguras.HuecoFigura;

public class Motor {
	private List<Figura> figuras;
	private List<HuecoFigura> huecos;
	private Figura figuraSeleccionada;
	
	private boolean finDeJuego;
	
	public Motor(){
		figuras = new ArrayList<>();
		huecos = new ArrayList<>();
		finDeJuego = false;
	}
	
	public void agregarFigura(Figura figura){
		figuras.add(figura);
	}
	
	public void agregarHueco(HuecoFigura hueco){
		huecos.add(hueco);
	}
	
	public Figura figuraTocada(float posX, float posY){
		System.out.println("Dentro de funcion figuraTocada");
		for (Figura figura: figuras){
			System.out.println("Analizando figura");
			if (figura.isTouched(posX, posY)){
				return figura;
			}
		}
		System.out.println("figuraTocada retorna null");
		return null;
	}
	
	/**
	 * Esta funcion comprobara todos los cambios que se haran antes de renderizar.
	 */
	public void actualizarEstado(){
		int posY;
		//Si la pantalla es tocada
		if (Gdx.input.isTouched()){
			/*La posicion 0 de Y del click esta arriba, 
			 * mientras que la posicion 0 y al dibujar esta abajo
			 * por lo tanto damos la vuelta a la posicion de Y de forma que coincida con la de dibujo
			 */
			posY = Gdx.graphics.getHeight() - Gdx.input.getY();
			//Si no hay una figura actualmente seleccionada
			if (figuraSeleccionada == null){
				System.out.println("Figura Seleccionada = null");
				//Guardamos la nueva figura seleccionada. null en caso de que no se seleccione ninguna
				figuraSeleccionada = figuraTocada(Gdx.input.getX(), posY);
			}
			if (figuraSeleccionada != null){//En caso de que la figura ya este seleccionada
				//Le cambiamos la posicion
				figuraSeleccionada.setPosX(Gdx.input.getX());
				//Elevamos la figura para evitar taparla con el dedo
				figuraSeleccionada.setPosY(posY + 10);
				
				evitarSobrepasarBordes(figuraSeleccionada);
				
				//Comprobar si la figura encaja en el hueco
				for(HuecoFigura hueco: huecos)
					figuraSeleccionada.setEncajado(comprobarIncrustado(figuraSeleccionada, hueco));
				
				//Comprobar si todos los casos han sido incrustados para determinar el fin del juego
				finDeJuego = true;
				for(Figura figura: figuras)
					if(!figura.isEncajado()){
						finDeJuego=false;
					}
			}
			
			
			
		}//En caso de que la pantalla no sea tocada
		else {
			//Se deja de tener seleccionada la figura pasando el valor a null
			figuraSeleccionada = null;
		}
		/* Es decir: 
		 * desde que se toca una figura se considerara que esta seleccionada 
		 * hasta que se deje de tocar la pantalla.
		 */
		
		
	}
	
	public void dibujar(ShapeRenderer shrend, SpriteBatch batch){
		batch.begin();
		//fuente por defecto en color negro
		BitmapFont font = new BitmapFont();
		font.setColor(Color.BLACK);
		
		//mostrar posicion X e Y de donde se a pulsado
		font.draw(batch, "X=" + Gdx.input.getX() + ", Y=" + Gdx.input.getY(), 10, 20);
		
		//Dibujamos los huecos
		for(HuecoFigura hueco: huecos)
		{
			//shrend es la herramienta para dibujar formas
			//queremos dibujar relleno
			shrend.begin(ShapeType.Filled);
			//del color de la propiedad del objeto hueco
			shrend.setColor(hueco.getColor());
			//dibujamos un rectangulo
			shrend.rect(hueco.getPosX(), hueco.getPosY(), 68 * hueco.getMedida(), 68 * hueco.getMedida());
			//acabamos con el hueco y se dibuja
			shrend.end();
		}
		
		
		//Dibujamos las formas
		for(Figura figura: figuras){
			//relleno
			shrend.begin(ShapeType.Filled);
			// color
			shrend.setColor(figura.getColor());
			//rectangulo
			shrend.rect(figura.getPosX(), figura.getPosY(), 64 * figura.getMedida(), 64 * figura.getMedida());
			//dibujar
			shrend.end();
		}
		
		//En caso de fin de juego
		if (finDeJuego){
			//Se escribe bien echo
			font.draw(batch, "Bien echo", 50, 50);
		}
		//fin del batch y se dibuja creo(la posicion de click y el texto "bien echo" si se da la condicion.
		batch.end();
	}
	/**
	 * Funcion para evitar que la figura seleccionada sobrepase los bordes de la pantalla.
	 * Evitando asi perder la figura mas haya de esta.
	 * @param figura
	 */
	private void evitarSobrepasarBordes(Figura figura){
		if (figura.getPosX() < 0)
			figura.setPosX(0);
			
		if(figura.getPosX() + (64 * figura.getMedida()) > Gdx.graphics.getWidth())//juego.w)
			figura.setPosX(Gdx.graphics.getWidth() - (64 * figura.getMedida()));
		
		if(figura.getPosY() < 0)
			figura.setPosY(0);
			
		if (figura.getPosY() + (64 * figura.getMedida()) > Gdx.graphics.getHeight())//juego.h)
			figura.setPosY(Gdx.graphics.getHeight() - (164 * figura.getMedida()));
	}
	
	private boolean comprobarIncrustado(Figura figura, HuecoFigura hueco){
		//En caso de que se inserte el cubo
		if (hueco.isInside(figura)){
			return true;
		}
		return false;
	}
}
