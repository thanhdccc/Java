package business;

public class InsertionSort {

	private static final Object lock = new Object();
	private static InsertionSort instance;

	private InsertionSort() {

	}

	public static InsertionSort getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new InsertionSort();
				}
			}
		}
		return instance;
	}

	public void sort(int[] numbers) {
		int size = numbers.length;
		int key;
		int j;

		for (int i = 1; i < size; i++) {
			key = numbers[i];
			j = i - 1;

			while (j >= 0 && numbers[j] > key) {
				numbers[j + 1] = numbers[j];
				j = j - 1;
			}
			numbers[j + 1] = key;
		}
	}
}
