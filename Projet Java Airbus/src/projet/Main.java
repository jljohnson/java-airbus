package projet;

import java.io.IOException;
import java.util.Iterator;

import projet.appli.Agent;
import projet.appli.Avion;
import projet.appli.Tache;
import projet.appli.Vol;
import projet.appli.agent.AgentPartiel;
import projet.appli.agent.AgentPlein;
import projet.appli.taches.TacheRepas;
import projet.appli.vols.VolArrivee;
import projet.appli.vols.VolDepart;
import projet.exceptions.MatAgentException;
import projet.exceptions.semaineInvalideException;
import projet.ihm.FenetreGestion;
import projet.ihm.popups.PopupPlanning;
import projet.outils.Horaire;

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
			AgentPartiel.lireAgent("fichiers/AgentsMiTemps-14-v1.txt");
			
			Agent.genererCalendrier();
			System.out.println("Taches courantes :" + Tache.tachesCourantes.size() + "Taches attribu�es :" + Tache.tachesAttribuees.size() + "/Taches repas:" + TacheRepas.lesTachesRepas.size());
	
			try {
				//Agent.getAgent("P0007").absence();
				Agent.getAgent("P0007").afficherPlanning();
				System.out.println("Taches courantes :" + Tache.tachesCourantes.size() + "Taches attribu�es :" + Tache.tachesAttribuees.size() + "/Taches repas:" + TacheRepas.lesTachesRepas.size());

			} catch (MatAgentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new FenetreGestion();
	}

}
