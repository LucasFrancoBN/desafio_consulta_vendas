package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportDTO>> getReport(
			@RequestParam(name = "maxDate", required = false, defaultValue = "") String finalDate,
			@RequestParam(name = "minDate", required = false, defaultValue = "") String initialDate,
			@RequestParam(name = "name", required = false, defaultValue = "") String name,
			@PageableDefault(size = 5, sort = "date") Pageable pageable
	) {
		LocalDate maxDate = finalDate.isEmpty() ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(finalDate);
		LocalDate minDate = initialDate.isEmpty() ? maxDate.minusYears(1L) : LocalDate.parse(initialDate);
		return ResponseEntity.ok(service.findAll(minDate, maxDate, name, pageable));
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryDTO>> getSummary(
			@RequestParam(name = "maxDate", required = false, defaultValue = "") String finalDate,
			@RequestParam(name = "minDate", required = false, defaultValue = "") String initialDate
	) {
		LocalDate maxDate = finalDate.isEmpty() ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(finalDate);
		LocalDate minDate = initialDate.isEmpty() ? maxDate.minusYears(1L) : LocalDate.parse(initialDate);
		return ResponseEntity.ok(service.findAllSummary(minDate, maxDate));
	}
}
