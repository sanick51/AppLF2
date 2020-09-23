package lf2.flap.views.menus;

import javax.swing.JPopupMenu;

import lf2.flap.views.listeners.CanvasMenuCommands;
import lf2.flap.views.listeners.CanvasMenuListener;

public class CanvasPopupMenu extends JPopupMenu {

	public CanvasPopupMenu() {
		this.init();
	}

	private void init() {
		MenuUtilities.makeMenu(this, MenuUtilities.makeMenuItems(CanvasMenuListener.getInstance(), new String[] { "Inicial", "Final" ,"Eliminar"},
				new CanvasMenuCommands[] { CanvasMenuCommands.TOGGLE_INIT, CanvasMenuCommands.TOGGLE_FINAL, CanvasMenuCommands.DELETE }));
	}

}
