package com.linkedin.learning.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.linkedin.learning.entity.RoomEntity;

@Repository
public interface PagedRoomRepository extends PagingAndSortingRepository<RoomEntity, Long> {

	Page<RoomEntity> findById(Long id, Pageable page);
}
