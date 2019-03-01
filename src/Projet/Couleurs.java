package Projet;

public class Couleurs {
	
	public static int changerCouleur(String couleur) {
		couleur = couleur.toLowerCase();
		int pixel;
		switch(couleur) {
		case "blanc" : pixel = toBlanc(); break;
		case "gris" : pixel = toGris(); break;
		case "noir" : pixel = toNoir(); break;
		case "jaune" : pixel = toJaune(); break;
		case "rouge" : pixel = toRouge(); break;
		case "bleu" : pixel = toBleu(); break;
		case "orange" : pixel = toOrange(); break;
		case "vert" : pixel = toVert(); break;
		case "violet" : pixel = toViolet(); break;
		default : pixel = toBlanc(); break;
		}
		return pixel;
	}
	
	public static int toBlanc() {
		int pixel = ImageUtil.computePixel(255, 255, 255, 255);
		return pixel;
	}
	
	public static int toGris() {
		int pixel = ImageUtil.computePixel(255, 127, 127, 127);
		return pixel;
	}
	
	public static int toNoir() {
		int pixel = ImageUtil.computePixel(255, 0, 0, 0);
		return pixel;
	}
	
	public static int toJaune() {
		int pixel = ImageUtil.computePixel(255, 255, 255, 0);
		return pixel;
	}
	
	public static int toRouge() {
		int pixel = ImageUtil.computePixel(255, 255, 0, 0);
		return pixel;
	}
	
	public static int toBleu() {
		int pixel = ImageUtil.computePixel(255, 0, 0, 255);
		return pixel;
	}
	
	public static int toOrange() {
		int pixel = ImageUtil.computePixel(255, 255, 127, 0);
		return pixel;
	}
	
	public static int toVert() {
		int pixel = ImageUtil.computePixel(255, 0, 255, 0);
		return pixel;
	}
	
	public static int toViolet() {
		int pixel = ImageUtil.computePixel(255, 102, 0, 153);
		return pixel;
	}
	
	public static int noirEtBlanc(int pixel) {
		int [] tab = ImageUtil.explodePixel(pixel);
		int midValue = (tab[1]+tab[2]+tab[3])/3;
		pixel = ImageUtil.computePixel(255, midValue, midValue, midValue);
		return pixel;
	}
	
	public static int filtrer(int pixel, String couleur) {
		int [] p1 = ImageUtil.explodePixel(pixel);
		int [] p2 = ImageUtil.explodePixel(changerCouleur(couleur));
		int r = (p1[1]+p2[1])/2;
		int g = (p1[2]+p2[2])/2;
		int b = (p1[3]+p2[3]/2);
		int newPix = ImageUtil.computePixel(p1[0], r, g, b);
		return newPix;
	}

}
