package projet.appli.vols;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import projet.appli.Avion;
import projet.appli.Vol;
import projet.appli.taches.TacheDebarquement;
import projet.exceptions.IdAvionException;
import projet.outils.Horaire;


public class VolArrivee extends Vol {
	
	static private Hashtable<String, VolArrivee> lesVolsArrivee = new Hashtable<String, VolArrivee>();

	private String villeDepart;
	
	
	public VolArrivee(String idVol, Horaire heureArrivee, String ville, Avion avion) {
		super(idVol, heureArrivee, avion);
		villeDepart = ville ;
		lesVolsArrivee.put(idVol, this);
	}
	
	@Override
	public String toString() {
		return "Vol Arriv�e n�" + super.toString() +"\n  - ville d�part :" + villeDepart;
	}
	
	static public void lireVolsArrivees (String adresseFichier) {
			
		BufferedReader entree = null;
			try {
				// Entrée du fichier
			    entree = new BufferedReader(new FileReader (adresseFichier));
				
				// Déclaration d'une ligne
				String ligne;
				
				// Découpage en mot
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

						 VolArrivee v = new VolArrivee(id, heureDepart, ville, Avion.getAvion(idAvion));
					  
					  }
				  }
				 entree.close();
			}
			catch (Exception e)
		      {
		    	  System.out.println("Erreur : "+ e.toString());
		      }

		
	}
	
	// Création des taches debarquement
	public void creerTaches ()
	{
		for (VolArrivee v : lesVolsArrivee.values()) {
			TacheDebarquement td = new TacheDebarquement(v) ;
		}
	}
}