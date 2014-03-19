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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelVol extends JPanel{
	JPanel courant = this;
	JPanel panelBox = new JPanel();
	JPanel panelBtns = new JPanel();
	JPanel panelCenter = new JPanel();
	BorderLayout layout = new BorderLayout();
	FlowLayout layoutBtns = new FlowLayout();
	JTable tableauVols ;
	ArrayList<Vol> lVols;
	private JButton btnAnnulation;
	private JButton btnRetard;


	public PanelVol(ArrayList<Vol> lV) {
		lVols = lV ;
		
		this.setLayout(layout);
		this.add(panelBox,BorderLayout.NORTH);
		
		JLabel lblListeDesVols = new JLabel("Liste des Vols");
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addGap(21)
					.addComponent(lblListeDesVols)
					.addContainerGap(342, Short.MAX_VALUE))
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addGap(23)
					.addComponent(lblListeDesVols)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panelBox.setLayout(gl_panelBox);
		this.add(panelBtns,BorderLayout.SOUTH);
		
		btnRetard = new JButton("Retard");
		panelBtns.add(btnRetard);
		
		btnAnnulation = new JButton("Annulation");
		panelBtns.add(btnAnnulation);
		
	
	}
	
	 public  class TableVol extends AbstractTableModel {
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
				return v.getAvion().getIdAvion();
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
	}
	
	public class ControleurTable  implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        if (!lsm.isSelectionEmpty()) {
	        	Vol v = lVols.get(tableauVols.convertRowIndexToModel(tableauVols.getSelectedRow()));
	        }			
		}
	}	
}
