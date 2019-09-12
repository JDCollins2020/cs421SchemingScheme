
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TokenStream tx = new TokenStream("hello.txt");
		for(int line = 0; line < 8; line++) {
			tx.nextLine();
			tx.peek();
		}
		
	}

}
