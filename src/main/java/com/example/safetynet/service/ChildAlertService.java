package com.example.safetynet.service;

import com.example.safetynet.dto.ChildAlert;

import java.util.List;

public interface ChildAlertService {

    List<ChildAlert> getChildByAddress(String address);
}
