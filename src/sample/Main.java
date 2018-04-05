package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Main extends Application {

        final double WIDTH = 1000;
        final double HEIGHT = 500;
        final double RECTANGLES_WIDTH = 7;
        final double RED_BLUE_LENTH = 150;
        final double YELLOW_GREEN_LETH = 200;
        Player player = new Player();;
        Integer score = 0;
        Text showScore;
        Timeline stopPlay;
        double rotate;
        FadeTransition fadeAtTheEndOfGame;
        Text showFinalScore;
        enum AllowedColors {blue, yellow, green, red, purple, gray, black}


        AnimationTimer play = new AnimationTimer() {
                boolean isFirstCall = true;
                long previousT;

                @Override
                public void handle(long now) {

                        if (isFirstCall) {
                                rotate = getRandomRotate();
                                player.setVelocityX(rotate);
                                player.setVelocityY(rotate);
                                player.setVelocity();
                                setScore();
                                showScore.setText("Score : " + score.toString());
                                player.movePlayer();
                                isFirstCall = false;
                        } else {
                                nextFrame();
                        }
                }
        };


        @Override
        public void start(Stage primaryStage) throws Exception{


                Group root = new Group();
                final Scene scene = new Scene(root, WIDTH, HEIGHT, Color.POWDERBLUE);

                primaryStage.setTitle("Catch It");

                Canvas canvas = new Canvas(WIDTH, HEIGHT);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                root.getChildren().add(canvas);

                createPlayground(gc, WIDTH, HEIGHT);

                showScore = new Text(40, 460, "Score : " + score.toString());
                showScore.setFont(Font.loadFont("file:resources/fonts/COOPBL.TTF", 30));
                showScore.setFill(Color.BLACK);
                root.getChildren().add(showScore);


                player.setX(WIDTH / 2);
                player.setY(HEIGHT / 2);
                root.getChildren().add(player.getPlayer());


                stopPlay = new Timeline();
                KeyFrame stopAnimationTimer = new KeyFrame(Duration.minutes(2), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                play.stop();
                        }
                });
                stopPlay.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                fadeAtTheEndOfGame = new FadeTransition(Duration.millis(1000), canvas);
                                fadeAtTheEndOfGame.setFromValue(1.5);
                                fadeAtTheEndOfGame.setToValue(0.3);
                                fadeAtTheEndOfGame.play();

                                fadeAtTheEndOfGame = new FadeTransition(Duration.millis(1000), player.getPlayer());
                                fadeAtTheEndOfGame.setFromValue(1.5);
                                fadeAtTheEndOfGame.setToValue(0.3);
                                fadeAtTheEndOfGame.play();

                                showFinalScore = new Text(WIDTH / 2 - 230, HEIGHT / 2, "Your Score : " + score.toString());
                                showFinalScore.setFont(Font.loadFont("file:resources/fonts/COOPBL.TTF", 60));
                                showFinalScore.setFill(Color.BLACK);
                                root.getChildren().removeAll(showScore, player.getPlayer());
                                root.getChildren().add(showFinalScore);
                        }
                });
                stopPlay.getKeyFrames().add(stopAnimationTimer);


                player.getPlayer().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                                play.start();
                                stopPlay.play();
                        }
                });

                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.sizeToScene();
                primaryStage.show();

        } // end method start(Stage)


        private void createPlayground(GraphicsContext gc, double width, double height) {

                gc.setFill(Color.BLACK);
                gc.setLineWidth(5);
                gc.strokeLine(0, 0, width, 0);
                gc.strokeLine(0, height, width, height);
                gc.strokeLine(0, 0, 0, height);
                gc.strokeLine(width, 0, width, height);

                gc.setFill(Color.RED);
                gc.fillRect(width - RECTANGLES_WIDTH, (height - RED_BLUE_LENTH) / 2, RECTANGLES_WIDTH, RED_BLUE_LENTH);
                gc.setFill(Color.BLUE);
                gc.fillRect(0, (height - RED_BLUE_LENTH) / 2, RECTANGLES_WIDTH, RED_BLUE_LENTH);
                gc.setFill(Color.YELLOW);
                gc.fillRect((width - YELLOW_GREEN_LETH) / 2, 0, YELLOW_GREEN_LETH, RECTANGLES_WIDTH);
                gc.setFill(Color.GREEN);
                gc.fillRect((width - YELLOW_GREEN_LETH) / 2, height - RECTANGLES_WIDTH, YELLOW_GREEN_LETH, RECTANGLES_WIDTH);

        } // end method createPlayground


        private double getRandomRotate() {

                Random randomRotate = new Random();
                return randomRotate.nextDouble() * 360;

        } // end method getRandomRotate


        private void setRandomColor(Color currentColor) {

                Random randomColor = new Random();
                Color resultColor = null;

                AllowedColors color;
                color = AllowedColors.values()[randomColor.nextInt(7)];

                switch (color) {
                        case blue:
                                resultColor = Color.BLUE;
                                break;
                        case yellow:
                                resultColor = Color.YELLOW;
                                break;
                        case green:
                                resultColor = Color.GREEN;
                                break;
                        case red:
                                resultColor = Color.RED;
                                break;
                        case purple:
                                resultColor = Color.PURPLE;
                                break;
                        case gray:
                                resultColor = Color.GRAY;
                                break;
                        case black:
                                resultColor = Color.BLACK;
                                break;
                }

                if (resultColor.equals(currentColor)) {
                        setRandomColor(currentColor);
                }

                player.setColor(resultColor);
        } // end method getRandomColor


        private void setScore() {
                score = score + (int) (player.getVelocity() / 10 + 10 * player.getVelocity());
        }


        public void nextFrame() {

                // Collision with entire the up border
                if(player.getY() <= player.getCurrentRedius()
                        // Except (up left corner
                        && (player.getY() <= player.getCurrentRedius()
                                || player.getX() <= player.getCurrentRedius())
                        // & up right corner)
                        && (player.getY() <= player.getCurrentRedius()
                                || player.getX() >= WIDTH - player.getCurrentRedius())) {

                        rotate = 360 - rotate;

                // Collision with entire the down border
                } else if(player.getY() >= HEIGHT - player.getCurrentRedius()
                        // Except (down left corner
                        && (player.getY() >= HEIGHT - player.getCurrentRedius()
                                || player.getX() <= player.getCurrentRedius())
                        // & down right corner)
                        && (player.getY() >= HEIGHT - player.getCurrentRedius()
                                || player.getX() >= WIDTH - player.getCurrentRedius())) {

                        rotate = 360 - rotate;

                // Collision with entire the left border
                } else if(player.getX() <= player.getCurrentRedius()
                        // Except (up left corner
                        && (player.getY() <= player.getCurrentRedius()
                                || player.getX() <= player.getCurrentRedius())
                        // & down left corner)
                        && (player.getY() >= HEIGHT - player.getCurrentRedius()
                                || player.getX() <= player.getCurrentRedius())) {

                        rotate = 180 - rotate;

                // Collision with entire right border
                } else if(player.getX() >= WIDTH - player.getCurrentRedius()
                        // Except (up right corner
                        && (player.getY() <= player.getCurrentRedius()
                                || player.getX() >= WIDTH - player.getCurrentRedius())
                        // & down right corner)
                        && (player.getY() >= HEIGHT - player.getCurrentRedius()
                                || player.getX() >= WIDTH - player.getCurrentRedius())) {

                        rotate = 180 - rotate;

                }


                // Collision with up left corner
                if(player.getY() <= player.getCurrentRedius()
                        && player.getX() <= player.getCurrentRedius()) {

                        rotate = Math.toDegrees(Math.atan2(HEIGHT, WIDTH));

                // Collision with up right corner
                } else if(player.getY() <= player.getCurrentRedius()
                        && player.getX() >= WIDTH - player.getCurrentRedius()) {

                        rotate = 180 - Math.toDegrees(Math.atan2(HEIGHT, WIDTH));

                // Collision with down left corner
                } else if(player.getY() >= HEIGHT - player.getCurrentRedius()
                        && player.getX() <= player.getCurrentRedius()) {

                        rotate = 360 - Math.toDegrees(Math.atan2(HEIGHT, WIDTH));

                // Collision with down right corner
                } else if(player.getY() >= HEIGHT - player.getCurrentRedius()
                        && player.getX() >= WIDTH - player.getCurrentRedius()) {

                        rotate = 180 + Math.toDegrees(Math.atan2(HEIGHT, WIDTH));

                }


                // Collision with yellow border
                if(player.getX() >= (WIDTH - YELLOW_GREEN_LETH) / 2
                        && player.getX() <= WIDTH - (WIDTH - YELLOW_GREEN_LETH) / 2
                        && player.getY() <= player.getCurrentRedius()) {

                        do {
                                rotate = getRandomRotate();
                        } while (rotate >= 180 && rotate <= 360);

                // Collision with green border
                } else if(player.getX() >= (WIDTH - YELLOW_GREEN_LETH) / 2
                        && player.getX() <= WIDTH - (WIDTH - YELLOW_GREEN_LETH) / 2
                        && player.getY() >= HEIGHT - player.getCurrentRedius()) {

                        setRandomColor(player.getColor());

                // Collision with blue border
                } else if(player.getY() >= (HEIGHT - RED_BLUE_LENTH) / 2
                        && player.getY() <= HEIGHT - (HEIGHT - RED_BLUE_LENTH) / 2
                        && player.getX() <= player.getCurrentRedius()) {

                        if(player.getCurrentRedius() * 1.5 <= player.MAX_RADIUS) {
                                double previousRedius = player.getCurrentRedius();
                                player.getPlayer().setRadius(player.getCurrentRedius() * 1.5);
                                double deltaRedius = player.getCurrentRedius() - previousRedius;
                                player.setX(player.getX() + deltaRedius);
                        }

                // Collision with red border
                } else if(player.getY() >= (HEIGHT - RED_BLUE_LENTH) / 2
                        && player.getY() <= HEIGHT - (HEIGHT - RED_BLUE_LENTH) / 2
                        && player.getX() >= WIDTH - player.getCurrentRedius()) {

                        if(player.getCurrentRedius() != player.MIN_RADIUS) {
                                double previousRedius = player.getCurrentRedius();
                                player.getPlayer().setRadius(player.MIN_RADIUS);
                                double deltaRedius = previousRedius - player.getCurrentRedius();
                                player.setX(player.getX() + deltaRedius);
                        }

                }


                player.getPlayer().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                                MouseButton mouseButton = event.getButton();
                                if (mouseButton == MouseButton.PRIMARY) {
                                        player.velocityX *= 1.1;
                                        player.velocityY *= 1.1;
                                        player.setVelocity();
                                        setScore();
                                        showScore.setText("Score : " + score.toString());
                                } else if (mouseButton == MouseButton.SECONDARY) {
                                        player.velocityX *= 0.9;
                                        player.velocityY *= 0.9;
                                        player.setVelocity();
                                        setScore();
                                        showScore.setText("Score : " + score.toString());
                                }
                        }
                });

                player.setVelocityX(rotate);
                player.setVelocityY(rotate);
                player.setVelocity();
                player.movePlayer();
        }


        public static void main(String[] args) {
                launch(args);
        } // end method main

} // end class Main
