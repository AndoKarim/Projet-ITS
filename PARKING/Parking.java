/*
 * Nom : Parking.java
 * Auteurs :
 * - ANDOLERZAK Abdelkarim
 * - FISCHMANN Mark
 */
/**
 * @author Vincent
 *
 */

package PARKING;

import COMMON.Lieu;

public class Parking {

	private String nom;
	private Lieu adresse;
	private boolean ferme;
	private int nbPlacesLibres;
	private int nbPlacesMax;
	private int zone;

	/**
	 * @param nom
	 * @param adresse
	 * @param nbPlacesMax
	 * @param zone
	 */
	public Parking(String nom, Lieu adresse, int nbPlacesMax, int zone) {
		this.nom = nom;
		this.adresse = adresse;
		this.nbPlacesMax = nbPlacesMax;
		this.nbPlacesLibres = nbPlacesMax;
		this.ferme = false;
		this.zone = zone;
	}

	/**
	 * @param nom
	 * @param adresse
	 * @param ferme
	 * @param nbPlacesLibres
	 * @param nbPlacesMax
	 * @param zone
	 */
	public Parking(String nom, Lieu adresse, boolean ferme, int nbPlacesLibres,
			int nbPlacesMax, int zone) {
		this.nom = nom;
		this.adresse = adresse;
		this.ferme = ferme;
		this.nbPlacesLibres = nbPlacesLibres;
		this.nbPlacesMax = nbPlacesMax;
		this.zone = zone;
	}

	/**
	 * @return
	 */
	public boolean getFerme() {
		return this.ferme;
	}

	/**
	 * 
	 * @param ferme
	 */
	public void setFerme(boolean ferme) {
		this.ferme = ferme;
	}

	/**
	 * @return
	 */
	public int getNbPlacesLibres() {
		return this.nbPlacesLibres;
	}

	/**
	 * 
	 * @param nbPlacesLibres
	 */
	public void setNbPlacesLibres(int nbPlacesLibres) {
		this.nbPlacesLibres = nbPlacesLibres;
	}

	/**
	 * @return
	 */
	public int getNbPlacesMax() {
		return this.nbPlacesMax;
	}

	/**
	 * 
	 * @param nbPlacesMax
	 */
	public void setNbPlacesMax(int nbPlacesMax) {
		this.nbPlacesMax = nbPlacesMax;
	}

	/**
	 * @return
	 */
	public String getNom() {
		return nom;
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
	public Lieu getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse
	 */
	public void setAdresse(Lieu adresse) {
		this.adresse = adresse;
	}

	/**
	 * 
	 */
	public void setPlein() {
		this.nbPlacesLibres = 0;
	}

	/**
	 * 
	 */
	public void setVide() {
		this.nbPlacesLibres = this.nbPlacesMax;
	}

	/**
	 * 
	 */
	public void incPlacesLibres() {
		if (this.nbPlacesLibres < this.nbPlacesMax)
			this.nbPlacesLibres++;
	}

	/**
	 * 
	 */
	public void decPlacesLibres() {
		if (this.nbPlacesLibres > 0)
			this.nbPlacesLibres--;
	}

	/**
	 * @return
	 */
	public boolean isPlein() {
		return (this.nbPlacesLibres == 0);
	}

	public boolean isVide() {
		double nbPL = (double) this.nbPlacesLibres;
		double nbPM = (double) this.nbPlacesMax;
		return nbPL / nbPM >= 0.9f;
	}

	/**
	 * @return
	 */
	public int getZone() {
		return this.zone;
	}

	/**
	 * 
	 * @param zone
	 */
	public void setZone(int zone) {
		this.zone = zone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "\n{\nNom Parking : " + this.getNom() + ","
				+ "Position géographique : " + this.adresse.toString() + ","
				+ "Cloture du parking : " + this.ferme + ",\n"
				+ "Nombre de places libres : " + this.nbPlacesLibres + ",\n"
				+ "Nombre de place maximum : " + this.nbPlacesMax + ",\n"
				+ "Zone géographique : " + this.zone + "\n}\n";
	}
}