package projet.appli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

public abstract class Tache implements Comparable{

	private String idTache;
	private TrancheHoraire horaire;

	static private Hashtable<String, Tache> lesTaches = new Hashtable<String, Tache>();
	static private TreeSet<Tache> tachesCourantes = new TreeSet<Tache>();

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
			res = 0;
		}
		else{
			res = 1;
		}
		return res;
	}
	
	
	public static TreeSet<Tache> getTache ()
	{
		return tachesCourantes;
	}
	
	public static TreeSet<Tache> test() {
		return new TreeSet<Tache>(lesTaches.values());
	}
	
	// Méthode qui affiche tous les avions
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
	
	static public void afficherInstanceTrier(){
		Iterator it = Tache.getTache().iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
	}
}