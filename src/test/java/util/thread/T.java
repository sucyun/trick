package util.thread;

public class T {
	public static void main(String[] args) throws InterruptedException {
		And thread1 = new And(1, 1000000000);
		And thread2 = new And(2, 1000000000);
		long startTime = System.currentTimeMillis();
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		long endTime = System.currentTimeMillis();
		System.out.println("并行结果=" + (thread1.getSum() + thread2.getSum()));
		System.out.println("并行时间=" + (endTime - startTime));

		startTime = System.currentTimeMillis();
		And serial = new And(1, 1000000000);
		long sum = serial.sum();
		endTime = System.currentTimeMillis();
		System.out.println("串行结果=" + sum);
		System.out.println("串行时间=" + (endTime - startTime));
	}
}
