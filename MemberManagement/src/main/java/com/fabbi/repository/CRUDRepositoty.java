package com.fabbi.repository;

import java.util.List;

public interface CRUDRepositoty<T> {

	public boolean add(T obj);

	public boolean update(T obj);

	public boolean delete(T obj);

	public T getById(int id);

	public T getByName(String name);

	public List<T> getAll();
}
