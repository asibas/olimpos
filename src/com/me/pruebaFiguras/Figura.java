package com.me.pruebaFiguras;

import com.badlogic.gdx.graphics.Color;

/**
 * Clase para instanciar las diferentes piezas de distintas formas que se usaran en el juego
 * @author aibanez
 *
 */
public class Figura {
	private float posX, posY; //posicion de de la figura
	private int medida;//tamanio de la figura, que he pensado que deberia haber 3 o 4 tamanios diferentes
	private float grados;//para girar la figura
	private Color color;//color de la figura
	private boolean encajado;//indica si la pieza a sido encajada en su propio hueco
	/*
	 * para el tipo de figura he usado una clase de tipo enum, 
	 * que funcionan un poco como una serie de opciones estaticas.
	 * puedes hacer desde otro punto de codigo: Figura.CIRCULO, Figura.CUADRADO...
	 * de manera que basta con insertar en la lista de abajo las diferentes figuras
	 * , en lugar de hacer una clase nueva para cada una extendiendo de Figura.
	 * (Si no conoces esto del enum te recomiendo que le eches un ojo,
	 * creo que en clase no lo hemos dado, pero lo vi y me parece una buena idea.)
	 */
	private TipoFigura tipoFigura;
	/**
	 * los diferentes tipos de figura que podran usarse
	 * @author aibanez
	 *
	 */
	public enum TipoFigura {
		CIRCULO, CUADRADO, ROMBO, ESTRELLA, TRIANGULO
	}
	
	//constructores, el de mas arriba te pide toda la informacion, 
	//y se va sobrecargando pidiendo cada vez menos parametros
	//y asignando valores por defecto
	/**
	 * Constructor que recoge toda la informacion
	 * 
	 * @param posX posicion X de la figura en juego
	 * @param posY posicion Y de la figura en juego
	 * @param tipoFigura la forma de la figura
	 * @param color color de la figura
	 * @param medida tamanio de la figura
	 * @param grados inclinacion de la figura sobre su propio eje central
	 */
	public Figura(float posX, float posY, TipoFigura tipoFigura, Color color, int medida, float grados) {
		this.posX = posX;
		this.posY = posY;
		this.medida = medida;
		this.grados = grados;
		this.color = color;
		this.tipoFigura = tipoFigura;
		encajado = false;
	}
	
	/**
	 * constructor que deja los grados por defecto en 0
	 * @param posX posicion X de la figura en juego
	 * @param posY posicion Y de la figura en juego
	 * @param tipoFigura la forma de la figura
	 * @param color color de la figura
	 * @param medida tamanio de la figura
	 */
	public Figura(float posX, float posY, TipoFigura tipoFigura, Color color, int medida) {
		this.posX = posX;
		this.posY = posY;
		this.medida = medida;
		this.grados = 0;
		this.color = color;
		this.tipoFigura = tipoFigura;
		encajado = false;
	}
	
	/**
	 * constructor que deja los grados por defecto en 0 y la medida en 1
	 * @param posX posicion X de la figura en juego
	 * @param posY posicion Y de la figura en juego
	 * @param tipoFigura la forma de la figura
	 * @param color color de la figura
	 */
	public Figura(float posX, float posY, TipoFigura tipoFigura, Color color) {
		this.posX = posX;
		this.posY = posY;
		this.medida = 1;
		this.grados = 0;
		this.color = color;
		this.tipoFigura = tipoFigura;
		encajado = false;
	}
	
	/**
	 * constructor que deja los grados por defecto en 0, la medida en 1 y el color azul
	 * @param posX posicion X de la figura en juego
	 * @param posY posicion Y de la figura en juego
	 * @param tipoFigura la forma de la figura
	 */
	public Figura(float posX, float posY, TipoFigura tipoFigura) {
		this.posX = posX;
		this.posY = posY;
		this.medida = 1;
		this.grados = 0;
		this.color = Color.BLUE;
		this.tipoFigura = tipoFigura;
		encajado = false;
	}
	
	/**
	 * contructor que parte de otra figura, pensado principalmente para el contructor de HuecoFigura, pero que tambien puede servir para clonar figuras
	 * @param figura Figura de la que partir para crear la nueva Figura
	 */
	public Figura(Figura figura){
		posX = figura.getPosX();
		posY = figura.getPosY();
		medida = figura.getMedida();
		grados = figura.getGrados();
		color = figura.getColor();
		this.tipoFigura = figura.getTipoFigura();
		encajado = false;
	}
	//Fin constructores
	
	//getters y setters
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public int getMedida() {
		return medida;
	}
	public void setMedida(int medida) {
		this.medida = medida;
	}
	public float getGrados() {
		return grados;
	}
	public void setGrados(float grados) {
		this.grados = grados;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public TipoFigura getTipoFigura() {
		return tipoFigura;
	}
	public void setTipoFigura(TipoFigura tipoFigura) {
		this.tipoFigura = tipoFigura;
	}
	public boolean isEncajado() {
		return encajado;
	}
	public void setEncajado(boolean encajado) {
		this.encajado = encajado;
	}
	//Fin getters y setters

	

	//Este metodo se usara cuando se detecte que se a tocado la pantalla
	//comprobaremos si la figura a sido pulsada
	//x y y son el punto donde se a pulsado la pantalla
	/**
	 * Metodo que comprueba que el punto tocado coincida con la figura
	 * @param x posicion X de donde  se a pulsado
	 * @param y posicion y de donde se a pulsado
	 * @return devuelve true en caso de que se haya pulsado sobre la figura. Devuelve false en caso de que no se haya pulsado sobre la figura.
	 */
	public boolean isTouched(float x, float y){
		//Se me ocurrio hacer un caso para cada tipo de figura
		//Pero igual desacemos esto y tratamos todos los casos como cuadrado
		//Para dar un margen de error en la pulsacion
		switch(this.tipoFigura){
			case CUADRADO: 
				System.out.println("Es cuadrado");
				System.out.println("zona tocada: x = " + x + "; y = " + y);
				System.out.println("Lados del cuadrado:");
				System.out.println("Superior: " + (this.posY + (64* this.medida)));
				System.out.println("derecha: " + this.posX + ((64* this.medida)));
				System.out.println("izquierda: " + this.posX);
				System.out.println("Inferior: " + this.posY);
				if(x < (this.posX + (64 * this.medida)) &&
					x > this.posX &&
					y < (this.posY + (64 * this.medida)) &&
					y > this.posY)
					return true;
				else
					return false;
			case CIRCULO: 
				return false;
			case ROMBO:
				return false;
			case ESTRELLA: 
				return false;
			default:
				return false;
		}
	}
}
