package IHM;

import java.awt.EventQueue;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.awt.Font;
import java.awt.Color;

public class main_window extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_window frame = new main_window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main_window() {
		setTitle("Intelligent Transport System - Menu Principal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnChercherParking = new JButton("Chercher Parking");
		btnChercherParking.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnChercherParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chercher_parking b1;
				try {
					b1 = new chercher_parking();
					dispose();
					b1.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnChercherParking.setBounds(391, 84, 208, 36);
		contentPane.add(btnChercherParking);

		JButton btnNewButton = new JButton("Rechercher Itineraire");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rechercher_itineraire b2;
				try {
					b2 = new rechercher_itineraire();
					dispose();
					b2.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(391, 131, 208, 36);
		contentPane.add(btnNewButton);

		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				about_window b3 = new about_window();
				dispose();
				b3.setVisible(true);
			}
		});
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAbout.setBounds(391, 178, 208, 36);
		contentPane.add(btnAbout);

		JButton btnNewButton_1 = new JButton("Quitter");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(453, 437, 89, 25);
		contentPane.add(btnNewButton_1);

		JLabel backgroundImg = new JLabel("");
		Image img = new ImageIcon(this.getClass()
				.getResource("../images/background_main.jpg")).getImage();
		backgroundImg.setIcon(new ImageIcon(img));
		backgroundImg.setBounds(0, 0, 246, 521);
		contentPane.add(backgroundImg);
		
		JLabel lblNewLabel = new JLabel("");
		Image img2 = new ImageIcon(this.getClass()
				.getResource("../images/background_main3.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img2));
		lblNewLabel.setBounds(748, 0, 246, 521);
		contentPane.add(lblNewLabel);

	}
}
