package fileReader;

import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.Random;

import exception.ExMapExceedWordSize;
import gameObject.WordInfo;

public class ReaderFactory extends ReaderApp{

	@Override
	public ArrayList<WordInfo> convert(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException {
		String extension = getFileType(path);
		ArrayList<WordInfo> wordlist = new ArrayList<WordInfo>();
		ArrayList<WordInfo> res = new ArrayList<WordInfo>();
		if(extension.equals("txt")) {
			wordlist = TxtReader.convert(path);
		}
		else if (extension.equals("xml")) {
			wordlist =  XMLReader.convert(path);
		}
		else {
			throw new FileNotFoundException();
		}
		if (m * n > wordlist.size() + 1) {
			throw new ExMapExceedWordSize();
		}
		int startPosInList = new Random().nextInt(wordlist.size() + 1 - m * n);
		for (int i = startPosInList; i < startPosInList + m * n; i++) {
			res.add(wordlist.get(i));
		}
		return res;
	}
	
	public String getFileType(String path) {
		String[] component = path.split("\\.");
		String extension = component[component.length-1];
		return extension;
	}
}
