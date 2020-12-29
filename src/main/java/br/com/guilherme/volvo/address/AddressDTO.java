package br.com.guilherme.volvo.address;

import lombok.Data;

import java.io.Serializable;


@Data
public class AddressDTO implements Serializable {

    private int id;
    private String zipCode;
    private int number;

}
