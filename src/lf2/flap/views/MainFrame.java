package lf2.flap.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lf2.flap.controllers.Actions;
import lf2.flap.controllers.Controller;
import lf2.flap.views.regex.RegexFrame;

public class MainFrame extends JFrame {
	private Controller controller;
	private JButton AFD;
	private JButton AFND;
	private JButton About;
	private JButton ER;
	private JButton Exit;
	private JButton GAR;
	private JButton RA;
	private JLabel image;
	private JPanel jPanel1;
	private JLabel title;

	public MainFrame(Controller controller) throws FontFormatException, IOException {
		initComponents();
		this.controller = controller;
		this.controller.setAbout(new AboutFrame());
		this.controller.setRegexFrame(new RegexFrame(this));
		File is = new File("./resources/Lato/Lato-Regular.ttf");
		Font Lato = Font.createFont(Font.TRUETYPE_FONT, is);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Lato);
		File f = new File("./resources/img/logo_uptc_negro.png");
		String image = "<html><body><img src=\"file:" + f.toString() + "\"></body></html>";
		String title = "<html>" + "<body>"
				+ "<p style=\"text-align: center; font-size: 65;\">Autómatas y Lenguajes Formales</p>" + "</body>"
				+ "</html>";
		String btn1 = "<html>" + "<body>"
				+ "<p style=\"text-align: center; font-size: 20;\">Autómatas Finitos Deterministas</p>" + "</body>"
				+ "</html>";
		String btn2 = "<html>" + "<body>"
				+ "<p style=\"text-align: center; font-size: 20;\">Autómatas Finitos No Deterministas</p>" + "</body>"
				+ "</html>";
		String btn3 = "<html>" + "<body>"
				+ "<p style=\"text-align: center; font-size: 20;\">Gramáticas y Árboles de Derivación</p>" + "</body>"
				+ "</html>";
		String btn4 = "<html>" + "<body>" + "<p style=\"text-align: center; font-size: 20;\">Reducción de Autómatas</p>"
				+ "</body>" + "</html>";
		String btn5 = "<html>" + "<body>" + "<p style=\"text-align: center; font-size: 20;\">Expresiones Regulares</p>"
				+ "</body>" + "</html>";
		String btn6 = "<html>" + "<body>" + "<p style=\"text-align: center; font-size: 20;\">Acerca de</p>" + "</body>"
				+ "</html>";
		String btn7 = "<html>" + "<body>" + "<p style=\"text-align: center; font-size: 20;\">Salir</p>" + "</body>"
				+ "</html>";
		this.image.setText(image);
		this.title.setText(title);
		this.title.setFont(Lato);
		this.AFD.setFont(Lato);
		this.AFD.setText(btn1);
		this.AFND.setFont(Lato);
		this.AFND.setText(btn2);
		this.GAR.setFont(Lato);
		this.GAR.setText(btn3);
		this.RA.setFont(Lato);
		this.RA.setText(btn4);
		this.ER.setFont(Lato);
		this.ER.setText(btn5);
		this.About.setFont(Lato);
		this.About.setText(btn6);
		this.Exit.setFont(Lato);
		this.Exit.setText(btn7);
		this.getContentPane().setBackground(Color.WHITE);
		actionButtons();
	}

	private void initComponents() {

		jPanel1 = new JPanel();
		AFD = new JButton();
		AFND = new JButton();
		GAR = new JButton();
		RA = new JButton();
		ER = new JButton();
		About = new JButton();
		Exit = new JButton();
		image = new JLabel();
		title = new JLabel();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ALF");
		setBackground(Color.WHITE);
		setLocationByPlatform(true);
		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);

		jPanel1.setBackground(new Color(197, 202, 208));
		jPanel1.setBorder(BorderFactory.createEtchedBorder());

		AFD.setBackground(ViewConstants.mainButtonsBackground);
		AFD.setFont(ViewConstants.mainButtonsFont); // NOI18N
		AFD.setForeground(Color.WHITE);

		AFND.setBackground(ViewConstants.mainButtonsBackground);
		AFND.setFont(ViewConstants.mainButtonsFont); // NOI18N
		AFND.setForeground(Color.WHITE);

		GAR.setBackground(ViewConstants.mainButtonsBackground);
		GAR.setFont(ViewConstants.mainButtonsFont); // NOI18N
		GAR.setForeground(Color.WHITE);

		RA.setBackground(ViewConstants.mainButtonsBackground);
		RA.setFont(ViewConstants.mainButtonsFont); // NOI18N
		RA.setForeground(Color.WHITE);

		ER.setBackground(ViewConstants.mainButtonsBackground);
		ER.setFont(ViewConstants.mainButtonsFont); // NOI18N
		ER.setForeground(Color.WHITE);

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(99, Short.MAX_VALUE).addComponent(RA)
						.addGap(91, 91, 91).addComponent(ER).addGap(196, 196, 196))
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(45, 45, 45).addComponent(AFD).addGap(71, 71, 71)
						.addComponent(AFND).addGap(65, 65, 65).addComponent(GAR)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(60, 60, 60)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(AFND, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(GAR, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(AFD, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGap(50, 50, 50)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(RA, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(ER, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(74, Short.MAX_VALUE)));

		About.setBackground(new Color(119, 211, 83));
		About.setFont(ViewConstants.mainButtonsFont); // NOI18N
		About.setForeground(Color.WHITE);

		Exit.setBackground(new Color(249, 95, 98));
		Exit.setFont(ViewConstants.mainButtonsFont); // NOI18N
		Exit.setForeground(Color.WHITE);

		image.setFont(new Font("Tahoma", 0, 70)); // NOI18N

		title.setFont(new Font("Comic Sans MS", 0, 13)); // NOI18N

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap(103, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(image, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(title, GroupLayout.PREFERRED_SIZE, 909, GroupLayout.PREFERRED_SIZE)
								.addGap(17, 17, 17))
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(About, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addGap(133, 133, 133)
								.addComponent(Exit, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addGap(491, 491, 491))))
				.addGroup(layout.createSequentialGroup().addGap(93, 93, 93).addComponent(jPanel1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap(200, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(image, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(18, 18, 18)
				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(36, 36, 36)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(About, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(Exit, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
				.addGap(34, 34, 34)));

		pack();
		setLocationRelativeTo(null);
	}

	private void actionButtons() {
		this.About.addActionListener(controller);
		this.About.setActionCommand(Actions.ABOUT.toString());
		this.AFD.addActionListener(controller);
		this.AFD.setActionCommand(Actions.AFD.toString());
		this.AFND.addActionListener(controller);
		this.AFND.setActionCommand(Actions.AFND.toString());
		this.GAR.addActionListener(controller);
		this.GAR.setActionCommand(Actions.GAR.toString());
		this.RA.addActionListener(controller);
		this.RA.setActionCommand(Actions.RA.toString());
		this.ER.addActionListener(controller);
		this.ER.setActionCommand(Actions.REGEX.toString());
		this.Exit.addActionListener(controller);
		this.Exit.setActionCommand(Actions.EXIT.toString());
	}
}
