package com.example.safetynet.service;

import com.example.safetynet.dto.Person;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.repository.PersonRepository;
import com.example.safetynet.repository.PersonRepositoryImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommunityEmailServiceIntegrationTest {

    private JsonReaderMock jsonReader;

    private CommunityEmailService communityEmailService;


    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        PersonRepository personRepository = new PersonRepositoryImp(jsonReader);

        communityEmailService = new CommunityEmailServiceImp(personRepository);
    }

    @Test
    @DisplayName("no answer found ")
    void noEmailFoundAtThisAddress() {
        //GIVEN
        final var city = "Culver";

        //WHEN
        final var response = communityEmailService.getEmailByCity(city);

        //THEN
        assertThat(response)
                .isNotNull()
                .isEmpty();
    }


    @Test
    @DisplayName("Found one email in this city")
    void oneEmailFoundAtThisCity() {
        //GIVEN
        final var city = "Culver";
        final var email = "jaboyd@email.com";

        final var person = new Person("John", "Boyd", "1509 culver st", "Culver", "97451", "841-874-6512", email);

        jsonReader.addPerson(person);

        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        //WHEN
        final var response = communityEmailService.getEmailByCity(city);

        //THEN
        assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    @DisplayName("Found all email in this city")
    void getallEmailFoundAtThisCity() {
        //GIVEN
        final var city = "Culver";
        final var email = "jaboyd@email.com";

        final var person = new Person("John", "Boyd", "1509 culver st", city, "97451", "841-874-6512", email);
        final var person2 = new Person("Jacob", "Boyd", "1509 culver st", city, "97451", "841-874-6512", "emailTest@yopmail.fr");
        jsonReader.addPerson(person);
        jsonReader.addPerson(person2);

        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);

        //WHEN
        final var response = communityEmailService.getEmailByCity(city);

        //THEN
        assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);
        Assertions.assertEquals(person.getEmail(), email);
        Assertions.assertEquals(person2.getEmail(), "emailTest@yopmail.fr");
        assertTrue(response, person.getEmail() + person2.getEmail());
    }
    private void assertTrue(List<String> response, String s) {
    }
}