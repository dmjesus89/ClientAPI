package com.srm.clientapi.mvc.resource;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srm.clientapi.mvc.model.dto.RiskDTO;
import com.srm.clientapi.mvc.service.RiskServiceImpl;

@RestController
@RequestMapping("/api/risks")
public class RiskResource {

	@Autowired
	private RiskServiceImpl riskService;

	@GetMapping
	public ResponseEntity<List<RiskDTO>> getUsers() {
		List<RiskDTO> listRisk = riskService.getRisks();
		if (!listRisk.isEmpty()) {
			return ResponseEntity.ok(listRisk);
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<RiskDTO> getUserById(@PathParam(value = "id") Long id) {
		RiskDTO risk = riskService.getById(id);
		if (risk != null) {
			return ResponseEntity.ok(risk);
		}
		return ResponseEntity.notFound().build();
	}

}
