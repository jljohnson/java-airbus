package projet;

import java.io.IOException;

import projet.appli.Avion;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String adresseFichier = "fichiers/avions14-v1.txt";
	
			Avion.lireAvion(adresseFichier);
			
			Avion.afficherInstance();
		
	}

}
