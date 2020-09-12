package lf2.flap.views;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class MainFrame extends JFrame {
	private Canvas canvas;
	
	public MainFrame() {
		this.canvas = new Canvas();
		this.init();
	}
	
	private void init() {
		this.setTitle(ViewConstants.appName);
		this.setLayout(new BorderLayout());
		this.setSize(ViewConstants.defaultSize);
		this.setMinimumSize(ViewConstants.minimumSize);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.add(canvas, BorderLayout.CENTER);
	
		this.setVisible(true);
	}
	
}
