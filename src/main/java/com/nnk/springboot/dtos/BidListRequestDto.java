package com.nnk.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record BidListRequestDto(

       @NotBlank @Size(max = 30)
       String account,
       @NotBlank @Size(max = 30)
       String type,
       @NotNull @PositiveOrZero
       Double bidQuantity
) {}
