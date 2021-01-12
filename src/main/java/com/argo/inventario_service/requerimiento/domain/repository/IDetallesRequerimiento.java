package com.argo.inventario_service.requerimiento.domain.repository;


import com.argo.inventario_service.requerimiento.domain.DetallesRequerimiento;
import com.argo.inventario_service.requerimiento.domain.Requerimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetallesRequerimiento extends JpaRepository<DetallesRequerimiento,Integer> {


    List<DetallesRequerimiento> findByIdRequerimiento(Requerimiento requerimiento);
}
