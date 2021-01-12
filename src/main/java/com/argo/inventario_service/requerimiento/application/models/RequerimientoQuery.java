package com.argo.inventario_service.requerimiento.application.models;

import com.argo.inventario_service.requerimiento.domain.enm.EstadoRequerimiento;
import com.argo.inventario_service.requerimiento.domain.enm.TipoRequerimiento;

import java.util.Date;

public interface RequerimientoQuery {

    String getCodigo();
    String getRemitente();
    Date getFechaEmision();
    TipoRequerimiento getTipoRequerimiento();
    EstadoRequerimiento getEstadoRequerimiento();
    String getEmpleado();
}
