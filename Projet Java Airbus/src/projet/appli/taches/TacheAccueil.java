package projet.appli.taches;

import projet.appli.Tache;
import projet.outils.Horaire;

public class TacheAccueil extends Tache {
	private static int numTache = 1 ;

	public TacheAccueil(Horaire debut, Horaire fin) {
		super("ACC" + numTache, debut, fin);
		// TODO Auto-generated constructor stub
	}

}
