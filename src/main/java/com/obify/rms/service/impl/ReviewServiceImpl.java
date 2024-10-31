package com.obify.rms.service.impl;

import com.obify.rms.dto.ErrorDTO;
import com.obify.rms.dto.RequestReviewDTO;
import com.obify.rms.dto.ServiceDTO;
import com.obify.rms.entity.ServiceEntity;
import com.obify.rms.exception.BusinessException;
import com.obify.rms.repository.ReviewRepository;
import com.obify.rms.service.ReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ServiceDTO addReview(ServiceDTO dto) {
        ServiceEntity re = new ServiceEntity();
        BeanUtils.copyProperties(dto, re);
        re.setCreatedAt(LocalDate.now());
        re = reviewRepository.save(re);
        BeanUtils.copyProperties(re, dto);
        return dto;
    }

    @Override
    public ServiceDTO updateStatus(RequestReviewDTO dto) {
        ServiceEntity re = reviewRepository.findById(dto.getReviewId())
                .orElseThrow(()->new BusinessException(List.of(new ErrorDTO("NOT_FOUND", "Review id not found"))));
        re.setStatus(dto.getStatus());
        re = reviewRepository.save(re);
        ServiceDTO reviewDTO = new ServiceDTO();
        BeanUtils.copyProperties(re, reviewDTO);
        return reviewDTO;
    }

    @Override
    public List<ServiceDTO> getReviews(Long organizationId, String status, Pageable pageable) {
        Page<ServiceEntity> pgReviews = reviewRepository.findAllByOrganizationIdAndStatusContainsOrderByCreatedAtAsc(organizationId, status, pageable);
        List<ServiceDTO> dtoList = null;
        if(!pgReviews.isEmpty()){
            dtoList = pgReviews.stream().map(re->{
                ServiceDTO reviewDTO = new ServiceDTO();
                BeanUtils.copyProperties(re, reviewDTO);
                return reviewDTO;
            }).collect(Collectors.toList());
        }
        return dtoList;
    }
}
