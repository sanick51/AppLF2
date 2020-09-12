package lf2.flap.views.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import lf2.flap.views.Canvas;
import lf2.flap.views.StateFigure;

public class MoveListerner implements MouseListener, MouseMotionListener {
	private static MoveListerner ml = null;
	private StateFigure selectedState, draggedState;
	private Canvas canvas;

	private MoveListerner() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		StateFigure f = this.canvas.getStateFigureMouse(e.getPoint());
		if (this.selectedState != null && this.selectedState != f) {
			this.selectedState.setSelected(false);
			this.selectedState = null;
		}
			
		if (f != null) {
			this.selectedState = this.draggedState = f;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.draggedState == null) {
				this.canvas.createNode(e.getPoint());
		} else {
			if (e.getPoint().distance(this.selectedState.getPosition()) <= StateFigure.RADIUS) {
				if (e.getClickCount() == 2)
					this.selectedState.setSelected(true);
			} else {
				StateFigure f = this.canvas.getStateFigureMouse(e.getPoint());

				if (f != null) {
					this.canvas.getAutomaton().createTransition("A", draggedState, f);
				}
			}

			this.canvas.setLinePoints(null, null);
			this.draggedState = null;
		}

		this.canvas.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (draggedState != null) {
			if (draggedState.isSelected()) {
				draggedState.setPosition(e.getPoint());
			} else {
				this.canvas.setLinePoints(e.getPoint(), draggedState.getPosition());
			}
			canvas.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
		this.canvas.addMouseListener(getInstance());
		this.canvas.addMouseMotionListener(getInstance());
	}

	public static MoveListerner getInstance() {
		if (ml == null)
			ml = new MoveListerner();

		return ml;
	}
}
