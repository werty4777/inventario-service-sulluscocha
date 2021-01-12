package com.argo.inventario_service.producto.application.models;

import com.argo.inventario_service.requerimiento.domain.enm.EstadoRequerimiento;

import java.util.Date;

public interface OrdenModel {


    String getCodigo();
    Date getFechaOrden();
    EstadoRequerimiento getEstadoOrden();
    String getProveedor();


}
