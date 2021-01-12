package com.argo.inventario_service.area.infrastructure;

import com.argo.inventario_service.area.domain.repository.IAlmacen;
import com.argo.inventario_service.area.domain.repository.IArea;
import com.argo.inventario_service.util.obtenerusuario;
import com.commons.user.models.entity.area.domain.Almacen;
import com.commons.user.models.entity.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class almacenController {

    @Autowired
    private IAlmacen iAlmacen;
    @Autowired
    private IArea iArea;

    @Autowired
    private obtenerusuario obtenerusuario;


    @RequestMapping("/almacen")
    public List listaAlmacen(@RequestHeader("Authorization") String token) {

        User user = this.obtenerusuario.getUser(token);
        Almacen idAlmacen = user.getIdAlmacen();

        int almacen = idAlmacen.getIdAlmacen();

        return this.iAlmacen.findByIdAlmacenIsNot(almacen);


    }

    @RequestMapping("/area")
    public List listaArea() {

        return iArea.findAll();

    }
}
