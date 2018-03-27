package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{

                final double WIDTH = 1000;
                final double HEIGHT = 500;

                Group root = new Group();
                Scene scene = new Scene(root, WIDTH, HEIGHT, Color.MIDNIGHTBLUE);

                createPlayground(root, WIDTH, HEIGHT);

                Player player = new Player();
                player.setX(WIDTH / 2);
                player.setY(HEIGHT / 2);
                root.getChildren().add(player.getNode());




                primaryStage.setScene(scene);
                primaryStage.show();

        } // end method start(Stage)


        private void createPlayground(Group root, double width, double height) {

                final double RECTANGLES_WIDTH = 10;
                final double RED_BLUE_LENTH = 150;
                final double YELLOW_GREEN_LETH = 200;

                Canvas canvas = new Canvas(width, height);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                root.getChildren().add(canvas);

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


        public static void main(String[] args) {
                launch(args);
        } // end method main
    
} // end class Main
