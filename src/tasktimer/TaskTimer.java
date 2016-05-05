package tasktimer;

import static java.lang.System.out;
import java.util.Scanner;
import java.io.*;
import java.util.function.IntConsumer;
import java.util.function.Consumer;
import java.util.concurrent.atomic.*;  // hack, using AtomicInteger as accumulator

/**
 * Time how long it takes to perform some tasks
 * using different programming constructs.
 * 
 * TODO Improve this code by restructuring it to eliminate duplicate code.
 */
public class TaskTimer
{
    private static String DICTIONARY = "wordlist.txt";
    /**
     * Process all the words in a file using Scanner to read and parse input.
     * Display summary statistics and elapsed time.
     */

    /** 
     * Define a customer Consumer class that computes <b>both</b> the average 
     * and count of values.
     * An IntConsumer is a special Consumer interface the has an 'int' parameter 
     * in accept().
     */
    static class IntCounter implements IntConsumer {
        // count the values
        public int count = 0;
        // total of the values
        private long total = 0;
        /** accept consumes an int. In this method, count the value and add it to total. */
        public void accept(int value) { count++; total += value; }
        /** Get the average of all the values consumed. */
        public double average() { 
            return (count>0) ? ((double)total)/count : 0.0;
        }
        public int getCount() { return count; }
    }
    
    /**
     * Process all the words in a file (one word per line) using BufferedReader
     * and the lines() method which creates a Stream of Strings (one item per line).  
     * Then use the stream to compute summary statistics.
     * This is same as task3, except we use a Collector instead of Consumer.
     */
    public static void task4( ) {
        // initialize
        InputStream instream = TaskTimer.class.getClassLoader().getResourceAsStream(DICTIONARY);
        BufferedReader br = null;
        try {
            br = new BufferedReader( new InputStreamReader(instream) );
        } catch (Exception ex) {
            out.println("Could not open dictionary: "+ex.getMessage());
            return;
        }
        
        out.println("Starting task: read words using BufferedReader and Stream with Collector");
        long starttime = System.nanoTime();
        // We want the Consumer to add to the count and total length,
        // but a Lambda can only access local variables (from surrounding scope) if
        // they are final.  That means, we can't use an int, long, or double variable. 
        // So, use AtomicInteger and AtomicLong, which are mutable objects.
        final AtomicLong total = new AtomicLong();
        final AtomicInteger counter = new AtomicInteger();
        //TODO Use a Collector instead of Consumer
        Consumer<String> consumer = new Consumer<String>() {
            public void accept(String word) {
                total.getAndAdd( word.length() );
                counter.incrementAndGet();
            }
        };
                
        br.lines().forEach( consumer );  // Ha! No loop.
        // close the input
        try { br.close(); } catch(IOException ex) { /* ignore it */ }
        
        int count = counter.intValue();
        double averageLength = (count > 0) ? total.doubleValue()/count : 0.0;
        out.printf("Average length of %,d words is %.2f\n", count, averageLength );
            
        long stoptime = System.nanoTime();
        out.printf("Elapsed time is %f sec\n",(stoptime - starttime)*1.0E-9 );  
    }
    
    public static void execAndPrint(Runnable task){
    	StopWatch elapsedTime = new StopWatch();
    	
    	System.out.println(task.toString());
    	elapsedTime.start();
    	task.run();
    	elapsedTime.stopTime();
    	System.out.printf("Elapsed time is %f sec\n",elapsedTime.getElapsed());
    	
    }
        
        
    /** Run all the tasks. */
    public static void main(String [] args) {
      execAndPrint( new Task1() );
      execAndPrint( new Task2() );
      execAndPrint( new Task3() );
      execAndPrint( new Task4() );
      execAndPrint( new Task5() );
      execAndPrint( new Task6() );
    	
    	
    }
    
}
