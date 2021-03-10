package com.santbetv.developermercado.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.santbetv.developermercado.persistence.entity.Compra;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {
	
    Optional<List<Compra>> findByIdCliente(String idCliente);
    
}
