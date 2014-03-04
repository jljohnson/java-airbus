package projet.appli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

public abstract class Vol {
	private String idVol ;
	private Avion avion ;
	private TrancheHoraire duree ;
	
	static private Hashtable<String, Vol> lesVols = new Hashtable<String, Vol>();

	public Vol(String idVol, Horaire heureDepart, Horaire heureArrivee, Avion avion) {
		this.idVol = idVol;
		duree = new TrancheHoraire(heureDepart, heureArrivee);
		this.avion = avion;
		
		lesVols.put(idVol, this);
	}
	
	@Override
	public String toString() {
		return idVol 
				+  "\n - heure de départ : " + duree.getDebutTrancheHoraire().toString()
				+  "\n - heure d'arrivée : " + duree.getFinTrancheHoraire().toString()
				+  "\n - Identifiant avion : " + avion.getIdAvion() ;
		
	}
	
	static public void afficherInstance()
	{
		for (Vol v : lesVols.values()) {
			System.out.println(v.toString());
		}
	}

	
}
