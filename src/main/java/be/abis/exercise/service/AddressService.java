package be.abis.exercise.service;

import be.abis.exercise.exception.ZipCodeNotCorrectException;

public interface AddressService {

    void checkZipcode(String zipcode, String countryCode) throws ZipCodeNotCorrectException;

}
