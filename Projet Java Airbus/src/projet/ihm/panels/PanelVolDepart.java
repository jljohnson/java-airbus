package projet.ihm.panels;

import java.util.ArrayList;

import projet.appli.Vol;
import projet.appli.vols.VolDepart;

public class PanelVolDepart extends PanelVol{
	
	// M�me panel que vol en changeant le JLabel
	public PanelVolDepart(ArrayList<Vol> lV) {
		super(VolDepart.getVolsDepart(),"Listes des vols au d�part :");	
	}
}
