package br.com.guilherme.volvo.address;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public List<AddressEntity> getAll(){

        return repository.findAll();
    }
    public List<AddressEntity> findAllByZipCode(String zipCode){

        return repository.findAllByZipCode(zipCode);

    }

}
