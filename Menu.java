import javax.swing.*;
import java.awt.*;

/**
 *La classe <code>Menu</code> est utilise pour le menu de debut du jeu.
 *
 *@version 0.1
 *@author Alexis Verbrugge et Chahine Boutaljante
 */
public class Menu extends JPanel {
	   
    public JTextField jtf = new JTextField("test1.bin");

    public JButton bouton_confirm = new JButton("Grille à partir d'un fichier");
    public JButton bouton_alea = new JButton("Grille Aleatoire");
    public  JButton bouton_quitter = new JButton("Quitter");
    private String fichier;

    /**
     *Constructeur qui permet de creer un menu.
     */
    public Menu() {
        this.fichier="";
        this.bouton_alea.setFont(new Font("Helvetica", Font.BOLD, 20));
        this.add(bouton_alea);
        this.bouton_confirm.setFont(new Font("Helvetica", Font.BOLD, 20));
        this.add(bouton_confirm);
        this.bouton_quitter.setFont(new Font("Helvetica", Font.BOLD, 20));
        this.add(bouton_quitter);
    }

    /**
     *Redefinition de la methode paintComponent pour la partie graphique du menu
     *
     *@param g Graphics
     */
    @Override 
    public void paintComponent (Graphics g) {
	g.setColor(new Color(233,201,177)); 	
	g.fillRect(0,0,getWidth(),getHeight());
	g.setColor(new Color(43,0,154));
	g.setFont(new Font("Helvetica", Font.PLAIN, 75));
	g.drawString("Same Game",getWidth()/5,getHeight()/4);
	
    }

    /**
     *Rentre le fichier voulu pour la partie.
     *
     *@param s fichier
     */
    public void setFichier(String s) {
        this.fichier=s;
    }

    /**
     *Renvoie le fichie voulu.
     *
     *@return fichier voulu
     */
    public String getFichier() {
        return this.fichier;
    }

}
