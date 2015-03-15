package graphe;

import grapheX.Arc;
import grapheX.Graphe;
import grapheX.Sommet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Cette classe définit un parcours simple de graphes
 * contenant des arcs inverses et plusieurs arcs entre deux sommets donnés.
 * @author blay
 *
 */
public class ParcoursSimple {

	Graphe graphe;


	public ParcoursSimple(Graphe graphe) {
		super();
		this.graphe = graphe;
	}

	
	
	/**
	 * @param origine
	 * @return une liste de chemin
	 * Cette méthode renvoie les chemins les plus longs possibles à partir du point d'orgine
	 */
	public ArrayList<Chemin> chemins(Sommet origine){
		HashMap<Sommet,ArrayList<Arc>> dejaVu = new HashMap<Sommet, ArrayList<Arc>>();
		ArrayList<Chemin> chemins = new ArrayList<Chemin>();
		chemins = chemins(origine,dejaVu);
		return chemins;
	}

	private ArrayList<Chemin> chemins(Sommet origine, HashMap<Sommet,ArrayList<Arc>> dejaVu){

		ArrayList<Chemin> chemins = new ArrayList<Chemin>();

		if  (dejaVu.containsKey(origine)){
			return chemins;
		}

		dejaVu.put(origine, new ArrayList<Arc>());


		Collection<Arc<Sommet>> voisins = graphe.voisins(origine);	

		HashMap<Sommet,ArrayList<Arc>> dejavVuLocal = new HashMap<Sommet, ArrayList<Arc>>();

		for (Arc<Sommet> a : voisins) {
			Sommet destination = a.destination();
			dejavVuLocal= new HashMap<Sommet,ArrayList<Arc>>(dejaVu);

			if (nouvelleDestinationOuNouvelArcSansRetour(origine,dejavVuLocal,destination,a)) { 
				dejavVuLocal.get(origine).add(a);
				ArrayList<Chemin> cheminsLocaux = chemins(destination,dejavVuLocal);

				if (cheminsLocaux.isEmpty()) {
					Chemin chemin = new Chemin();
					chemins.add(chemin);
					chemin.add(a);
					}
				else {
					for (Chemin c : cheminsLocaux) {
						c.add(0,a);
						chemins.add(c);
					}
				}
			}
		}
		return chemins;
	}

	private boolean nouvelleDestinationOuNouvelArcSansRetour(
		Sommet origine, HashMap<Sommet, ArrayList<Arc>> dejaVu, Sommet destination,
		Arc<Sommet> a) {

		if (! dejaVu.containsKey(destination) )
			return true;

		ArrayList<Arc> x = dejaVu.get(destination);

		return ( (! dejaVu.get(destination).contains(a)) && (! dejaVu.containsKey(a.destination()) ) );
	}
}
