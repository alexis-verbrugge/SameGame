import javax.swing.*;
import java.awt.*;

/**
 *La classe <code>Menu_Fin</code> est utilise pour creer un menu de fin de jeu.
 *
 *@version 0.1
 *@author Chahine Boutaljante et Alexis Verbrugge
 */
public class Menu_Fin extends JPanel {
	   
    public JButton bouton_restart = new JButton("Recommencer");
    public  JButton bouton_quitter = new JButton("Quitter");

    /**
     *Constructeur qui cree le menu de fin.
     *
     *@param s score
     */
    public Menu_Fin(String s) {
	JLabel score_final = new JLabel(s);
	GridLayout gl = new GridLayout(2, 2);
	this.add(bouton_restart);   
	this.add(bouton_quitter);
	this.add(score_final);
    }
}

