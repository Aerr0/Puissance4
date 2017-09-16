package model;
public interface Puissance4Model {
	
	/**
	 * Retourne le joueur qui est en train de jouer
	 */
	char getJoueur ();
	
	/**
	 * Retourne vraie si la partie a �t� gagner, faux sinon
	 */
	boolean getPartieGagne ();
	
	/**
	 * Permet de savoir qu'elle jeton est en (lig, col)
	 */
	char getJeton ( int lig, int col );
	
	/**
	 * Retourne la grille du jeu
	 */
	char[][] getGrille ();
	
	/**
	 * Simule le placement du jeton du joueur courant dans la colonne (voir cin�matique de la m�thode)
	 */
	void jouer ( int col );
	
	/**
	 * Permet de savoir si la grille est pleine
	 */
	boolean grillePleine ();
	
	/**
	 * Affiche la grille du jeu. Utile pour le mode console
	 */
	String toString ();
	
	/**
	 * Vide la grille
	 */
	void viderGrille();
	
	/**
	 * Coord x nouveau jeton
	 */
	int getNvX();
	
	/**
	 * Coord y nouveau jeton
	 */
	int getNvY();
	
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
