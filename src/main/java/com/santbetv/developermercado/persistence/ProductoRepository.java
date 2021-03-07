/**
 * 
 */
package com.santbetv.developermercado.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.santbetv.developermercado.persistence.crud.ProductoCrudRepository;
import com.santbetv.developermercado.persistence.entity.Producto;

/**
 * @author rizzoli
 *
 */
//Estamos indicando a spring que esta clase es la se encarga de interantuar con la base de datos
@Repository
public class ProductoRepository {
	
	
    private ProductoCrudRepository productoCrudRepositor;
    
	

//    public List<Producto> getAll() {
//    	return List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
//    }

}
