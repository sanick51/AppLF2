package lf2.flap.views.regex;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RegexAboutDialog extends JDialog {
	public RegexAboutDialog(JFrame main) {
		super(main);
		this.init();
	}

	private void init() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(6, 1));
		p.add(new JLabel("Expresiones regulares"));
		p.add(new JLabel());
		p.add(new JLabel("Implementado por:"));
		p.add(new JLabel("Yohan Eduardo Caro Pongutá"));
		p.add(new JLabel("Andrés Felipe Chaparro Rosas"));
		p.add(new JLabel("Fabian Alejandro Cristancho Rincón"));
		
		
		this.add(p);
		this.setSize(320, 160);
		this.setLocationRelativeTo(null);
	}
}
