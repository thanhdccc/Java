package com.fabbi.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private ModelMapper mapper;

	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = new NewEntity();
		if (newDTO.getId() != null) {
			NewEntity oldNewEntity = newRepository.findOneById(newDTO.getId());
			oldNewEntity = mapper.map(newDTO, NewEntity.class);
			newEntity = oldNewEntity;
		} else {
			newEntity = mapper.map(newDTO, NewEntity.class);
		}
		
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);
		NewDTO result = mapper.map(newEntity, NewDTO.class);
		result.setCategoryCode(categoryEntity.getCode());
		
		return result;
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
			NewDTO newDTO = mapper.map(item, NewDTO.class);
			newDTO.setCategoryCode(item.getCategory().getCode());
			results.add(newDTO);
		}
		return results;
	}
}
