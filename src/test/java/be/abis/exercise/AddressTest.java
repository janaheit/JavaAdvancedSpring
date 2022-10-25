package be.abis.exercise;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import be.abis.exercise.model.Address;
import be.abis.exercise.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressTest {

    @Autowired private AddressService addressService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void checkCorrectNL() {
        Address address = new Address("Kanaalstraat", "85", "8933DB",
                "Leeuwarden", "Netherlands", "NL");

        assertDoesNotThrow(() -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }
    @Test
    void checkCorrectNLWithSpace() {
        Address address = new Address("Kanaalstraat", "85", "8933 DB",
                "Leeuwarden", "Netherlands", "NL");

        assertDoesNotThrow(() -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }

    @Test
    void checkWrongNLThrows() {
        Address address = new Address("Kanaalstraat", "85", "8933 D5B",
                "Leeuwarden", "Netherlands", "NL");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }

    @Test
    void checkTooLongNLThrows() {
        Address address = new Address("Kanaalstraat", "85", "8933 DJB",
                "Leeuwarden", "Netherlands", "NL");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }

    @Test
    void checkCorrectLenOnlyDigitsNLThrows() {
        Address address = new Address("Kanaalstraat", "85", "8933 89",
                "Leeuwarden", "Netherlands", "NL");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }
    @Test
    void checkNullNLThrows(){
        Address address = new Address("Kanaalstraat", "85", null,
                "Leeuwarden", "Netherlands", "NL");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }

    @Test
    void checkEmptyStringNLThrows(){
        Address address = new Address("Kanaalstraat", "85", "",
                "Leeuwarden", "Netherlands", "NL");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }

    @Test
    void checkCorrectBE() {
        Address address = new Address("Rue de Bourgogne", "99", "1190",
                "Brussels", "België", "BE");

        assertDoesNotThrow(() -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }

    @Test
    void checkWrongBETooManyDigits() {
        Address address = new Address("Rue de Bourgogne", "99", "119056",
                "Brussels", "België", "BE");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }


    @Test
    void checkWrongBEWithLettersThrows() {
        Address address = new Address("Kanaalstraat", "85", "8933DB",
                "Leeuwarden", "België", "BE");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }

    @Test
    void checkWrongBEWithLeading0Throws() {
        Address address = new Address("Kanaalstraat", "85", "0933",
                "Leeuwarden", "België", "BE");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }

    @Test
    void checkNullBEThrows(){
        Address address = new Address("Kanaalstraat", "85", null,
                "Leeuwarden", "Netherlands", "BE");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }

    @Test
    void checkTooShortBEThrows(){
        Address address = new Address("Kanaalstraat", "85", "125",
                "Leeuwarden", "Netherlands", "BE");

        assertThrows(ZipCodeNotCorrectException.class, () -> addressService.checkZipcode(address.getZipCode(), address.getCountryCode()));
    }
}