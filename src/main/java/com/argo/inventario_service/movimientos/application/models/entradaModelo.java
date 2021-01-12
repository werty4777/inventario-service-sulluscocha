package com.argo.inventario_service.movimientos.application.models;

import com.argo.inventario_service.movimientos.domain.enm.estadoMovimiento;

import java.util.Date;

public interface entradaModelo {

    int getIdEntrada();
    Date getFechaEntrada();
    String getEstadoMovimiento();
    String getTipoComprobante();
    String getTipoEntrada();
}
