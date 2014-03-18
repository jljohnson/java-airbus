package projet.ihm.popups;

import javax.swing.JDialog;

import projet.appli.Agent;
import projet.exceptions.SaisieInvalideException;
import projet.outils.Horaire;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PopupRetard extends JDialog {
	private Agent agent;
	private JTextField textH;
	private JTextField textM;
	private JLabel lblHoraire;
	private JLabel lblHeureDeRetard;
	private JLabel lblH;
	private JLabel lblM;
	
	public PopupRetard(Agent a) {
		agent = a ;
		setModal (true);
		setAlwaysOnTop (true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		setLocationRelativeTo(null); //On centre la fenètre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenètre
		setSize(234,137); //On donne une taille à notre fenètre

		
		this.setTitle("Agent " + a.getMatricules() + " - retard");
		
		lblHoraire = new JLabel("Horaire actuel : " + a.getHoraire().toString());
		
		lblHeureDeRetard = new JLabel("Heure de retard :");
		
		textH = new JTextField();
		textH.setColumns(10);
		
		lblH = new JLabel("h");
		
		textM = new JTextField();
		textM.setColumns(10);
		
		lblM = new JLabel("m");
		
		JButton btnValider = new JButton("Valider");
		
		JButton btnAnnuler = new JButton("Annuler");
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int h = Integer.parseInt(textH.getText());
					int m = Integer.parseInt(textH.getText());
					Horaire retard = new Horaire(h,m) ;
					if (saisieValide(retard)) {
						agent.retard(retard);
						dispose();
					}
				}
				catch (NumberFormatException numberException) {
					JOptionPane.showMessageDialog(null, "Veuillez entrer des nombres valides.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
				} catch (SaisieInvalideException e1) {
					JOptionPane.showMessageDialog(null, "Veuillez entrer un horaire valide.", "Erreur horaire", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHoraire)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblHeureDeRetard)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblH)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textM, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblM))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnValider)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAnnuler)))
					.addContainerGap(234, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHoraire)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeureDeRetard)
						.addComponent(textH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblH)
						.addComponent(textM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblM))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnValider)
						.addComponent(btnAnnuler))
					.addContainerGap(181, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		this.setVisible(true);
		
	}
	
	public boolean saisieValide(Horaire h) throws SaisieInvalideException {
		if (h.compareTo(agent.getHoraire().getFinTrancheHoraire()) >= 0) {
			throw new SaisieInvalideException();
		}
		return true ;
	}
}
