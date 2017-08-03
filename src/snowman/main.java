package snowman;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class main extends Application {
    private Circle[] circles;
    private Pane root = new Pane();
    @Override
     public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(600);
        primaryStage.setHeight(700);

        Label label = new Label();
        label.setTranslateX(20);
        label.setTranslateY(20);
        label.setText("Minimal radius");
        label.setFont(new Font("Courier New", 12));

        TextField countField1 = new TextField();
        countField1.setTranslateX(10);
        countField1.setTranslateY(35);
        countField1.setText("10");

        Label label2 = new Label();
        label2.setTranslateX(20);
        label2.setTranslateY(60);
        label2.setText("Maximal radius");
        label2.setFont(new Font("Courier New", 12));

        TextField countField2 = new TextField();
        countField2.setTranslateX(10);
        countField2.setTranslateY(75);
        countField2.setText("50");

        Label label3 = new Label();
        label3.setTranslateX(20);
        label3.setTranslateY(100);
        label3.setText("Number of circles");
        label3.setFont(new Font("Courier New", 12));

        TextField countField = new TextField();
        countField.setTranslateX(10);
        countField.setTranslateY(115);
        countField.setText("10");

        Button button = new Button();
        button.setTranslateX(20);
        button.setTranslateY(150);
        button.setPrefSize(150,20);
        button.setText("Generate snowman");

        Button buttonToRed = new Button();
        buttonToRed.setTranslateX(20);
        buttonToRed.setTranslateY(180);
        buttonToRed.setPrefSize(150,20);
        buttonToRed.setText("Paint to RED");

        Button buttonToGradient = new Button();
        buttonToGradient.setTranslateX(20);
        buttonToGradient.setTranslateY(210);
        buttonToGradient.setPrefSize(150,20);
        buttonToGradient.setText("Paint to Gradient");

        button.setOnMouseClicked(event -> {
            int count = Integer.parseInt(countField.getText()) + 3;
            int minR = Integer.parseInt(countField1.getText());
            int maxR = Integer.parseInt(countField2.getText());
            root.getChildren().addAll(generateCircles(count, minR, maxR));
             });
        buttonToRed.setOnMouseClicked(event -> {
            for (int i = 0; i < circles.length;i++){
              circles[i].setFill(Paint.valueOf("#FF0000"));
            }
        });

        buttonToGradient.setOnMouseClicked(event -> {
            if (circles.length != 0) {
                float transperency = 1f / circles.length;
                float step = transperency;
                for (int i = circles.length-1; i >= 0; i--) {
                    if (transperency > 1f) transperency = 1f;
                    circles[i].setFill(randomColor(transperency));
                    transperency += step;
                }
            }
        });

        root.getChildren().addAll(button, countField, label, label2, label3, countField1, countField2, buttonToRed, buttonToGradient);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private Circle[] generateCircles(int count, int minR, int maxR) {
        clearCircles();
        Color color = Color.color(1,
                1,
                1,
                0.1f);
        circles = new Circle[count];
        circles[0] = new Circle(300, 600, RandomUtils.range(minR, maxR), randomColor());
        circles[0].setStroke(Paint.valueOf("#000000"));
        circles[0].setStrokeWidth(1);
        // double rad = random.nextDouble();
        for(int i = 1; i < (circles.length-3); i++) {

            int randomNewRadius = RandomUtils.range(minR, maxR);
                    circles[i] = new Circle(
                    300,
                    circles[i-1].getCenterY() - circles[i-1].getRadius()-randomNewRadius, randomNewRadius
                    , randomColor());
            circles[i].setStroke(Paint.valueOf("#000000"));
            circles[i].setStrokeWidth(1);
            //малюємо ніс та оченята
   //         cir
        }
        circles[circles.length-1] = new Circle(circles[circles.length-4].getCenterX()-circles[circles.length-4].getRadius()*0.5,
                circles[circles.length-4].getCenterY()-circles[circles.length-4].getRadius()*0.2,
                 circles[circles.length-4].getRadius()*0.3,
                randomColor());
        circles[circles.length-2] = new Circle(circles[circles.length-4].getCenterX()+circles[circles.length-4].getRadius()*0.5,
                circles[circles.length-4].getCenterY()-circles[circles.length-4].getRadius()*0.2,
                circles[circles.length-4].getRadius()*0.1,
                randomColor());
        circles[circles.length-3] = new Circle(circles[circles.length-4].getCenterX(),
                circles[circles.length-4].getCenterY()+circles[circles.length-4].getRadius()*0.3,
                circles[circles.length-4].getRadius()*0.2,
                randomColor());
        for (int i = circles.length-3; i < circles.length; i++){
            circles[i].setStroke(Paint.valueOf("#000000"));
            circles[i].setStrokeWidth(1);
        }
        return circles;
    }
    private void clearCircles() {
        if (circles != null && circles.length > 0) {
            root.getChildren().removeAll(circles);
        }
    }
    private Paint randomColor() {
        Color color = Color.color(
                RandomUtils.range(0f, 1f),
                RandomUtils.range(0f, 1f),
                RandomUtils.range(0f, 1f),
                RandomUtils.range(0.2f, 0.8f));

        return Paint.valueOf(color.toString());
    }
    private Paint randomColor(float transparency) {
        Color color = Color.color(
                0f,
                0f,
                0f,
                transparency);

        return Paint.valueOf(color.toString());
    }

}