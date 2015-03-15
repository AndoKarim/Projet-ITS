/*
 * Nom : Lieu.java
 * Auteurs : 
 * - ANDOLERZAK Abdelkarim
 * - HUBER Romain
 */

package COMMON;

import parcours.GestionnaireLieu;
import grapheX.Sommet;

/**
 * @author Vincent
 *
 */
public class Lieu  extends Sommet{
	public static int id = 0;
	private String nom;
	private double latitude;
	private double longitude;

	/**
	 * @param latitude
	 * @param longitude
	 * @throws Exception
	 */
	public Lieu(double latitude, double longitude) throws Exception {
		super("");
		if (latitude < 0)
			throw new Exception("Erreur : latitude négative");
		if (longitude < 0)
			throw new Exception("Erreur : longitude négative");
		this.nom = "Lieu_" + id;
		this.latitude = latitude;
		this.longitude = longitude;
		GestionnaireLieu.addLieu(this);
	}

	/**
	 * @param nom
	 * @param latitude
	 * @param longitude
	 * @throws Exception
	 */
	public Lieu(String nom, double latitude, double longitude) throws Exception {
		super(nom);
		if (latitude < 0)
			throw new Exception("Erreur : latitude négative");
		if (longitude < 0)
			throw new Exception("Erreur : longitude négative");
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
		GestionnaireLieu.addLieu(this);
	}

	/**
	 * @return
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return
	 */
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @param lieu
	 * @return
	 */
	public double distanceLieuDegre(Lieu lieu) {
		return Math.sqrt(Math.pow(this.latitude - lieu.latitude, 2)
				+ Math.pow(this.longitude - lieu.longitude, 2));
	}

	/**
	 * @param lieu
	 * @return
	 */
	public double distanceLieuMetre(Lieu lieu) {
		return this.distanceLieuDegre(lieu) * 111000;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "\n{\nNom: " + this.nom + ",\n" + "Latitude: "
				+ this.latitude + ",\n" + "Longitude: "
				+ this.longitude + "\n}\n";
	}
}
