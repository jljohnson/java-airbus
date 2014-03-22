package projet.appli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.TreeSet;

import projet.appli.taches.TacheAccueil;
import projet.appli.taches.TacheRepas;
import projet.outils.Duree;
import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

public abstract class Vol {
	private String idVol ;
	private Avion avion ;
	private Horaire heure ;
	private ArrayList<Tache> tachesVol;
	
	static private Hashtable<String, Vol> lesVols = new Hashtable<String, Vol>();

	// Constructeur de la classe Vol
	public Vol(String idVol, Horaire heure, Avion avion) {
		this.idVol = idVol;
		this.heure = heure ;
		this.avion = avion;
		tachesVol = new ArrayList<Tache>();
		lesVols.put(idVol, this);
	}
	
	// Début des accesseurs 
	
	public String getId ()
	{
		return (idVol);
	}
	
	public Horaire getHeure()
	{
		return (heure);
	}
	
	public Avion getAvion() {
		return avion;
	}
	
	public abstract String  getVille ();
	
	// Fin des accesseurs
	
	// Méthode qui redéfinit l'affichage pour un Vol
	public String toString() {
		return idVol 
				+  "\n - heure : " + heure.toString()
				+  "\n - Identifiant avion : " + avion.getIdAvion() ;		
	}
	
	// Méthode qui lit les fichiers Vols.
	static public void lireVolsArrivees(String adresseFichier)
			throws NumberFormatException, IOException {
		BufferedReader entree = null;

		// Déclaration d'une ligne
		String ligne;

		// Découpage en mot
		StringTokenizer mot;

		try {
			// Entrée du fichier
			entree = new BufferedReader(new FileReader(adresseFichier));

			while ((ligne = entree.readLine()) != null) 
			{
				// Lecture par mot sur chaque ligne
				mot = new StringTokenizer(ligne);

				while (mot.hasMoreTokens()) {
					// Recuperation du mot
					String id = mot.nextToken();
					String marque = mot.nextToken();
					String place = mot.nextToken();
					int capa = Integer.parseInt(place);

					Avion a = new Avion(id, capa, marque);
				}
			}
			entree.close();
		} catch (IOException e) {
			System.out.println("Erreur : " + e.toString());
		} catch (NumberFormatException e) {
			System.out.println("Erreur : " + e.toString());
		}
	}
	
	// Affiche les instances de vol
	static public void afficherInstances() {
		for (Vol v : lesVols.values()) {
			System.out.println(v.toString());
			
		}
	}
	
	// Méthode qui genère l'ensemble des tâches en fonction des vols
	public static void genererTaches() {
		for (Vol v : lesVols.values()) {
			v.creerTaches();
		}
	}
	
	// Méthode pour la création des taches en fonction de vol arrivé ou départ
	public abstract void  creerTaches ();
	
	// Retourne la liste des vols, utilisé dans la JTable graphique.
	public static ArrayList<Vol> getVols() {
		return new ArrayList<Vol>(lesVols.values());
	}
	
	// méthode d'annulation des vols
	public void annulerVol() {
		for (Tache t : tachesVol) {
			Agent a = t.getAgent();
			System.out.println("Agent " + a.getMatricules() + " désaffecté");
			a.desaffecterTache(t);
			a.ajouterTache(new TacheAccueil(t.getHoraires().getDebutTrancheHoraire(), t.getHoraires().getDebutTrancheHoraire()));
		}
	}
	
	/**
	 * Méthode retard vol
	 * @param d : Durée du retard 
	 */
	
	public void retarderVol(Duree d){
		TreeSet<Tache> tachesAReaffecter = new TreeSet<Tache>();
		
		for (Tache t : tachesVol){
			t.setHoraire(new TrancheHoraire(t.getHoraires().getDebutTrancheHoraire().ajout(d),t.getHoraires().getFinTrancheHoraire().ajout(d)));
			tachesAReaffecter.add(t);
			t.getAgent().desaffecterTache(t);
		}
		
		for (Tache tacheAReaffecter : tachesAReaffecter) {
			boolean affecte = false ;
			ArrayList<Agent> agents = new ArrayList<Agent>(Agent.lesAgents.values()) ;
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
	
	/**
	 * Ajoute une tache au vol, si elle n'est pas présente dans sa liste de taches
	 * @param t : tache à ajouter
	 */
	public void ajouterTacheVol(Tache t) {
		if (!tachesVol.contains(t)) {
			tachesVol.add(t);
		}
	}
}
