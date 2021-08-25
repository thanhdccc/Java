package com.fabbi.news.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabbi.news.entity.RoleEntity;
import com.fabbi.news.util.ERole;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	
	Optional<RoleEntity> findByName(ERole name);
}
