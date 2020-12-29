package br.com.guilherme.volvo.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

@Query("select ab from AddressEntity ab where ab.zipCode = :zipCode and ab.number = :number")
Optional<AddressEntity> findIdByZipCodeAndNumber(String zipCode, int number);

List<AddressEntity> findAllByZipCode(String zipCode);

}
