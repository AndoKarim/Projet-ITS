/* Nom de classe : Troncon                          */
/* Description   : Enregistre un Troncon            */
/* Version       : 1.0                              */
/* Date          : 15/10/2014                       */
/* Auteurs       : Ghira,Perrel,Zaabara             */

package parcours;


import java.util.ArrayList;

import COMMON.Lieu;
import grapheX.*;

public class Troncon extends Arc {
	private String nom;
	private String nature;
	private double distance;
	private int nbVoies;
	private double vitesseMax;
	private ArrayList<Lieu> troncon;
	private ArrayList<Message> listeMessage;

	public Troncon(String nom, String nature, double distance, int nbVoies, double vitesseMax, Lieu depart, Lieu arrivee) {
		super(depart, arrivee, distance);
		this.nom = nom;
		this.nature = nature;
		this.distance = distance;
		this.nbVoies = nbVoies;
		this.vitesseMax = vitesseMax;
		this.troncon = new ArrayList<Lieu>();
		troncon.add(depart);
		troncon.add(arrivee);
		this.listeMessage = new ArrayList<Message>();
		GestionnaireTroncon.addTroncon(this);
	}

	public void addLieu(Lieu l) {
		this.troncon.add(l);
	}

	public String getNom() {
		return this.nom;
	}

	public String getNature() {
		return this.nature;
	}

	public double getDistance() {
		return this.distance;
	}

	public int getNbVoies() {
		return this.nbVoies;
	}

	public double getVitesseMax() {
		return this.vitesseMax;
	}

	public Lieu getLieuDepart() {
		Lieu LDepart = troncon.get(0);
		return LDepart;
	}

	public Lieu getLieuArrivee() {
		Lieu LArrivee = troncon.get(1);
		return LArrivee;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setNbVoies(int nbVoies) {
		this.nbVoies = nbVoies;
	}

	public void setVitesseMax(double vitesseMax) {
		this.vitesseMax = vitesseMax;
	}

	public String toString() {
		return "{\"nom\" : \"" + this.nom + "\", \"nature\" : " + this.nature
				+ ", \"distance\" : " + this.distance + "\", \"nbVoies\" : "
				+ this.nbVoies + "\", \"vitesseMax\" : " + this.vitesseMax
				+ "}";
	}

	public void addMessage(Message m) {
		listeMessage.add(m);
	}

	public ArrayList<Message> listMessage() {
		return listeMessage;
	}
}
