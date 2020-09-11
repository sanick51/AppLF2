package lf2.flap.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class NodeFigure {
	
	protected static int SIZE = 40;
	protected int y;
	protected int x;
	private String text;
	
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, SIZE, SIZE);
		g.drawString(text, x+(SIZE/2)-5, y+(SIZE/2)+5);
	}
	
	public void drawArrow(Graphics g2, int x1, int y1, int x2, int y2) {
		Graphics2D g = (Graphics2D) g2.create();
		
        double dx = x2 - x1; 
        double dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-5, len-5, len},
                      new int[] {0, -5, 5, 0}, 4);
	}
		
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	
}
