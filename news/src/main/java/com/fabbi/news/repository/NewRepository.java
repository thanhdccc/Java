package com.fabbi.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabbi.news.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {

	NewEntity findOneById(Long id);

}
