/**
/ * @author DIB Nasreddine, CHARENTUS Rémi & ZABOROWSKI William
 * 
 * */
package Consommation;
import java.util.ArrayList;
import java.util.Collection;

import COMMON.Lieu;
import parcours.TousChemins;
import parcours.Troncon;
import Bouchons.Fichier;

public abstract class CalculEnergie{
	//---------------------------------------
	public static double consoTrajet(Vehicule v, double energiedepart){
		//on prend l'energie disponible à l'arrivé
		double energieF=0;
		try {
			energieF = v.getEnergieDispo();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//on en deduis ainsi la consommation du trajet 
		double conso= energiedepart - energieF;
		return conso;		
	}
	//---------------------------------------
	/**
	 * @author CHARENTUS Rémi & ZABOROWSKI William
	 * @param v le Vehicule
	 * */
	public static double quantiteEnergie(Vehicule v){
		double energieDisponible = 0;
		try {
			energieDisponible = v.getEnergieDispo();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return energieDisponible;
	}
	//---------------------------------------
	/**
	 * @author Dib Nasreddine
	 * @param unTroncon
	 * @param V
	 * @return une consommation par un vehicule sur un troncon
	 */
	//Retourne la consomation sur un troncon par rapport a sa longuer et sa nature
	public static double consoTroncon(Troncon unTroncon, Vehicule V){
		String LaNature = unTroncon.getNature();
		double laDistance = unTroncon.getDistance();
		double LaConsoParNature = 0;
		switch (LaNature) {
			case "Route":
				LaConsoParNature = laDistance * V.getConsoRoute()/ 100;
			break;
			case "Urbain":
				LaConsoParNature = laDistance * V.getConsoUrbain()/ 100;
			break;
			case "Mixte":
				LaConsoParNature = laDistance * V.getConsoMixte() / 100;
			break;		
		}
			return LaConsoParNature;
	}
	//---------------------------------------
	public static double consommationRoute(Route uneRoute, Vehicule unVehicule){
		double SommeConsoTr = 0;
		double laDistance = 0;
		for(Troncon leTrancon : uneRoute.getTroncons()){
			SommeConsoTr += consoTroncon(leTrancon, unVehicule);
			laDistance += leTrancon.getDistance();
		}
		return (100*SommeConsoTr/laDistance);	
	}
	//---------------------------------------
	public static double consommationRoute(ArrayList<Troncon> Troncons, Vehicule unVehicule){
		double SommeConsoTr = 0;
		double laDistance = 0;
		for(Troncon leTrancon : Troncons){
			SommeConsoTr += consoTroncon(leTrancon, unVehicule);
			laDistance += leTrancon.getDistance();
		}
		return (100*SommeConsoTr/laDistance);	
	}
	//---------------------------------------
	public static ArrayList<Troncon> trajetMoinsCher(Lieu depart, Lieu arrivee, Vehicule unVehicule){
		TousChemins ts = new TousChemins();
		ArrayList<ArrayList<Troncon>> Routes = ts.toutesRoutes(depart, arrivee);	
		ArrayList<Troncon> routeMoinchere = new ArrayList<Troncon>();
		
		double consoMin = 10000;
		
		
		for (ArrayList<Troncon> uneRoute : Routes){
			if(consommationRoute(uneRoute, unVehicule) < consoMin){
				consoMin = consommationRoute(uneRoute, unVehicule);
				routeMoinchere = uneRoute;
			}
		}
		return routeMoinchere;
	}
	//---------------------------------------
	/**
	 * @author Dib Nasreddine
	 * 
	 * @param Routes : Collection de Routes
	 * @param V le Vehicule
	 * @return la consomation moyenne du vehicule
	 */
	public static double consommationMoyenne(Collection<Route> Routes, Vehicule V){
		double ConsoMoyenne = 0;
		//Contiendra les consommations moyennes pour chacune des routes
		ArrayList<Double> consomations = new ArrayList<Double>();
		
		//Pour chaque route on calcul la consommation moyenne
			for (Route uneRoute : Routes){
			//Distance d'une route calculée en faisan la somme des longueurs des troncons
			double laDistance = 0;
			double SommeConsoTr = 0;
	
			for(Troncon leTrancon : uneRoute.getTroncons()){
				SommeConsoTr += consoTroncon(leTrancon, V);
				laDistance += leTrancon.getDistance();
			}
			consomations.add(100*SommeConsoTr/laDistance);
			}
		for (double ConsoRoute : consomations)
			ConsoMoyenne+= ConsoRoute;
		ConsoMoyenne = ConsoMoyenne/consomations.size();
		
		return ConsoMoyenne;
	}
	//---------------------------------------
	/**
	 * Calcul la consommation moyenne a partir d une base de trajets parcourus et enregistres
	 * par un vehicule auparavant
	 * 
	 * @author Dib Nasreddine
	 * @return La consommation moyenne a partir d'un historique de trajets enregistres pour un vehicule
	 * @throws Throwable 
	 */
	public static double consommationMoyenneLog(String unFichier) throws Throwable{
		//---------------------------------------------------------
		//Recuperer le contenu du fichier dans un String
		String leLog = Fichier.lireFichier(unFichier);
		//List qui contiendra les consomation pour chaquee enregistrement
		ArrayList<Double> lesConsommations = new ArrayList<Double>();
		//---------------------------------------------------------
		//Mettre chaque ligne du fichier dans une case du tableau correspondant a un trajet enregistre
		String LesEnregistrements[] = leLog.split("\n");
		//---------------------------------------------------------
		//On parcours les Enregistrements un par un
		for (String lEnregistrement : LesEnregistrements){
			String y[] = lEnregistrement.split("\\|");
			String Debut[] = y[0].split(",");
			String Fin[] = y[1].split(",");
			//Q1 = Quantite energie au debut et Q2 = Quantite energie a la fin
			double Q1 = Double.parseDouble(Debut[1]);
			double Q2 = Double.parseDouble(Fin[1]);
			//K1 = kilometrage au debur et K2 = Kilometrage a la fin
			double K1 = Double.parseDouble(Debut[2]);
			double K2 = Double.parseDouble(Fin[2]);
			//On calcul la consommation sur le trajet
			double ConsoSurUnTrajet = (Q1-Q2)*100/(K2-K1);
			lesConsommations.add(ConsoSurUnTrajet);
		}
		//Calculer la consomation moyenne
		double laConsoMoyenneLog = 0;
		for(double uneConso : lesConsommations)
			laConsoMoyenneLog+=uneConso;
		return laConsoMoyenneLog/lesConsommations.size();
	}
	//---------------------------------------
	/**
	 * Je suis utilisateur, je veux savoir quelle est la distance maximale que je peux 
	 * parcourir dans des conditions actuelles et la visualiser.
	 * 
	 *@author Charentus Remi et Zaborowki William
	 * @return la Distance max que peut parcourir un vehicule
	 **/
	public static double distanceMaximale(Vehicule v1){
		double consoMoyen=(v1.getConsoRoute()+v1.getConsoUrbain()+v1.getConsoMixte())/3;
		return Math.round(((v1.getEnergieDisponible()/consoMoyen)*100)); 
	}
	//---------------------------------------
	
	//---------------------------------------

}
