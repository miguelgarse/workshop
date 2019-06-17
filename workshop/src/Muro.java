/**
 * 
 * Los Muros representan Bloques indestructibles que crean el contorno del campo de juego La posición del muro viene determinada por el vector posición (coordenadas de su centro). Su dimensión viene determinado por el vector dimensiones (semianchura y semialtura). Los muros son siempre bloques paralelos a los ejes de coordenadas. Todas las coordenadas y dimensiones están entre 0 y 1.
 * @author Miguel García
 * @version 1.0
 */
public class Muro extends Bloque {
	/**
	 * Crea un muro con la posición y dimensiónes especificadas en los parámetros.
	 * @param posicion Vector con las coordenadas del centro del muro.
	 * @param dimensiones Vector con las dimensiones del muro (semianchura, semialtura).
	 */
	public Muro(Vector posicion, Vector dimensiones){
		super(posicion, dimensiones);
	}
	/**
	 * Método sobreescrito de la clase Bloque. Indica que el Muro es un bloque indestructible.
	 * @return Valor de la constante Bloque.INDESTRUCTIBLE
	 */
	public int getNumImpactosRestantes(){
		return INDESTRUCTIBLE;
	}
	/**
	 * Método sobreescrito de la clase Bloque. Dibuja el muro en el campo de juego. El muro se dibuja como un rectángulo relleno del color que desee el alumno.
	 */
	public void dibujar(){

		StdDraw.setPenRadius(.02); 
		StdDraw.setPenColor(StdDraw.BLACK); 
		
		StdDraw.filledRectangle(getPosicion().cartesian(0), getPosicion().cartesian(1), getDimensiones().cartesian(0), getDimensiones().cartesian(1)); 
		
	}
}
