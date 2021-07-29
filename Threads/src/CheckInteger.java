
public class CheckInteger implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			System.out.println("Message: " + ex.toString());
		}
		for (int i = 0; i < 5; i++) {
			if (RandomInteger.number % 2 == 0) {
				System.out.println("--> " + RandomInteger.number + " is even number");
			} else {
				System.out.println("--> " + RandomInteger.number + " is odd number");
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Message: " + e.toString());
			}
		}
	}

}
