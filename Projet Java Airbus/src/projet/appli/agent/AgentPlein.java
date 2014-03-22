package projet.appli.agent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

import projet.appli.Agent;
import projet.appli.Tache;
import projet.appli.taches.TacheRepas;
import projet.exceptions.semaineInvalideException;
import projet.outils.Duree;
import projet.outils.Horaire;
import projet.outils.TrancheHoraire;


public class AgentPlein extends Agent{
	
		private boolean aMange;
		
		// Constructeur
		public AgentPlein(String mat, String n, String p, int c){
			super(mat,n,p,c);
			aMange = false ;
		}
		
		
		/**
		 * Caclul les tranches horaire d'un agent en fonction de son cycle
		 * @return tranche horaire de l'agent
		 */
		public TrancheHoraire getH (int numHo){
			Horaire hdeb = new Horaire();
			Horaire hfin = new Horaire();		
			TrancheHoraire th = new TrancheHoraire(hdeb,hfin);
			switch(numHo){
				// Cas de la semaine multiple de 3
				case 3:
					hdeb = new Horaire(13,30);
					hfin = new Horaire(21,30);
					th = new TrancheHoraire(hdeb,hfin);
					break;
				case 1:
					hdeb = new Horaire(9,0);
					hfin = new Horaire(17,0);
					th = new TrancheHoraire(hdeb,hfin);
					break;
				case 2:
					hdeb = new Horaire(6,0);
					hfin = new Horaire(14,0);
					th = new TrancheHoraire(hdeb,hfin);
					break;
			}
			return th;	
		}

		/**
		 * Permet de trouver l'horaire pour une semaine donnée
		 * @return Retourne une trancheHoraire
		 */
		public TrancheHoraire horaireSemaine(int sem){
			Horaire hdeb = new Horaire();
			Horaire hfin = new Horaire();
			TrancheHoraire th = new TrancheHoraire(hdeb,hfin);
			if(super.getCycle() == 1){
				switch(sem%3){
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
			}
			else if(super.getCycle() == 2){
				switch(sem%3){
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
			}
			else{
				switch(sem%3){
				// Cas de la semaine multiple de 3
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
			}
				
			
			return th;
		}
				
		// Lecture du fichier AgentPlein
		 static public void lireAgent (String adresseFichier) {
					BufferedReader entree = null;
					
					// Déclaration d'une ligne
					String ligne;
					
					// Découpage en mot
					StringTokenizer mot;
					
					try {
						// Entrée du fichier
						 entree = new BufferedReader(new FileReader (adresseFichier));
						
						while ((ligne = entree.readLine()) != null )
						  { 
							// Lecture par mot sur chaque ligne
							  mot = new StringTokenizer(ligne);
							
							  while (mot.hasMoreTokens())
							  {
								  // Recuperation du mot
								  String mat = mot.nextToken();
								  String nom = mot.nextToken();
								  String prenom = mot.nextToken();
								  String c = mot.nextToken();
								  int cycle = Integer.parseInt(c);
								  
								  AgentPlein a = new AgentPlein (mat,nom,prenom,cycle);
							  }
						  }
						entree.close();
					}
					catch (IOException e)
				      {
				    	  System.out.println("Erreur : "+ e.toString());
				      }
					catch (NumberFormatException e)
				      {
				    	  System.out.println("Erreur : "+ e.toString());
				      }
				
			}

		 /**
			 * Création du planning pour un agentPlein
			 */
		public void creerPlanning() throws semaineInvalideException {
				TrancheHoraire trancheTravail = getHoraire();
				TrancheHoraire trancheService = getHoraire();
				TrancheHoraire trancheHoraireSoir = getH(3);
				TrancheHoraire trancheRepas = new TrancheHoraire(new Horaire(11, 30),new Horaire(14,0));
				TrancheHoraire trancheLastTache;
				boolean fini = false;
				
				// Si on est dans la tranche et que l'agent n'a pas encore mangé alors on affecte la tâche repas
				if (trancheService.equals(trancheHoraireSoir) && !aMange) {
					TacheRepas tR = Tache.demanderTacheRepas(trancheService.getDebutTrancheHoraire()); 
					ajouterTache(tR);
					trancheTravail = new TrancheHoraire(trancheTravail.getDebutTrancheHoraire().ajout(new Duree(1, 0)),
					trancheTravail.getFinTrancheHoraire());
					aMange = true ;
				}

				// Tant qu'on a pas parcouru l'ensemble de ces horaires de services
				while((trancheTravail.getDebutTrancheHoraire().compareTo(trancheTravail.getFinTrancheHoraire()) < 0) && !fini){
					try{
						Tache t= Tache.demanderTache(trancheTravail);
						ajouterTache(t);
						trancheTravail = new TrancheHoraire(t.getHoraires().getFinTrancheHoraire(), trancheTravail.getFinTrancheHoraire());
						
						// Si l'heure de fin de la tache est plus grande que l'heure de debut de la tranche repas et que l'agent n'a pas mangé alors
						if ((t.getHoraires().getFinTrancheHoraire().compareTo(trancheRepas.getDebutTrancheHoraire()) > 0) && !aMange) {
							TacheRepas tR = Tache.demanderTacheRepas(t.getHoraires().getFinTrancheHoraire()); 
							ajouterTache(tR);
							trancheTravail = new TrancheHoraire(t.getHoraires().getFinTrancheHoraire().ajout(new Duree(1, 0)),
							trancheTravail.getFinTrancheHoraire());
							aMange = true;
						}
					}
					catch (Exception e){
						fini = true;
						//e.printStackTrace();
					}
				}
				
				genererTachesAccueil();
		}
		
		
}
