package projet.appli.taches;
import projet.appli.Tache;
import projet.outils.Horaire;

public class TacheDebarquement extends Tache {
	private static int numTaches = 0 ;

	public TacheDebarquement( String d, Horaire debut, Horaire fin) {
		super("D" + numTaches, d, debut, fin);
		numTaches++;
	}

}
