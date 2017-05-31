package com.mzps.repository;

import com.mzps.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByCityNameAndStreetNameAndHallName(String cityName, String streetName, String hallName);
}
