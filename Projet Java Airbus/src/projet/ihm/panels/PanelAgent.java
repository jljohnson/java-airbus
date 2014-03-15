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
import javax.swing.table.AbstractTableModel;

import projet.appli.Agent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class PanelAgent extends JPanel{
	JPanel courant = this;
	JPanel panelBox = new JPanel();
	JPanel panelBtns = new JPanel();
	JPanel panelCenter = new JPanel();
	BorderLayout layout = new BorderLayout();
	FlowLayout layoutBtns = new FlowLayout();
	JCheckBox tempsPlein, tempsPartiel ;
	JScrollPane tableauAgents ;
	JButton boutonPlanning ;


	public PanelAgent(ArrayList<Agent> lAgents) {
		
		this.setLayout(layout);
	
		
		/* création des check box */
		tempsPlein = new JCheckBox("Agents à temps plein",true);
		tempsPartiel = new JCheckBox("Agents à temps partiel",true);
		
		panelBox.add(tempsPlein);
		panelBox.add(tempsPartiel);
		this.add(panelBox,BorderLayout.NORTH);
		
		/* création du bouton planning*/	
		boutonPlanning = new JButton("Voir planning");
		boutonPlanning.setEnabled(false);
		panelBtns.add(boutonPlanning);
		this.add(panelBtns,BorderLayout.SOUTH);
		
		/* création du tableau d'agents */
		tableauAgents = new JScrollPane(new JTable(new TableAgent(lAgents)));
		tableauAgents.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				System.out.println("test");

			}
		});
		tableauAgents.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("test");
			}
		});
		
		panelCenter.add(tableauAgents);	
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
	
	
}
