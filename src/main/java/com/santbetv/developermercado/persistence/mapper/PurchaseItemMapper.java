package com.santbetv.developermercado.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.santbetv.developermercado.domain.PurchaseItem;
import com.santbetv.developermercado.persistence.entity.ComprasProducto;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

	/**
	 * Se debe de llamar un atributo con . cuanto es una entidad compuesta
	 * Si en la clase dominio de English se llama igual que la entity se debe de omitir.
	 * 
	 */
	@Mappings({
        @Mapping(source = "id.idProducto", target = "productId"),
        @Mapping(source = "cantidad", target = "quantity"),
        @Mapping(source = "estado", target = "active")
	})
	PurchaseItem toPurchaseItem(ComprasProducto producto);
	
	//mtodologia para realizar conversion contraria
	//simpre ignorar lo que no se require
	@InheritInverseConfiguration
	@Mappings({
        @Mapping(target = "compra", ignore = true),
        @Mapping(target = "producto", ignore = true),
        @Mapping(target = "id.idCompra", ignore = true)
	})
	ComprasProducto toComprasProducto(PurchaseItem item);
}
