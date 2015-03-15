/*
 * Nom : BD_Parking.java
 * Auteur : 
 * - PERREL Alain
 * - BOUCHER Vincent
 * - HUBER Romain
 */

package BDD;

import COMMON.Lieu;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * @author Vincent
 *
 */
public class BD_Lieux {
	private TreeMap<String, Lieu> listeLieux;

	/**
	 * @throws Exception
	 */
	public BD_Lieux() throws Exception {
		listeLieux = new TreeMap<String, Lieu>();
		this.add(new Lieu("Place Masséna", 43.697491, 7.270643));
		this.add(new Lieu("Jean Medecin", 43.6997406, 7.2666345));
		this.add(new Lieu("Allianz Riviera", 43.7050130, 7.1926509));
		this.add(new Lieu("Stade du Ray", 43.7234545, 7.2584719));
		this.add(new Lieu("Gare de Nice Ville", 43.7046049, 7.2619276));
		this.add(new Lieu("Gare Saint Augustin", 43.6709225, 7.2162285));
		this.add(new Lieu("Le Negresco", 43.6935044, 7.2574748));
		this.add(new Lieu("Hôtel de Ville", 43.7003225, 7.2734818));
		this.add(new Lieu("Opéra de Nice", 43.6956834, 7.2705316));
		this.add(new Lieu("Le Port", 43.6985363, 7.2855189));
		this.add(new Lieu("Aéroport", 43.6595360, 7.2061525));
		this.add(new Lieu("Acropolis", 43.7049912, 7.2826184));
		this.add(new Lieu("Université de Nice Sophia Antipolis", 43.7168053,
				7.2676750));
		this.add(new Lieu("Nice Etoile", 43.7015414, 7.2681457));
		this.add(new Lieu("Musée National Marc Chagall", 43.7091369, 7.2694034));
		this.add(new Lieu("Cours Saleya", 43.6957695703, 7.274073));
		this.add(new Lieu("12 Avenue Thiers", 43.703848, 7.260576));
		this.add(new Lieu("Quai des Deux Emmanuel", 43.693864, 7.287147));
		this.add(new Lieu("Place du XVe Corps", 43.707971, 7.281629));
		this.add(new Lieu("11 Avenue Aubert", 43.700510, 7.261619));
		this.add(new Lieu("Rue Alfred Binet", 43.709691, 7.261678));
		this.add(new Lieu("2 Boulevard Henri Sappia", 43.730433, 7.253500));
		this.add(new Lieu("Rue Sulzer", 43.695570, 7.270852));
		this.add(new Lieu("15 Boulevard Général Delfino", 43.705360, 7.284900));
		this.add(new Lieu("29 Promenade des Anglais", 43.695199, 7.260115));
		this.add(new Lieu("9 Auguste Gal", 43.702254, 7.284666));
		this.add(new Lieu("4 Rue Spitalieri", 43.701868, 7.268786));
		this.add(new Lieu("28 Avenue Notre-Dame", 43.703844, 7.267536));
		this.add(new Lieu("24 Avenue des Diables Bleus", 43.708895, 7.289188));

	}

	// --- retourne le dictionnaire des lieux
	/**
	 * @return
	 */
	public TreeMap<String, Lieu> getListeLieux() {
		return listeLieux;
	}

	/**
	 * @param lieu
	 */
	public void add(Lieu lieu) {
		listeLieux.put(lieu.getNom(), lieu);
	}

	/**
	 * @param regex
	 * @return
	 */
	public ArrayList<Lieu> find(String regex) {
		Pattern p;
		ArrayList<Lieu> temp = new ArrayList<Lieu>();
		for (String i : listeLieux.keySet()) {
			p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			if (p.matcher(i).find()) {
				temp.add(listeLieux.get(i));
			}
		}
		return temp;
	}

	public Lieu lieuProche(Lieu lieu) {
		double distance = Double.POSITIVE_INFINITY;
		Lieu lieuPProche = null;
		for (Lieu i : this.listeLieux.values()) {
			if (i.distanceLieuMetre(lieu) < distance) {
				distance = i.distanceLieuMetre(lieu);
				lieuPProche = i;
			}
		}
		return lieuPProche;
	}

	public Lieu lieuProche(double lat, double lon) throws Exception {
		return this.lieuProche(new Lieu(lat, lon));
	}

	public String toString() {
		return listeLieux.toString();
	}
}