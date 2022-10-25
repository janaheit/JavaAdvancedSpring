package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.CourseParticipant;
import be.abis.exercise.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getPersons();

    void printPersonsSortedToFile(String file);
    void writeAllPersonsToFile(String file);
    Person findPersonByID(int id) throws PersonNotFoundException;
    Person findPerson(String email, String password) throws PersonNotFoundException;

    Person findPersonByName(String name) throws PersonNotFoundException;
}
