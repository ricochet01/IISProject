package hr.mperhoc.iisproject.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.net.http.HttpResponse;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import hr.mperhoc.iisproject.util.HttpUtils;
import hr.mperhoc.iisproject.util.HttpUtils.RequestType;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = -5938144910517871970L;
	private ApplicationWindow window;

	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JButton btnLogin;

	public LoginPanel(ApplicationWindow window) {
		super(new GridBagLayout());
		this.window = window;
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints c = new GridBagConstraints();
		Insets i = new Insets(8, 0, 8, 0);
		c.anchor = GridBagConstraints.NORTH;

		// Title
		JLabel lbTitle = new JLabel("Login to an existing account");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipady = 48;
		c.ipadx = 8;
		add(lbTitle, c);

		// Username
		JLabel lbUsername = new JLabel("Username:");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		c.insets = i;
		add(lbUsername, c);

		tfUsername = new JTextField(24);
		c.gridx = 1;
		c.gridy = 1;
		add(tfUsername, c);

		// Password
		JLabel lbPassword = new JLabel("Password:");
		c.gridx = 0;
		c.gridy = 2;
		add(lbPassword, c);

		pfPassword = new JPasswordField(24);
		c.gridx = 1;
		c.gridy = 2;
		add(pfPassword, c);

		// Login button
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {
			try {
				btnLoginOnClick(e);
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
		});
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		add(btnLogin, c);
	}

	private void btnLoginOnClick(ActionEvent e) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		rootNode.put("username", tfUsername.getText());
		rootNode.put("password", new String(pfPassword.getPassword()));

		String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);

		HttpResponse<String> response = HttpUtils.sendRequest(
				"http://localhost:8080/iisproject/api/authentication/login", jsonString, RequestType.POST,
				"Content-Type", "application/json");

		String token = response.body();

		if (response.statusCode() == 201) {
			window.setToken(token);
			window.toggleServiceTabs(true);
			window.toggleAuthenticationTabs(false);
			window.setTab(2);
			JOptionPane.showMessageDialog(null, "Successfully logged in!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid username or password!", "Error", JOptionPane.WARNING_MESSAGE);
		}

	}

}
