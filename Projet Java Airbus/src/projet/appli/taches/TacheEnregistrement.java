package projet.appli.taches;

import java.util.Hashtable;

import projet.appli.Tache;
import projet.appli.vols.VolArrivee;
import projet.appli.vols.VolDepart;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheEnregistrement extends Tache {
	private static int numTache = 1;
	private VolDepart volEnregistrement ;
	static private Hashtable<String, TacheEnregistrement> lesTachesEnregistrement = new Hashtable<String, TacheEnregistrement>();

	// Constructeur avec g�n�ration d'un num�ro automatique pour chaque t�che
	public TacheEnregistrement(VolDepart v) {
		super("ER" + numTache, v.getHeure().retrait(new Duree (1,30)),v.getHeure().retrait(new Duree (0,15)));
		numTache++;
		volEnregistrement = v ;
		lesTachesEnregistrement.put(this.getIdTache(), this);
		tachesCourantes.add(this);
		volEnregistrement.ajouterTacheVol(this);
	}


	// Retourne le type de la t�che
	public String getType() {
		return "Enregistrement vol " + volEnregistrement.getId();
	}
	
	// Retourne le vol associ� � la t�che
	public VolDepart getVol() {
		return volEnregistrement;
	}

}
