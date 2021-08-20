package com.fabbi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fabbi.dao.INewDAO;
import com.fabbi.mapper.NewMapper;
import com.fabbi.model.NewModel;

@Repository
public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		return query(sql.toString(), new NewMapper());
	}
}
