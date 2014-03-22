package projet.appli.agent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

import projet.appli.Agent;
import projet.appli.Tache;
import projet.exceptions.semaineInvalideException;
import projet.outils.Duree;
import projet.outils.Horaire;
import projet.outils.TrancheHoraire;



public class AgentPartiel extends Agent {

	// Constructeur
	public AgentPartiel(String mat, String n, String p, int c) {
		super(mat, n, p, c);
	}

	/**
	 * Caclul les tranches horaire d'un agent en fonction de son cycle
	 * @return tranche horaire de l'agent
	 */
	public TrancheHoraire getH(int numHo) {
		Horaire hdeb = new Horaire();
		Horaire hfin = new Horaire();
		TrancheHoraire th = new TrancheHoraire(hdeb, hfin);
		switch (numHo) {
		// cas de la semaine multiple de 3
		case 1:
			hdeb = new Horaire(9, 0);
			hfin = new Horaire(12, 30);
			th = new TrancheHoraire(hdeb, hfin);
			break;
		case 2:
			hdeb = new Horaire(5, 30);
			hfin = new Horaire(9, 0);
			th = new TrancheHoraire(hdeb, hfin);
			break;
		case 3:
			hdeb = new Horaire(20, 0);
			hfin = new Horaire(23, 30);
			th = new TrancheHoraire(hdeb, hfin);
			break;
		}
		return th;
	}

	
	/**
	 * Permet de trouver l'horaire pour une semaine donnée
	 * @return Retourne une trancheHoraire
	 */
	public TrancheHoraire horaireSemaine(int sem) throws semaineInvalideException {
		if (sem == 0) {
			throw new semaineInvalideException();
		}

		Horaire hdeb = new Horaire();
		Horaire hfin = new Horaire();
		TrancheHoraire th = new TrancheHoraire(hdeb, hfin);
		if (super.getCycle() == 1) {
			switch (sem % 3) {
			// Cas de la semaine multiple de 3
			case 0:
				th = getH(3);
				break;
			case 1:
				th = getH(1);
				break;
			case 2:
				th = getH(2);
				break;
			}
		} else if (super.getCycle() == 2) {

			switch (sem % 3) {
			// Cas de la semaine multiple de 3
			case 0:
				th = getH(2);
				break;
			case 1:
				th = getH(3);
				break;
			case 2:
				th = getH(1);
				break;
			}
		} else

			switch (sem % 3) {
			// cas de la semaine multiple de 3
			case 0:
				th = getH(1);
				break;
			case 1:
				th = getH(2);
				break;
			case 2:
				th = getH(3);
				break;
			}

		return th;
	}

	/**
	 * Création du planning pour un agentPartiel
	 */
	public void creerPlanning() throws semaineInvalideException {
		TrancheHoraire trancheTravail = getHoraire();

		boolean fini = false;

		while ((trancheTravail.getDebutTrancheHoraire().compareTo(
				trancheTravail.getFinTrancheHoraire()) < 0)
				&& !fini) {
			try {
				Tache t;
				t = Tache.demanderTache(trancheTravail);
				ajouterTache(t);
				trancheTravail = new TrancheHoraire(t.getHoraires()
						.getFinTrancheHoraire(),
						trancheTravail.getFinTrancheHoraire());
			} catch (Exception e) {
				fini = true;
				// e.printStackTrace();
			}
		}

		genererTachesAccueil();
	}

	// Méthode qui lit le fichier des AgentPartiels
	static public void lireAgent(String adresseFichier) {

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
					String mat = mot.nextToken();
					String nom = mot.nextToken();
					String prenom = mot.nextToken();
					String c = mot.nextToken();
					int cycle = Integer.parseInt(c);

					AgentPartiel a = new AgentPartiel(mat, nom, prenom, cycle);
				}
			}
			entree.close();
		} catch (IOException e) {
			System.out.println("Erreur : " + e.toString());
		} catch (NumberFormatException e) {
			System.out.println("Erreur : " + e.toString());
		}

	}

}
