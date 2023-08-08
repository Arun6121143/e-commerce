package com.userservice.userservice.address;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void saveAddress(Address address, UUID userId) {
        address.setUserId(userId);
        addressRepository.save(address);
    }

    public List<Address> findAlladdress() {
        return addressRepository.findAll();
    }
}
