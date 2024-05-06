package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query(value = "SELECT new com.devsuperior.dsmeta.dto.ReportDTO(sale.id, sale.date, sale.amount, sale.seller.name) FROM Sale sale " +
      "WHERE sale.date <= :maxDate " +
      "AND sale.date >= :minDate " +
      "AND UPPER(sale.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",
    countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller"
  )
  Page<ReportDTO> searchReportWithParams(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

  @Query("SELECT new com.devsuperior.dsmeta.dto.SummaryDTO(sale.seller.name, SUM(sale.amount)) FROM Sale sale " +
      "WHERE sale.date >= :minDate " +
      "AND sale.date <= :maxDate " +
      "GROUP BY sale.seller.name")
  List<SummaryDTO> searchSummary(LocalDate minDate, LocalDate maxDate);
}
