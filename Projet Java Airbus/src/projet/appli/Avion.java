package projet.appli;

import java.util.Hashtable;
import java.util.Iterator;

import projet.exceptions.IdAvionException;


public class Avion {
	
	private String idAvion;
	private int capacite;
	private String modele;
	private String constructeur;
	
	static private Hashtable<String,Avion> lesAvions = new Hashtable<String,Avion>();
	
	
	//Constructeur
	public Avion (String id, int place, String mod, String marque)
	{
		idAvion = id;
		capacite = place;
		modele = mod;
		constructeur = marque;
		
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
	
	public String getConstructeur ()
	{
		return (constructeur);
	}
	
	
	// Méthode statique qui permet de retrouver la réference du pointeur
	public static Avion getAvion (String id) throws IdAvionException
	{
		if (!lesAvions.containsKey(id))
		{
			throw new IdAvionException (id);
		}
		return ((Avion)lesAvions.get(id));
		
	}
	
	// Méthode qui affiche tous les avions
	public String toString ()
	{
		String res = "Identifiant : " + idAvion + " Constructeur : " + constructeur + " Modèle : " + modele + " Capacite :" + capacite;
		return res;
	}
	
	// Méthode statique pour lire le fichier avion
	// lit le fichier
	// creer une instance
	// ajoute dans la map


}

