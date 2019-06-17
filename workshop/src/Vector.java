/**
 *  Compilation:  javac Vector.java
 *  Execution:    java Vector
 *
 *  Implementation of a vector of real numbers.
 *
 *  This class is implemented to be immutable: once the client program
 *  initialize a Vector, it cannot change any of its fields
 *  (N or data[i]) either directly or indirectly. Immutability is a
 *  very desirable feature of a data type.
 *
 *
 *  % java Vector
 *  x        =  (1.0, 2.0, 3.0, 4.0)
 *  y        =  (5.0, 2.0, 4.0, 1.0)
 *  x + y    =  (6.0, 4.0, 7.0, 5.0)
 *  10x      =  (10.0, 20.0, 30.0, 40.0)
 *  |x|      =  5.477225575051661
 *  <x, y>   =  25.0
 *  |x - y|  =  5.0990195135927845
 *
 *  Note that java.util.Vector is an unrelated Java library class.
 * 
 */
public class Vector {

	private final int N; // length of the vector
	private double[] data; // array of vector's components

	/**
	 * create the zero vector of length N
	 * 
	 * @param N
	 *            Vector length.
	 */
	public Vector(int N) {
		this.N = N;
		this.data = new double[N];
	}

	/**
	 * create a vector from either an array or a vararg list this constructor uses
	 * Java's vararg syntax to support a constructor that takes a variable number of
	 * arguments, such as Vector x = new Vector(1.0, 2.0, 3.0, 4.0); Vector y = new
	 * Vector(5.0, 2.0, 4.0, 1.0);
	 * 
	 * @param data
	 *            Initial value of the vector.
	 */
	public Vector(double... data) {
		N = data.length;

		// defensive copy so that client can't alter our copy of data[]
		this.data = new double[N];
		for (int i = 0; i < N; i++)
			this.data[i] = data[i];
	}

	/**
	 * return the length of the vector
	 * 
	 * @return Length of the vector.
	 */
	public int length() {
		return N;
	}

	/**
	 * return the inner product of this Vector a and b
	 * 
	 * @param that
	 *            The other vector
	 * @return A new vector which is the inner product of this vector and the other
	 *         vector
	 * @throws RuntimeException
	 *             Attempt to calculate the inner product of two vectors of
	 *             different lengths
	 */
	public double dot(Vector that) {
		if (this.N != that.N)
			throw new RuntimeException("Dimensions don't agree");
		double sum = 0.0;
		for (int i = 0; i < N; i++)
			sum = sum + (this.data[i] * that.data[i]);
		return sum;
	}

	/**
	 * return the Euclidean norm of this Vector
	 * 
	 * @return Euclidean norm of this Vector
	 */
	public double magnitude() {
		return Math.sqrt(this.dot(this));
	}

	/**
	 * return the Euclidean distance between this and that
	 * 
	 * @param that
	 *            The other vector
	 * @return Euclidean distance between this vector and the other vector
	 * @throws RuntimeException
	 *             Attempt to calculate the distance between two vectors of
	 *             different lengths
	 */
	public double distanceTo(Vector that) {
		if (this.N != that.N)
			throw new RuntimeException("Dimensions don't agree");
		return this.minus(that).magnitude();
	}

	/**
	 * return this + that
	 * 
	 * @param that
	 *            The other vector
	 * @return A new vector which is the sum of this vector and the other vector
	 * @throws RuntimeException
	 *             Attempt to add two vectors of different lengths
	 */
	public Vector plus(Vector that) {
		if (this.N != that.N)
			throw new RuntimeException("Dimensions don't agree");
		Vector c = new Vector(N);
		for (int i = 0; i < N; i++)
			c.data[i] = this.data[i] + that.data[i];
		return c;
	}

