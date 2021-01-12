package com.argo.inventario_service.producto.infrastructure.api;

import java.math.BigDecimal;

public class precioModelo {

    BigDecimal precio;

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
