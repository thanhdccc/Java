package com.fabbi.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabbi.news.converter.NewConverter;
import com.fabbi.news.dto.NewDTO;
import com.fabbi.news.entity.CategoryEntity;
import com.fabbi.news.entity.NewEntity;
import com.fabbi.news.repository.CategoryRepository;
import com.fabbi.news.repository.NewRepository;
import com.fabbi.news.service.INewService;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewConverter newConverter;

	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = new NewEntity();
		if (newDTO.getId() != null) {
			NewEntity oldNewEntity = newRepository.findOneById(newDTO.getId());
			newEntity = newConverter.toEntity(newDTO, oldNewEntity);
		} else {
			newEntity = newConverter.toEntity(newDTO);
		}
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}

	@Override
	public void delete(long[] ids) {
		for(long item: ids) {
			newRepository.deleteById(item);
		}
	}

	@Override
	public List<NewDTO> findAll() {
		List<NewDTO> results = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll();
		for (NewEntity item : entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}
}
