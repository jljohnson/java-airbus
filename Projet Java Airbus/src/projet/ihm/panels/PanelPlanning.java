package projet.ihm.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.DefaultTableCellRenderer;

import projet.appli.Agent;
import projet.appli.Tache;
import projet.appli.taches.TacheAccueil;
import projet.appli.taches.TacheDebarquement;
import projet.appli.taches.TacheEmbarquement;
import projet.appli.taches.TacheEnregistrement;
import projet.appli.taches.TacheRepas;
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

public class PanelPlanning extends JPanel{
	private JPanel panelBox = new JPanel();
	private JPanel panelBtns = new JPanel();
	private JPanel panelCenter = new JPanel();
	private BorderLayout layout = new BorderLayout();
	private FlowLayout layoutBtns = new FlowLayout();
	private JTable tableauTaches ;
	private JButton boutonPlanning ;
	private ArrayList<Tache> lTaches;
	private JLabel lblAgentCorrespondant, lblNomAgent, lblPrnomAgent, lblHorairesAgent, lblVolCorrespondant, lblTypeVol, lblHeureVol;
	private JButton boutonVol;



	public PanelPlanning(ArrayList<Tache> lT) {
		lTaches = lT ;
		
		this.setLayout(layout);
		this.add(panelBox,BorderLayout.NORTH);
		
		JLabel lblListeDesAgents = new JLabel("Liste des taches :");
		lblListeDesAgents.setFont(new Font("Tahoma", Font.BOLD, 11));
		

		
		// création du bouton planning
		boutonPlanning = new JButton("Voir planning");
		boutonPlanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new PopupPlanning(lTaches.get(tableauTaches.convertRowIndexToModel(tableauTaches.getSelectedRow())).getAgent()) ;
			}
		});
		boutonPlanning.setEnabled(false);
		
		boutonVol = new JButton("Voir vols");
		
		// Gestion des écoutes sur les boutons
		boutonVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		boutonVol.setEnabled(false);

		this.add(panelBtns,BorderLayout.SOUTH);
		
		
		// Déclaration de l'ensemble des JLabels
		lblAgentCorrespondant = new JLabel("Agent correspondant :");
		lblAgentCorrespondant.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblNomAgent = new JLabel("Nom :");
		
		lblPrnomAgent = new JLabel("Pr\u00E9nom :");
		
		lblHorairesAgent = new JLabel("Horaires : ");
		
		lblVolCorrespondant = new JLabel("Vol correspondant :");
		lblVolCorrespondant.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblTypeVol = new JLabel("Type :");
		
		lblHeureVol = new JLabel("Heure : ");
		
		
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesAgents, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListeDesAgents)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panelBox.setLayout(gl_panelBox);
		
		
		GroupLayout gl_panelBtns = new GroupLayout(panelBtns);
		gl_panelBtns.setHorizontalGroup(
			gl_panelBtns.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBtns.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBtns.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelBtns.createSequentialGroup()
							.addGroup(gl_panelBtns.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAgentCorrespondant)
								.addComponent(lblNomAgent)
								.addComponent(lblPrnomAgent))
							.addGap(7)
							.addComponent(lblHorairesAgent))
						.addComponent(boutonPlanning))
					.addGap(64)
					.addGroup(gl_panelBtns.createParallelGroup(Alignment.LEADING)
						.addComponent(boutonVol, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeureVol)
						.addComponent(lblTypeVol)
						.addComponent(lblVolCorrespondant))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		gl_panelBtns.setVerticalGroup(
			gl_panelBtns.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBtns.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBtns.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAgentCorrespondant)
						.addComponent(lblVolCorrespondant))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelBtns.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomAgent)
						.addComponent(lblHorairesAgent)
						.addComponent(lblTypeVol))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelBtns.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrnomAgent)
						.addComponent(lblHeureVol))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelBtns.createParallelGroup(Alignment.BASELINE)
						.addComponent(boutonPlanning)
						.addComponent(boutonVol))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBtns.setLayout(gl_panelBtns);
		
		/* crï¿½ation du tableau d'agents */
		tableauTaches = new JTable(new TablePlanning(lTaches));
		tableauTaches.setAutoCreateRowSorter(true);
		tableauTaches.setDefaultRenderer(Object.class, new TachesRenderer());

		
		ListSelectionModel listSelectionModel = tableauTaches.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new ControleurTable());
		panelCenter.setLayout(new GridLayout(0, 1, 20, 10));

		
		panelCenter.add(new JScrollPane(tableauTaches));	
		this.add(panelCenter,BorderLayout.CENTER);
	}
	
	
	// Modèle pour la JTable
	private class TablePlanning extends AbstractTableModel {
		private ArrayList<Tache> taches ;
		private String index[] =  {"Identifiant","Horaires","Type"};
		
		public TablePlanning(ArrayList<Tache> lTaches) {
			super();
			taches = lTaches ;
		}
		
		public int getColumnCount() {
			return index.length;
		}

		public int getRowCount() {
			return taches.size();
		}
		
		public Tache getTache(int ligne) {
			return taches.get(ligne);
		}

		@Override
		public Object getValueAt(int ligne, int colonne) {
			Tache t = taches.get(ligne);
			switch (colonne) {
			case 0:
				return t.getIdTache();
			case 1:
				return t.getHoraires();
			case 2:
				return t.getType();
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
	}
	
	// Controleur permettant de voir quel agent est affecté à une tâche, quel vol est associé à la tâche
	private class ControleurTable  implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        if (!lsm.isSelectionEmpty()) {
	        	Tache t = lTaches.get(tableauTaches.convertRowIndexToModel(tableauTaches.getSelectedRow()));
	        	
	        	if ((t.getClass() == TacheAccueil.class) || (t.getClass() == TacheRepas.class)) {
	        		
        			lblVolCorrespondant.setText("Vol correspondant : " );
	        		lblHeureVol.setText("Heure : ");
	        		lblTypeVol.setText("Type :");
	        		lblVolCorrespondant.setEnabled(false);
	        		lblHeureVol.setEnabled(false);
	        		lblTypeVol.setEnabled(false);	
	        	} else {
	        		if (t.getClass() == TacheDebarquement.class) {
	        			TacheDebarquement tCast = (TacheDebarquement) t;
	        			lblVolCorrespondant.setText("Vol correspondant : " + tCast.getVol().getId());
		        		lblHeureVol.setText("Heure : " + tCast.getVol().getHeure());
		        		lblTypeVol.setText("Type : arrivée");
	        		}
	        		if (t.getClass() == TacheEmbarquement.class) {
	        			TacheEmbarquement tCast = (TacheEmbarquement) t;
	        			lblVolCorrespondant.setText("Vol correspondant : " + tCast.getVol().getId());
		        		lblHeureVol.setText("Heure : " + tCast.getVol().getHeure());
		        		lblTypeVol.setText("Type : départ");
	        		}
	        		if (t.getClass() == TacheEnregistrement.class) {
	        			TacheEnregistrement tCast = (TacheEnregistrement) t;
	        			lblVolCorrespondant.setText("Vol correspondant : " + tCast.getVol().getId());
		        		lblHeureVol.setText("Heure : " + tCast.getVol().getHeure());
		        		lblTypeVol.setText("Type : départ");
	        		}
	        		lblVolCorrespondant.setEnabled(true);
	        		lblHeureVol.setEnabled(true);
	        		lblTypeVol.setEnabled(true);
	        	}
	        	
				if (t.isAffectee()) {
	        		lblNomAgent.setText("Nom : " + t.getAgent().getNom());
	        		lblPrnomAgent.setText("Prénom : " + t.getAgent().getPrenom());
	        		lblHorairesAgent.setText("Horaire travail :" + t.getAgent().getHoraire().toString());
	        		lblNomAgent.setEnabled(true);
	        		lblPrnomAgent.setEnabled(true);
	        		lblHorairesAgent.setEnabled(true);
	        		

				} else {
	        		lblNomAgent.setText("Nom : NON AFFECTE");
	        		lblPrnomAgent.setText("Prénom : NON AFFECTE");
	        		lblHorairesAgent.setText("Horaire travail : NON AFFECTE");
	        		lblNomAgent.setEnabled(false);
	        		lblPrnomAgent.setEnabled(false);
	        		lblHorairesAgent.setEnabled(false);
				}

	        }			
		}
	}	
	
	// Permet de colorer les taches non affectées en rouge
	private class TachesRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component cell = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);

			if (!lTaches.get(row).isAffectee()) {
				cell.setBackground(Color.red);
			} else {
				if (tableauTaches.getSelectedRow() == row) {
					cell.setBackground(new Color(51,151,255));
				} else cell.setBackground(Color.white);
			}
			
			
			return cell;
		}

	}
}
