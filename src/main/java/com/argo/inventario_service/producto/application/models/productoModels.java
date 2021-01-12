package com.argo.inventario_service.producto.application.models;

import java.math.BigDecimal;

public interface productoModels {


    String getCodigo();

    String getNombre();

    BigDecimal getPrecioUnitario();

    int getCantidad();
}
