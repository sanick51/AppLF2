package lf2.flap.views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JDialojInRegex extends JDialog {

	private JLabel labelText;
	private JTextField fieldRegex;
	private JButton btAccept;
	
	public JDialojInRegex() {
		labelText = new JLabel("Ingrese la regex");
		fieldRegex = new JTextField();
		btAccept = new JButton("Aceptar");
		
		this.init();
	}
	
	private void init() {
		this.setSize(400, 100);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.add(labelText);
		this.add(fieldRegex);
		this.add(btAccept);
		
		this.setVisible(true);
	}
	
	public String getRegex() {
		return fieldRegex.getText();
	}
	
}
