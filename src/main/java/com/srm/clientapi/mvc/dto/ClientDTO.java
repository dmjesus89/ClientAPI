package com.srm.clientapi.mvc.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClientDTO {

	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 5, max = 20)
	private String name;

	@NotNull
	private BigDecimal creditLimit;
	
	@NotNull
	private Integer chargesRate;

}
