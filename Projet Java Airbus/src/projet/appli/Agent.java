package projet.appli;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.StringTokenizer;

import projet.exceptions.IdAvionException;
import projet.exceptions.MatAgentException;
import projet.outils.Duree;
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
	static private Hashtable <String,Tache> lesTaches = new Hashtable<String,Tache>();
	static private Hashtable <String,Agent> lesAgents = new Hashtable<String,Agent>();
	
	// constructeur
	public Agent(String mat, String n, String p, int c){
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
	
	public void setHeureTravaille(int d){
		//methode
		//HeureTravaille.ajout();
	}
	
	// Méthode statique qui permet de retrouver la réference du pointeur
		public static Agent getAvion (String id) throws MatAgentException
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
	 public abstract TrancheHoraire getHoraire(int sem);
	
	
	// récupération des données du fichier
	
	static public void lireAgent (String adresseFichier){
	}
	
	// gestion du planning
	
	// gestion de l'affichage
		@Override
		public String toString() {
			return  "Agent : " + matricule
					+  "\n - Nom : " + nom 
					+  "\n - Prénom : " + prenom
					+  "\n - Cycle de travail : " + cycle;	
		}
}