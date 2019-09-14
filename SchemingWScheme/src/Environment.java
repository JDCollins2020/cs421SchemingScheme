import java.util.HashMap;

public class Environment extends HashMap<Character,String> {
	private Environment parent;
	public void Environment() {
		//super is implicitly added
		parent = null;
	}
	public void initEnv(TokenStream ts, Schemeterpreter s) {
		
	}
	public String evaluate(char c) {
		String ret = this.get(c);
		if(ret == null) {
			return "undefined";
		}
		return ret ;
	}
	
}
