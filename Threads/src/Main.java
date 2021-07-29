import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws InterruptedException {

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		int temp = 0;
		int option = 0;
		boolean checkOption = true;
		boolean checkChoose = true;

		do {
			System.out.println("-------------------");
			System.out.println("1. Bai 1.");
			System.out.println("2. Bai 2.");
			System.out.println("3. Bai 3.");
			System.out.println("4. Exit.");
			System.out.println("-------------------");

			do {
				System.out.print("Enter your choise: ");
				try {
					option = scanner.nextInt();
					if (option <= 4 || option >= 1) {
						checkChoose = false;
					}
				} catch (InputMismatchException ex) {
					System.out.println("Message: " + ex.toString());
					System.out.println("Please input number (1-4)!");
				}
				scanner.nextLine();
			} while (checkChoose);

			switch (option) {

			case 1:
				Thread generateNumber = new Thread(new RandomInteger());
				Thread checkNumber = new Thread(new CheckInteger());
				generateNumber.start();
				checkNumber.start();
				generateNumber.join();
				checkNumber.join();
				break;

			case 2:
				int optionBT2 = 0;
				boolean checkChooseBT2 = true;
				boolean checkOptionBT2 = true;
				List<Integer> numbers = new ArrayList<>();
				List<Integer> checkNumbers = new ArrayList<>();
				for (int i = 0; i < 500000; i++) {
					temp = random.nextInt(900) + 100;
					numbers.add(temp);
				}
				List<Integer> sub1 = numbers.subList(0, 50000);
				List<Integer> sub2 = numbers.subList(50000, 100000);
				List<Integer> sub3 = numbers.subList(100000, 150000);
				List<Integer> sub4 = numbers.subList(150000, 200000);
				List<Integer> sub5 = numbers.subList(200000, 250000);
				List<Integer> sub6 = numbers.subList(250000, 300000);
				List<Integer> sub7 = numbers.subList(300000, 350000);
				List<Integer> sub8 = numbers.subList(350000, 400000);
				List<Integer> sub9 = numbers.subList(400000, 450000);
				List<Integer> sub10 = numbers.subList(450000, 500000);
				do {
					System.out.println("-------------------");
					System.out.println("1. Run normal.");
					System.out.println("2. Run with 10 threads.");
					System.out.println("3. Back.");
					System.out.println("-------------------");

					do {
						System.out.print("Enter your choise: ");
						try {
							optionBT2 = scanner.nextInt();
							if (optionBT2 <= 3 || optionBT2 >= 1) {
								checkChooseBT2 = false;
							}
						} catch (InputMismatchException ex) {
							System.out.println("Message: " + ex.toString());
							System.out.println("Please input number (1-3)!");
						}
						scanner.nextLine();
					} while (checkChooseBT2);

					switch (optionBT2) {

					case 1:
						final long startTimeBT2Case1 = System.currentTimeMillis();
						checkNumbers = numbers.stream().filter(o -> o % 5 == 0).collect(Collectors.toList());
						checkNumbers.forEach(System.out::println);
						final long endTimeBT2Case1 = System.currentTimeMillis();
						System.out.println(
								"Total execution time (milliseconds): " + (endTimeBT2Case1 - startTimeBT2Case1));
						break;

					case 2:
						final long startTimeBT2Case2 = System.currentTimeMillis();
//						ExecutorService pool = Executors.newFixedThreadPool(10);
//						pool.execute(new Thread(new CheckList(sub1)));
//						pool.execute(new Thread(new CheckList(sub2)));
//						pool.execute(new Thread(new CheckList(sub3)));
//						pool.execute(new Thread(new CheckList(sub4)));
//						pool.execute(new Thread(new CheckList(sub5)));
//						pool.execute(new Thread(new CheckList(sub6)));
//						pool.execute(new Thread(new CheckList(sub7)));
//						pool.execute(new Thread(new CheckList(sub8)));
//						pool.execute(new Thread(new CheckList(sub9)));
//						pool.execute(new Thread(new CheckList(sub10)));
//						pool.shutdown();
//						try {
//					        if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
//					        	pool.shutdownNow();
//					        }
//					    } catch (InterruptedException ex) {
//					    	pool.shutdownNow();
//					        Thread.currentThread().interrupt();
//					    }
						Thread thread1 = new Thread(new CheckList(sub1));
						Thread thread2 = new Thread(new CheckList(sub2));
						Thread thread3 = new Thread(new CheckList(sub3));
						Thread thread4 = new Thread(new CheckList(sub4));
						Thread thread5 = new Thread(new CheckList(sub5));
						Thread thread6 = new Thread(new CheckList(sub6));
						Thread thread7 = new Thread(new CheckList(sub7));
						Thread thread8 = new Thread(new CheckList(sub8));
						Thread thread9 = new Thread(new CheckList(sub9));
						Thread thread10 = new Thread(new CheckList(sub10));
						thread1.start();
						thread2.start();
						thread3.start();
						thread4.start();
						thread5.start();
						thread6.start();
						thread7.start();
						thread8.start();
						thread9.start();
						thread10.start();
						thread1.join();
						thread2.join();
						thread3.join();
						thread4.join();
						thread5.join();
						thread6.join();
						thread7.join();
						thread8.join();
						thread9.join();
						thread10.join();
						final long endTimeBT2Case2 = System.currentTimeMillis();
						System.out.println(
								"Total execution time (milliseconds): " + (endTimeBT2Case2 - startTimeBT2Case2));
						break;

					case 3:
						checkOptionBT2 = false;
						break;
					}
				} while (checkOptionBT2);
				break;

			case 3:
				int optionBT3 = 0;
				boolean checkChooseBT3 = true;
				boolean checkOptionBT3 = true;
				List<Integer> numbersBT3 = new ArrayList<>();
				List<Integer> checkNumbersBT3 = new ArrayList<>();
				for (int i = 0; i < 500000; i++) {
					temp = random.nextInt(900) + 100;
					numbersBT3.add(temp);
				}
				do {
					System.out.println("-------------------");
					System.out.println("1. Using stream.");
					System.out.println("2. Using parallelStream.");
					System.out.println("3. Back.");
					System.out.println("-------------------");

					do {
						System.out.print("Enter your choise: ");
						try {
							optionBT3 = scanner.nextInt();
							if (optionBT3 <= 3 || optionBT3 >= 1) {
								checkChooseBT3 = false;
							}
						} catch (InputMismatchException ex) {
							System.out.println("Message: " + ex.toString());
							System.out.println("Please input number (1-3)!");
						}
						scanner.nextLine();
					} while (checkChooseBT3);

					switch (optionBT3) {

					case 1:
						final long startTimeBT3Case1 = System.currentTimeMillis();
						checkNumbersBT3 = numbersBT3.stream().filter(o -> o % 3 == 0).collect(Collectors.toList());
						System.out.println("Total numbers: " + checkNumbersBT3.size());
						final long endTimeBT3Case1 = System.currentTimeMillis();
						System.out.println(
								"Total execution time (milliseconds): " + (endTimeBT3Case1 - startTimeBT3Case1));
						break;

					case 2:
						final long startTimeBT3Case2 = System.currentTimeMillis();
						checkNumbersBT3 = numbersBT3.parallelStream().filter(o -> o % 3 == 0)
								.collect(Collectors.toList());
						System.out.println("Total numbers: " + checkNumbersBT3.size());
						final long endTimeBT3Case2 = System.currentTimeMillis();
						System.out.println(
								"Total execution time (milliseconds): " + (endTimeBT3Case2 - startTimeBT3Case2));
						break;

					case 3:
						checkOptionBT3 = false;
						break;
					}
				} while (checkOptionBT3);
				break;

			case 4:
				checkOption = false;
				break;
			}
		} while (checkOption);

		scanner.close();
	}
}
