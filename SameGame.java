import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.io.*;

/**
 *La classe <code>SameGame</code> est la classe essentiel du jeu, elle contient
 *toutes les methodes se rapportant au bon deroulement de la partie.
 *
 *@version 0.1
 *@author Alexis Verbrugge et Chahine Boutaljante
 */
public class SameGame extends JPanel {
    /* creation de la grille aleatoire a partir de la classe grille */
    private Grille grille1;
    /* tableaux permettant de stocker les fichiers images */
    private String tab_nom_image[] = {"img2/rouge.png", "img2/vert.png", "img2/bleu.png", "img2/rouge_sub.png",
				      "img2/vert_sub.png", "img2/bleu_sub.png", "img2/blanc.png" };
    private ImageIcon tab_image[][] = new ImageIcon[10][15];
    private Boule tab_boule[][] = new Boule[10][15];
    /* tableau permettant de stocker les id des cases du tableau de 1 a 150 */
    private boolean tab_etat[][] = new boolean[10][15];
    /* texte permettant l'affichage du score */
    private JLabel resultat = new JLabel("score : "  + this.scr);
    private JLabel high_resultat= new JLabel("high score : " + this.high_resultat);
    /* reel permettant le compte du score a partir des methodes scores et comptecaseproximite */
    private double scr;
    private double high_score;
    /* booleen permettant a la fonction recursive de mettre en surbrillance quand c'est vrai et faire disparaitre 
       les boules lorsque c'est faux */
    private int decision;
    private int etat;
    private int compt_score;
    private char tab_caractere[][];


    /**
     *Constructeur qui construit le jeu a partir d'une grille aleatoire.
     *
     */
    public SameGame() {
	super();
	this.grille1 = new Grille();
	int i,j;
	this.scr=0;
	this.etat=0;
	this.tab_caractere=grille1.getTab_caractere();
	/* mise en place du high score lors du lancement de jeu */
	try {
	    FileInputStream fichier_lecture = new FileInputStream("high_score.bin");
	    DataInputStream flux_entree = new DataInputStream(fichier_lecture);  
	    try {
		this.high_score=flux_entree.readDouble();
	    }
	    catch (IOException e) {
	   	System.err.println("Erreur de lecture");
	    }
	}
	catch (FileNotFoundException e) {
	    System.err.println("Erreur d'ouverture");
	}
	/* ajout des ecouteurs sur le jeu */
	this.addMouseMotionListener(new EvenementJeu());
	this.addMouseListener(new EvenementJeu());
	this.grille1.afficheGrille();

	
    }

    /**
     *constructeur qui prend en argument une chaine de caractere representant le fichier 
     *a partir du quel est construite la grille.
     *
     *@param s fichier
     */
    public SameGame(String s) {
	super();
	this.grille1 = new Grille(s);
	int i,j;
	this.scr=0;
	this.etat=0;
	this.tab_caractere=grille1.getTab_caractere();
	chuteDeBoule();
	decaleBouleGauche();
	try {
	    FileInputStream fichier_lecture = new FileInputStream("high_score.bin");
	    DataInputStream flux_entree = new DataInputStream(fichier_lecture);  
	    try {
		this.high_score=flux_entree.readDouble();
	    }
	    catch (IOException e) {
	   	System.err.println("Erreur de lecture");
	    }
	}
	catch (FileNotFoundException e) {
	    System.err.println("Erreur d'ouverture");
	}      
	this.addMouseMotionListener(new EvenementJeu());
	this.addMouseListener(new EvenementJeu());
	grille1.afficheGrille();
    }

