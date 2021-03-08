package com.santbetv.developermercado.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.santbetv.developermercado.domain.Product;
import com.santbetv.developermercado.persistence.entity.Producto;


/**
 * uses = como Category tiene su mapper propio debe de adicionar en estos casos
 * 
 * 
 * @author rizzoli
 *
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
	
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category"),
    })
    Product toProduct(Producto producto);
    
    List<Product> toProducts(List<Producto> productos);

    
    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}