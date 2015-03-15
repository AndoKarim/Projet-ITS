package IHM;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextArea;

public class about_window extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					about_window frame = new about_window();
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
	public about_window() {
		setTitle("About");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				main_window b1 = new main_window();
				b1.setVisible(true);
			}
		});
		btnRetour.setBounds(795, 435, 89, 25);
		contentPane.add(btnRetour);

		JTextPane textPane1 = new JTextPane();
		textPane1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPane1.setBackground(Color.WHITE);
		textPane1.setText("Auteurs codage :\n\nANDOLERZAK Karim\n\nBOUCHER-THOUVENY Vincent\n\nCHARENTUS Remi\n\nDIB Nasreddine");
		textPane1.setBounds(323, 50, 250, 375);
		textPane1.setEditable(false);
		contentPane.add(textPane1);

		JTextPane textPane2 = new JTextPane();
		textPane2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPane2.setBackground(Color.WHITE);
		textPane2.setText("FISCHMANN Mark\n\nGHIRA Anasse\n\nHUBER Romain\n\nVALVERDÉ THOMAS\n\nZABAROWSKI William");
		textPane2.setBounds(620, 50, 250, 375);
		textPane2.setEditable(false);
		contentPane.add(textPane2);

		JTextPane textPane3 = new JTextPane();
		textPane3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPane3.setBackground(Color.WHITE);
		textPane3.setText("Encadrement :\n\nBLAY-FORNARINO Mireille");
		textPane3.setBounds(50, 50, 250, 80);
		textPane3.setEditable(false);
		contentPane.add(textPane3);

		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("../images/logo_iut.gif"))
				.getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(50, 159, 206, 249);
		contentPane.add(label);

		JTextArea textAreaTitle = new JTextArea();
		textAreaTitle.setFont(new Font("Cambria", Font.ITALIC, 18));
		textAreaTitle.setText("Intelligent Transport System");
		textAreaTitle.setBounds(354, 0, 219, 23);
		textAreaTitle.setEditable(false);
		contentPane.add(textAreaTitle);

	}
}
