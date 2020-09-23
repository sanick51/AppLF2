package lf2.flap.views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CanvasMenuListener implements ActionListener {
	private static CanvasMenuListener canvasMenuListener = null;

	private CanvasMenuListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (CanvasMenuCommands.valueOf(e.getActionCommand())) {
		case TOGGLE_INIT:
			if (!MoveListerner.getInstance().getDraggedState().isInit())
				MoveListerner.getInstance().getDraggedState().setInit(true);
			else
				MoveListerner.getInstance().getDraggedState().setInit(false);
			break;

		case TOGGLE_FINAL:
			if (!MoveListerner.getInstance().getDraggedState().isFinal())
				MoveListerner.getInstance().getDraggedState().setFinal(true);
			else
				MoveListerner.getInstance().getDraggedState().setFinal(false);
			break;
		case DELETE:
			MoveListerner.getInstance().getCanvas().getAutomaton()
					.removeState(MoveListerner.getInstance().getDraggedState());
			break;

		default:
			System.out.println("El comando " + e.getActionCommand() + " no tiene funci�n.");
			break;
		}

		MoveListerner.getInstance().repaint();
	}

	public static CanvasMenuListener getInstance() {
		if (canvasMenuListener == null)
			canvasMenuListener = new CanvasMenuListener();

		return canvasMenuListener;
	}
}
