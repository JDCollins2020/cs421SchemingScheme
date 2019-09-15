
public class Schemeterpreter {
	static TokenStream ts;
	static Environment env;
	public static void main(String args[]) {
		ts = new TokenStream(args[0]);
		while(ts.nextStream()) {
			//ts.print("---->");
			System.out.println(evaluate());
		}
	}

	private static String evaluate() {
		char c;
		if( (c = ts.peek(0).charAt(0)) == '(') {
			c = ts.peek(1).charAt(0);
			ts.step(2);
			if( Operations.isTwoArgOp(c) ) {
				return Operations.perform(c,evaluate(),evaluate());
			}
			else if(Operations.isOneArgOp(c)) {
				return Operations.perform(c,evaluate());
			}
			else { // is an env
				//always have main env available
				// or redeclare/add 
				if(env == null || env.isEmpty()) {
					//System.out.println("\nmake a new env");
					env = new Environment();
				}
				else {
					//System.out.println("\nmake a child env");
					Environment temp = new Environment();
					temp.setParent(env);
					env = temp;
				}
				while(ts.peek(0).charAt(0) != ')') {
					ts.step(2);
					env.put(ts.peek(-1).charAt(0),evaluate());
					//System.out.println(ts.peek(-2)+" --> "+env.get(ts.peek(-2).charAt(0)));
					ts.step(1);
				}
				ts.step(1);
				String ret = evaluate();
				//System.out.println("------");
				env.clear();
				env = env.getParent();
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
				if(env != null) {
					return env.evaluate(c);
				}
				return "undefined";
			}
		}

		ts.step(1);
		return evaluate();
	}
	
}
