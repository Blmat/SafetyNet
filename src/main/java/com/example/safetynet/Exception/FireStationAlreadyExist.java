package com.example.safetynet.Exception;

public class FireStationAlreadyExist extends RuntimeException {

    public FireStationAlreadyExist(String message) {
        super("The Firestation already exists" );
    }
}
