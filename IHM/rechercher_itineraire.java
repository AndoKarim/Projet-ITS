package IHM;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import Consommation.CalculEnergie;
import Consommation.Vehicule;
import COMMON.Lieu;
import PARKING.Parking;
import BDD.BD_Lieux;
import BDD.BD_Parking;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import parcours.GestionnaireLieu;
import parcours.TousChemins;
import parcours.Troncon;

public class rechercher_itineraire extends JFrame {

	private JPanel contentPane;
	private final JTextField textField1;
	private JTextField textField2;
	private String coordGPSString;
	private String coordDepartString;
	private String coordDestinationString;
	private boolean var;
	Lieu coordGPS;
	ArrayList<Lieu> arraylistLieux = new ArrayList<Lieu>();
	ArrayList<Lieu> arraylistLieux2 = new ArrayList<Lieu>();
	ArrayList<Lieu> arraylistParking = new ArrayList<Lieu>();
	ArrayList<Lieu> arraylistParking2 = new ArrayList<Lieu>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rechercher_itineraire frame = new rechercher_itineraire();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public rechercher_itineraire() throws Exception {
		setTitle("Interactive Transport System - Rechercher Itinéraire");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final BD_Lieux bdL = new BD_Lieux();
		final BD_Parking bdP = new BD_Parking();
		//Simulation de coordonnées GPS
		coordGPS = new Lieu("IUT de Nice", 43.6705476, 7.2032781);

		// image de fond
		JLabel backgroundImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("../images/background_itineraire_2.jpg"))
				.getImage();
		backgroundImg.setIcon(new ImageIcon(img));
		backgroundImg.setBounds(0, 0, 188, 521);
		contentPane.add(backgroundImg);

