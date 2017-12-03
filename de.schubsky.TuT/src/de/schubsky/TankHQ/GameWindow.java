package de.schubsky.TankHQ;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
 *
 */

public class GameWindow extends JFrame{
    
    private final GamePanel panzerGamePanel;
    
    public GameWindow() {   
        
        this.panzerGamePanel = new GamePanel();
                
        registerWindowListener();
        createMenu();
          
        add(panzerGamePanel);
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
	
	public void registerWindowListener(){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
			@Override
			public void windowDeactivated(WindowEvent e){
				//pause game
			}
			@Override
			public void windowActivated(WindowEvent e){
				//continue game
			}
		});
	}
}
