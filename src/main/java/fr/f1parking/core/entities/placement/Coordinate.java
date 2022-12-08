package fr.f1parking.core.entities.placement;

import fr.f1parking.utils.Tuple;

/**
 * Used to store an X and Y value representing a coordinate
 * @author 278deco
 */
public class Coordinate {

	private int x, y;
	
	/**
	 * Create a new coordinate (store the x and y in one object)
	 * @param x - the x value
	 * @param y - the y value
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Create a new coordinate
	 * @param tuple - a tuple of integer (Value A = X, Value B = Y)
	 */
	public Coordinate(Tuple<Integer, Integer> tuple) {
		this.x = tuple.getValueA();
		this.y = tuple.getValueB();
	}

	/**
	 * Add a number to the coordinate's x and y values
	 * @param a - the value to be added
	 * @return a new coordinate with the calculated values
	 */
	public Coordinate add(int a) {
		return new Coordinate(this.x+a, this.y+a);
	}
	
	/**
	 * Add an other coordinate to the coordinate's x and y values
	 * @param other - The coordinate to be added
	 * @return a new coordinate with the calculated values
	 */
	public Coordinate add(Coordinate other) {
		return new Coordinate(this.x+other.x, this.y+other.y);
	}
	
	/**
	 * Subtract a number to the coordinate's x and y values
	 * @param a - the value to be subtracted
	 * @return a new coordinate with the calculated values
	 */
	public Coordinate sub(int a) {
		return new Coordinate(this.x-a, this.y-a);
	}
	
	/**
	 * Subtract an other coordinate to the coordinate's x and y values
	 * @param other - The coordinate to be subtracted
	 * @return a new coordinate with the calculated values
	 */
	public Coordinate sub(Coordinate other) {
		return new Coordinate(this.x-other.x, this.y-other.y);
	}

	/**
	 * Multiply a number to the coordinate's x and y values
	 * @param a - the value to be multiplied
	 * @return a new coordinate with the calculated values
	 */
	public Coordinate times(int a) {
		return new Coordinate(this.x*a, this.y*a);
	}
	
	/**
	 * Multiply an other coordinate to the coordinate's x and y values
	 * @param other - The coordinate to be multiplied
	 * @return a new coordinate with the calculated values
	 */
	public Coordinate times(Coordinate other) {
		return new Coordinate(this.x*other.x, this.y*other.y);
	}
	
	/**
	 * Divide a number to the coordinate's x and y values
	 * @param a - the value to be divided
	 * @return a new coordinate with the calculated values
	 */
	public Coordinate div(int a) {
		return new Coordinate(this.x/a, this.y/a);
	}
	
	/**
	 * Divide an other coordinate to the coordinate's x and y values
	 * @param other - The coordinate to be divided
	 * @return a new coordinate with the calculated values
	 */
	public Coordinate div(Coordinate other) {
		return new Coordinate(this.x/other.x, this.y/other.y);
	}
	
	/**
	 * Get the absolute value of the coordinates (replace every negative number by their positive counterparts)
	 * @return a new coordinate with the calculated values
	 */
	public Coordinate abs() {
		return new Coordinate((this.x < 0 ? -this.x : this.x), (this.y < 0 ? -this.y : this.y));
	}
	
	/**
	 * Get the X value stored in the object
	 * @return the x value
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Get the Y value stored in the object
	 * @return the y value
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Compare the coordinate with two other objects and determine
	 * if the coordinate is contained between the {@code lower} and {@code upper}
	 * @param lower - the lower coordinate (the tested coordinate shouldn't be lower than this one)
	 * @param upper - the upper coordinate (the tested coordinate shouldn't be greater than this one)
	 * @return if the coordinate is within the interval
	 */
	public boolean withinInterval(Coordinate lower, Coordinate upper) {
		return lower.getX() <= getX() && lower.getY() <= getY() && upper.getX() >= getX() && upper.getY() >= getY();
	}
	
	/**
	 * Compare the coordinate with an other object and determine
	 * if the coordinate is greater than {@code other}
	 * @param other - the other coordinate
	 * @return if the coordinate is greater than the argument
	 */
	public boolean greaterThan(Coordinate other) {
		return other.getX() <= getX() && other.getY() <= getY();
	}
	
	/**
	 * Compare the coordinate with an other object and determine
	 * if the coordinate is lower than {@code other}
	 * @param other - the other coordinate
	 * @return if the coordinate is lower than the argument
	 */
	public boolean lowerThan(Coordinate other) {
		return other.getX() >= getX() && other.getY() >= getY();
	}
 	
	/**
	 * Compare the coordinate with an other object and determine
	 * if the coordinate is equal to {@code other}
	 * @param other - the other coordinate
	 * @return if the coordinate is equal to the argument
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Coordinate && isEqual((Coordinate)obj);
	}
	
	private boolean isEqual(Coordinate coord) {
		return coord.getX() == this.x && coord.getY() == this.y;
	}
	
	/**
	 * Return a string representation of the Coordinate
	 */
	@Override
	public String toString() {
		return "( "+ x + "," + y +" )";
	}
	
}
