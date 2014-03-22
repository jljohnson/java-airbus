package projet.appli.taches;

import java.util.Hashtable;

import projet.appli.Tache;
import projet.outils.Duree;
import projet.outils.Horaire;


public class TacheRepas extends Tache{
	private static int numTaches = 1 ;
	static public Hashtable<String, TacheRepas> lesTachesRepas = new Hashtable<String, TacheRepas>();

	// Constructeur avec génération d'un numéro automatique pour chaque tâche
	public TacheRepas(Horaire horaireDeb) {
		super("REP" + numTaches ,horaireDeb, horaireDeb.ajout(new Duree(1,0))) ;
		numTaches++;
		lesTachesRepas.put(this.getIdTache(), this);
	}

	// Retourne le type de la tâche
	public String getType() {
		return "Repas";
	}
}
