package com.obify.rms.service;

import com.obify.rms.dto.RequestReviewDTO;
import com.obify.rms.dto.ServiceDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    ServiceDTO addReview(ServiceDTO dto);
    ServiceDTO updateStatus(RequestReviewDTO dto);
    List<ServiceDTO> getReviews(Long organizationId, String status, Pageable pageable);
}
