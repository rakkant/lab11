package tasktimer;

import static java.lang.System.out;

import java.io.InputStream;
import java.util.Scanner;

public class Task1 implements Runnable{

	@Override
	public void run() {
		InputStream instream = Dictionary.getWordsAsStream();
		Scanner in = new Scanner(instream);
		long starttime = System.nanoTime();
		int count = 0;
		long totalsize = 0;
		while(in.hasNext()) {
			String word = in.next();
			totalsize += word.length();
			count++;
		}
		double averageLength = ((double)totalsize)/(count>0 ? count : 1);
		out.printf("Average length of %,d words is %.2f\n", count, averageLength);
		in.close();
	}
	
	public String toString(){
		return "Starting task: read words using Scanner and a while loop";
	}

}
