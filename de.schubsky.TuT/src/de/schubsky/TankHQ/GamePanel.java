package de.schubsky.TankHQ;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Markus Schubsky
 * @version 1.0
 *
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel{

	private Missile testMissile1;
	private Missile testMissile2;
	
	private Tank testTank;
	
	private EnemyTank testEnemyTank;
	
	public static final String IMAGE_DIR = "/images/";
    
	private final Dimension prefSize = new Dimension(1180, 780);
    
	private ImageIcon backgroundImage;
	private final String[] backgroundImages= new String [] {"bg_mud.jpg", "bg_snow.jpg", "bg_sand.jpg"};
	
	private boolean gameOver = false;
	private int tanksDestroyedCounter = 0;
	
	private Timer timer;  
    
    
	public GamePanel() {        
		setFocusable(true);
		setPreferredSize(prefSize);
        
		initGame();         
		startGame();
	}
    
    
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
    
	private void initGame () {
		setBackgroundImage(2);
		createGameObjects();
		
		initPlayersTank();
       
		timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doOnTick();     
			}
		});
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				switch(e.getKeyCode()) {
					case VK_SPACE:
						if(testTank.isAbleToShoot()) {
							testMissile1 = testTank.shoot();
						}
						break;
					case VK_DOWN:
					case VK_UP: testTank.stopTank(); break;
				
					case VK_LEFT:
					case VK_RIGHT: testTank.stopTurningTank(); break;
				
					case VK_W:
					case VK_E: testTank.stopTurningCannon(); break;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
                	case VK_LEFT: testTank.turnTankLeft(); break;
                	case VK_RIGHT: testTank.turnTankRight(); break;
                        
                	case VK_UP: testTank.accelerateTank(); break;
                	case VK_DOWN: testTank.decelerateTank(); break;
                        
                	case VK_W: testTank.turnCannonLeft(); break;
                	case VK_E: testTank.turnCannonRight(); break;
				}
			}
		});
	}
    
	private void createGameObjects() {
		// later: creating game objects
		testMissile1 = new Missile(new Coordinate(200, 100), 15, Math.toRadians(45), 5);
		testMissile2 = new Missile(new Coordinate(200, 600), 15, Math.toRadians(-45), 5);
		
		testEnemyTank = new EnemyTank(new Coordinate(40, 600), 80, 50, Math.toRadians(-20), 0, testTank);
	} 
    
	private void initPlayersTank() {        
		// later: initialize tank of player
		testTank = new Tank(new Coordinate(600, 250), 70, 45, Math.toRadians(270), 0);
		/*testTank.accelerateTank();
		testTank.turnTankLeft();
		testTank.turnCannonRight();
		testMissile1 = testTank.shoot();*/
	}
    
	public void setBackgroundImage(int imageNumber) {
		String imagePath = IMAGE_DIR + backgroundImages[imageNumber];
		URL imageURL = getClass().getResource(imagePath);
		if (imageURL == null) {
			System.out.println("Das wars dann wohl");
			timer.stop();
		} else {
			backgroundImage = new ImageIcon(imageURL);
		}
	}
    
	private void startGame() {
		timer.start();
	}
    
	public void pauseGame() {
    timer.stop();
	}
    
	public void continueGame() {
		if (!isGameOver()) timer.start();
	}
    
	public void restartGame() {
		tanksDestroyedCounter = 0;
		setGameOver(false);
		createGameObjects();
		startGame();
	}
    
	private void endGame() { 
		setGameOver(true);
		pauseGame();
	}
    
	private void doOnTick() {        
		tanksDestroyedCounter++;
		if (tanksDestroyedCounter > 2015) endGame();
		
		testMissile1.makeMove();
		testMissile2.makeMove();
		//if (testMissile1.touches(testMissile2)) endGame();
		
		testTank.makeMove();
		if (testTank.touches(testMissile2))	endGame();
		//if (testTank.isAbleToShoot()) testMissile1 = testTank.shoot();
		//if(testMissile2.getRange() < 1) testMissile2 = new Missile(new Coordinate(200, 609), 9, Math.toRadians(-45), 5);
		
		testEnemyTank.setPlayersTank(testTank);
		testEnemyTank.makeMove();
		if (testEnemyTank.isTargetLocked() && testEnemyTank.isAbleToShoot()) testMissile2 = testEnemyTank.shoot();
		if (testEnemyTank.touches(testMissile1)) endGame();
        
		repaint();
	}
    
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
        
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	           
	    backgroundImage.paintIcon(null, g, 0, 0); 
                
	    g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));            
	    g.setColor(Color.BLUE);
	    g.drawString("Tanks destroyed: " + tanksDestroyedCounter, 22, prefSize.height-5);        
                
	    if (isGameOver()) {
	    	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
	    	g.setColor(Color.RED);
	    	g.drawString("Noob Down!", prefSize.width/2 - 130, prefSize.height/5);
	    }
	    testTank.paintMe(g);
	    
	    testEnemyTank.paintMe(g);
	    
	    testMissile1.paintMe(g);
	    testMissile2.paintMe(g);
	}
    
}