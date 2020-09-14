package lf2.flap.views.figures;

import java.awt.Graphics;

import lf2.flap.models.entity.State;
import lf2.flap.models.entity.Transition;

public class TransitionFigure extends Transition  implements Drawer{
	

	public TransitionFigure(String value, State state) {
		super(value, state);
	}
	
	public TransitionFigure(String value, State start, State end) {
		super(value, start, end);
	}
	
	
	public void draw(Graphics g) {
		StateFigure s=((StateFigure)startState);
		StateFigure f=((StateFigure)endState);
		float x = s.x, y=s.y;
		if (startState == endState) {
			g.drawArc((int) (x - RADIUS / 2), (int) (y - RADIUS * 5 / 2), RADIUS, RADIUS * 2, 320, 260);
			g.drawString(value, (int) (x), (int) (y -5 - RADIUS * 5 / 2));
			g.drawPolyline(
					new int[] { (int) (x - RADIUS * 9 / 10), (int) (x - RADIUS * 2 / 5), (int) (x - RADIUS / 5) },
					new int[] { (int) (y - RADIUS * 5 / 4), (int) (y - RADIUS * 9 / 10), (int) (y - RADIUS * 7 / 5) },
					3);
		} else {
			double relRadius = 1 - RADIUS / s.getPosition().distance(f.getPosition());
			double relArrow = 1 - (RADIUS * 1.5) / s.getPosition().distance(f.getPosition());
			double slope = -(x - f.x) / (y - f.y);

			g.drawLine((int) x, (int) y, (int) (x + (f.x - x) * relRadius),
					(int) (y + (f.y - y) * relRadius));

			int xm = (int) (x + (f.x - x) * relArrow);
			int ym = (int) (y + (f.y - y) * relArrow);

			int x1, y1, x2, y2, arrowWidth = 5;

			if (slope <= 1 && slope >= -1) {
				x1 = xm + arrowWidth;
				y1 = (int) (slope * (x1 - xm) + ym);
				x2 = xm - arrowWidth;
				y2 = (int) (slope * (x2 - xm) + ym);
			} else {
				y1 = ym + arrowWidth;
				x1 = (int) ((y1 - ym) / slope + xm);
				y2 = ym - arrowWidth;
				x2 = (int) ((y2 - ym) / slope + xm);
			}

			g.drawPolyline(new int[] { x1, (int) (x + (f.x - x) * relRadius), x2 },
					new int[] { y1, (int) (y + (f.y - y) * relRadius), y2 }, 3);
			g.drawString(value, (int) (x + (f.x - x) * relRadius / 2), (int) (y -5 + (f.y - y) * relRadius / 2));
		}
	}
	
	
	
	
}
