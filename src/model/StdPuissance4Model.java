package model;
import java.util.Observable;

public class StdPuissance4Model extends Observable implements Puissance4Model {
	private final int NB_LIGNES   = 7;
	private final int NB_COLONNES = 7;
	
	private char[][] grille;
	private char 	 joueurCourant;
	private boolean  partieGagne;
	private int nvX, nvY;
	// il sera plus ais� de d�terminer si la partie est gagn�e au moment o� le joueur place son jeton,
	// ainsi on ne parcours pas toute la grille, mais on part uniquement du pion venant d'�tre plac�.

	public StdPuissance4Model () {
		int cptL, cptC;
		
		this.grille = new char[NB_LIGNES][NB_COLONNES];

		cptL = 0;
		while ( cptL < NB_LIGNES-1 ) {
			cptC = 0;

			while ( cptC < NB_COLONNES-1 ) {
				this.grille[cptL][cptC] = ' ';
				cptC ++;
			}
			cptL ++;
		}
		
		// 1er joueur : joueur Rouge
		this.joueurCourant = 'R';
		this.partieGagne = false;
	}

	public char    getJoueur      () { return this.joueurCourant; } // 'R' ou 'J'
	public boolean getPartieGagne () { return this.partieGagne;   }
	public char[][] getGrille     () { return grille;             }
	public int getNvX             () { return nvX;                }
	public int getNvY             () { return nvY;                }
	
	public char getJeton ( int lig, int col ) { return grille[lig][col]; }

	public void jouer ( int col ) {
		// positionne le jeton du joueur courant dans la colonne (voir cin�matique de la m�thode)
		int cptL;
		boolean dessousVide;
		
		if ( col >= 1 && col <= NB_COLONNES - 1) {
			col --;

			dessousVide = true;
			cptL = 0;
			while ( dessousVide ) { 
				if ( this.grille[cptL+1][col] == ' ') { dessousVide = true; cptL ++; }
				else 								  { dessousVide = false; 		 }
			}
			
			if ( this.grille[0][col] != ' ' ) { dessousVide = false; }
			else							  { dessousVide = true;  }

			if ( dessousVide ) {
				this.grille[cptL][col] = this.joueurCourant;
				nvX = cptL;
				nvY = col;
				if ( this.aGagne (cptL, col) ) {
					this.partieGagne = true;
				}
				this.changerJoueur ();
			}
		}
	}

	public boolean grillePleine () {
		int cptC;

		cptC = 0;
		while ( cptC < NB_COLONNES - 1 ) { 
			if ( this.grille[0][cptC] == ' ' ) { return false; }
			cptC ++;
		}
		return true;
	}

	public String toString () {
		int cptL, cptC;
		String sRet = "";

		sRet += "+";
		for ( cptC = 0; cptC < NB_COLONNES-1; cptC ++ ) { sRet += "---+"; }
		sRet += "\n";
		cptL = 0;
		while ( cptL < NB_LIGNES-1 ) {
			sRet += "| ";

			cptC = 0;
			while ( cptC < NB_COLONNES-1 ) {
				sRet += this.grille[cptL][cptC] + " | ";
				cptC ++;
			}
			sRet += "\n";
			cptL ++;
		}
		sRet += "+";

		for ( cptC = 0; cptC < NB_COLONNES-1; cptC ++ ) { sRet += "---+"; }
		sRet += "\n";

		if ( this.partieGagne ) {
			if ( joueurCourant == 'R' ) { sRet += "Le joueur rouge a gagne !!!"; }
			if ( joueurCourant == 'J' ) { sRet += "Le joueur jaune a gagne !!!"; } 
		}
		return sRet;
	}

