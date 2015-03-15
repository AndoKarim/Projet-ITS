package IHM;

//Auteur Thomas VALVERDE

import java.util.HashMap;
import java.util.Scanner;

public class Scenario {

	public static void main(String[] args) throws Throwable {
		//creation des variables
		Scanner depart = new Scanner(System.in);
		Scanner arrivee = new Scanner(System.in);
		Scanner choixParcours = new Scanner(System.in);
		Scanner choixParking = new Scanner(System.in);
		Scanner trajet = new Scanner(System.in);
		System.out.println("Bonjour Monsieur ROGER!!!! \n");
		Thread.sleep(2000);
		System.out.println("Taper 1 pour recuperer vos coordonnées GPS:");
		Thread.sleep(1000);
		System.out.println("Taper 2 pour entrer votre point de départ et d'arrivée:\n");
		Thread.sleep(1000);
		System.out.println("Il est temps de choisir !");

		int lechoix = choixParcours.nextInt();
		while(lechoix!=1 && lechoix!=2){
			System.out.println("Votre choix est invalide veuillez en choisir un disponible :");
			System.out.println("Taper 1 pour recuperer vos coordonnées GPS:");
			Thread.sleep(1000);
			System.out.println("Taper 2 pour entrer votre point de départ et d'arrivée:\n");
			Thread.sleep(1000);
			System.out.println("Il est temps de choisir !");
			lechoix = choixParcours.nextInt();
		}
		if (lechoix == 1) {
			System.out.println("Voici vos coordonnées.");
			// this.getLatitude();  Lieu
			// this.getLongitude(); Lieu
		}
		if (lechoix == 2) {
			System.out.println("Taper votre point de Depart,puis votre point d'Arrivee:");
			String de = depart.nextLine();
			//rechercheInst(String unMot, HashMap<Integer, Lieu> listeLieu ) Dictionnaire
			String ae = arrivee.nextLine();
			//rechercheInst(String unMot, HashMap<Integer, Lieu> listeLieu ) Dictionnaire
			System.out.println("Vous avez saisi comme ville de depart : "+ de.toString());
			//toString(); Dictionnaire
			System.out.println("Vous avez saisi comme ville d'arrivee: "+ ae.toString());
			//toString(); Dictionnaire
		}
		if(lechoix!=1 || lechoix!=2)
		Thread.sleep(3000);
		System.out.println("Desirez vous un parking à l'arrivée?");
		Thread.sleep(1000);
		System.out.println("Taper 1 pour un parking a l'arrivée:");
		Thread.sleep(1000);
		System.out.println("Taper 2 si vous n'en voulez pas:");
		int leparking = choixParcours.nextInt();
		/*
		 * while(leparking != 1 || leparking !=2) {
		 * System.out.println("Taper 1 pour un parking a l'arrivée");
		 * Thread.sleep(1000); System.out .println(
		 * "Taper 2 si vous penser être un génie pour trouver les places alone"
		 * ); }
		 */
		if (leparking == 1) {
			System.out.println("une place de parking vous attend.");
			//getNom();
			//getAdresse();

		}
		if (leparking == 2) {
			System.out.println("Bonne chance pour trouver une place.");
		}
		Thread.sleep(2000);
		System.out.println("Souhaitez vous le trajet le plus rapide ou le moins cher?");
		Thread.sleep(1000);
		System.out.println("Taper 1 pour le trajet le plus rapide:");
		Thread.sleep(1000);
		System.out.println("Taper 2 pour le trajet le moins cher:");
		int lechoix3 = trajet.nextInt();
		if (lechoix3 == 1) {
			System.out.println("Vous avez choisi le trajet le plus rapide.");
			//trajet en fonction du temps
		}
		if (lechoix3 == 2) {
			System.out.println("Vous avez choisi le trajet le moins cher.");
			//trajetMoinsCher(lieu depart, lieu arrivee); Consommation
		}
	}
}
