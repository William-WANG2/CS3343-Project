package keyValue;

public class WordInfo extends Info{
	
	public WordInfo(String d, String a) {
		this.defin=d;
		this.ans=a;
	}
	

	@Override
	public String getDefin() {
		return defin;
	}

	@Override
	public String getAns() {
		return ans;
	}
}
