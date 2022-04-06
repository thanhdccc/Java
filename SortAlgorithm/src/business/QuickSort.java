package business;

import util.Util;

public class QuickSort {

	private static final Object lock = new Object();
	private static QuickSort instance;

	private QuickSort() {

	}

	public static QuickSort getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new QuickSort();
				}
			}
		}
		return instance;
	}

	public void sort(int[] numbers, int low, int high) {
		if (low < high) {
			int pi = partition(numbers, low, high);

			sort(numbers, low, pi - 1);
			sort(numbers, pi + 1, high);
		}
	}

	private int partition(int[] numbers, int low, int high) {
		Util util = Util.getInstance();
		int pivot = numbers[high];

		int i = low - 1;

		for (int j = low; j <= high - 1; j++) {
			if (numbers[j] < pivot) {
				i++;
				util.swap(numbers, i, j);
			}
		}
		util.swap(numbers, i + 1, high);
		return i + 1;
	}
}
