package lf2.flap.views;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import lf2.flap.models.entity.Automaton;
import lf2.flap.models.entity.State;
import lf2.flap.views.listeners.MoveListerner;
import lf2.flap.views.menus.CanvasPopupMenu;
import lf2.flap.views.menus.PopupInputMenu;

public class Canvas extends JPanel {
	private Automaton automaton;
	private Point mousePoint, statePoint;
	private CanvasPopupMenu canvasPopupMenu;
	private PopupInputMenu popupInputMenu;

	public Canvas() {
		this.automaton = new Automaton();
		this.canvasPopupMenu = new CanvasPopupMenu();
		this.popupInputMenu = new PopupInputMenu(this);
		MoveListerner.getInstance().setCanvas(this);
		this.init();
	}

	private void init() {
		this.add(this.canvasPopupMenu);
		this.add(this.popupInputMenu);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (State s : this.automaton.getStates()) {
			((StateFigure) s).draw(g);
		}

		if (mousePoint != null && statePoint != null)
			g.drawLine(mousePoint.x, mousePoint.y, statePoint.x, statePoint.y);
	}

	public void createNode(Point p) {
		StateFigure figure = new StateFigure(this.automaton);
		figure.setX(p.x);
		figure.setY(p.y);
		figure.setLabel("q" + this.automaton.getStates().size());
		this.automaton.getStates().add(figure);
	}

	public void createNode(int x, int y) {
		this.createNode(new Point(x, y));
	}

	public Automaton getAutomaton() {
		return automaton;
	}

	public StateFigure getStateFigureMouse(Point p) {
		StateFigure f = null, aux;

		for (State s : this.automaton.getStates()) {
			aux = (StateFigure) s;
			if (aux.isMouseOver(p))
				f = aux;
		}

		return f;
	}

	public void setLinePoints(Point mousePoint, Point statePoint) {
		this.mousePoint = mousePoint;
		this.statePoint = statePoint;
	}
	
	public CanvasPopupMenu getCanvasPopupMenu() {
		return canvasPopupMenu;
	}
	
	public PopupInputMenu getPopupInputMenu() {
		return popupInputMenu;
	}
}
