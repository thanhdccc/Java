package com.fabbi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabbi.dao.INewDAO;
import com.fabbi.model.NewModel;
import com.fabbi.service.INewService;

@Service
public class NewService implements INewService {
	
	@Autowired
	private INewDAO newDao;
	
	@Override
	public List<NewModel> findAll() {
		return newDao.findAll();
	}
}
