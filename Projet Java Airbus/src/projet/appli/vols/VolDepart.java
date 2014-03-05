package projet.appli.vols;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import projet.appli.Avion;
import projet.appli.Vol;
import projet.outils.Horaire;

public class VolDepart extends Vol {

    static private Hashtable<String, VolDepart> lesVolsDepart = new Hashtable<String, VolDepart>();
    private String villeArrivee ;

	public VolDepart(String idVol, Horaire heureArrivee,
			String ville, Avion avion) {
		
		super(idVol, heureArrivee, avion);
		villeArrivee = ville ;
		lesVolsDepart.put(idVol, this);

	}
	
	@Override
	public String toString() {
		return "Vol Départ n°" + super.toString() + "\n - ville arrivée : " + villeArrivee;
	}
	
	static public void lireVolsDepart (String adresseFichier) {
		

		try {
			// EntrÃ©e du fichier
			BufferedReader entree = new BufferedReader(new FileReader (adresseFichier));
			
			// DÃ©claration d'une ligne
			String ligne;
			
			// DÃ©coupage en mot
			StringTokenizer mot;
			
			while ((ligne = entree.readLine()) != null ) // boucle de lecture/affichage du fichier
			  { 
				// Lecture par mot sur chaque ligne
				  mot = new StringTokenizer(ligne);
				
				  while (mot.hasMoreTokens())
				  {
					  // Recuperation du mot
					  String id = mot.nextToken();
					  int heures = Integer.parseInt(mot.nextToken());
					  int minutes = Integer.parseInt(mot.nextToken());
					  String ville = mot.nextToken();
					  String idAvion = mot.nextToken();
					  
					  Horaire heureDepart = new Horaire(heures, minutes);

					  VolDepart v = new VolDepart(id, heureDepart, ville, Avion.getAvion(idAvion));
				  }
			  }
			entree.close();
		}
		catch (Exception e)
	      {
	    	  System.out.println("Erreur : "+ e.toString());
	      }	
	}
	
	@Override
	public void creerTaches() {
		
		for (VolDepart v : lesVolsDepart.values()) {
			// tache d'embarquem
		}
	}

}
