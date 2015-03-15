/*
 * Nom : GestionnaireDeParking.java
 * Auteur : 
 * - BOUCHER-THOUVENY Vincent
 */

package PARKING;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import BDD.BD_Lieux;
import BDD.BD_Parking;

/**
 * @author Vincent
 *
 */
public class Gestionnaire {
	public BD_Lieux bd_lieux;
	public BD_Parking bd_parking;
	public TreeMap<String, Parking> listeParking;
	private File logOccupation;
	private String logName = "./log.txt";

	/**
	 * @throws Exception
	 */
	public Gestionnaire() throws Exception {
		this.bd_lieux = new BD_Lieux();
		this.bd_parking = new BD_Parking();
		this.listeParking = bd_parking.getListeParking();
		this.makeLogOccupation();
		this.writeLogOccupation();
	}

	/**
	 * @param nom
	 * @return
	 */
	public Parking get(String nom) {
		if (!listeParking.containsKey(nom))
			return null;
		for (String i : listeParking.keySet())
			if (i.equals(nom))
				return listeParking.get(i);
		return null;
	}

	/**
	 * @return
	 */
	public ArrayList<Parking> getListeParking() {
		ArrayList<Parking> temp = new ArrayList<Parking>();
		for (Parking i : listeParking.values())
			temp.add(i);
		return temp;
	}

	/**
	 * @param zone
	 * @return
	 */
	public ArrayList<Parking> listeParkingVide(int zone) {
		ArrayList<Parking> listeParkingVide = new ArrayList<Parking>();
		for (int i = 0; i < listeParking.size(); i++) {
			if (listeParking.get(i).isVide()
					&& listeParking.get(i).getZone() == zone)
				listeParkingVide.add(listeParking.get(i));
		}
		return listeParkingVide;
	}

	/**
	 * @param zone
	 * @return
	 */
	public ArrayList<Parking> listeParkingPlein(int zone) {
		ArrayList<Parking> listeParkingPlein = new ArrayList<Parking>();
		for (int i = 0; i < listeParking.size(); i++) {
			if (listeParking.get(i).isPlein()
					&& listeParking.get(i).getZone() == zone)
				listeParkingPlein.add(listeParking.get(i));
		}
		return listeParkingPlein;
	}

	/**
	 * @return
	 */
	public boolean makeLogOccupation() {
		try {

			logOccupation = new File(logName);

			if (logOccupation.createNewFile()) {
				// System.out.println("Fichier log cree.");
				return true;
			} else {
				// System.out.println("Fichier log existant, recuperation des donnees.");
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 */
	public void writeLogOccupation() {
		String chaine;
		PrintWriter out;
		SimpleDateFormat formatter = new SimpleDateFormat(
				"MMM dd, yyyy HH:mm:ss");
		for (Parking i : this.getListeParking()) {
			chaine = formatter.format(new Date()) + "|"
					+ i.getAdresse().getNom() + "|" + i.getNbPlacesLibres()
					+ "|" + i.getNbPlacesMax();

			try {
				out = new PrintWriter(new FileWriter(logName, true));
				out.println(chaine);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param chaine
	 */
	public void writeLogOccupation(String chaine) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(logName, true));
			out.print(chaine);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param heure
	 * @param unNom
	 * @return
	 */
	public double fetchOccupation(int heure, String unNom) {
		boolean debug = true;
		int nbLignes = 0;
		double temp1 = 0.f;
		double temp2 = 0.f;
		SimpleDateFormat formatter = new SimpleDateFormat(
				"MMM dd, yyyy HH:mm:ss");
		try {
			String sCurrentLine;

			BufferedReader br = new BufferedReader(new FileReader(logName));

			while ((sCurrentLine = br.readLine()) != null) {
				try {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(formatter.parse(sCurrentLine.substring(0,
							sCurrentLine.indexOf("|"))));
					int heure_lue = calendar.get(Calendar.HOUR_OF_DAY);
					if (debug)
						System.out
								.println(sCurrentLine.substring(0,
										sCurrentLine.indexOf("|"))
										+ "-"
										+ sCurrentLine
												.substring(
														sCurrentLine
																.indexOf("|") + 1)
												.substring(
														0,
														sCurrentLine
																.substring(
																		sCurrentLine
																				.indexOf("|") + 1)
																.indexOf("|")));
					if (heure_lue == heure
							&& sCurrentLine
									.substring(sCurrentLine.indexOf("|") + 1)
									.substring(
											0,
											sCurrentLine
													.substring(
															sCurrentLine
																	.indexOf("|") + 1)
													.indexOf("|"))
									.equals(unNom)) {
						nbLignes++;
						String chaine = sCurrentLine;
						if (debug)
							System.out.println(chaine);
						chaine = chaine.substring(chaine.indexOf("|") + 1);
						if (debug)
							System.out.println(chaine);
						chaine = chaine.substring(chaine.indexOf("|") + 1);
						if (debug)
							System.out.println(chaine);
						temp1 += (Integer.parseInt(chaine.substring(chaine
								.indexOf("|") + 1)) - Integer.parseInt(chaine
								.substring(0, chaine.indexOf("|"))));
						temp2 += Integer.parseInt(chaine.substring(chaine
								.indexOf("|") + 1));
						if (debug)
							System.out.println(temp1);
						if (debug)
							System.out.println(temp2);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (debug)
			System.out.println(nbLignes);

		return temp1 / temp2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String chaine = "";
		for (Parking i : this.listeParking.values()) {
			chaine += i + "\n";
		}
		return chaine;
	}
}