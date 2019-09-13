
public class Operations {
	static public String perform(char c, String E1, String E2) {
		
		switch(c) {
			case '<':{
				return E1 + E2;
			}
			case '/':{
				return zip( zPad(E1,E2,E2),zPad(E2,E1,E1));
			}
			case '^':{
				return xor(E1,E2);
			}
			case '@':{
				return rotate(E1);
			}
			case '!':{
				return negate(E1);
			}
			default:{}
		
		}
		return "";
	}
	
	public static String perform(char c,String E1) {
		return perform(c,E1,null);
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
	private static String xor(String E1,String E2) {
		return zPad( ""+(Integer.parseInt(E1)^Integer.parseInt(E2)),E1,E2);
	}
	private static String zip(String E1,String E2){
		String zipped = "";
		for(int ndx = 0; ndx < E2.length(); ndx++) {
			zipped = zipped+E1.charAt(ndx)+E2.charAt(ndx);
		}
		return zipped;
	}
	
	private static String zPad(String x,String E1, String E2) {
		while(x.length() < E1.length() || x.length() < E2.length()) {
			x = "0"+x;
		}
		return x;
	}

	private static String negate(String E1) {
		String str = "";
		for(int ndx = 0; ndx < E1.length();ndx++) {
			str = str+ (~Integer.parseInt(E1.charAt(ndx)+"") +2);
		}
		return str;
	}
	
	private static String rotate(String E1) {
		String str = "";
		if(E1.length()%2 != 0) {
			E1 = "0"+E1;
		}
		for(int ndx = 0; ndx < E1.length(); ndx += 2) {
			str = str + E1.charAt(ndx+1) + "" +E1.charAt(ndx);
		}
		
		return str;
	}
}
