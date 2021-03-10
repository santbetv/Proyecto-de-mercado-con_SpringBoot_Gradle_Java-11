package com.santbetv.developermercado.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.santbetv.developermercado.domain.Purchase;
import com.santbetv.developermercado.domain.repository.PurchaseRepository;
import com.santbetv.developermercado.persistence.crud.CompraCrudRepository;
import com.santbetv.developermercado.persistence.entity.Compra;
import com.santbetv.developermercado.persistence.mapper.PurchaseMapper;


@Repository
public class CompraRepository implements PurchaseRepository {

    private CompraCrudRepository compraCrudRepository;
    private PurchaseMapper mapper;
    
    
    @Autowired
    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper mapper) {
		this.compraCrudRepository = compraCrudRepository;
		this.mapper = mapper;
	}

	@Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        //Guardamos en cascada
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
