package sample;

import javafx.animation.AnimationTimer;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Main extends Application {

        Player player = new Player();

        @Override
        public void start(Stage primaryStage) throws Exception{

                final double WIDTH = 1000;
                final double HEIGHT = 500;
                Integer score = 0;

                Group root = new Group();
                Scene scene = new Scene(root, WIDTH, HEIGHT, Color.MIDNIGHTBLUE);

                primaryStage.setTitle("Catch It");

                Canvas canvas = new Canvas(WIDTH, HEIGHT);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                root.getChildren().add(canvas);

                createPlayground(gc, WIDTH, HEIGHT);

                Text showScore = new Text(40, 460, "Score : " + score.toString());
                showScore.setFont(Font.loadFont("file:resources/fonts/COOPBL.TTF", 30));
                showScore.setFill(Color.BLACK);
                root.getChildren().add(showScore);

                player.setX(WIDTH / 2);
                player.setY(HEIGHT / 2);
                root.getChildren().add(player.getPlayer());

                final AnimationTimer play = new AnimationTimer() {
                        double rotate = getRandomRotate();

                        @Override
                        public void handle(long now) {

                                player.setVelocityX(rotate);
                                player.setVelocityY(rotate);
                                player.setVelocity();

                                if(player.getY() == player.getRedius()) {
                                        rotate = -1 * rotate;
                                        player.setVelocityX(rotate);
                                        player.setVelocityY(rotate);
                                        player.setVelocity();
                                }

                                player.movePlayer();





                        }
                };


                final Timeline stopPlay = new Timeline();
                KeyFrame stopAnimationTimer = new KeyFrame(Duration.minutes(2), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                play.stop();
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
                primaryStage.show();

        } // end method start(Stage)


        private void createPlayground(GraphicsContext gc, double width, double height) {

                final double RECTANGLES_WIDTH = 10;
                final double RED_BLUE_LENTH = 150;
                final double YELLOW_GREEN_LETH = 200;

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


        private Color getRandomColor(Color currentColor) {

                Random randomColor = new Random();
                Color resultColor = currentColor;
                switch (randomColor.nextInt(6) + 1) {
                        case 1:
                                resultColor = Color.BLUE;
                                break;
                        case 2:
                                resultColor = Color.YELLOW;
                                break;
                        case 3:
                                resultColor = Color.GREEN;
                                break;
                        case 4:
                                resultColor = Color.RED;
                                break;
                        case 5:
                                resultColor = Color.PURPLE;
                                break;
                        case 6:
                                resultColor = Color.GRAY;
                                break;
                        case 7:
                                resultColor = Color.BLACK;
                                break;
                }

                if (resultColor == currentColor) {
                        getRandomColor(currentColor);
                }

                return resultColor;

        } // end method getRandomColor



        public static void main(String[] args) {
                launch(args);
        } // end method main


} // end class Main
