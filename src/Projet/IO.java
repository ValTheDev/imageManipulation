package Projet;

import java.util.*;

public class IO {
	
	static Scanner scan = new Scanner(System.in);
	
	public static int lireEnt(String str, int min, int max) throws AbandonException {
		do {
			try {
				affiche(str);
				int input = scan.nextInt();
				if (input>=min && input<=max) return input;
				if (confirmer("Hors intervalle indiquee (["+min+"-"+max+"]), voulez-vous fournir une nouvelle valeur ? (O/N)"));
				else throw new AbandonException();
			}
			catch (InputMismatchException ie) {
				affiche("Veuillez indiquer une valeur numerique \n");
				scan.next();
			}
		}while(true);
	}
	
	public static String lireString(String st) {
		System.out.println(st);
		String input = scan.nextLine();
		if (input.isEmpty()) input = scan.nextLine();
		return input;
	}
	
	public static Point lirePoint(String sti, int largeur, int hauteur) throws AbandonException {
		String st = "Veuillez indiquer les coordonnees du " + sti ;
		affiche(st);
		int xa = lireEnt("Absisse : ", 0, largeur);
		int ya = lireEnt("Ordonnee : ", 0, hauteur);
		Point p = new Point(xa, ya);
		return p;
	}
	
	public static void affiche(String st) {
		System.out.println(st);
	}
	
	public static boolean confirmer(String st) {
		affiche(st);
		char c = scan.next().charAt(0);
		return (c == 'o' || c == 'O');
	}

}
