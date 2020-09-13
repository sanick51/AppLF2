package lf2.flap.views.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import lf2.flap.views.Canvas;
import lf2.flap.views.StateFigure;

public class MoveListerner implements MouseListener, MouseMotionListener {
	private static MoveListerner ml = null;
	private StateFigure draggedState, prevState;
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

		if (f != null) {
			if (this.draggedState != null)
				this.prevState = this.draggedState;

			this.draggedState = f;
		} else {
			this.prevState = this.draggedState;
			this.draggedState = null;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (this.draggedState == null) {
				if (this.prevState == null)
					this.canvas.createNode(e.getPoint());
				else
					this.prevState.setSelected(false);
			} else {
				if (e.getPoint().distance(this.draggedState.getPosition()) <= StateFigure.RADIUS) {
					if (e.getClickCount() == 1) {
						if (this.prevState != null)
							this.prevState.setSelected(false);

						this.draggedState.setSelected(true);
					} else if (e.getClickCount() == 2) {
						this.canvas.getPopupInputMenu().setStates(draggedState, draggedState);
						this.canvas.getPopupInputMenu().show(canvas, e.getX(), e.getY() - StateFigure.RADIUS * 2);
					}
				} else {
					StateFigure f = this.canvas.getStateFigureMouse(e.getPoint());

					if (f != null) {
						int x = draggedState.getPosition().x + (f.getPosition().x - draggedState.getPosition().x) / 2;
						int y = draggedState.getPosition().y + (f.getPosition().y - draggedState.getPosition().y) / 2;
						this.canvas.getPopupInputMenu().setStates(draggedState, f);
						this.canvas.getPopupInputMenu().show(canvas, x, y);
					}

					if (this.prevState != null)
						this.prevState.setSelected(false);
				}
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {		
			if (this.draggedState != null) {
				if (this.canvas.getAutomaton().isThereInitialState()) {
					if (this.draggedState.isInit())
						this.canvas.getCanvasPopupMenu().getComponents()[0].setVisible(true);
					else
						this.canvas.getCanvasPopupMenu().getComponents()[0].setVisible(false);
				}
				this.canvas.getCanvasPopupMenu().show(canvas, e.getX(), e.getY());
			}
			
			if (this.prevState != null)
				this.prevState.setSelected(false);
		}

		this.canvas.setLinePoints(null, null);
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

	public void repaint() {
		this.canvas.repaint();
	}

	public StateFigure getDraggedState() {
		return draggedState;
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
