package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Node;

public class Player extends Circle{


        Circle player;

        final double MIN_VELOCITY = 2;
        private double velocity = MIN_VELOCITY;
        final double MAX_VELOCITY = 50;
        double velosityX;
        double velocityY;

        final double MIN_RADIUS = 50;
        private double redius = MIN_RADIUS;
        final double MAX_RADIUS = 75;


        public Player() {

                player = new Circle(MIN_RADIUS, Color.WHITE);
                player.setFill(Color.GRAY);
        } //end constructor




        public void setX(double x) { player.setTranslateX(x); }
        public void setY(double y) { player.setTranslateY(y); }
        public void setVelocity(double factor) { velocity *= factor; }
        public void setVelosityX(double factor) { velosityX *= factor; }
        public void setVelocityY(double factor) { velocityY *= factor; }

        public double getX() { return player.getTranslateX(); }
        public double getVelocity() { return velocity; }
        public double getVelocityX(double rotate) { return getVelocity() * Math.cos(Math.toRadians(rotate)); }
        public double getVelocityY(double rotate) { return getVelocity() * Math.sin(Math.toRadians(rotate)); }
        public double getRedius() { return redius; }
        public double getY() { return player.getTranslateY(); }
        public Circle getPlayer() { return player; }





} // end class Player