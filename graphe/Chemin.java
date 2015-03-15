package graphe;

import grapheX.Arc;
import grapheX.Sommet;

import java.util.ArrayList;
import java.util.Collection;



/**
 * La classe Chemin définit un chemin comme un ensemble d'arcs et maintient pour chaque chemin 
 * la somme des valeurs des arcs
 * 
 * @author blay
 *
 */
public class Chemin implements Comparable<Chemin>{

	//On pourrait éviter cet attribut en calculant la valeur à la demande.

	double distance = 0;

	ArrayList<Arc> paths = new ArrayList<Arc> ();

	public double valeur() {
		return distance;
	}


	public ArrayList<Arc> getArcs() {
		return paths;
	}

	public boolean add(Arc e) {
		distance += e.valeur();
		return paths.add(e);
	}
	public void add(int x, Arc e) {
		distance += e.valeur();
		paths.add(x,e);
	}

	public boolean addAll(Collection<Arc> c) {
		for (Arc a : c)
			distance += a.valeur();
		return paths.addAll(c);
	}

	public void clear() {
		distance = 0;
		paths.clear();

	}

	public boolean contains(Arc o) {
		return paths.contains(o);
	}


	public boolean isEmpty() {
		return paths.isEmpty();
	}

	public boolean remove(Arc o) {
		return paths.remove(o);
	}

	public boolean removeAll(Collection<Arc> c) {
		return 	paths.removeAll(c);
	}


	public int size() {
		return paths.size();
	}


	@Override
	public String toString() {
		return "Chemin [dist.=" + distance + ", paths=" + paths + "]";
	}


	public boolean atteint(Sommet arrivee) {
		for (Arc a : paths)
			if (a.destination().equals(arrivee))
				return true;
		return false;
	}



	public Chemin extraireChemin(Sommet depart, Sommet arrivee) {
		Chemin c = new Chemin();
		for (Arc a : paths) {
			c.add(a);
			if (a.destination().equals(arrivee))
				return c;
		}
		return c;
	}


	@Override
	public int compareTo(Chemin c) {
		if (this.valeur() < c.valeur() )
			return -1;
		else if  (this.valeur() == c.valeur() )	
			return 0;
		else
			return 1;

	}
}