	private boolean aGagne ( int lig, int col ) {
		// m�thode priv�e permettant de d�terminer si le joueur venant de jouer � effectuer un alignement 
		// de quatre jetons
		int ligT = lig + 1;
		int colT = col + 1;

		/**** Test pour les Rouges ****/
		// Test verticale
		if (ligT <= 3 && this.grille[lig][col] == 'R' && this.grille[lig+1][col] == 'R' && 
			this.grille[lig+2][col] == 'R' && this.grille[lig+3][col] == 'R') 		   				
		{ return true; }
		
		// Test horizontale
		if (colT >= 1 && colT <= 3 && this.grille[lig][col] == 'R' && this.grille[lig][col+1] == 'R' && 
			this.grille[lig][col+2] == 'R' && this.grille[lig][col+3] == 'R' ) 
		{ return true; }

		if (colT >= 2 && colT <= 4 && this.grille[lig][col] == 'R' && this.grille[lig][col-1] == 'R' && 
				this.grille[lig][col+1] == 'R' && this.grille[lig][col+2]
			== 'R' ) { return true; }

		if (colT >= 3 && colT <= 5 && this.grille[lig][col] == 'R' && this.grille[lig][col-1] == 'R' && 
			this.grille[lig][col-2] == 'R' && this.grille[lig][col+1] == 'R' ) 
		{ return true; }

		

		if (colT >= 4 && colT <= 6 && this.grille[lig][col] == 'R' && this.grille[lig][col-1] == 'R' && 
			this.grille[lig][col-2] == 'R' && this.grille[lig][col-3] == 'R' ) 
		{ return true; }



		// Test diagonale haut - bas (de gauche � droite)
		if (ligT >= 1 && colT >= 1 && ligT <= 3 && colT <= 3 && 
			this.grille[lig][col] == 'R' && this.grille[lig+1][col+1] == 'R' && this.grille[lig+2][col+2] == 'R' &&
			this.grille[lig+3][col+3] == 'R' ) 
			{ return true; }

		

		if (ligT >= 2 && colT >= 2 && ligT <= 4 && colT<= 4 && 
			this.grille[lig][col] == 'R' && this.grille[lig-1][col-1] == 'R' && this.grille[lig+1][col+1] == 'R' && 
			this.grille[lig+2][col+2] == 'R' ) 
			{ return true; }

		

		if (ligT >= 3 && colT >= 3 && ligT <= 5 && colT<= 5 &&
			this.grille[lig][col] == 'R' && this.grille[lig-1][col-1] == 'R' && this.grille[lig-2][col-2] == 'R' && 
			this.grille[lig+1][col+1] == 'R' ) 
			{ return true; }

		

		if (ligT >= 4 && colT >= 4 && ligT <= 6 && colT<= 6 && 
			this.grille[lig][col] == 'R' && this.grille[lig-1][col-1] == 'R' && this.grille[lig-2][col-2] == 'R' && 
			this.grille[lig-3][col-3] == 'R' ) 
			{ return true; }



		// Test diagonale bas - haut (de gauche � droite)
		if (ligT >= 4 && colT >= 1 && ligT <= 6 && colT <= 3 &&
			this.grille[lig][col] == 'R' && this.grille[lig-1][col+1] == 'R' && this.grille[lig-2][col+2] == 'R' && 
			this.grille[lig-3][col+3] == 'R' ) 
			{ return true; }

			

		if (ligT >= 3 && colT >= 2 && ligT <= 5 && colT <= 4 && 
			this.grille[lig][col] == 'R' && this.grille[lig+1][col-1] == 'R' && this.grille[lig-1][col+1] == 'R' && 
			this.grille[lig-2][col+2] == 'R' ) 
			{ return true; }

			

		if (ligT >= 2 && colT >= 3 && ligT <= 4 && colT <= 5 &&
			this.grille[lig][col] == 'R' && this.grille[lig+1][col-1] == 'R' && this.grille[lig+2][col-2] == 'R' &&
			this.grille[lig-1][col+1] == 'R' ) 
			{ return true; }

		

		if (ligT >= 1 && colT >= 4 && ligT <= 3 && colT <= 6 && 
			this.grille[lig][col] == 'R' && this.grille[lig+1][col-1] == 'R' && this.grille[lig+2][col-2] == 'R' && 
			this.grille[lig+3][col-3] == 'R' ) 
			{ return true; }





		/**** Test pour les jaunes ****/
		// Test verticale
		if (ligT <= 3 && this.grille[lig][col] == 'J' && this.grille[lig+1][col] == 'J' && 
			this.grille[lig+2][col] == 'J' && this.grille[lig+3][col] == 'J') 		   	
			{ return true; }



		// Test horizontale
		if (colT >= 1 && colT <= 3 && this.grille[lig][col] == 'J' && this.grille[lig][col+1] == 'J' && 
			this.grille[lig][col+2] == 'J' && this.grille[lig][col+3] == 'J' ) 
			{ return true; }

		if (colT >= 2 && colT <= 4 && this.grille[lig][col] == 'J' && this.grille[lig][col-1] == 'J' && 
			this.grille[lig][col+1] == 'J' && this.grille[lig][col+2] == 'J' ) 
			{ return true; }

		if (colT >= 3 && colT <= 5 && this.grille[lig][col] == 'J' && this.grille[lig][col-1] == 'J' && 
			this.grille[lig][col-2] == 'J' && this.grille[lig][col+1] == 'J' ) 
			{ return true; }

		if (colT >= 4 && colT <= 6 && this.grille[lig][col] == 'J' && this.grille[lig][col-1] == 'J' && 
			this.grille[lig][col-2] == 'J' && this.grille[lig][col-3] == 'J' ) 
			{ return true; }

		// Test diagonale haut - bas (de gauche � droite)
		if (ligT >= 1 && colT >= 1 && ligT <= 3 && colT <= 3 && 
			this.grille[lig][col] == 'J' && this.grille[lig+1][col+1] == 'J' && this.grille[lig+2][col+2] == 'J' && 
			this.grille[lig+3][col+3] == 'J' ) 
			{ return true; }

		if (ligT >= 2 && colT >= 2 && ligT <= 4 && colT<= 4 && 
			this.grille[lig][col] == 'J' && this.grille[lig-1][col-1] == 'J' && this.grille[lig+1][col+1] == 'J' && 
			this.grille[lig+2][col+2] == 'J' ) 
			{ return true; }	

		if (ligT >= 3 && colT >= 3 && ligT <= 5 && colT<= 5 &&
			this.grille[lig][col] == 'J' && this.grille[lig-1][col-1] == 'J' && this.grille[lig-2][col-2] == 'J' && 
			this.grille[lig+1][col+1] == 'J' ) 
			{ return true; }	

		if (ligT >= 4 && colT >= 4 && ligT <= 6 && colT<= 6 && 
			this.grille[lig][col] == 'J' && this.grille[lig-1][col-1] == 'J' && this.grille[lig-2][col-2] == 'J' && 
			this.grille[lig-3][col-3] == 'J' ) 
			{ return true; }



		// Test diagonale bas - haut (de gauche � droite)
		if (ligT >= 4 && colT >= 1 && ligT <= 6 && colT <= 3 &&
			this.grille[lig][col] == 'J' && this.grille[lig-1][col+1] == 'J' && this.grille[lig-2][col+2] == 'J' && 
			this.grille[lig-3][col+3] == 'J' ) 
			{ return true; }

		if (ligT >= 3 && colT >= 2 && ligT <= 5 && colT <= 4 && 
			this.grille[lig][col] == 'J' && this.grille[lig+1][col-1] == 'J' && this.grille[lig-1][col+1] == 'J' && 
			this.grille[lig-2][col+2] == 'J' ) 
			{ return true; }

		if (ligT >= 2 && colT >= 3 && ligT <= 4 && colT <= 5 &&
			this.grille[lig][col] == 'J' && this.grille[lig+1][col-1] == 'J' && this.grille[lig+2][col-2] == 'J' && 
			this.grille[lig-1][col+1] == 'J' ) 
			{ return true; }
		
		if (ligT >= 1 && colT >= 4 && ligT <= 3 && colT <= 6 && 
			this.grille[lig][col] == 'J' && this.grille[lig+1][col-1] == 'J' && this.grille[lig+2][col-2] == 'J' && 
			this.grille[lig+3][col-3] == 'J' ) 
			{ return true; }

		return false;
	}

	private void changerJoueur () {
		if ( this.joueurCourant == 'R' ) {
			this.joueurCourant = 'J';
		}
		else {
			this.joueurCourant = 'R';
		}

	}
	
	public void viderGrille() {
		int i, j;
		i = 0;
		while ( i < NB_LIGNES-1 ) {
			j = 0;
			while ( j < NB_COLONNES-1 ) {
				this.grille[i][j] = ' ';
				j ++;
			}
			i ++;
		}
		partieGagne = false;
	}
	

	/*--------------------------------------------------------------------------------------------------------

	Cin�matique de la m�thode jouer 

		�Est-ce que la colonne est valide

		�Est-ce qu'il reste des lignes disponibles dans la colonne choisie

		�Recherche de la ligne o� va tomber le jeton

		�On place sur cette ligne le pion du joueur en cours mat�rialis� par sa lettre

		�D�termination si le joueur � Gagner (appelle � la m�thode aGagne)

		� Si le joueur n'a pas gagn� on change de joueur

	----------------------------------------------------------------------------------------------------------*/



}

