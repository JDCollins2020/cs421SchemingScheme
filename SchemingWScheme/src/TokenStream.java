import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * TokenStream controls interactions with the current stream of tokens from a 
 * BITS file
 * @author  John Collins
 */
public class TokenStream {
	private BufferedReader fBuffer = null;
	private String[] currentStream = null;
	private int streamNdx = -1;
	
	/**
	  * Constructor for token stream. Creates BufferedReader for the BITS file 
	  * to be executed 
	  * @param fileName name of the BITS file to be executed
	  */
	public TokenStream(String fileName) {
		try {
			this.fBuffer = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tokenizes the next line of the BITS file and saves as the current stream
	 * @return boolean indicates if another line was tokenized
	 */
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
	
	/**
	 * Performs the tokenization of the current line to be evaluated
	 * @param line the current BITS expression to be tokenized
	 * @return String[] the current stream of tokens
	 */
	private static String[] tokenize(String line) {
		String[] tokens = line.trim().split("\\s+");
		return tokens;
	}
	
	/**
	 * Adjusts the index for the current stream
	 * @param size the number of indices to jump on the current stream
	 */
	public void step(int size) {
		if(this.streamNdx + size < this.currentStream.length) {
			this.streamNdx += size;
		}
	}
	
	/**
	 * Gives the token in the current stream at the streamNdx + ndx
	 * @param ndx the value to add to streamNdx
	 * @return String the token at ndx+streamNdx
	 */
	public String peek(int ndx) {
		if(ndx+this.streamNdx >= 0 && ndx+this.streamNdx < this.currentStream.length) {
			return this.currentStream[ndx+this.streamNdx];
		}
		return this.currentStream[this.streamNdx];
	}
}
