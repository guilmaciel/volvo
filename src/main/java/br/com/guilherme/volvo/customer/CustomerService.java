package br.com.guilherme.volvo.customer;


import br.com.guilherme.volvo.address.AddressDTO;
import br.com.guilherme.volvo.address.AddressEntity;
import br.com.guilherme.volvo.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final AddressService addressService;

    public List<CustomerEntity> getAllCustomers(){

       return repository.findAll();
    }

    public CustomerEntity findById(int id){
        return repository.findById(id).orElse(new CustomerEntity());
    }


    public List<CustomerEntity> findCustomersByZipCode(String zipCode){

        return repository.findByAddressBook_ZipCode(zipCode);
    }

    public CustomerEntity saveCustomer(CustomerDTO customer){

        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(customer, entity);
        entity.setRegistrationDate(LocalDate.now());
        entity.setLastUpdate(LocalDate.now());
        List<AddressEntity> addressEntities = new ArrayList<>();

        for(AddressDTO dto : customer.getAddressBook()){
            AddressEntity addressEntity = new AddressEntity();
            BeanUtils.copyProperties(dto, addressEntity);
            addressEntities.add(addressEntity);
        }
        entity.setAddressBook(addressEntities);
        return repository.save(entity);
    }

    public CustomerEntity updateCustomer(int id, CustomerDTO customer){

        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(customer, entity);
        entity.setId(id);
        entity.setRegistrationDate(LocalDate.now());
        entity.setLastUpdate(LocalDate.now());
        List<AddressEntity> addressEntities = new ArrayList<>();

        for(AddressDTO dto : customer.getAddressBook()){
            AddressEntity addressEntity = new AddressEntity();
            BeanUtils.copyProperties(dto, addressEntity);
            addressEntities.add(addressEntity);
        }
        entity.setAddressBook(addressEntities);
        return repository.save(entity);
    }

    public void deleteCustomer(int id){

        repository.deleteById(id);

    }

    public boolean isIdExist(int id){
        return repository.findById(id).isPresent();
    }

    public List<CustomerEntity> findByZipCode(String zipCode){
        return repository.findByAddressBook_ZipCode(zipCode);
    }


}
