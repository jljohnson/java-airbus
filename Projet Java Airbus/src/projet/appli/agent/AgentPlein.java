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

/**
 * <p>Title: AgentPlein</p>
 * <p>Description: classe d'un agent plein</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: Miage L3 </p>
 * @author Le Moing Stéfan
 * @version 1.0
 */

public class AgentPlein extends Agent{
	
		private boolean aMange;
	// constructeur
		public AgentPlein(String mat, String n, String p, int c){
			super(mat,n,p,c);
			aMange = false ;
		}
		
		// récupère les horaires en temps partiel
		public TrancheHoraire getH (int numHo){
			Horaire hdeb = new Horaire();
			Horaire hfin = new Horaire();		
			TrancheHoraire th = new TrancheHoraire(hdeb,hfin);
			switch(numHo){
				// cas de la semaine multiple de 3
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

		// méthode permettant de trouver l'horaire pour une semaine donnée
		//c = cycle / sem = numéro de la semaine
		public TrancheHoraire horaireSemaine(int sem){
			Horaire hdeb = new Horaire();
			Horaire hfin = new Horaire();
			TrancheHoraire th = new TrancheHoraire(hdeb,hfin);
			if(super.getCycle() == 1){
				switch(sem%3){
					// cas de la semaine multiple de 3
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
					// cas de la semaine multiple de 3
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
			}
				
			
			return th;
		}
				
		// récupération des données du fichier
		
		 static public void lireAgent (String adresseFichier) {
					BufferedReader entree = null;
					
					// DÃ©claration d'une ligne
					String ligne;
					
					// DÃ©coupage en mot
					StringTokenizer mot;
					
					try {
						// EntrÃ©e du fichier
						 entree = new BufferedReader(new FileReader (adresseFichier));
						
						while ((ligne = entree.readLine()) != null ) // boucle de lecture/affichage du fichier
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

		@Override
		// gestion du planning pour les agents à temps plein
		public void creerPlanning() throws semaineInvalideException {
				TrancheHoraire trancheTravail = getHoraire();
				TrancheHoraire trancheService = getHoraire();
				TrancheHoraire trancheHoraireSoir = getH(3);
				
				//System.out.println("Val de trancheHoraire soir" + trancheHoraireSoir.toString());
				TrancheHoraire trancheRepas = new TrancheHoraire(new Horaire(11, 30),new Horaire(14,0));
				TrancheHoraire trancheLastTache;
				boolean fini = false;
				
				if (trancheService.equals(trancheHoraireSoir) && !aMange) {
					TacheRepas tR = Tache.demanderTacheRepas(trancheService.getDebutTrancheHoraire()); 
					ajouterTache(tR);
					trancheTravail = new TrancheHoraire(trancheTravail.getDebutTrancheHoraire().ajout(new Duree(1, 0)),
					trancheTravail.getFinTrancheHoraire());
					aMange = true ;
				}

				while((trancheTravail.getDebutTrancheHoraire().compareTo(trancheTravail.getFinTrancheHoraire()) < 0) && !fini){
					try{

						Tache t= Tache.demanderTache(trancheTravail);
						ajouterTache(t);
						trancheTravail = new TrancheHoraire(t.getHoraires().getFinTrancheHoraire(), trancheTravail.getFinTrancheHoraire());
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
				affecterTachesAAgent();
		}
		
		
}
