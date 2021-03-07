package com.santbetv.developermercado.persistence.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * 
 * Esta clase tiene como referencia dos llave FK, se debe de impletar una agregacion de otra cla
 * que las contenga, y embeber de esta
 * @name santiago betancur villegas
 * @author santiago-betancur@hotmail.com
 *
 */

@Entity
@Table(name = "compras_productos")
public class ComprasProducto {
	//Se anota con con este @ para llaves compuestas 
    @EmbeddedId
    private ComprasProductoPK id;

    private Integer cantidad;
    private Double total;
    private Boolean estado;

    //de muchos a uno
    @ManyToOne
    @MapsId("idCompra")
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    //de muchos a uno
    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
