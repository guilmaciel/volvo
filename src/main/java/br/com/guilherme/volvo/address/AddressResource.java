package br.com.guilherme.volvo.address;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Address")
public class AddressResource {

    @Inject
    private AddressService service;

    @GetMapping
    public List<AddressEntity> getAll(){

        return service.getAll();
    }

    @GetMapping(value="/{id}")
    public List<AddressEntity> getId(@PathVariable("id") String zipCode){

        return service.findAllByZipCode(zipCode);
    }
}
