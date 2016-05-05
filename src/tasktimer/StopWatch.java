package tasktimer;

public class StopWatch {
	private long startTime;
	private long stopTime;
	private boolean running = false;

	public void start(){
		if (!running){
			startTime = System.nanoTime();
			running = true;
		}
	}

	public void stopTime(){
		if (running){
			stopTime = System.nanoTime();
			running = false;
		}
	}

	public double getElapsed(){
		return (this.stopTime - this.startTime) *1.0E-9;
	}
}
