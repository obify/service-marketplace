package com.obify.rms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderDTO {
    private Long id;
    @NotNull(message = "Organization name is mandatory")
    private String name;
    @NotNull(message = "Organization email is mandatory")
    private String email;
    @NotNull(message = "Organization phone is mandatory")
    private String phone;
    private LocalDate createdAt;
}
