package lf2.flap;

import java.awt.FontFormatException;
import java.io.IOException;

import lf2.flap.controllers.Controller;
import lf2.flap.views.MainFrame;

/**
 * FlapAppRunner - Clase encargada de ejecutar el modulo de Expresiones Regulares
 * @author Felipe Chaparro - Yohan Caro - Fabian Cristancho
 * 22 sep. 2020
 */
public class FlapAppRunner {
	public static void main(String[] args) {
		try {
			new MainFrame(new Controller()).setVisible(true);
		} catch (FontFormatException | IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
