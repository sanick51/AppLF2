package lf2.flap.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lf2.flap.models.entity.Grammar;
import lf2.flap.views.AboutFrame;
import lf2.flap.views.MainFrame;
import lf2.flap.views.grammar.JFrameWindow;
import lf2.flap.views.regex.RegexFrame;

public class Controller implements ActionListener {
	private AboutFrame about;
	private RegexFrame regexFrame;
	private MainFrame MainFrame;
	private JFrameWindow frameGramar;
	private Grammar grammar;

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
		case AFND:
			break;
		case GAR:
			MainFrame.dispose();
			grammar = new Grammar();
			frameGramar = new JFrameWindow(this, MainFrame);
			frameGramar.setVisible(true);
			break;
		case ADD_ALPHABET:
			addAlphabet();
			break;
		case ADD_PRODUCT:
			addProduction();
			break;
		case HOME:
			this.frameGramar.dispose();
			MainFrame.setVisible(true);
			break;
		case ADD_V:
			addV();
			break;
		case VALIDATE_LANGUAJE:
			frameGramar.changeToGrammar();
			break;
		case SELECT_S:
			addS();
			break;
		case PANEL_GRAMMAR:
			frameGramar.changeToGrammarPanel();
			break;
		case VALIDATE:
			validate();
			break;
		case EXIT:
			System.exit(0);
			break;
		default:
			break;
		}

	}

	private void validate() {
		grammar.validate(frameGramar.validateWord());
		frameGramar.change(grammar.getnArio().getRoot(), grammar.getTreeDerivate());
		if (grammar.getTreeDerivate().equals("No pertenece al lenguaje")) {
			JOptionPane.showMessageDialog(null, "El lenguaje no pertenece");
		}
		grammar.setTreeDerivate("");
	}

	private void addAlphabet() {
		String alphabet = frameGramar.getAlphabet();
		boolean validator = true;
		for (int i = 0; i < grammar.getAlphabet().size(); i++) {
			if (alphabet.equals(grammar.getAlphabet().get(i))) {
				validator = false;
			}
		}
		for (int i = 0; i < grammar.getNonterminalVariables().size(); i++) {
			if (alphabet.equals(grammar.getNonterminalVariables().get(i))) {
				validator = false;
			}
		}
		if (validator) {
			grammar.getAlphabet().add(alphabet);
		} else {
			System.out.println("Ya existe");
		}
	}

	private void addS() {
		grammar.setInitialAxiomaticSymbol(frameGramar.axiomati());
	}

	private void addV() {
		String v = frameGramar.getV();
		boolean validator = true;
		for (int i = 0; i < grammar.getNonterminalVariables().size(); i++) {
			if (v.equals(grammar.getNonterminalVariables().get(i))) {
				validator = false;
			}
		}
		for (int i = 0; i < grammar.getAlphabet().size(); i++) {
			if (v.equals(grammar.getAlphabet().get(i))) {
				validator = false;
			}
		}
		if (validator) {
			grammar.getNonterminalVariables().add(v);
		} else {
			System.out.println("Ya existe");
		}

		frameGramar.refreshCombo(grammar.getNonterminalVariables());

	}

	private void addProduction() {
		grammar.getProductions().add(frameGramar.getProduct());
	}

	public void setAbout(AboutFrame about) {
		this.about = about;
	}

	public void MainAppFrame(MainFrame mainFrame) {
		this.MainFrame = mainFrame;
	}

	public void setRegexFrame(RegexFrame regexFrame) {
		this.regexFrame = regexFrame;
	}
}
