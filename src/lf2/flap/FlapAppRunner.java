package lf2.flap;

import java.awt.FontFormatException;
import java.io.IOException;

import lf2.flap.controllers.Controller;
import lf2.flap.views.MainFrame;

public class FlapAppRunner {
	public static void main(String[] args) {
		try {
			new MainFrame(new Controller()).setVisible(true);
		} catch (FontFormatException | IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
