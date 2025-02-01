package com.example.demo.mappings;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String lase_name;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="address_id")
//    private Address address;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "customer_id")
//    private List<Address> addresses;
    @ManyToMany
    @JoinTable(
            name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;
}
