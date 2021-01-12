package com.argo.inventario_service.util;

import com.argo.inventario_service.requerimiento.infrastructure.api.dto.RequerimientoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notificaciones-service")
public interface enviarNotificacion {


    @PostMapping("/notificaciones/{id}")
    ResponseEntity<?> enviarNotificacion(@RequestBody RequerimientoDTO requerimientoDTO, @PathVariable("id") int tipo);


}
