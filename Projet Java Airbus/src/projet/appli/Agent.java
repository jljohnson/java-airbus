package projet.appli;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeSet;



import projet.appli.taches.TacheAccueil;
import projet.appli.taches.TacheRepas;
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
	private TrancheHoraire horaires ;
	protected TreeSet<Tache> tachesAgent ;
	private TreeSet<TacheAccueil> tachesAccueil ;
	static public Hashtable <String,Agent> lesAgents = new Hashtable<String,Agent>();
	private boolean absent ;
	
	// constructeur
	public Agent(String mat, String n, String p, int c){
		tachesAgent = new TreeSet<Tache>();
		tachesAccueil = new TreeSet<TacheAccueil>();
		matricule = mat;
		nom = n;
		prenom = p;
		cycle = c;
		absent = false ;
		try {
			horaires = horaireSemaine(N_SEM);
		} catch (semaineInvalideException e) {
			e.printStackTrace();
		}
		lesAgents.put(matricule, this);
		
	}
	
	public boolean isAbsent() {
		return absent ;
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
	public abstract TrancheHoraire horaireSemaine(int sem) throws semaineInvalideException;
	
	public TrancheHoraire getHoraire() {
		return horaires;
	}
	
	public void ajouterTache(Tache t) {
		tachesAgent.add(t);
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
	
	public static void afficherCalendrier() {
		for (Agent a : lesAgents.values()) {
			System.out.println(a.toString());
			a.afficherPlanning();
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

			TrancheHoraire trancheService = horaires ;
		
			ArrayList<TrancheHoraire> tranches = new ArrayList<TrancheHoraire>();
			
			if (tachesAgent.isEmpty()) {
				tranches.add(trancheService);
				return tranches;
			}
			Iterator<Tache> it = tachesAgent.iterator();

			Tache first = it.next();
			if (trancheService.getDebutTrancheHoraire().compareTo(first.getHoraires().getDebutTrancheHoraire()) < 0) {
				tranches.add(new TrancheHoraire(trancheService.getDebutTrancheHoraire(), first.getHoraires().getDebutTrancheHoraire()));				
			}
			
			Tache tNext,tCour ;
			tCour = first ;
			while (it.hasNext()) {
				tNext =  it.next();
				TrancheHoraire trancheAMettre ;
				
				trancheAMettre = new TrancheHoraire(tCour.getHoraires().getFinTrancheHoraire(), tNext.getHoraires().getDebutTrancheHoraire()) ;
				if (trancheAMettre.getDuree().dureeEnMinutes() > 0) {
					tranches.add(trancheAMettre);
				}
				if (!it.hasNext()) {	
					trancheAMettre = new TrancheHoraire(tNext.getHoraires().getFinTrancheHoraire(), trancheService.getFinTrancheHoraire());
					if (trancheAMettre.getDuree().dureeEnMinutes() > 0) {
						tranches.add(trancheAMettre);
					}
				}

				tCour = tNext;
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
//					System.out.println("Id tache accueil : " + t.getIdTache() + " agent asso " + t.getAgent().matricule + "agent base " + matricule );
				}
			}
		}
		
		public void affecterTachesAAgent(){
			for (Tache t : tachesAgent){
				t.setAgent(this);
				System.out.println("Agent : " + matricule + " tache : " + t.toString() + " ag asso : " + t.getAgent().toString());
			}
		}
		
		public void absence() {
			absent = true ;
			horaires = new TrancheHoraire(new Horaire(0),new Horaire(0));
			
			TreeSet<Tache> tachesAReaffecter = new TreeSet<Tache>();
			
			for (Tache t : tachesAgent) {
				if ((t.getClass() != TacheAccueil.class) && (t.getClass() != TacheRepas.class)) {
					tachesAReaffecter.add(t);
				}
			} 
			tachesAgent.clear();
			
			for (Tache tacheAReaffecter : tachesAReaffecter) {
				boolean affecte = false ;
				ArrayList<Agent> agents = new ArrayList<Agent>(lesAgents.values()) ;
				for (int i = 0; i < agents.size() && !affecte; i++ ) {
					Agent a = agents.get(i);
					if (!a.isAbsent()) {
						if (a.affecterTache(tacheAReaffecter)) {
							affecte = true ;
							//System.out.println("tache " + tacheAReaffecter.getIdTache() + " affectée à l'agent " + a.getMatricules());
						}
					}					
				}
			}
		}
		
		public void retard(Horaire h1) {
			
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