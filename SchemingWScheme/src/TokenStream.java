import java.io.*;

public class TokenStream {
	private BufferedReader fBuffer = null;
	private String[] currentStream = null;
	private int streamNdx = -1;
	public TokenStream(String fileName) {
		try {
			fBuffer = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	//remove return, instead update current stream
	//every nextline sets streamNdx to 0;
	public void nextLine() {
		String line = "";
		streamNdx = 0;
		try {
			line = fBuffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(line == null) {
			System.out.println("null");
			currentStream = null;
		}
		else {
			System.out.println(line);
			currentStream = tokenize(line);
		}
	}
	
	private String[] tokenize(String line) {
		String[] tokens = line.trim().split("\\s+");
		return tokens;
	}
	
	public void peek() {
		if(currentStream != null) {
			for(int ndx = 0; ndx < currentStream.length; ndx++) {
				System.out.println("\""+currentStream[ndx]+ "\"");
			}
		}
	}
	
	
	
}
