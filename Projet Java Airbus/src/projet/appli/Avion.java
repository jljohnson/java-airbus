package projet.appli;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

import projet.exceptions.IdAvionException;


public class Avion {
	
	private String idAvion;
	private int capacite;
	private String modele;
	
	static private Hashtable<String,Avion> lesAvions = new Hashtable<String,Avion>();
	
	
	//Constructeur
	public Avion (String id, int place, String mod)
	{
		idAvion = id;
		capacite = place;
		modele = mod;
	
		// Ajout dans la map
		lesAvions.put(idAvion, this);
				
	}
	
	// Tous les get
	public String getIdAvion ()
	{
		return (idAvion);
	}
	
	public int getCapacite ()
	{
		return (capacite);
	}
	
	public String getModele ()
	{
		return (modele);
	}
	
	
	// M�thode statique qui permet de retrouver la r�ference du pointeur
	public static Avion getAvion (String id) throws IdAvionException
	{
		if (!lesAvions.containsKey(id))
		{
			throw new IdAvionException (id);
		}
		return ((Avion)lesAvions.get(id));
		
	}
	
	// M�thode qui affiche tous les avions
	public String toString ()
	{
		String res = "Identifiant : " + idAvion + " Modèle : " + modele + " Capacite :" + capacite;
		return res;
	}
	
	// M�thode statique pour lire le fichier avion
	// lit le fichier
	// creer une instance
	// ajoute dans la map

	static public void lireAvion (String adresseFichier) 
	{
	
			BufferedReader entree = null;
			
			// Déclaration d'une ligne
			String ligne;
			
			// Découpage en mot
			StringTokenizer mot;
			
			try {
				// Entrée du fichier
				 entree = new BufferedReader(new FileReader (adresseFichier));
				
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
	
	static public void afficherInstance()
	{
		for (Avion a : lesAvions.values()) {
			System.out.println(a.toString());
		}
	}

}

