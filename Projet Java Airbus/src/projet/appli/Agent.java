package projet.appli;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import projet.appli.taches.TacheAccueil;
import projet.appli.taches.TacheRepas;
import projet.exceptions.IdAvionException;
import projet.exceptions.MatAgentException;
import projet.exceptions.semaineInvalideException;
import projet.outils.Duree;
import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

/**
 * <p>Title: Agent</p>
 * <p>Description: classe d'un agent</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: Miage L3 </p>
 * @author Le Moing Stéfan
 * @version 1.0
 */

public abstract class  Agent {
	private String matricule;
	private String nom;
	private String prenom;
	private int cycle;
	private TreeSet<Tache> tachesAgent ;
	static private Hashtable <String,Agent> lesAgents = new Hashtable<String,Agent>();
	
	// constructeur
	public Agent(String mat, String n, String p, int c){
		tachesAgent = new TreeSet<Tache>();
		matricule = mat;
		nom = n;
		prenom = p;
		cycle = c;
		lesAgents.put(matricule, this);
		
	}
	
	
	// getteur
	public String getMatricules(){
		return matricule;
	}
	
	public String getNom(){
		return nom;
	}
	
	public String getPrenom(){
		return prenom;
	}
	
	public int getCycle(){
		return cycle;
	}
	
	
	// setteur
	public void setNom(String n){
	 nom = n;
	}
	
	public void setPrenom(String p){
		 prenom = p;
	}
	
	public void setCycle(int c){
		cycle = c;	
	}
	
	// Méthode statique qui permet de retrouver la réference du pointeur
		public static Agent getAgent (String id) throws MatAgentException
		{
			if (!lesAgents.containsKey(id))
			{
				throw new MatAgentException (id);
			}
			return ((Agent)lesAgents.get(id));
			
		}
	
	// gestion du temps
	public Duree tempsDeTravailRestant(){
		Duree d = new Duree();
		
		return d;
	}
	
	// gestion de la tranche horaire
	 public abstract TrancheHoraire getHoraire(int sem) throws semaineInvalideException;
	
	
	// gestion du planning pour les agents à temps plein
	public void creerPlanning() throws semaineInvalideException {
		TrancheHoraire trancheTravail = getHoraire(1);
		TrancheHoraire trancheRepas = new TrancheHoraire(new Horaire(11, 30),new Horaire(14, 0));
		TrancheHoraire trancheLastTache;
		
		boolean repasEffectue = false ;
		
		for (Tache t : Tache.getTache()) {		
			if (trancheTravail.contient(t.getHoraires())) {		// si t est contenue dans les horaires de l'agent
				// si l'agent n'a pas de taches on lui en ajoute
				if (tachesAgent.isEmpty()) {
					ajouterTache(t);
					// on met une tache d'accueil entre s'il y a de la place
					if (t.getHoraires().getDebutTrancheHoraire().retrait(trancheTravail.getDebutTrancheHoraire()).dureeEnMinutes() >= 30) {
						ajouterTache(new TacheAccueil(trancheTravail.getDebutTrancheHoraire(), t.getHoraires().getDebutTrancheHoraire()));
					}
				} else {
					trancheLastTache = tachesAgent.last().getHoraires();
					
					// si la tache courante commence après la dernière tache
					if (trancheLastTache.getFinTrancheHoraire().retrait(t.getHoraires().getDebutTrancheHoraire()).dureeEnMinutes() > 0 ) {
						// vérifier que l'agent peut terminer la tache
						if (trancheTravail.contient(trancheLastTache)) {
							ajouterTache(t);
							if (trancheLastTache.getFinTrancheHoraire().retrait(t.getHoraires().getDebutTrancheHoraire()).dureeEnMinutes() >= 30) {
								ajouterTache(new TacheAccueil(trancheLastTache.getFinTrancheHoraire(), t.getHoraires().getDebutTrancheHoraire()));
							}
							trancheLastTache = t.getHoraires(); 
						}
					}
					
					// création de la tache repas
					if (!repasEffectue && trancheRepas.contient(trancheLastTache)) {
						ajouterTache(new TacheRepas(trancheLastTache.getFinTrancheHoraire()));
						repasEffectue=true;
					}
				}
				
			}
		}
		
		trancheLastTache = tachesAgent.last().getHoraires();

		if (trancheLastTache.getFinTrancheHoraire().retrait(trancheTravail.getFinTrancheHoraire()).dureeEnMinutes() >= 30) {
			ajouterTache(new TacheAccueil(trancheLastTache.getFinTrancheHoraire(), trancheTravail.getFinTrancheHoraire()));
		}
	}
	
	public void ajouterTache(Tache t) {
		tachesAgent.add(t);
		if (Tache.getTache().contains(t)) {
			Tache.getTache().remove(t);
		}
	}
	
	public static void genererCalendrier() {
		for (Agent a : lesAgents.values()) {
			try {
				a.creerPlanning();
			} catch (semaineInvalideException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void afficherPlanning() {
		for (Tache t : tachesAgent) {
			System.out.println(t.toString());
		}
	}
	
	// gestion de l'affichage
		@Override
		public String toString() {
			return  "Agent : " + matricule
					+  "\n - Nom : " + nom 
					+  "\n - Prénom : " + prenom
					+  "\n - Cycle de travail : " + cycle;	
		}
		
		static public void afficherInstance()
		{
			for (Agent a : lesAgents.values()) {
				a.afficherPlanning();
			}
		}
}