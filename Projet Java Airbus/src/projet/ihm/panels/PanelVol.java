package projet.ihm.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import projet.appli.Agent;
import projet.appli.Vol;
import projet.ihm.popups.PopupPlanning;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelVol extends JPanel{
	JPanel courant = this;
	JPanel panelBox = new JPanel();
	JPanel panelBtns = new JPanel();
	JPanel panelCenter = new JPanel();
	BorderLayout layout = new BorderLayout();
	FlowLayout layoutBtns = new FlowLayout();
	JCheckBox tempsPlein, tempsPartiel ;
	JTable tableauVol ;
	ArrayList<Vol> lVols;


	public PanelVol(ArrayList<Vol> lV) {
		lVols = lV ;
		
		this.setLayout(layout);
	
		
		/* cr?ation des check box */
		
		panelBox.add(tempsPlein);
		panelBox.add(tempsPartiel);
		this.add(panelBox,BorderLayout.NORTH);
		
		/* cr?ation du tableau d'agents */
		tableauVol = new JTable(new TableVol(lV));
		
		ListSelectionModel listSelectionModel = tableauVol.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new ControleurTable());

		
		panelCenter.add(new JScrollPane(tableauVol));	
		this.add(panelCenter,BorderLayout.CENTER);
	}
	
	private class TableVol extends AbstractTableModel {
		private ArrayList<Vol> vols ;
		private String index[] =  {"Id Vol","Heure","Destination","Id Avion"};
		
		public TableVol(ArrayList<Vol> lVols) {
			super();
			vols = lVols ;
		}
		
		@Override
		public int getColumnCount() {
			return index.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return vols.size();
		}
		
		public Vol getVol(int ligne) {
			return vols.get(ligne);
		}

		@Override
		public Object getValueAt(int ligne, int colonne) {
			Vol v = vols.get(ligne);
			switch (colonne) {
			case 0:
				return v.getId();
			case 1:
				return v.getHeure();
			case 2:
				return v.getVille();
			case 3:
				return v.getAvion();
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
	}
	
	private class ControleurTable  implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        if (!lsm.isSelectionEmpty()) {
	        	Vol v = lVols.get(tableauVol.convertRowIndexToModel(tableauVol.getSelectedRow()));
	        }			
		}
	}	
}
