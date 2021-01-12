package com.argo.inventario_service.dashboard;

import com.argo.inventario_service.dashboard.models.dashboardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {


    @Autowired
    private DashboardService service;


    @GetMapping("")
    public ResponseEntity<?> dashboardData(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        dashboardModel dashboardModel = this.service.dashboardData(token);


        return new ResponseEntity<>(dashboardModel, HttpStatus.OK);


    }
}
