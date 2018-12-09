package com.srm.clientapi.mvc.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientDTO {
	
	public ClientDTO() {
		
	}

	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 5, max = 20)
	private String name;

	@NotNull
	private BigDecimal creditLimit;

    @JsonProperty("riskDTO")
	@NotNull
	private RiskDTO riskDTO;

}
