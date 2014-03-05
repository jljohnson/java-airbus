package projet.appli.taches;

import projet.appli.Tache;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheAccueil extends Tache{
	private static int numTaches = 1 ;

	public TacheAccueil(Horaire horaireDeb, Horaire horaireFin) {
		super("ACC" + numTaches ,horaireDeb, horaireFin) ;
		numTaches++;
	}
}
