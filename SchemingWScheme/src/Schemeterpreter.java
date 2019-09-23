
public class Schemeterpreter {
	static TokenStream ts;
	static Environment env;
	
	public static void main(String args[]) {
		ts = new TokenStream(args[0]);
		while(ts.nextStream()) {
			ts.print("---->");
			System.out.println(evaluate());
		}
	}
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
				initEnvironment();
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
	private static boolean isLiteral(char c) {
		if(c == '0' || c == '1' || ts.peek(0).compareTo("undefined") == 0) {
			ts.step(1);
			return true;
		}
		return false;
	}
	private static boolean isId(char c) {
		if((c >= 'a' && c <= 'z')) {
			ts.step(1);
			return true;
		}
		return false;
	}
	private static String twoArgOp(char c) {
		String str = Operations.perform(c,evaluate(),evaluate());
		//System.out.println(c+" close two arg op ---> "+str);
		ts.step(1);
		return str;
	}
	private static String oneArgOp(char c) {
		String str = Operations.perform(c,evaluate());
		//System.out.println(c+" close one arg op ---> "+str);
		ts.step(1);
		return str;
	}
	private static void initEnvironment() {
		if(env == null ) {
			env = new Environment();
		}
		else {
			Environment temp = new Environment();
			temp.setParent(env);
			env = temp;
		}
	}
	private static void evalDecl() {
		while(ts.peek(0).charAt(0) == '(' && ts.peek(1).charAt(0) >= 'a' && ts.peek(1).charAt(0) <= 'z') {
			ts.step(2);
			//String str = ts.peek(-1);
			env.put(ts.peek(-1).charAt(0),evaluate());
			//System.out.println(str+" --> "+env.get(str.charAt(0)) );
			ts.step(1);
			while(ts.peek(0).charAt(0) == ')') {
				ts.step(1);
			}
		}
	}
	private static String evalEnvExp() {
		String ret = evaluate();
		env.clear();
		env = env.getParent();
		ts.step(1);
		return ret;
	}
}