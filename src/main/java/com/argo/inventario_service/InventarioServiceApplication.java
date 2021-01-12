package com.argo.inventario_service;


import com.argo.inventario_service.producto.application.ProductoService;
import com.argo.inventario_service.producto.domain.ProductoAlmacen;
import com.argo.inventario_service.producto.domain.repository.CodigoListNuevo;
import com.argo.inventario_service.producto.domain.repository.IProduct;
import org.checkerframework.checker.signature.qual.IdentifierOrArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
public class InventarioServiceApplication implements CommandLineRunner {




    @Autowired
    private IProduct iProduct;

    public static void main(String[] args) {
        SpringApplication.run(InventarioServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {





    }

    @Bean(name = "poolnotification")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

}
