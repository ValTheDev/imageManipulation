package Projet;

public class Cercle extends FormeGeometrique {
	
	private int rayon;
	
	public Cercle(Point a, int rayon, String couleur, int epaisseur) {
		super(a, couleur, epaisseur);
		this.rayon = rayon;
	}
	
	public void draw(int[] image, int largeur, int hauteur) {
		int xa = a.getX(); int ya = a.getY();
		int rayonE1 = (rayon-e1)*(rayon-e1);
		int rayonE2 = (rayon+e2)*(rayon+e2);
		for(int i=0; i<largeur; i++)
			for(int j=0; j<hauteur; j++) {
				int pixel = (xa-i)*(xa-i) +(ya-j)*(ya-j);
				if(pixel>=rayonE1 && pixel<=rayonE2) image[i+j*largeur] = Couleurs.changerCouleur(couleur);
			}
		
	}

}
