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
		//System.out.println(c);
		String ret = this.get(c);
		if(ret == null) {
			return "undefined";
		}
		return ret ;
	}
	public String get(char c) {
		String got = super.get(c);
		//System.out.println("got("+c+") = " + got);
		if(this.parent != null && got == null) {
			return this.parent.get(c);
		}

		return got;
	}
	public void setParent(Environment e) {
		this.parent = e;
	}
	public Environment getParent() {
		if(this.parent != null) {
			return this.parent;
		}
		return null;
	}
}
