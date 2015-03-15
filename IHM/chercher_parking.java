package IHM;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import COMMON.Lieu;
import PARKING.Parking;
import BDD.BD_Lieux;
import BDD.BD_Parking;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class chercher_parking extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String textString;
	Lieu coordGPS;
	Parking newParking;
	ArrayList<Lieu> arraylistLieux = new ArrayList<Lieu>();
	ArrayList<Parking> arraylistParking = new ArrayList<Parking>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chercher_parking frame = new chercher_parking();
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
	public chercher_parking() throws Exception {
		setTitle("Interactive Transport System - Chercher un Parking");
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
		coordGPS = new Lieu("IUT de Nice", 43.6705476, 7.2032781);

		JButton btnNewButton = new JButton("Retour");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_window b1 = new main_window();
				dispose();
				b1.setVisible(true);
			}
		});
		btnNewButton.setBounds(649, 472, 89, 25);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Chercher Parking Proche");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(347, 42, 300, 36);
		contentPane.add(btnNewButton_1);

		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textArea.setBounds(303, 232, 396, 229);
		textArea.setVisible(false);
		contentPane.add(textArea);

		final JButton btnNewButton_2 = new JButton(
				"Entrer une adresse de destination");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setVisible(true);
				textArea.setText("");
			}
		});
		btnNewButton_2.setBounds(347, 89, 300, 36);
		contentPane.add(btnNewButton_2);

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String choix = (String) arg0.getItem();
				if (choix != "") {
					Parking p = bdP.parkingProche(bdL.getListeLieux()
							.get(choix));
					String situation = null;
					if (!p.getFerme())
						situation = "Ouvert";
					else
						situation = "Fermé";
					textArea.setText("Nom du Parking: " + p.getNom() + "\n"
							+ "Nombre places libres: " + p.getNbPlacesLibres()
							+ "\n" + "Nombre places max: " + p.getNbPlacesMax()
							+ "\n" + "Zone: " + p.getZone() + "\n"
							+ "Situation: " + situation);
					textArea.setVisible(true);
				}
			}
		});
		comboBox.setBounds(347, 175, 300, 28);
		comboBox.setVisible(false);
		contentPane.add(comboBox);

		textField = new JTextField(50);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textField.setBounds(347, 136, 300, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textString = "^*" + textField.getText() + ".*$";
				arraylistLieux = bdL.find(textString);
				/*
				 * Entrer une adresse de destination Masséna -> string Cherche
				 * liste de lieu avc Masséna Prendre coordonné du lieu retourné
				 * utiliser public parking parkingProche(double lat, double lon)
				 * returns un parking proche, proche des coordonnees de Masséna
				 */
				String[] temp = new String[arraylistLieux.size() + 1];
				for (int i = 0; i < arraylistLieux.size() + 1; i++) {
					if (i == 0)
						temp[i] = "";
					else
						temp[i] = arraylistLieux.get(i - 1).getNom();
				}

				comboBox.removeAllItems();
				ComboBoxModel<String> model = new DefaultComboBoxModel<String>(
						temp);
				comboBox.setModel(model);
				comboBox.setVisible(true);

			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea.setVisible(true);
				textField.setVisible(false);
				comboBox.setVisible(false);
				newParking = bdP.parkingProche(coordGPS);
				String situation = null;
				if (!newParking.getFerme())
					situation = "Ouvert";
				else
					situation = "Fermé";
				textArea.setText("Place proche de \"" + coordGPS.getNom()
						+ "\":\n\n" + "Nom du parking: " + newParking.getNom()
						+ "\n" + "Nombre de places libres: "
						+ newParking.getNbPlacesLibres() + "\n"
						+ "Nombre de places max: "
						+ newParking.getNbPlacesMax() + "\n" + "Zone: "
						+ newParking.getZone() + "\n" + "Situation: "
						+ situation);
			}
		});

		JLabel backgroundImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("../images/background_chercher_2.jpg"))
				.getImage();
		backgroundImg.setIcon(new ImageIcon(img));
		backgroundImg.setBounds(0, 0, 246, 521);
		contentPane.add(backgroundImg);

		JLabel lblNewLabel = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("../images/background_chercher2_2.jpg"))
				.getImage();
		lblNewLabel.setIcon(new ImageIcon(img2));
		lblNewLabel.setBounds(748, 0, 246, 521);
		contentPane.add(lblNewLabel);

	}
}
