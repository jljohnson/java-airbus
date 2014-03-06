package projet;

import java.io.IOException;

import projet.appli.Avion;
import projet.appli.Tache;
import projet.appli.Vol;
import projet.appli.vols.VolArrivee;
import projet.appli.vols.VolDepart;
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
			
			//Vol.afficherInstances();
			//VolDepart.afficherInstances();
			//VolArrivee.afficherInstances();
			
			System.out.println("tache sans tri");
			//Tache.afficherInstance();
			
			System.out.println("tache avec tri");
			// recupe taches
			Tache.afficherInstanceTrier();
			
			//new FenetreGestion().setVisible(true);
	}

}
