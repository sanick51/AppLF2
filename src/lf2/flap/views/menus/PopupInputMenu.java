package lf2.flap.views.menus;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import lf2.flap.views.Canvas;
import lf2.flap.views.StateFigure;

public class PopupInputMenu extends JPopupMenu implements KeyListener {
	private Canvas canvas;
	private JTextField inputField;
	private StateFigure draggedState, droppedState;

	public PopupInputMenu(Canvas canvas) {
		this.canvas = canvas;
		this.inputField = new JTextField(5);
		this.init();
	}

	private void init() {
		this.inputField.addKeyListener(this);
		this.add(this.inputField);
	}

	public String getText() {
		return this.inputField.getText();
	}
	
	@Override
	public void show(Component invoker, int x, int y) {
		super.show(invoker, x, y);
		this.inputField.requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.canvas.getAutomaton().createTransition(this.inputField.getText(), draggedState, droppedState);
			this.setVisible(false);
		}else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.setVisible(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void setStates(StateFigure dragged, StateFigure dropped) {
		this.draggedState = dragged;
		this.droppedState = dropped;
	}
	
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		this.inputField.setText("");
		this.canvas.repaint();
	}
}
