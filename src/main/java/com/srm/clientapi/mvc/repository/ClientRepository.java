package com.srm.clientapi.mvc.repository;

import org.springframework.data.repository.CrudRepository;

import com.srm.clientapi.mvc.entity.ClientEntity;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

}
