package util.thread;

public class And extends Thread {
	private long start;
	private long end;
	private long sum = 0;

	public And(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}

	public void run() {
		for (long i = start; i <= end; i += 2)
			sum += i;
	}

	public long sum() {
		for (long i = start; i <= end; i++)
			sum += i;
		return sum;
	}

	public long getSum() {
		return sum;
	}
}
