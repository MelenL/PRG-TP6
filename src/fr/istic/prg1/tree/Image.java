package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree_util.AbstractImage;
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;
import fr.istic.prg1.tree_util.NodeType;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2023-09-23
 * <p>
 * Classe décrivant les images en noir et blanc de 256 sur 256 pixels
 * sous forme d'arbres binaires.
 */

public class Image extends AbstractImage {
    private static final Scanner standardInput = new Scanner(System.in);

    public Image() {
        super();
    }

    public static void closeAll() {
        standardInput.close();
    }

    /**
     * this devient identique à image2.
     *
     * @param image2 image à copier
     * @pre !image2.isEmpty()
     */
    @Override
    public void affect(AbstractImage image2) {
        Iterator<Node> it1 = this.iterator();// Il devrait manquer une ligne sinon erreur
        Iterator<Node> it2 = image2.iterator();
        it1.clear();
        affectAux(it1, it2);
    }

    private void affectAux(Iterator<Node> it1, Iterator<Node> it2) {
        Node node = it2.getValue();
        it1.addValue(node);

        if (it2.getValue().state == 2) {
            it1.goLeft();
            it2.goLeft();
            affectAux(it1, it2);
            it1.goUp();
            it2.goUp();
            it1.goRight();
            it2.goRight();
            affectAux(it1, it2);
            it1.goUp();
            it2.goUp();
        }
    }

    /**
     * this devient rotation de image2 à 180 degrés.
     *
     * @param image2 image pour rotation
     * @pre !image2.isEmpty()
     */
    @Override
    public void rotate180(AbstractImage image2) {
        Iterator<Node> it1 = this.iterator();
        Iterator<Node> it2 = image2.iterator();
        it1.clear();
        rotate180Aux(it1, it2);
    }

    private void rotate180Aux(Iterator<Node> it1, Iterator<Node> it2) {
        it1.addValue(it2.getValue());

        if (it2.getValue().state == 2) {
            it1.goRight();
            it2.goLeft();
            rotate180Aux(it1, it2);
            it1.goUp();
            it2.goUp();
            it1.goLeft();
            it2.goRight();
            rotate180Aux(it1, it2);
            it1.goUp();
            it2.goUp();
        }
    }

    /**
     * this devient inverse vidéo de this, pixel par pixel.
     *
     * @pre !image.isEmpty()
     */
    @Override
    public void videoInverse() {
        Iterator<Node> it = this.iterator();
        videoInverseAux(it);
    }

    private void videoInverseAux(Iterator<Node> it) {
        Node currentNode = it.getValue();
        if (currentNode.state == 1) {
            it.setValue(Node.valueOf(0));
        } else if (currentNode.state == 0) {
            it.setValue(Node.valueOf(1));
        } else {
            it.goLeft();
            videoInverseAux(it);
            it.goUp();
            it.goRight();
            videoInverseAux(it);
            it.goUp();
        }
    }

    /**
     * this devient image miroir verticale de image2.
     *
     * @param image2 image à agrandir
     * @pre !image2.isEmpty()
     */
    @Override
    public void mirrorV(AbstractImage image2) {
        Iterator<Node> it1 = this.iterator();
        Iterator<Node> it2 = image2.iterator();
        it1.clear();
        mirrorVAux(it1, it2, 0);
    }

    private void mirrorVAux(Iterator<Node> it1, Iterator<Node> it2, int hauteur) {
        it1.addValue(it2.getValue());

        if (it2.getValue().state == 2) {
            if (hauteur % 2 == 0) {
                it1.goRight();
                it2.goLeft();
                mirrorVAux(it1, it2, hauteur + 1);
                it1.goUp();
                it2.goUp();

                it1.goLeft();
                it2.goRight();
                mirrorVAux(it1, it2, hauteur + 1);
                it1.goUp();
                it2.goUp();
            } else {
                it1.goLeft();
                it2.goLeft();
                mirrorVAux(it1, it2, hauteur + 1);
                it1.goUp();
                it2.goUp();

                it1.goRight();
                it2.goRight();
                mirrorVAux(it1, it2, hauteur + 1);
                it1.goUp();
                it2.goUp();
            }
        }
    }


    /**
     * this devient image miroir horizontale de image2.
     *
     * @param image2 image à agrandir
     * @pre !image2.isEmpty()
     */
    @Override
    public void mirrorH(AbstractImage image2) {
        Iterator<Node> it1 = this.iterator();
        Iterator<Node> it2 = image2.iterator();
        it1.clear();
        mirrorHAux(it1, it2, 0);
    }

