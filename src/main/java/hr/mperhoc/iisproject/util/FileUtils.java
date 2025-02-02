package hr.mperhoc.iisproject.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class FileUtils {
	private FileUtils() {
	}

	public static boolean fileExists(String path) {
		return new File(path).isFile();
	}

	public static void writeObjectToFile(Object obj, String file) {
		File f = new File(file);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeStringToFile(String str, String file) {
		try (PrintWriter out = new PrintWriter(file)) {
			out.println(str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
