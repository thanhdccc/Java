package com.fabbi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabbi.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findOneByCode(String code);
}
