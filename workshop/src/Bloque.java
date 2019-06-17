/**
 * Esta clase representa a todos los elementos que forman parte del campo de juego y contra los que puede impactar una bola. En caso de impacto, la bola rebotará cambiando su dirección y sentido pero manteniendo constante el módulo de su velocidad.
 * @author Miguel García
 * @version 1.0
 */
public abstract class Bloque {
	public static final double NO_IMPACTO = 999;
	public static final int INDESTRUCTIBLE = -1;
	
	protected Vector posicion;
	protected Vector dimensiones;
	protected Vector posicionAnterior;
	protected int ImpactosRestantes;
	/**
	 * Crea un bloque en la posición y de dimensiones que se indican en los parámetros.
	 * @param posicion Indica la posición en el plano.
	 * @param dimensiones Indica las dimensiones del bloque.
	 */
	public Bloque(Vector posicion, Vector dimensiones){
		this.posicion=posicion;
		this.dimensiones=dimensiones;
	}
	/**
	 * Calcula si una bola ha impactado contra este bloque.
	 * En caso afirmativo, devuelve el ángulo que habrá que sumar al vector velocidad de la bola para que rebote adecuadamente.
	 * @param bola Bola contra la que se quiere comprobar el impacto.
	 * @return Si hay impacto, devuelve un double con el ángulo (en radianes) que habrá que sumar al vector velocidad de la bola para que rebote adecuadamente. Si no hay impacto, devuelve el valor de la constante NO_IMPACTO.
	 */
	public double calcularImpacto(Bola bola){
		double anguloIncidencia = bola.getVelocidad().angle(); 
		double anguloBola = bola.getPosicion().minus(posicion).angle();
		double anguloEsquinaMuro = dimensiones.angle();
		double anguloReflejado = NO_IMPACTO;
		double xb = bola.getPosicion().cartesian(0);
		double yb = bola.getPosicion().cartesian(1);
		double xm = posicion.cartesian(0);
		double ym = posicion.cartesian(1);
		double xd = dimensiones.cartesian(0);
		double yd = dimensiones.cartesian(1);
		double r = bola.getRadio();
		// Impacto por arriba
		if (anguloBola >= anguloEsquinaMuro && anguloBola < Math.PI - anguloEsquinaMuro
			&& bola.getVelocidad().cartesian(1) < 0
			&& yb - r <= ym + yd
			&& xb - r/2 >= xm - xd
			&& xb + r/2 <= xm + xd) {
			anguloReflejado = (2 * Math.PI - anguloIncidencia) * 2;
		}
		// Impacto por abajo
		else if (anguloBola >= Math.PI + anguloEsquinaMuro && anguloBola < 2 * Math.PI - anguloEsquinaMuro
				&& bola.getVelocidad().cartesian(1) > 0
				&& yb + r >= ym - yd
				&& xb - r/2 >= xm - xd
				&& xb + r/2 <= xm + xd) {
			anguloReflejado = -2 * anguloIncidencia;
		}
		// Impacto por la izquierda
		else if (anguloBola >= Math.PI - anguloEsquinaMuro && anguloBola < Math.PI + anguloEsquinaMuro
				&& bola.getVelocidad().cartesian(0) > 0
				&& xb + r >= xm - xd
				&& yb - r/2 >= ym - yd
				&& yb + r/2 <= ym + yd) {
			anguloReflejado = -(anguloIncidencia - Math.PI / 2) * 2;
		}
		// Impacto por la derecha
		else if ((anguloBola >= 2 * Math.PI - anguloEsquinaMuro || anguloBola < anguloEsquinaMuro)
				&& bola.getVelocidad().cartesian(0) < 0
				&& xb - r <= xm + xd
				&& yb - r/2 >= ym - yd
				&& yb + r/2 <= ym + yd) {
			anguloReflejado = (Math.PI / 2 - anguloIncidencia) * 2;
		}
		return anguloReflejado;
	}
	/**
	 * Devuelve la posición del bloque.
	 * @return Vector con la posición del bloque
	 */
	public Vector getPosicion(){
		return posicion;
	}
	/**
	 * Devuelve las dimensiones del bloque.
	 * @return Vector con las dimensiones del bloque.
	 */
	public Vector getDimensiones(){
		return dimensiones;
	}
	/**
	 * Devuelve el número de impactos que le quedan a un bloque para su eliminación. Este método deberá ser sobrescrito en todas las clases que extiendan a Bloque, en aquellas que representen bloques indestructibles devolver´ Bloque.INDESTRUCTIBLE y en las que representes bloques destructibles, devolverá el número de impactos restantes del bloque para su destrucción.
	 * @return Valor entero con el número de impactos restantes del bloque.
	 */
	public int getNumImpactosRestantes(){
		ImpactosRestantes=ImpactosRestantes-1;
		return ImpactosRestantes;
	}
	/**
	 * Dibuja un bloque el el campo de juego
	 * Este método deberá ser implementado (sobreescrito) en todos las clases que extiendan a Bloque. Dibuja sobre el campo de juego la forma geométrica del bloque.
	 */
	public abstract void dibujar();
}
