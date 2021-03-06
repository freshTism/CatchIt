package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Player extends Circle{


        Circle player;

        final double MIN_VELOCITY = 2;
        private double velocity = MIN_VELOCITY;
        final double MAX_VELOCITY = 25;
        double velocityX;
        double velocityY;

        final double MIN_RADIUS = 50;
        private double redius = MIN_RADIUS;
        final double MAX_RADIUS = 75;

        private Color color = Color.GRAY;


        public Player() {
                player = new Circle(MIN_RADIUS);
                player.setFill(color);
        } //end constructor



        public void setX(double x) { player.setCenterX(x); }
        public void setY(double y) { player.setCenterY(y); }
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
        public void setColor(Color newColor) {
                color = newColor;
                player.setFill(color);
        }

        public double getX() { return player.getCenterX(); }
        public double getY() { return player.getCenterY(); }
        public double getVelocity() { return velocity; }
        public double getVelocityX() { return velocityX; }
        public double getVelocityY() { return  velocityY; }
        public double getCurrentRedius() { return player.getRadius(); }
        public Circle getPlayer() { return player; }
        public Color getColor() { return color; }

        public void movePlayer() {
                player.setCenterX(player.getCenterX() + velocityX);
                player.setCenterY(player.getCenterY() + velocityY);
        } // end method movePlayerBy



} // end class Player