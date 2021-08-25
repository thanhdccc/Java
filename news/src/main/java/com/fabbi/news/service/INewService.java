package com.fabbi.news.service;

import java.util.List;

import com.fabbi.news.dto.NewDTO;

public interface INewService {

	NewDTO save(NewDTO newDTO);
	void delete(long[] ids);
	List<NewDTO> findAll();
}
