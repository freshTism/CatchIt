package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Player extends Circle{


        Circle player;

        final double MIN_VELOCITY = 2;
        private double velocity = MIN_VELOCITY;
        final double MAX_VELOCITY = 50;
        double velocityX;
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
        public void setVelocity() {
                velocity = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
                if (velocity > MAX_VELOCITY) {
                        velocityX = MAX_VELOCITY * velocityX / velocity;
                        velocityY = MAX_VELOCITY * velocityY / velocity;
                        velocity = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
                }
        }
        public void setVelocityX(double rotate) { velocityX = velocity * Math.cos(Math.toRadians(rotate)); }
        public void setVelocityY(double rotate) { velocityY = velocity * Math.sin(Math.toRadians(rotate)); }

        public double getX() { return player.getTranslateX(); }
        public double getVelocity() { return velocity; }
        public double getVelocityX() { return velocityX; }
        public double getVelocityY() { return  velocityY; }
        public double getRedius() { return redius; }
        public double getY() { return player.getTranslateY(); }
        public Circle getPlayer() { return player; }


        public void movePlayer() {
                player.setCenterX(player.getCenterX() + velocityX);
                player.setCenterY(player.getCenterY() + velocityY);
        } // end method movePlayerBy



} // end class Player