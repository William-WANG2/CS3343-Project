package gameObject;

public enum EnumVocabularyBook {
	PRIMARY,
	IELTS,
	TOFEL,
	GRE;
	
	public static EnumVocabularyBook IntToWordType(int i) {
		switch(i) {
		case 0:
			return PRIMARY;
		case 1:
			return IELTS;
		case 2:
			return TOFEL;
		case 3:
			return GRE;
		default:
			return null;
		}
	}
	
	//obtain the path for the words resources
	public static String getWordTypePath(EnumVocabularyBook b) {
		switch(b) {
			case PRIMARY:
				return "res/words/PRIMARY.xml";
			case IELTS:
				return "res/words/IELTS.xml";
			case TOFEL:
				return "res/words/TOFEL.xml";
			case GRE:
				return "res/words/GRE.xml";
			default:
				return null;
		}
	}
}