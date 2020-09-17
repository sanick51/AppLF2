package lf2.flap.views.figures;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import lf2.flap.models.entity.State;
import lf2.flap.models.entity.Transition;
import lf2.flap.views.ViewConstants;

public class TransitionFigure extends Transition implements Drawer {
	protected boolean isDrawed = false;;
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
		float x = s.x, y = s.y;
		Font font = g.getFont();

		if (value.isEmpty())
			g.setFont(ViewConstants.emptyFont);

		if (startState == endState) {
			g.drawArc((int) (x - RADIUS / 2), (int) (y - RADIUS * 5 / 2), RADIUS, RADIUS * 2, 320, 260);
			g.drawString(value.isEmpty() ? ViewConstants.emptySymbol : value, (int) (x),
					(int) (y - 5 - RADIUS * 5 / 2));
			g.drawPolyline(
					new int[] { (int) (x - RADIUS * 9 / 10), (int) (x - RADIUS * 2 / 5), (int) (x - RADIUS / 5) },
					new int[] { (int) (y - RADIUS * 5 / 4), (int) (y - RADIUS * 9 / 10), (int) (y - RADIUS * 7 / 5) },
					3);
		} else {
			double relRadius = 1 - RADIUS / s.getPosition().distance(f.getPosition());
			double relArrow = 1 - (RADIUS * 1.5) / s.getPosition().distance(f.getPosition());
			double slope = -(x - f.x) / (y - f.y);

			
			if(false)//uso para par de nodos directos, falta condicion
			g.drawLine((int) x, (int) y, (int) (x + (f.x - x) * relRadius), (int) (y + (f.y - y) * relRadius));

			//rotacion de string y curva| uso para par de nodos recursivos, falta condicion, falta punta de flecha
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

			g2.drawArc(RADIUS, (int) (-arcHeight * 2 / 3), (int) s.getPosition().distance(f.getPosition()) - RADIUS * 2,
					(int) arcHeight, 0, 180);
			//string debe cambiar de posicion pero no rotar
			g.drawString(value.isEmpty() ? ViewConstants.emptySymbol : value,
					(int) s.getPosition().distance(f.getPosition()) / 2, (int) (arcHeight * -0.7));

			if (f.x > s.x)
				g2.rotate(-Math.atan((f.y - s.y) / (f.x - s.x)));
			else if (f.x < s.x)
				g2.rotate(-Math.PI - Math.atan((f.y - s.y) / (f.x - s.x)));
			else if (f.y > s.y)
				g2.rotate(-Math.PI / 2);
			else if (f.y < s.y)
				g2.rotate(Math.PI / 2);
			
			g2.translate(-x, -y);

			//buscando solucion de punta de flecha
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
			
		}
		
		g.setFont(font);
		this.isDrawed = true;
	}

}
