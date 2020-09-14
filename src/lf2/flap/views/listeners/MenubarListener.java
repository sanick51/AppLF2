package lf2.flap.views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lf2.flap.models.entity.Automaton;
import lf2.flap.models.entity.RegularExpression;

public class MenubarListener implements ActionListener {
	private static MenubarListener menubarListener = null;

	private MenubarListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Automaton a;

		switch (MenubarCommands.valueOf(e.getActionCommand())) {
		case GENRT_AUTOM:
			JOptionPane.showInputDialog(null, "Introduzca la expresión regular");
			break;
		case GENRT_REGEX:
			a = MoveListerner.getInstance().getCanvas().getAutomaton();

			if (a.getInitialState() != null)
				JOptionPane.showMessageDialog(null, RegularExpression.getRegex(a));
			break;

		case ABOUT:
			a = MoveListerner.getInstance().getCanvas().getAutomaton();

			if (a.getInitialState() != null)
				MoveListerner.getInstance().getCanvas().setAutomaton(RegularExpression.reduce(a));
			break;

		default:
			System.out.println("El comando " + e.getActionCommand() + " no tiene función.");
			break;
		}
	}

	public static MenubarListener getInstance() {
		if (menubarListener == null)
			menubarListener = new MenubarListener();

		return menubarListener;
	}
}
