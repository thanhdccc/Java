import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	private static CategoryService categoryService = null;
	private static ProductService productService = null;
	private static OrderService orderService = null;

	private static String inputCategoryName(List<Category> categoryList, int option1, int option2) {
		List<Category> matchingCategory = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		do {
			if (option2 == 0) {
				System.out.print("Enter category name: ");
			} else if (option2 == 1) {
				System.out.print("Enter new category name: ");
			} else if (option2 == 2) {
				System.out.print("Enter Category Name that Product belong to: ");
			}
			String name = scanner.nextLine();
			System.out.print("");
			matchingCategory = categoryList.stream().filter(c -> c.getCategoryName().equalsIgnoreCase(name))
					.collect(Collectors.toList());
			if (option1 == 0) {
				if (matchingCategory.size() == 0) {

					return name;
				} else {
					System.out.println("Duplicate!");
				}
			} else if (option1 == 1) {
				if (matchingCategory.size() == 0) {
					System.out.println("Not Exist!");
				} else {

					return name;
				}
			}

		} while (true);
	}

	private static String inputProductName(List<Product> productList, int option1, int option2) {
		List<Product> matchingProduct = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		do {
			if (option2 == 0) {
				System.out.print("Enter product name: ");
			} else if (option2 == 1) {
				System.out.print("Enter new product name: ");
			}
			String name = scanner.nextLine();
			System.out.print("");
			matchingProduct = productList.stream().filter(c -> c.getProductName().equalsIgnoreCase(name))
					.collect(Collectors.toList());
			if (option1 == 0) {
				if (matchingProduct.size() == 0) {

					return name;
				} else {
					System.out.println("Duplicate!");
				}
			} else if (option1 == 1) {
				if (matchingProduct.size() == 0) {
					System.out.println("Not Exist!");
				} else {

					return name;
				}
			}

		} while (true);
	}

	private static String inputOrderName(List<Order> orderList, int option1, int option2) {
		List<Order> matchingOrder = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		do {
			if (option2 == 0) {
				System.out.print("Enter order name: ");
			} else if (option2 == 1) {
				System.out.print("Enter new order name: ");
			}
			String name = scanner.nextLine();
			System.out.print("");
			matchingOrder = orderList.stream().filter(c -> c.getOrderName().equalsIgnoreCase(name))
					.collect(Collectors.toList());
			if (option1 == 0) {
				if (matchingOrder.size() == 0) {

					return name;
				} else {
					System.out.println("Duplicate!");
				}
			} else if (option1 == 1) {
				if (matchingOrder.size() == 0) {
					System.out.println("Not Exist!");
				} else {

					return name;
				}
			}

		} while (true);
	}

	private static float inputProductPrice(int option) {
		Scanner scanner = new Scanner(System.in);
		float price;
		do {
			if (option == 0) {
				System.out.print("Enter product price: ");
			} else if (option == 1) {
				System.out.print("Enter new product price: ");
			}
			try {
				price = scanner.nextFloat();

				return price;
			} catch (Exception e) {
				System.out.println("Message: " + e.toString());
				System.out.println("Please input number!");
			}
			scanner.nextLine();
		} while (true);
	}

	private static int inputProductQuantity(int option) {
		Scanner scanner = new Scanner(System.in);
		int quantity;
		do {
			if (option == 0) {
				System.out.print("Enter product quantity: ");
			} else if (option == 1) {
				System.out.print("Enter new product quantity: ");
			}
			try {
				quantity = scanner.nextInt();

				return quantity;
			} catch (Exception e) {
				System.out.println("Message: " + e.toString());
				System.out.println("Please input number!");
			}
			scanner.nextLine();
		} while (true);
	}

	private static int inputOption(int min, int max) {
		Scanner scanner = new Scanner(System.in);
		int option;

		do {
			System.out.print("Enter your choise: ");
			try {
				option = scanner.nextInt();
				if (option > max || option < min) {
					System.out.printf("Please input number (%d-%d)!", min, max);
					System.out.println();
				} else {
					return option;
				}
			} catch (InputMismatchException ex) {
				System.out.println("Message: " + ex.toString());
				System.out.printf("Please input number (%d-%d)!", min, max);
				System.out.println();
			}
			scanner.nextLine();
		} while (true);
	}

	private static void subProductManagementMenu() {
		System.out.println("---------------");
		System.out.println("1. Update product name.");
		System.out.println("2. Update product price.");
		System.out.println("3. Update product quantity.");
		System.out.println("4. Move category.");
		System.out.println("5. Back.");
		System.out.println("---------------");
	}

	private static void productUpdateManagement(String categoryName, String productNameOld) {
		Scanner scanner = new Scanner(System.in);
		if (productService == null) {
			productService = new ProductService();
		}
		if (categoryService == null) {
			categoryService = new CategoryService();
		}
		int option = 0;
		boolean checkOption = true;
		String productNameNew = null;
		boolean isNameUpdated = false;
		Product productTmp;
		boolean resultUpdate;

		do {
			subProductManagementMenu();
			option = inputOption(1, 5);

			switch (option) {
			case 1:
				productNameNew = inputProductName(productService.getAll(categoryService, categoryName), 0, 1);
				productTmp = productService.getByName(categoryService, categoryName, productNameOld);
				resultUpdate = productService.update(categoryService, categoryName, productTmp, productNameNew, 0, 0,
						1);

				if (resultUpdate == true) {
					isNameUpdated = true;
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 2:
				float productPriceNew = inputProductPrice(1);
				productTmp = isNameUpdated ? productService.getByName(categoryService, categoryName, productNameNew)
						: productService.getByName(categoryService, categoryName, productNameOld);
				resultUpdate = productService.update(categoryService, categoryName, productTmp, null, productPriceNew,
						0, 2);

				if (resultUpdate == true) {
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 3:
				int productQuantityNew = inputProductQuantity(1);
				productTmp = isNameUpdated ? productService.getByName(categoryService, categoryName, productNameNew)
						: productService.getByName(categoryService, categoryName, productNameOld);
				resultUpdate = productService.update(categoryService, categoryName, productTmp, null, 0,
						productQuantityNew, 3);

				if (resultUpdate == true) {
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 4:
				String categoryNameNew = inputCategoryName(categoryService.getAll(), 1, 1);
				String productNameTmp = isNameUpdated ? productNameNew : productNameOld;
				resultUpdate = productService.moveCategory(categoryService, categoryName, categoryNameNew,
						productNameTmp);

				if (resultUpdate == true) {
					System.out.println("Move success!");
				} else {
					System.out.println("Move failed!");
				}
				break;
			case 5:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

	private static void subMenu(String name) {
		System.out.println("---------------");
		System.out.println("1. List " + name + ".");
		System.out.println("2. Add " + name + ".");
		System.out.println("3. Edit " + name + ".");
		System.out.println("4. Delete " + name + ".");
		System.out.println("5. Back.");
		System.out.println("---------------");
	}

	private static void mainMenu() {
		System.out.println("---------------------------------------");
		System.out.println("1. Quan ly Category.");
		System.out.println("2. Quan ly Product.");
		System.out.println("3. Quan ly Order.");
		System.out.println("4. Thong ke so luong moi Category.");
		System.out.println("5. Thong ke 10 san pham ban chay.");
		System.out.println("6. Exit.");
		System.out.println("---------------------------------------");
	}

	private static void categoryManagement() {
		Scanner scanner = new Scanner(System.in);
		if (categoryService == null) {
			categoryService = new CategoryService();
		}
		int option = 0;
		boolean checkOption = true;

		do {
			System.out.println();
			System.out.println(">>>>>> Category Management <<<<<<");
			subMenu("Category");
			option = inputOption(1, 5);

			switch (option) {
			case 1:
				List<Category> categoryList = categoryService.getAll();

				if (categoryList.size() == 0) {
					System.out.println("List of Category is empty!");
				} else {
					categoryList.forEach(System.out::println);
				}
				break;
			case 2:
				String categoryName = inputCategoryName(categoryService.getAll(), 0, 0);
				Category resultAdd = categoryService.add(categoryName);

				if (resultAdd != null) {
					System.out.println("Add success!");
				} else {
					System.out.println("Add failed!");
				}
				break;
			case 3:
				if (categoryService.getAll().size() == 0) {
					System.out.println("There is no Category to update!");
				} else {
					String categoryNameOld = inputCategoryName(categoryService.getAll(), 1, 0);
					String categoryNameNew = inputCategoryName(categoryService.getAll(), 0, 1);
					Category categoryTmp = categoryService.getByName(categoryNameOld);
					boolean resultUpdate = categoryService.update(categoryTmp, categoryNameNew);

					if (resultUpdate == true) {
						System.out.println("Update success!");
					} else {
						System.out.println("Update failed!");
					}
				}
				break;
			case 4:
				if (categoryService.getAll().size() == 0) {
					System.out.println("There is no Category to delete!");
				} else {
					String categoryNameDelete = inputCategoryName(categoryService.getAll(), 1, 0);
					if (categoryService.getByName(categoryNameDelete).getProductList().size() == 0) {
						boolean resultDelete = categoryService.delete(categoryNameDelete);

						if (resultDelete == true) {
							System.out.println("Delete success!");
						} else {
							System.out.println("Delete failed!");
						}
					} else {
						System.out.printf("There is a Product belong to %s category. Delete failed!",
								categoryNameDelete);
						System.out.println();
					}
				}
				break;
			case 5:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

	private static void productManagement() {
		Scanner scanner = new Scanner(System.in);
		if (productService == null) {
			productService = new ProductService();
		}
		if (categoryService == null) {
			categoryService = new CategoryService();
		}
		int option = 0;
		boolean checkOption = true;
		String categoryName;
		List<Product> productList;

		do {
			System.out.println();
			System.out.println(">>>>>> Product Management <<<<<<");
			subMenu("Product");
			option = inputOption(1, 5);

			switch (option) {
			case 1:
				if (categoryService.getAll().size() == 0) {
					System.out.println("Empty!");
				} else {
					for (Category category : categoryService.getAll()) {
						productList = category.getProductList();

						if (productList.size() == 0) {
							System.out.printf("------ %s ------", category.getCategoryName());
							System.out.println();
							System.out.println("List of Product is empty!");
						} else {
							System.out.printf("------ %s ------", category.getCategoryName());
							System.out.println();
							productList.forEach(System.out::println);
						}
					}
				}
				break;
			case 2:
				if (categoryService.getAll().size() == 0) {
					System.out.println("There is no Category exsit. Please add a Category first!");
				} else {
					categoryName = inputCategoryName(categoryService.getAll(), 1, 2);
					String productName = inputProductName(categoryService.getByName(categoryName).getProductList(), 0,
							0);
					float productPrice = inputProductPrice(0);
					int productQuantity = inputProductQuantity(0);

					boolean resultAdd = productService.add(categoryService, categoryName, productName, productPrice,
							productQuantity);

					if (resultAdd == true) {
						System.out.println("Add success!");
					} else {
						System.out.println("Add failed!");
					}
				}
				break;
			case 3:
				if (categoryService.getAll().size() == 0) {
					System.out.println("There is no Category exsit. Please add a Category first!");
				} else {
					categoryName = inputCategoryName(categoryService.getAll(), 1, 2);
					productList = productService.getAll(categoryService, categoryName);
					if (productList.size() == 0) {
						System.out.printf("There is no Product in %s category to update!", categoryName);
						System.out.println();
					} else {
						String productNameOld = inputProductName(productList, 1, 0);
						productUpdateManagement(categoryName, productNameOld);
					}
				}

				break;
			case 4:
				categoryName = inputCategoryName(categoryService.getAll(), 1, 2);
				productList = productService.getAll(categoryService, categoryName);
				if (productList.size() == 0) {
					System.out.printf("There is no Product in %s category to delete!", categoryName);
					System.out.println();
				} else {
					String productNameDelete = inputProductName(productList, 1, 0);
					boolean resultDelete = productService.delete(categoryService, categoryName, productNameDelete);

					if (resultDelete == true) {
						System.out.println("Delete success!");
					} else {
						System.out.println("Delete failed!");
					}
				}
				break;
			case 5:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

	private static void orderManagement() {
		Scanner scanner = new Scanner(System.in);
		if (productService == null) {
			productService = new ProductService();
		}
		if (categoryService == null) {
			categoryService = new CategoryService();
		}
		if (orderService == null) {
			orderService = new OrderService();
		}
		int option = 0;
		boolean checkOption = true;
		List<Order> orderList;

		do {
			System.out.println();
			System.out.println(">>>>>> Order Management <<<<<<");
			subMenu("Order");
			option = inputOption(1, 5);

			switch (option) {
			case 1:
				orderList = orderService.getAll();

				if (orderList.size() == 0) {
					System.out.println("Order list is empty!");
				} else {
					for (Order order : orderList) {
						System.out.printf("*** Order ID: %d - Order Name: %s - Total Price: %.4f ***",
								order.getOrderId(), order.getOrderName(), order.getTotalPrice());
						System.out.println();
						order.getProductList().forEach(System.out::println);
					}
				}
				break;
			case 2:
				int totalProduct = 0;
				if (categoryService.getAll().size() == 0) {
					System.out.println("There is no Category exsit. Please add a Category first!");
				} else {
					for (Category category : categoryService.getAll()) {
						totalProduct += category.getProductList().size();
					}
					if (totalProduct == 0) {
						System.out.println("There is no Product to Order!");
					} else {
						String orderName = inputOrderName(orderService.getAll(), 0, 0);
						List<Product> productList = new ArrayList<>();
						List<Product> tmpList = new ArrayList<>();
						int choice;
						int productQuantity = 0;
						String productName;
						int currentQuantity;
						boolean checkInputProduct = true;
						boolean checkProductQuantity = true;
						do {
							String categoryName = inputCategoryName(categoryService.getAll(), 1, 2);
							productName = inputProductName(productService.getAll(categoryService, categoryName), 1, 0);
							currentQuantity = productService.getByName(categoryService, categoryName, productName)
									.getProductQuantity();
							if (currentQuantity == 0) {
								System.out.println("Product out of stock!");
								break;
							}

							String tmpProductName = productName;
							tmpList = productList.stream()
									.filter(p -> p.getProductName().equalsIgnoreCase(tmpProductName))
									.collect(Collectors.toList());

							if (tmpList.size() == 0) {
								do {
									productQuantity = inputProductQuantity(0);

									if (currentQuantity - productQuantity >= 0) {
										Product productTmp = productService.getByName(categoryService, categoryName,
												productName);
										Product product = new Product(
												categoryService.getByName(categoryName).getCategoryId(), categoryName,
												productTmp.getProductId(), productTmp.getProductName(),
												productTmp.getProductPrice(), productQuantity);
										productList.add(product);
										Product productInStock = productService.getByName(categoryService, categoryName,
												productName);
										productInStock.setProductQuantity(currentQuantity - productQuantity);
										checkProductQuantity = false;
									} else {
										System.out.println("Not enough product in stock!");
									}
								} while (checkProductQuantity);
							} else {
								do {
									productQuantity = inputProductQuantity(0);

									if (currentQuantity - productQuantity >= 0) {
										for (Product product : productList) {
											if (product.getProductName().equalsIgnoreCase(productName)) {
												product.setProductQuantity(
														product.getProductQuantity() + productQuantity);
												Product productInStock = productService.getByName(categoryService,
														categoryName, productName);
												productInStock.setProductQuantity(currentQuantity - productQuantity);
												break;
											}
										}
										checkProductQuantity = false;
									} else {
										System.out.println("Not enough product in stock!");
									}
								} while (checkProductQuantity);
							}

							System.out.println("-------------------------------");
							System.out.println("1. Continue Input product.");
							System.out.println("2. Save Order.");
							System.out.println("-------------------------------");

							choice = inputOption(1, 2);

							if (choice == 2) {
								float total = orderService.totalPrice(productList);
								boolean resultAdd = orderService.add(orderName, total, productList);

								if (resultAdd == true) {
									System.out.println("Add success!");
								} else {
									System.out.println("Add failed!");
								}
								checkInputProduct = false;
							}
						} while (checkInputProduct);
					}
				}
				break;
			case 3:
				if (orderService.getAll().size() == 0) {
					System.out.println("There is no order to Update");
				} else {
					int optionUpdate = 0;
					boolean checkOptionUpdate = true;
					boolean resultUpdate;
					String orderNameOld;
					String orderNameNew;
					do {
						System.out.println("-----------------------------------");
						System.out.println("1. Update Order Name.");
						System.out.println("2. Add new Product.");
						System.out.println("3. Update Product quantity.");
						System.out.println("4. Back.");
						System.out.println("-----------------------------------");

						optionUpdate = inputOption(1, 4);

						switch (optionUpdate) {
						case 1:
							orderNameOld = inputOrderName(orderService.getAll(), 1, 0);
							orderNameNew = inputOrderName(orderService.getAll(), 0, 1);
							resultUpdate = orderService.update(orderNameOld, orderNameNew, null, 0, 1);
							if (resultUpdate == true) {
								System.out.println("Update success!");
							} else {
								System.out.println("Update failed!");
							}
							break;
						case 2:
							orderNameOld = inputOrderName(orderService.getAll(), 1, 0);
							String categoryNameAdd = inputCategoryName(categoryService.getAll(), 1, 0);
							String productNameAdd = inputProductName(
									productService.getAll(categoryService, categoryNameAdd), 1, 0);
							boolean isExistProduct = false;
							for (Product product : orderService.getByName(orderNameOld).getProductList()) {
								if (product.getProductName().equalsIgnoreCase(productNameAdd)) {
									isExistProduct = true;
									System.out.printf("There are product name: %s in %s order", productNameAdd,
											orderNameOld);
									System.out.println();
									break;
								}
							}
							if (!isExistProduct) {
								float totalPrice = 0;
								Product productTmp = productService.getByName(categoryService, categoryNameAdd,
										productNameAdd);
								int stockProductQuantity = productService
										.getByName(categoryService, productTmp.getCategoryName(), productNameAdd)
										.getProductQuantity();

								boolean isQuantityEnough = false;
								do {
									int quantityProduct = inputProductQuantity(0);

									if (quantityProduct <= stockProductQuantity) {
										int quantityChange = stockProductQuantity - quantityProduct;
										Product productAdd = new Product(productTmp.getCategoryId(), categoryNameAdd,
												productTmp.getProductId(), productNameAdd, productTmp.getProductPrice(),
												quantityProduct);
										orderService.getByName(orderNameOld).getProductList().add(productAdd);
										productService.getByName(categoryService, categoryNameAdd, productNameAdd)
												.setProductQuantity(quantityChange);
										totalPrice += orderService
												.totalPrice(orderService.getByName(orderNameOld).getProductList());
										orderService.getByName(orderNameOld).setTotalPrice(totalPrice);
										System.out.println("Update success!");
										isQuantityEnough = true;
									} else {
										System.out.println("Product out of Stock!");
									}
								} while (!isQuantityEnough);
							}
							break;
						case 3:
							orderNameOld = inputOrderName(orderService.getAll(), 1, 0);
							Order order = orderService.getByName(orderNameOld);
							float totalPriceChange = 0;

							System.out.printf("*** Order ID: %d - Order Name: %s - Total Price: %.4f ***",
									order.getOrderId(), order.getOrderName(), order.getTotalPrice());
							System.out.println();
							order.getProductList().forEach(System.out::println);
							System.out.println("********************************************************");

							String productNameUpdate = inputProductName(
									orderService.getByName(orderNameOld).getProductList(), 1, 0);
							Product productTmp = orderService.getOrderProductByName(orderNameOld, productNameUpdate);
							int stockProductQuantity = productService
									.getByName(categoryService, productTmp.getCategoryName(), productNameUpdate)
									.getProductQuantity();
							int orderProductQuantity = productTmp.getProductQuantity();
							int quantityChange = inputProductQuantity(1);
							int quantityNew = (stockProductQuantity + orderProductQuantity) - quantityChange;

							if (quantityNew >= 0) {
								orderService.getOrderProductByName(orderNameOld, productNameUpdate)
										.setProductQuantity(quantityChange);
								productService
										.getByName(categoryService, productTmp.getCategoryName(), productNameUpdate)
										.setProductQuantity(quantityNew);

								totalPriceChange += orderService
										.totalPrice(orderService.getByName(orderNameOld).getProductList());
								orderService.getByName(orderNameOld).setTotalPrice(totalPriceChange);
								System.out.println("Update success!");
							} else {
								System.out.println("Product out of Stock!");
							}
							break;
						case 4:
							checkOptionUpdate = false;
							break;
						}
					} while (checkOptionUpdate);
				}
				break;
			case 4:
				if (orderService.getAll().size() == 0) {
					System.out.println("There is no order to Delete");
				} else {
					String orderNameDelete = inputOrderName(orderService.getAll(), 1, 0);
					boolean resultDelete = orderService.delete(orderNameDelete);
					if (resultDelete == true) {
						System.out.println("Delete success!");
					} else {
						System.out.println("Delete success!");
					}
				}
				break;
			case 5:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

	private static void totalProductOfCategory() {
		if (categoryService == null) {
			categoryService = new CategoryService();
		}
		if (categoryService.getAll().size() == 0) {
			System.out.println("There is no Category!");
		} else {
			for (Category category : categoryService.getAll()) {
				int total = categoryService.getTotalProductByCategoryName(category.getCategoryName());
				System.out.printf("%s have %d products", category.getCategoryName(), total);
				System.out.println();
			}
		}
	}

	private static List<Product> top10Product() {
		if (orderService == null) {
			orderService = new OrderService();
		}
		List<Product> productTmp = new ArrayList<>();
		List<Product> matchingProductRaw = new ArrayList<>();
		List<Product> matchingProduct = new ArrayList<>();
		List<Product> productList = new ArrayList<>();
		if (orderService.getAll().size() == 0) {
			System.out.println("There is no Order information.");
			return null;
		} else {
			for (Order order : orderService.getAll()) {
				productTmp.addAll(order.getProductList());
			}
			for (Product product : productTmp) {
				matchingProductRaw = productTmp.stream()
						.filter(p -> p.getProductName().equalsIgnoreCase(product.getProductName()))
						.collect(Collectors.toList());
				matchingProduct = productList.stream()
						.filter(p -> p.getProductName().equalsIgnoreCase(product.getProductName()))
						.collect(Collectors.toList());
				if (matchingProduct.size() == 0) {
					int totalProductQuantity = matchingProductRaw.stream().mapToInt(p -> p.getProductQuantity()).sum();
					Product productAdd = new Product(
							product.getCategoryId(),
							product.getCategoryName(),
							product.getProductId(),
							product.getProductName(),
							product.getProductPrice(),
							totalProductQuantity
							);
					productList.add(productAdd);
				}
			}
			return productList.stream()
					.sorted(Comparator.comparing(Product::getProductQuantity).reversed())
					.collect(Collectors.toList());
		}
	}

	public static void main(String[] args) {
		int option = 0;
		boolean checkOption = true;

		do {
			System.out.println();
			System.out.println(">>>>>>------ Main Menu ------<<<<<<");
			mainMenu();
			option = inputOption(1, 6);

			switch (option) {
			case 1:
				categoryManagement();
				break;
			case 2:
				productManagement();
				break;
			case 3:
				orderManagement();
				break;
			case 4:
				totalProductOfCategory();
				break;
			case 5:
				List<Product> productList = top10Product();
				if (productList != null) {
					Product product = null;
					System.out.println("****** Top 10 best seller products ******");
					if (productList.size() >= 10) {
						for (int i = 0; i < 10; i++) {
							product = productList.get(i);
							System.out.printf("Product Name: %s - Quantity: %d", product.getProductName(), product.getProductQuantity());
							System.out.println();
						}
					} else {
						for (int i = 0; i < productList.size(); i++) {
							product = productList.get(i);
							System.out.printf("Product Name: %s - Quantity: %d", product.getProductName(), product.getProductQuantity());
							System.out.println();
						}
					}
				}
				break;
			case 6:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}
}
