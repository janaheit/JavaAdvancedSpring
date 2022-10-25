package be.abis.exercise;

import java.util.List;

import be.abis.exercise.model.Company;
import be.abis.exercise.repository.FileCompanyRepository;
import be.abis.exercise.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestReadCompanies {

	@Autowired private CompanyRepository companyRepository;

	@Test
	void numberOfCompaniesInFileIs5(){
		
		List<Company> comps = companyRepository.getCompanies();

		assertEquals(5,comps.size());

	}

}
