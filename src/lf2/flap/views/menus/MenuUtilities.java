package lf2.flap.views.menus;

import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import lf2.flap.views.listeners.MenuCommander;

public interface MenuUtilities {
	public static JMenu makeMenu(String title, JMenuItem... items) {
		JMenu menu = new JMenu(title);

		for (JMenuItem jMenuItem : items) {
			menu.add(jMenuItem);
		}

		return menu;
	}
	
	public static void makeMenu(JComponent container, JMenuItem... items) {
		for (JMenuItem jMenuItem : items) {
			container.add(jMenuItem);
		}
	}

	public static JMenuItem makeMenuItem(ActionListener l, String text, MenuCommander command) {
		JMenuItem item = new JMenuItem(text);
		item.setActionCommand(command.toString());
		item.addActionListener(l);
		return item;
	}

	public static JMenuItem[] makeMenuItems(ActionListener l, String[] texts, MenuCommander[] commands) {
		if (texts.length != commands.length)
			System.exit(0);

		JMenuItem[] items = new JMenuItem[texts.length];

		for (int i = 0; i < items.length; i++) {
			items[i] = makeMenuItem(l, texts[i], commands[i]);
		}

		return items;
	}
}
