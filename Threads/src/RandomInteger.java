import java.util.Random;

public class RandomInteger implements Runnable {

	public static int number;

	@Override
	public void run() {
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			number = random.nextInt(9000) + 1000;
			System.out.println("-- Number is generated: " + number);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Message: " + e.toString());
			}
		}
	}

}
