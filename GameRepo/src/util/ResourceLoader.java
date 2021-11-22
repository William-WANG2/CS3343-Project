package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceLoader {

	public static InputStream load(Class<?> clazz, String filePath) {
		// try the resource first
		InputStream in = null;
		try {
			in = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			System.out.print("No file");

		}
		return in;
	}
}