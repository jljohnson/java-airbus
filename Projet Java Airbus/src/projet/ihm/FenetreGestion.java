
package projet.ihm;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;




import projet.appli.Agent;
import projet.appli.Avion;
import projet.ihm.panels.PanelAgent;

public class FenetreGestion extends JFrame {
		
	private JMenuBar menu;
	protected JMenu menuAvion,menuAgent,menuVol,menuPlanning;
	protected JMenuItem listeAvion; 
	protected JPanel contentPane;
	protected String etat;
	
	
	
	
	public FenetreGestion() {
		setTitle("Gestion Aéroport"); // On donne un titre à l'application
		setSize(900, 700); // On donne une taille à notre fenètre
		setLocationRelativeTo(null); // On centre la fenètre sur l'écran
		setResizable(false); // On interdit la redimensionnement de la fenètre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		// Création du menu
		menu = new JMenuBar();
		this.setJMenuBar(menu);

		menuAvion = new JMenu("Avion");
		menuAgent = new JMenu("Agent");
		menuVol = new JMenu("Vols");
		menuPlanning = new JMenu("Planning");

		// Ajout au menu principale
		menu.add(menuAvion);
		menu.add(menuAgent);
		menu.add(menuVol);
		menu.add(menuPlanning);

		// Création des items
		listeAvion = new JMenuItem("Liste des avions");

		// Ajout des composants
		menuAvion.add(listeAvion);

		this.setContentPane(new PanelAgent(Agent.getAgents()));

		this.setVisible(true);
	}

}
