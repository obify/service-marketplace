package com.obify.rms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.obify.rms.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ServiceEntity, Long> {
    Page<ServiceEntity> findAllByOrganizationIdAndStatusContainsOrderByCreatedAtAsc(Long organizationId, String status, Pageable pageable);
}
