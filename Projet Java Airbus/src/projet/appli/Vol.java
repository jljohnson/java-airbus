package projet.appli;

import java.util.Hashtable;

import projet.outils.Horaire;

public abstract class Vol {
	private String idVol ;
	private Horaire heureDepart ;
	private Horaire heureArrivee ;
	private String ville ;
	private Avion avion ;
	
	static private Hashtable<String, Vol> lesVols = new Hashtable<String, Vol>();

	public Vol(String idVol, Horaire heureDepart, Horaire heureArrivee,
			String ville, Avion avion) {
		this.idVol = idVol;
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		this.ville = ville;
		this.avion = avion;
		
		lesVols.put(idVol, this);
	}
	
	@Override
	public String toString() {
		return idVol 
				+  "\n - heure de départ : " + heureDepart 
				+  "\n - heure d'arrivée : " + heureArrivee
				+  "\n - ville : " + ville
				+  "\n - Identifiant avion : " + avion.getIdAvion() ;
		
	}

	
}
