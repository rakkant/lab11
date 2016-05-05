package tasktimer;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import tasktimer.TaskTimer.IntCounter;

public class Task3 implements Runnable {

	@Override
	public void run() {
		InputStream instream = Dictionary.getWordsAsStream();
		BufferedReader br = null;
		try {
			br = new BufferedReader( new InputStreamReader(instream) );
		} catch (Exception ex) {
			out.println("Could not open dictionary: "+ex.getMessage());
			return;
		}
		long totalsize = 0;
		long count = 0;
		IntCounter counter = new IntCounter();
		br.lines().mapToInt( word -> word.length() ).forEach( counter );
		try {
			br.close();
		} catch(IOException ex) { /* ignore it */ }
		out.printf("Average length of %,d words is %.2f\n",
				counter.getCount(), counter.average() );
	}

	public String toString(){
		return "Starting task: read words using BufferedReader and Stream";
	}

}
