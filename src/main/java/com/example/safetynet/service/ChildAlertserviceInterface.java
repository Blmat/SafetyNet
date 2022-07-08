package com.example.safetynet.service;

import com.example.safetynet.dto.ChildAlert;

import java.util.List;

public interface ChildAlertserviceInterface {

    List<ChildAlert> getChildByAddress(String address);
}
