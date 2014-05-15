package com.me.pruebaFiguras;

import com.badlogic.gdx.graphics.Color;

/**
 * Clase que representa los huecos donde hay que arrastrar las figuras
 * 
 * @author aibanez
 *
 */
public class HuecoFigura extends Figura {
	private int margen = 2;//margen entre la figura y su hueco
	/**
	 * Constructor para la clase HuecoFigura
	 * @param posX posicion X de la clase
	 * @param posY posicion Y de la clase
	 * @param figura Figura de la que se parte para generar el hueco
	 */
	public HuecoFigura(float posX, float posY, Figura figura) {
		super(figura);
		//this.setColor(Color.GRAY);
		this.setPosX(posX);
		this.setPosY(posY);
		//sumamos 2 para que haya un margen entre la figura y su hueco
		this.setMedida(this.getMedida() + margen);
	}
	
	/**
	 * Metodo que comprueba si la figura a entrado en su correspondiente metodo
	 * 
	 * @param figura la figura que debe entrar en el hueco
	 * @return true si se encuentra dentro del hueco, false si aun no se encuentra dentro del hueco
	 */
	public boolean isInside(Figura figura){
		if(	figura.getTipoFigura().equals(this.getTipoFigura()) &&
			figura.getPosX() >= this.getPosX() - margen &&
			figura.getPosX() <= this.getPosX() + margen &&
			figura.getPosY() >= this.getPosY() - margen &&
			figura.getPosY() <= this.getPosY() + margen){
			return true;
		}else{
			return false;
		}
	}

}
