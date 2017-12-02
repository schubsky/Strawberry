package de.schubsky.TankHQ;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Markus Schubsky
 *
 */

public class GameWindow extends JFrame{
	
	public GameWindow() {
		JPanel testPanel = new JPanel();
		testPanel.setPreferredSize(new Dimension(1000, 800));
		
		createMenu();
		
		add(testPanel);
		pack();
		
		setTitle("TankHQ");
		setLocation(10,10);
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
}
