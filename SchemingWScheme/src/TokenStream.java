import java.io.*;

public class TokenStream {
	private BufferedReader fBuffer = null;
	private String[] currentStream = null;
	private int streamNdx = -1;
	//
	public TokenStream(String fileName) {
		try {
			fBuffer = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	//
	public boolean nextStream() {
		String line = "";
		streamNdx = 0;
		try {
			line = fBuffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(line == null) {
			currentStream = null;
			return false;
		}
		else {
			currentStream = tokenize(line);
			return true;
		}
	}
	//
	private String[] tokenize(String line) {
		String[] tokens = line.trim().split("\\s+");
		return tokens;
	}
	//
	public String step(int size) {
		streamNdx += size;
		return this.currentStream[streamNdx];
	}
	//
	public String peek(int ndx) {
		if(ndx >= 0 && ndx < this.currentStream.length) {
			return this.currentStream[ndx+streamNdx];
		}
		return "";
	}
	//
	public int getNdx() {
		return streamNdx;
	}
	//delete
	public void printAll() {
		if(currentStream != null) {
			for(int ndx = 0; ndx < currentStream.length; ndx++) {
				System.out.print(currentStream[ndx]+ " ");
			}
		}
		
	}
	
	
	
}
