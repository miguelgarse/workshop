/**
 * Representa un Bloque indestructible que tiene la caracter�stica de poder desplazarse a derecha e izquierda, en la base del campo de juego, dependiendo de la pulsaci�n de las teclas ',' y '.'
 * @author Miguel Garc�a
 * @version 1.0
 */
public class Pala extends Bloque {
	/**
	 * Crea una pala en la posici�n y de las dimensiones especificadas en los par�metros.
	 * @param posicionInicial Vector con las coordenadas del centro de la pala.
	 * @param dimensiones Vector con las dimensiones de al pala (semianchura, semialtura).
	 */
	public Pala(Vector posicionInicial, Vector dimensiones){
		super(posicionInicial, dimensiones);
	}
	/**
	 * Realiza el desplazamiento de la pala a una nueva posici�n en el campo de juego.
	 * @param desplazamiento Vector que indica el desplazamiento sobre la posici�n actual.
	 */
	public void mover(Vector desplazamiento){
		posicionAnterior=posicion;
		posicion = posicion.plus(desplazamiento);
	}
	/**
	 * M�todo sobreescrito de la clase Bloque.
	 * Indica que la Pala es un Bloque indestructible
	 * @return la constante Bloque.INDESTRUCTIBLE
	 */
	public int getNumImpactosRestantes(){
		return INDESTRUCTIBLE;
	}
	/**
	 * M�todo sobreescrito de la clase Bloque. Dibuja la pala en el area de juego.
	 */
	public void dibujar(){
		StdDraw.setPenRadius(.02); 
		StdDraw.setPenColor(StdDraw.WHITE); 
		StdDraw.filledRectangle(posicionAnterior.cartesian(0), posicionAnterior.cartesian(1),  getDimensiones().cartesian(0) + 0.015, getDimensiones().cartesian(1) + 0.015);
		
		
		StdDraw.setPenRadius(.02); 
		StdDraw.setPenColor(StdDraw.PINK); 
		StdDraw.filledRectangle(getPosicion().cartesian(0), getPosicion().cartesian(1),  getDimensiones().cartesian(0), getDimensiones().cartesian(1));
		
		StdDraw.setPenRadius(0.01); 
		StdDraw.setPenColor(StdDraw.BLACK); 
		StdDraw.rectangle(getPosicion().cartesian(0), getPosicion().cartesian(1),  getDimensiones().cartesian(0), getDimensiones().cartesian(1));
	}
}
