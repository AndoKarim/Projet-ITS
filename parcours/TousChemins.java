/********************************************************/
/* Nom de classe : Route                                */
/* Description   : Enregistre un chemin entre deux lieu */
/* Version       : 1.0                                  */ 
/* Date          : 15/10/2014                           */
/* Auteurs       : Ghira Anasse                         */
/********************************************************/

package parcours;
import java.util.*;

import COMMON.Lieu;
import grapheX.*;
import graphe.*;

public  class TousChemins {

	 Graphe reseau = new GrapheSimple();
	
	public TousChemins(){
		addGestTroncon();
		
	}
	
	public void addLieu(Lieu p){
		reseau.ajouterSommet(p);
	}
	
	public  void addTroncon(Troncon arc){
		reseau.ajouterArc(arc);
	}
	
	public void addGestTroncon(){
		ArrayList<Troncon> listTroncon = GestionnaireTroncon.listTroncon();
		for(int i=0; i<listTroncon.size();i++){
                        reseau.ajouterSommet(listTroncon.get(i).getLieuDepart());
                        reseau.ajouterSommet(listTroncon.get(i).getLieuArrivee());
			reseau.ajouterArc(listTroncon.get(i));
		}
	}
	
	public ArrayList<Chemin> reseau(Sommet origine,Sommet arrivee){
		ParcoursSimple PS1 = new ParcoursSimple(reseau);
		ArrayList<Chemin> listeChemins =  PS1.chemins(origine);
		ArrayList<Chemin> c = new ArrayList<Chemin>();
		for(int i=0; i<listeChemins.size(); i++){
			c.add(listeChemins.get(i).extraireChemin(origine, arrivee));
		}
		return c;
	}
	
	public ArrayList<ArrayList<Troncon>> toutesRoutes(Sommet origine, Sommet arrivee){
		ArrayList<ArrayList<Troncon>> retour = new ArrayList<ArrayList<Troncon>>();
		ArrayList<Chemin> reseau = reseau(origine, arrivee);
		for(Chemin c : reseau){
			ArrayList<Troncon> t = new ArrayList<Troncon>();
			ArrayList<Arc> arcs = c.getArcs();
			for(Arc a : arcs){
				t.add((Troncon)a);
			}
			retour.add(t);
		}
		return retour;
	}
	
	public ArrayList<Troncon> plusRapide(Sommet origine, Sommet arrivee){
		ArrayList<Troncon> retour = new ArrayList<Troncon>();
		ArrayList<ArrayList<Troncon>> lesRoutes = toutesRoutes(origine, arrivee);
		double temps=1000;
		for(ArrayList<Troncon> uneRoute : lesRoutes){
			double tempsT=0;
			for(Troncon t : uneRoute){
				tempsT+=t.getDistance()/t.getVitesseMax();
			}
			if(tempsT<=temps){
				temps=tempsT;
				retour=uneRoute;
			}
		}
		return retour;
	}
	
	public  String toString(){
		return reseau.toString();
	}
	
}