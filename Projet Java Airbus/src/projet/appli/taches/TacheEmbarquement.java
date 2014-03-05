package projet.appli.taches;

import projet.appli.Tache;
import projet.appli.vols.VolDepart;
import projet.outils.Horaire;

public class TacheEmbarquement extends Tache{
	private static int numTaches = 1 ;
	private VolDepart volEmbarquement ;

	public TacheEmbarquement(VolDepart v, Horaire debut, Horaire fin) {
		super("E" + numTaches, debut, fin);
		volEmbarquement = v;
		numTaches++;
	}

}
