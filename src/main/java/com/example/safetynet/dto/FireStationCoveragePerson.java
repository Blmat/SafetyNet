package com.example.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class FireStationCoveragePerson {
    /**http://localhost:8080/firestation?stationNumber=<station_number>
    Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
    Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste
    doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
    elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
    moins) dans la zone desservie.*/
    private int adults;
    private int child;
    private List<PersonCovered> personsCovered;

    public FireStationCoveragePerson(int nbAdults, int nbChild, List<PersonCovered> personsCovered) {
        this.adults = nbAdults;
        this.child = nbChild;
        this.personsCovered = personsCovered;
    }
}

