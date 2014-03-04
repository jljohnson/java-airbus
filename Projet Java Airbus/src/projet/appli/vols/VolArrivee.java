package projet.appli.vols;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import projet.appli.Avion;
import projet.appli.Vol;
import projet.outils.Horaire;


public class VolArrivee extends Vol {
	
	static private Hashtable<String, VolArrivee> lesVolsArrivee = new Hashtable<String, VolArrivee>();

	private String villeDepart;
	
	public VolArrivee(String idVol, Horaire heureDepart, Horaire heureArrivee,
			String ville, Avion avion) {
		super(idVol, heureDepart, heureArrivee, avion);
		villeDepart = ville ;
		lesVolsArrivee.put(idVol, this);
	}
	
	@Override
	public String toString() {
		return "Vol Arrivée n°" + super.toString() +"\n  - ville départ :" + villeDepart;
	}
	
	static public void lireVolsArrivees (String adresseFichier) throws NumberFormatException, IOException
	{
	
			// EntrÃ©e du fichier
			BufferedReader entree = new BufferedReader(new FileReader (adresseFichier));
			
			// DÃ©claration d'une ligne
			String ligne;
			
			// DÃ©coupage en mot
			StringTokenizer mot;
			
			try {
				while ((ligne = entree.readLine()) != null ) // boucle de lecture/affichage du fichier
				  { 
					// Lecture par mot sur chaque ligne
					  mot = new StringTokenizer(ligne);
					
					  while (mot.hasMoreTokens())
					  {
						  // Recuperation du mot
						  String id = mot.nextToken();
						  String marque = mot.nextToken();
						  String place = mot.nextToken();
						  int capa = Integer.parseInt(place);

						 Avion a = new Avion (id,capa,marque);
					  }
				  }
			}
			catch (IOException e)
		      {
		    	  System.out.println("Erreur : "+ e.toString());
		      }
			finally
			{
				entree.close();
			}	
		
	}

}