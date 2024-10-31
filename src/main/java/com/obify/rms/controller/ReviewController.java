package com.obify.rms.controller;

import com.obify.rms.dto.RequestReviewDTO;
import com.obify.rms.dto.ServiceDTO;
import com.obify.rms.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rms/api/v1/reviews")
@Validated
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ServiceDTO> addReview(@Valid @RequestBody ServiceDTO review){
        review = reviewService.addReview(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
    @PostMapping("/retrieve")
    public ResponseEntity<List<ServiceDTO>> getReviews(@RequestBody RequestReviewDTO requestReviewDTO){
        Pageable pageable = PageRequest.of(requestReviewDTO.getPageNo(), requestReviewDTO.getPageSize());
        List<ServiceDTO> reviews = reviewService.getReviews(requestReviewDTO.getOrganizationId(), requestReviewDTO.getStatus(), pageable);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @PatchMapping
    public ResponseEntity<ServiceDTO> updateStatus(@Valid @RequestBody RequestReviewDTO requestReviewDTO){
        ServiceDTO reviewDTO = reviewService.updateStatus(requestReviewDTO);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }
}
