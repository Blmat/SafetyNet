package com.example.safetynet.service;

import com.example.safetynet.exception.PersonNotFoundException;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepository;
import com.example.safetynet.repository.PersonRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonServiceIntegrationTest {

    private JsonReaderMock jsonReader;
    private PersonInt personInt;

    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        PersonRepository personRepository = new PersonRepositoryImp(jsonReader);

        personInt = new PersonServiceImp(personRepository);
    }

    @Test
    @DisplayName("Test add method")
    void addMethodTest() {
        // GIVEN

        final var person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");

        jsonReader.addPerson(person);
        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        // WHEN
        final var response = personInt.addPerson(person);

        // THEN
        assertThat(response)
                .isNotNull();
        assertEquals(response.getFirstName(), person.getFirstName());
        assertEquals(response.getLastName(), person.getLastName());
    }

    @Test
    @DisplayName("Test update method")
    void updateMethodTest() {
        // GIVEN

        final var person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");

        jsonReader.addPerson(person);
        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        // WHEN
        final var response = personInt.updatePerson(person, "John", "Boyd");

        // THEN
        assertThat(response)
                .isNotNull();
        assertEquals(response.getFirstName(), person.getFirstName());
        assertEquals(response.getLastName(), person.getLastName());
    }

    @Test
    @DisplayName("catch error if the person is not found")
    void updateMethodErrorTest() {
        // GIVEN

        final var person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");

        jsonReader.addPerson(person);
        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        assertThrows(PersonNotFoundException.class, () -> personInt.updatePerson(person, "Jacob", "Boyd"));
    }
    @Test
    @DisplayName("Test delete method")
    void deleteMethodTest() {
        // GIVEN

        final var person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");

        jsonReader.addPerson(person);
        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        // WHEN
        personInt.deletePerson("John", "Boyd");

        //THEN
        assertThat(jsonReader.getDatas().getMedicalrecords())
                .isNotNull()
                .isEmpty();
    }
}