package fileReader;
import java.util.ArrayList;

import gameObject.WordInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class TxtReader implements Reader{

	public ArrayList<WordInfo> convert(String path) throws FileNotFoundException {
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
