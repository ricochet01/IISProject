package hr.mperhoc.iisproject.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.mperhoc.iisproject.util.FileUtils;
import hr.mperhoc.iisproject.util.HttpUtils;
import hr.mperhoc.iisproject.util.HttpUtils.RequestType;
import hr.mperhoc.iisproject.util.SwingUtils;

public class XMLEntityUploadPanel extends JPanel {
	private static final long serialVersionUID = -5049271050139009329L;
	private static final String URL = "http://localhost:8080/iisproject/api/food/";
	private String postMethod;

	private JButton btnChooseFile, btnSubmit, btnDownload;
	private JTextField tfFilePath;

	private ApplicationWindow window;
	private File file;

	public XMLEntityUploadPanel(ApplicationWindow window, String postMethod) {
		super(new GridBagLayout());
		this.window = window;
		this.postMethod = postMethod;

		initComponents();
	}

	private void initComponents() {
		GridBagConstraints c = new GridBagConstraints();
		Insets i = new Insets(8, 8, 8, 8);
		c.anchor = GridBagConstraints.NORTH;

		// Title
		JLabel lbTitle = new JLabel("Send an XML food entity and verify using " + postMethod.toUpperCase());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipady = 48;
		c.ipadx = 8;
		add(lbTitle, c);

		// Choose file
		btnChooseFile = new JButton("Choose file");
		btnChooseFile.addActionListener(e -> {
			this.file = SwingUtils.chooseFile("XML Food entity file", "xml");
			if (file != null) {
				tfFilePath.setText(file.getAbsolutePath());
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		c.insets = i;
		c.fill = GridBagConstraints.VERTICAL;
		add(btnChooseFile, c);

		tfFilePath = new JTextField(24);
		tfFilePath.setEnabled(false);
		c.gridx = 1;
		c.gridy = 1;
		add(tfFilePath, c);

		// Submit button
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(e -> {
			try {
				String body = Files.readString(file.toPath());
				String[] headers = { "Content-Type", "application/xml", "Authorization",
						"Bearer " + window.getToken() };
				HttpResponse<String> response = HttpUtils.sendRequest(URL + postMethod + "-verify", body,
						RequestType.POST, headers);

				if (response.statusCode() == 201) {
					JOptionPane.showMessageDialog(null, "Successfully uploaded the XML file", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "An error occured with uploading the XML file", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		add(btnSubmit, c);

		// Download button
		btnDownload = new JButton("Download");
		btnDownload.addActionListener(e -> {
			File saveTo = SwingUtils.createNewFile("Save XML entities file", "xml");
			String[] headers = { "Authorization", "Bearer " + window.getToken() };
			HttpResponse<String> response = HttpUtils.sendGetRequest(URL, headers);

			if (response.statusCode() == 200) {
				FileUtils.writeStringToFile(response.body(), saveTo.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Successfully downloaded the XML file", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "An error occured with downloading the XML file", "Error",
						JOptionPane.WARNING_MESSAGE);
			}

		});
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		add(btnDownload, c);
	}
}
