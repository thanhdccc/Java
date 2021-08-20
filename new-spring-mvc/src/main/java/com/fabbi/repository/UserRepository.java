package com.fabbi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabbi.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUserNameAndStatus(String name, int status);
}
