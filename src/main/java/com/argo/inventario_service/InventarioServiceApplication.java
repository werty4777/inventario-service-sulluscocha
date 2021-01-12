package com.argo.inventario_service;


import com.argo.inventario_service.producto.application.ProductoService;
import com.argo.inventario_service.producto.domain.ProductoAlmacen;
import com.argo.inventario_service.producto.domain.repository.CodigoListNuevo;
import com.argo.inventario_service.producto.domain.repository.IProduct;
import org.checkerframework.checker.signature.qual.IdentifierOrArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@EntityScan({"com.commons.user.models.entity", "com.argo.inventario_service"})
@EnableFeignClients
@EnableEurekaClient
@EnableAsync
@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class InventarioServiceApplication implements CommandLineRunner {



    @Autowired
    private IProduct iProduct;

    public static void main(String[] args) {
        SpringApplication.run(InventarioServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        List<ProductoAlmacen> producto = this.iProduct.getProducto(3);
        List<ProductoAlmacen> producto2 = new ArrayList<>(producto);


        AtomicReference<Integer> index=new AtomicReference<>(0);
        AtomicReference<List<ProductoAlmacen>> valoresRepetidos=new AtomicReference<>();
        valoresRepetidos.set(new ArrayList<>());
        List<List<ProductoAlmacen>> listaFiltrada = producto.stream().map(productoAlmacen -> {

            List<ProductoAlmacen> collect1 = producto2.stream().filter(pros -> pros.getId().getIndex().equals(productoAlmacen.getId().getIndex()) && pros.getId().getCodigo().equals(productoAlmacen.getId().getCodigo())).collect(Collectors.toList());

            if(!valoresRepetidos.get().contains(productoAlmacen)){
                System.out.println("no lo contiene");
                valoresRepetidos.get().addAll(collect1);
                return collect1;
            }
            return null;

        }).filter(Objects::nonNull).collect(Collectors.toList());

        List<CodigoListNuevo> listaNueva = listaFiltrada.stream().map(productoAlmacens -> {

            int stock = productoAlmacens.stream().mapToInt(ProductoAlmacen::getStock).sum();
            ProductoAlmacen productoAlmacen = productoAlmacens.get(productoAlmacens.size() - 1);


            return new CodigoListNuevo() {
                @Override
                public String getCodigo() {
                    return productoAlmacen.getId().getCodigo().getCodigo();
                }

                @Override
                public String getDescripcion() {
                    return productoAlmacen.getId().getCodigo().getDescripcion();
                }

                @Override
                public String getModelo() {
                    return productoAlmacen.getId().getCodigo().getModelo();
                }

                @Override
                public String getMarca() {
                    return productoAlmacen.getId().getCodigo().getMarca();
                }

                @Override
                public String getColor() {
                    return productoAlmacen.getId().getCodigo().getColor();
                }

                @Override
                public String getTalla() {
                    return productoAlmacen.getId().getCodigo().getTalla();
                }

                @Override
                public String getTipo() {
                    return productoAlmacen.getId().getCodigo().getTipo().getTipo();

                }

                @Override
                public BigDecimal getPrecio() {
                    return productoAlmacen.getId().getCodigo().getPrecioUnitario();
                }

                @Override
                public int getCantidad() {
                    return stock;
                }
            };

        }).filter(Objects::nonNull).collect(Collectors.toList());

        listaNueva.forEach(codigoListNuevo -> {
            System.out.println(codigoListNuevo.getCantidad());
        });




    }

    @Bean(name = "poolnotification")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

}
