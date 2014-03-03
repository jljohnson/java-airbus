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
		String res = "Identifiant : " + idAvion + " Constructeur : " + constructeur + " Mod�le : " + modele + " Capacite :" + capacite;
		return res;
	}
	
	// M�thode statique pour lire le fichier avion
	// lit le fichier
	// creer une instance
	// ajoute dans la map


}

