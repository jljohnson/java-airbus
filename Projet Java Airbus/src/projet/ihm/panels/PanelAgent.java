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
import projet.ihm.FenetrePlanning;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAgent extends JPanel{
	JPanel courant = this;
	JPanel panelBox = new JPanel();
	JPanel panelBtns = new JPanel();
	JPanel panelCenter = new JPanel();
	BorderLayout layout = new BorderLayout();
	FlowLayout layoutBtns = new FlowLayout();
	JCheckBox tempsPlein, tempsPartiel ;
	JTable tableauAgents ;
	JButton boutonPlanning ;
	ArrayList<Agent> lAgents;


	public PanelAgent(ArrayList<Agent> lA) {
		lAgents = lA ;
		
		this.setLayout(layout);
	
		
		/* création des check box */
		tempsPlein = new JCheckBox("Agents à temps plein",true);
		tempsPartiel = new JCheckBox("Agents à temps partiel",true);
		
		panelBox.add(tempsPlein);
		panelBox.add(tempsPartiel);
		this.add(panelBox,BorderLayout.NORTH);
		
		/* création du bouton planning*/	
		boutonPlanning = new JButton("Voir planning");
		boutonPlanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FenetrePlanning(lAgents.get(tableauAgents.convertRowIndexToModel(tableauAgents.getSelectedRow()))) ;
			}
		});
		boutonPlanning.setEnabled(false);
		panelBtns.add(boutonPlanning);
		this.add(panelBtns,BorderLayout.SOUTH);
		
		/* création du tableau d'agents */
		tableauAgents = new JTable(new TableAgent(lAgents));
		
		ListSelectionModel listSelectionModel = tableauAgents.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new ControleurTable());

		
		panelCenter.add(new JScrollPane(tableauAgents));	
		this.add(panelCenter,BorderLayout.CENTER);
	}
	
	private class TableAgent extends AbstractTableModel {
		private ArrayList<Agent> agents ;
		private String index[] =  {"Identifiant","Nom","Prénom","Cycle de travail"};
		
		public TableAgent(ArrayList<Agent> lAgents) {
			super();
			agents = lAgents ;
		}
		
		@Override
		public int getColumnCount() {
			return index.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return agents.size();
		}
		
		public Agent getAgent(int ligne) {
			return agents.get(ligne);
		}

		@Override
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
				return a.getCycle();
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
	        if (lsm.isSelectionEmpty()) {
	            System.out.println("No rows selected");
	        }
	        else{
	            int selectedRow = lsm.getMinSelectionIndex();
	            System.out.println("The row "+selectedRow+" is now selected");
	            boutonPlanning.setEnabled(true);
	 
	        }			
		}
	}
	
	
}
