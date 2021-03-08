
package com.santbetv.developermercado.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.santbetv.developermercado.domain.Product;
import com.santbetv.developermercado.domain.repository.ProductRepository;
import com.santbetv.developermercado.persistence.crud.ProductoCrudRepository;
import com.santbetv.developermercado.persistence.entity.Producto;
import com.santbetv.developermercado.persistence.mapper.ProductMapper;

/**
 * @author rizzoli
 *
 */
//Estamos indicando a spring que esta clase es la se encarga de interantuar con la base de datos
@Repository
public class ProductoRepository implements ProductRepository {


    private ProductoCrudRepository productoCrudRepository;


    private ProductMapper mapper;
    
    
    @Autowired
    public ProductoRepository(ProductoCrudRepository productoCrudRepository, ProductMapper mapper) {
		this.productoCrudRepository = productoCrudRepository;
		this.mapper = mapper;
	}

	@Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
    	//realizar conversion de product a producto
        Producto producto = mapper.toProducto(product);
      //realizar conversion de producto a product
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
