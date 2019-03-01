package Projet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Afficheur extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	int[] tab;
	JPanel jp;
  
	private final int BORDINF = 39;
	private final int BORDDROIT = 16;

	Afficheur(int width, int height, int[] pixels, String title) {
		BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		tab = ( (DataBufferInt) bim.getRaster().getDataBuffer()).getData();
	    System.arraycopy(pixels, 0, tab, 0, pixels.length);
	    jp = new ImagePanel(bim);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(bim.getWidth()+BORDDROIT,bim.getHeight()+BORDINF);
	    this.setLocationRelativeTo(null);
	    this.setTitle(title);
	    this.add(jp);
	    this.setVisible(true);
	    addMouseListener(this);
	}
  
	public void mouseClicked(MouseEvent ev) {
	    int x = ev.getX()-(BORDDROIT/2);
	    int y = ev.getY()-30 ;
	    IO.affiche("clic sur le pixel de coordonnees x ="+x+", y ="+y);
	}

	public void mousePressed(MouseEvent ev) {}
	public void mouseReleased(MouseEvent ev) {}
	public void mouseEntered(MouseEvent ev) {}
	public void mouseExited(MouseEvent ev) {}
  
	public void update(int[] pixels){
	    System.arraycopy(pixels, 0, tab, 0, pixels.length);
	    jp.revalidate();
	    jp.repaint();
	}
  
	void masquer(){
		this.setVisible(false);
	}

}

class ImagePanel extends JPanel{
 	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
    public ImagePanel(BufferedImage i) {image=i;}
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
    
}