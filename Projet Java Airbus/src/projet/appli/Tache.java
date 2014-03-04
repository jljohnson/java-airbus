package projet.appli;

import java.util.Hashtable;

import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

public abstract class Tache {

	private String idTache;
	private String description;
	private TrancheHoraire duree ;

	static private Hashtable<String, Tache> lesTaches = new Hashtable<String, Tache>();

	// Constructeur
	public Tache(String id, String d, Horaire debut, Horaire fin) {
		idTache = id;
		description = d;
		duree = new TrancheHoraire(debut, fin);

		lesTaches.put(idTache, this);
	}

	// tous les gets
	public String getIdTache() {
		return (idTache);
	}

	public String getDescription() {
		return (description);
	}

	public TrancheHoraire getHoraires() {
		return (duree);
	}

}