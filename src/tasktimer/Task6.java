package tasktimer;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Task6 implements Runnable{
	static final int MAXCOUNT = 50_000;
	
	@Override
	public void run() {
		InputStream instream = Dictionary.getWordsAsStream();
        BufferedReader br = null;
        try {
            br = new BufferedReader( new InputStreamReader(instream) );
        } catch (Exception ex) {
            out.println("Could not open dictionary: "+ex.getMessage() );
            return;
        }
        StringBuilder result = new StringBuilder();
        String word = null;
        int count = 0;
        try {
            while( (word=br.readLine()) != null  && count < MAXCOUNT) {
                result.append(word);
                count++;
            }
        } catch(IOException ioe) { System.out.println( ioe.getMessage() ); }
        System.out.printf("Done appending %d words to StringBuilder.\n", count);
	}
	
	public String toString(){
		return "Starting task: append "+MAXCOUNT+" words to a StringBuilder";
	}

}
