package fileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import exception.ExMapExceedWordSize;
import keyValue.Info;

public abstract class ReaderApp {
	abstract ArrayList<Info> convert(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException;
	public ArrayList<Info> getList(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException{
		//break up the function
		ArrayList<Info> res = convert(path, m, n);
		return res;
	}
}
