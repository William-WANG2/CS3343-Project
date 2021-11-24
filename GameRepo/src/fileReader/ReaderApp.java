package fileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import exception.ExMapExceedWordSize;
import gameObject.WordInfo;

public class ReaderApp {
	public ArrayList<WordInfo> getList(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException{
		FileMaker mk = new FileMaker();
		ArrayList<WordInfo> wordlist = mk.convert(path);
		ArrayList<WordInfo> res = new ArrayList<WordInfo>();
		if (m * n > wordlist.size() + 1) {
			throw new ExMapExceedWordSize();
		}
		int startPosInList = new Random().nextInt(wordlist.size() + 1 - m * n);
		for (int i = startPosInList; i < startPosInList + m * n; i++) {
			res.add(wordlist.get(i));
		}
		return res;
	}
}
