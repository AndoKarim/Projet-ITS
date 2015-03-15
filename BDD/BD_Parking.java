/*
 * Nom : BD_Parking.java
 * Auteur : 
 * - BOUCHER-THOUVENY Vincent
 * - HUBER Romain 
 */

package BDD;

import COMMON.Lieu;
import PARKING.Parking;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * @author Vincent, Romain
 *
 */
public class BD_Parking {

	private TreeMap<String, Parking> listeParking;

	/**
	 * @throws Exception
	 *             Constructeur par défaut de la base de données qui marche avec
	 *             la DB des lieux
	 */
	public BD_Parking() throws Exception {

		this.listeParking = new TreeMap<String, Parking>();
		TreeMap<String, Lieu> listeLieux = new BD_Lieux().getListeLieux();

		this.add(new Parking("Saleya", listeLieux.get("Cours Saleya"), 422, 11));
		this.add(new Parking("Masséna", listeLieux.get("Place Masséna"), 325, 4));
		this.add(new Parking("Gare Thiers", listeLieux.get("12 Avenue Thiers"),
				618, 3));
		this.add(new Parking("Port de Nice - Deux Emmanuel", listeLieux
				.get("Quai des Deux Emmanuel"), 450, 13));
		this.add(new Parking("Acropolis - Jean Bouin", listeLieux
				.get("Place du XVe Corps"), 1928, 12));
		this.add(new Parking("Mozart", listeLieux.get("11 Avenue Aubert"), 468,
				5));
		this.add(new Parking("Gare du Sud", listeLieux.get("Rue Alfred Binet"),
				442, 9));
		this.add(new Parking("Las Planas", listeLieux
				.get("2 Boulevard Henri Sappia"), 765, 10));
		this.add(new Parking("Sulzer", listeLieux.get("Rue Sulzer"), 476, 11));
		this.add(new Parking("TNL (Centre commercial)", listeLieux
				.get("15 Boulevard Général Delfino"), 1400, 12));
		this.add(new Parking("Palais Masséna", listeLieux
				.get("29 Promenade des Anglais"), 362, 6));
		this.add(new Parking("Vinci Park Services", listeLieux
				.get("9 Auguste Gal"), 301, 13));
		this.add(new Parking("Nice Etoile", listeLieux.get("4 Rue Spitalieri"),
				1200, 3));
		this.add(new Parking("Notre-Dame", listeLieux
				.get("28 Avenue Notre-Dame"), 193, 3));
		this.add(new Parking("Saint Jean d'Angély", listeLieux
				.get("24 Avenue des Diables Bleus"), 524, 13));

	}

	/**
	 * @param parking
	 */
	public void add(Parking parking) {
		this.listeParking.put("Parking " + parking.getNom(), parking);
	}

	/**
	 * @return
	 */
	public TreeMap<String, Parking> getListeParking() {
		return listeParking;
	}

	public ArrayList<Parking> find(String regex) {
		Pattern p;
		ArrayList<Parking> temp = new ArrayList<Parking>();
		for (String i : listeParking.keySet()) {
			p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			if (p.matcher(i).find()) {
				temp.add(listeParking.get(i));
			}
		}
		return temp;
	}

	/**
	 * @param lieu
	 * @return
	 */
	public Parking parkingProche(Lieu lieu) {
		double distance = Double.POSITIVE_INFINITY;
		Parking parkPProche = null;
		for (Parking p : this.listeParking.values()) {
			if (!p.isPlein()
					&& p.getAdresse().distanceLieuMetre(lieu) < distance) {
				distance = p.getAdresse().distanceLieuMetre(lieu);
				parkPProche = p;
			}
		}
		return parkPProche;
	}

	public Parking parkingProche(double lat, double lon) throws Exception {
		return this.parkingProche(new Lieu(lat, lon));
	}
}