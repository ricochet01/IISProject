package hr.mperhoc.iisproject;

import hr.mperhoc.iisproject.ui.ApplicationWindow;
import hr.mperhoc.iisproject.util.HttpUtils;

public class Main {

	public static void main(String[] args) {
		HttpUtils.init();
		ApplicationWindow window = new ApplicationWindow();
	}

}
