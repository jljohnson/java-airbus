package projet.appli;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import projet.appli.agent.AgentPartiel;
import projet.appli.agent.AgentPlein;
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
	protected TreeSet<Tache> tachesAgent ;
	static public Hashtable <String,Agent> lesAgents = new Hashtable<String,Agent>();
	
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
	
	
	public void ajouterTache(Tache t) {
		tachesAgent.add(t);
	}
	
	public static void genererCalendrier() {
		for (Agent a : lesAgents.values()) {
			try {
				a.creerPlanning();
				System.out.println(a.toString());
				a.afficherPlanning();
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
	
	// gestion du planning pour les agents à temps plein
	public abstract void creerPlanning() throws semaineInvalideException ;
	
	
	
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