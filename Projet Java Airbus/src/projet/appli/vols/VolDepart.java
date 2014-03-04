package projet.appli.vols;

import java.util.Hashtable;

import projet.appli.Avion;
import projet.appli.Vol;
import projet.outils.Horaire;

public class VolDepart extends Vol {

    static private Hashtable<String, VolDepart> lesVolsDepart = new Hashtable<String, VolDepart>();
    private String villeArrivee ;

	public VolDepart(String idVol, Horaire heureDepart, Horaire heureArrivee,
			String ville, Avion avion) {
		
		super(idVol, heureDepart, heureArrivee, avion);
		villeArrivee = ville ;
		lesVolsDepart.put(idVol, this);

	}
	
	@Override
	public String toString() {
		return "Vol Départ n°" + super.toString() + "ville arrivée : " + villeArrivee;
	}

}
