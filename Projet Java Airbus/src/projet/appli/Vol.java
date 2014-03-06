package projet.appli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import projet.outils.Horaire;
import projet.outils.TrancheHoraire;

public abstract class Vol {
	private String idVol ;
	private Avion avion ;
	private Horaire heure ;
	
	static private Hashtable<String, Vol> lesVols = new Hashtable<String, Vol>();

	public Vol(String idVol, Horaire heure, Avion avion) {
		this.idVol = idVol;
		this.heure = heure ;
		this.avion = avion;
		
		lesVols.put(idVol, this);
	}
	
	public Horaire getHeure()
	{
		return (heure);
	}
	
	public Avion getAvion() {
		return avion;
	}
	
	@Override
	public String toString() {
		return idVol 
				+  "\n - heure : " + heure.toString()
				+  "\n - Identifiant avion : " + avion.getIdAvion() ;		
	}
	
	static public void lireVolsArrivees(String adresseFichier)
			throws NumberFormatException, IOException {
		BufferedReader entree = null;

		// D��claration d'une ligne
		String ligne;

		// D��coupage en mot
		StringTokenizer mot;

		try {
			// Entr��e du fichier
			entree = new BufferedReader(new FileReader(adresseFichier));

			while ((ligne = entree.readLine()) != null) // boucle de
														// lecture/affichage du
														// fichier
			{
				// Lecture par mot sur chaque ligne
				mot = new StringTokenizer(ligne);

				while (mot.hasMoreTokens()) {
					// Recuperation du mot
					String id = mot.nextToken();
					String marque = mot.nextToken();
					String place = mot.nextToken();
					int capa = Integer.parseInt(place);

					Avion a = new Avion(id, capa, marque);
				}
			}
			entree.close();
		} catch (IOException e) {
			System.out.println("Erreur : " + e.toString());
		} catch (NumberFormatException e) {
			System.out.println("Erreur : " + e.toString());
		}
	}
	
	static public void afficherInstances() {
		for (Vol v : lesVols.values()) {
			System.out.println(v.toString());
			
		}
	}
	
	public static void genererTaches() {
		for (Vol v : lesVols.values()) {
			v.creerTaches();
		}
	}
	
	// Méthode pour la création des taches
	public abstract void  creerTaches ();
}
