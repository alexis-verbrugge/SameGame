import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 *La classe <code>Fenetre</code> gère toutes les fenetres de jeu du SameGame
 *notamment les menus et la partie en cours.
 *
 *@version 0.1
 *@author Chahine Boutaljante et Alexis Verbrugge
 */
public class Fenetre extends JFrame implements ActionListener, MouseListener {

    //public int etat;
    private double score;
    private Menu menu = new Menu();
    private Menu_Fin menu_fin = new Menu_Fin("");
    private String fichier_choisi;
    private SameGame jeux1 = new SameGame();
    private boolean partie_en_cours;

    /**
     *Constructeur de la fenetre qui gere le changement de panneau en fonction
     *de l'avancement de la partie et sur quel bouton l'on clique.
     */
    public Fenetre() {

	super();
	this.partie_en_cours=true;
	this.fichier_choisi="test1.bin";
	this.setTitle("SameGame");
	this.setSize(758,567);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	menu.bouton_quitter.addActionListener(this);
        menu.bouton_alea.addActionListener(this);
        menu.bouton_confirm.addActionListener(this);  
	this.getContentPane().add(this.menu); 
    }

    /**
     *Methode qui gère les differents etats du jeu.
     *
     *@param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
	
    	/* etat=1 on passe du menu a la grille aleatoire en cliquant sur le bouton portant ce nom*/
    	if(e.getSource().equals(menu.bouton_alea)) {  
	    SameGame tmp = new SameGame();
	    this.jeux1= tmp;
	    jeux1.addMouseListener(this);
	    this.getContentPane().remove(this.menu);
	    this.getContentPane().add(jeux1);
	    this.revalidate();	
      
        }

	/*etat=2 on passe du menu a la grille a partir d'un fichier en le choisissant
	  grace a JFileChooser */
	if(e.getSource().equals(menu.bouton_confirm)) {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    if (chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
		File file = chooser.getSelectedFile();
		String s = "" + file;
		this.fichier_choisi=s;
		SameGame tmp_jeux = new SameGame(this.fichier_choisi);
		this.jeux1= tmp_jeux;
		this.jeux1.addMouseListener(this);
		this.getContentPane().remove(this.menu);
		this.getContentPane().add(jeux1);
		this.revalidate();	
	    } 
       
       
        }

	/* etat=3 on ferme la fenetre a partir des boutons quitter */
	if(e.getSource().equals(menu.bouton_quitter) || e.getSource().equals(menu_fin.bouton_quitter)) {
	    this.dispose();
	    this.partie_en_cours=false;
   
    
	}

	/* etat=5 lorsque l'on appuie sur recommencer on enleve le panneau du menu de fin 
	   pour repasser au menu de depart */
	if(e.getSource().equals(menu_fin.bouton_restart)) {
	    	this.setSize(758,567);
            Menu tmp_menu = new Menu();
	    this.menu=tmp_menu;
	    this.getContentPane().remove(this.menu_fin);
	    this.getContentPane().add(this.menu); 
	    menu.bouton_quitter.addActionListener(this);
	    menu.bouton_alea.addActionListener(this);
	    menu.bouton_confirm.addActionListener(this);
	    this.revalidate();
            
        }
    }

    /**
     *Lorsque la partie est terminee on enleve le panneau contenat le jeu pour afficher le menu de fin.
     *
     *@param evenement MouseEvent
     */
    
    public void mouseClicked(MouseEvent evenement) {
	if (jeux1.finDeJeu()) {	 
	    score=jeux1.getScore();
	    	this.setSize(300,200);
	    Menu_Fin tmp_menu_fin = new Menu_Fin("votre score finale : " + this.score);
	     
	    this.menu_fin=tmp_menu_fin;
	    this.menu_fin.bouton_quitter.addActionListener(this);
	    this.menu_fin.bouton_restart.addActionListener(this); 
	    this.getContentPane().remove(jeux1);
	    this.getContentPane().add(this.menu_fin);
	    this.revalidate();   
	}
			       
			   
    }
    // un bouton cliqué
    public void mouseEntered(MouseEvent evenement) {

    }  
    // debut du survol
    public void mouseExited(MouseEvent evenement) {

    }           // fin du survol
    public void mousePressed(MouseEvent evenement) {

    }          // un bouton appuyé
    public void mouseReleased(MouseEvent evenement) {

    }
} 

