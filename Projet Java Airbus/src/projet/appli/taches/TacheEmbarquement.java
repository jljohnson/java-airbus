package projet.appli.taches;

import java.util.Hashtable;

import projet.appli.Tache;
import projet.appli.vols.VolArrivee;
import projet.appli.vols.VolDepart;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheEmbarquement extends Tache{
	private static int numTaches = 1 ;
	private VolDepart volEmbarquement ;
	static private Hashtable<String, TacheEmbarquement> lesTachesEmbarquement = new Hashtable<String, TacheEmbarquement>();

	// Constructeur avec g�n�ration d'un num�ro automatique pour chaque t�che
	public TacheEmbarquement(VolDepart v) {
		super("D" + numTaches,v.getHeure().retrait(new Duree (0,15)),v.getHeure().ajout(new Duree (0,5)));
		volEmbarquement = v;
		numTaches++;
		lesTachesEmbarquement.put(this.getIdTache(), this);
		tachesCourantes.add(this);
		volEmbarquement.ajouterTacheVol(this);
	}


	// Retourne le type de la t�che
	public String getType() {
		return "Embarquement vol " + volEmbarquement.getId();
	}
	
	// Retourne le vol associ� � la t�che
	public VolDepart getVol() {
		return volEmbarquement;
	}

}
