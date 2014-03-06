package projet.ihm;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class FenetreGestion extends JFrame {
	public FenetreGestion() {
		setTitle("Gestion planning agents"); //On donne un titre � l'application
		setSize(320,240); //On donne une taille � notre fen�tre
		setLocationRelativeTo(null); //On centre la fen�tre sur l'�cran
		setResizable(false); //On interdit la redimensionnement de la fen�tre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l'application de se fermer lors du clic sur la croix
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuAgent = new JMenu("Agent");
		
		menuBar.add(menuAgent);
		
		this.setJMenuBar(menuBar);
	}
}
