package projet.ihm.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JDialog;
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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;

public class PopupPlanning extends JDialog {
	private Agent agent;
	private JPanel panelNord,panelCentre,panelSud,contentPane;
	private BorderLayout layout ;
	private FlowLayout layoutSud ;
	private JScrollPane planning ;
	
	public PopupPlanning(Agent a) {
		agent = a ;
		// Récupération du design système
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		setTitle("Agent " + agent.getMatricules() + " - Planning"); //On donne un titre à l'application
		setSize(450,400); //On donne une taille à notre fenètre
		setLocationRelativeTo(null); //On centre la fenètre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenètre
		setModal (true);
		setAlwaysOnTop (true);
		setModalityType (ModalityType.APPLICATION_MODAL);

		
		panelCentre = new JPanel();
		panelNord = new JPanel();
		panelSud = new JPanel();
		contentPane = new JPanel();
		
		layout = new BorderLayout() ;
		contentPane.setLayout(layout);

		this.setContentPane(contentPane);
		
		
		/* cr�ation footer */
		layoutSud = new FlowLayout();
		panelSud.setLayout(layoutSud);
		panelCentre.setLayout(new GridLayout(0, 1, 0, 0));
		planning = new JScrollPane(new JTable(new TablePlanning(a)));
		
		panelCentre.add(planning);	
		
		contentPane.add(panelNord,BorderLayout.NORTH);
		
		JLabel lblAgentNXxx = new JLabel("Agent n\u00B0 " + a.getMatricules() + " : ");
		lblAgentNXxx.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNom = new JLabel("Nom : " + a.getNom());
		
		JButton boutonRetard = new JButton("Indiquer un retard");
		JButton boutonAbsence = new JButton("Indiquer une abscence");
		
		boutonRetard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PopupRetard(agent);
			}
		});
		
		boutonAbsence.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				agent.absence();
				dispose();
			}
		});
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :" + a.getPrenom());
		
		JLabel lblHoraireDeDbut = new JLabel("Horaire de d\u00E9but : " + a.getHoraire().getDebutTrancheHoraire());
		
		JLabel lblHoraireDeFin = new JLabel("Horaire de fin : " + a.getHoraire().getFinTrancheHoraire());
		
		JLabel lblPlanning = new JLabel("Planning :");
		lblPlanning.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panelNord = new GroupLayout(panelNord);
		gl_panelNord.setHorizontalGroup(
			gl_panelNord.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNord.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelNord.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAgentNXxx)
						.addGroup(gl_panelNord.createSequentialGroup()
							.addGroup(gl_panelNord.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNom)
								.addComponent(lblHoraireDeDbut)
								.addComponent(boutonRetard))
							.addGap(43)
							.addGroup(gl_panelNord.createParallelGroup(Alignment.LEADING)
								.addComponent(boutonAbsence)
								.addComponent(lblHoraireDeFin)
								.addComponent(lblPrnom)))
						.addComponent(lblPlanning))
					.addContainerGap(154, Short.MAX_VALUE))
		);
		gl_panelNord.setVerticalGroup(
			gl_panelNord.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelNord.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblAgentNXxx)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelNord.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNom)
						.addComponent(lblPrnom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelNord.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHoraireDeDbut)
						.addComponent(lblHoraireDeFin))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelNord.createParallelGroup(Alignment.BASELINE)
						.addComponent(boutonRetard)
						.addComponent(boutonAbsence))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPlanning)
					.addGap(4))
		);
		panelNord.setLayout(gl_panelNord);
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
