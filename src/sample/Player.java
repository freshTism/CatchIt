package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Node;

public class Player extends Circle{

        Circle player;


        public Player() {

                player = new Circle(50, Color.WHITE);
                player.setFill(Color.GRAY);
        } //end constructor

        public void setX(double x) { player.setTranslateX(x); }
        public void setY(double y) { player.setTranslateY(y); }
        public double getX() {return player.getTranslateX(); }
        public double getY() {return player.getTranslateY(); }
        public Node getNode() {return player; }

        public void setColor(Color color) {
                player.setFill(color);
        }




} // end class Player