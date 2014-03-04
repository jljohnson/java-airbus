package projet.appli;


import java.util.Hashtable;

import projet.outils.Duree;

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
	private Duree HeureTravaille;
	static private Hashtable <String,Tache> lesTaches = new Hashtable<String,Tache>();
	static private Hashtable <String,Agent> lesAgents = new Hashtable<String,Agent>();
	
	// constructeur
	public Agent(String mat, String n, String p){
		matricule = mat;
		nom = n;
		prenom = p;
		HeureTravaille = new Duree();
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
	
	public Duree getHeureTravaille(){
		return HeureTravaille;
	}
	
	
	// setteur
	public void setNom(String n){
	 nom = n;
	}
	
	public void setPrenom(String p){
		 prenom = p;
	}
	
	public void setHeureTravaille(Duree d){
		HeureTravaille.ajout(d);
	}
	
	// gestion du temps
	public Duree tempsDeTravailRestant(){
		Duree d = new Duree();
		
		return d;
	}
	
	// gestion du planning
	
	// gestion de l'affichage
	public String toString(){
		String res = "";
		return res;
	}
}