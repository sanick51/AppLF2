package lf2.flap.views.grammar;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import lf2.flap.controllers.Actions;
import lf2.flap.models.entity.NodeTree;

import javax.swing.JButton;
import java.awt.Color;

public class JPanelGrammar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private PanelFullTree tree;
	
	public JPanelGrammar(ActionListener listener) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(12);
		
		JButton btnNewButton = new JButton("Validar");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setActionCommand(Actions.VALIDATE.toString());
		btnNewButton.addActionListener(listener);
		panel.add(btnNewButton);
		add(panel, BorderLayout.NORTH);	

		tree = new PanelFullTree(null);
		add(tree , BorderLayout.CENTER);
	}

	public String getText() {
		return textField.getText();
	}

	public void change(NodeTree<String> nodeTree , String rute) {
		tree.refreshTree(nodeTree , rute);
		repaint();
		revalidate();
	}

}
