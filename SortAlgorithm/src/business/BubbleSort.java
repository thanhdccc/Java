package business;

import util.Util;

public class BubbleSort {

	private static final Object lock = new Object();
	private static BubbleSort instance;

	private BubbleSort() {

	}

	public static BubbleSort getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new BubbleSort();
				}
			}
		}
		return instance;
	}

	public void sort(int[] numbers) {
		Util util = Util.getInstance();
		int size = numbers.length;
		boolean isSwapped;

		for (int i = 0; i < size - 1; i++) {
			isSwapped = false;

			for (int j = 0; j < size - i - 1; j++) {
				if (numbers[j] > numbers[j + 1]) {
					util.swap(numbers, j, j + 1);
					isSwapped = true;
				}
			}

			if (!isSwapped) {
				break;
			}
		}
	}
}
