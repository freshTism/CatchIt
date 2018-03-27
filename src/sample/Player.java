package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Node;

public class Player {

        Circle player;


        public Player() {

                player = new Circle(50, Color.WHITE);
                player.setFill(Color.BLACK);
        } //end constructor

        public void setX(double x) { player.setTranslateX(x); }
        public void setY(double y) { player.setTranslateY(y); }
        public double getX() {return player.getTranslateX(); }
        public double getY() {return player.getTranslateY(); }
        public Node getNode() {return player; }



} // end class Player