    private void mirrorHAux(Iterator<Node> it1, Iterator<Node> it2, int hauteur) {
        it1.addValue(it2.getValue());

        if (it2.getValue().state == 2) {
            if (hauteur % 2 != 0) {
                it1.goRight();
                it2.goLeft();
                mirrorHAux(it1, it2, hauteur + 1);
                it1.goUp();
                it2.goUp();

                it1.goLeft();
                it2.goRight();
                mirrorHAux(it1, it2, hauteur + 1);
                it1.goUp();
                it2.goUp();
            } else {
                it1.goLeft();
                it2.goLeft();
                mirrorHAux(it1, it2, hauteur + 1);
                it1.goUp();
                it2.goUp();

                it1.goRight();
                it2.goRight();
                mirrorHAux(it1, it2, hauteur + 1);
                it1.goUp();
                it2.goUp();
            }
        }
    }

    /**
     * this devient quart supérieur gauche de image2.
     *
     * @param image2 image à agrandir
     * @pre !image2.isEmpty()
     */
    @Override
    public void zoomIn(AbstractImage image2) {
        Iterator<Node> iterator1 = this.iterator();
        Iterator<Node> iterator2 = image2.iterator();

        iterator1.clear();

        for (int i = 0; i < 2; i++) { //Au bout de 2 fois, on est à présent sûr d'être dans le quart supérieur gauche.
            //Il faudra toujours deux descentes à gauche pour y parvenir.
            if (iterator2.getValue() != Node.valueOf(2)) { //Car dans ce cas, l'image est tout éteinte ou tout allumé
                affectAux(iterator1, iterator2); //Donc le quart sera la même chose.
                return; //VAUX T'IL MIEUX UTILISER UN BOOLEAN FLAG ET DONC UNE BOUCLE WHILE ?
            } else {
                iterator2.goLeft(); //On va donc vers le quart supérieur gauche
            }
        }

        affectAux(iterator1, iterator2); //Donc on copie.
    }


