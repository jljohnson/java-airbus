package projet.appli;

import java.util.Hashtable;

import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

public abstract class Vol {
	private String idVol ;
	private String ville ;
	private Avion avion ;
	private TrancheHoraire duree ;
	
	static private Hashtable<String, Vol> lesVols = new Hashtable<String, Vol>();

	public Vol(String idVol, Horaire heureDepart, Horaire heureArrivee,
			String ville, Avion avion) {
		this.idVol = idVol;
		duree = new TrancheHoraire(heureDepart, heureArrivee);
		this.ville = ville;
		this.avion = avion;
		
		lesVols.put(idVol, this);
	}
	
	@Override
	public String toString() {
		return idVol 
				+  "\n - heure de départ : " + duree.getDebutTrancheHoraire().toString()
				+  "\n - heure d'arrivée : " + duree.getFinTrancheHoraire().toString()
				+  "\n - ville : " + ville
				+  "\n - Identifiant avion : " + avion.getIdAvion() ;
		
	}

	
}
