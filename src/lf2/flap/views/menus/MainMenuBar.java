package lf2.flap.views.menus;

import javax.swing.JMenuBar;

import lf2.flap.views.listeners.MenubarCommands;
import lf2.flap.views.listeners.MenubarListener;

public class MainMenuBar extends JMenuBar {

	public MainMenuBar() {
		this.init();
	}

	private void init() {
		
		this.add(MenuUtilities.makeMenu("App", 
				MenuUtilities.makeMenuItems(MenubarListener.getInstance(), 
						new String[] { 
								"Reiniciar canvas" 
						}, new MenubarCommands[] { 
								MenubarCommands.RESET_CANVAS
						})));
		
		this.add(MenuUtilities.makeMenu("Regex", 
				MenuUtilities.makeMenuItems(MenubarListener.getInstance(), 
						new String[] { 
								"Generar regex", 
								"Generar automata" 
						}, new MenubarCommands[] { 
								MenubarCommands.GENRT_REGEX, 
								MenubarCommands.GENRT_AUTOM 
						})));
		
		
		this.add(MenuUtilities.makeMenu("Ayuda", 
				MenuUtilities.makeMenuItems(MenubarListener.getInstance(), 
						new String[] { 
								"Contenido", 
								"Acerca de" 
						}, new MenubarCommands[] { 
								MenubarCommands.HELP_CONTENTS, 
								MenubarCommands.ABOUT 
						})));
	}
}
