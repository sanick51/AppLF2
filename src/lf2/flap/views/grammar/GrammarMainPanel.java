package lf2.flap.views.grammar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lf2.flap.controllers.Actions;
import lf2.flap.models.entity.NodeTree;
import lf2.flap.models.entity.Production;

public class GrammarMainPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel actual;
	private PanelGrammar grammar;
	private JPanelGrammar jGrammar;
	
	public GrammarMainPanel(ActionListener listener) {
		
		JPanel panelButtons = new JPanel();
		jGrammar = new JPanelGrammar(listener);
		JButton btn = new JButton("Inicio");
		btn.addActionListener(listener);
		btn.setActionCommand(Actions.HOME.toString());
		btn.setBackground(Color.decode("#BE1E4A"));
		btn.setForeground(Color.WHITE);
		panelButtons.add(btn);
		
		JLabel lbl = new JLabel();
		lbl.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		panelButtons.add(lbl);
		
		JButton btnGrammar = new JButton("Gramatica");
		btnGrammar.addActionListener(listener);
		btnGrammar.setActionCommand(Actions.PANEL_GRAMMAR.toString());
		btnGrammar.setBackground(Color.decode("#00A6FF"));
		btnGrammar.setForeground(Color.WHITE);
		panelButtons.add(btnGrammar);
		
		JLabel lbl2 = new JLabel();
		lbl2.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		panelButtons.add(lbl2);
		
		JButton btnValidation = new JButton("Validar lenguaje");
		btnValidation.addActionListener(listener);
		btnValidation.setActionCommand(Actions.VALIDATE_LANGUAJE.toString());
		btnValidation.setBackground(Color.decode("#00A6FF"));
		btnValidation.setForeground(Color.WHITE);
		panelButtons.add(btnValidation);
		
		panelButtons.setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		add(panelButtons , BorderLayout.NORTH);
		setBackground(Color.WHITE);
		
		grammar = new PanelGrammar(listener);
		actual = grammar;
		add(actual, BorderLayout.CENTER);
	}
	
	public void refreshNoTerminal(ArrayList< String > list) {
		grammar.refreshCombo(list);
	}
	
	public void change(NodeTree<String> nodeTree , String rute) {
		jGrammar.change(nodeTree , rute);
	}

	public String getAphabet() {
		return grammar.getaphabet();
	}

	public String getNoTerminal() {
		return grammar.getNoTerminal();
	}

	public Production getProduction() {
		return grammar.getProduction();
	}
	
	public String axiomaticalSimbol() {
		return grammar.axiomaticalSimbol();
	}

	public String validateWord() {
		return jGrammar.getText();
	}

	public void changeToGrammar() {
		remove(actual);
		actual = jGrammar;
		add(actual , BorderLayout.CENTER);
		repaint();
		revalidate();
	}

	public void changeToGrammarPanel() {
		remove(actual);
		actual = grammar;
		add(actual , BorderLayout.CENTER);
		repaint();
		revalidate();
	}
}
