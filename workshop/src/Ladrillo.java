/**
 * Representa a un Bloque destructible con las siguientes características:
 * 		- La posición del ladrillo viene determinada por las coordenadas de su centro. 
 * 		- Su forma es rectangular y puede ubicarse en cualquier área del campo de juego. 
 * 		- Son destruidos cuando reciben un número determinado de impactos. 
 * 		- Dependiendo del número de impactos restantes para su destrucción el ladrillo tendrá un determinado color: Rojo 3, Amarillo 2 y Verde 1.
 * @author Miguel García
 * @version 1.0
 */
public class Ladrillo extends Bloque {
	/**
	 * Construye un ladrillo dada su posición y sus dimensiones. Le asigna el valor 3 al número de impactos restantes para su destrucción.
	 * @param posicion Posición inicial del ladrillo.
	 * @param dimensiones Dimensiones iniciales del ladrillo.
	 */
	public Ladrillo(Vector posicion, Vector dimensiones){
		super(posicion, dimensiones);
		
	}
	/**
	 * Constructor especializado que crea un ladrillo dada su posición inicial, dimensiones y número de impactos.
	 * @param posicion Posicion inicial del ladrillo.
	 * @param dimensiones Dimensiones iniciales del ladrillo.
	 * @param numImpactos de impactos iniciales del ladrillo.
	 */ 
	public Ladrillo(Vector posicion, Vector dimensiones, int numImpactos){
		this(posicion, dimensiones);
		ImpactosRestantes = numImpactos;
	}
	/**
	 * Metodo sobreescrito de la clase Bloque. Dibuja un ladrillo del color adecuado en función del numero de impactos restantes para su destrucción.
	 */
	public void dibujar(){
		
		
		if(ImpactosRestantes == 3){
			StdDraw.setPenRadius(0.001); 
			StdDraw.setPenColor(StdDraw.BLACK); 
			StdDraw.rectangle(getPosicion().cartesian(0), getPosicion().cartesian(1), getDimensiones().cartesian(0), getDimensiones().cartesian(1)); 
		
			StdDraw.setPenRadius(.02); 
			StdDraw.setPenColor(StdDraw.RED); 
			StdDraw.filledRectangle(getPosicion().cartesian(0), getPosicion().cartesian(1), getDimensiones().cartesian(0), getDimensiones().cartesian(1)); 
		}
		if(ImpactosRestantes == 2){
			StdDraw.setPenRadius(0.001); 
			StdDraw.setPenColor(StdDraw.BLACK); 
			StdDraw.rectangle(getPosicion().cartesian(0), getPosicion().cartesian(1), getDimensiones().cartesian(0), getDimensiones().cartesian(1)); 
		
			StdDraw.setPenRadius(.02); 
			StdDraw.setPenColor(StdDraw.YELLOW); 
			StdDraw.filledRectangle(getPosicion().cartesian(0), getPosicion().cartesian(1),  getDimensiones().cartesian(0), getDimensiones().cartesian(1)); 
		}
		if(ImpactosRestantes == 1){
			StdDraw.setPenRadius(0.001); 
			StdDraw.setPenColor(StdDraw.BLACK); 
			StdDraw.rectangle(getPosicion().cartesian(0), getPosicion().cartesian(1), getDimensiones().cartesian(0), getDimensiones().cartesian(1)); 
		
			StdDraw.setPenRadius(.02); 
			StdDraw.setPenColor(StdDraw.GREEN); 
			StdDraw.filledRectangle(getPosicion().cartesian(0), getPosicion().cartesian(1),  getDimensiones().cartesian(0), getDimensiones().cartesian(1)); 
		}
		if(ImpactosRestantes == 0){
			StdDraw.setPenRadius(0.001); 
			StdDraw.setPenColor(StdDraw.BLACK); 
			StdDraw.rectangle(getPosicion().cartesian(0), getPosicion().cartesian(1), getDimensiones().cartesian(0), getDimensiones().cartesian(1)); 
		
			StdDraw.setPenRadius(0.02); 
			StdDraw.setPenColor(StdDraw.WHITE); 
			StdDraw.filledRectangle(getPosicion().cartesian(0), getPosicion().cartesian(1),  getDimensiones().cartesian(0) + 0.01, getDimensiones().cartesian(1) + 0.01); 
		}
		
	}
	/**
	 * Método sobreescrito de la clase Bloque, permite tener en cuenta los impactos que va teniendo el ladrillo y así decrementar el contador de impactos.
	 * @param bola Bola contra la que se quiere comprobar el impacto.
	 * @return El ángulo de desvío a aplicar al ladrillo.
	 */
	public double calcularImpacto(Bola bola){
		
		return super.calcularImpacto(bola);
	}
}
