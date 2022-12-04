package com.company.springwebflux.service;

import com.company.springwebflux.entity.CustomerEntity;
import com.company.springwebflux.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Flux<CustomerEntity> saveCustomer(Mono<CustomerEntity> customer) {
        return customerRepository.saveAll(customer);
    }

    public Mono<CustomerEntity> findCustomerById(Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public Flux<CustomerEntity> findCustomerList() {
        return customerRepository.findAll();
    }

    public Mono<CustomerEntity> updateCustomer(CustomerEntity customer, Long customerId) {

        return customerRepository.findById(customerId)
                .flatMap(c -> {
                    c.setName(customer.getName());
                    c.setAddress(customer.getAddress());
                    c.setPhoneNum(customer.getPhoneNum());
                    return customerRepository.save(customer);
                });
    }

}

