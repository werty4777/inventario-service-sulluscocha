package com.argo.inventario_service.reporte;

import com.argo.inventario_service.reporte.models.ReporteModelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reporte")
public class reporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("")
    public ReporteModelo obtenerReporte(HttpServletRequest request){

        String token=request.getHeader("Authorization");
        return this.reporteService.cargarReporte(token);


    }
}