    /**
     * Le quart supérieur gauche de this devient image2, le reste de this devient
     * éteint.
     *
     * @param image2 image à réduire
     * @pre !image2.isEmpty()
     */
    @Override
    public void zoomOut(AbstractImage image2) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction a ecrire");
        System.out.println("-------------------------------------------------");
        System.out.println();
    }

    /**
     * this devient l'intersection de image1 et image2 au sens des pixels allumés.
     *
     * @param image1 premiere image
     * @param image2 seconde image
     * @pre !image1.isEmpty() && !image2.isEmpty()
     */
    @Override
    public void intersection(AbstractImage image1, AbstractImage image2) {
        Iterator<Node> it = this.iterator(); // Itérateur pour construire le résultat
        Iterator<Node> it2 = image1.iterator(); // Itérateur pour le premier arbre
        Iterator<Node> it3 = image2.iterator(); // Itérateur pour le deuxième arbre

        it.clear(); // Réinitialiser l'arbre cible
        intersectionAux(it, it2, it3); // Construction de l'arbre résultat
        simplify(it); // Simplification de l'arbre
    }

    private void intersectionAux(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {
        Node node1 = it1.getValue();
        Node node2 = it2.getValue();


        if (node1 == Node.valueOf(0) || node2 == Node.valueOf(0)) {
            it.addValue(Node.valueOf(0));
        } else if (node1 == Node.valueOf(2) || node2 == Node.valueOf(2)) {
            it.addValue(Node.valueOf(2));

            it.goLeft();
            if (node1 != null)
                it1.goLeft();
            if (node2 != null)
                it2.goLeft();
            intersectionAux(it, it1, it2);

            it.goUp();
            if (node1 != null)
                it1.goUp();
            if (node2 != null)
                it2.goUp();

            it.goRight();
            if (node1 != null)
                it1.goRight();
            if (node2 != null)
                it2.goRight();
            intersectionAux(it, it1, it2);

            it.goUp();
            if (node1 != null)
                it1.goUp();
            if (node2 != null)
                it2.goUp();
        } else {
            it.addValue(Node.valueOf(1));
        }
    }

    private void simplify(Iterator<Node> it) {
        Node currentNode = it.getValue();

        if (currentNode == Node.valueOf(2)) { // On vérifie uniquement les nœuds mixtes
            it.goLeft();
            simplify(it);
            Node leftValue = it.getValue(); // Récupérer la valeur simplifiée du fils gauche (0 ou 1)
            it.goUp();

            it.goRight();
            simplify(it);
            Node rightValue = it.getValue();
            it.goUp();

            // Si les deux fils sont identiques, on remplace le nœud courant
            if (leftValue.equals(rightValue) && leftValue != Node.valueOf(2)) {
                it.clear();
                it.addValue(leftValue);
            }
        }
    }



    /**
     * this devient l'union de image1 et image2 au sens des pixels allumés.
     *
     * @param image1 premiere image
     * @param image2 seconde image
     * @pre !image1.isEmpty() && !image2.isEmpty()
     */
    @Override
    public void union(AbstractImage image1, AbstractImage image2) {
        Iterator<Node> it = this.iterator();
        Iterator<Node> it1 = image1.iterator();
        Iterator<Node> it2 = image2.iterator();

        it.clear();
        unionAux(it, it1, it2);

        simplify(it);
    }

    private void unionAux(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {
        Node node1 = it1.getValue();
        Node node2 = it2.getValue();


        if (node1 == Node.valueOf(1) || node2 == Node.valueOf(1)) {
            it.addValue(Node.valueOf(1));
        } else if (node1 == Node.valueOf(2) || node2 == Node.valueOf(2)) {
            it.addValue(Node.valueOf(2));

            it.goLeft();
            if (node1 != null)
                it1.goLeft();
            if (node2 != null)
                it2.goLeft();
            unionAux(it, it1, it2);

            it.goUp();
            if (node1 != null)
                it1.goUp();
            if (node2 != null)
                it2.goUp();

            it.goRight();
            if (node1 != null)
                it1.goRight();
            if (node2 != null)
                it2.goRight();
            unionAux(it, it1, it2);

            it.goUp();
            if (node1 != null)
                it1.goUp();
            if (node2 != null)
                it2.goUp();
        } else {
            it.addValue(Node.valueOf(0));
        }
    }


    /**
     * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
     *
     * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
     * sont allumés dans this, false sinon
     */
    @Override
    public boolean testDiagonal() {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction a ecrire");
        System.out.println("-------------------------------------------------");
        System.out.println();
        return false;
    }

    /**
     * @param x abscisse du point
     * @param y ordonnée du point
     * @return true, si le point (x, y) est allumé dans this, false sinon
     * @pre !this.isEmpty()
     */
    @Override
    public boolean isPixelOn(int x, int y) {
        // Vérification des coordonnées valides
        if (x < 0 || y < 0 || x >= 256 || y >= 256) return false;

        // Initialisation des bornes et de l'itérateur
        Iterator<Node> it = this.iterator();
        boolean horizontal = true; // Indique si la coupure est horizontale
        int xLower = 0, xUpper = 256; // Limites pour x
        int yLower = 0, yUpper = 256; // Limites pour y

        // Parcours de l'arbre jusqu'à atteindre une feuille
        while (it.nodeType() != NodeType.LEAF) {
            Node currentNode = it.getValue();

            if (horizontal) { // Coupure horizontale
                int split = (yLower + yUpper) / 2; // Position de coupure
                if (y < split) { 
                    it.goLeft();
                    yUpper = split; // Mettre à jour la limite supérieure
                } else { // Pixel en bas
                    it.goRight();
                    yLower = split; // Mettre à jour la limite inférieure
                }
            } else { // Coupure verticale
                int split = (xLower + xUpper) / 2; // Position de coupure
                if (x < split) { // Pixel à gauche
                    it.goLeft();
                    xUpper = split; // Mettre à jour la limite supérieure
                } else { // Pixel à droite
                    it.goRight();
                    xLower = split; // Mettre à jour la limite inférieure
                }
            }
            horizontal = !horizontal;
        }
        return it.getValue().state == 1; 
    } 
    

    /**
     * @param x1 abscisse du premier point
     * @param y1 ordonnée du premier point
     * @param x2 abscisse du deuxième point
     * @param y2 ordonnée du deuxième point
     * @return true si les deux points (x1, y1) et (x2, y2) sont représentés par la
     * même feuille de this, false sinon
     * @pre !this.isEmpty()
     */
    @Override
    public boolean sameLeaf(int x1, int y1, int x2, int y2) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction a ecrire");
        System.out.println("-------------------------------------------------");
        System.out.println();
        return false;
    }

    /**
     * @param image2 autre image
     * @return true si this est incluse dans image2 au sens des pixels allumés false
     * sinon
     * @pre !this.isEmpty() && !image2.isEmpty()
     */
    @Override
    public boolean isIncludedIn(AbstractImage image2) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction a ecrire");
        System.out.println("-------------------------------------------------");
        System.out.println();
        return false;
    }
}
