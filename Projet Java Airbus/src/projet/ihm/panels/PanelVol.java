package projet.ihm.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import projet.appli.Agent;
import projet.appli.Vol;
import projet.ihm.popups.PopupPlanning;
import projet.ihm.popups.PopupRetardVol;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.SwingConstants;

public class PanelVol extends JPanel{
	private JPanel courant = this;
	private JPanel panelBox, panelBtns, panelCenter;
	private BorderLayout layout ;
	private FlowLayout layoutBtns ;
	private JTable tableauVols ;
	private ArrayList<Vol> lVols;
	private JButton btnAnnulation;
	private JButton btnRetard;


	public PanelVol(ArrayList<Vol> lV, String nomListe) {
		lVols = lV ;
		panelBox = new JPanel();
		panelBtns = new JPanel();
		panelCenter = new JPanel();
		layout = new BorderLayout();
		layoutBtns = new FlowLayout();

		this.setLayout(layout);
		this.add(panelBox,BorderLayout.NORTH);
		
		JLabel lblListeDesVols = new JLabel(nomListe);
		GroupLayout gl_panelBox = new GroupLayout(panelBox);
		gl_panelBox.setHorizontalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addGap(21)
					.addComponent(lblListeDesVols)
					.addContainerGap(342, Short.MAX_VALUE))
		);
		gl_panelBox.setVerticalGroup(
			gl_panelBox.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBox.createSequentialGroup()
					.addGap(23)
					.addComponent(lblListeDesVols)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panelBox.setLayout(gl_panelBox);
		this.add(panelBtns,BorderLayout.SOUTH);
		
		btnRetard = new JButton("Retard");
		panelBtns.add(btnRetard);
		
		btnRetard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PopupRetardVol(lVols.get(tableauVols.convertRowIndexToModel(tableauVols.getSelectedRow()))) ;
			}
		});
		btnRetard.setEnabled(false);

		
		btnAnnulation = new JButton("Annulation");
		btnAnnulation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lVols.get(tableauVols.convertRowIndexToModel(tableauVols.getSelectedRow())).annulerVol();
			}
		});
		panelBtns.add(btnAnnulation);
		
		tableauVols = new JTable(new TableVol(lV));
		tableauVols.setAutoCreateRowSorter(true);
		tableauVols.setDefaultRenderer(Object.class, new TachesRenderer());


		
		ListSelectionModel listSelectionModel = tableauVols.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new ControleurTable());
		panelCenter.setLayout(new GridLayout(0, 1, 0, 0));

		
		panelCenter.add(new JScrollPane(tableauVols));	
		this.add(panelCenter,BorderLayout.CENTER);

		
	
	}
	
	 public  class TableVol extends AbstractTableModel {
		private ArrayList<Vol> vols ;
		private String index[] =  {"Id Vol","Heure","Destination","Id Avion"};
		
		public TableVol(ArrayList<Vol> lVols) {
			super();
			vols = lVols ;
		}
		
		@Override
		public int getColumnCount() {
			return index.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return vols.size();
		}
		
		public Vol getVol(int ligne) {
			return vols.get(ligne);
		}

		@Override
		public Object getValueAt(int ligne, int colonne) {
			Vol v = vols.get(ligne);
			switch (colonne) {
			case 0:
				return v.getId();
			case 1:
				return v.getHeure();
			case 2:
				return v.getVille();
			case 3:
				return v.getAvion().getIdAvion();
			default:
				return null;
			}
		}
		
		public String getColumnName(int colonne) {
	        return index[colonne];
	    }
	}
	
	public class ControleurTable  implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			if (listSelectionEvent.getValueIsAdjusting())
	            return;
	        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
	        
	        if (!lsm.isSelectionEmpty()) {
	        	Vol v = lVols.get(tableauVols.convertRowIndexToModel(tableauVols.getSelectedRow()));
	        	btnRetard.setEnabled(true);
	        }	
	        else
	        {
	        	btnRetard.setEnabled(false);
	        }
		}
	}	
	
	private class TachesRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component cell = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);

			if (lVols.get(row).isAnnule()) {
				cell.setBackground(Color.red);
			} else {
				if (lVols.get(row).isRetard()) {
					cell.setBackground(Color.GREEN);					
				} else {
					if (tableauVols.getSelectedRow() == row) {
						cell.setBackground(new Color(51,151,255));
					} else cell.setBackground(Color.white);
				}
			}
			
			
			return cell;
		}

	}
}
