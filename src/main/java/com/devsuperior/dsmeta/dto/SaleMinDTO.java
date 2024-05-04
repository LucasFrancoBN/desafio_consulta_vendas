package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public record SaleMinDTO(
    Long id,
    Double amount,
    LocalDate date
) {
}
