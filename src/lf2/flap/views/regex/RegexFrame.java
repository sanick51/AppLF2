package lf2.flap.views.regex;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import lf2.flap.views.Canvas;
import lf2.flap.views.MainFrame;
import lf2.flap.views.ViewConstants;
import lf2.flap.views.regex.listeners.RegexMenubarListener;


public class RegexFrame extends JFrame {
	private MainFrame mainFrame;
	private RegexMenuBar menuBar;
	private RegexContentDialog content;
	private RegexAboutDialog about;
	private Canvas canvas;
	
	public RegexFrame(MainFrame mf) {
		this.mainFrame = mf;
		this.canvas = new Canvas();
		this.menuBar = new RegexMenuBar();
		this.content = new RegexContentDialog(this);
		this.about = new RegexAboutDialog(this);
		this.init();
	}
	
	private void init() {
		RegexMenubarListener.getInstance().setAbout(this.about);
		RegexMenubarListener.getInstance().setContent(this.content);
		RegexMenubarListener.getInstance().setFrame(this);
		RegexMenubarListener.getInstance().setCanvas(this.canvas);
		this.setTitle(ViewConstants.appName);
		this.setLayout(new BorderLayout());
		this.setSize(ViewConstants.defaultSize);
		this.setMinimumSize(ViewConstants.minimumSize);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setJMenuBar(this.menuBar);
		this.add(canvas, BorderLayout.CENTER);
	}
	
	@Override
	public void dispose() {
		this.mainFrame.setVisible(true);
		super.dispose();
	}
	
	@Override
	public void setVisible(boolean b) {
		this.canvas.resetAutomaton();
		this.mainFrame.dispose();
		super.setVisible(b);
	}
	
}
