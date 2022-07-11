package com.example.safetynet.service;

import java.util.List;

public interface PhoneAlertService {

    List<String> getPhoneNumberByCoverage(String firestation_number);
}
