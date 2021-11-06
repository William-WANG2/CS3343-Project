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
	
	//obtain the path for the words resources
	public static String getWordTypePath(WordType w) {
		switch(w) {
			case PRIMARY:
				return "res/words/PRIMARY.xml";
			case IELTS:
				return "res/words/IELTS.xml";
			case TOEFL:
				return "res/words/TOEFL.xml";
			case GRE:
				return "res/words/GRE.xml";
				
		}
		return null;
	}
}