package lf2.flap.view;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import lf2.flap.controller.MoveListerner;


public class JPanelToggle extends JPanel {

	private JToggleButton jtbState;
	private JToggleButton jtbTransition;
	private JCheckBox jcbFinal;
	private JCheckBox jcbInictial;
	
	public JPanelToggle(MoveListerner ml) {
		jtbState = new JToggleButton("Estados");
		jtbTransition = new JToggleButton("Transiciones");
		jcbFinal = new JCheckBox("Final");
		jcbInictial = new JCheckBox("Inicial");
		
		jtbState.setActionCommand("Com");
		jtbState.addActionListener(ml);
		
		jcbFinal.setActionCommand("mo");
		jcbFinal.addActionListener(ml);
		
		jcbInictial.setActionCommand("mo2");
		jcbInictial.addActionListener(ml);
		
		this.init();
	}
	
	private void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(jtbState);
		this.add(jtbTransition);
		this.add(jcbFinal);
		this.add(jcbInictial);
	}
	
	public JCheckBox getJcbFinal() {
		return jcbFinal;
	}
	
	public JCheckBox getJcbInictial() {
		return jcbInictial;
	}
	
}
