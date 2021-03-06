package projet.ihm.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import projet.appli.Agent;
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

import javax.swing.JToggleButton;
import javax.swing.JComboBox;

public class PanelAgent extends JPanel{
	private JPanel courant = this;
	private JPanel panelBox, panelBtns, panelCenter;
	private BorderLayout layout ;
	private FlowLayout layoutBtns ;
	private JCheckBox tempsPlein, tempsPartiel ;
	private JTable tableauAgents ;
	private JButton boutonPlanning ;
	private ArrayList<Agent> lAgents;
	private TableAgent modeleAgent;
	private TableRowSorter sorter;
	private JLabel lblFiltrerPar, lblListeDesAgents;

	// Constructeur
	public PanelAgent(ArrayList<Agent> lA) {
		
		// Cr�ation des diff�rents panels pour le borderLayout
		lAgents = lA ;
		panelBox = new JPanel();
		panelBtns = new JPanel();
		panelCenter = new JPanel();
		layout = new BorderLayout();
		layoutBtns = new FlowLayout();
		this.setLayout(layout);
	
		
		//  cr�ation des check box et mise en place du panel
		tempsPlein = new JCheckBox("Agents � temps plein",false);
		tempsPartiel = new JCheckBox("Agents � temps partiel",false);
		
		// ecoute des checbox
		tempsPlein.addItemListener(new ItemListener() {
			
			// Algorithme d'�coute sur le clic de la checkbox
			public void itemStateChanged(ItemEvent item) {

				int status = item.getStateChange();
			if (tempsPlein.isSelected() && tempsPartiel.isSelected())
			{
		          sorter.setRowFilter(RowFilter.regexFilter(""));
		          
			}
			else if (status == ItemEvent.SELECTED)
				{
				          sorter.setRowFilter(RowFilter.regexFilter("P",0));
				}
			else if (status == ItemEvent.DESELECTED)
			{
				sorter.setRowFilter(RowFilter.regexFilter(""));
				
				if (status == ItemEvent.DESELECTED && tempsPartiel.isSelected())
				{
					sorter.setRowFilter(RowFilter.regexFilter("M",0));
				}
				

			}
		}
		});
		
	tempsPartiel.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent item) {

				int status = item.getStateChange();
			if (tempsPlein.isSelected() && tempsPartiel.isSelected())
			{
		          sorter.setRowFilter(RowFilter.regexFilter(""));

			}
			else if (status == ItemEvent.SELECTED)
				{
				          sorter.setRowFilter(RowFilter.regexFilter("M",0));
				}
			else if (status == ItemEvent.DESELECTED)
			{
				sorter.setRowFilter(RowFilter.regexFilter(""));
				
				if (status == ItemEvent.DESELECTED && tempsPlein.isSelected())
				{
					sorter.setRowFilter(RowFilter.regexFilter("P",0));
				}
			}			
		 }
		});
		this.add(panelBox,BorderLayout.NORTH);
		
		lblListeDesAgents = new JLabel("Liste des agents :");
		lblListeDesAgents.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblFiltrerPar = new JLabel("Filtrer par :");
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBox.createParallelGroup(Alignment.LEADING)
						.addComponent(lblListeDesAgents)
						.addGroup(gl_panelBox.createSequentialGroup()
							.addComponent(lblFiltrerPar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tempsPlein)))
					.addGap(80)
					.addComponent(tempsPartiel)
					.addGap(120))
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesAgents)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelBox.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiltrerPar)
						.addComponent(tempsPlein)
						.addComponent(tempsPartiel))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panelBox.setLayout(gl_panelBox);
		
		// cr�ation du bouton planning	
		boutonPlanning = new JButton("Voir planning");
		
		// Ecoute sur le boutton planning
		boutonPlanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PopupPlanning(lAgents.get(tableauAgents.convertRowIndexToModel(tableauAgents.getSelectedRow()))) ;
			}
		});
		boutonPlanning.setEnabled(false);
		panelBtns.add(boutonPlanning);
		this.add(panelBtns,BorderLayout.SOUTH);
		
		// cr�ation du tableau d'agents avec la JTable
		 modeleAgent = new TableAgent(lAgents);

		 // Cr�ation de la JTable permettant le tri
		tableauAgents = new JTable(modeleAgent);
		tableauAgents.setAutoCreateRowSorter(true);
		
		 sorter = new TableRowSorter(modeleAgent);
	    tableauAgents.setRowSorter(sorter);
		
		ListSelectionModel listSelectionModel = tableauAgents.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new ControleurTable());
		panelCenter.setLayout(new GridLayout(0, 1, 0, 0));

		
		panelCenter.add(new JScrollPane(tableauAgents));	
		this.add(panelCenter,BorderLayout.CENTER);
	}
	
	// Mod�le d'agent pour la JTable
	private class TableAgent extends AbstractTableModel {
		private ArrayList<Agent> agents ;
		private String index[] =  {"Identifiant","Nom","Pr�nom","Horaires"};
		
		public TableAgent(ArrayList<Agent> lAgents) {
			super();
			agents = lAgents ;
		}
		
		public int getColumnCount() {
			return index.length;
		}

		public int getRowCount() {
			return agents.size();
		}
		
		public Agent getAgent(int ligne) {
			return agents.get(ligne);
		}

		public Object getValueAt(int ligne, int colonne) {
			Agent a = agents.get(ligne);
			switch (colonne) {
			case 0:
				return a.getMatricules();
			case 1:
				return a.getNom();
			case 2:
				return a.getPrenom();
			case 3:
				return (a.isAbsent() ? "ABSENT" : a.getHoraire());
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
	}
	
	// Controleur sur la JTable qui active ou d�sactive le bouton "voir planning"
	private class ControleurTable  implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        if (!lsm.isSelectionEmpty()) {
	        	Agent a = lAgents.get(tableauAgents.convertRowIndexToModel(tableauAgents.getSelectedRow()));
	        	if (a.isAbsent()) {
		            boutonPlanning.setEnabled(false);
	        	} else boutonPlanning.setEnabled(true);
	        }			
		}
	}	
}
