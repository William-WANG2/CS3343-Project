package fileReader;
import java.util.ArrayList;

import exception.ExMapExceedWordSize;
import keyValue.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class TxtReader {

	public static ArrayList<Info> convert(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException {
		ArrayList<Info> wordlist = new ArrayList<Info>();
		File file = new File(path);
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			Info i = new WordInfo(sc.nextLine(),sc.nextLine());
			wordlist.add(i);
		}
		sc.close();
		return wordlist;
	}

}
