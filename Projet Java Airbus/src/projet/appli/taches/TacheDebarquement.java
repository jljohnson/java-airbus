package projet.appli.taches;
import projet.appli.Tache;
import projet.appli.vols.VolArrivee;
import projet.outils.Horaire;

public class TacheDebarquement extends Tache {
	private static int numTaches = 1 ;
	private VolArrivee volDebarquement ;

	public TacheDebarquement(VolArrivee v, Horaire debut, Horaire fin) {
		super("D" + numTaches,debut, fin);
		volDebarquement = v ;
		numTaches++;
	}

}
