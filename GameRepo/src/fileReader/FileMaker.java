package fileReader;

import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.Random;

import exception.ExMapExceedWordSize;
import gameObject.WordInfo;

public class FileMaker{

	private Reader txt = new TxtReader();
	private Reader xml = new XMLReader();
	public ArrayList<WordInfo> convert(String path) throws FileNotFoundException {
		String extension = getFileType(path);
		ArrayList<WordInfo> wordlist = new ArrayList<WordInfo>();
		if(extension.equals("txt")) {
			wordlist = txt.convert(path);
		}
		else if (extension.equals("xml")) {
			wordlist =  xml.convert(path);
		}
		else {
			throw new FileNotFoundException();
		}
		return wordlist;
	}
	
	public String getFileType(String path) {
		String[] component = path.split("\\.");
		String extension = component[component.length-1];
		return extension;
	}
}
