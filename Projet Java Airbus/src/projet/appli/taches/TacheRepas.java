package projet.appli.taches;

import projet.appli.Tache;
import projet.appli.vols.VolArrivee;
import projet.outils.Duree;
import projet.outils.Horaire;
import projet.outils.TrancheHoraire;


public class TacheRepas extends Tache{
	private static int numTaches = 1 ;
	
	
	public TacheRepas(Horaire horaireDeb){
		super("R" + numTaches,horaireDeb,horaireDeb.ajout(new Duree(1,0)));
		numTaches++;
	}
}
