package com.fabbi.news.converter;

import org.springframework.stereotype.Component;

import com.fabbi.news.dto.NewDTO;
import com.fabbi.news.entity.NewEntity;

@Component
public class NewConverter {

	public NewEntity toEntity(NewDTO dto) {
		NewEntity result = new NewEntity();
		result.setTitle(dto.getTitle());
		result.setContent(dto.getContent());
		result.setShortDescription(dto.getShortDescription());
		return result;
	}
	
	public NewDTO toDTO(NewEntity entity) {
		NewDTO result = new NewDTO();
		if(entity.getId() != null) {
			result.setId(entity.getId());
		}
		result.setTitle(entity.getTitle());
		result.setContent(entity.getContent());
		result.setShortDescription(entity.getShortDescription());
		result.setCreatedDate(entity.getCreatedDate());
		result.setCreatedBy(entity.getCreatedBy());
		result.setModifiedDate(entity.getModifiedDate());
		result.setModifiedBy(entity.getModifiedBy());
		result.setCategoryCode(entity.getCategory().getCode());
		return result;
	}
	
	public NewEntity toEntity(NewDTO dto, NewEntity entity) {
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}
}
