package lf2.flap.views;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import lf2.flap.views.menus.MainMenuBar;


public class MainFrame extends JFrame {
	private MainMenuBar menuBar;
	private Canvas canvas;
	
	public MainFrame() {
		this.canvas = new Canvas();
		this.menuBar = new MainMenuBar();
		this.init();
	}
	
	private void init() {
		this.setTitle(ViewConstants.appName);
		this.setLayout(new BorderLayout());
		this.setSize(ViewConstants.defaultSize);
		this.setMinimumSize(ViewConstants.minimumSize);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setJMenuBar(this.menuBar);
		this.add(canvas, BorderLayout.CENTER);
	
		this.setVisible(true);
	}
	
}
