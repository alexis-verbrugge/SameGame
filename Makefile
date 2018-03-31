JFLAGS = -g
JC = javac
J = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Boule.java \
        Grille.java \
        Menu.java \
	    Menu_Fin.java \
	    EvenementJeu.java \
	    SameGame.java \
	    Fenetre.java \
        Main.java

default: classes

classes: $(CLASSES:.java=.class)

test: classes
	$(J) Main

clean:
	$(RM) *.class