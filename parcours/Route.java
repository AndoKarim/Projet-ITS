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

public  class Route {

	 Graphe reseau = new GrapheSimple();
	
	public Route(){
		
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
	
	public ArrayList reseau(Sommet origine,Sommet arrivee){
		ParcoursSimple PS1 = new ParcoursSimple(reseau);
		ArrayList<Chemin> listeChemins =  PS1.chemins(origine);
		ArrayList c = new ArrayList();
		for(int i=0; i<listeChemins.size(); i++){
			c.add(listeChemins.get(i).extraireChemin(origine, arrivee));
		}
		return c;
	}
	
	public  String toString(){
		return reseau.toString();
	}
	
}