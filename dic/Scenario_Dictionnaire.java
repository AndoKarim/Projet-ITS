package dic;


import java.util.ArrayList;
import java.util.HashMap;

import COMMON.Lieu;
//import Consommation.US_5;
public class Scenario_Dictionnaire {

	public static void main(String[] args) {
			
			Dictionnaire dico = new Dictionnaire();
		
			String unMot ="Promenade";
			ArrayList<String> res=new ArrayList<String>();
			
			HashMap<Integer,Lieu> listeLieu=new HashMap<Integer,Lieu> ();
			Lieu l1;
			Lieu l2;
			Lieu l3;
			Lieu l4;
			Lieu l5;
			try {
				l1 = new Lieu("Promenade des Anglais", 1,1);
				l2 = new Lieu("Promenade des Arts", 1,2);
				l3 = new Lieu("Palais des Expositions", 2,2);
				l4 = new Lieu("Jean-Medecin", 4,5);
				l5 = new Lieu("Promenade", 5,5);
			    listeLieu.put(0, l1);
			    listeLieu.put(1, l2);
			    listeLieu.put(2, l3);
			    listeLieu.put(3, l4);
			    listeLieu.put(4, l5);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			   
		   res=dico.rechercheInst(unMot,listeLieu);
		   System.out.println(res.size());
		   System.out.println(res.toString());
		}

	}
