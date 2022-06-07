//package com.example.safetynet.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.DateTimeException;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.Assert.assertThrows;
//
//@ExtendWith(SpringExtension.class)
//class AgeCalculatorServiceTest {
//
//    private static final AgeCalculatorService ageCalculatorService = new AgeCalculatorService();
//
//    @Test
//    @DisplayName("If birthDate = 14/04/2021, ageCalculator.calculate return 1")
//    public void calculAgeForTeenager15yearsOld() {
//
//        String birthDate = "14/04/2021";
//        assertThat(1).isEqualTo(ageCalculatorService.calculateAge(birthDate));
//    }
//
//    @Test
//    @DisplayName("Test if the birthDate in the future catch DateException")
//    void bornInTheFuture() {
//
//        String birthDate = "18/05/2023";
//        assertThrows(DateTimeException.class, ()->ageCalculatorService.calculateAge(birthDate));
//    }
//
//    @Test
//    @DisplayName("If the birthday is null, it should return a nullPointerException")
//    void calculateAgeNullBirthdateTest() {
//
//        String birthdate = null;
//        Assertions.assertThrows(NullPointerException.class, () -> ageCalculatorService.calculateAge(null));
//    }
//
//    @Test
//    @DisplayName("When birthdate is 32/04/1965, then throws DateTimeParseException")
//    void calculateAgeInvalidFormatBirthdateTest() {
//
//        String birthdate = "32/04/1965";
//        assertThrows(DateTimeException.class, () -> ageCalculatorService.calculateAge(birthdate));
//    }
//}