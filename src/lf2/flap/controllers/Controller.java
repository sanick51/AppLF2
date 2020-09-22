package lf2.flap.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lf2.flap.views.AboutFrame;
import lf2.flap.views.regex.RegexFrame;

public class Controller implements ActionListener {
	private AboutFrame about;
	private RegexFrame regexFrame;

	public Controller() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ABOUT:
			about.setVisible(true);
			break;
		case AFD:
			break;
		case REGEX:
			regexFrame.setVisible(true);
			break;
		case RA:
			break;
		case GAR:
			break;
		case AFND:
			break;
		case EXIT:
			System.exit(0);
			break;
		default:
			break;
		}

	}
	
	public void setAbout(AboutFrame about) {
		this.about = about;
	}
	
	public void setRegexFrame(RegexFrame regexFrame) {
		this.regexFrame = regexFrame;
	}
}
