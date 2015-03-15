/**
 *@author DIB Nasreddine & POIRIER Patrick 
 * */
package Bouchons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public abstract class Fichier {
	//------------------------------------------------------------------------------------
	//Methode LireLog qui renvoi un String contenant les elements enregistre par un trajet
	/**
	 * Ecrire dans un fichier
	 * 
	 * @param String text : le text a ajouter au fichier
	 * @param String leFichier : le nom du fichier
	 * @param boolean append : true = ecrire a la suite. false = ecraser le contenu
	 * 
	 * @author Dib Nasreddine
	 * @throws Throwable
	 */
	public static void ecrireFichier(String texte, String leFichier, boolean append) throws IOException {
		try{
			File dir = new File(".");
			File file = new File(dir.getCanonicalPath() + File.separator + "\\src\\Bouchons\\" + leFichier);

			//Si le Fichier n'existe pas le cree
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), append);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(texte);
			bw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
	//------------------------------------------------------------------------------------
	//Methode LireLog qui renvoi un String contenant les elements enregistre par un trajet
	/**
	 * Lire d'un fichier
	 * @param String leFichier : le nom du fichier
	 * 
	 * @author Dib Nasreddine
	 * @return Retourne leContenu du fichier
	 * @throws Throwable
	 */
		public static String lireFichier(String leFichier) throws Throwable {
			File dir = new File(".");
			File file = new File(dir.getCanonicalPath() + File.separator + "\\src\\Bouchons\\" + leFichier);
			if (!file.exists())
				throw new Throwable("Le fichier n'existe pas");
			String Log = "";
			FileInputStream fis = new FileInputStream(file);	
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		 
			String line;
			while ((line = br.readLine()) != null)
				Log+= line + "\n";
			br.close();
			return Log;
		}
	//------------------------------------------------------------------------------------
		/**
		 * vider un fichier
		 * @param String leFichier : le nom du fichier
		 * 
		 * @author Dib Nasreddine
		 * @throws Throwable
		 */
			public static void viderFichier(String leFichier){
				try {
					ecrireFichier("", leFichier, false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//------------------------------------------------------------------------------------
		
}
