package fileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import exception.ExMapExceedWordSize;
import gameObject.WordInfo;

public abstract class ReaderApp {
	abstract ArrayList<WordInfo> convert(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException;
	public ArrayList<WordInfo> getList(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException{
		//break up the function
		ArrayList<WordInfo> res = convert(path, m, n);
		return res;
	}
}
