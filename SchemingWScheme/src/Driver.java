
public class Driver {

	public static void main(String[] args) {
		TokenStream ts = new TokenStream(args[0]);
		while(ts.nextLine()) {
			ts.peek();
			System.out.println("------------------------------------------");
		}
		
	}

}
