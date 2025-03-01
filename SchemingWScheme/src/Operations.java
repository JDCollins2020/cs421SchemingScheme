/**
 * Operations performs the <>, !, @, ^, // on BSL(s), always returning another
 * BSL
 * @author John Collins
 */
public class Operations {
	/**
	 * Determines which operation has been specified and performs it using 
	 * E1 and E2
	 * @param c the operator of the BIT-expression
	 * @param E1 the first argument of the BIT-expression, a BSL
	 * @param E2 the second argument of the BIT-expression, a BSL
	 * @return String the BSL result of the BIT-expression
	 */
	static public String perform(char c, String E1, String E2) {
		if( E1.compareTo("undefined") == 0 || E2.compareTo("undefined") == 0) {
			return "undefined";
		}
		switch(c) {
			case '<':{
				 return concat(E1,E2);
			}
			case '/':{
				return zip( zPad(E1,E2,E2),zPad(E2,E1,E1));
			}
			case '^':{
				return xor(zPad(E1,E2,E2),zPad(E2,E1,E1));
			}
			case '@':{
				return rotate(E1);
			}
			case '!':{
				return negate(E1);
			}
			default:{
				return "";
			}		
		}
	}
	
	/**
	 * Calls perform for an operation which takes only one argument
	 * @param c the operator of the BIT-expression
	 * @param E1 the argument of the BIT-expression, a BSL
	 * @return String the BSL result of the BIT-expression
	 */
	public static String perform(char c,String E1) {
		return perform(c,E1,"");
	}
	
	/**
	 * Specifies if an expression is an operation with two arguments
	 * @param c the operator of the BIT-expression
	 * @return boolean true if c is a two argument operator, false otherwise
	 */
	public static boolean isTwoArgOp(char c) {
		if(c== '<' || c == '^' || c == '/') {
			return true;
		}
		return false;
	}
	
	/**
	 * Specifies if an expression is an operation with one argument
	 * @param c the operator of the BIT-expression
	 * @return boolean true if c is a one argument operator, false otherwise
	 */
	public static boolean isOneArgOp(char c) {
		if(c == '!' || c == '@') {
			return true;
		}
		return false;
	}
	
	/**
	 * Concatenates E1 and E2
	 * @param E1 the first argument of the BIT-expression, a BSL
	 * @param E2 the second argument of the BIT-expression, a BSL
	 * @return String the BSL result of the BIT-expression
	 */
	private static String concat(String E1, String E2) {
		if(E1.length()+E2.length() > 2019) {
			 return "undefined";
		 }
		return E1 + E2;
	}
	
	/**
	 * Exclusive or's E1 and E2
	 * @param E1 the first argument of the BIT-expression, a BSL
	 * @param E2 the second argument of the BIT-expression, a BSL
	 * @return String the BSL result of the BIT-expression
	 */
	private static String xor(String E1,String E2) {
		String ret = "";
		 if( E1.length() > 2019 || E2.length() > 2019) {
			 return "undefined";
		 }
		for(int ndx = 0; ndx < E1.length(); ndx++) {
			if(E1.charAt(ndx) == '1' && E2.charAt(ndx) == '0' ) {
				ret = ret + "1";
			}
			else if(E1.charAt(ndx) == '0' && E2.charAt(ndx) == '1' ) {
				ret = ret + "1";
			}
			else {
				ret = ret + "0";
			}
		}
		return ret;
	}
	/**
	 * Interleaves the digits in E1 and E2
	 * @param E1 the first argument of the BIT-expression, a BSL
	 * @param E2 the second argument of the BIT-expression, a BSL
	 * @return String the BSL result of the BIT-expression
	 */
	private static String zip(String E1,String E2){
		String ret = "";
		if( E1.length() + E2.length() > 2019 ) {
			 return "undefined";
		 }
		for(int ndx = 0; ndx < E2.length(); ndx++) {
			ret = ret+E1.charAt(ndx)+E2.charAt(ndx);
		}
		return ret;
	}
	
	/**
	 * Zero-pads a BSL from the left to match the length of the longest BSL 
	 * E1 or E2
	 * @param x BSL to be padded
	 * @param E1 first BSL to match the length of
	 * @param E2 second BSL to match the length of 
	 * @return String the zero-padded BSL 
	 */
	private static String zPad(String x,String E1, String E2) {
		while(x.length() < E1.length() || x.length() < E2.length()) {
			x = "0"+x;
		}	
		return x;
	}
	
	/**
	 * Negates E1
	 * @param E1 the first argument of the BIT-expression, a BSL
	 * @return String the BSL result of the BIT-expression
	 */
	private static String negate(String E1) {
		String ret = "";
		if( E1.length() > 2019) {
			 return "undefined";
		 }
		for(int ndx = 0; ndx < E1.length();ndx++) {
			ret = ret+ (~Long.parseLong(E1.charAt(ndx)+"") +2);
		}	
		return ret;
	}
	
	/**
	 * Swaps consecutive pairs of digits in E1
	 * @param E1 the first argument of the BIT-expression, a BSL
	 * @return String the BSL result of the BIT-expression
	 */
	private static String rotate(String E1) {
		String ret = "";
		if( E1.length() > 2019) {
			 return "undefined";
		 }
		if(E1.length()%2 != 0) {
			E1 = "0"+E1;
		}
		for(int ndx = 0; ndx < E1.length(); ndx += 2) {
			ret = ret + E1.charAt(ndx+1) + "" +E1.charAt(ndx);
		}
		return ret;
	}
}