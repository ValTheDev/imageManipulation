package Projet;

public class Rectangle extends FormeGeometrique {

	private Trait t1, t2, t3, t4;
	
	public Rectangle(Point a, int largeur, int hauteur, String couleur, int epaisseur) {
		super(a, couleur, epaisseur);
		int xa = a.getX(); int ya = a.getY();
		Point b = new Point(xa, ya+hauteur);
		Point c = new Point(xa+largeur, ya+hauteur);
		Point d = new Point(xa+largeur, ya);
		t1 = new Trait(a, b, couleur, epaisseur);
		t2 = new Trait(b, c, couleur, epaisseur);
		t3 = new Trait(c, d, couleur, epaisseur);
		t4 = new Trait(d, a, couleur, epaisseur);	
	}
	
	public void draw(int[] image, int largeur, int epaisseur) {
		t1.draw(image, largeur, epaisseur);
		t2.draw(image, largeur, epaisseur);
		t3.draw(image, largeur, epaisseur);
		t4.draw(image, largeur, epaisseur);
	}
}
