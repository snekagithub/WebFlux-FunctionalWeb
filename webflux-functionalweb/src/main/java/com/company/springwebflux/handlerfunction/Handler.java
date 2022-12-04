package com.company.springwebflux.handlerfunction;

import com.company.springwebflux.entity.CustomerEntity;
import com.company.springwebflux.service.CustomerService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;

@Service
public class Handler {
    private CustomerService customerService;

    public Handler(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(
                        customerService.findCustomerList(), CustomerEntity.class
                );
    }

    public Mono<ServerResponse> getId(ServerRequest serverRequest) {

        //String pathVariable = request.pathVariable("student_id");
        Long customerId = Long.valueOf(serverRequest.pathVariable("customerId"));
        return ServerResponse
                .ok()
                .body(
                        customerService.findCustomerById(customerId), CustomerEntity.class
                );
    }
    public Mono<ServerResponse> addCustomer(ServerRequest serverRequest) {
        Mono<CustomerEntity> customer = serverRequest.body(toMono(CustomerEntity.class));
        return ServerResponse.ok()
                .body(customerService.saveCustomer(customer),CustomerEntity.class);
    }
//    public Mono<ServerResponse> update(ServerRequest serverRequest) {
//        Mono<CustomerEntity> customer = serverRequest.body(toMono(CustomerEntity.class));
//        Long customerId = Long.valueOf(serverRequest.pathVariable("customerId"));
//        return ServerResponse.ok()
//                .body(customerService.updateCustomer(customer,customerId),CustomerEntity.class);
//    }

    public Mono<ServerResponse> updateCustomerById(ServerRequest request) {
        Long  customerId = Long.valueOf(request.pathVariable("customerId"));
        Mono<CustomerEntity> customer = request.bodyToMono(CustomerEntity.class);

        return customer
                .flatMap(c -> ServerResponse
                        .ok()
                        .body(customerService.updateCustomer(c, customerId), CustomerEntity.class)
                );
    }
}
