package gameObject;

public enum WordType {
	PRIMARY,
	IELTS,
	TOEFL,
	GRE;
	
	public static WordType fromIntToWordType(int i) {
		switch(i) {
		case 0:
			return PRIMARY;
		case 1:
			return IELTS;
		case 2:
			return TOEFL;
		case 3:
			return GRE;
		}
		return null;
	}
	
	public static String getWordTypePath(WordType w) {
		switch(w) {
			case PRIMARY:
				return "res/word.txt";
		}
		return null;
	}
}