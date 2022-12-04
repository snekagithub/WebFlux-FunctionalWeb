package com.company.springwebflux.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class CustomerEntity {
    @Id
    private Long customerId;
    private String name;
    private String address;
    private Long phoneNum;
}
