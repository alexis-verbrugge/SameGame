import java.util.Random;
import java.io.*;

/**
 *La classe <code>Grille</code> est utilise pour creer les Grille de boules.
 *
 *@version 0.1
 *@author Chahine Boutaljante et Alexis Verbrugge
 */
public class Grille {

    private Random boule = new Random();
    private int longueur_grille;
    private int largeur_grille;
    private  char tab_caractere[][] = new char[10][15];

    /**
     *Constructeur qui permet de creer une grille remplit aleatoirement.
     *
     */
    public Grille() {
	int i,j;
	for (i=0; i<10; i++) {
	    for (j=0; j<15; j++) {
		int cmp=this.boule.nextInt(3);
		if (cmp==0) {
		    tab_caractere[i][j]='R';
		}
		if (cmp==1) {
		    tab_caractere[i][j]='V';
		}

		if (cmp==2) {
		    tab_caractere[i][j]='B';
		}
	    }
	}
    }

    /**
     *Constructeur qui permet de creer une grille avec un fichier choisi
     *
     *@param s fichier choisi
     */
    public Grille(String s) {
	try {
	    FileInputStream fichier = new FileInputStream(s);
	    InputStreamReader flux = new InputStreamReader(fichier);
	    try {
		for (int i=0; i<10; i++) {
		    for (int j=0; j<15; j++) {
			char cmp=(char)flux.read();
			tab_caractere[i][j]=cmp;
			if (tab_caractere[i][j]=='\n') {
			    j--;
			}

		    }
		}
	    }
	    catch (IOException e) {
		System.out.println("Erreur de lecture");
	    }
	}
	catch (FileNotFoundException e) {
	    System.out.println("Erreur a l'ouverture du fichier");
	}
    }

    /**
     *Affiche la grille dans la console.
     *
     */
    public void afficheGrille() {
	int i,j;
	for (i=0; i<10; i++) {
	    for (j=0; j<15; j++) {
		System.out.print("" + tab_caractere[i][j]);
	    }
	    System.out.println("");
	}
    }

    /**
     *Renvoie le caractere de la case indiqué
     *
     *@return caractere
     */
    public char[][] getTab_caractere() {
	return this.tab_caractere;
    }
}
