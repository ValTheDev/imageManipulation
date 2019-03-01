package Projet;

import java.io.*;

public class Client {
	
	static int largeur;
	static int hauteur;
	static FramedImage aff;
	
	public static void main (String [] args) {
		
		IO.affiche("Bienvenue sur votre gestionnaire d'image\n");
		IO.affiche("En cliquant sur un point de l'image, vous obtiendrez les coordonnees du pixel correspondant\n");
		IO.affiche("Vous disposez de la palette de couleur suivante pour les formes geometriques et les filtres : ");
		IO.affiche("Blanc, gris, noir, jaune, rouge, bleu, vert, orange et violet");
		IO.affiche("Si vous indiquez une couleur non existante ou que vous ne l'epeller pas correctement, le blanc sera selectionne par defaut\n");
		
		do {
			try {
				String fichier = IO.lireString("Veuillez indiquer le nom de l'image a charger : ");
				largeur = ImageUtil.getImageWidth(fichier);
				hauteur = ImageUtil.getImageHeight(fichier);
				int [] image = ImageUtil.readImage(fichier);
				aff = new FramedImage(image, largeur, hauteur, "Image");
			}	
			catch (IOException ioe) {
				IO.affiche("Aucun fichier trouve au nom indique\n");
			}
		}while(aff == null);
		
		int choix = -1;
		do {
			try {
				choix = menuTexte();
				switch(choix) {
					case 1 : aff.trait(); break;
					case 2 : aff.rectangle(); break;
					case 3 : aff.triangle(); break;
					case 4 : aff.cercle(); break;
					case 5 : aff.noirEtBlanc(); break;
					case 6 : aff.filtrer(); break;
					case 7 : aff.miroirHor(); break;
					case 8 : aff.miroirVer(); break;
					case 9 : aff.incruster(); break;
					case 10 : rotation(); break;
					case 11 : reduire(); break;
					case 12 : rogner(); break;
					case 0 : break;
				}
			}
			catch (AbandonException ae) {
				IO.affiche("Retour au menu\n");
			}
			catch (ArrayIndexOutOfBoundsException ai) {
				IO.affiche("Veillez a ce que la forme indiquee ne depasse pas les bords de l'image");
				IO.affiche("Pour rappel, l'image fait " +largeur+ " pixels de large et " +hauteur+ " pixels de haut");
				IO.affiche("Retour au menu\n");
			}
		}while(choix!=0);
		IO.affiche("Au revoir");
	}


	public static int menuTexte() throws AbandonException {
		String menu = "1) Ajouter un trait\t\t2) Ajouter un rectangle\n"
					+"3) Ajouter un triangle\t\t4) Ajouter un cercle\n"
					+"5) Noir et Blanc\t\t6) Filtrer\n"
					+"7) Miroir (axe horizontal)\t8) Miroir (axe vertical)\n"
					+"9) Incruster\t\t\t10) Rotation 90°\n"
					+"11) Reduire\t\t\t12) Rogner\n"
					+"0)Terminer programme\n"
					+"Choix : ";
		int x = IO.lireEnt(menu, 0, 12);
		return x;
	}
	
	public static void rotation() {
		aff.cacher();
		largeur = largeur + hauteur;
		hauteur = largeur - hauteur;
		largeur = largeur - hauteur;
		aff = new FramedImage(aff.rotation(), largeur, hauteur, "Image");
	}
	
	public static void rogner() throws AbandonException {
		Point a = IO.lirePoint("coin superieur gauche du rectangle a rogner", largeur, hauteur);
		largeur = IO.lireEnt("Veuillez indiquer la largeur du rectangle a rogner : ", 0, largeur-a.getX());
		hauteur = IO.lireEnt("Veuillez indiquer la hauteur du rectangle a rogner : ", 0, hauteur-a.getY());
		aff.cacher();
		aff = new FramedImage(aff.rogner(a, largeur, hauteur), largeur, hauteur, "Image");
	}
	
	public static void reduire() {
		aff.cacher();
		if (hauteur%2 == 0) hauteur = hauteur/2;
		else hauteur = hauteur/2+1;
		if (largeur%2 == 0) largeur = largeur/2;
		else largeur = largeur/2+1;
		aff = new FramedImage(aff.reduire(largeur, hauteur), largeur, hauteur, "Image");
	}
	
}
