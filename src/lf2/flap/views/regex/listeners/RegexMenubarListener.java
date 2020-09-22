package lf2.flap.views.regex.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lf2.flap.models.entity.Automaton;
import lf2.flap.models.entity.RegularExpression;
import lf2.flap.views.Grapher;
import lf2.flap.views.listeners.MoveListerner;

public class RegexMenubarListener implements ActionListener {
	private static RegexMenubarListener menubarListener = null;

	private RegexMenubarListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Automaton a;
		String input;

		switch (RegexMenubarCommands.valueOf(e.getActionCommand())) {
		case GENRT_AUTOM:
			input=JOptionPane.showInputDialog(null, "Introduzca la expresión regular");
			
			if(input != null)
				MoveListerner.getInstance().getCanvas().setAutomaton(Grapher.toGraph(RegularExpression.toAutomaton(input)));
			break;
		case GENRT_REGEX:
			a = MoveListerner.getInstance().getCanvas().getAutomaton();

			if (a.getInitialState() != null)
				JOptionPane.showMessageDialog(null, RegularExpression.getRegex(a));
			break;

		case ABOUT:
			a = MoveListerner.getInstance().getCanvas().getAutomaton();

			if (a.getInitialState() != null)
				MoveListerner.getInstance().getCanvas().setAutomaton(Grapher.toGraph(RegularExpression.reduce(a)));
			break;
			
		case RESET_CANVAS:
			MoveListerner.getInstance().getCanvas().resetAutomaton();
			break;

		default:
			System.out.println("El comando " + e.getActionCommand() + " no tiene función.");
			break;
		}
	}

	public static RegexMenubarListener getInstance() {
		if (menubarListener == null)
			menubarListener = new RegexMenubarListener();

		return menubarListener;
	}
}
