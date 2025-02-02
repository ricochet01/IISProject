package hr.mperhoc.iisproject.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class ApplicationWindow extends JFrame {
	private static final long serialVersionUID = 1742609245013826876L;
	public static final String TITLE = "IIS Project - Client";

	private JTabbedPane tpContent;
	private String token; // Authorization Bearer token

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
		tpContent.add("Login", new LoginPanel(this));
		tpContent.add("REST XSD", new XMLEntityUploadPanel(this, "xsd"));
		tpContent.add("REST RNG", new XMLEntityUploadPanel(this, "rng"));
		tpContent.add("SOAP search", new SOAPSearchPanel(this));
		tpContent.add("XML RPC", new XMLRPCWeatherPanel(this));

		add(tpContent, BorderLayout.NORTH);

		// Disabling the services at the start when the user's not logged in
		toggleServiceTabs(false);
	}

	void setTab(int index) {
		tpContent.setSelectedIndex(index);
	}

	void toggleAuthenticationTabs(boolean toggle) {
		// First two tabs are Register and Login
		tpContent.setEnabledAt(0, toggle);
		tpContent.setEnabledAt(1, toggle);
	}

	void toggleServiceTabs(boolean toggle) {
		for (int i = 2; i < 6; i++) {
			tpContent.setEnabledAt(i, toggle);
		}
	}

	void setToken(String token) {
		this.token = token;
	}

	String getToken() {
		return token;
	}

}
