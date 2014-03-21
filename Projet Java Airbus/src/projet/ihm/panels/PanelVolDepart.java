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
import projet.ihm.panels.PanelVol.ControleurTable;
import projet.ihm.panels.PanelVol.TableVol;
import projet.ihm.popups.PopupPlanning;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.SwingConstants;

public class PanelVolDepart extends PanelVol{
	
	public PanelVolDepart(ArrayList<Vol> lV) {
		super(lV);
		
		/* cr?ation du tableau d'agents */
		tableauVols = new JTable(new TableVol(lV));
		tableauVols.setAutoCreateRowSorter(true);

		
		ListSelectionModel listSelectionModel = tableauVols.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new ControleurTable());
		panelCenter.setLayout(new GridLayout(0, 1, 0, 0));

		
		panelCenter.add(new JScrollPane(tableauVols));	
		this.add(panelCenter,BorderLayout.CENTER);
	}
	
	private class TableVol extends AbstractTableModel {
		private ArrayList<Vol> vols ;
		private String index[] =  {"Id Vol","Heure Départ","Destination","Id Avion"};
		
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
		
		public Vol getAgent(int ligne) {
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
				return v.getAvion().getIdAvion();
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
	}
	
		
}
