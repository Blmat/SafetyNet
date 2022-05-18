package com.example.safetynet.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class AgeCalculatorService {

    private final Logger logger = LogManager.getLogger(AgeCalculatorService.class);

    public int calculateAge(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate localDate = LocalDate.parse(birthdate, formatter);
            if (localDate.isAfter(LocalDate.now())) {
                throw new DateTimeException("Birthdate is incorrect: " + birthdate);
            }
            return Period.between(localDate, LocalDate.now()).getYears();
        } catch (DateTimeParseException parseDateEx) {
            logger.error("invalid format for birthdate");
            throw parseDateEx;
        } catch (NullPointerException nullBirthdateEx) {
            logger.error("error : birthdate is null.");
            throw nullBirthdateEx;
        } catch (DateTimeException dateTimeException) {
            logger.error("error : invalid birthdate.");
            throw dateTimeException;
        }
    }
}