	/**
	 * return this - that
	 * 
	 * @param that
	 *            The other vector
	 * @return A new vector which is the difference between this vector and the
	 *         other vector
	 * @throws RuntimeException
	 *             Attempt to substract two vectors of different lengths
	 */
	public Vector minus(Vector that) {
		if (this.N != that.N)
			throw new RuntimeException("Dimensions don't agree");
		Vector c = new Vector(N);
		for (int i = 0; i < N; i++)
			c.data[i] = this.data[i] - that.data[i];
		return c;
	}

	/**
	 * return the corresponding coordinate
	 * 
	 * @param i
	 *            Coordinate index (0 is the first coordinate)
	 * @return Value of the ith coordinate (0 is the first coordinate)
	 */
	public double cartesian(int i) {
		return data[i];
	}

	/**
	 * create and return a new object whose value is (this * factor)
	 * 
	 * @param factor
	 *            Factor
	 * @return A new vector whose value is (this * factor)
	 */
	public Vector times(double factor) {
		Vector c = new Vector(N);
		for (int i = 0; i < N; i++)
			c.data[i] = factor * data[i];
		return c;
	}

	/**
	 * return the corresponding unit vector
	 * 
	 * @return A new vector which is the corresponding unit vector
	 * @throws RuntimeException
	 *             Attempt to calculate the direction of a zero vector
	 */
	public Vector direction() {
		if (this.magnitude() == 0.0)
			throw new RuntimeException("Zero-vector has no direction");
		return this.times(1.0 / this.magnitude());
	}

	/**
	 * returns the angle in radians of this 2D vector in polar coordinates The (1,0)
	 * vector has an angle of 0 radians, and the angle increases counterclockwise up
	 * to 2*PI radians.
	 * 
	 * @return Angle in radians of this 2D vector in polar coordinates
	 * @throws RuntimeException
	 *             Attempt to calculate the angle of a vector which is not
	 *             two-dimensional
	 */
	public double angle() {
		if (this.N != 2)
			throw new RuntimeException("Polar angle is defined only for 2D vectors");
		if (this.cartesian(0) < 0)
			return Math.PI + Math.atan(this.cartesian(1) / this.cartesian(0));
		else if (this.cartesian(1) >= 0)
			return Math.atan(this.cartesian(1) / this.cartesian(0));
		else
			return 2 * Math.PI + Math.atan(this.cartesian(1) / this.cartesian(0));
	}

	/**
	 * rotates a 2D vector by the specified angle in radians.
	 * 
	 * @param angle
	 *            Rotation angle. Positive values rotate counterclockwise
	 * @return A new vector which is the rotated vector
	 * @throws RuntimeException
	 *             Attempt to rotate a vector which is not two-dimensional
	 */
	public Vector rotate(double angle) {
		if (this.N != 2)
			throw new RuntimeException("Rotation is defined only for 2D vectors");
		double a = this.angle();
		double r = this.magnitude();
		a += angle;
		return new Vector(Math.cos(a) * r, Math.sin(a) * r);
	}

	/**
	 * return a string representation of the vector
	 * 
	 * @return a string representation of the vector
	 */
	public String toString() {
		String s = "(";
		for (int i = 0; i < N; i++) {
			s += data[i];
			if (i < N - 1)
				s += ", ";
		}
		return s + ")";
	}

	/**
	 * test client
	 * 
	 * @param args
	 *            test arguments (not used)
	 */
	public static void main(String[] args) {
		double[] xdata = { 1.0, 2.0, 3.0, 4.0 };
		double[] ydata = { 5.0, 2.0, 4.0, 1.0 };

		Vector x = new Vector(xdata);
		Vector y = new Vector(ydata);

		System.out.println("x        =  " + x);
		System.out.println("y        =  " + y);
		System.out.println("x + y    =  " + x.plus(y));
		System.out.println("10x      =  " + x.times(10.0));
		System.out.println("|x|      =  " + x.magnitude());
		System.out.println("<x, y>   =  " + x.dot(y));
		System.out.println("|x - y|  =  " + x.minus(y).magnitude());
	}
}
