package lf2.flap.views.regex;

import javax.swing.JMenuBar;

import lf2.flap.views.menus.MenuUtilities;
import lf2.flap.views.regex.listeners.RegexMenubarCommands;
import lf2.flap.views.regex.listeners.RegexMenubarListener;

public class RegexMenuBar extends JMenuBar {

	public RegexMenuBar() {
		this.init();
	}

	private void init() {
		
		this.add(MenuUtilities.makeMenu("App", 
				MenuUtilities.makeMenuItems(RegexMenubarListener.getInstance(), 
						new String[] { 
								"Reiniciar canvas",
								"Pantalla principal"
						}, new RegexMenubarCommands[] { 
								RegexMenubarCommands.RESET_CANVAS,
								RegexMenubarCommands.RETURN_MAIN
						})));
		
		this.add(MenuUtilities.makeMenu("Regex", 
				MenuUtilities.makeMenuItems(RegexMenubarListener.getInstance(), 
						new String[] { 
								"Generar regex", 
								"Generar automata" 
						}, new RegexMenubarCommands[] { 
								RegexMenubarCommands.GENRT_REGEX, 
								RegexMenubarCommands.GENRT_AUTOM 
						})));
		
		
		this.add(MenuUtilities.makeMenu("Ayuda", 
				MenuUtilities.makeMenuItems(RegexMenubarListener.getInstance(), 
						new String[] { 
								"Contenido", 
								"Acerca de" 
						}, new RegexMenubarCommands[] { 
								RegexMenubarCommands.HELP_CONTENTS, 
								RegexMenubarCommands.ABOUT 
						})));
	}
}
