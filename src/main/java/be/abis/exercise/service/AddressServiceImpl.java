package be.abis.exercise.service;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AddressServiceImpl implements AddressService{

    @Override
    public void checkZipcode(String zipcode, String countryCode) throws ZipCodeNotCorrectException {

        if (zipcode == null) throw new ZipCodeNotCorrectException("Zip code is null.");

        if (countryCode.equals("BE")){
            String regexBE = "[1-9]\\d{3}";
            Pattern p = Pattern.compile(regexBE);
            Matcher m = p.matcher(zipcode);
            if (!m.matches()) {
                throw new ZipCodeNotCorrectException("Zip code is not correct for Belgium");
            }
        } else if (countryCode.equals("NL")){
            String regexNL = "[1-9]\\d{3}\\s?[A-Z]{2}";
            Pattern p = Pattern.compile(regexNL);
            Matcher m = p.matcher(zipcode);
            if (!m.matches()) {
                throw new ZipCodeNotCorrectException("Zip code is not correct for the Netherlands");
            }
        }
    }

}
