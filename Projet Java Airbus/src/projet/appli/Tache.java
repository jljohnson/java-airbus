package projet.appli;

import java.util.Hashtable;

import projet.outils.Horaire;

public abstract class Tache {

	private String idTache;
	private String description;
	private Horaire heureDebut;
	private Horaire heureFin;

	static private Hashtable<String, Tache> lesTaches = new Hashtable<String, Tache>();

	// Constructeur
	public Tache(String id, String d, Horaire debut, Horaire fin) {
		idTache = id;
		description = d;
		heureDebut = debut;
		heureFin = fin;

		lesTaches.put(idTache, this);
	}

	// tous les gets
	public String getIdTache() {
		return (idTache);
	}

	public String getDescription() {
		return (description);
	}

	public Horaire getHeureDebut() {
		return (heureDebut);
	}

	public Horaire getHeureFin() {
		return (heureFin);
	}

}