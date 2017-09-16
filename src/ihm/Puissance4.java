package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Puissance4Model;
import model.StdPuissance4Model;

public class Puissance4 {
	
	private JFrame frame;
    private Puissance4Model model;
    private Grille grille;
    private JPanel panelCentre, panelSud;
    private JLabel joueurCourant;
    private JButton vider;
    private JLabel[][] img;

    public Puissance4() {
        createModel();
        createView();
        placeComponents();
        createController();
    }
    
    public void display() {
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    }
    
    public void createModel() {
    	model = new StdPuissance4Model();
    }
    
    public void createView() {
    	final int frameWidth = 600;
    	final int frameHeight = 670;
    	
    	frame = new JFrame("Puissance 4");
    	frame.setPreferredSize(new Dimension (frameWidth, frameHeight));
    	frame.setBackground(Color.WHITE);
    	
    	grille = new Grille(model);
    	// Joueur courant = rouge au début
    	joueurCourant = new JLabel (new ImageIcon ("images/joueurRouge.jpg"));
  
    	vider = new JButton ("Vider");
    	vider.setPreferredSize(new Dimension(200,75));
    }
    
    public void placeComponents() {
    	// Panel Centre
    	panelCentre = new JPanel(); {
    		panelCentre.setBackground(Color.WHITE);
    		JPanel q = new JPanel(); {
    			q.setBackground(Color.WHITE);
    			q.add(joueurCourant);
    		}
    		panelCentre.add(q, BorderLayout.NORTH);
    		
    		panelCentre.add(grille, BorderLayout.CENTER);
    	
    		
    	}
    	frame.add(panelCentre);
    	
    	// Panel Sud
    	panelSud = new JPanel(new BorderLayout()); {
    		panelSud.setBackground(Color.WHITE);
    		JPanel q = new JPanel(new FlowLayout(FlowLayout.CENTER)); {
    			q.setBackground(Color.WHITE);
    			q.add(vider);
    		}
    		panelSud.add(q, BorderLayout.NORTH);
    		q = new JPanel(); {
    			q.setBackground(Color.WHITE);
	    		// panel joueur 1
	    		JPanel r = new JPanel(); {
	        		r.setBackground (Color.WHITE);
	        		JPanel s = new JPanel(); {
	        			s.setBackground(Color.WHITE);
	        			s.add(new JLabel(new ImageIcon ("images/joueurRouge.jpg")));
	        		}
	        		r.add(s);
	        	}
	    		q.add(r);
	    		
	    		// label VS
	    		r = new JPanel(); {
	    			r.setBackground(Color.WHITE);
	    			r.add(new JLabel (new ImageIcon ("images/vs.png")));
	    		}
	    		q.add(r);
	    		
	    		// panel joueur 2
	    		r = new JPanel(); {
	        		r.setBackground (Color.WHITE);
	        		JPanel s = new JPanel(); {
	        			s.setBackground(Color.WHITE);
	        			s.add(new JLabel(new ImageIcon ("images/joueurJaune.jpg")));
	        		}
	        		r.add(s);
	    		}
	    		q.add(r);
    		}
    		panelSud.add(q, BorderLayout.CENTER);
    	}
    	frame.add(panelSud, BorderLayout.SOUTH);
    }
    
    public void createController () {
    	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    	
    	vider.addActionListener (new ActionListener () {
    		public void actionPerformed (ActionEvent e) {
    			model.viderGrille();
    			grille.viderGrille();
    			refresh();
    		}
    	});
    	
    	grille.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				
			}
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				if (x <= 70) {
					model.jouer(1);
				} else if (x >= 70 && x  <= 138) {
					model.jouer(2);
				} else if (x >= 138 && x  <= 205) {
					model.jouer(3);
				} else if (x >= 205 && x <= 274) {
					model.jouer(4);
				} else if (x >= 274 && x  <= 340) {
					model.jouer(5);
				} else {
					model.jouer(6);
				}
				
				grille.maj(model);
				if (model.getPartieGagne()) {
					if (model.getJoueur() == 'R') { // c'est l'inverse des couleurs
						JOptionPane.showMessageDialog(frame,"Le joueur avec les jetons jaunes a gagné !!", "Victoire !", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame,"Le joueur avec les jetons rouges a gagné !!", "Victoire !", JOptionPane.INFORMATION_MESSAGE);
					}
					model.viderGrille();
	    			grille.viderGrille();
				}
				if (model.grillePleine()) {
					JOptionPane.showMessageDialog(frame,"La grille est pleine.\n Nouvelle partie !", "Grille pleine !", JOptionPane.INFORMATION_MESSAGE);
					model.viderGrille();
	    			grille.viderGrille();
				}
				refresh();
			}
			
		});
    }
    
    private void refresh () {
    	if (model.getJoueur() == 'R') {
    		// refresh concernant les éléments rouges
    		joueurCourant.setIcon(new ImageIcon("images/joueurRouge.jpg"));
    	} else {
    		// refresh concernant les éléments jaunes
    		joueurCourant.setIcon(new ImageIcon("images/joueurJaune.jpg"));
    	}
    }
    
    public static void main (String[] args) {
    	SwingUtilities.invokeLater(new Runnable () {
    		public void run () {
    			new Puissance4().display();
    		}
    	});
    }
}
