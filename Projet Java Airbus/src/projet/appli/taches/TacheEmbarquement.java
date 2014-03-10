package projet.appli.taches;

import java.util.Hashtable;

import projet.appli.Tache;
import projet.appli.vols.VolDepart;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheEmbarquement extends Tache{
	private static int numTaches = 1 ;
	private VolDepart volEmbarquement ;
	static private Hashtable<String, Tache> lesTachesEmbarquement = new Hashtable<String, Tache>();

	
	public TacheEmbarquement(VolDepart v) {
		super("D" + numTaches,
				v.getHeure().retrait(new Duree (0,15)),
				v.getHeure().ajout(new Duree (0,5)));
		volEmbarquement = v;
		numTaches++;
		lesTachesEmbarquement.put(this.getIdTache(), this);
	}

}
