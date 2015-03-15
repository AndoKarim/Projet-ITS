/**
	 * Classe Vehicule
	 * @author DIB Nasreddine, POIRIER Patrick, CHARENTUS Rémi & ZABOROWSKI William
	 * 
	 */
package Consommation;

import COMMON.Lieu;
import Bouchons.Fichier;

public class Vehicule {

	private double reservoir;
	private double energieDisponible;
	private double ConsoRoute;
	private double ConsoUrbain;
	private double ConsoMixte;
	private boolean estDeemaree;
	public double kilometrage;
	public String modele;

	//TODO
	public double[] lesConso(String modele){
		
		return null;
		
	}
	//---------------------------------------
	public Vehicule(int unReservoir, double uneEnergieDispo, double uneConsoRoute, double uneConsoUrbaine, double uneConsoMixte, double unKilometrage){
		energieDisponible = uneEnergieDispo;
		reservoir = unReservoir;
		ConsoMixte = uneConsoMixte;
		ConsoRoute = uneConsoRoute;
		ConsoUrbain = uneConsoUrbaine;
		estDeemaree = false;
		kilometrage = unKilometrage;
	}
	//TODO modifier
	//---------------------------------------
	public boolean isEstDemaree() {
		return estDeemaree;
	}
	//---------------------------------------
	public void setEstDeemaree(boolean estDeemaree) {
		this.estDeemaree = estDeemaree;
	}
	//---------------------------------------
	public double getEnergieDisponible(){
		return energieDisponible;
	}
	//---------------------------------------
	public double getReservoir() {
		return reservoir;
	}
	//---------------------------------------
	public void setReservoir(int unReservoir) {
		reservoir = unReservoir;
	}
	//---------------------------------------
	public double getConsoRoute(){
		return ConsoRoute;
	}
	//---------------------------------------
	public double getConsoMixte(){
		return ConsoMixte;
	}
	//---------------------------------------
	public double getConsoUrbain(){
		return ConsoUrbain;
	}
	//---------------------------------------
	public double getKilometrage() {
		return kilometrage;
	}
	//---------------------------------------
	/**
	 * Permet de savoir si une voiture est demarrée ou non 
	 * @author CHARENTUS Rémi & ZABOROWSKI William
	 */
	//Stats de du vehicule au départ
	public void demarrer(){
		estDeemaree = true;
	}
	public void arreterVoiture() {
		if (estDeemaree == true)
			estDeemaree = false;
	}
	public void setEnergieDisponible(double uneEnergieDisponible) {
		energieDisponible = uneEnergieDisponible;
	}
	//---------------------------------------
	/**
	 * Commencer l'enregistrement
	 * 
	 * @author Dib Nasreddine
	 * @throws Throwable si la voiture n'est pas demarer on ne peut enregistrer
	 */
	public void EnregistrerTrajet(String FichierLog)throws Throwable{
		if (estDeemaree == false)
			throw new Throwable("La voiture n'est pas demaree");
		Lieu unLieuActuel = getLocation();
		String content = unLieuActuel.getNom()+","+getEnergieDispo()+","+getKilometrageActuel()+"|";
		Fichier.ecrireFichier(content, FichierLog, true);
	}
	//---------------------------------------
	/**
	 * Arreter l'enregistrement
	 * 
	 * @author Dib Nasreddine
	 * @throws Throwable
	 */
	public void arreterEnregistrement(String FichierLog)throws Throwable {
		String leLog = Fichier.lireFichier("Log.txt");
		//On verifie qu'un enregistrement est bien en cours
		if (!leLog.endsWith("|\n"))
			throw new Throwable("Aucun enregistrement a arreter");
		//On arrete l'enregistrement en enregistrant le Lieu actuel, l'energie dispo et le kilometrage actuel
		EnregistrerTrajet(FichierLog);
		//On enregistre la consommation dans le log
		Fichier.ecrireFichier(";\n", "Log.txt", true);
	}
	//---------------------------------------
	/**
	 * Retourne le Lieu ou se trouve le vehicule
	 * Ceci est un bouchon qui lit dans un fichier et cree un lieu car nous ne disponsons 
	 * pas de capteurs gps
	 * 
	 * @author Dib Nasreddine
	 * @return actual Location of the vehicule
	 * @throws Throwable 
	 */
	public Lieu getLocation() throws Throwable{
		String unFichier = Fichier.lireFichier("ValsCapteurs");
		String leContenu[] = unFichier.split("\n");
		String Location = leContenu[0];
		String lesVals[] = Location.split(",");

		long x = Long.parseLong(lesVals[1]);
		long y = Long.parseLong(lesVals[2]);
		return new Lieu(lesVals[0], x, y);
	}
	//---------------------------------------
	/**
	 * Retourne la quantite d'energie dispo dans le reservoir du vehicule
	 * Ceci est un bouchon qui lit dans un fichier une valeur representant la quantite d energie dans 
	 * le reservoir
	 * 
	 * @author Dib Nasreddine
	 * @return actual Location of the vehicule
	 * @throws Throwable 
	 */
	public double getEnergieDispo() throws Throwable{
		String unFichier = Fichier.lireFichier("ValsCapteurs");
		String leContenu[] = unFichier.split("\n");
		return Double.parseDouble(leContenu[1]);
	}
	//---------------------------------------
	/**
	 * Retourne le Kilometrage actuel du vehicule
	 * Ceci est un bouchon qui lit dans un fichier le kilometrage actuel du vehicule
	 * 
	 * @author Dib Nasreddine
	 * @return actual Location of the vehicule
	 * @throws Throwable 
	 */
	private double getKilometrageActuel() throws Throwable {
		String unFichier = Fichier.lireFichier("ValsCapteurs");
		String leContenu[] = unFichier.split("\n");
		return Double.parseDouble(leContenu[2]);
	}
	//---------------------------------------

}
