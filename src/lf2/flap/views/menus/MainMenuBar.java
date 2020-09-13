package lf2.flap.views.menus;

import javax.swing.JMenuBar;

import lf2.flap.views.listeners.MenubarCommands;
import lf2.flap.views.listeners.MenubarListener;

public class MainMenuBar extends JMenuBar {

	public MainMenuBar() {
		this.init();
	}

	private void init() {
		this.add(MenuUtilities.makeMenu("Ayuda", MenuUtilities.makeMenuItems(MenubarListener.getInstance(), new String[] { "Contenido", "Acerca de" },
				new MenubarCommands[] { MenubarCommands.HELP_CONTENTS, MenubarCommands.ABOUT })));
	}
}
