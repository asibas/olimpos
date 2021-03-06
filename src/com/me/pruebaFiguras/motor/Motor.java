package com.me.pruebaFiguras.motor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.pruebaFiguras.Figura;
import com.me.pruebaFiguras.HuecoFigura;
import com.me.pruebaFiguras.PruebaFiguras;
import com.me.pruebaFiguras.nivelesFormas.NivelFormasPrueba2;

public class Motor {
	private int nivel;
	private List<Figura> figuras;
	private List<HuecoFigura> huecos;
	private Figura figuraSeleccionada;
	
	private boolean finDeJuego;
	
	private Screen siguienteNivel;
	
	public Motor(){
		figuras = new ArrayList<>();
		huecos = new ArrayList<>();
		finDeJuego = false;
		nivel = 1;
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
			//Si la figura no esta encajada y esta siendo pulsada
			if (!figura.isEncajado() && figura.isTouched(posX, posY)){
				return figura;//Se retorna la figura
			}
		}
		System.out.println("figuraTocada retorna null");
		return null;//En caso de no haber pulsado ninguna figura se retorna null
	}
	
	/**
	 * Esta funcion comprobara todos los cambios que se haran antes de renderizar.
	 */
	public void actualizarEstado(PruebaFiguras juego){
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
				//Colocamos el centro horizontal del cuadrado donde hemos pulsado
				figuraSeleccionada.setPosX(Gdx.input.getX());
				//Elevamos la figura para evitar taparla con el dedo
				figuraSeleccionada.setPosY(posY + figuraSeleccionada.getMedida());
				
				evitarSobrepasarBordes(figuraSeleccionada);
				
				//Comprobar si la figura encaja en el hueco
				for(HuecoFigura hueco: huecos)
				{
					if (comprobarIncrustado(figuraSeleccionada, hueco)){
						//marcamos como encajada
						figuraSeleccionada.setEncajado(true);
						//centramos la figura en el hueco
						figuraSeleccionada.setPosX(hueco.getPosX());
						figuraSeleccionada.setPosY(hueco.getPosY());
						
					}
				}
				//Si la figura a sido encajada deseleccionamos la figura para evitar seguir moviendola
				if (figuraSeleccionada.isEncajado())
					figuraSeleccionada = null;
				
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
		if (finDeJuego && Gdx.input.justTouched()){
			System.out.println("fin de juego");
			nivel++;
			switch(nivel){
			case 2:  siguienteNivel = new NivelFormasPrueba2(juego);
			}
			juego.setScreen(siguienteNivel);
		}
		
	}
	
	public void dibujar(ShapeRenderer shrend, SpriteBatch batch){
		batch.begin();
		//fuente por defecto en color negro
		BitmapFont font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(4);
		
		//mostrar posicion X e Y de donde se a pulsado
		//font.draw(batch, "X=" + Gdx.input.getX() + ", Y=" + Gdx.input.getY(), 10, 20);
		
		//Dibujamos los huecos
		for(HuecoFigura hueco: huecos)
		{
			//shrend es la herramienta para dibujar formas
			//queremos dibujar relleno
			shrend.begin(ShapeType.Filled);
			//del color de la propiedad del objeto hueco
			shrend.setColor(Color.GRAY);
			switch(hueco.getTipoFigura()){
			case CUADRADO:
				shrend.rect(hueco.getPosX() - hueco.getMedida(), hueco.getPosY() - hueco.getMedida(), hueco.getMedida() * 2, hueco.getMedida() * 2);
				break;
			case CIRCULO:
				shrend.circle(hueco.getPosX(), hueco.getPosY(), hueco.getMedida());
				break;
			default:
				shrend.rect(hueco.getPosX() - hueco.getMedida(), hueco.getPosY() - hueco.getMedida(), hueco.getMedida() * 2, hueco.getMedida() * 2);
			}
			shrend.end();
			
			//Bordes
			shrend.begin(ShapeType.Line);
			//del color de la propiedad del objeto hueco
			shrend.setColor(hueco.getColor());
			switch(hueco.getTipoFigura()){
			case CUADRADO:
				shrend.rect(hueco.getPosX() - hueco.getMedida(), hueco.getPosY() - hueco.getMedida(), hueco.getMedida() * 2, hueco.getMedida() * 2);
				break;
			case CIRCULO:
				shrend.circle(hueco.getPosX(), hueco.getPosY(), hueco.getMedida());
				break;
			default:
				shrend.rect(hueco.getPosX() - hueco.getMedida(), hueco.getPosY() - hueco.getMedida(), hueco.getMedida() * 2, hueco.getMedida() * 2);
			}
			shrend.end();
		}
		
		
		//Dibujamos las formas
		for(Figura figura: figuras){
			//relleno
			shrend.begin(ShapeType.Filled);
			// color
			shrend.setColor(figura.getColor());
			switch (figura.getTipoFigura()){
			case CUADRADO:
				shrend.rect(figura.getPosX() - figura.getMedida(), figura.getPosY() - figura.getMedida(), figura.getMedida() * 2, figura.getMedida() * 2);
				break;
			case CIRCULO:
				shrend.circle(figura.getPosX(), figura.getPosY(),figura.getMedida());
				break;
			default:
				shrend.rect(figura.getPosX() - figura.getMedida(), figura.getPosY() - figura.getMedida(), figura.getMedida() * 2, figura.getMedida() * 2);
			}
			
			//dibujar
			shrend.end();
		}
		
		//En caso de fin de juego
		if (finDeJuego){
			//Se escribe bien echo
			font.draw(batch, "Bien hecho", Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2);
			
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
		if (figura.getPosX() - figura.getMedida() < 0)
		{
			System.out.println("Recolocando a la izquierda: " + (figura.getPosX() - figura.getMedida()));
			System.out.println("figura.getPosX = " + figura.getPosX());
			System.out.println("figura.getMedida = " + figura.getMedida());
			figura.setPosX(figura.getMedida());
		}
			
			
		if(figura.getPosX() + figura.getMedida() > Gdx.graphics.getWidth())//juego.w)
			figura.setPosX(Gdx.graphics.getWidth() - figura.getMedida());
		
		if(figura.getPosY() < 0)
			figura.setPosY(figura.getMedida());
			
		if (figura.getPosY() + figura.getMedida() > Gdx.graphics.getHeight())//juego.h)
			figura.setPosY(Gdx.graphics.getHeight() - figura.getMedida());
	}
	
	private boolean comprobarIncrustado(Figura figura, HuecoFigura hueco){
		//En caso de que se inserte el cubo
		if (hueco.getColor().equals(figura.getColor()) && hueco.isInside(figura)){
			return true;
		}
		return false;
	}
}
