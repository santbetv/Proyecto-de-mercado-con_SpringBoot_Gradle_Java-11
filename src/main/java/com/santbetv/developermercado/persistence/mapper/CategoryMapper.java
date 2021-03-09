package com.santbetv.developermercado.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.santbetv.developermercado.domain.Category;
import com.santbetv.developermercado.persistence.entity.Categoria;

//Lo integramos con String componente
@Mapper(componentModel = "spring")
public interface CategoryMapper {

	// Generamos la conversion de la entity en persitence al dominio category
	@Mappings({ @Mapping(source = "idCategoria", target = "categoryId"),
			@Mapping(source = "descripcion", target = "category"), 
			@Mapping(source = "estado", target = "active"), })
	Category toCategory(Categoria categoria);

	// Anotacion que indica que la conversion es al inversa
	@InheritInverseConfiguration
	@Mapping(target = "productos", ignore = true) // ignoramos un atributo que no requrimos de entity en persitence
	Categoria toCategoria(Category category);
}
