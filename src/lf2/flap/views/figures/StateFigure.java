package lf2.flap.views.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import lf2.flap.models.entity.Automaton;
import lf2.flap.models.entity.State;
import lf2.flap.models.entity.Transition;
import lf2.flap.views.ViewConstants;

public class StateFigure extends State implements Drawer {
	protected float x, y;
	private boolean isSelected = false;

	public StateFigure(Automaton automaton) {
		super(automaton);
		this.y = this.x = -1;
	}

	public StateFigure(Automaton automaton, String label) {
		super(automaton, label);
		this.y = this.x = -1;
	}

	public void draw(Graphics g) {
		Map<StateFigure, Integer> s = new HashMap<StateFigure, Integer>();
		TransitionFigure tf;
		StateFigure sf;
		int aux;

		for (Transition t : outTransitions) {
			tf = ((TransitionFigure) t);
			sf = (StateFigure)t.getEndState();

			if (s.containsKey(t.getEndState())) {
				aux = s.get(sf);
				tf.labelHeight = aux;
				s.put(sf, aux + 1);
			} else
				s.put(sf, 1);

			tf.draw(g);
		}

		aux = 0;
		
		for (Transition t : selfTransitions) {
			tf = ((TransitionFigure) t);
			tf.labelHeight = aux;
			tf.draw(g);
			aux++;
		}

		g.setColor(ViewConstants.stateColor);

		if (isSelected)
			g.setColor(ViewConstants.selectedStateColor);

		g.fillOval((int) x - RADIUS, (int) y - RADIUS, RADIUS * 2, RADIUS * 2);
		g.setColor(Color.BLACK);
		g.drawOval((int) x - RADIUS, (int) y - RADIUS, RADIUS * 2, RADIUS * 2);
		g.drawString(this.label, (int) x - 5, (int) y + 5);

		if (this.isFinal) {
			g.drawOval((int) x - RADIUS + 3, (int) y - RADIUS + 3, RADIUS * 2 - 6, RADIUS * 2 - 6);
		}

		if (this.isInit) {
			g.drawPolygon(new int[] { (int) (x - RADIUS * 2), (int) (x - RADIUS), (int) (x - RADIUS * 2) },
					new int[] { (int) (y - RADIUS), (int) y, (int) (y + RADIUS) }, 3);
		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public boolean isMouseOver(Point p) {
		return Math.hypot(Math.abs(p.x - this.x), Math.abs(p.y - this.y)) <= RADIUS;
	}

	public void setPosition(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public Point getPosition() {
		return new Point((int) this.x, (int) this.y);
	}
}
