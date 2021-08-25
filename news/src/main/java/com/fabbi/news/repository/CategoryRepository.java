package com.fabbi.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabbi.news.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	CategoryEntity findOneByCode(String code);
}
