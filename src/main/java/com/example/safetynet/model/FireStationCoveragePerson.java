package com.example.safetynet.model;

import java.util.List;

public class FireStationCoveragePerson {
/*http://localhost:8080/firestation?stationNumber=<station_number>

Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste
doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
moins) dans la zone desservie.*/
	private String station;
	private int adults;
	private int child;
	private List<PersonCovered> personsCovered;

	public FireStationCoveragePerson() {}

	public FireStationCoveragePerson(int nbAdults, int nbChild, List<PersonCovered> personsCovered) {
		this.adults = nbAdults;
		this.child = nbChild;
		this.personsCovered = personsCovered;
	}

	public int getAdults() { return adults; }
	public int getChild() { return child; }

	public List<PersonCovered> getPersonsCovered() { return personsCovered; }
	public void setAdults(int adults) {
		this.adults = adults;
	}

	public void setChild(int child) {
		this.child = child;
	}
	public void setPersonsCovered(List<PersonCovered> personsCovered) {
		this.personsCovered = personsCovered;
	}

	@Override
	public String toString() {
		return "Coverage: adults = " + adults + ", child = " + child + ", person covered = " + personsCovered;
	}

}
