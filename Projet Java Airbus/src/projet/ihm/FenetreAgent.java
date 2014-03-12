package projet.ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class FenetreAgent extends FenetreGestion{
	
	public FenetreAgent ()
	{
		super();
		FlowLayout grilleAgent = new FlowLayout();
		contentPane.setLayout(grilleAgent);
		setContentPane(contentPane);
		contentPane.setBackground(Color.RED);
		
		etat = "Agent";
		
	}
	
}
