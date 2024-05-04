package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
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
		System.out.println(name);
		return repository.searchReportWithParams(minDate, maxDate, name, pageable);
	}

	private SaleMinDTO toSaleMinDTO(Sale entity) {
		return new SaleMinDTO(
				entity.getId(),
				entity.getAmount(),
				entity.getDate()
		);
	}
}
