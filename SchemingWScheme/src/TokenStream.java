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
	
	private String[] tokenize(String line) {
		String[] tokens = line.trim().split("\\s+");
		return tokens;
	}
	
	public void step(int size) {
		if(streamNdx + size < currentStream.length) {
			streamNdx += size;
		}
	}

	public String peek(int ndx) {
		if(ndx+streamNdx >= 0 && ndx+streamNdx < this.currentStream.length) {
			return this.currentStream[ndx+streamNdx];
		}
		return this.currentStream[streamNdx];
	}
	
	public int getNdx() {
		return streamNdx;
	}
	
	public void print(String append) {
		if(currentStream != null) {
			for(int ndx = 0; ndx < currentStream.length; ndx++) {
				System.out.print(currentStream[ndx]+ " ");
			}
		}
		System.out.print(append);
		
	}
	
	
	
}
