package hr.mperhoc.iisproject.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileUtils {
	private FileUtils() {
	}

	public static boolean fileExists(String path) {
		return new File(path).isFile();
	}

	public static void writeObjectToFile(Object obj, String file) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
