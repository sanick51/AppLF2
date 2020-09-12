package lf2.flap.views;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import lf2.flap.models.entity.Automaton;
import lf2.flap.models.entity.State;
import lf2.flap.views.listeners.MoveListerner;

public class Canvas extends JPanel {
	private Automaton automaton;
	private Point mousePoint, statePoint;

	public Canvas() {
		this.automaton = new Automaton();
		MoveListerner.getInstance().setCanvas(this);
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
		StateFigure figure = new StateFigure();
		figure.setX(p.x);
		figure.setY(p.y);
		figure.setLabel("q" + this.automaton.getStates().size());
		this.getAutomaton().getStates().add(figure);
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
}
