package lf2.flap.views;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

import lf2.flap.models.entity.Automaton;
import lf2.flap.models.entity.State;
import lf2.flap.views.figures.StateFigure;
import lf2.flap.views.listeners.MoveListerner;
import lf2.flap.views.menus.CanvasPopupMenu;
import lf2.flap.views.menus.PopupInputMenu;

public class Canvas extends JPanel {
	private Automaton automaton;
	private Point mousePoint, statePoint;
	private CanvasPopupMenu canvasPopupMenu;
	private PopupInputMenu popupInputMenu;
	private int stateCounter = 0;

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

		if (mousePoint != null && statePoint != null)
			g.drawLine(mousePoint.x, mousePoint.y, statePoint.x, statePoint.y);

		for (State s : this.automaton.getStates()) {
			((StateFigure) s).draw(g);
		}

	}

	public void createNode(Point p) {
		StateFigure figure = new StateFigure(this.automaton);
		figure.setX(p.x);
		figure.setY(p.y);
		figure.setLabel("q" + stateCounter++);
		this.automaton.addState(figure);
		;
	}

	public void createNode(int x, int y) {
		this.createNode(new Point(x, y));
	}

	public Automaton getAutomaton() {
		return automaton;
	}

	public void setAutomaton(Automaton automaton) {
		this.automaton = automaton;
		StateFigure aux = (StateFigure) this.automaton.getInitialState();
		Point lastPoint = new Point(70, 70);

		aux.setPosition(lastPoint);

		for (State s : this.automaton.getStates()) {
			aux = (StateFigure) s;
			Random rnd = new Random();
			aux.setX(rnd.nextInt(300) + lastPoint.x / 3);
			aux.setY(rnd.nextInt(300) + lastPoint.y / 3);
			lastPoint.x = aux.getX();
			lastPoint.y = aux.getY();
		}

		this.repaint();
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
