package fileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import exception.ExMapExceedWordSize;
import keyValue.Info;

public class ReaderFactory extends ReaderApp{

	@Override
	public ArrayList<Info> convert(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException{
		return TxtReader.convert(path, m, n);
	}
}
