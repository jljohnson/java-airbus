package projet.appli.taches;

import java.util.Hashtable;

import projet.appli.Tache;
import projet.appli.vols.VolDepart;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheEnregistrement extends Tache {
	private static int numTache = 1;
	static private Hashtable<String, TacheEnregistrement> lesTachesEnregistrement = new Hashtable<String, TacheEnregistrement>();

	
	public TacheEnregistrement(VolDepart v) {
		super("ER" + numTache, v.getHeure().retrait(new Duree (1,30)),
				v.getHeure().retrait(new Duree (0,15)));
		numTache++;
		lesTachesEnregistrement.put(this.getIdTache(), this);
	}

}
