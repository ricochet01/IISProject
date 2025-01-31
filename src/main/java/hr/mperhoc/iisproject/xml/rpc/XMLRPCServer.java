package hr.mperhoc.iisproject.xml.rpc;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class XMLRPCServer {
	public static final int PORT = 8086;
	private WebServer webServer;

	public void start() {
		try {
			webServer = new WebServer(PORT);

			XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
			PropertyHandlerMapping phm = new PropertyHandlerMapping();

			// Add a handler that maps "Weather" to the Weather class
			phm.addHandler("Weather", Weather.class);

			xmlRpcServer.setHandlerMapping(phm);

			// Configure the XML-RPC server
			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
			serverConfig.setEnabledForExtensions(true);
			serverConfig.setContentLengthOptional(false);

			// Start the server
			webServer.start();
			System.out.println("XML-RPC Server started successfully on port " + PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		webServer.shutdown();
	}
}
