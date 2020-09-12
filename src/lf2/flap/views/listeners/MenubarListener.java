package lf2.flap.views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenubarListener implements ActionListener {
	private static MenubarListener menubarListener = null;
	
	private MenubarListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (MenubarCommands.valueOf(e.getActionCommand())) {
		default:
			System.out.println("El comando " + e.getActionCommand() + " no tiene función.");
			break;
		}
	}
	
	public static MenubarListener getInstance() {
		if(menubarListener == null)
			menubarListener = new MenubarListener();
			
		return menubarListener;
	}
}
