package be.abis.exercise;

import be.abis.exercise.model.*;
import be.abis.exercise.repository.CompanyRepository;
import be.abis.exercise.repository.FileCompanyRepository;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublicSessionTest {

    private PublicSession publicSession;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        Company company = companyRepository.getCompanies().get(0);
        company.setAddress(new Address("Fonsnylaan", "223", "1190", "Brussel", "Belgie", "BE"));
        List<Person> persons = personRepository.getPersons();

        publicSession = new PublicSession(Course.JAVA_ADVANCED, LocalDate.of(2022, 12, 18), company,
                persons.get(0));

        publicSession.addEnrolment(persons.get(1), persons.get(2), persons.get(3), persons.get(4));
    }

    @Test
    public void checkParticipantsToFile(){
        publicSession.printListOfParticipants();
    }

    @Test
    public void printRevenue(){
        double revenue = publicSession.calculateRevenue();

        // format revenue
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("nl", "BE"));
        nf.setGroupingUsed(false);
        String s = nf.format(revenue).replace("\u00a0", "");

        System.out.println(s);
    }

    @Test
    public void printToStringInEnglish(){
        String out = publicSession.toString("en");

        Locale locale = new Locale("en");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
        String english = "This session about " + publicSession.getCourse().getTitle()
                + " will be given at " + publicSession.getLocation().getName()
                + " by " + publicSession.getInstructor().toString() + " on " + fmt.format(publicSession.getDate()) + ".";

        assertEquals(english, out);
    }

    @Test
    public void printToStringInGerman(){
        String out = publicSession.toString("de");
        System.out.println(out);

        Locale locale = new Locale("de");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
        String german = "Diese Session ueber " + publicSession.getCourse().getTitle() +
                " wird unterrichtet bei "+ publicSession.getLocation().getName()+ " von "+ publicSession.getInstructor().toString()
                + " am " + fmt.format(publicSession.getDate())+ ".";

        assertEquals(german, out);
    }

    @Test
    public void printToStringInDutch(){
        String out = publicSession.toString("nl");

        Locale locale = new Locale("nl");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
        String dutch = "Deze sessie over " + publicSession.getCourse().getTitle() +
                " word gegeven bij "+ publicSession.getLocation().getName()+ " van "+ publicSession.getInstructor().toString()
                + " op de " + fmt.format(publicSession.getDate())+ ".";

        assertEquals(dutch, out);
    }
}