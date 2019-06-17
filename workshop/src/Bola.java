/**
 * Representa a una bola. La bola es circular, tiene una posici�n y radio y se mueve rectil�neamente con una velocidad de magnitud constante. Todas las coordenadas y dimensiones est�n entre 0 y 1.
 * @author Miguel Garc�a
 * @version 1.0
 */
public class Bola {
	private double radio;
	private Vector velocidad;
	private Vector posicion;
	private Vector posicionAnterior;
	
	/**
	 * Crea una bola.
	 * @param radio Radio de la bola.
	 * @param posicionInicial Posici�n inicial de la bola.
	 * @param velocidadInicial Velocidad inicial de la bola.
	 */
	public Bola(double radio, Vector posicionInicial, Vector velocidadInicial){
		this.radio=radio;
		this.velocidad=velocidadInicial;
		this.posicion=posicionInicial;
		
	}
	/**
	 * Devuelve la velocidad actual de la bola.
	 * @return Velocidad actual de la bola.
	 */
	public Vector getVelocidad(){
		return velocidad;
	}
	/**
	 * Devuelve la posici�n actual de la bola.
	 * @return Posici�n actual de la bola.
	 */
	public Vector getPosicion(){
		return posicion;
	}
	/**
	 * Devuelve el radio de la bola.
	 * @return Radio de la bola.
	 */
	public double getRadio(){
		return radio;
	}
	/**
	 * Actualiza la posici�n de la bola aplicando su velocidad durante el intervalo de tiempo especificado.
	 * @param dt Intervalo de tiempo.
	 */
	public void mover(double dt){
		posicionAnterior=posicion;	//Se almacenar� la posicion anterior de la bola para poder borrarla en antes del siguiente fotograma.
		posicion = posicion.plus(velocidad.times(dt));
	}
	/**
	 * Cambia la trayectoria de la bola girando su vector velocidad el �ngulo en radianes que se especifica.
	 * @param anguloGiro �ngulo en radianes que hay que girar el vector velocidad de la bola.
	 */
	public void cambiarTrayectoria(double anguloGiro){
		velocidad = velocidad.rotate(anguloGiro);
	}
	/**
	 * Dibuja la bola en la ventana. La bola se dibuja como un c�rculo relleno del color que desee el alumno. Este m�todo tiene en cuenta que la bola se mueve, por lo que primero la borra de su posici�n anterior y luego la dibuja en su posici�n actual.
	 */
	public void dibujar(){
		StdDraw.setPenRadius(0.02); 
		StdDraw.setPenColor(StdDraw.WHITE);
		
		StdDraw.filledCircle(posicionAnterior.cartesian(0), posicionAnterior.cartesian(1), radio + 0.0015); 
		
		StdDraw.setPenRadius(.02); 
		StdDraw.setPenColor(StdDraw.BLUE); 
		
		StdDraw.filledCircle(posicion.cartesian(0), posicion.cartesian(1), radio); 
	
	}
}
