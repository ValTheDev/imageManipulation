package Projet;

import java.io.IOException;

public class FramedImage {
	
	Afficheur aff;
	int [] image;
	int largeur;
	int hauteur;
	
	public FramedImage(int[] image, int largeur, int hauteur, String title) {
		this.image = image; this.largeur = largeur; this.hauteur = hauteur;
		aff = new Afficheur(largeur, hauteur, image, title);
	}
	
	public void trait() throws AbandonException, ArrayIndexOutOfBoundsException {
		Point a = IO.lirePoint("premier point du trait", largeur, hauteur);
		Point b = IO.lirePoint("second point du trait", largeur, hauteur);
		int epaisseur = IO.lireEnt("Veuillez indiquer l'epaisseur du trait (de 1 a 10) : ", 1, 10); 
		String couleur = IO.lireString("Veuillez indiquer la couleur du trait");
		Trait trait = new Trait(a, b, couleur, epaisseur);
		trait.draw(image, largeur, hauteur);
		aff.update(image);
	}
	
	public void rectangle() throws AbandonException, ArrayIndexOutOfBoundsException {
		Point a = IO.lirePoint("coin superieur gauche du rectangle", largeur, hauteur);
		int epaisseur = IO.lireEnt("Veuillez indiquer l'épaisseur des bords du rectangle (de 1 a 10) : ", 1, 10);
		int largeurR = IO.lireEnt("Veuillez indiquer la largeur du rectangle : ", 4, largeur-a.getX());
		int hauteurR = IO.lireEnt("Veuillez indiquer la hauteur du rectangle : ", 4, hauteur-a.getY());
		String couleur = IO.lireString("Veuillez indiquer la couleur du rectangle : ");
		Rectangle rec = new Rectangle(a, largeurR, hauteurR, couleur, epaisseur);
		rec.draw(image, largeur, hauteur);
		aff.update(image);
	}
	
	public void triangle() throws AbandonException, ArrayIndexOutOfBoundsException {
		Point a = IO.lirePoint("premier point du triangle", largeur, hauteur);
		Point b = IO.lirePoint("second point du triangle", largeur, hauteur);
		Point c= IO.lirePoint("troisieme point du triangle", largeur, hauteur);
		int epaisseur = IO.lireEnt("Veuillez indiquer l'epaisseur des bords du triangle (de 1 a 10) : ", 1, 10);
		String couleur = IO.lireString("Veuillez indiquer la couleur du rectangle");
		Triangle triangle = new Triangle(a, b, c, couleur, epaisseur);
		triangle.draw(image, largeur, hauteur);
		aff.update(image);	
	}

	public void cercle() throws AbandonException, ArrayIndexOutOfBoundsException {
		Point a = IO.lirePoint("centre du cercle", largeur, hauteur);
		int rayon = IO.lireEnt("Veuillez indiquer la taille du rayon du cercle : ", 0, 400);
		int epaisseur = IO.lireEnt("Veuillez indiquer l'epaisseur des bords du cercle : ", 0, 10);
		String couleur = IO.lireString("Veuillez indiquer la couleur du cercle : ");
		Cercle cercle = new Cercle(a, rayon, couleur, epaisseur);
		cercle.draw(image, largeur, hauteur);
		aff.update(image);
	}
	
	public void noirEtBlanc() {
		int [][] tab = imageToTab();
		for (int i=0; i<largeur; i++)
			for (int j=0; j<hauteur; j++) {
				image[i+j*largeur] = Couleurs.noirEtBlanc(tab[i][j]);
			}
		aff.update(image);
	}
	
	public void filtrer() {
		int [][] tab = imageToTab();
		String couleur = IO.lireString("Veuillez indiquer la couleur du filtre : ");
		for (int i=0; i<largeur; i++)
			for (int j=0; j<hauteur; j++) {
				image[i+j*largeur] = Couleurs.filtrer(tab[i][j], couleur);
			}
		aff.update(image);
	}
	
	public void miroirHor() {
		int [][] tab = imageToTab();
		for (int i=0; i<largeur; i++)
			for (int j=0; j<hauteur; j++) {
				image[(hauteur*largeur)-((j+1)*largeur)+i] = tab[i][j];
			}
		aff.update(image);
	}
	
	public void miroirVer() {
		int [][] tab = imageToTab();
		for (int i=0; i<largeur; i++)
			for (int j=0; j<hauteur; j++) {
				image[((j+1)*largeur)-i-1] = tab[i][j];
			}
		aff.update(image);
	}
	
	public void incruster() throws AbandonException {
		boolean retry;
		do {
			retry = false;
			try{
				String nomImage = IO.lireString("Veuillez indiquer le nom de l'image a incruster : ");
				int[] image2 = ImageUtil.readImage(nomImage);
				int largeur2 = ImageUtil.getImageWidth(nomImage);
				int hauteur2 = ImageUtil.getImageHeight(nomImage);
				if (largeur2>largeur || hauteur2>hauteur) IO.affiche("Cette image est trop grande pour etre incrustee");
				else {
					IO.affiche("Veuillez indiquer la position du coin supérieur gauche de l'image a incruster : ");
					int x = IO.lireEnt("Abscisse : ", 0, (largeur-largeur2));
					int y = IO.lireEnt("Ordonnee : ", 0, (hauteur-hauteur2));
					
					for(int i=x; i<(x+largeur2); i++)
						for(int j=y; j<(y+hauteur2); j++) {
							image[i+j*largeur] = image2[(i-x)+(j-y)*largeur2];
						}
					aff.update(image);
				}	
			}
			catch (IOException ie) {
				IO.affiche("Aucun fichier trouve au nom indique");
				retry = IO.confirmer("Voulez-vous fournir un nouveau nom de fichier? (O/N) ");
			}
			catch (ArrayIndexOutOfBoundsException e) {
				IO.affiche("L'image fournie depasse du support a cette position");
				retry = IO.confirmer("Voulez-vous essayer avec une autre image ou une nouvelle position? (O/N)");
			}
		}while(retry);
		
		
	}
	
	public int[] rotation() {
		int [][] tab = imageToTab();
		for (int i=0; i<largeur; i++)
			for(int j=0; j<hauteur; j++) {
				image[((i+1)*hauteur)-j-1] = tab[i][j];
		}
		return image;
	}
	
	public int[] reduire(int largeur, int hauteur) {
		int [][] tab = imageToTab();
		int [] newImage = new int[largeur*hauteur]; 
		for (int i=0; i<this.largeur; i++)
			for(int j=0; j<this.hauteur; j++) {
				if(i%2 == 0 && j%2 == 0) newImage[(i/2)+(j/2)*largeur] = tab[i][j];
			}
		return newImage;
	}
	
	public int[] rogner(Point a, int largeur, int hauteur) {
		int xa = a.getX(); int ya = a.getY();
		int [] newImage = new int[largeur*hauteur];
		int [][] tab = imageToTab();
		for (int i=xa; i<xa+largeur; i++)
			for (int j=ya; j<ya+hauteur; j++) {
				newImage[(i-xa)+((j-ya)*largeur)] = tab[i][j];
			}
		return newImage;
	}
	
	public int[][] imageToTab() {
		int [][] tab = new int[largeur][hauteur];
		for(int i=0; i<largeur; i++)
			for(int j=0; j<hauteur; j++) {
				tab[i][j] = image[i + j*largeur];
			}
		return tab;
	}
	
	public void cacher() {
		aff.masquer();
	}
	
}
