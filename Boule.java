import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *La classe <code>Boule</code> est utilise pour creer un objet boule.
 *
 *@version 0.1
 *@author Alexis Verbrugge et Chahine Boutaljante
 */
public class Boule extends JComponent {
	
    private int etat;
    private int i;
    private int j;
    private int x;
    private int y;
    private Image imageBoule;
    
    /**
     *Constructeur qui permet de créer une boule.
     *
     *@param i la ligne
     *@param j la colonne
     *@param x l'abcisse
     *@param y l'ordonne
     *@param img l'image
     */
    public Boule ( int i, int j, int x, int y,Image img) {
	this.etat=0;
	this.i=i;
	this.j=j;
	this.x=x;
	this.y=y;
	imageBoule=img;
    }

    /**
     *Renvoie l'etat de la partie.
     *
     *@return l'etat de la partie
     */
    public int getEtat() {
	return this.etat;
    } 

    /**
     *Change l'etat de la partie
     *
     *@param e l'etat
     */
    public void setEtat(int e) {
	this.etat=e;
    }

    /**
     *Renvoie l'image de la boule
     *
     *@return l'image de la boule
     */
    public Image getImageBoule() {
	return this.imageBoule;
    }

    /**
     *Renvoie l'abcisse de la boule
     *
     *@return x l'abcisse
     */
    public int getXBoule() {
	return this.x;
    }

    /**
     *Renvoie l'ordonnee de la boule
     *
     *@return y l'odonnee
     */
    public int getYBoule() {

	return this.y;
    }

    /**
     *Change l'image de la boule
     *
     *@param img l'image
     */
    public void setImageBoule(Image img) {
	this.imageBoule=img;
    }
}
