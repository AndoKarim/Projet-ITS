/*******************************************************/
/* Nom de classe : GestionnaireTroncon                 */
/* Description   : Enregistre les instances de Troncon */
/* Version       : 1.0                                 */ 
/* Date          : 15/10/2014                          */
/* Auteurs       : Perrel Ali                          */
/*******************************************************/

package parcours;
import java.util.*;

public abstract class GestionnaireTroncon {
	
	private static HashMap<String, Troncon> listeTroncon= new HashMap<String, Troncon>();

	public static ArrayList<Troncon> listTroncon() {
		Set listKeys = listeTroncon.keySet();
		Iterator i = listKeys.iterator();
                ArrayList al = new ArrayList();
		while(i.hasNext()) {
			Object key = i.next();
			al.add(listeTroncon.get(key));
		}
                return al;
	}
	
	public static void addTroncon(Troncon t){
		String n = t.getNom();
		listeTroncon.put(n, t);
	}
        
        public static Troncon findTroncon(String nom) {
		return listeTroncon.get(nom);	
	}
        
    public static Troncon getTroncon(int i){
        ArrayList<Troncon> list = listTroncon();
        return list.get(i);
    }
        
    public static double doubleRandomInclusive(double max, double min) {
        double r = Math.random();
        if (r < 0.5) {
             return ((1 - Math.random()) * (max - min) + min);
        }
        return (Math.random() * (max - min) + min);
    } 
}