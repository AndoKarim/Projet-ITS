package dic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import COMMON.Lieu;

public class Dictionnaire {
	
	private static ArrayList<String> resultat=new ArrayList<String>();

    // while(matcher.find())
    	 
	public static ArrayList<String> rechercheInst(String unMot, HashMap<Integer, Lieu> listeLieu ){
		
			for(int i =0;i<listeLieu.size();i++)
			{	
				//on extrait le nom d'un lieu
				String nomLieu=listeLieu.get(i).getNom();
				
				//On créé un pattern et on teste si la valeur est presente
				if(Pattern.matches("^*"+unMot+".*$", nomLieu )==true)
						resultat.add(nomLieu);
			}
		return resultat;	
	}

}

