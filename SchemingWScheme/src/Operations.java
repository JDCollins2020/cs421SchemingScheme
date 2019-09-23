
public class Operations {
	
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
	public static String perform(char c,String E1) {
		return perform(c,E1,"");
	}
	public static boolean isTwoArgOp(char c) {
		if(c== '<' || c == '^' || c == '/') {
			return true;
		}
		return false;
	}
	public static boolean isOneArgOp(char c) {
		if(c == '!' || c == '@') {
			return true;
		}
		return false;
	}
	private static String concat(String E1, String E2) {
		if(E1.length()+E2.length() > 2019) {
			 return "undefined";
		 }
		return E1 + E2;
	}
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
	private static String zPad(String x,String E1, String E2) {
		while(x.length() < E1.length() || x.length() < E2.length()) {
			x = "0"+x;
		}	
		return x;
	}
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