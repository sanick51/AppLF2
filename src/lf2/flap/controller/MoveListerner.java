package lf2.flap.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import lf2.flap.view.Canvas;
import lf2.flap.view.JPanelToggle;


public class MoveListerner implements MouseListener, ActionListener {
	
	private Canvas canvas;
	private JPanelToggle jPanelToggle;
	
	public MoveListerner() {
		canvas = new Canvas(this);
		jPanelToggle = new JPanelToggle(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.getPosition(e.getX(), e.getY());
		System.out.println("X: " + e.getX() + " - Y: " + e.getY());
		canvas.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void getPosition(int x, int y) {
		canvas.createNode(x, y);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (jPanelToggle.getJcbFinal().isSelected()) {
			canvas.setMode(1);
		}
		
		if (jPanelToggle.getJcbInictial().isSelected()) {
			canvas.setMode(2);
		}
			
		if (jPanelToggle.getJcbFinal().isSelected() && jPanelToggle.getJcbInictial().isSelected()) {
			canvas.setMode(3);
		}
		
		if (!jPanelToggle.getJcbFinal().isSelected() && !jPanelToggle.getJcbInictial().isSelected()) {
			canvas.setMode(0);
		}
	}
	
	public JPanelToggle getjPanelToggle() {
		return jPanelToggle;
	}

}
