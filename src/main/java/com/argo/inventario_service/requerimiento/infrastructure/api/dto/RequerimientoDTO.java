package com.argo.inventario_service.requerimiento.infrastructure.api.dto;

import com.argo.inventario_service.requerimiento.domain.enm.TipoRequerimiento;

public class RequerimientoDTO {
    private String codigoRequerimiento;


    private int almacenRecibe;


    private TipoRequerimiento tipoRequerimiento;

    public String getCodigoRequerimiento() {
        return codigoRequerimiento;
    }

    public void setCodigoRequerimiento(String codigoRequerimiento) {
        this.codigoRequerimiento = codigoRequerimiento;
    }

    public int getAlmacenRecibe() {
        return almacenRecibe;
    }

    public void setAlmacenRecibe(int almacenRecibe) {
        this.almacenRecibe = almacenRecibe;
    }

    public TipoRequerimiento getTipoRequerimiento() {
        return tipoRequerimiento;
    }

    public void setTipoRequerimiento(TipoRequerimiento tipoRequerimiento) {
        this.tipoRequerimiento = tipoRequerimiento;
    }

    public RequerimientoDTO(String codigoRequerimiento, int almacenRecibe, TipoRequerimiento tipoRequerimiento) {
        this.codigoRequerimiento = codigoRequerimiento;
        this.almacenRecibe = almacenRecibe;
        this.tipoRequerimiento = tipoRequerimiento;
    }
}
