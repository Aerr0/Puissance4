package ihm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Puissance4Model;

public class Grille extends JPanel {
	
	private Puissance4Model model;
	private JLabel[][] img;
	

	public Grille(Puissance4Model m) {
		model = m;
		
		img = new JLabel[6][6];
    	for (int i = 0; i < 6; i++) {
    		for (int j = 0; j < 6; j++) {
    			switch(model.getJeton(i, j)) {
					case 'R': img[i][j] = new JLabel(new ImageIcon("images/pionRouge.gif"));
					case 'J': img[i][j] = new JLabel(new ImageIcon("images/pionJaune.gif"));
					default:  img[i][j] = new JLabel(new ImageIcon("images/vide.gif"));
    			}
    		}
    	}
		
    	setLayout(new GridLayout (6,6));
    	setBackground(Color.WHITE);
    	
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				add(img[x][y]);
			}
		}
		
		repaint();
	}
	
	public void maj(Puissance4Model m) {
		String image;
		model = m;
		
		// on mélange les conditions car on change de joueur avant de mettre à jour
		
		if (model.getJoueur() == 'R') {
			image = "images/pionJaune.gif";
    	} else {
    		image = "images/pionRouge.gif";
    	}
		
		// animation
		/*int x = 0;
		while (x <= model.getNvX()) {
			if (x == 0) {
				img[x][model.getNvY()].setIcon(new ImageIcon(image));				 
			} else {
				img[x][model.getNvY()].setIcon(new ImageIcon(image));
				img[x-1][model.getNvY()].setIcon(new ImageIcon("images/vide.gif"));
			}
			repaint();
			try {
				wait(100);
			} catch(InterruptedException e){ System.out.println(e.getMessage()); }
			x++;
		}*/
		
		img[model.getNvX()][model.getNvY()].setIcon(new ImageIcon(image));
		
    	repaint();
	}
	
	public void viderGrille() {
		for (int i = 0; i < 6; i++) {
    		for (int j = 0; j < 6; j++) {
    			img[i][j].setIcon(new ImageIcon("images/vide.gif"));
    		}
		}
		
		repaint();
	}
}
