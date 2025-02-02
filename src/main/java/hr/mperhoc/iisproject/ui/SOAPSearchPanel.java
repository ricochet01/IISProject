package hr.mperhoc.iisproject.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.http.HttpResponse;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.mperhoc.iisproject.util.HttpUtils;

public class SOAPSearchPanel extends JPanel {
	private static final long serialVersionUID = 4242258268447615530L;
	private static final String URL = "http://localhost:8080/iisproject/soap/foodService";
	private ApplicationWindow window;

	private JTextField tfFoodNameSearchBar;
	private JButton btnSearch;

	private String envelope = "" + "<?xml version=\"1.0\"?>\r\n" + "<soap:Envelope\r\n"
			+ "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"\r\n"
			+ "soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\"\r\n"
			+ "xmlns:ns=\"http://service.resources.iisproject.mperhoc.hr/\">\r\n" + "  <soap:Body>\r\n"
			+ "    <ns:findFoodByName>\r\n" + "      <name>%s</name>\r\n" + "    </ns:findFoodByName>\r\n"
			+ "  </soap:Body>\r\n" + "</soap:Envelope>";

	public SOAPSearchPanel(ApplicationWindow window) {
		super(new GridBagLayout());
		this.window = window;
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints c = new GridBagConstraints();
		Insets i = new Insets(8, 0, 8, 0);
		c.anchor = GridBagConstraints.NORTH;

		// Title
		JLabel lbTitle = new JLabel("Search for food by name using a SOAP web method");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipady = 48;
		c.ipadx = 8;
		add(lbTitle, c);

		// Search bar
		JLabel lbSearchBar = new JLabel("Enter food name:");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		c.insets = i;
		add(lbSearchBar, c);

		tfFoodNameSearchBar = new JTextField(24);
		c.gridx = 1;
		c.gridy = 1;
		add(tfFoodNameSearchBar, c);

		// Search button
		btnSearch = new JButton("Search!");
		btnSearch.addActionListener(e -> {
			HttpResponse<String> result = HttpUtils.callSOAPServiceWebMethod(URL,
					String.format(envelope, tfFoodNameSearchBar.getText()));

			if (result.statusCode() == 200) {
				JOptionPane.showMessageDialog(null, result.body(), "Search result", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "An error occured with the SOAP service!", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		});
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		add(btnSearch, c);
	}
}
