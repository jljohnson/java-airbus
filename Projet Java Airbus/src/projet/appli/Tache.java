package projet.appli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import projet.appli.taches.TacheAccueil;
import projet.appli.taches.TacheRepas;
import projet.exceptions.PlusDeTachesExeception;
import projet.outils.Duree;
import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

public abstract class Tache implements Comparable{

	private String idTache;
	private TrancheHoraire horaire;
	private Agent a ;

	static private Hashtable<String, Tache> lesTaches = new Hashtable<String, Tache>();
	static public TreeSet<Tache> tachesCourantes = new TreeSet<Tache>();
	static public ArrayList<Tache> tachesAttribuees = new ArrayList<Tache>();

	// Constructeur
	public Tache(String id, Horaire debut, Horaire fin) {
		idTache = id;
		horaire = new TrancheHoraire(debut, fin);

		lesTaches.put(idTache, this);
		tachesCourantes.add(this);
	}

	// tous les gets
	public String getIdTache() {
		return (idTache);
	}

	public TrancheHoraire getHoraires() {
		return (horaire);
	}
	
	public int compareTo(Object o){
		Tache t = (Tache)o;
		int res; 
		if((((this.getHoraires()).getDebutTrancheHoraire()).compareTo(t.getHoraires().getDebutTrancheHoraire())) < 0){
			res = -1;
		}
		else if((((this.getHoraires()).getDebutTrancheHoraire()).compareTo(t.getHoraires().getDebutTrancheHoraire())) == 0){
			res = this.idTache.compareTo(t.getIdTache());
		}
		else{
			res = 1;
		}
		return res;
	}
	
	public abstract String getType() ;
	
	public static Collection<Tache> getTaches() {
		return lesTaches.values();
	}
	
	public String toString ()
	{
		String res = "Identifiant : " + idTache + " tranche horaire :" + horaire.toString();
		return res;
	}

	static public void afficherInstance()
	{
		for (Tache t : lesTaches.values()) {
			System.out.println(t.toString());
		}
	}
	
	static public Tache demanderTache(TrancheHoraire trancheH) throws PlusDeTachesExeception {
		TrancheHoraire repas = new TrancheHoraire(new Horaire(11,30), new Horaire(14,00));
		
		for (Tache t : tachesCourantes) {
			if (trancheH.contient(t.getHoraires()) && !tachesAttribuees.contains(t)) {
				tachesAttribuees.add(t);
				return t;
			} 
		}
		throw new PlusDeTachesExeception();
	}
	
	static public Tache demanderTacheRepas(Horaire hDeb) {
		return new TacheRepas(hDeb);
	}
	
	static public Tache demanderTacheAccueil(TrancheHoraire trancheH) {
		return new TacheAccueil(trancheH.getDebutTrancheHoraire(),trancheH.getFinTrancheHoraire());
	}
}