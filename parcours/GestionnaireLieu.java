/****************************************************/
/* Nom de classe : GestionnaireLieu                 */
/* Description   : Enregistre les instances de lieu */
/* Version       : 1.0                              */ 
/* Date          : 15/10/2014                       */
/* Auteurs       : Ghira Anasse                     */
/****************************************************/

package parcours;

import java.io.*;
import java.util.*;

import COMMON.Lieu;



public abstract class GestionnaireLieu {
	private static HashMap<String,Lieu> listeLieu = new HashMap<String, Lieu>();


	public static ArrayList<Lieu> listLieu() {
		Set<String> listKeys = listeLieu.keySet();
		Iterator<String> i = listKeys.iterator();
                ArrayList<Lieu> al = new ArrayList<Lieu>();
		while(i.hasNext()) {
			Object key = i.next();
			al.add(listeLieu.get(key));
		}
                return al;
	}
	
	public static void addLieu(Lieu l) throws FileNotFoundException, IOException {
            listeLieu.put(l.getNom(), l);
            System.out.println("GestionnaireLieu.addLieu()");
	}
        
        public static Lieu findLieu(String nom) {
		return listeLieu.get(nom);	
	}
        
        /******************************************************************/
        /*Methode utiles simplement pour la demonstration (serialisation) */
        /******************************************************************/
        public static void addListe(ArrayList<Lieu> l){
            int taille = l.size();
            for(int i=0;i<taille;i++)
                listeLieu.put(l.get(i).getNom(), l.get(i));
        }
}