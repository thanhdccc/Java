package com.fabbi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabbi.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {

}
