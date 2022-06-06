package com.example.safetynet.service;

import com.example.safetynet.model.Children;

import java.util.List;

public interface ChildrenServiceInterface {

    List<Children> getChildrenByAddress(String address);
}
