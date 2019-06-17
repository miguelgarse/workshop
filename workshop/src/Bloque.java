/**
 * Esta clase representa a todos los elementos que forman parte del campo de juego y contra los que puede impactar una bola. En caso de impacto, la bola rebotar� cambiando su direcci�n y sentido pero manteniendo constante el m�dulo de su velocidad.
 * @author Miguel Garc�a
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
	 * Crea un bloque en la posici�n y de dimensiones que se indican en los par�metros.
	 * @param posicion Indica la posici�n en el plano.
	 * @param dimensiones Indica las dimensiones del bloque.
	 */
	public Bloque(Vector posicion, Vector dimensiones){
		this.posicion=posicion;
		this.dimensiones=dimensiones;
	}
	/**
	 * Calcula si una bola ha impactado contra este bloque.
	 * En caso afirmativo, devuelve el �ngulo que habr� que sumar al vector velocidad de la bola para que rebote adecuadamente.
	 * @param bola Bola contra la que se quiere comprobar el impacto.
	 * @return Si hay impacto, devuelve un double con el �ngulo (en radianes) que habr� que sumar al vector velocidad de la bola para que rebote adecuadamente. Si no hay impacto, devuelve el valor de la constante NO_IMPACTO.
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
	 * Devuelve la posici�n del bloque.
	 * @return Vector con la posici�n del bloque
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
	 * Devuelve el n�mero de impactos que le quedan a un bloque para su eliminaci�n. Este m�todo deber� ser sobrescrito en todas las clases que extiendan a Bloque, en aquellas que representen bloques indestructibles devolver� Bloque.INDESTRUCTIBLE y en las que representes bloques destructibles, devolver� el n�mero de impactos restantes del bloque para su destrucci�n.
	 * @return Valor entero con el n�mero de impactos restantes del bloque.
	 */
	public int getNumImpactosRestantes(){
		ImpactosRestantes=ImpactosRestantes-1;
		return ImpactosRestantes;
	}
	/**
	 * Dibuja un bloque el el campo de juego
	 * Este m�todo deber� ser implementado (sobreescrito) en todos las clases que extiendan a Bloque. Dibuja sobre el campo de juego la forma geom�trica del bloque.
	 */
	public abstract void dibujar();
}
