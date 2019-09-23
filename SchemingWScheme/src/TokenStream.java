import java.io.*;

public class TokenStream {
	private BufferedReader fBuffer = null;
	private String[] currentStream = null;
	private int streamNdx = -1;
	
	public TokenStream(String fileName) {
		try {
			this.fBuffer = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public boolean nextStream() {
		String line = "";
		this.streamNdx = 0;
		try {
			line = this.fBuffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(line == null) {
			this.currentStream = null;
			return false;
		}
		else {
			this.currentStream = tokenize(line);
			return true;
		}
	}
	private static String[] tokenize(String line) {
		String[] tokens = line.trim().split("\\s+");
		return tokens;
	}
	public void step(int size) {
		if(this.streamNdx + size < this.currentStream.length) {
			this.streamNdx += size;
		}
	}
	public String peek(int ndx) {
		if(ndx+this.streamNdx >= 0 && ndx+this.streamNdx < this.currentStream.length) {
			return this.currentStream[ndx+this.streamNdx];
		}
		return this.currentStream[this.streamNdx];
	}
	public void print(String append) {
		if(this.currentStream != null) {
			for(int ndx = 0; ndx < this.currentStream.length; ndx++) {
				System.out.print(this.currentStream[ndx]+ " ");
			}
		}
		System.out.print(append);
	}
}
