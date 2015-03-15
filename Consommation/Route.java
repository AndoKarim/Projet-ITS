/**
 *@author DIB Nasreddine & POIRIER Patrick 
 * */
package Consommation;
import java.util.ArrayList;

import parcours.Troncon;

public class Route {
	private String nom;
	private ArrayList<Troncon> Troncons = new ArrayList<Troncon>();
	//-----------------------------------------
	public Route(){
		nom = null;
		Troncons = null;
	}
	//-----------------------------------------
	public Route(String unNom, ArrayList<Troncon> desTroncons){
		nom = unNom;
		Troncons = desTroncons;
	}
	//-----------------------------------------
	public ArrayList<Troncon> getTroncons() {
		return Troncons;
	}
	//-----------------------------------------
	public void setTroncons(ArrayList<Troncon> troncons) {
		Troncons = troncons;
	}
	//-----------------------------------------
	public void getDepart() {
		throw new UnsupportedOperationException();
	}
	//-----------------------------------------
	public void getArrivee() {
		throw new UnsupportedOperationException();
	}
	//-----------------------------------------
	public Object getNom() {
		return nom;
	}
}