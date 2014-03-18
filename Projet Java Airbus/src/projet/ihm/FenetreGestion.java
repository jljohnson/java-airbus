
package projet.ihm;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;









import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import projet.appli.Agent;
import projet.appli.Avion;
import projet.appli.Tache;
import projet.ihm.panels.PanelAgent;
import projet.ihm.panels.PanelAvion;
import projet.ihm.panels.PanelPlanning;

public class FenetreGestion extends JFrame {
		
	private JMenuBar menu;
	protected JMenu menuAvion,menuAgent,menuVol,menuPlanning,menuFichier;
	protected JMenuItem listeAvion,listeAgent,listeVolA,listeVolD,listePlanning,quitter; 
	protected JPanel contentPane,panelPlanning,panelAgent,panelAvion,panelCourant;
	
	
	
	
	public FenetreGestion() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		setTitle("Gestion A��roport"); // On donne un titre �� l'application
		setSize(600, 450); // On donne une taille �� notre fen��tre
		setLocationRelativeTo(null); // On centre la fen��tre sur l'��cran
		setResizable(false); // On interdit la redimensionnement de la fen��tre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout());
		ArrayList<Tache> tachesPlanning = new ArrayList<Tache>(Tache.getTaches());
		panelPlanning = new PanelPlanning(tachesPlanning);
		panelAgent = new PanelAgent(Agent.getAgents());
		
		panelAvion = new PanelAvion(Avion.getAvions());

		// Cr��ation du menu
		menu = new JMenuBar();
		this.setJMenuBar(menu);

		menuFichier = new JMenu("Fichier");
		menuAvion = new JMenu("Avion");
		menuAgent = new JMenu("Agent");
		menuVol = new JMenu("Vols");
		menuPlanning = new JMenu("Planning");
		
		// Ajout au menu principal
		menu.add(menuFichier);
		menu.add(menuAvion);
		menu.add(menuAgent);
		menu.add(menuVol);
		menu.add(menuPlanning);

		// Cr��ation des items
		listeAvion = new JMenuItem("Liste des avions");		
		listeVolA = new JMenuItem("Vols arriv�es");
		listeVolD = new JMenuItem("Vols d�part");
		listeAgent = new JMenuItem("Liste des agents");
		listePlanning = new JMenuItem("Liste des t�ches");
		quitter = new JMenuItem("Quitter");
		
		listeAvion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changerPanel(panelAvion);
			}
		});
		
		listePlanning.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changerPanel(panelPlanning);
			}
		});
		
		listeAgent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changerPanel(panelAgent);
			}
		});
		
		quitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
			listeVolA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Arrive");				
			}
		});
			
		listeVolD.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Depart");				
			}
		});


		// Ajout des composants
		menuAvion.add(listeAvion);
		menuAgent.add(listeAgent);
		menuVol.add(listeVolA);
		menuVol.add(listeVolD);
		menuPlanning.add(listePlanning);
		menuFichier.add(quitter);
		
		this.setContentPane(contentPane);
		contentPane.add(panelAgent);
		panelCourant = panelAgent;
		
		this.setVisible(true);
	}
	
	private void changerPanel(JPanel p) {
		panelCourant.setVisible(false);
		contentPane.remove(panelCourant);
		panelCourant = p;
		contentPane.add(panelCourant);
		panelCourant.setVisible(true);
	}

}
