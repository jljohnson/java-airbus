
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
import projet.appli.Vol;
import projet.appli.vols.VolArrivee;
import projet.appli.vols.VolDepart;
import projet.ihm.panels.PanelAgent;
import projet.ihm.panels.PanelAvion;
import projet.ihm.panels.PanelPlanning;
import projet.ihm.panels.PanelVol;
import projet.ihm.panels.PanelVolArrivee;
import projet.ihm.panels.PanelVolDepart;

public class FenetreGestion extends JFrame {
		
	// Déclaration des objets graphiques
	private JMenuBar menu;
	protected JMenu menuAvion,menuAgent,menuVol,menuPlanning,menuFichier;
	protected JMenuItem listeAvion,listeAgent,listeVolA,listeVolD,listePlanning,quitter; 
	protected JPanel contentPane,panelPlanning,panelAgent,panelAvion,panelVolArrivee,panelVolDepart,panelCourant;
	
	public FenetreGestion() {
		try {
			// Permet de prendre l'apparence du système hôte
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		// Paramètre initiale pour la fenêtre
		setTitle("Gestion Aéroport"); // On donne un titre �� l'application
		setSize(600, 450); // On donne une taille �� notre fen��tre
		setLocationRelativeTo(null); // On centre la fen��tre sur l'��cran
		setResizable(false); // On interdit la redimensionnement de la fen��tre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		// Initialisation du panel principal
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout());
		
		// Initialisation des différentes JTable avec les listes
		ArrayList<Tache> tachesPlanning = new ArrayList<Tache>(Tache.getTaches());
		panelPlanning = new PanelPlanning(tachesPlanning);
		panelAgent = new PanelAgent(Agent.getAgents());
		panelAvion = new PanelAvion(Avion.getAvions());
		panelVolArrivee = new PanelVolArrivee(VolArrivee.getVolsArrivee());
		panelVolDepart = new PanelVolDepart (VolDepart.getVolsDepart());

		// Création de la JMenuBar
		menu = new JMenuBar();
		this.setJMenuBar(menu);

		// Déclaration des JMenu
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

		// Création des items
		listeAvion = new JMenuItem("Liste des avions");		
		listeVolA = new JMenuItem("Vols arrivées");
		listeVolD = new JMenuItem("Vols départ");
		listeAgent = new JMenuItem("Liste des agents");
		listePlanning = new JMenuItem("Liste des tâches");
		quitter = new JMenuItem("Quitter");
		
		// Création de l'écoute sur le bouton listeAvion
		listeAvion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				changerPanel(panelAvion);
			}
		});
		
		// Création de l'écoute sur le bouton listePlanning
		listePlanning.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				changerPanel(panelPlanning);
			}
		});
		
		// Création de l'écoute sur le bouton listeAgent
		listeAgent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				changerPanel(panelAgent);
			}
		});
		
		// Création de l'écoute sur le bouton quitter
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
		// Création de l'écoute sur le bouton listeVolA
			listeVolA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				changerPanel(panelVolArrivee);			}
		});
			
			// Création de l'écoute sur le bouton listeVolB
		listeVolD.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				changerPanel(panelVolDepart);				
			}
		});
		
		// Ajout des composants à la JMenuBar
		menuAvion.add(listeAvion);
		menuAgent.add(listeAgent);
		menuVol.add(listeVolA);
		menuVol.add(listeVolD);
		menuPlanning.add(listePlanning);
		menuFichier.add(quitter);
		
		// Modification du panel courant, par défaut l'application s'ouvre sur la gestion des agents
		this.setContentPane(contentPane);
		contentPane.add(panelAgent);
		panelCourant = panelAgent;
		
		this.setVisible(true);
	}
	
	// Méthode qui permet de changer le JPanel du contentpane
	private void changerPanel(JPanel p) {
		panelCourant.setVisible(false);
		contentPane.remove(panelCourant);
		panelCourant = p;
		contentPane.add(panelCourant);
		panelCourant.setVisible(true);
	}

}
