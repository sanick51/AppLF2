package lf2.flap.views.figures;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import lf2.flap.models.entity.State;
import lf2.flap.models.entity.Transition;
import lf2.flap.views.ViewConstants;

public class TransitionFigure extends Transition implements Drawer {
	protected boolean isDrawed = false;
	protected int labelHeight = 0;
	protected float arcHeight = 50;

	public TransitionFigure(String value, State state) {
		super(value, state);
	}

	public TransitionFigure(String value, State start, State end) {
		super(value, start, end);
	}

	public void draw(Graphics g) {
		StateFigure s = ((StateFigure) startState);
		StateFigure f = ((StateFigure) endState);
		boolean hasTransition = f.hasTransition(s);
		float x = s.x, y = s.y;
		Font font = g.getFont();

		if (value.isEmpty())
			g.setFont(ViewConstants.emptyFont);

		if (startState == endState) {
			if (this.labelHeight == 0) {
				g.drawArc((int) (x - RADIUS / 2), (int) (y - RADIUS * 5 / 2), RADIUS, RADIUS * 2, 320, 260);
				g.drawPolyline(
						new int[] { (int) (x - RADIUS * 9 / 10), (int) (x - RADIUS * 2 / 5), (int) (x - RADIUS / 5) },
						new int[] { (int) (y - RADIUS * 5 / 4), (int) (y - RADIUS * 9 / 10),
								(int) (y - RADIUS * 7 / 5) },
						3);
			}

			g.drawString(value.isEmpty() ? ViewConstants.emptySymbol : value, (int) (x),
					(int) (y - 5 - RADIUS * 5 / 2) - 10 * this.labelHeight);
		} else {
			double relRadius = 1 - RADIUS / s.getPosition().distance(f.getPosition());

			// rotacion de string y curva| uso para par de nodos recursivos
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(x, y);

			if (f.x > s.x)
				g2.rotate(Math.atan((f.y - s.y) / (f.x - s.x)));
			else if (f.x < s.x)
				g2.rotate(Math.PI + Math.atan((f.y - s.y) / (f.x - s.x)));
			else if (f.y > s.y)
				g2.rotate(Math.PI / 2);
			else if (f.y < s.y)
				g2.rotate(-Math.PI / 2);

			if (!hasTransition) {// uso para par de nodos directos
				g.drawLine(0, 0, (int) (s.getPosition().distance(f.getPosition()) * relRadius), 0);
				g.fillPolygon(
						new int[] { (int) (s.getPosition().distance(f.getPosition()) * relRadius),
								(int) (s.getPosition().distance(f.getPosition()) * relRadius - 10),
								(int) (s.getPosition().distance(f.getPosition()) * relRadius - 10) },
						new int[] { 0, 5, -5 }, 3);
			} else {
				g2.drawArc(RADIUS, (int) (-arcHeight * 2 / 3),
						(int) s.getPosition().distance(f.getPosition()) - RADIUS * 2, (int) arcHeight, 0, 180);
				g.fillPolygon(
						new int[] { (int) (s.getPosition().distance(f.getPosition()) * relRadius),
								(int) (s.getPosition().distance(f.getPosition()) * relRadius - 5),
								(int) (s.getPosition().distance(f.getPosition()) * relRadius - 10) },
						new int[] { -7, -18, -12 }, 3);
			}

			g.drawString(value.isEmpty() ? ViewConstants.emptySymbol : value,
					(int) s.getPosition().distance(f.getPosition()) / 2,
					(int) (arcHeight * -0.8) - 12 * this.labelHeight);
			g.drawLine((int) s.getPosition().distance(f.getPosition()) / 2,
					(int) (arcHeight * -0.8) - 12 * this.labelHeight + 3,
					(int) s.getPosition().distance(f.getPosition()) / 2 + this.value.length() * 10,
					(int) (arcHeight * -0.8) - 12 * this.labelHeight + 3);

			if (f.x > s.x)
				g2.rotate(-Math.atan((f.y - s.y) / (f.x - s.x)));
			else if (f.x < s.x)
				g2.rotate(-Math.PI - Math.atan((f.y - s.y) / (f.x - s.x)));
			else if (f.y > s.y)
				g2.rotate(-Math.PI / 2);
			else if (f.y < s.y)
				g2.rotate(Math.PI / 2);

			g2.translate(-x, -y);
		}

		g.setFont(font);
		this.isDrawed = true;
	}

}
