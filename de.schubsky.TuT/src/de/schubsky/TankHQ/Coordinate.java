package de.schubsky.TankHQ;

/**
 * 
 * @author Markus Schubsky
 * @version 1.0
 *
 */

public class Coordinate {
	
	private double x;
	private double y;
	
	public Coordinate(double X, double Y) {
		this.x = X;
		this.y = Y;
	}
	
	public double getX(){
		return x;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
