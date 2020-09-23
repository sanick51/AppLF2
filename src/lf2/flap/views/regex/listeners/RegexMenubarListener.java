
package lf2.flap.views.regex.listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import lf2.flap.models.entity.Automaton;
import lf2.flap.models.entity.RegularExpression;
import lf2.flap.views.Canvas;
import lf2.flap.views.Grapher;
import lf2.flap.views.regex.RegexAboutDialog;
import lf2.flap.views.regex.RegexContentDialog;
import lf2.flap.views.regex.RegexFrame;

public class RegexMenubarListener implements ActionListener {
	private static RegexMenubarListener menubarListener = null;
	private Canvas canvas;
	private RegexAboutDialog about;
	private RegexContentDialog content;
	private RegexFrame frame;
	
	private RegexMenubarListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Automaton a;
		String input;

		switch (RegexMenubarCommands.valueOf(e.getActionCommand())) {
		case GENRT_AUTOM:
			input = JOptionPane.showInputDialog(null, "Introduzca la expresión regular");

			if (input != null)
				this.canvas.setAutomaton(Grapher.toGraph(RegularExpression.toAutomaton(input)));
			break;
		case GENRT_REGEX:
			a = this.canvas.getAutomaton();

			if (a.getInitialState() != null)
				JOptionPane.showMessageDialog(null, RegularExpression.getRegex(a));
			break;

		case ABOUT:
			this.about.setVisible(true);
			break;
		case HELP_CONTENTS:
			File f = new File("resources/help/regex.html");
			try {
				Desktop.getDesktop().browse(f.toURI());
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
			}
			this.content.setVisible(false);
			break;

		case RESET_CANVAS:
			this.canvas.resetAutomaton();
			break;
			
		case RETURN_MAIN:
			this.frame.dispose();
			break;

		default:
			System.out.println("El comando " + e.getActionCommand() + " no tiene función.");
			break;
		}
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public void setAbout(RegexAboutDialog about) {
		this.about = about;
	}
	
	public void setContent(RegexContentDialog content) {
		this.content = content;
	}
	
	public void setFrame(RegexFrame frame) {
		this.frame = frame;
	}

	public static RegexMenubarListener getInstance() {
		if (menubarListener == null)
			menubarListener = new RegexMenubarListener();

		return menubarListener;
	}
}
