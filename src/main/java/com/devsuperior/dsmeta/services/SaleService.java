package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {
	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return toSaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<ReportDTO> findAll(
			LocalDate minDate,
			LocalDate maxDate,
			String name,
			Pageable pageable
	) {
		return repository.searchReportWithParams(minDate, maxDate, name, pageable);
	}

	@Transactional(readOnly = true)
	public List<SummaryDTO> findAllSummary(LocalDate minDate, LocalDate maxDate) {
		return repository.searchSummary(minDate, maxDate);
	}

	private SaleMinDTO toSaleMinDTO(Sale entity) {
		return new SaleMinDTO(
				entity.getId(),
				entity.getAmount(),
				entity.getDate()
		);
	}
}
