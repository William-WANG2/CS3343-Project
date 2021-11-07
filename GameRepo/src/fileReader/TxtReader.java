package fileReader;
import java.util.ArrayList;

import exception.ExMapExceedWordSize;
import gameObject.WordInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class TxtReader {

	public static ArrayList<WordInfo> convert(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException {
		ArrayList<WordInfo> wordlist = new ArrayList<WordInfo>();
		File file = new File(path);
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			WordInfo i = new WordInfo(sc.nextLine(),sc.nextLine());
			wordlist.add(i);
		}
		sc.close();
		return wordlist;
	}

}
