package hr.mperhoc.iisproject.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.net.http.HttpResponse;
import java.util.Arrays;

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

public class RegisterPanel extends JPanel {
	private static final long serialVersionUID = -1197889881026746142L;

	private JTextField tfEmail, tfUsername;
	private JPasswordField pfPassword, pfConfirmPassword;
	private JButton btnRegister;

	public RegisterPanel(ApplicationWindow window) {
		super(new GridBagLayout());
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints c = new GridBagConstraints();
		Insets i = new Insets(8, 0, 8, 0);
		c.anchor = GridBagConstraints.NORTH;

		// Title
		JLabel lbTitle = new JLabel("Create a new account");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipady = 48;
		c.ipadx = 8;
		add(lbTitle, c);

		// Email
		JLabel lbEmail = new JLabel("Email:");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		c.insets = i;
		add(lbEmail, c);

		tfEmail = new JTextField(24);
		c.gridx = 1;
		c.gridy = 1;
		add(tfEmail, c);

		// Username
		JLabel lbUsername = new JLabel("Username:");
		c.gridx = 0;
		c.gridy = 2;
		add(lbUsername, c);

		tfUsername = new JTextField(24);
		c.gridx = 1;
		c.gridy = 2;
		add(tfUsername, c);

		// Password
		JLabel lbPassword = new JLabel("Password:");
		c.gridx = 0;
		c.gridy = 3;
		add(lbPassword, c);

		pfPassword = new JPasswordField(24);
		c.gridx = 1;
		c.gridy = 3;
		add(pfPassword, c);

		// Confirm password
		JLabel lbConfirmPassword = new JLabel("Confirm password:");
		c.gridx = 0;
		c.gridy = 4;
		add(lbConfirmPassword, c);

		pfConfirmPassword = new JPasswordField(24);
		c.gridx = 1;
		c.gridy = 4;
		add(pfConfirmPassword, c);

		// Register button
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(e -> {
			if (!Arrays.equals(pfPassword.getPassword(), pfConfirmPassword.getPassword())) {
				JOptionPane.showMessageDialog(null, "The passwords must match!", "Error", JOptionPane.WARNING_MESSAGE);
				return;
			}

			try {
				btnRegisterOnClick(e);
			} catch (JsonProcessingException ex) {
				ex.printStackTrace();
			}
		});
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		add(btnRegister, c);
	}

	private void btnRegisterOnClick(ActionEvent e) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		rootNode.put("email", tfEmail.getText());
		rootNode.put("username", tfUsername.getText());
		rootNode.put("password", new String(pfPassword.getPassword()));

		String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);

		HttpResponse<String> response = HttpUtils.sendRequest(
				"http://localhost:8080/iisproject/api/authentication/register", jsonString, RequestType.POST,
				"Content-Type", "application/json");

		if (response.statusCode() == 201) {
			JOptionPane.showMessageDialog(null, "Successfully created the account!", "Success",
					JOptionPane.INFORMATION_MESSAGE);

			tfEmail.setText("");
			tfUsername.setText("");
			pfPassword.setText("");
			pfConfirmPassword.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Error when creating the account!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
