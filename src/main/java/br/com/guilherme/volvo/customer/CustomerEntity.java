package br.com.guilherme.volvo.customer;

import br.com.guilherme.volvo.address.AddressEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="CUSTOMER")
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String documentId;

    private String name;

    private int age;

    private LocalDate registrationDate;

    private LocalDate lastUpdate;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="CUSTOMER_ADDRESS_BOOK", joinColumns = {@JoinColumn(name="customer_id")}, inverseJoinColumns = {@JoinColumn(name = "address_id")})
    private List<AddressEntity> addressBook;

    public void addAddressBook(AddressEntity entity){
        addressBook.add(entity);
        entity.getCustomerEntity().add(this);
    }
    public void removeAddressBook(AddressEntity entity){
        addressBook.remove(entity);
        entity.getCustomerEntity().remove(this);
    }


}
