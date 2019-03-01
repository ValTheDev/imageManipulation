package Projet;

public class Trait extends FormeGeometrique{
	
	private Point b;
	
	public Trait(Point a, Point b, String couleur, int epaisseur) {
		super(a, couleur, epaisseur);
		this.b = b;
	}
	
	public void draw(int[] image, int largeur, int hauteur) {
		int xa = a.getX(); int ya = a.getY();
		int xb = b.getX(); int yb = b.getY();
		
		int xMini = Math.min(xa, xb)-e1;
		int yMini = Math.min(ya, yb)-e1;
		int xMaxi = Math.max(xa, xb)+e2;
		int yMaxi = Math.max(ya, yb)+e2;
		if (xMaxi>largeur) xMaxi = largeur;
		if (yMaxi>hauteur) yMaxi = hauteur;
		
		if (xa == xb || ya == yb) {
			for(int i=xMini; i<xMaxi; i++)
				for(int j=yMini; j<yMaxi; j++) {
					image[i+j*largeur] = Couleurs.changerCouleur(couleur);
				}
		}
		
		else {
			double m  = (double)(yb-ya)/(xb-xa);
			double p = (double)ya-m*xa;

			if (m>1) {
				for(int i=xMini; i<xMaxi; i++)
					for(int j=yMini; j<yMaxi; j++) {
						if(j>=m*(i-e1)+p && j<=m*(i+e2)+p) image[i+j*largeur] = Couleurs.changerCouleur(couleur);
					}
			}
			
			else if (m<-1) {
				for(int i=xMini; i<xMaxi; i++)
					for(int j=yMini; j<yMaxi; j++) {
						if(j>=m*(i+e1)+p && j<=m*(i-e2)+p) image[i+j*largeur] = Couleurs.changerCouleur(couleur);
					}
			}
			
			else {
				for(int i=xMini; i<xMaxi; i++)
					for(int j=yMini; j<yMaxi; j++) {
						if(j>=m*i+p-e1 && j<=m*i+p+e2) image[i+j*largeur] = Couleurs.changerCouleur(couleur);
					}
			}
			
		}
	}
	
}

