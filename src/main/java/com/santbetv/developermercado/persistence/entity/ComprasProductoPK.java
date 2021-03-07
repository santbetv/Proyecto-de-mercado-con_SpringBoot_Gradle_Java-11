package com.santbetv.developermercado.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * No mapea datos, solo embebe destro de compras producto
 * 
 * @name santiago betancur villegas
 * @author santiago-betancur@hotmail.com
 *
 */
//atributo de spring data para embeber, con implementacion Serializable, par que navegue por la web
@Embeddable
public class ComprasProductoPK implements Serializable {


	@Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    
    
	private static final long serialVersionUID = 4397676212273922136L;
}
