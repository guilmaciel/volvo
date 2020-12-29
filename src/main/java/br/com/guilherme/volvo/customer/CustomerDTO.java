package br.com.guilherme.volvo.customer;

import br.com.guilherme.volvo.address.AddressDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class CustomerDTO implements Serializable {

    private int id;

    private String documentId;

    private String name;

    private int age;

    private List<AddressDTO> addressBook;

    private LocalDate registrationDate;

    private LocalDate lastUpdate;

}
