package com.srm.clientapi.mvc.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RiskDTO {
	
	public RiskDTO() {
		
	}

	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 5, max = 20)
	private String name;

	private BigDecimal chargesRate;

}
