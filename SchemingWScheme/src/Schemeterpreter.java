
public class Schemeterpreter {
	public static void main(String args[]) {
		TokenStream ts = new TokenStream(args[0]);
		while(ts.nextStream()) {
			ts.printAll();
			System.out.print("---->");
			System.out.println(evaluate(ts));
		}
	}

	private static String evaluate(TokenStream ts) {
		char c;
		while(ts.peek(0).charAt(0) == ')') {
			ts.step(1);
		}
		
		if( (c = ts.peek(0).charAt(0)) == '(') {
			c = ts.peek(1).charAt(0);
			if( Operations.isTwoArgOp(c) ) {
				ts.step(2);
				return Operations.perform(c,evaluate(ts),evaluate(ts));
			}
			else if(Operations.isOneArgOp(c)) {
				ts.step(2);
				return Operations.perform(c,evaluate(ts));
			}
		}
		else {
			if(c == '0' || c == '1') {
				String ret = ts.peek(0);
				ts.step(1);
				return ret;
			}
			if(ts.peek(0).compareTo("undefined") == 0) {
				//ts.step(1);
				return ts.peek(0);
			}
			if((c >= 'a' && c <= 'z')) {
				ts.step(1);
				return ""+c;
			}
		}

		
		return "";
	}
	
}
