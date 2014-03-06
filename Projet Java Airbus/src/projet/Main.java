package projet;

import java.io.IOException;
import java.util.Iterator;

import projet.appli.Agent;
import projet.appli.Avion;
import projet.appli.Tache;
import projet.appli.Vol;
import projet.appli.agent.AgentPartiel;
import projet.appli.agent.AgentPlein;
import projet.appli.vols.VolArrivee;
import projet.appli.vols.VolDepart;
import projet.exceptions.MatAgentException;
import projet.ihm.FenetreGestion;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String adresseFichier = "fichiers/avions14-v1.txt";
	
			Avion.lireAvion(adresseFichier);
			
			//Avion.afficherInstance();
			
			VolArrivee.lireVolsArrivees("fichiers/ProgrammeVolsArrivees14-v2.txt");
			VolDepart.lireVolsDepart("fichiers/ProgrammeVolsDeparts14-v2.txt");
			
			Vol.genererTaches();

			AgentPlein.lireAgent("fichiers/AgentsTempsPlein-14-v1.txt");
			
			Agent.genererCalendrier();
			
			try {
				Agent.getAgent("P0003").afficherPlanning();
			} catch (MatAgentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
