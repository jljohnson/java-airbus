package projet.appli.taches;
import projet.appli.Tache;
import projet.appli.vols.VolArrivee;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheDebarquement extends Tache {
	private static int numTaches = 1 ;
	private VolArrivee volDebarquement ;

	public TacheDebarquement(VolArrivee v) {
		super("D" + numTaches,v.getHeure().retrait(new Duree (0,5)),v.getHeure().ajout(new Duree (0,15)));
		volDebarquement = v ;
		numTaches++;
	}

}
