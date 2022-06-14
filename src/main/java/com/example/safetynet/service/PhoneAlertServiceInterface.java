package com.example.safetynet.service;

import java.util.List;

public interface PhoneAlertServiceInterface {

    List<String> getPhoneNumberByCoverage(String firestation_number);
}
