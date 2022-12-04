package com.company.springwebflux.repository;

import com.company.springwebflux.entity.CustomerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<CustomerEntity,Long> {
    Mono<CustomerEntity> findByCustomerId(Long customerId);
}
