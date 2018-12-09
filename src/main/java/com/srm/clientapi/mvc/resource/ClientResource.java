package com.srm.clientapi.mvc.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srm.clientapi.api.event.ResourceCreateEvent;
import com.srm.clientapi.mvc.model.dto.ClientDTO;
import com.srm.clientapi.mvc.service.ClientServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/clients")
public class ClientResource {

	@Autowired
	private ClientServiceImpl clientService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO clientDTO, HttpServletResponse response) {
		clientDTO = clientService.save(clientDTO);
		publisher.publishEvent(new ResourceCreateEvent(this, response, clientDTO.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clientDTO);
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> getUsers() {
		List<ClientDTO> listUser = clientService.getClients();
		if (!listUser.isEmpty()) {
			return ResponseEntity.ok(listUser);
		}
		return ResponseEntity.ok().build();
	}
	
	

}
