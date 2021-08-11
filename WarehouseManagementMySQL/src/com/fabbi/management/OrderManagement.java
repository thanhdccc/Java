package com.fabbi.management;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fabbi.entity.Category;
import com.fabbi.entity.Order;
import com.fabbi.entity.Product;
import com.fabbi.service.CategoryServiceImpl;
import com.fabbi.service.OrderServiceImpl;
import com.fabbi.service.ProductServiceImpl;
import com.fabbi.util.Helper;

public class OrderManagement {

	private Helper helper = null;
	private static OrderManagement instance;
	
	private OrderManagement() {
		
	}
	
	public static OrderManagement getInstance() {
		if (instance == null) {
			instance = new OrderManagement();
		}
		return instance;
	}
	
	public void getTop10BestSoldProduct(OrderServiceImpl orderService) {
		
		List<Product> productList = orderService.top10BestSellerProduct();
		if (productList != null) {
			System.out.println("****** Top 10 best seller products ******");
			int count = 1;
			for (Product product : productList) {
				System.out.printf("Top %d -> Product ID: %d - Product Name: %s - Sold Quantity: %d", 
						count, product.getProductId(), product.getProductName(), product.getProductQuantity());
				System.out.println();
				count++;
			}
		} else {
			System.out.println("Order list empty!");
		}
	}

