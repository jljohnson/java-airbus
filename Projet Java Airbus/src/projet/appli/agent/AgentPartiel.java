package projet.appli.agent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import projet.appli.Agent;
import projet.exceptions.semaineInvalideException;
import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

/**
 * <p>Title: AgentPartiel</p>
 * <p>Description: classe d'un agent partiel</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: Miage L3 </p>
 * @author Le Moing Stéfan
 * @version 1.0
 */

public class AgentPartiel extends Agent{

	// constructeur
	public AgentPartiel(String mat, String n, String p, int c){
		super(mat,n,p,c);
	}
	
	// récupère les horaires en temps partiel
	public TrancheHoraire getH (int numHo){
		Horaire hdeb = new Horaire();
		Horaire hfin = new Horaire();		
		TrancheHoraire th = new TrancheHoraire(hdeb,hfin);
		switch(numHo){
			// cas de la semaine multiple de 3
			case 1:
				hdeb = new Horaire(9,0);
				hfin = new Horaire(12,30);
				th = new TrancheHoraire(hdeb,hfin);
				break;
			case 2:
				hdeb = new Horaire(5,30);
				hfin = new Horaire(9,0);
				th = new TrancheHoraire(hdeb,hfin);
				break;
			case 3:
				hdeb = new Horaire(20,0);
				hfin = new Horaire(23,30);
				th = new TrancheHoraire(hdeb,hfin);
				break;
		}
		return th;	
	}

	// méthode permettant de trouver l'horaire pour une semaine donnée
	//c = cycle / sem = numéro de la semaine
	public TrancheHoraire getHoraire(int sem) throws semaineInvalideException{
		if(sem == 0){
			throw new semaineInvalideException();
		}
		
		Horaire hdeb = new Horaire();
		Horaire hfin = new Horaire();
		TrancheHoraire th = new TrancheHoraire(hdeb,hfin);
		if(super.getCycle() == 1){
			System.out.println(sem%3);
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
		else
			
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
		
		return th;
	}
	
	
	// récupération des données du fichier
	
	 static public void lireAgent (String adresseFichier) 
		{
		
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
							  
							 AgentPartiel a = new AgentPartiel (mat,nom,prenom,cycle);
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
	
}
