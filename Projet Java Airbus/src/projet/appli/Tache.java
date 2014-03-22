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
	private boolean affectee ;

	// Constructeur de la classe Tache
	public Tache(String id, Horaire debut, Horaire fin) {
		affectee = false ;
		idTache = id;
		horaire = new TrancheHoraire(debut, fin);
		lesTaches.put(idTache, this);
	}
	
	// Permet de modifier un agent affecté à une tâche
	public void setAgent(Agent ag){
		affectee = true ;
		a = ag;
	}
	
	// Permet de savoir si une tâche est bien affectée
	public boolean isAffectee() {
		return affectee ;
	}
	
	// Début des acesseurs
	public String getIdTache() {
		return (idTache);
	}
	
	public Agent getAgent(){
		return a;
	}

	public TrancheHoraire getHoraires() {
		return (horaire);
	}
	
	// Permet de savoir le type d'une tâche
	public abstract String getType() ;
	
	// Retourne la liste des taches
	public static Collection<Tache> getTaches() {
		return lesTaches.values();
	}
	
	// Fin des accesseurs
	
	// Implementation de Comparable afin de comparer des TrancheHoraires
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
	
	// Définition de la méthode pour afficher une tâche
	public String toString ()
	{
		String res = "Identifiant : " + idTache + " tranche horaire :" + horaire.toString();
		return res;
	}

	// Affiche toute les instance de tâche
	static public void afficherInstance()
	{
		for (Tache t : lesTaches.values()) {
			System.out.println(t.toString());
		}
	}
	
	// Méthode qui donne une tâche non affecté en fonction des horaires demandés
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
	
	// Méthode qui renvoi une tâche repas
	static public TacheRepas demanderTacheRepas(Horaire hDeb) {
		return new TacheRepas(hDeb);
	}
	
	// Méthode qui crée une tâche acceuil dans les horaires demandés.
	static public Tache demanderTacheAccueil(TrancheHoraire trancheH) {
		return new TacheAccueil(trancheH.getDebutTrancheHoraire(),trancheH.getFinTrancheHoraire());
	}
}