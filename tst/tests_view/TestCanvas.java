package tests_view;

import javax.swing.JFrame;

import lf2.flap.views.Canvas;
import lf2.flap.views.ViewConstants;

public class TestCanvas {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test canvas");
		frame.setSize(ViewConstants.defaultSize);
		frame.setLocationRelativeTo(null);
		frame.add(new Canvas());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
