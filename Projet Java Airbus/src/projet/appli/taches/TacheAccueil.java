package projet.appli.taches;

import java.util.Hashtable;

import projet.appli.Agent;
import projet.appli.Tache;
import projet.outils.Duree;
import projet.outils.Horaire;

public class TacheAccueil extends Tache {
	private static int numTaches = 1 ;
	static private Hashtable<String, TacheAccueil> lesTachesAccueil = new Hashtable<String, TacheAccueil>();

	// Constructeur avec g�n�ration d'un num�ro automatique pour chaque t�che
	public TacheAccueil(Horaire horaireDeb, Horaire horaireFin) {
		super("ACC" + numTaches ,horaireDeb, horaireFin) ;
		lesTachesAccueil.put(this.getIdTache(), this);
		numTaches++;
	}

	// Retourne le type de la t�che
	public String getType() {
		return "Accueil";
	}

}
