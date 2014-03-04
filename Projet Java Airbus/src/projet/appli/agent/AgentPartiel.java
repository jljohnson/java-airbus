package projet.appli.agent;

import projet.appli.Agent;
import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

public class AgentPartiel extends Agent{

	public AgentPartiel(String mat, String n, String p, int c){
		super(mat,n,p,c);
	}
	
	// récupère le planning des horaires
	public TrancheHoraire getH (int numHo){
		Horaire hdeb = new Horaire();
		Horaire hfin = new Horaire();		
		TrancheHoraire th = new TrancheHoraire(hdeb,hfin);
		switch(numHo){
			// cas de la semaine multiple de 3
			case 0:
				hdeb = new Horaire(20,0);
				hfin = new Horaire(23,30);
				th = new TrancheHoraire(hdeb,hfin);
				break;
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
		}
		return th;
		
	}

	// méthode permettant de trouver l'horaire pour une semaine donnée
	//c = cycle / sem = numéro de la semaine
	public TrancheHoraire getHoraire(int sem){
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
					th = getH(2);
					break;
				case 2:
					th = getH(1);
					break;
			}
		}
		if(super.getCycle() == 2){
			switch(sem%3){
				// cas de la semaine multiple de 3
				case 0:
					th = getH(2);
					break;
				case 1:
					th = getH(1);
					break;
				case 2:
					th = getH(3);
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
				th = getH(3);
				break;
			case 2:
				th = getH(2);
				break;
		}
		}
			
		
		return th;
	}
}
