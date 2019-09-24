/**
 * BitStrings is a Bit-Expression interpreter, which evaluates each line of a 
 * BITS file and prints the results to the console
 * @author  John Collins
 */
public class BitStrings {
	private static TokenStream ts;
	private static Environment env;
	/**
	 * The main method, which runs the evaluation loop over every token stream
	 * @param args the BITS file name
	 */
	public static void main(String args[]) {
		ts = new TokenStream(args[0]);
		while(ts.nextStream()) {
			System.out.println(evaluate());
		}
	}
	
	/**
	 * Evaluates a BIT-expression recursively 
	 * @return String the BSL result of a given Bit-expression
	 */
	private static String evaluate() {
		char c;
		if( (c = ts.peek(0).charAt(0)) == '(') {
			c = ts.peek(1).charAt(0);
			ts.step(2);
			if( Operations.isTwoArgOp(c) ) {
				return twoArgOp(c);
			}
			else if(Operations.isOneArgOp(c)) {
				return oneArgOp(c);
			}
			else { 
				evalDecl();
				return evalEnvExp();
			}
		}
		else {
			if(isLiteral(c)) {
				return ts.peek(-1);
			}
			if(isId(c)) {
				if(env != null) {
					return env.evaluate(c);
				}
				return "undefined";
			}
		}
		ts.step(1);
		return evaluate();
	}

	/**
	 * Determines if the token beginning with c is a BSL
	 * @param c the first character of a token
	 * @return boolean True if token which starts with c is a BSL, false 
	 * otherwise
	 */
	private static boolean isLiteral(char c) {
		if(c == '0' || c == '1' || ts.peek(0).compareTo("undefined") == 0) {
			ts.step(1);
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if the token beginning with c is a variable
	 * @param c the first character of a token
	 * @return boolean True if token which starts with c is a variable, false 
	 * otherwise
	 */
	private static boolean isId(char c) {
		if((c >= 'a' && c <= 'z')) {
			ts.step(1);
			return true;
		}
		return false;
	}
	
	/**
	 * evaluates an operation Bit-expression with two arguments
	 * @param c the first character of the operator token
	 * @return String the result of the operation Bit-expression
	 */
	private static String twoArgOp(char c) {
		String str = Operations.perform(c,evaluate(),evaluate());
		ts.step(1);
		return str;
	}

	/**
	 * evaluates an operation Bit-expression with one argument
	 * @param c the first character of the operator token
	 * @return String the result of the operation Bit-expression
	 */
	private static String oneArgOp(char c) {
		String str = Operations.perform(c,evaluate());
		ts.step(1);
		return str;
	}
	
	/**
	 * Evaluates all declarations in an Environment Bit-expression and 
	 * initializes the Environment upon completion
	 */
	private static void evalDecl() {
	    Environment temp = new Environment();
	    while(ts.peek(0).charAt(0) == '(' && ts.peek(1).charAt(0) >= 'a' && ts.peek(1).charAt(0) <= 'z') {
			ts.step(2);
			temp.put(ts.peek(-1).charAt(0),evaluate());
			ts.step(1);
			while(ts.peek(0).charAt(0) == ')') {
				ts.step(1);
			}
		}
	    if(env == null ) {
	    	env = temp;
	    }
	    else {
	    	temp.setParent(env);
	    	env = temp;
	    }    
	}
	
	/**
	 * Evaluates the expression E1 in the context of the Environment in the 
	 * BIT-expression of the form ( ENV E1)
	 * @return String the BSL result of the Bit-expression
	 */
	private static String evalEnvExp() {
		String ret = evaluate();
		env.clear();
		env = env.getParent();
		ts.step(1);
		return ret;
	}
}
