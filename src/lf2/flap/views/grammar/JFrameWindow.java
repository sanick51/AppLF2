package lf2.flap.views.grammar;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

import lf2.flap.models.entity.NodeTree;
import lf2.flap.models.entity.Production;
import lf2.flap.views.MainFrame;

public class JFrameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private GrammarMainPanel mainPanel;
	private MainFrame mainFrame;
	
	public JFrameWindow(ActionListener listener , MainFrame mf) {
		this.mainFrame = mf;
		mainPanel = new GrammarMainPanel(listener);
		setTitle("Gramatica y arboles de derivacion");
		add(mainPanel);
		setSize(800, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void dispose() {
		this.mainFrame.setVisible(true);
		super.dispose();
	}
	
	public void changeToGrammar() {
		mainPanel.changeToGrammar();
	}

	public String getAlphabet() {
		return mainPanel.getAphabet();
	}

	
	public void refreshCombo(ArrayList<String> list) {
		mainPanel.refreshNoTerminal(list);
	}

	public Production getProduct() {
		return mainPanel.getProduction();
	}

	public String getV() {
		return mainPanel.getNoTerminal();
	}

	public String axiomati() {
		return mainPanel.axiomaticalSimbol();
	}
	
	public String validateWord() {
		return mainPanel.validateWord();
	}

	public void changeToGrammarPanel() {
		mainPanel.changeToGrammarPanel();
	}
	
	public void change(NodeTree<String> nodeTree , String rute) {
		mainPanel.change(nodeTree , rute);
		repaint();
		revalidate();
	}
}
