
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
			if((c = ts.peek(1).charAt(0)) == '<' || c == '^' || c == '/' || c == '!' || c == '@') {
				//System.out.println("peek(0) before step(2) = "+ts.peek(0));
				ts.step(2);
				//System.out.println("peek(0) after step(2)= "+ts.peek(0));
				String E1 =  evaluate(ts);
				//System.out.println("peek(0) after eval E1= "+ts.peek(0));
				//System.out.println(E1);
				String E2 = evaluate(ts);
				//System.out.println("peek(0) = "+ts.peek(0));
				//System.out.println(E2);
				return E1 + E2;
			}
		}
		else {
			if(c == '0' || c == '1') {
				ts.step(1);
				return c+"";
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
