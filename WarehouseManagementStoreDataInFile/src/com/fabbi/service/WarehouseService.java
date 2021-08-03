package com.fabbi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import com.fabbi.entity.Warehouse;

public class WarehouseService {
	
	private static final String DATA_FILE = "Data.txt";
	
	public Warehouse readData() {
		File file = new File(DATA_FILE);
		Warehouse warehouse = new Warehouse();
		
		if(file.exists()) {
			try {
				FileInputStream dataFile = new FileInputStream(file);
				ObjectInputStream inputStream = new ObjectInputStream(dataFile);
				
				warehouse = (Warehouse) inputStream.readObject();
				
				inputStream.close();
				dataFile.close();
				
				return warehouse;
				
			} catch (FileNotFoundException e) {
				System.out.println("Error: " + e);
				return null;
			} catch (IOException e) {
				System.out.println("Error: " + e);
				return null;
			} catch (ClassNotFoundException e) {
				System.out.println("Error: " + e);
				return null;
			}
		} else {
			return null;
		}
	}
	
	public void writeData(CategoryService categoryService, ProductService productService, OrderService orderService) {
		try {
			File file = new File(DATA_FILE);
			
			if (!file.exists()) {
				file.createNewFile();
				
				Warehouse warehouse = new Warehouse();
				warehouse.setCategoryList(categoryService.getAll());
				warehouse.setProductList(productService.getAll());
				warehouse.setOrderList(orderService.getAll());
				
				FileOutputStream dataFile = new FileOutputStream(file);
				ObjectOutputStream outputStream = new ObjectOutputStream(dataFile);
				
				outputStream.writeObject(warehouse);
				
				outputStream.close();
				dataFile.close();
				
			} else {
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();
				
				Warehouse warehouse = new Warehouse();
				warehouse.setCategoryList(categoryService.getAll());
				warehouse.setProductList(productService.getAll());
				warehouse.setOrderList(orderService.getAll());
				
				FileOutputStream dataFile = new FileOutputStream(file);
				ObjectOutputStream outputStream = new ObjectOutputStream(dataFile);
				
				outputStream.writeObject(warehouse);
				
				outputStream.close();
				dataFile.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
