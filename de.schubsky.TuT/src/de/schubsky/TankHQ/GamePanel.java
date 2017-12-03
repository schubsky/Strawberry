package de.schubsky.TankHQ;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel{

  public static final String IMAGE_DIR = "images/";
    
  private final Dimension prefSize = new Dimension(1180, 780);
    
  private ImageIcon backgroundImage;
  private final String[] backgroundImages= new String [] {"bg_mud.jpg", 
                                                          "bg_snow.jpg", 
                                                          "bg_sand.jpg"};
    
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
    setBackgroundImage(1);
    createGameObjects();
       
    timer = new Timer(20, new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
          doOnTick();     
       }
    });
  }
    
  private void createGameObjects() {
    // later: creating game objects
  } 
    
  private void initPlayersTank() {        
    // later: initialize tank of player
  }
    
  public void setBackgroundImage(int imageNumber) {
    String imagePath = IMAGE_DIR + backgroundImages[imageNumber];
    URL imageURL = getClass().getResource(imagePath);        
    backgroundImage = new ImageIcon(imageURL);
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
  }
    
}