    /** redifinition de la methode paintCompnent permettant de changer l'aspect graphique
     *au fur et a mesure de l'avancement du programme.
     *
     *@param g Graphics
     */
    @Override 
    public void paintComponent (Graphics g) {
   
	int i,j,k,x=0,y=35;
	char tab_tmp[] = {'R', 'V', 'B', 'r', 'v', 'b', ' '};
	ImageIcon img_tmp= new ImageIcon(" "); 
	super.paintComponent(g);

	for (i=0; i<10; i++,y+=50) {
	    for (j=0; j<15; j++,x+=50) {
		for (k=0; k<7; k++) {
                    /* dessin des images des boules en fonction du caractere stocké 
		       dans le tableau de grille */
                    if (this.tab_caractere[i][j]==tab_tmp[k]) {
			img_tmp= new ImageIcon(tab_nom_image[k]);
		    }
		    Image converti = img_tmp.getImage();
		    tab_boule[i][j] = new Boule(i,j,x,y,converti);
		    g.drawImage(tab_boule[i][j].getImageBoule(),x,y,null);
		}
	    }
	    x=0;	
	}
	g.setColor(new Color(43,0,154));
	g.setFont(new Font("Helvetica", Font.PLAIN, 17));
	g.drawString("Votre score : " + this.scr,160,20);
	this.resultat.paint(g);
	g.setColor(new Color(217,1,21));
	g.setFont(new Font("Helvetica", Font.PLAIN, 17));
	g.drawString("Votre high score : " + this.high_score,380,20);
	this.high_resultat.paint(g);
    }

    /**
     *Renvoie la grille utilisé.
     *
     *@return grille
     */
    public Grille getGrille() {
    	return this.grille1;
    }

    /**
     *Renvoie le tableau de boules.
     *
     *@return tableau de boules
     */
    public Boule[][] getTab_boule() {
    	return this.tab_boule;
    }

    public int getDecision() {
    	return this.decision;
    }

    public int setDecision(int d) {
    	return this.decision=d;
    }
    public int getCmptScore() {
    	return this.compt_score;
    } 
    public void setCmptScore(int s) {
    	this.compt_score=s;
    }
    public char getCaractere(int i, int j) {
    	return this.tab_caractere[i][j];
    }

    /**
     *methodes permettant de remplir le tableau de booleen 
     * de valeur=false pour pouvoir rappeler la methode recursive par 
     * la suite 
     */
    public void remplirTab_etat() {
    	for (int i=0; i<10; i++) {
	    for (int j=0; j<15; j++) {
		this.tab_etat[i][j]=false;
	    }
    	}
    }

    /**
     *methode principale du jeu permettant la gestion de la surbrillance, 
     *l'enlevement des blocs 
     *en fonction du changement des valeurs dans le tableau d'etat 
     *
     *@param i ligne
     *@param j colonne
     */
    public void groupeDeBouleSurbrillanceEtEnleve (int i, int j) {
	char tmp1=this.tab_caractere[i][j];
	ImageIcon tmp2=tab_image[i][j];
	
        /* a partir de l'attribut decision qui sera gerer on decide si la methode gere la surbrillance
	   ou la disparition des boules */
        if (this.decision==2) {

	    if (tmp1=='R')
		this.tab_caractere[i][j]='r';
	    if (tmp1=='V')
		this.tab_caractere[i][j]='v';
	    if (tmp1=='B')
		this.tab_caractere[i][j]='b';
	}

	else if (this.decision==1) {
	    this.tab_caractere[i][j]=' ';
	}

	if (tab_etat[i][j]==false) {
	    tab_etat[i][j]=true;
	    
	    if (i>0) {
		if (tmp1==this.tab_caractere[i-1][j]) {
		    groupeDeBouleSurbrillanceEtEnleve(i-1,j);
		    
		}
	    } 
	    if (j>0) {
		if (tmp1==this.tab_caractere[i][j-1] ) {
		    groupeDeBouleSurbrillanceEtEnleve(i,j-1);
		    
		}
	    } 
	    if (i<9) {
		if (tmp1==this.tab_caractere[i+1][j]) {
		    groupeDeBouleSurbrillanceEtEnleve(i+1,j);
		    
		}
	    } 
	    if (j<14) {
		if (tmp1==this.tab_caractere[i][j+1]) {
		    groupeDeBouleSurbrillanceEtEnleve(i,j+1);
		    
		}
	    }
	}
    }

    /**
     *methode permettant le compte du nombre de bloc dans un groupe a partir du tableau
     *de booleen apres un appel a la methode recursive
     *
     *@return nombre de boules d'un groupe
     */
    public int compteGroupe() {
    	int compteur=0;
    	for (int i=0; i<10; i++) {
	    for (int j=0; j<15; j++) {
		if (this.tab_etat[i][j]==true) {
		    compteur++;
		}
	    }
    	}
    	return compteur;
    }


