package exception;

public class ExMapExceedWordSize extends Exception {

	private static final long serialVersionUID = 1L;
	public ExMapExceedWordSize() {
		super("m*n is bigger than the word list size, please enter another dimension of the map");
	}
}
