import java.util.HashMap;

public class Environment extends HashMap<Character,String> {
	private static final long serialVersionUID = 1L;
	private Environment parent;
	public Environment() {
		super();
		this.parent = null;
	}
	public String evaluate(char c) {
		String ret = this.get(c);
		if(ret == null) {
			return "undefined";
		}
		return ret ;
	}
	public String get(char c) {
		String got = super.get(c);
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