    /**
     *methode permettant la chute des boules si un vide se trouve dans la case d'indice
     *i inferieur
     */
    public void chuteDeBoule () {
	int i,j,k;
	for (k=0; k<10; k++) {
	    for (i=0; i<9; i++) {
		for (j=0; j<15; j++) {
		    if (this.tab_caractere[i+1][j]==' ' && this.tab_caractere[i][j]!=' ') {
			this.tab_caractere[i+1][j]= this.tab_caractere[i][j];
			this.tab_caractere[i][j]=' ';
		    }

		}

	    }
	}
    }

    /**
     *methode permettant de savoir si une colonne de la grille est vide 
     *
     *@param j colonne
     *@return booleen attestant de l'etat de la colonne
     */
    public boolean etatColonne(int j) {
	for (int i=0; i<10; i++) {
	    if (this.tab_caractere[i][j]!=' ') {
		return false;
	    }
	}
	return true;
    }

    /**
     *methode permettant de decaler les colonnes si une colonne possede des boules 
     *et que celle a sa gauche est vide
     */
    public void decaleBouleGauche () {
    	int i,j,k;
    	for (k=0; k<10; k++) {
	    for (j=0; j<14; j++) {
		if (etatColonne(j)) {
		    for (i=0; i<10; i++) {
			this.tab_caractere[i][j]= this.tab_caractere[i][j+1];
			this.tab_caractere[i][j+1]=' ';
		    }
		}
	    }
	}
    }

    /**
     *methode permettant d'enlever la surbrillance apres que la souris a quitté 
     *les coordonnee d'une case 
     */
    public void reintialiseTableau() {
	for (int i=0; i<10; i++) {
	    for (int j=0; j<15; j++) {
		if (this.tab_caractere[i][j]=='r')
		    this.tab_caractere[i][j]='R';
		if (this.tab_caractere[i][j]=='v')
		    this.tab_caractere[i][j]='V';
		if (this.tab_caractere[i][j]=='b')
		    this.tab_caractere[i][j]='B';
	    }
	}
    }

    /**
     *methode qui retourne le score apres une disparition de boules 
     *
     *@param n nombre de boules detruites
     */
    public void score(int n) {
	double score = (double) n;
	this.scr=this.scr+Math.pow ( n-2.0, 2.0);
	System.out.println("groupe : " + n+ " boules");
    } 

    /**
     *methode permettant de changer le high score si le score lui est superieur.
     */ 
    public void high_score() {
    	double score=0.0;
    	try {
	    FileInputStream fichier_lecture = new FileInputStream("high_score.bin");
	    DataInputStream flux_entree = new DataInputStream(fichier_lecture);  
	    try {
		score=flux_entree.readDouble();
	    }
	    catch (IOException e) {
	
	    }
	    
	    try {
		fichier_lecture.close();
	    }
	    catch (IOException e) {
	   	System.err.println("Erreur de fermeture");
	    } 
	}
	catch (FileNotFoundException e) {
	    System.err.println("Erreur d'ouverture");
	   
	}
	try {
	    FileOutputStream fichier_sortie = new FileOutputStream("high_score.bin");
	    DataOutputStream flux_sortie = new DataOutputStream(fichier_sortie);
	    try {     
		if (high_score<scr) {
		    this.high_score=scr;
		    flux_sortie.writeDouble(scr);
		}
		else {
		    flux_sortie.writeDouble(score);
		}

	    }
	    catch (IOException e) {
	   	System.out.println("Erreur d'ecriture");
	    }
	

	    try {
		fichier_sortie.close(); 
	    }
	    catch (IOException e) {
		System.out.println("Erreur de fermeture");
	    }

	}
	catch (FileNotFoundException e) {
	    System.out.println("Erreur d'ouverture");
	}

    }

    /**
     *methode qui permet de tester la fin du jeu.
     *
     *@return booleen
     */
    public boolean finDeJeu() {
    	for (int i=0; i<10; i++) {
	    for (int j=0; j<15; j++) {
		if (this.tab_caractere[i][j]!=' ') {
		    setEtat(0);
		    groupeDeBouleSurbrillanceEtEnleve(i,j);
    			
		    if (compteGroupe()>1) {
			return false;
		    }
		    remplirTab_etat();
		}
	    }
    	}
    	remplirTab_etat();
    	return true;
    }

    public void setEtat(int e) {
	this.etat=e;
    }

    public int getEtat() {
	return this.etat;
    }
    public double getScore() {
	return this.scr;
    }
    
}



