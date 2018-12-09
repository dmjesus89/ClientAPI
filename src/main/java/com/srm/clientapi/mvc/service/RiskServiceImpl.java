package com.srm.clientapi.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.srm.clientapi.config.util.ObjectMapperUtils;
import com.srm.clientapi.mvc.model.dto.RiskDTO;
import com.srm.clientapi.mvc.model.entity.RiskEntity;
import com.srm.clientapi.mvc.repository.RiskRepository;

@Service
public class RiskServiceImpl {

	@Autowired
	private RiskRepository riskRepository;

	@Autowired
	private ObjectMapperUtils objectMapperUtils;

	public List<RiskDTO> getRisks() {
		List<RiskEntity> listRiskEntity = (List<RiskEntity>) riskRepository.findAll();
		List<RiskDTO> listRiskDTO = objectMapperUtils.mapAll(listRiskEntity, RiskDTO.class);
		return listRiskDTO;
	}

	public RiskDTO getById(Long id) {
		Optional<RiskEntity> option = riskRepository.findById(id);
		if (null != option && option.get() != null) {
			RiskDTO riskDTO = objectMapperUtils.map(option.get(), RiskDTO.class);
			return riskDTO;
		}
		throw new EmptyResultDataAccessException(1);
	}

}