		// bouton retour au menu principal
		JButton btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				main_window b1 = new main_window();
				b1.setVisible(true);
			}
		});
		btnRetour.setBounds(895, 485, 89, 25);
		contentPane.add(btnRetour);

		// textArea qui affiche le trajet, sera setVisible(true) dans la methode
		// de calcule du trajet
		final JTextArea textAreaTrajet = new JTextArea();
		textAreaTrajet.setText("");
		textAreaTrajet.setBounds(765, 27, 219, 449);
		textAreaTrajet.setEditable(false);
		contentPane.add(textAreaTrajet);
		
		final JScrollPane scrollPane3 = new JScrollPane(textAreaTrajet);
		scrollPane3.setBounds(765, 27, 219, 449);
		contentPane.add(scrollPane3);
		scrollPane3.setVisible(false);
		
		// ------bouton Trajet qui active deux autres boutons
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCalculer.setBounds(422, 126, 117, 30);
		contentPane.add(btnCalculer);

		// label du premier textField pour le lieu de depart
		JLabel lblDepart = new JLabel("Entrez lieu de départ");
		lblDepart.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDepart.setBounds(392, 11, 172, 14);
		contentPane.add(lblDepart);

		// textField pour le lieu de depart, se desactive quand le GPS est
		// active
		textField1 = new JTextField("");
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField1.setBounds(392, 36, 172, 25);
		contentPane.add(textField1);
		textField1.setColumns(10);

		// label du premier textField pour le lieu d'arrive
		JLabel lblDestination = new JLabel("Entrez lieu d'arrivé");
		lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDestination.setBounds(392, 67, 172, 14);
		contentPane.add(lblDestination);

		// textField pour le lieu d'arrive
		textField2 = new JTextField("");
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField2.setBounds(392, 90, 172, 25);
		contentPane.add(textField2);
		textField2.setColumns(10);

		// ------creation du checkbox
		final JCheckBox checkBoxGPS = new JCheckBox("Utiliser GPS");
		checkBoxGPS.setBackground(Color.WHITE);
		checkBoxGPS.setFont(new Font("Tahoma", Font.PLAIN, 17));
		checkBoxGPS.setBounds(570, 35, 172, 23);
		contentPane.add(checkBoxGPS);

		// ------bouton qui appelle la methode qui return le trajet le moins
		// cher
		final JButton btnTrajetMoinsCher = new JButton("Trajet le moins cher");
		btnTrajetMoinsCher.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnTrajetMoinsCher.setBounds(369, 162, 217, 30);
		contentPane.add(btnTrajetMoinsCher);
		btnTrajetMoinsCher.setVisible(false);

		// bouton qui appelle la methode qui return le trajet le plus rapide
		final JButton btnTrajetPlusRapide = new JButton("Trajet le plus rapide");
		btnTrajetPlusRapide.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnTrajetPlusRapide.setBounds(369, 199, 217, 30);
		contentPane.add(btnTrajetPlusRapide);
		btnTrajetPlusRapide.setVisible(false);

		// comboBox pour le lieu de Depart
		final JComboBox<String> comboBox1 = new JComboBox<String>();
		comboBox1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox1.setBounds(221, 240, 257, 23);
		contentPane.add(comboBox1);

		// comboBox pour le lieu d'arrive
		final JComboBox<String> comboBox2 = new JComboBox<String>();
		comboBox2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox2.setBounds(483, 240, 257, 23);
		contentPane.add(comboBox2);

		// textArea affichant le LIEU de depart
		final JTextArea textAreaCoord = new JTextArea();
		textAreaCoord.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaCoord.setBounds(369, 243, 214, 183);
		textAreaCoord.setEditable(false);

		// scrollPane qui contient le textAreaCoord
		final JScrollPane scrollPane1 = new JScrollPane(textAreaCoord);
		scrollPane1.setBounds(221, 287, 257, 183);
		contentPane.add(scrollPane1);

		// textArea affichant le LIEU de destination
		final JTextArea textAreaDesti = new JTextArea();
		textAreaDesti.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaDesti.setBounds(626, 243, 214, 183);
		textAreaDesti.setEditable(false);

		// scrollPane qui contient le textAreaDesti
		final JScrollPane scrollPane2 = new JScrollPane(textAreaDesti);
		scrollPane2.setBounds(483, 287, 257, 183);
		contentPane.add(scrollPane2);

		
		JCheckBox checkBoxParking = new JCheckBox("Itinéraire parking");
		checkBoxParking.setBackground(Color.WHITE);
		checkBoxParking.setFont(new Font("Tahoma", Font.PLAIN, 17));
		checkBoxParking.setBounds(570, 126, 189, 23);
		contentPane.add(checkBoxParking);


		scrollPane1.setVisible(false); // sera setVisible(true) dans le trigger
		scrollPane2.setVisible(false); // du boutton "Calculer"
		comboBox1.setVisible(false); // sera setVisible(true) dans le trigger
		comboBox2.setVisible(false); // de la comboBox

		// ==========
		// TRIGGERS
		// ==========

		checkBoxParking.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					var = true;
					comboBox1.setSelectedIndex(0);
					comboBox2.setSelectedIndex(0);
					textAreaCoord.setText("");
					textAreaDesti.setText("");
					// System.out.println("var = true");
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					var = false;

					// System.out.println("var = false");
				}
			}
		});

		textField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordDepartString = "^*" + textField1.getText() + ".*$";
				arraylistLieux = bdL.find(coordDepartString);
				String[] temp = new String[arraylistLieux.size() + 1];
				for (int i = 0; i < arraylistLieux.size() + 1; i++) {
					if (i == 0)
						temp[i] = "";
					else
						temp[i] = arraylistLieux.get(i - 1).getNom();
				}
				comboBox1.removeAllItems();
				ComboBoxModel<String> model = new DefaultComboBoxModel<String>(
						temp);
				comboBox1.setModel(model);
			}
		});

		textField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordDestinationString = "^*" + textField2.getText() + ".*$";
				arraylistLieux2 = bdL.find(coordDestinationString);
				String[] temp = new String[arraylistLieux2.size() + 1];
				for (int i = 0; i < arraylistLieux2.size() + 1; i++) {
					if (i == 0)
						temp[i] = "";
					else
						temp[i] = arraylistLieux2.get(i - 1).getNom();
				}
				comboBox2.removeAllItems();
				ComboBoxModel<String> model = new DefaultComboBoxModel<String>(
						temp);
				comboBox2.setModel(model);

			}
		});

		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTrajetMoinsCher.setVisible(true);
				btnTrajetPlusRapide.setVisible(true);
				comboBox1.setVisible(true);
				comboBox2.setVisible(true);
				if (checkBoxGPS.isSelected())
					textAreaDesti.setText("Lieu de destination :\n"
							+ arraylistParking2);
				else {
					textAreaCoord.setText("Lieu de départ :\n"
							+ arraylistParking);
					textAreaDesti.setText("Lieu de destination :\n"
							+ arraylistParking2);
				}
			}
		});

		btnTrajetMoinsCher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String resultat = "";
				Vehicule uneVoiture = new Vehicule(60, 60, 17, 16, 15, 3000);
				
				Lieu depart = GestionnaireLieu.findLieu(comboBox1.getSelectedItem().toString());
				Lieu arrivee = GestionnaireLieu.findLieu(comboBox2.getSelectedItem().toString());
								
				CalculEnergie.trajetMoinsCher(depart, arrivee, uneVoiture);
				ArrayList<Troncon> dsa = CalculEnergie.trajetMoinsCher(depart, arrivee, uneVoiture);
				for(Troncon t : dsa){
					resultat += t.getNom() +"\n"+ t.getLieuDepart().getNom() +"|"+ t.getLieuArrivee().getNom() +"\n--------\n";
				}
				
				textAreaTrajet.setVisible(true);
				textAreaTrajet.setText(resultat);
				scrollPane3.setVisible(true);
				// trajetMoinsCher(lieu depart, lieu arrivee);
			}
		});

		btnTrajetPlusRapide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// trajetPlusRapide(lieu depart, lieu arrivee);
				TousChemins ts = new TousChemins();
				Lieu dep = GestionnaireLieu.findLieu(comboBox1.getSelectedItem().toString());
				Lieu arr = GestionnaireLieu.findLieu(comboBox2.getSelectedItem().toString());
				ArrayList<Troncon> t = ts.plusRapide(dep, arr);
				String text ="";
				for(Troncon tt : t){
				text+=tt.getNom()+"\n";
				text+=tt.getLieuDepart().getNom()+" | "+tt.getLieuArrivee().getNom()+"\n";
				text+="----------------------\n";
				}
				textAreaTrajet.setText(text);
				scrollPane3.setVisible(true);
			}
		});

		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String choix = (String) arg0.getItem();
				if (var) {
					if (choix != "") {
						Parking p = bdP.parkingProche(bdL.getListeLieux().get(
								choix));
						String situation = null;
						if (!p.getFerme())
							situation = "Ouvert";
						else
							situation = "Fermé";
						textAreaCoord.setText("Nom du Parking: " + p.getNom()
								+ "\n" + "Nombre places libres: "
								+ p.getNbPlacesLibres() + "\n"
								+ "Nombre places max: " + p.getNbPlacesMax()
								+ "\n" + "Zone: " + p.getZone() + "\n"
								+ "Situation: " + situation);
						scrollPane1.setVisible(true);
					}
				} else {
					String temp = "^*" + (String) comboBox1.getSelectedItem()
							+ "*$";
					ArrayList<Lieu> arrayListTemp = new ArrayList<Lieu>();
					arrayListTemp = bdL.find(temp);
					textAreaCoord.setText("Information du lieu:\n"
							+ arrayListTemp.toString());
					scrollPane1.setVisible(true);
				}
			}
		});

		comboBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String choix = (String) arg0.getItem();
				if (var) {
					if (choix != "") {
						Parking p = bdP.parkingProche(bdL.getListeLieux().get(
								choix));
						String situation = null;
						if (!p.getFerme())
							situation = "Ouvert";
						else
							situation = "Fermé";
						textAreaDesti.setText("Nom du Parking: " + p.getNom()
								+ "\n" + "Nombre places libres: "
								+ p.getNbPlacesLibres() + "\n"
								+ "Nombre places max: " + p.getNbPlacesMax()
								+ "\n" + "Zone: " + p.getZone() + "\n"
								+ "Situation: " + situation);
						scrollPane2.setVisible(true);
					}
				} else {
					String temp = "^*" + (String) comboBox2.getSelectedItem()
							+ "*$";
					ArrayList<Lieu> arrayListTemp = new ArrayList<Lieu>();
					arrayListTemp = bdL.find(temp);
					textAreaDesti.setText("Information du lieu:\n"
							+ arrayListTemp.toString());
					scrollPane2.setVisible(true);
				}
			}
		});

		// checkbox du GPS qui desactive le premier textField
		checkBoxGPS.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textField1.setEnabled(false);
					coordGPSString = "Vos coordonnées :" + coordGPS.toString();
					textAreaCoord.setText(coordGPSString);
					comboBox1.setEnabled(false);
					scrollPane1.setVisible(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textField1.setEnabled(true);
					coordGPSString = null;
					textAreaCoord.setText(coordGPSString);
					textField1.setText("");
					comboBox1.setEnabled(true);
					scrollPane1.setVisible(false);
				}
			}
		});

	}
}
