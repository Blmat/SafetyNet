package com.example.safetynet.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/*http://localhost:8080/phoneAlert?firestation=<firestation_number>

Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.*/
@Data
public class FireStationPersonListPhone {
    private String station;
    private Set<String> residentsPhone = new HashSet<String>();

}
