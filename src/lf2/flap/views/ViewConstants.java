package lf2.flap.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public interface ViewConstants {
	public static final String appName = "FLAP LF";
	
	public static final Dimension defaultSize= new Dimension(800, 600);
	public static final Dimension minimumSize= new Dimension(400, 300);
	
	
	public static final Color stateColor= Color.YELLOW;
	public static final Color selectedStateColor= Color.CYAN;
	
	public static final Font emptyFont = new Font("Serif", Font.BOLD, 16);
	public static final String emptySymbol ="λ";
	
	public static final Font mainButtonsFont = new Font("Tahoma", 0, 20);
	public static final Color mainButtonsBackground= new Color(0, 166, 255);
	
}
