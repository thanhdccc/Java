package business;

public class MergeSort {

	private static final Object lock = new Object();
	private static MergeSort instance;

	private MergeSort() {

	}

	public static MergeSort getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new MergeSort();
				}
			}
		}
		return instance;
	}

	public void sort(int[] numbers, int left, int right) {
		if (left < right) {
			int middle = left + (right - left) / 2;

			sort(numbers, left, middle);
			sort(numbers, middle + 1, right);

			merge(numbers, left, right, middle);
		}
	}

	private void merge(int[] numbers, int left, int right, int middle) {
		int sizeLeft = middle - left + 1;
		int sizeRight = right - middle;

		int[] leftNumbers = new int[sizeLeft];
		int[] rightNumbers = new int[sizeRight];

		for (int i = 0; i < sizeLeft; i++) {
			leftNumbers[i] = numbers[left + i];
		}
		for (int i = 0; i < sizeRight; i++) {
			rightNumbers[i] = numbers[middle + i + 1];
		}

		int i = 0;
		int j = 0;
		int k = left;

		while (i < sizeLeft && j < sizeRight) {
			if (leftNumbers[i] <= rightNumbers[j]) {
				numbers[k] = leftNumbers[i];
				i++;
			} else {
				numbers[k] = rightNumbers[j];
				j++;
			}
			k++;
		}

		while (i < sizeLeft) {
			numbers[k] = leftNumbers[i];
			i++;
			k++;
		}

		while (j < sizeRight) {
			numbers[k] = rightNumbers[j];
			j++;
			k++;
		}
	}
}
