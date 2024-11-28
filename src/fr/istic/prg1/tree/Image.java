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
        rotate180Aux(it1,it2);
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
            }
            else if (currentNode.state == 0) {
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
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction a ecrire");
        System.out.println("-------------------------------------------------");
        System.out.println();
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
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction a ecrire");
        System.out.println("-------------------------------------------------");
        System.out.println();
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
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction a ecrire");
        System.out.println("-------------------------------------------------");
        System.out.println();
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
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction a ecrire");
        System.out.println("-------------------------------------------------");
        System.out.println();
        return false;
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