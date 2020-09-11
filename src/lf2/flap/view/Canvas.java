package lf2.flap.view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import lf2.flap.controller.MoveListerner;

public class Canvas extends JPanel {

	private StateFigure figure;
	private ArrayList<StateFigure> list;
	private int mode;

	public Canvas(MoveListerner ml) {
		figure = new StateFigure();

		list = new ArrayList<StateFigure>();
		this.addMouseListener(ml);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (StateFigure nodeFigure : list) {
			nodeFigure.draw(g);
		}
	}

	public void createNode(int x, int y) {
		figure = new StateFigure();

		if (mode == 0) {
			figure.setFinal(false);
			figure.setInit(false);
		} else if (mode == 1) {
			figure.setFinal(true);
			figure.setInit(false);
		} else if (mode == 2) {
			figure.setFinal(false);
			figure.setInit(true);
		} else {
			figure.setFinal(true);
			figure.setInit(true);
		}

		figure.setX(x);
		figure.setY(y);
		figure.setLabel("q" + list.size());
		list.add(figure);
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void drawConcatenation(NodeFigure nf, NodeFigure dnf) {
		int nm = (nf.x + (NodeFigure.SIZE / 2));
		int nm2 = (nf.y + (NodeFigure.SIZE / 2));

		int dnm = (dnf.x + (NodeFigure.SIZE / 2));
		int dnm2 = (dnf.y + (NodeFigure.SIZE / 2));

		double d = nm2 - nm;
		double d2 = dnm2 - dnm;

		double dt = Math.sqrt(Math.pow(d, 2) + Math.pow(d2, 2));

		double angle = Math.toDegrees(Math.asin(d2 / dt)) * -1;

		System.out.println(angle);

		int getX = (int) (Math.toDegrees(Math.sin(angle)) * (NodeFigure.SIZE / 2));
		int getY = (int) (Math.toDegrees(Math.cos(angle)) * (NodeFigure.SIZE / 2));
		System.out.println("X: " + getX);
		System.out.println("Y: " + getY);

		if (nf.x > dnf.x && nf.y > dnf.y) { // 2 cuadrante
			nf.drawArrow(getGraphics(), nm - ((NodeFigure.SIZE / 2) + getY), nm2 - ((NodeFigure.SIZE / 2) + getX), dnm, dnm2);
		} else if (nf.x > dnf.x && nf.y < dnf.y) { // 3 cuadrante
			nf.drawArrow(getGraphics(), nf.x, nf.y + NodeFigure.SIZE, dnf.x + NodeFigure.SIZE, dnf.y);
		} else if (nf.x < dnf.x && nf.y > dnf.y) { // 1 cuadrante
			nf.drawArrow(getGraphics(), nf.x + NodeFigure.SIZE, nf.y, dnf.x, dnf.y + NodeFigure.SIZE);
		} else if (nf.x < dnf.x && nf.y < dnf.y) { // 4 cuadrante
			nf.drawArrow(getGraphics(), nf.x + NodeFigure.SIZE, nf.y + NodeFigure.SIZE, dnf.x, dnf.y);
		}

	}

	public void drawPlus() {

	}

}
