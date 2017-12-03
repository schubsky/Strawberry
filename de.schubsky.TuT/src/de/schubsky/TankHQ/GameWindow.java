package de.schubsky.TankHQ;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * @author Markus Schubsky
 * @version 1.0
 *
 */

@SuppressWarnings("serial")
public class GameWindow extends JFrame{
    
    private final GamePanel tankGamePanel;
    
    public GameWindow() {   
        
        this.tankGamePanel = new GamePanel();
                
        registerWindowListener();
        createMenu();
          
        add(tankGamePanel);
        pack();
        
        setTitle("TankHQ");
        setLocation(10, 10);
        setResizable(false);
        
        setVisible(true);
    }
	
	public void createMenu(){
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenu gameMenu = new JMenu("Game");
		JMenu prefMenu = new JMenu("Preferences");
		
		menuBar.add(fileMenu);
		menuBar.add(gameMenu);
		menuBar.add(prefMenu);
		
		addFileMenuItems(fileMenu);
		addGameMenuItems(gameMenu);
		addPrefMenuItems(prefMenu);
	}
	
	public void addFileMenuItems(JMenu fileMenu){
		JMenuItem quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
		
		quitItem.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void addGameMenuItems(JMenu gameMenu) {
		JMenuItem pauseItem = new JMenuItem("Pause");
		gameMenu.add(pauseItem);
		
		pauseItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tankGamePanel.pauseGame();
			}
		});
		
		JMenuItem continueItem = new JMenuItem("Continue");
		gameMenu.add(continueItem);
		
		continueItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tankGamePanel.continueGame();
			}
		});
		
		gameMenu.addSeparator();
		JMenuItem restartItem = new JMenuItem("Restart");
		gameMenu.add(restartItem);
		
		restartItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tankGamePanel.restartGame();
			}
		});
	}
	
	public void addPrefMenuItems(JMenu prefMenu) {
		JMenu submenu = new JMenu("Your Background");
		prefMenu.add(submenu);
		
		JMenuItem menuItem = new JMenuItem("Crap Area");
		submenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tankGamePanel.setBackgroundImage(0);
				repaint();
			}
		});
		
		menuItem = new JMenuItem("Snow Area");
		submenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tankGamePanel.setBackgroundImage(1);
				repaint();
			}
		});
		
		menuItem = new JMenuItem("Desert Area");
		submenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tankGamePanel.setBackgroundImage(2);
				repaint();
			}
		});
	}
	
	public void registerWindowListener(){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
			@Override
			public void windowDeactivated(WindowEvent e){
				//pause game
				tankGamePanel.pauseGame();
			}
			@Override
			public void windowActivated(WindowEvent e){
				//continue game
				tankGamePanel.continueGame();
			}
		});
	}
}
