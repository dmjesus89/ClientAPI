package com.srm.clientapi.mvc.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.srm.clientapi.config.util.ObjectMapperUtils;
import com.srm.clientapi.mvc.model.dto.ClientDTO;
import com.srm.clientapi.mvc.model.entity.ClientEntity;
import com.srm.clientapi.mvc.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired 
    private ObjectMapperUtils objectMapperUtils;

	public List<ClientDTO> getClients() {
		List<ClientEntity> listUser = (List<ClientEntity>) clientRepository.findAll();
		List<ClientDTO> listClientsDTO = objectMapperUtils.mapAll(listUser, ClientDTO.class);
		return listClientsDTO;
	}

	public ClientDTO getClientById(Long id) throws NoSuchElementException {
		Optional<ClientEntity> option = clientRepository.findById(id);
		if (null != option && option.get() != null) {
			ClientDTO clientDTO = objectMapperUtils.map(option.get(), ClientDTO.class);
			return clientDTO;
		}
		throw new EmptyResultDataAccessException(1);
	}

	public ClientDTO save(ClientDTO clientDTO) {
		
		// chamada para validacao de juros
		ClientEntity clientEntity = objectMapperUtils.map(clientDTO, ClientEntity.class);
		saveUser(clientEntity);
		clientDTO = objectMapperUtils.map(clientEntity, ClientDTO.class);
		return clientDTO;
	}

	private ClientEntity saveUser(final ClientEntity clientEntity) {
		clientRepository.save(clientEntity);
		return clientEntity;
	}

}
