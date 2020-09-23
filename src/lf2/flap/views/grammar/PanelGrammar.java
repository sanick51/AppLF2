package lf2.flap.views.grammar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import lf2.flap.controllers.Actions;
import lf2.flap.models.entity.Production;

public class PanelGrammar extends JPanel{

	private static final long serialVersionUID = 1L;
	private JComboBox<String> combo ;
	private JTextArea txt ;
	private JTextArea txtVar;
	private JTextArea txtProd;
	private JTextArea txtPro2;
	
	public PanelGrammar(ActionListener l) {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		JLabel lbl = new JLabel("Propiedades de la gramatica");
		lbl.setFont(new Font("Lato", Font.PLAIN, 20));
		lbl.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
		lbl.setAlignmentX(CENTER_ALIGNMENT);
		add(lbl);
		
		JPanel pane = new JPanel();
		pane.setBackground(Color.WHITE);
		JLabel lblAxi = new JLabel("Simbolo inicial axiomatico");
		lblAxi.setFont(new Font("Lato", Font.PLAIN, 18));
		pane.add(lblAxi);
		
		combo = new JComboBox<>();
		pane.add(combo);
		pane.setAlignmentX(CENTER_ALIGNMENT);
		add(pane);
		
		JButton btnSimAxi = new JButton("Seleccionar simbolo");
		btnSimAxi.setAlignmentX(CENTER_ALIGNMENT);
		btnSimAxi.setActionCommand(Actions.SELECT_S.toString());
		btnSimAxi.addActionListener(l);
		btnSimAxi.setBackground(Color.decode("#00A6FF"));
		btnSimAxi.setForeground(Color.WHITE);
		add(btnSimAxi);
		
		JLabel lb = new JLabel();
		lb.setBorder(new EmptyBorder(50, 0, 0, 0));
		add(lb);
		
		JPanel panel = new JPanel();
		JLabel lblVar = new JLabel("Variables no terminales");
		lblVar.setFont(new Font("Lato", Font.PLAIN, 18));
		panel.add(lblVar);
		
		txt = new JTextArea();
		txt.setBorder(new LineBorder(Color.black, 2));
		txt.setFont(new Font("Arial", Font.PLAIN, 18));
		txt.setPreferredSize(new Dimension(200, 30));
		panel.add(txt);
		panel.setBackground(Color.WHITE);
		panel.setAlignmentX(CENTER_ALIGNMENT);
		add(panel);
		
		
		JButton btnAdd = new JButton("Añadir variable no terminal");
		btnAdd.setAlignmentX(CENTER_ALIGNMENT);
		btnAdd.setActionCommand(Actions.ADD_V.toString());
		btnAdd.addActionListener(l);
		btnAdd.setBackground(Color.decode("#00A6FF"));
		btnAdd.setForeground(Color.WHITE);
		add(btnAdd);
		
		JLabel lbb = new JLabel();
		lbb.setBorder(new EmptyBorder(50, 0, 0, 0));
		add(lbb);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		JLabel lblVarT = new JLabel("Variables terminales");
		lblVarT.setFont(new Font("Lato", Font.PLAIN, 18));
		panel2.add(lblVarT);
		
		txtVar = new JTextArea();
		txtVar.setBorder(new LineBorder(Color.black, 2));
		txtVar.setFont(new Font("Arial", Font.PLAIN, 18));
		txtVar.setPreferredSize(new Dimension(200, 30));
		panel2.add(txtVar);
		panel2.setAlignmentX(CENTER_ALIGNMENT);
		add(panel2);
		
		JButton btnAddVar = new JButton("Añadir variable terminal");
		btnAddVar.setAlignmentX(CENTER_ALIGNMENT);
		btnAddVar.setActionCommand(Actions.ADD_ALPHABET.toString());
		btnAddVar.addActionListener(l);
		btnAddVar.setBackground(Color.decode("#00A6FF"));
		btnAddVar.setForeground(Color.WHITE);
		add(btnAddVar);
		
		JLabel lbb2 = new JLabel();
		lbb2.setBorder(new EmptyBorder(50, 0, 0, 0));
		add(lbb2);
		
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.WHITE);
		JLabel lblVarPro = new JLabel("Producciones");
		lblVarPro.setFont(new Font("Lato", Font.PLAIN, 18));
		panel3.setAlignmentX(CENTER_ALIGNMENT);
		panel3.add(lblVarPro);
		
		txtProd = new JTextArea();
		txtProd.setFont(new Font("Arial", Font.PLAIN, 18));
		txtProd.setBorder(new LineBorder(Color.black, 2));
		txtProd.setPreferredSize(new Dimension(100, 30));
		panel3.add(txtProd);
		add(panel3);
		
		JLabel lblPro = new JLabel("<html>&rarr;</html>");
		lblPro.setFont(new Font("Lato", Font.PLAIN, 18));
		panel3.add(lblPro);
		
		txtPro2 = new JTextArea();
		txtPro2.setFont(new Font("Arial", Font.PLAIN, 18));
		txtPro2.setBorder(new LineBorder(Color.black, 2));
		txtPro2.setPreferredSize(new Dimension(100, 30));
		panel3.add(txtPro2);
		add(panel3);
		
		JButton btnAddProd = new JButton("Añadir produccion");
		btnAddProd.setAlignmentX(CENTER_ALIGNMENT);
		btnAddProd.addActionListener(l);
		btnAddProd.setActionCommand(Actions.ADD_PRODUCT.toString());
		btnAddProd.setBackground(Color.decode("#00A6FF"));
		btnAddProd.setForeground(Color.WHITE);
		add(btnAddProd);
		
		JLabel lbb3 = new JLabel();
		lbb3.setBorder(new EmptyBorder(50, 0, 0, 0));
		add(lbb3);
	}
	

	public String getNoTerminal() {
		String noTerminal = txt.getText();
		txt.setText("");
		return noTerminal;
	}
	
	public String getaphabet() {
		String aph = txtVar.getText();
		txtVar.setText("");
		return aph;
	}

	public Production getProduction() {
		Production pr = new Production(txtProd.getText(), txtPro2.getText());
		txtProd.setText("");
		txtPro2.setText("");
		return pr;
	}


	public void refreshCombo(ArrayList<String> list) {
		combo.removeAllItems();
		for (String string : list) {
			combo.addItem(string);
		}
		repaint();
		revalidate();
	}
	
	public String axiomaticalSimbol() {
		return (String) combo.getSelectedItem();
	}
}
