package br.com.guilherme.volvo.address;


import br.com.guilherme.volvo.customer.CustomerEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ADDRESS_BOOK")
@Data
public class AddressEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String zipCode;

    private int number;

    @ManyToMany(mappedBy = "addressBook" )
    private List<CustomerEntity> customerEntity;


}
