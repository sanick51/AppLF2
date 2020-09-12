package lf2.flap.views.menus;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lf2.flap.views.listeners.MenubarCommands;
import lf2.flap.views.listeners.MenubarListener;

public class MainMenuBar extends JMenuBar {

	public MainMenuBar() {
		this.init();
	}

	private void init() {
		this.add(this.makeMenu("Ayuda", makeMenuItems(MenubarListener.getInstance(), new String[] { "Contenido", "Acerca de" },
				new MenubarCommands[] { MenubarCommands.HELP_CONTENTS, MenubarCommands.ABOUT })));
	}

	private JMenu makeMenu(String title, JMenuItem... items) {
		JMenu menu = new JMenu(title);

		for (JMenuItem jMenuItem : items) {
			menu.add(jMenuItem);
		}

		return menu;
	}

	private JMenuItem makeMenuItem(ActionListener l, String text, MenubarCommands command) {
		JMenuItem item = new JMenuItem(text);
		item.setActionCommand(command.toString());
		item.addActionListener(l);
		return item;
	}

	private JMenuItem[] makeMenuItems(ActionListener l, String[] texts, MenubarCommands[] commands) {
		if (texts.length != commands.length)
			System.exit(0);

		JMenuItem[] items = new JMenuItem[texts.length];

		for (int i = 0; i < items.length; i++) {
			items[i] = makeMenuItem(l, texts[i], commands[i]);
		}

		return items;
	}
}
