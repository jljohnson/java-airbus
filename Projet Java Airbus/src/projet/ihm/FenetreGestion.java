package projet.ihm;

import javax.swing.JFrame;

public class FenetreGestion extends JFrame {
	public FenetreGestion() {
		setTitle("Gestion planning agents"); //On donne un titre � l'application
		setSize(320,240); //On donne une taille � notre fen�tre
		setLocationRelativeTo(null); //On centre la fen�tre sur l'�cran
		setResizable(false); //On interdit la redimensionnement de la fen�tre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l'application de se fermer lors du clic sur la croix
	}
}
