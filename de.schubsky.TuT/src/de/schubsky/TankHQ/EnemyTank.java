package de.schubsky.TankHQ;

import java.awt.Color;

/**
 * 
 * @author Markus Schubsky
 * @version 1.0
 *
 */

public class EnemyTank extends Tank {
	
	private Tank playersTank;
	private boolean targetLocked = false;
	
	
	public EnemyTank(Coordinate position, double width, double height, double movingAngle, double movingDistance, Tank playersTank) {
		super(position, width, height, movingAngle, movingDistance);
		this.playersTank = playersTank;
		
		setTurningVelocity(TURNING_VELOCITY/10 + Math.random() * 0.01);
		setDrivingVelocity(DRIVING_VELOCITY/10 + Math.random() * 1.0);
		setLoadingTime(AMMO_LOADING_TIME*2);
		setTurretColor(new Color(200, 120, 60));
		
		int enemyTankEnergy = (int)(width/10 + Math.random()*5);
		setEnergy(enemyTankEnergy);
		setEnergyStart(enemyTankEnergy);
		
		accelerateTank();
	}
	
	public Tank getPlayersTank() {
		return playersTank;
	}
	
	public void setPlayersTank(Tank playersTank) {
		this.playersTank = playersTank;
	}
	
	public boolean isTargetLocked() {
		return targetLocked;
	}
	
	public void setTargetLocked(boolean targetLocked) {
		this.targetLocked = targetLocked;
	}
	
	@Override
	public void makeMove() {
		
		if(getObjPosition().getX() > 800 || getObjPosition().getY() < 200) {
			stopTank();
		}
		aimAtPlayer();
		
		super.makeMove();
	}
	
	public void aimAtPlayer() {
		//Mittelpunkt vom Spielerpanzer auf der Spielfläche
		double playersTankCenterX = playersTank.getObjPosition().getX() + playersTank.getWidth()/2;
		double playersTankCenterY = playersTank.getObjPosition().getY() + playersTank.getHeight()/2;
		
		//Mittelpunkt vom Gegnerpanzer auf der Spielfläche
		double enemyTankCenterX = getObjPosition().getX() + getWidth()/2;
		double enemyTankCenterY = getObjPosition().getY() + getHeight()/2;
		
		//Winkelberechnung vom Gegnerpanzer zum Spielerpanzer
        double x = playersTankCenterX - enemyTankCenterX;
        double y = playersTankCenterY - enemyTankCenterY;
        double angleToPlayer = Math.atan2(y, x);
        if (angleToPlayer < 0) angleToPlayer = angleToPlayer + 2 * Math.PI; 
        
        //Winkel in den die Kanone des Gegnerpanzers zeigt
        double absoluteCannonAngle = getAngleCannon() + getMovingAngle();        
        if (absoluteCannonAngle > 2 * Math.PI) absoluteCannonAngle = absoluteCannonAngle - 2 * Math.PI;
        
        //Abweichung vom Kanonenwinkel zur Position des Spielers
        double deltaAngle = angleToPlayer - absoluteCannonAngle;
        
        if(Math.abs(deltaAngle) <= 0.01) {
        	setTargetLocked(true);
        	stopTurningCannon();
        }
        else {
        	setTargetLocked(false);
        	if(deltaAngle < 0.01) {
        		turnCannonLeft();
        		if(Math.abs(deltaAngle) > Math.toRadians(180)) {
        			turnCannonRight();
        		}
        	}
        	if(deltaAngle > 0.01) {
        		turnCannonRight();
        		if(Math.abs(deltaAngle) > Math.toRadians(180)) {
        			turnCannonLeft();
        		}
        	}
        }
	}
}
