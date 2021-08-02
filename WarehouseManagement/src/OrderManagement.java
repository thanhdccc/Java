import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderManagement {

	private Helper helper = null;

	public void orderManagementExecute(CategoryService categoryService, ProductService productService,
			OrderService orderService) {

		if (helper == null) {
			helper = new Helper();
		}
		int option = 0;
		boolean checkOption = true;
		List<Order> orderList;

		do {
			System.out.println();
			System.out.println(">>>>>> Order Management <<<<<<");
			helper.subMenu("Order");
			option = helper.inputOption(1, 5);

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
						totalProduct += productService.getByCategoryId(category.getCategoryId()).size();
					}
					
					if (totalProduct == 0) {
						System.out.println("There is no Product to Order!");
					} else {
						String orderName = helper.inputOrderName(orderService.getAll(), 0, 0);
						List<Product> productList = new ArrayList<>();
						List<Product> tmpList = new ArrayList<>();
						int choice;
						int productQuantity = 0;
						String productName;
						int currentQuantity;
						boolean checkInputProduct = true;
						boolean checkProductQuantity = true;
						
						do {
							String categoryName = helper.inputCategoryName(categoryService.getAll(), 1, 2);
							Category category = categoryService.getByName(categoryName);
							productName = helper
									.inputProductName(productService.getByCategoryId(category.getCategoryId()), 1, 0);
							currentQuantity = productService.getByName(productName).getProductQuantity();
							if (currentQuantity == 0) {
								System.out.println("Product out of stock!");
								break;
							}

							String tmpProductName = productName;
							tmpList = productList.stream()
									.filter(p -> p.getProductName().equalsIgnoreCase(tmpProductName))
									.collect(Collectors.toList());
							Category categoryTmp = categoryService.getByName(categoryName);

							if (tmpList.size() == 0) {
								do {
									productQuantity = helper.inputProductQuantity(0);

									if (currentQuantity - productQuantity >= 0) {
										Product productTmp = productService.getByName(productName);
										Product product = new Product(productTmp.getProductId(),
												productTmp.getProductName(), productTmp.getProductPrice(),
												productQuantity, categoryTmp.getCategoryId());
										productList.add(product);
										Product productInStock = productService.getByName(productName);
										productInStock.setProductQuantity(currentQuantity - productQuantity);
										checkProductQuantity = false;
									} else {
										System.out.println("Not enough product in stock!");
									}
								} while (checkProductQuantity);
							} else {
								do {
									productQuantity = helper.inputProductQuantity(0);

									if (currentQuantity - productQuantity >= 0) {
										for (Product product : productList) {
											if (product.getProductName().equalsIgnoreCase(productName)) {
												product.setProductQuantity(
														product.getProductQuantity() + productQuantity);
												Product productInStock = productService.getByName(productName);
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

							choice = helper.inputOption(1, 2);

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

						optionUpdate = helper.inputOption(1, 4);

						switch (optionUpdate) {
						case 1:
							orderNameOld = helper.inputOrderName(orderService.getAll(), 1, 0);
							orderNameNew = helper.inputOrderName(orderService.getAll(), 0, 1);
							resultUpdate = orderService.update(orderNameOld, orderNameNew, null, 0, 1);
							if (resultUpdate == true) {
								System.out.println("Update success!");
							} else {
								System.out.println("Update failed!");
							}
							break;
						case 2:
							orderNameOld = helper.inputOrderName(orderService.getAll(), 1, 0);
							String categoryNameAdd = helper.inputCategoryName(categoryService.getAll(), 1, 0);
							Category category = categoryService.getByName(categoryNameAdd);
							String productNameAdd = helper
									.inputProductName(productService.getByCategoryId(category.getCategoryId()), 1, 0);
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
								Product productTmp = productService.getByName(productNameAdd);
								int stockProductQuantity = productService.getByName(productNameAdd)
										.getProductQuantity();

								boolean isQuantityEnough = false;
								do {
									int quantityProduct = helper.inputProductQuantity(0);

									if (quantityProduct <= stockProductQuantity) {
										int quantityChange = stockProductQuantity - quantityProduct;
										Product productAdd = new Product(productTmp.getProductId(), productNameAdd,
												productTmp.getProductPrice(), quantityProduct,
												productTmp.getCategoryId());
										orderService.getByName(orderNameOld).getProductList().add(productAdd);
										productService.getByName(productNameAdd).setProductQuantity(quantityChange);
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
							orderNameOld = helper.inputOrderName(orderService.getAll(), 1, 0);
							Order order = orderService.getByName(orderNameOld);
							float totalPriceChange = 0;

							System.out.printf("*** Order ID: %d - Order Name: %s - Total Price: %.4f ***",
									order.getOrderId(), order.getOrderName(), order.getTotalPrice());
							System.out.println();
							order.getProductList().forEach(System.out::println);
							System.out.println("********************************************************");

							String productNameUpdate = helper
									.inputProductName(orderService.getByName(orderNameOld).getProductList(), 1, 0);
							Product productTmp = orderService.getOrderProductByName(orderNameOld, productNameUpdate);
							int stockProductQuantity = productService.getByName(productNameUpdate).getProductQuantity();
							int orderProductQuantity = productTmp.getProductQuantity();
							int quantityChange = helper.inputProductQuantity(1);
							int quantityNew = (stockProductQuantity + orderProductQuantity) - quantityChange;

							if (quantityNew >= 0) {
								orderService.getOrderProductByName(orderNameOld, productNameUpdate)
										.setProductQuantity(quantityChange);
								productService.getByName(productNameUpdate).setProductQuantity(quantityNew);

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
					String orderNameDelete = helper.inputOrderName(orderService.getAll(), 1, 0);
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

	public List<Product> top10BestSellerProduct(OrderService orderService) {

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
					Product productAdd = new Product(product.getProductId(), product.getProductName(),
							product.getProductPrice(), totalProductQuantity, product.getCategoryId());
					productList.add(productAdd);
					int productListSize = productList.size();
					if (productListSize == 10) {
						return productList.stream().sorted(Comparator.comparing(Product::getProductQuantity).reversed())
								.collect(Collectors.toList());
					}
				}
			}
			return productList.stream().sorted(Comparator.comparing(Product::getProductQuantity).reversed())
					.collect(Collectors.toList());
		}
	}
}
