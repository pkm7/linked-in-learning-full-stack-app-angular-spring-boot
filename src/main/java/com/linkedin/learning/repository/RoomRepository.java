package com.linkedin.learning.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.linkedin.learning.entity.RoomEntity;

public interface RoomRepository extends CrudRepository<RoomEntity, Long>{

	Optional<RoomEntity> findById(Long id);
	
	
}
