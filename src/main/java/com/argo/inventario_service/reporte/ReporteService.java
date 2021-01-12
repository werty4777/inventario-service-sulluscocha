package com.argo.inventario_service.reporte;

import com.argo.inventario_service.area.domain.repository.IAlmacen;
import com.argo.inventario_service.movimientos.domain.repository.IEntrada;
import com.argo.inventario_service.movimientos.domain.repository.IEntradaDetalles;
import com.argo.inventario_service.movimientos.domain.repository.ISalida;
import com.argo.inventario_service.movimientos.domain.repository.ISalidaDetalles;
import com.argo.inventario_service.producto.domain.repository.ICodigoProducto;
import com.argo.inventario_service.producto.domain.repository.IProduct;
import com.argo.inventario_service.reporte.models.DetallesReporteModelo;
import com.argo.inventario_service.reporte.models.ReporteModelo;
import com.argo.inventario_service.requerimiento.domain.repository.IDetallesRequerimiento;
import com.argo.inventario_service.requerimiento.domain.repository.IRequerimiento;
import com.argo.inventario_service.util.obtenerusuario;
import com.argo.inventario_service.util.transformarFecha;
import com.commons.user.models.entity.area.domain.Almacen;
import com.commons.user.models.entity.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    private final IEntradaDetalles iEntradaDetalles;
    private final IEntrada iEntrada;
    private final IProduct iProduct;
    private final IRequerimiento iRequerimiento;
    private final IDetallesRequerimiento iDetallesRequerimiento;
    private final transformarFecha fecha;
    private final ICodigoProducto iCodigoProducto;
    private final IAlmacen iAlmacen;
    private final ISalida iSalida;
    private final ISalidaDetalles iSalidaDetalles;
    @Autowired
    private obtenerusuario getUser;

    public ReporteService(IEntradaDetalles iEntradaDetalles, IEntrada iEntrada, IProduct iProduct, IRequerimiento iRequerimiento, IDetallesRequerimiento iDetallesRequerimiento, transformarFecha fecha, ICodigoProducto iCodigoProducto, IAlmacen iAlmacen, ISalida iSalida, ISalidaDetalles iSalidaDetalles) {
        this.iEntradaDetalles = iEntradaDetalles;
        this.iEntrada = iEntrada;
        this.iProduct = iProduct;
        this.iRequerimiento = iRequerimiento;
        this.iDetallesRequerimiento = iDetallesRequerimiento;
        this.fecha = fecha;
        this.iCodigoProducto = iCodigoProducto;
        this.iAlmacen = iAlmacen;
        this.iSalida = iSalida;
        this.iSalidaDetalles = iSalidaDetalles;
    }


    public ReporteModelo cargarReporte(String token) {


        try {
            User user = this.getUser.getUser(token);
            Almacen almacen = user.getIdAlmacen();


            List<DetallesReporteModelo> detalleReporte = this.iCodigoProducto.findAll().stream().map(codigoProducto -> {

                int entrada = 0;
                int salida = 0;
                int stock = 0;
                System.out.println(codigoProducto);
                BigDecimal bigDecimal1 = this.iEntradaDetalles.sumaTotales(almacen.getIdAlmacen(), codigoProducto.getCodigo());
                BigDecimal sumaTotales = bigDecimal1 == null ? BigDecimal.ZERO : bigDecimal1;

                System.out.println(sumaTotales);
                BigDecimal bigDecimal2 = this.iEntradaDetalles.misEntradasDetalles(almacen, codigoProducto);
                entrada = bigDecimal2 == null ? 0 : bigDecimal2.intValue();
                System.out.println(entrada);
                BigDecimal bigDecimal3 = this.iSalidaDetalles.misSalidas(almacen, codigoProducto);
                salida = bigDecimal3 == null ? 0 : bigDecimal3.intValue();
                System.out.println(salida);
                stock = entrada - salida;
                System.out.println(stock);

                BigDecimal pukardex = entrada != 0 ? (sumaTotales.divide(new BigDecimal(entrada), RoundingMode.HALF_UP)) : BigDecimal.ZERO;
                System.out.println(pukardex);
                BigDecimal existenciasEntradas = sumaTotales;
                System.out.println(existenciasEntradas);
                BigDecimal bigDecimal = this.iSalidaDetalles.sumaSalida(almacen, codigoProducto);
                BigDecimal existenciasSalidas = this.iSalidaDetalles.sumaSalida(almacen, codigoProducto) == null ? BigDecimal.ZERO : bigDecimal;
                System.out.println(existenciasSalidas);
                BigDecimal totalExistencias = existenciasEntradas.subtract(existenciasSalidas);
                System.out.println(totalExistencias);

                if(entrada==0 && salida==0){

                return null;
                }

                return new DetallesReporteModelo(
                        codigoProducto.getCodigo(), codigoProducto.getDescripcion(), codigoProducto.getModelo(), codigoProducto.getMarca(),
                        codigoProducto.getColor(), codigoProducto.getTalla(), codigoProducto.getTipo().getTipo(), codigoProducto.getUnidadMedida(),
                        entrada, salida, stock, sumaTotales, pukardex, existenciasEntradas, existenciasSalidas, totalExistencias);


            }).filter(detallesReporteModelo -> {

                if(detallesReporteModelo==null){
                    return false;
                }else{
                    return true;
                }



            }).collect(Collectors.toList());

            return new ReporteModelo(detalleReporte);


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }


    }


}
