
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

public class FenetreGestion extends JFrame {
	public FenetreGestion() {
		setTitle("Gestion Aéroport Paris"); //On donne un titre à l'application
		setSize(800,500); //On donne une taille à notre fenètre
		setLocationRelativeTo(null); //On centre la fenètre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenètre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
			
		// Création du menu
		JMenuBar menuBar = new JMenuBar();
		
		// Création des différents onglets
		JMenu menuAgent = new JMenu("Agents");
		JMenu menuAvions = new JMenu("Avions");
		JMenu menuVols= new JMenu("Vols");
		JMenu menuPlanning = new JMenu("Planning");
		JMenu menuQuitter= new JMenu("Quitter");
		
		// Ajout au menu principale
		menuBar.add(menuAgent);
		menuBar.add(menuAvions);
		menuBar.add(menuVols);
		menuBar.add(menuPlanning);
		menuBar.add(menuQuitter);
		
		// Création des menus item pour agent
		JMenuItem voirAgent = new JMenuItem("Liste Des Agents");
		menuAgent.add(voirAgent);
		
		// Création des menus items pour avions
		JMenuItem voirAvions = new JMenuItem("Liste Des Avions");
		menuAvions.add(voirAvions);
		
		// Création des menus item pour Vols
		JMenuItem voirArrive = new JMenuItem("Liste Des Vols Arrivés");
		menuVols.add(voirArrive);
		
		JMenuItem voirDepart = new JMenuItem("Liste Des Vols Départ");
		menuVols.add(voirDepart);
		
		// Création dans le menu planning
		JMenuItem voirPlanning = new JMenuItem("Afficher le planning");
		menuPlanning.add(voirPlanning);
	
		// Affichage du menu
		this.setJMenuBar(menuBar);
		
		// Création du panel principale + Grille + Ajout à la fenetre
		JPanel panneauPrincipale = new JPanel ();
		BorderLayout grillePrincipale = new BorderLayout ();
		panneauPrincipale.setLayout(grillePrincipale);
		
		// Ajout de la combo Box
		JCheckBox boxPlein = new JCheckBox("Agent Temps Plein");
		JCheckBox boxPartiel = new JCheckBox("Agent Temps Partiel");
		
		// Création du pannel north
		JPanel pannelNord = new JPanel();
		pannelNord.add(boxPlein);
		pannelNord.add(boxPartiel);
		
		panneauPrincipale.add(pannelNord,BorderLayout.NORTH);
		
		
		// Création de la jtable 
		
		JTable tableAgent = new JTable();
		panneauPrincipale.add(tableAgent,BorderLayout.CENTER);
		
		
	
		this.add(panneauPrincipale);

		
	
	
	}
	
	
	
	public static void main(String[] args) 
	{
		JFrame f = new FenetreGestion();
		f.setVisible(true);
		
	
	}
}
