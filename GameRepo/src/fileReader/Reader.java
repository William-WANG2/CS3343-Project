package fileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import gameObject.WordInfo;

public interface Reader {
	public abstract ArrayList<WordInfo> convert(String path) throws FileNotFoundException;
}
