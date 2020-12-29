package br.com.guilherme.volvo.customer;


import br.com.guilherme.volvo.address.AddressDTO;
import br.com.guilherme.volvo.address.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("Customer")
@Validated
public class CustomerResource {
    @Inject
    private CustomerService service;

    @GetMapping
    public ResponseEntity findAll(){
        List<CustomerDTO> dtos = new ArrayList<>();

        for (CustomerEntity entity: service.getAllCustomers()) {
            CustomerDTO dto = new CustomerDTO();
            BeanUtils.copyProperties(entity, dto);

            List<AddressDTO> addressDTOS = new ArrayList<>();
            for(AddressEntity addressEntity: entity.getAddressBook()){
                AddressDTO addressDTO = new AddressDTO();
                BeanUtils.copyProperties(addressEntity, addressDTO);
                addressDTOS.add(addressDTO);
            }
            dto.setAddressBook(addressDTOS);
            dtos.add(dto);
        }

        return ResponseEntity.ok(dtos);
    }
    @GetMapping(value="/zipcode/{zipCode}")
    public ResponseEntity findByZipCode(@PathVariable("zipCode")String zipCode){
        List<CustomerDTO> dtos = new ArrayList<>();
        System.out.println(zipCode);
        for (CustomerEntity entity: service.findCustomersByZipCode(zipCode)) {
            CustomerDTO dto = new CustomerDTO();
            BeanUtils.copyProperties(entity, dto);

            List<AddressDTO> addressDTOS = new ArrayList<>();
            for(AddressEntity addressEntity: entity.getAddressBook()){
                AddressDTO addressDTO = new AddressDTO();
                BeanUtils.copyProperties(addressEntity, addressDTO);
                addressDTOS.add(addressDTO);
            }
            dto.setAddressBook(addressDTOS);
            dtos.add(dto);
        }

        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity findById(@PathVariable("id") int id){
        if(!service.isIdExist(id)){
            return ResponseEntity.badRequest().body("Id not found");
        }else{

            CustomerDTO dto = new CustomerDTO();
            CustomerEntity entity = service.findById(id);
            BeanUtils.copyProperties(entity, dto);
            List<AddressDTO> addressDTOS = new ArrayList<>();
            for(AddressEntity addressEntity: entity.getAddressBook()){
                AddressDTO addressDTO = new AddressDTO();
                BeanUtils.copyProperties(addressEntity, addressDTO);
                addressDTOS.add(addressDTO);
            }
            return ResponseEntity.ok(dto);
        }

    }

    @PostMapping
    public ResponseEntity saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){

        System.out.println(customerDTO);

        if(!isValidZipCode(customerDTO.getAddressBook())){
            return ResponseEntity.badRequest().body("Invalid zip code");
        }
        service.saveCustomer(customerDTO);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @PutMapping(value="/{id}")
    public ResponseEntity updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("id") int id){
        if(!isValidZipCode(customerDTO.getAddressBook())){
            return ResponseEntity.badRequest().body("Invalid zip code.");
        }else if(!service.isIdExist(id)){
            return ResponseEntity.badRequest().body("Id not found");
        }

        CustomerEntity entity = service.updateCustomer(id, customerDTO);
        return ResponseEntity.ok().body(entity);

    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") int id){
        if(!service.isIdExist(id)){
            return ResponseEntity.badRequest().body("Id not found");
        }
        service.deleteCustomer(id);
        return ResponseEntity.ok().body("Customer Deleted");
    }

    private boolean isValidZipCode(List<AddressDTO> addressDTO){

        String regex = "\\d{5}-\\d{3}";
        for(AddressDTO dto: addressDTO){

            if (!dto.getZipCode().matches(regex)) {
                return false;
            }
        }
        return true;
    }
}
