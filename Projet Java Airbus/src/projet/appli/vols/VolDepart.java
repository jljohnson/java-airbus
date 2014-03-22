package projet.appli.vols;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import projet.appli.Avion;
import projet.appli.Vol;
import projet.appli.taches.TacheDebarquement;
import projet.appli.taches.TacheEmbarquement;
import projet.appli.taches.TacheEnregistrement;
import projet.outils.Horaire;

public class VolDepart extends Vol {

    static private Hashtable<String, VolDepart> lesVolsDepart = new Hashtable<String, VolDepart>();
    private String villeArrivee ;

    // Constructeur
	public VolDepart(String idVol, Horaire heureArrivee,String ville, Avion avion) {
		super(idVol, heureArrivee, avion);
		villeArrivee = ville ;
		lesVolsDepart.put(idVol, this);

	}
	
	// Retourne la ville d'arriv�e
	public String getVille()
	{
		return (villeArrivee);
	}
	
	// Modifie la m�thode d'affichage par d�faut
	public String toString() {
		return "Vol D�part " + super.toString() + "\n - ville arriv�e : " + villeArrivee;
	}
	
	
	
	static public void lireVolsDepart (String adresseFichier) {
		try {
			// Entr�e du fichier
			BufferedReader entree = new BufferedReader(new FileReader (adresseFichier));
			
			// D�claration d'une ligne
			String ligne;
			
			// D�coupage en mot
			StringTokenizer mot;
			
			while ((ligne = entree.readLine()) != null )
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
	
	
	
	// Cr�ation des taches debarquement
	public void creerTaches ()
	{
			TacheEmbarquement te = new TacheEmbarquement(this) ;
			
			int nbTachesEnregistrement = this.getAvion().getCapacite() / 90 ;
			
			for (int i=0;i<nbTachesEnregistrement; i++) {
				TacheEnregistrement tEr = new TacheEnregistrement(this);
			}
		
	}
	
	// Retourne la liste des vols pour le graphisme
	public static ArrayList<Vol> getVolsDepart() {
		return new ArrayList<Vol>(lesVolsDepart.values());
	}

}
