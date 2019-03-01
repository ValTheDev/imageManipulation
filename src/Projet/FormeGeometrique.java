package Projet;

public abstract class FormeGeometrique {
	
	protected int e1, e2, epaisseur;
	protected Point a;
	protected String couleur;
	
	public FormeGeometrique(Point a, String couleur, int epaisseur) {
		this.a = a;
		this.couleur = couleur;
		this.epaisseur = epaisseur;
		calculerEpaisseur();
	}
	
	public void calculerEpaisseur() {
		e1 = epaisseur/2;
		e2 = e1;
		if(epaisseur%2 != 0) e2++;
	}
	
	public abstract void draw(int[] image, int largeur, int hauteur);
	
	
}
