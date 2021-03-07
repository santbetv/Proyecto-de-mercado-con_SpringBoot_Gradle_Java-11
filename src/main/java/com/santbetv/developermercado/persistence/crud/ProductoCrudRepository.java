/**
 * 
 */
package com.santbetv.developermercado.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.santbetv.developermercado.persistence.entity.Producto;

/**
 * @author rizzoli
 *
 */
//Colocar de tipo interface el crud a utilizar y heredamos de otra interface de srping nos definen
//los metodos que relizaran las consultas poor nosotros.
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
	
	
	
	

}
