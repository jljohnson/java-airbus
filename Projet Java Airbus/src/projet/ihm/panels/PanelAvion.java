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
import projet.appli.Avion;
import projet.appli.Vol;
import projet.ihm.popups.PopupPlanning;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

public class PanelAvion extends JPanel{
	JPanel courant = this;
	JPanel panelBox = new JPanel();
	JPanel panelCenter = new JPanel();
	BorderLayout layout = new BorderLayout();
	JTable tableauAvion ;
	ArrayList<Avion> lAvions;
	private final JLabel lblListeDesAvions = new JLabel("Liste des avions :\r\n");


	public PanelAvion(ArrayList<Avion> lA) {
		
		// Création du layout
		lAvions = lA ;
		this.setLayout(layout);
				
		this.add(panelBox,BorderLayout.NORTH);
		lblListeDesAvions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListeDesAvions.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesAvions)
					.addContainerGap(344, Short.MAX_VALUE))
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesAvions)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panelBox.setLayout(gl_panelBox);

		
		/* cr?ation du tableau d'agents */
		tableauAvion = new JTable(new TableAvion(lA));
		tableauAvion.setAutoCreateRowSorter(true);

		tableauAvion.setToolTipText("");
		
		ListSelectionModel listSelectionModel = tableauAvion.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new ControleurTable());
		panelCenter.setLayout(new GridLayout(0, 1, 20, 10));

		
		JScrollPane scrollPane = new JScrollPane(tableauAvion);
		panelCenter.add(scrollPane);
		this.add(panelCenter,BorderLayout.CENTER);
	}
	
	
	// Modèle pour la JTable
	private class TableAvion extends AbstractTableModel {
		private ArrayList<Avion> avions ;
		private String index[] =  {"Id Avion","Modèle","Capacité"};
		
		public TableAvion(ArrayList<Avion> lAvions) {
			super();
			avions = lAvions ;
		}
		
		
		public int getColumnCount() {
			return index.length;
		}

		public int getRowCount() {
			return avions.size();
		}
		
		public Avion getAvion(int ligne) {
			return avions.get(ligne);
		}

		@Override
		public Object getValueAt(int ligne, int colonne) {
			Avion a = avions.get(ligne);
			switch (colonne) {
			case 0:
				return a.getIdAvion();
			case 1:
				return a.getModele();
			case 2:
				return a.getCapacite();
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
	}
	
	// Controleur qui récupère la ligne sélectionnée
	private class ControleurTable  implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        if (!lsm.isSelectionEmpty()) {
	        	Avion a = lAvions.get(tableauAvion.convertRowIndexToModel(tableauAvion.getSelectedRow()));
	        }			
		}
	}	
	
	
}
