package projet.appli;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
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
	
	/// A CHANGER 
	private static final int N_SEM = 1;
	
	private String matricule;
	private String nom;
	private String prenom;
	private int cycle;
	protected TreeSet<Tache> tachesAgent ;
	private TreeSet<TacheAccueil> tachesAccueil ;
	static public Hashtable <String,Agent> lesAgents = new Hashtable<String,Agent>();
	
	// constructeur
	public Agent(String mat, String n, String p, int c){
		tachesAgent = new TreeSet<Tache>();
		tachesAccueil = new TreeSet<TacheAccueil>();
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
	
	public TreeSet<Tache> getPlanning() {
		return tachesAgent;
	}
	
	// gestion du planning pour les agents à temps plein
	public abstract void creerPlanning() throws semaineInvalideException ;
	
	
	
	// gestion de l'affichage
		@Override
		public String toString() {
			return  "\nAgent : " + matricule
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

		public ArrayList<TrancheHoraire> tranchesLibres() {

			TrancheHoraire trancheService = null ;
			try {
				trancheService = getHoraire(N_SEM);
			} catch (semaineInvalideException e) {
				e.printStackTrace();
			}
		
			
			ArrayList<TrancheHoraire> tranches = new ArrayList<TrancheHoraire>();
			
			if (tachesAgent.isEmpty()) {
				tranches.add(trancheService);
				return tranches;
			}
			
			Tache first = tachesAgent.first();
			tranches.add(new TrancheHoraire(trancheService.getDebutTrancheHoraire(), first.getHoraires().getDebutTrancheHoraire()));
			
			Tache t,tPrec ;
			for (Iterator it = tachesAgent.iterator(); it.hasNext();) {
				tPrec = (Tache) it.next();

				if (it.hasNext()) {
					t = (Tache) it.next();
					tranches.add(new TrancheHoraire(tPrec.getHoraires().getFinTrancheHoraire(), t.getHoraires().getDebutTrancheHoraire()) ) ;
				} else {
					tranches.add(new TrancheHoraire(tPrec.getHoraires().getFinTrancheHoraire(), trancheService.getFinTrancheHoraire()));
				}
			}
						
			return tranches;
		}
		
		public void genererTachesAccueil() {
			ArrayList<TrancheHoraire> listeTranches = this.tranchesLibres();
			for (TrancheHoraire tH : listeTranches) {
				if (tH.getDuree().dureeEnMinutes() >= 30) {
					TacheAccueil t = new TacheAccueil(tH.getDebutTrancheHoraire(), tH.getFinTrancheHoraire());
					tachesAgent.add(t);
					tachesAccueil.add(t);
				}
			}
		}
		
		
	
		public void retard(Horaire retard) {
			TreeSet<Tache> tachesAReaffecter = new TreeSet<Tache>();
			
			for (Tache t : tachesAgent) {
				if (retard.compareTo(t.getHoraires().getDebutTrancheHoraire())>0) {
					tachesAReaffecter.add(t);
				} else break ;
			} 
			
			for (Agent a : lesAgents.values()) {
				boolean affecte = false ;
				for (Tache t: tachesAReaffecter) {
					if (affecterTache(t)) {
						affecte = true ;
						break;
					}
					if (affecte==true) break;
				}
			}
		}
		
		public boolean affecterTache(Tache t) {
			for (TrancheHoraire tH : this.tranchesLibres()) {
				if (tH.contient(t.getHoraires())) {
					tachesAgent.add(t);
					return true ;
				}
			}			
			
			for (TacheAccueil tAcc : tachesAccueil) {
				if (tAcc.getHoraires().contient(t.getHoraires())) {
					tachesAgent.remove(tAcc);
					tachesAgent.add(t);
			
					return true ;
				}
			}
			
			return false ;
		}


		public static ArrayList<Agent> getAgents() {
			// TODO Auto-generated method stub
			return new ArrayList<Agent>(lesAgents.values());
		}
		
}