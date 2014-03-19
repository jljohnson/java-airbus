package projet.appli.taches;
import java.util.Hashtable;

import projet.appli.Tache;
import projet.appli.vols.VolArrivee;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheDebarquement extends Tache {
	private static int numTaches = 1 ;
	private VolArrivee volDebarquement ;
	static private Hashtable<String, TacheDebarquement> lesTachesDebarquement = new Hashtable<String, TacheDebarquement>();

	
	public TacheDebarquement(VolArrivee v) {
		super("A" + numTaches,v.getHeure().retrait(new Duree (0,5)),v.getHeure().ajout(new Duree (0,15)));
		volDebarquement = v ;
		numTaches++;
		lesTachesDebarquement.put(this.getIdTache(), this);
		tachesCourantes.add(this);

	}


	@Override
	public String getType() {
		return "Débarquement vol " + volDebarquement.getId();
	}
	
	public VolArrivee getVol() {
		return volDebarquement;
	}

}
