
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

import com.sun.codemodel.internal.JLabel;

import projet.appli.Avion;

public class FenetreGestion extends JFrame {
	
	private Ecouteur monEcouteur;
	
	private JMenuBar menu;
	protected JMenu menuAvion,menuAgent,menuVol,menuPlanning,menuAccueil;
	protected JMenuItem listeAvion; 
	protected JPanel contentPane;
	protected String etat;
	
	
	
	
	public FenetreGestion() {
	
		monEcouteur = new Ecouteur();
	 
		// Récupération du design système
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){}
		
		setTitle("Gestion Aéroport"); //On donne un titre à l'application
		setSize(800,500); //On donne une taille à notre fenètre
		setLocationRelativeTo(null); //On centre la fenètre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenètre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
			
		// Création du menu
		 menu = new JMenuBar();
		this.setJMenuBar(menu);
		
		 menuAccueil = new JMenu("Accueil");
		 menuAvion = new JMenu("Avion");
		 menuAgent = new JMenu("Agent");
		 menuVol = new JMenu("Vols");
		 menuPlanning = new JMenu("Planning");
		
		//Ajout au menu principale
		menu.add(menuAccueil);
		menu.add(menuAvion);
		menu.add(menuAgent);
		menu.add(menuVol);
		menu.add(menuPlanning);
		
		//Création des items
		 listeAvion = new JMenuItem("ListeAvions");
		
		// Ajout des écouteurs
		 menuAccueil.addActionListener(monEcouteur);
		listeAvion.addActionListener(monEcouteur);
		
		// Ajout des composants
		 menuAvion.add(listeAvion);
		 
		 
		// Création du panel Principale (BorderLayout) on ne le touche plus
			contentPane = new JPanel();
			BorderLayout grillePrincipale = new BorderLayout();
			contentPane.setLayout(grillePrincipale);
			setContentPane(contentPane);
			
			
		// Modification sur chaque page en fonction de ce qu'on veut
			
			// Création du panel qu'on veut mettre en position north
			JPanel AccueilHaut = new JPanel();
			//Création de la grille dans le north
			FlowLayout grilleHaut = new FlowLayout();
			//Ajout de la grille au panel north
			AccueilHaut.setLayout(grilleHaut);
			
			// Composant que l'on veut mettre
			javax.swing.JLabel label = new javax.swing.JLabel("Bienvenue");
			// Ajout au panel haut
			AccueilHaut.add(label);
			
			// Ajout des panels cré
			contentPane.add(AccueilHaut,BorderLayout.NORTH);
			
			etat = "Gestion";

		this.setVisible(true);
	}
	


 private class Ecouteur implements ActionListener{
			  
		public void actionPerformed (ActionEvent evt)
		{
			Object source = evt.getSource();
			
		// Redirection depuis Gestion
		if (etat.equals("Gestion"));
		{
			if (source == listeAvion)
			{
				// dispose();
				 FenetreAgent FA = new FenetreAgent();
				System.out.println(etat);
				return;
			}
		}
			
		// Redirection depuis Agent
		if (etat.equals("Agent"))
		{
			if (source == menuAccueil)
			{
				dispose();
				FenetreGestion FG = new FenetreGestion();
				System.out.println(etat);
				return;
			}
		}
				
		}

	}
	
	

}
