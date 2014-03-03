package projet.appli.vols;

import java.util.Hashtable;

import projet.appli.Avion;
import projet.appli.Vol;
import projet.outils.Horaire;

public class VolDepart extends Vol {

    static private Hashtable<String, Vol> lesVolsDepart = new Hashtable<String, Vol>();

	public VolDepart(String idVol, Horaire heureDepart, Horaire heureArrivee,
			String ville, Avion avion) {
		
		super(idVol, heureDepart, heureArrivee, ville, avion);
		lesVolsDepart.put(idVol, this);

	}
	
	@Override
	public String toString() {
		return "Vol Départ n°" + super.toString();
	}

}
