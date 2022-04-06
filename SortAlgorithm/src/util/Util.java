package util;

public class Util {

	private static final Object lock = new Object();
	private static Util instance;

	private Util() {

	}

	public static Util getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new Util();
				}
			}
		}
		return instance;
	}

	public void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}
}
