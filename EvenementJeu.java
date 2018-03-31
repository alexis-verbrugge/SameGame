import java.awt.event.*;

/**
 *La classe <code>EvenementJeu<code> gère tous les evenements qui surviennent
 *au cours d'une partie.
 *
 *@version 0.1
 *@author Chahine Boutaljante et Alexis Verbrugge
 */
public class EvenementJeu implements MouseMotionListener, MouseListener{

    /**
     *Methode qui utilise plusieurs methodes afin de s'assurer du decalage des
     *boules et de la chute des boules.
     *
     *@param evenement MouseEvent
     */
    public void mouseClicked(MouseEvent evenement) {
	SameGame s = (SameGame) evenement.getSource();
	int i,j,k;
	int cmp=0;
	int couleur=0;
	Boule boule[][] = s.getTab_boule();
     
	for (i=0; i<10; i++) {
	    for (j=0; j<15; j++) {
		s.setDecision(0);
		if (evenement.getX() >= boule[i][j].getXBoule() && evenement.getX() <boule[i][j].getXBoule()+50 &&
		    evenement.getY() >= boule[i][j].getYBoule() && evenement.getY() < boule[i][j].getYBoule()+50 ) {
		    s.remplirTab_etat(); 
		    s.setDecision(0);
		    s.groupeDeBouleSurbrillanceEtEnleve(i,j);
		    if (s.getCaractere(i,j)!=' ' && s.compteGroupe()>1) {
			s.remplirTab_etat();
			s.setDecision(1);
			s.groupeDeBouleSurbrillanceEtEnleve(i,j);
			s.score(s.compteGroupe());
			s.high_score();
			s.remplirTab_etat();    
			s.chuteDeBoule();
			s.decaleBouleGauche();        
			s.repaint();
			s.setDecision(0);
			if (s.finDeJeu()) {
			    s.setEtat(4);     
			}
           
		    }

      
          
		}
	    }
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
    
    public void mouseDragged(MouseEvent evenement) {

    }      
    /** 
     *Methode qui utilise plusieurs méthodes afin d'assurer la surbrillance des
     *groupes et leur reinitialisation lorsqu'on arrete d'en survoler un.
     *
     *@param evenement MouseEvent
     */
    public void mouseMoved(MouseEvent evenement) {
	SameGame s = (SameGame) evenement.getSource();
	int i,j;
	Boule boule[][] = s.getTab_boule();
	s.reintialiseTableau();
      
	for (i=0; i<10; i++) {
	    for (j=0; j<15; j++) {

		if (evenement.getX() >= boule[i][j].getXBoule() && evenement.getX() <boule[i][j].getXBoule()+50 &&
		    evenement.getY() >= boule[i][j].getYBoule() && evenement.getY() < boule[i][j].getYBoule()+50 ) {
		    s.remplirTab_etat(); 
		    s.setDecision(0);
		    s.groupeDeBouleSurbrillanceEtEnleve(i,j);
		    if (s.getCaractere(i,j)!=' ' && s.compteGroupe()>1) {
			s.remplirTab_etat();
			s.setDecision(2);    
			s.groupeDeBouleSurbrillanceEtEnleve (i,j);
			s.remplirTab_etat();
			s.repaint();
		    }
		    else {
			s.remplirTab_etat();
			s.reintialiseTableau();
			s.repaint();
		    }
		}
	    }
	} 
    }
}
