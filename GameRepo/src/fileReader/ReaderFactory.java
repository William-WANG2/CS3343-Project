package fileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import exception.ExMapExceedWordSize;
import keyValue.Info;

public class ReaderFactory extends ReaderApp{

	@Override
	public ArrayList<Info> convert(String path, int m, int n) throws ExMapExceedWordSize, FileNotFoundException {
		String extension = getFileType(path);
		if(extension.equals("txt")) {
			return TxtReader.convert(path, m, n);
		}
		else if (extension.equals("xml")) {
			return XMLReader.convert(path, m, n);
		}
		return null;
	}
	
	public String getFileType(String path) {
		String[] component = path.split("\\.");
		String extension = component[component.length-1];
		return extension;
	}
}
