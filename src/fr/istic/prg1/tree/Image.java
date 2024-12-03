package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree_util.AbstractImage;
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;
import fr.istic.prg1.tree_util.NodeType;

/**
 * @author Melen Laclais <melen.laclais@etudiant.univ-rennes1.fr
 * @author Noah Tombeze <noah.tombeze@etudiant.univ-rennes1.fr
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

        for (int i = 0; i < 2; i++) {
            if (iterator2.getValue() != Node.valueOf(2)) {
                affectAux(iterator1, iterator2);
                return;
            } else {
                iterator2.goLeft();
            }
        }

        affectAux(iterator1, iterator2);
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
        Iterator<Node> it = this.iterator();
        Iterator<Node> it2 = image2.iterator();

        it.clear();

        //On crée la structure de notre arbre qui créera le quart supérieur gauche
        it.addValue(Node.valueOf(2));
        it.goRight();
        it.addValue(Node.valueOf(0));
        it.goUp();
        it.goLeft();
        it.addValue(Node.valueOf(2));
        it.goRight();
        it.addValue(Node.valueOf(0));
        it.goUp();
        it.goLeft();

        //On copie l'image dans le quart supérieur gauche
        affectAux(it, it2);
        it.goRoot();

        compress(it, 0);
    }

    private void compress(Iterator<Node> it, int profondeur) {
        if (it.getValue() == Node.valueOf(2)) {
            if (profondeur <= 15) {
                it.goLeft();
                compress(it, profondeur + 1);
                Node gauche = it.getValue();
                it.goUp();

                it.goRight();
                compress(it, profondeur + 1);
                Node droit = it.getValue();
                it.goUp();

                //On supprime les doublons
                if((gauche.state == 0 && droit.state == 0) || (gauche.state == 1 && droit.state == 1)){
                    it.clear();
                    it.addValue(gauche);
                }
            } else {
                //On arrive au niveau les plus détaillés qui nécessitent une compression, on compte donc les pixels majoritaires : Étends ou allumés ?
                int count0 = occurrences(it, Node.valueOf(0));
                int count1 = occurrences(it, Node.valueOf(1));

                it.clear();
                it.addValue(count1 >= count0 ? Node.valueOf(1) : Node.valueOf(0));
            }
        }
    }

    private int occurrences(Iterator<Node> it, Node valeur) {
        if (it.getValue() == Node.valueOf(2)) {
            it.goLeft();
            int compteurGauche = occurrences(it, valeur);
            it.goUp();

            it.goRight();
            int compteurDroit = occurrences(it, valeur);
            it.goUp();

            return compteurGauche + compteurDroit;
        }

        return it.getValue() == valeur ? 1 : 0;
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
        Iterator<Node> it = this.iterator();
        Iterator<Node> it1 = image1.iterator();
        Iterator<Node> it2 = image2.iterator();

        it.clear();
        intersectionAux(it, it1, it2);

    }

    private void intersectionAux(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {

        if(it1.getValue().state == 0 || it2.getValue().state == 0) {
            it.addValue(Node.valueOf(0));
        }

        else if(it1.getValue().state == 2 && it2.getValue().state == 1) {
            affectAux(it,it1);
        }

        else if(it2.getValue().state == 2 && it1.getValue().state == 1) {
            affectAux(it,it2);
        }

        else if(it1.getValue().state == 1 && it2.getValue().state == 1) {
            it.addValue(Node.valueOf(1));
        }

        else {
            it.addValue(Node.valueOf(2));
            it.goLeft(); it1.goLeft(); it2.goLeft();
            intersectionAux(it,it1,it2);
            Node gauche = it.getValue();
            it.goUp(); it1.goUp(); it2.goUp();
            it.goRight(); it1.goRight(); it2.goRight();
            intersectionAux(it,it1,it2);
            Node droite = it.getValue();
            it.goUp(); it1.goUp(); it2.goUp();

            if(gauche.state == 0 && droite.state == 0) {
                it.clear();
                it.addValue(gauche);
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

    }

    private void unionAux(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {

        if(it1.getValue().state == 1 || it2.getValue().state == 1) {
            it.addValue(Node.valueOf(1));
        }

        else if(it1.getValue().state == 2 && it2.getValue().state == 0) {
            affectAux(it,it1);
        }

        else if(it1.getValue().state == 0 && it2.getValue().state == 2) {
            affectAux(it,it2);
        }

        else if(it1.getValue().state == 0 && it2.getValue().state == 0) {
            it.addValue(Node.valueOf(0));
        }

        else {
            it.addValue(Node.valueOf(2));
            it.goLeft(); it1.goLeft(); it2.goLeft();
            unionAux(it,it1,it2);
            Node gauche = it.getValue();
            it.goUp(); it1.goUp(); it2.goUp();
            it.goRight(); it1.goRight(); it2.goRight();
            unionAux(it,it1,it2);
            Node droite = it.getValue();
            it.goUp(); it1.goUp(); it2.goUp();

            if(gauche.state == 1 && droite.state == 1) {
                it.clear();
                it.addValue(gauche);
            }
        }
    }


    /**
     * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
     *
     * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
     * sont allumés dans this, false sinon
     */
    @Override
    public boolean testDiagonal() {
        Iterator<Node> it = this.iterator();
        return testDiagonalAux(it);
    }

    private boolean testDiagonalAux(Iterator<Node> it) {
        if(it.getValue() == Node.valueOf(2)) {
            boolean gauche;
            boolean droit;

            it.goLeft();
            if(it.getValue() == Node.valueOf(2)) {
                it.goLeft();
                gauche = testDiagonalAux(it);
                it.goUp();
            }
            else gauche = (it.getValue() == Node.valueOf(1));
            it.goUp();

            it.goRight();
            if(gauche && it.getValue() == Node.valueOf(2)) {
                it.goRight();
                droit = testDiagonalAux(it);
                it.goUp();
            }
            else droit = (it.getValue() == Node.valueOf(1));
            it.goUp();
            return gauche && droit;
        }
        else return it.getValue() == Node.valueOf(1);
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
        int xLower = 0;
        int xUpper = 256; // Limites pour x
        int yLower = 0;
        int yUpper = 256; // Limites pour y

        // Parcours de l'arbre jusqu'à atteindre une feuille
        while (it.nodeType() != NodeType.LEAF) {

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
        Iterator<Node> it = this.iterator();
        boolean horizontal = true;
        int xMin = 0;
        int xMax = 256;
        int yMin = 0;
        int yMax = 256;

        while (it.nodeType() != NodeType.LEAF) {
            int split = horizontal ? (yMin + yMax) / 2 : (xMin + xMax) / 2;

            // Appel de la fonction auxiliaire pour vérifier les positions des points
            int direction = getDirection(horizontal, x1, y1, x2, y2, split);

            if (direction == -1) {
                it.goLeft();
                if (horizontal) yMax = split; else xMax = split;
            } else if (direction == 1) {
                it.goRight();
                if (horizontal) yMin = split; else xMin = split;
            } else {
                return false; // Les points ne sont pas dans la même sous-région
            }

            horizontal = !horizontal; // Alterner coupure
        }
        return true; // Les points sont dans la même feuille
    }

    /**
     * Fonction auxiliaire pour déterminer la direction.
     *
     * @param horizontal indique si la coupure est horizontale
     * @param x1 abscisse du premier point
     * @param y1 ordonnée du premier point
     * @param x2 abscisse du deuxième point
     * @param y2 ordonnée du deuxième point
     * @param split position de la coupure
     * @return -1 si les deux points sont à gauche, 1 s'ils sont à droite, 0 s'ils sont dans des sous-régions différentes
     */
    private int getDirection(boolean horizontal, int x1, int y1, int x2, int y2, int split) {
        if (horizontal) {
            if (y1 < split && y2 < split) return -1; // Aller à gauche
            if (y1 >= split && y2 >= split) return 1; // Aller à droite
        } else {
            if (x1 < split && x2 < split) return -1; // Aller à gauche
            if (x1 >= split && x2 >= split) return 1; // Aller à droite
        }
        return 0; // Points dans des sous-régions différentes
    }

    /**
     * @param image2 autre image
     * @return true si this est incluse dans image2 au sens des pixels allumés false
     * sinon
     * @pre !this.isEmpty() && !image2.isEmpty()
     */
    @Override
    public boolean isIncludedIn(AbstractImage image2) {
        boolean inclus = true;
        int x = 0;

        while (x < 256 && inclus) {
            int y = 0;
            while (y < 256 && inclus) {
                if (this.isPixelOn(x, y)) {
                    inclus = image2.isPixelOn(x, y);
                }
                y++;
            }
            x++;
        }

        return inclus;
    }
}