	public void orderManagementExecute(CategoryServiceImpl categoryService, ProductServiceImpl productService,
			OrderServiceImpl orderService) {

		helper = Helper.getInstance();
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
				List<Category> categoryList = categoryService.getAll();
				orderList = orderService.getAll();
				
				if (categoryList.size() == 0) {
					System.out.println("There is no Category exsit. Please add a Category first!");
				} else {
					for (Category category : categoryList) {
						totalProduct += productService.getByCategoryId(category.getCategoryId()).size();
					}
					
					if (totalProduct == 0) {
						System.out.println("There is no Product to Order!");
					} else {
						String orderName = helper.inputOrderName(orderList, 0, 0);
						Order order = new Order(orderName);
						boolean resultAddOrder = orderService.add(order);
						
						if (!resultAddOrder) {
							System.out.println("Create order failed!");
						} else {
							List<Product> productList = new ArrayList<>();
							List<Product> tmpList = new ArrayList<>();
							int choice;
							int productQuantity = 0;
							String productName;
							int currentQuantity;
							boolean checkInputProduct = true;
							boolean checkProductQuantity = true;
							
							do {
								String categoryName = helper.inputCategoryName(categoryList, 1, 2);
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
									int orderId = orderService.getByName(orderName).getOrderId();
									do {
										productQuantity = helper.inputProductQuantity(0);

										if (currentQuantity - productQuantity >= 0) {
											Product productTmp = productService.getByName(productName);
											Product product = new Product(productTmp.getProductId(),
													productTmp.getProductName(), productTmp.getProductPrice(),
													productQuantity, categoryTmp.getCategoryId());
											
											productList.add(product);
											orderService.addProductToOrder(orderId, product);
											float total = orderService.totalPrice(productList);
											boolean result = orderService.updateOrderTotalPrice(orderName, total);
											
											if (result) {
												productTmp.setProductQuantity(currentQuantity - productQuantity);
												productService.update(productTmp);
												checkProductQuantity = false;
											} else {
												System.out.println("Error.");
												checkProductQuantity = false;
											}
											
										} else {
											System.out.println("Not enough product in stock!");
										}
									} while (checkProductQuantity);
								} else {
									int orderId = orderService.getByName(orderName).getOrderId();
									do {
										productQuantity = helper.inputProductQuantity(0);

										if (currentQuantity - productQuantity >= 0) {
											for (Product product : productList) {
												if (product.getProductName().equalsIgnoreCase(productName)) {
													
													product.setProductQuantity(product.getProductQuantity() + productQuantity);
													orderService.updateOrderProductQuantity(product.getProductQuantity(), orderId, product.getProductId());
													Product productInStock = productService.getByName(productName);
													productInStock.setProductQuantity(currentQuantity - productQuantity);
													productService.update(productInStock);
													break;
												}
											}
											float total = orderService.totalPrice(productList);
											orderService.updateOrderTotalPrice(orderName, total);
											
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
									boolean resultAdd = orderService.updateOrderTotalPrice(orderName, total);

									if (resultAdd) {
										System.out.println("Add success!");
									} else {
										System.out.println("Add failed!");
									}
									checkInputProduct = false;
								}
							} while (checkInputProduct);
						}
					}
				}
				break;
			case 3:
				List<Order> orderListTmp = orderService.getAll();
				Order order = null;
				
				if (orderListTmp.size() == 0) {
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
							orderListTmp = orderService.getAll();
							orderNameOld = helper.inputOrderName(orderListTmp, 1, 0);
							orderNameNew = helper.inputOrderName(orderListTmp, 0, 1);
							order = orderService.getByName(orderNameOld);
							order.setOrderName(orderNameNew);
							resultUpdate = orderService.update(order);
							if (resultUpdate == true) {
								System.out.println("Update success!");
							} else {
								System.out.println("Update failed!");
							}
							break;
						case 2:
							orderListTmp = orderService.getAll();
							orderNameOld = helper.inputOrderName(orderListTmp, 1, 0);
							String categoryNameAdd = helper.inputCategoryName(categoryService.getAll(), 1, 0);
							Category category = categoryService.getByName(categoryNameAdd);
							String productNameAdd = helper
									.inputProductName(productService.getByCategoryId(category.getCategoryId()), 1, 0);
							Order orderTmp = orderService.getByName(orderNameOld);
							Product productTmp = orderService.getProductByOrderIdAndProductName(orderTmp.getOrderId(), productNameAdd);
							boolean isExistProduct = false;
							if (productTmp != null) {
								isExistProduct = true;
								System.out.printf("There are product name: %s in %s order", productNameAdd, orderNameOld);
								System.out.println();
							}
							if (!isExistProduct) {
								float totalPrice = 0;
								productTmp = productService.getByName(productNameAdd);
								int stockProductQuantity = productTmp.getProductQuantity();

								boolean isQuantityEnough = false;
								do {
									int quantityProduct = helper.inputProductQuantity(0);

									if (quantityProduct <= stockProductQuantity) {
										int quantityChange = stockProductQuantity - quantityProduct;
										Product productAdd = new Product(productTmp.getProductId(), productNameAdd,
												productTmp.getProductPrice(), quantityProduct,
												productTmp.getCategoryId());
										
										orderService.addProductToOrder(orderTmp.getOrderId(), productAdd);
										productTmp.setProductQuantity(quantityChange);
										productService.update(productTmp);
										
										List<Product> productListTmp = orderService.getProductListByOrderId(orderTmp.getOrderId());
										totalPrice += orderService.totalPrice(productListTmp);
										orderService.updateOrderTotalPrice(orderNameOld, totalPrice);
										System.out.println("Update success!");
										isQuantityEnough = true;
									} else {
										System.out.println("Product out of Stock!");
									}
								} while (!isQuantityEnough);
							}
							break;
						case 3:
							orderListTmp = orderService.getAll();
							orderNameOld = helper.inputOrderName(orderListTmp, 1, 0);
							order = orderService.getByName(orderNameOld);
							int orderId = order.getOrderId();
							List<Product> productListTmp = orderService.getProductListByOrderId(orderId);
							float totalPriceChange = 0;

							System.out.printf("*** Order ID: %d - Order Name: %s - Total Price: %.4f ***", order.getOrderId(), order.getOrderName(), order.getTotalPrice());
							System.out.println();
							productListTmp.forEach(System.out::println);
							System.out.println("********************************************************");

							String productNameUpdate = helper.inputProductName(productListTmp, 1, 0);
							
							productTmp = orderService.getProductByOrderIdAndProductName(orderId, productNameUpdate);
							
							int stockProductQuantity = productService.getByName(productNameUpdate).getProductQuantity();
							int orderProductQuantity = productTmp.getProductQuantity();
							int quantityChange = helper.inputProductQuantity(1);
							int quantityNew = (stockProductQuantity + orderProductQuantity) - quantityChange;

							if (quantityNew >= 0) {
								
								orderService.updateOrderProductQuantity(quantityChange, orderId, productTmp.getProductId());
								productListTmp = orderService.getProductListByOrderId(orderId);
								totalPriceChange += orderService.totalPrice(productListTmp);
								orderService.updateOrderTotalPrice(order.getOrderName(), totalPriceChange);
								
								productTmp = productService.getByName(productNameUpdate);
								productTmp.setProductQuantity(quantityNew);
								productService.update(productTmp);

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
				orderListTmp = orderService.getAll();
				if (orderListTmp.size() == 0) {
					System.out.println("There is no order to Delete");
				} else {
					String orderNameDelete = helper.inputOrderName(orderListTmp, 1, 0);
					Order orderTmp = orderService.getByName(orderNameDelete);
					int id = orderTmp.getOrderId();
					
					boolean resultDeleteProduct = orderService.deleteProductInOrder(id);
					boolean resultDelete = false;
					
					if (resultDeleteProduct) {
						resultDelete = orderService.delete(orderTmp);
						
						if (resultDelete) {
							System.out.println("Delete success!");
						} else {
							System.out.println("Delete failed!");
						}
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
}
