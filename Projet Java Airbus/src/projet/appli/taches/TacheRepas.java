package projet.appli.taches;

import projet.appli.Tache;
import projet.outils.Duree;
import projet.outils.Horaire;


public class TacheRepas extends Tache{
	private static int numTaches = 1 ;

	public TacheRepas(Horaire horaireDeb) {
		super("REP" + numTaches ,horaireDeb, horaireDeb.ajout(new Duree(1,0))) ;
		numTaches++;
	}
}
