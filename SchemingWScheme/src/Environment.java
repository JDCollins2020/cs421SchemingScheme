import java.util.HashMap;
/**
 * Environment facilitates the evaluation (as a hash map) of ENV 
 * BIT-expressions by storing variables and their values in a hierarchical 
 * fashion 
 * @author  John Collins
 */
public class Environment extends HashMap<Character,String> {
	private static final long serialVersionUID = 1L;
	private Environment parent;
	
	/**
	 * Constructor for Environment, initializes parent to null
	 */
	public Environment() {
		super();
		this.parent = null;
	}
	
	/**
	 * Finds the value associated with a variable
	 * @param c the variable name
	 * @return String the BSL which is the variable's associated value
	 */
	public String evaluate(char c) {
		String ret = this.get(c);
		if(ret == null) {
			return "undefined";
		}
		return ret ;
	}
	
	/**
	 * Searches the chain of parents for a value associated with a variable
	 * @param c the name of the variable
	 * @return	String the BSL value stored for a given variable in the 
	 * hierarchy of Environments
	 */
	private String get(char c) {
		String got = super.get(c);
		if(this.parent != null && got == null) {
			return this.parent.get(c);
		}
		return got;
	}
	
	/**
	 * Sets the parent of an Environment (child)
	 * @param e the parent Environment
	 */
	public void setParent(Environment e) {
		this.parent = e;
	}
	
	/**
	 * Finds the parent of an Environment
	 * @return Environment the parent of this Environment
	 */
	public Environment getParent() {
		if(this.parent != null) {
			return this.parent;
		}
		return null;
	}
}
