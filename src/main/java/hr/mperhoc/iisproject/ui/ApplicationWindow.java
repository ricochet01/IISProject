package hr.mperhoc.iisproject.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ApplicationWindow extends JFrame {
	private static final long serialVersionUID = 1742609245013826876L;
	public static final String TITLE = "IIS Project - Client";

	private JTabbedPane tpContent;

	public ApplicationWindow() {
		super(TITLE);

		initComponents();
		pack();
		setSize(new Dimension(640, 480));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		tpContent = new JTabbedPane();

		tpContent.add("Register", new RegisterPanel(this));
		tpContent.add("Login", new JPanel());
		tpContent.add("REST XSD", new JPanel());
		tpContent.add("REST RNG", new JPanel());
		tpContent.add("SOAP search", new JPanel());
		tpContent.add("XML RPC", new JPanel());

		add(tpContent, BorderLayout.NORTH);

		// Disabling the services at the start when the user's not logged in
		toggleServiceTabs(false);
	}

	void toggleServiceTabs(boolean toggle) {
		for (int i = 2; i < 6; i++) {
			tpContent.setEnabledAt(i, toggle);
		}
	}

}
