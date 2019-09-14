
public class Schemeterpreter {
	static TokenStream ts;
	static Environment env;
	public static void main(String args[]) {
		ts = new TokenStream(args[0]);
		env = new Environment();
		while(ts.nextStream()) {
			ts.printAll();
			System.out.print("---->");
			System.out.println(evaluate());
		}
	}

	private static String evaluate() {
		char c;
		if( (c = ts.peek(0).charAt(0)) == '(') {
			c = ts.peek(1).charAt(0);
			if( Operations.isTwoArgOp(c) ) {
				ts.step(2);
				//System.out.println(ts.peek(0)+" "+ts.peek(1));
				return Operations.perform(c,evaluate(),evaluate());
			}
			else if(Operations.isOneArgOp(c)) {
				ts.step(2);
				return Operations.perform(c,evaluate());
			}
			else { // is an env
				ts.step(2);
				while(ts.peek(0).charAt(0) != ')') {
					//System.out.print(ts.peek(0));
					//System.out.print(ts.peek(1));
					//System.out.print(ts.step(2));
					ts.step(2);
					//System.out.println(ts.peek(-2).charAt(0));
					env.put(ts.peek(-1).charAt(0),evaluate());
					//System.out.println(ts.peek(-2)+" --> "+env.get(ts.peek(-2).charAt(0)));
					ts.step(1);
				}
				//System.out.println("a --> "+env.get('a'));
				ts.step(1);
				String ret = evaluate();
				env.clear();
				return ret;
			}
		}
		else {
			if(c == '0' || c == '1' || ts.peek(0).compareTo("undefined") == 0) {
				ts.step(1);
				return ts.peek(-1);
			}
			if((c >= 'a' && c <= 'z')) {
				ts.step(1);
				return env.evaluate(c);
			}
		}

		ts.step(1);
		return evaluate();
	}
	
}
