package projet.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;

import org.w3c.dom.views.AbstractView;

import projet.appli.Agent;
import projet.appli.Tache;

public class FenetrePlanning extends JFrame {
	private Agent agent;
	private JPanel panelNord,panelCentre,panelSud,contentPane;
	private BorderLayout layout ;
	private FlowLayout layoutSud ;
	private JScrollPane planning ;
	
	public FenetrePlanning(Agent a) {
		agent = a ;
		// Récupération du design système
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		setTitle("Agent " + agent.getMatricules() + " - Planning"); //On donne un titre à l'application
		setSize(600,500); //On donne une taille à notre fenètre
		setLocationRelativeTo(null); //On centre la fenètre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenètre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		
		panelCentre = new JPanel();
		panelNord = new JPanel();
		panelSud = new JPanel();
		contentPane = new JPanel();
		
		layout = new BorderLayout() ;
		contentPane.setLayout(layout);

		this.setContentPane(contentPane);
		
		/* cr�ation header */
		panelNord.setLayout(new FlowLayout());
		panelNord.add(new JLabel("Agent : " + agent.getNom())) ;
		
		
		/* cr�ation footer */
		layoutSud = new FlowLayout();
		panelSud.setLayout(layoutSud);
		
		panelSud.add(new JButton("Indiquer un retard"));
		panelSud.add(new JButton("Indiquer une abscence"));
		
	
		/* cr�ation planning */	
		panelCentre.setLayout(new FlowLayout());
		planning = new JScrollPane(new JTable(new TablePlanning(a)));
		
		panelCentre.add(planning);	
		
		contentPane.add(panelNord,BorderLayout.NORTH);
		contentPane.add(panelCentre,BorderLayout.CENTER);
		contentPane.add(panelSud,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	class TablePlanning extends AbstractTableModel {
		private ArrayList<Tache> taches ;
		private String[] index = {"Horaires","Num�ro","Description"};

		public TablePlanning(Agent a) {
			super();
			taches = new ArrayList<Tache>(a.getPlanning());
		}
		@Override
		public int getColumnCount() {
			return index.length ;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return taches.size();
		}

		@Override
		public Object getValueAt(int ligne, int colonne) {
			Tache t = taches.get(ligne);
			
			switch(colonne) {
				case 0 :
					return t.getHoraires().toString();
				case 1 :
					return t.getIdTache();
				case 2 : 
					return t.toString();
				default:
					return null;
			}
		}
		
	    public String getColumnName(int columnIndex) {
	        return index[columnIndex];
	    }
		
	}
}
