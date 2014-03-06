package projet.appli.taches;

import projet.appli.Tache;
import projet.appli.vols.VolDepart;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheEnregistrement extends Tache {
	private static int numTache = 1;

	public TacheEnregistrement(VolDepart v) {
		super("ER" + numTache, v.getHeure().retrait(new Duree (1,30)),
				v.getHeure().retrait(new Duree (0,15)));
		numTache++;
	}

}
