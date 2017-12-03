package de.schubsky.TankHQ;

/**
 * 
 * @author Markus Schubsky
 * @version 1.0
 *
 */

public abstract class GameObject {
	
	private Coordinate objPosition;
	private double width;
	private double height;
	private double movingAngle;
	private double movingDistance;
	
	public GameObject(Coordinate objPosition, double width, double height) {
		this.objPosition = objPosition;
		this.width = width;
		this.height = height;
		movingAngle = 0;
		movingDistance = 0;
	}
	
    public Coordinate getObjPosition() {
        return objPosition;
    }

    public void setObjectPosition(Coordinate objPosition) {
        this.objPosition = objPosition;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getMovingAngle() {
        return movingAngle;
    }

    public void setMovingAngle(double movingAngle) {
        this.movingAngle = movingAngle;
    }

    public double getMovingDistance() {
        return movingDistance;
    }

    public void setMovingDistance(double movingDistance) {
        this.movingDistance = movingDistance;
    }
    
    public boolean isLeftOf(GameObject that) {
    	return this.getObjPosition().getX() + this.getWidth() < that.getObjPosition().getX();
    }
    
    public boolean isAbove(GameObject that) {
    	return this.getObjPosition().getY() + this.getHeight() < that.getObjPosition().getY();
    }
    
    public boolean touches(GameObject that) {
    	if(this.isLeftOf(that)) return false;
    	if(that.isLeftOf(this)) return false;
    	if(this.isAbove(that)) return false;
    	if(that.isAbove(this)) return false;
    	
    	else return true;
    }
    
    public static Coordinate polarToCartesianCoordinates(double angle) {
    	/** X-Ache Richtung Osten, Y-Achse Richtung Süden! **/
    	double x = Math.cos(angle);
    	double y = Math.sin(angle);
    	
    	return new Coordinate(x, y);
    }
    
    public void moveGameObject() {
    	
    	Coordinate direction = polarToCartesianCoordinates(movingAngle);
    	
    	objPosition.setX(objPosition.getX() + direction.getX() * movingDistance);
    	objPosition.setY(objPosition.getY() + direction.getY() * movingDistance);
    }
    
    public void makeMove() {
    	moveGameObject();
    }
    
    protected abstract void paintMe(java.awt.Graphics g);
}
