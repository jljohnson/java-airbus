package projet.ihm.panels;

import java.util.ArrayList;

import projet.appli.Vol;
import projet.appli.vols.VolArrivee;


public class PanelVolArrivee extends PanelVol{
	
	// M�me panel que vol en changeant le JLabel
	public PanelVolArrivee(ArrayList<Vol> lV) {
		super(VolArrivee.getVolsArrivee(), "Liste des vols d'arriv�e :");
	}
		
}
