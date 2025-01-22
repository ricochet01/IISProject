package hr.mperhoc.iisproject;

import hr.mperhoc.iisproject.xml.XMLValidator;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ServletStartupService implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);

		XMLValidator.init();
	}
}
