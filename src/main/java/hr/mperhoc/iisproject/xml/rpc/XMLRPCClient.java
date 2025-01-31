package hr.mperhoc.iisproject.xml.rpc;

import java.net.URI;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class XMLRPCClient {

	public double getTemperature(String city) {
		try {
			// Configure the client
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URI("http://localhost:8086/xmlrpc").toURL());

			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);

			// Make a remote procedure call
			Object[] params = new Object[] { city };
			double result = (double) client.execute("Weather.getTemperature", params);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Double.MIN_VALUE;
		}
	}

}
