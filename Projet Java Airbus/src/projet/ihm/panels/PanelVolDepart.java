package projet.ihm.panels;

import java.util.ArrayList;

import projet.appli.Vol;
import projet.appli.vols.VolDepart;

public class PanelVolDepart extends PanelVol{
	
	public PanelVolDepart(ArrayList<Vol> lV) {
		super(VolDepart.getVolsDepart(),"Listes des vols au départ :");	
	}
}
