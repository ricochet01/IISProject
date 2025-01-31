package hr.mperhoc.iisproject;

import hr.mperhoc.iisproject.xml.XMLUtils;
import hr.mperhoc.iisproject.xml.XMLValidator;
import hr.mperhoc.iisproject.xml.rpc.XMLRPCClient;
import hr.mperhoc.iisproject.xml.rpc.XMLRPCServer;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ServletStartupService implements ServletContextListener {
	private XMLRPCServer xmlRpcServer;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);

		XMLValidator.init();
		XMLUtils.init();

		// Start the XML RPC server
		xmlRpcServer = new XMLRPCServer();
		xmlRpcServer.start();

		XMLRPCClient xmlRpcClient = new XMLRPCClient();
		xmlRpcClient.getTemperature("Rijeka");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		xmlRpcServer.stop();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
