package Projet;

public class Triangle extends FormeGeometrique {

	private Trait t1, t2, t3;
	
	public Triangle(Point a, Point b, Point c, String couleur, int epaisseur) {
		super(a, couleur, epaisseur);
		t1 = new Trait(a, b, couleur, epaisseur);
		t2 = new Trait(b, c, couleur, epaisseur);
		t3 = new Trait(c, a, couleur, epaisseur);
	}
	
	public void draw(int[] image, int largeur, int hauteur) {
		t1.draw(image, largeur, hauteur);
		t2.draw(image, largeur, hauteur);
		t3.draw(image, largeur, hauteur);
	}
	
	
}
