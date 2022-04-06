package app;

import java.util.Random;

import business.BubbleSort;
import business.InsertionSort;
import business.MergeSort;
import business.QuickSort;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		BubbleSort bubbleSort = BubbleSort.getInstance();
		InsertionSort insertionSort = InsertionSort.getInstance();
		MergeSort mergeSort = MergeSort.getInstance();
		QuickSort quickSort = QuickSort.getInstance();

		int n = 100;
		int[] numbers = new int[n];
		Random rd = new Random();
		for (int i = 0; i < n; i++) {
			numbers[i] = rd.nextInt(n);
		}

		System.out.println("------> BEFORE SORT <------");
		print(numbers);

		final long startTime = System.currentTimeMillis();
//		bubbleSort.sort(numbers);
//		insertionSort.sort(numbers);
//		mergeSort.sort(numbers, 0, numbers.length - 1);
//		quickSort.sort(numbers, 0, numbers.length - 1);
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));

		System.out.println("------> AFTER SORT <-------");
		print(numbers);
	}

	public static void print(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			if (i <= 100) {
				System.out.print(numbers[i] + " ");
			} else {
				break;
			}
		}
		System.out.println();
	}
}