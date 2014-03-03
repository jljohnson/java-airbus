package projet.appli.vols;

import java.util.Hashtable;

import projet.appli.Avion;
import projet.appli.Vol;
import projet.outils.Horaire;


public class VolArrivee extends Vol {
	
	static private Hashtable<String, Vol> lesVolsArrivee = new Hashtable<String, Vol>();

	public VolArrivee(String idVol, Horaire heureDepart, Horaire heureArrivee,
			String ville, Avion avion) {
		super(idVol, heureDepart, heureArrivee, ville, avion);
		lesVolsArrivee.put(idVol, this);
	}
	
	@Override
	public String toString() {
		return "Vol Arrivée n°" + super.toString();
	}

}