package com.srm.clientapi.mvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.srm.clientapi.mvc.model.entity.ClientEntity;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

}
