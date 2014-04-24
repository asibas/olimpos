package com.me.pruebaFiguras.motor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.me.pruebaFiguras.Figura;
import com.me.pruebaFiguras.HuecoFigura;

public class Motor {
	private List<Figura> figuras;
	private List<HuecoFigura> huecos;
	private Figura figuraSeleccionada;
	
	public Motor(){
		figuras = new ArrayList<>();
		huecos = new ArrayList<>();
	}
	
	public void agregarFigura(Figura figura){
		figuras.add(figura);
	}
	
	public void agregarHueco(HuecoFigura hueco){
		huecos.add(hueco);
	}
	
	public Figura figuraTocada(float posX, float posY){
		for (Figura figura: figuras){
			if (figura.isTouched(posX, posY)){
				return figura;
			}
		}
		return null;
	}
	
	/**
	 * Esta funcion comprobara todos los cambios que se haran antes de renderizar.
	 */
	public void actualizarEstado(){
		//Si la pantalla es tocada
		if (Gdx.input.isTouched()){
			//Si no hay una figura actualmente seleccionada
			if (figuraSeleccionada == null){
				//Guardamos la nueva figura seleccionada. null en caso de que no se seleccione ninguna
				figuraSeleccionada = figuraTocada(Gdx.input.getX(), Gdx.input.getY());
			}
			else{//En caso de que la figura ya este seleccionada
				//Le cambiamos la posicion
				figuraSeleccionada.setPosX(Gdx.input.getX());
				//Elevamos la figura para evitar taparla con el dedo
				figuraSeleccionada.setPosY(Gdx.input.getY() + 10);
			}
		}//En caso de que la pantalla no sea tocada
		else {
			//Se deja de tener seleccionada la figura pasando el valor a null
			figuraSeleccionada = null;
		}
		/* Es decir: 
		 * desde que se toca una figura se considerara que esta seleccionada 
		 * asta que se deje de tocar la pantalla.
		 */
	}
}
