package graphe;

import grapheX.Arc;
import grapheX.Graphe;
import grapheX.Sommet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Cette classe a été nécessaire pour gérer les graphes ayant plusieurs aretes entre deux sommets données 
 * et ayant des arcs dans les deux sens entre deux sommets.
 * @author blay
 *
 */
public class GrapheSimple extends Graphe {
	  LinkedList<Sommet> listeSommets;
	  HashMap<Sommet,HashMap<Sommet,ArrayList<Arc<Sommet>>>> aretes;
	  
	  
	 public  GrapheSimple() {
	    listeSommets = new LinkedList<Sommet>();
	    aretes = new HashMap<Sommet,HashMap<Sommet,ArrayList<Arc<Sommet>>>>();
	  }
	    
	  public int taille() {
	    return listeSommets.size();
	  }
	  
	  public Graphe copie() {
	    return null;
	  }
	  
	  //n'ajoute le sommet que s'il n'est pas déjà dans le graphe.
	  public void ajouterSommet(Sommet s) {
		  if (existeSommet(s))
		      return;
	    listeSommets.add(s);
	    aretes.put(s,new HashMap<Sommet,ArrayList<Arc<Sommet>>>());
	  }
	  
	  public boolean existeArc(Sommet s, Sommet t) {
	    return aretes.get(s).containsKey(t);
	  }
	  
	  private boolean existeSommet(Sommet s) {
		    return aretes.containsKey(s);
		  }
	  public ArrayList<Arc<Sommet>> arcs(Sommet s, Sommet t) {
		    return aretes.get(s).get(t);
		  }
	  
	  public void ajouterArc(Sommet s, Sommet t) {
		  this.ajouterArc(s,t,0);
	  }
	  
	  
	  public void ajouterArc(Arc<Sommet> arc) {
		 HashMap<Sommet, ArrayList<Arc<Sommet>>> s = aretes.get(arc.origine());
		 if (s == null)
			 ajouterSommet(arc.origine());
		 if (aretes.get(arc.destination()) == null)
			 ajouterSommet(arc.destination());
		 if  (s.get(arc.destination()) == null)
			 s.put(arc.destination(), new ArrayList<Arc<Sommet>>());
		  s.get(arc.destination()).add(arc);
	  	
	  }
	  public void ajouterArc(Sommet s, Sommet t, int val) {
		 Arc<Sommet> a = new Arc<Sommet>(s,t,val);
	     this.ajouterArc(a);
	  }
	  
	  public double valeurArc(Sommet s, Sommet t) {
	    if (!existeArc(s,t)) throw new Error("Arc inexistant");
	    return aretes.get(s).get(t).get(0).valeur();
	  }
	  
	  //RETIRE TOUS LES ARCS VERS T
	  public void enleverArc(Sommet s, Sommet t) {
	    if (!existeArc(s,t)) return ;
	    aretes.get(s).remove(t);
	  }
	  
	  public Collection<Sommet> sommets() {
	    return listeSommets;
	  }
	  
	  public Collection<Arc<Sommet>> voisins(Sommet s) {
		ArrayList<Arc<Sommet>> voisins = new ArrayList<Arc<Sommet>>();
	    HashMap<Sommet, ArrayList<Arc<Sommet>>> arcs = aretes.get(s);
	    if ( arcs != null )
	    	for (ArrayList<Arc<Sommet>> av : arcs.values())
	    		voisins.addAll(av);
	      return voisins ;
	  }

	@Override
	public String toString() {
		return "Sommets=" + listeSommets + ";\n arcs="
				+ toStringArretes(aretes) + "]";
	}

	private String toStringArretes(
			HashMap<Sommet, HashMap<Sommet, ArrayList<Arc<Sommet>>>> aretes2) {
		String s = new String();
		for ( HashMap<Sommet, ArrayList<Arc<Sommet>>> x : aretes2.values()){
			for ( ArrayList<Arc<Sommet>> aretes : x.values())
				for (Arc a : aretes)
					s+= "\t"+ a.toString() + "\n" ;
		}
		return s;	
	}

}
