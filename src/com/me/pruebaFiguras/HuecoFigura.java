package com.me.pruebaFiguras;

import com.badlogic.gdx.graphics.Color;

/**
 * Clase que representa los huecos donde hay que arrastrar las figuras
 * 
 * @author aibanez
 *
 */
public class HuecoFigura extends Figura {

	/**
	 * Constructor para la clase HuecoFigura
	 * @param posX posicion X de la clase
	 * @param posY posicion Y de la clase
	 * @param figura Figura de la que se parte para generar el hueco
	 */
	public HuecoFigura(float posX, float posY, Figura figura) {
		super(figura);
		this.setColor(Color.GRAY);
		this.setPosX(posX);
		this.setPosY(posY);
	}
	
	/**
	 * Metodo que comprueba si la figura a entrado en su correspondiente metodo
	 * 
	 * @param figura la figura que debe entrar en el hueco
	 * @return true si se encuentra dentro del hueco, false si aun no se encuentra dentro del hueco
	 */
	public boolean isInside(Figura figura){
		if(figura.getPosX() >= this.getPosX() &&
			(figura.getPosX() + (64 * figura.getMedida())) < (this.getPosX() + (68 * this.getMedida())) &&
			figura.getPosY() > this.getPosY() &&
			(figura.getPosY() + (64 * figura.getMedida())) < (this.getPosY() + (68 * this.getMedida()))){
			return true;
		}else{
			return false;
		}
	}

}
