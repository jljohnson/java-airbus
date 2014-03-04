package projet;

import java.io.IOException;

import projet.appli.Avion;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String adresseFichier = "fichiers/avions14-v1.txt";
		try {
			Avion.lireAvion(adresseFichier);
			
			Avion.afficherInstance();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
