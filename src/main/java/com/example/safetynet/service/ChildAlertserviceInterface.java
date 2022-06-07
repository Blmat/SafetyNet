package com.example.safetynet.service;

import com.example.safetynet.model.ChildAlert;

import java.util.List;

public interface ChildAlertserviceInterface {

    List<ChildAlert> getChildByAddress(String address);
}
