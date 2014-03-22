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
	
	// Constructeur de la classe Agent
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
	
	// Permet de savoir si un agent est absent
	public boolean isAbsent() {
		return absent ;
	}
	
	// Retourne le Matricule d'un agent
	public String getMatricules(){
		return matricule;
	}
	
	// Retourne le nom d'un agent
	public String getNom(){
		return nom;
	}
	
	// Retourne le prénom d'un agent
	public String getPrenom(){
		return prenom;
	}
	
	// Retourne le cycle d'un agent
	public int getCycle(){
		return cycle;
	}
	
	
	// Permet de modifier le nom de l'agent
	public void setNom(String n){
	 nom = n;
	}
	
	// Permet de modifier le prénom de l'agent
	public void setPrenom(String p){
		 prenom = p;
	}
	
	// Permet de modifier le cycle d'un agent
	public void setCycle(int c){
		cycle = c;	
	}
	
	// Méthode statique qui permet de retrouver la réference du pointeur
	public static Agent getAgent (String id) throws MatAgentException {
		if (!lesAgents.containsKey(id))
		{
			throw new MatAgentException (id);
		}
		return ((Agent)lesAgents.get(id));
	}
	
	//  Retourne le temps de travai restant pour un Agent
	public Duree tempsDeTravailRestant(){
		Duree d = new Duree();
		
		return d;
	}
	
	// Permet de calculer les horaires de service en fonction d'une semaine donnée
	public abstract TrancheHoraire horaireSemaine(int sem) throws semaineInvalideException;
	
	// Permet d'obtenir les horaires d'un agent
	public TrancheHoraire getHoraire() {
		return horaires;
	}
	
	// Méthode qui ajoute une tâche dans la map d'un agent
	public void ajouterTache(Tache t) {
		tachesAgent.add(t);
		t.setAgent(this);
	}
	
	public void ajouterTache(TacheAccueil t) {
		tachesAgent.add(t);
		t.setAgent(this);
		tachesAccueil.add(t);
	}
	
	
	// Genère le planning de la journée pour un agent
	public static void genererCalendrier() {
	  for (Agent a : lesAgents.values()) {
			try {
				a.creerPlanning();
			} catch (semaineInvalideException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Affiche le planning pour tous les agents de l'aéroport
	public static void afficherCalendrier() {
		for (Agent a : lesAgents.values()) {
			System.out.println(a.toString());
			a.afficherPlanning();
		}
	}
	
	// Affiche le planning d'un agent
	public void afficherPlanning() {
		for (Tache t : tachesAgent) {
			System.out.println(t.toString());
		}
	}
	
	/**
	 * Liste des tâches d'un agent
	 * @return planning agent
	 */
	public TreeSet<Tache> getPlanning() {
		return tachesAgent;
	}
	
	/**
	 * Crée le planning de l'agent en fonction du numéro de semaine
	 * @throws semaineInvalideException
	 */
	public abstract void creerPlanning() throws semaineInvalideException ;
	

	public String toString() {
		return  "\nAgent : " + matricule +  "\n - Nom : " + nom  +  "\n - Prénom : " + prenom +  "\n - Cycle de travail : " + cycle;	
	}
		
	/**
	 * affiche le planning de tous les agents
	 */
	static public void afficherInstance(){
		for (Agent a : lesAgents.values()) {
			a.afficherPlanning();
		}
	}

	/**
	 * Renvoie les tranches horaires libres de l'agent
	 * @return liste de tranches horaires libres
	 */
	public ArrayList<TrancheHoraire> tranchesLibres() {

		TrancheHoraire trancheService = horaires ;
		
		ArrayList<TrancheHoraire> tranches = new ArrayList<TrancheHoraire>();
		
		// Si l'agent n'a pas encore de tâche affecté
		if (tachesAgent.isEmpty()) {
				tranches.add(trancheService);
				return tranches;
			}
		
			Iterator<Tache> it = tachesAgent.iterator();
			Tache first = it.next();
			
			// Si l'horaire de prise de service est inférieur à la premiere tache alors on a une tranche libre
			if (trancheService.getDebutTrancheHoraire().compareTo(first.getHoraires().getDebutTrancheHoraire()) < 0) {
				tranches.add(new TrancheHoraire(trancheService.getDebutTrancheHoraire(), first.getHoraires().getDebutTrancheHoraire()));				
			}
			
			Tache tNext,tCour ;
			tCour = first ;
			
			// Parcours de l'ensemble du planning d'un agent
			while (it.hasNext()) {
				tNext =  it.next();
				TrancheHoraire trancheAMettre ;
				trancheAMettre = new TrancheHoraire(tCour.getHoraires().getFinTrancheHoraire(), tNext.getHoraires().getDebutTrancheHoraire()) ;
				
				// Si la durée de la comparaison tâche suivante - tache actuel est supérieur à 0 alors on insère une nouvelle tranche libre
				if (trancheAMettre.getDuree().dureeEnMinutes() > 0) {
					tranches.add(trancheAMettre);
				}
				
				// Si on est arrivé à la dernière tâche de l'agent on compare la dernière tâche avec son horaire de fin de service
				if (!it.hasNext()) {	
					trancheAMettre = new TrancheHoraire(tNext.getHoraires().getFinTrancheHoraire(), trancheService.getFinTrancheHoraire());
					
					// Si la durée de l'horaire de fin de service - la dernière tâche est supérieur à 0, on insère une nouvelle tâche
					if (trancheAMettre.getDuree().dureeEnMinutes() > 0) {
						tranches.add(trancheAMettre);
					}
				}

				tCour = tNext;
			}
						
			return tranches;
		}
		
		/**
		 * Génère les taches accueil en  parcourant les tranches libres
		 */
		public void genererTachesAccueil() {
			ArrayList<TrancheHoraire> listeTranches = this.tranchesLibres();
			for (TrancheHoraire tH : listeTranches) {
				
				// Si une tranche libre à une durée supérieur ou egale à 30 minutes alors on insère la tâche
				if (tH.getDuree().dureeEnMinutes() >= 30) {
					TacheAccueil t = new TacheAccueil(tH.getDebutTrancheHoraire(), tH.getFinTrancheHoraire());
					ajouterTache(t);
				}
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
						} else {
							tacheAReaffecter.setAgent(null);
							tacheAReaffecter.setAffectee(false);
						}
					}					
				}
			}
		}
		
		public void retard(Horaire h1) {		
		}
		
		
		/**
		 * Affecte une tache en fonction du temps libre de l'agent
		 * @param t : tache à affecter
		 * @return réussite ou non 
		 */
		public boolean affecterTache(Tache t) {
			for (TrancheHoraire tH : this.tranchesLibres()) {
				if (tH.contient(t.getHoraires())) {
					System.out.println("NORMAl / tache " + t.getIdTache() + " affectée à l'agent " + getMatricules());
					ajouterTache(t);
					return true ;
				}
			}			
			
			for (TacheAccueil tAcc : tachesAccueil) {
				if (tAcc.getHoraires().contient(t.getHoraires())) {
					System.out.println("ACCUEIL / tache " + t.getIdTache() + " affectée à l'agent " + getMatricules());
					tachesAgent.remove(tAcc);
					ajouterTache(t);
					
					return true ;
				}
			}
			
			return false ;
		}


		// Liste des agents pour la JTable graphique
		public static ArrayList<Agent> getAgents() {
			return new ArrayList<Agent>(lesAgents.values());
		}

		/**
		 * Désaffecte un tache si elle existe
		 * @param t : tache à désaffecter
		 */
		public void desaffecterTache(Tache t) {
			tachesAgent.remove(t);		
		}
		
}