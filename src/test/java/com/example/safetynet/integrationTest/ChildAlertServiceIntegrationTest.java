package com.example.safetynet.integrationTest;

import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.dto.Person;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.MedicalRecordRepositoryImp;
import com.example.safetynet.repository.PersonRepository;
import com.example.safetynet.repository.PersonRepositoryImp;
import com.example.safetynet.service.ChildAlertService;
import com.example.safetynet.service.ChildAlertServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChildAlertServiceIntegrationTest {

    private JsonReaderMock jsonReader;

    private ChildAlertService childAlertService;

    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        PersonRepository personRepository = new PersonRepositoryImp(jsonReader);
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImp(jsonReader);

        childAlertService = new ChildAlertServiceImp(personRepository, medicalRecordRepository);
    }

    @Test
    @DisplayName("No children at this address")
    void noChildFoundAtThisAddress() {
        //GIVEN
        final var address = "1509 Culver St";

        //WHEN
        final var response = childAlertService.getChildByAddress(address);

        //THEN
        assertThat(response)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Not child at this address because age>18")
    void toOldForForKidTest() {
        final var address = "1509 Culver St";
        final var firstName = "John";
        final var lastName = "Boyd";

        final var age = 19;
        final var birthday = LocalDate.now().minusYears(age);

        final var person = new Person(firstName, lastName, address, "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        final var medicalRecord = new MedicalRecord(firstName, lastName, birthday, List.of(), List.of());

        jsonReader.addPerson(person);
        jsonReader.addMedicalRecord(medicalRecord);

        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        //WHEN
        final var response = childAlertService.getChildByAddress(address);

        //THEN
        assertThat(response)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Found 1 children")
    void oneChildFoundAtThisAddress() {
        //GIVEN
        final var address = "1509 Culver St";
        final var firstName = "John";
        final var lastName = "Boyd";

        final var age = 3;
        final var birthday = LocalDate.now().minusYears(age);


        final var person = new Person(firstName, lastName, address, "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        final var medicalRecord = new MedicalRecord(firstName, lastName, birthday, List.of(), List.of());

        jsonReader.addPerson(person);
        jsonReader.addMedicalRecord(medicalRecord);

        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        //WHEN
        final var response = childAlertService.getChildByAddress(address);

        //THEN
        assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .satisfies(c -> {
                    assertThat(c.getFirstName()).hasToString(firstName);
                    assertThat(c.getLastName()).hasToString(lastName);
                    assertThat(c.getAge()).isEqualTo(age);
                    assertThat(c.getFamily()).isEmpty();
                });
    }

    @Test
    @DisplayName("Found a family")
    void foundFamilyTest() {

        //GIVEN
        final var address = "1509 Culver St";
        final var lastName = "Boyd";

        final var age = 8;
        final var birthday1 = LocalDate.now().minusYears(age);
        final var birthday2 = LocalDate.now().minusYears(6);
        final var birthday3 = LocalDate.now().minusYears(20);


        final var person1 = new Person("Tenley", lastName, address, "Culver", "97451", "841-874-6512", "tenz@email.com");
        final var person2 = new Person("John", lastName, address, "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        final var person3 = new Person("Jacob", lastName, address, "Culver", "97451", "841-874-6512", "drk@email.com");
        final var medicalRecord1 = new MedicalRecord("Tenley", lastName, birthday1, List.of(), List.of());
        final var medicalRecord2 = new MedicalRecord("John", lastName, birthday2, List.of(), List.of());
        final var medicalRecord3 = new MedicalRecord("Jacob", lastName, birthday3, List.of(), List.of());


        jsonReader.addPerson(person1);
        jsonReader.addPerson(person2);
        jsonReader.addPerson(person3);
        jsonReader.addMedicalRecord(medicalRecord1);
        jsonReader.addMedicalRecord(medicalRecord2);
        jsonReader.addMedicalRecord(medicalRecord3);


        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);

        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);

        //WHEN
        final var response = childAlertService.getChildByAddress(address);

        //THEN
        assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .first()
                .satisfies(c -> {
                    assertThat(c.getFirstName()).hasToString(person1.getFirstName());
                    assertThat(c.getLastName()).hasToString(lastName);
                    assertThat(c.getAge()).isEqualTo(medicalRecord1.getAge());
                    assertThat(c.getFamily()).isNotEmpty();
                });
        Assertions.assertTrue(medicalRecord1.getAge() < 18);
        Assertions.assertTrue(medicalRecord2.getAge() < 18);
        Assertions.assertTrue(medicalRecord3.getAge() > 18);
    }
}
