package com.company.springwebflux.routerfunction;

import com.company.springwebflux.handlerfunction.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {
    @Autowired
    private Handler handler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/get/customers"), handler::getAll)
                .andRoute(RequestPredicates.GET("/get/customer/{customerId}"), handler::getId)
                 .andRoute(RequestPredicates.POST("/add/customer"), handler::addCustomer)
                .andRoute(RequestPredicates.PUT("/update/customer/{customerId}"), handler::updateCustomerById);
    }
}
