package com.obify.rms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "service")
@Setter
@Getter
@NoArgsConstructor
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String unit;
    private String status;
    private Long categoryId;
    private Long providerId;
    private List<ImageEntity> images;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
