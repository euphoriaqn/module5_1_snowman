package star;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Line;


/**
 * Created by user22 on 03.08.2017.
 */
public class star2D extends Application {
    int n = 5;
    private float max_radius = 100;
    private float x0 = 300;
    private float y0 = 300;
    private float alpha = 0.95f;
    private float min_radius = max_radius/2.63f;
    private Line[] lines ;
    Pane root = new Pane();
    public void generateStart(int n){
        double a = alpha;
        double dimension_a = Math.PI/n;
        double hyp;

        Point[] starpoint = new Point[2*n+1];

        for (int j = 0; j<2*n+1; j++){
            hyp = j%2 == 0 ? max_radius: min_radius;
            starpoint[j] = new Point(x0+hyp*Math.cos(a), y0+hyp*Math.sin(a));
            a += dimension_a;
        }

        lines = new Line[n*2];
        for (int i = 0; i < lines.length;i++){
            lines[i] = new Line(starpoint[i].getX(),starpoint[i].getY(), starpoint[i+1].getX(), starpoint[i+1].getY());
        }
        root.getChildren().addAll(lines);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Star");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);

        generateStart(n);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
