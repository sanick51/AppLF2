package lf2.flap.views;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutFrame extends JFrame {

	private JLabel jLabel2;

	public AboutFrame() throws FontFormatException, IOException {
		initComponents();
		this.getContentPane().setBackground(new java.awt.Color(255, 255, 255));
		File f = new File("./resources/img/logo_sistemas.png");
		File is = new File("./resources/Lato/Lato-Regular.ttf");
		Font Lato = Font.createFont(Font.TRUETYPE_FONT, is);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Lato);
		String title = "<html>" + "<body>" + "<h1 style=\"text-align: center;\">" + ViewConstants.appName + "</h1>"
				+ "<p style=\"text-align: center; font-size: 13;\">Autómatas y Lenguajes Formales<br>Universidad Pedagógica y Tecnológica de Colombia"
				+ "<br><img src=\"file:" + f.toString() + "\">" + "</p>"
				+ "<p marginheight=5 style=\"text-align: justify; font-size: 13;\">Desarrollado por estudiantes de la asignatura Lenguajes Formales Grupo 2 que pertenecen a la escuela de Ingeniería de Sistemas y Computación durante el semestre 2020-I</p>"
				+ "</body>" + "</html>";
		this.jLabel2.setText(title);
		this.jLabel2.setFont(Lato);
	}

	private void initComponents() {

		jLabel2 = new JLabel();

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		jLabel2.setFont(new Font("Tahoma", 0, 14)); // NOI18N

		javax.swing.GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
						.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE).addGap(25, 25, 25)));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addContainerGap().addComponent(jLabel2).addContainerGap(461, Short.MAX_VALUE)));

		pack();
		setLocationRelativeTo(null);
	}
}
