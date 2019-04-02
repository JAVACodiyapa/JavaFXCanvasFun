import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    GraphicsContext gc;
    Color[] colors={Color.RED, Color.BLUE, Color.GREEN, Color.BLACK};
    int colorIdx=0;
    public static void main(String args[]){
        //start javaFx application by calling launch() method.
        launch(args);
    }

    @Override
    public void start(Stage myStage){
        //Give Stage a Name.
        myStage.setTitle("Draw to Canvas ");

        //use flow pane For root Node .
        FlowPane rootNode=new FlowPane();

        //centre the node in the scene
        rootNode.setAlignment(Pos.CENTER);

        //create a scene
        Scene myScene=new Scene(rootNode, 450,450);

        //set the Scene to the Stage
        myStage.setScene(myScene);

        //create a Canvas
        Canvas myCanvas = new Canvas(400,400);

        //get the graphic context for the canvas.
        gc=myCanvas.getGraphicsContext2D();

        //create a push button
        Button btnChangeColor= new Button("Change Color");

        //Handle the Action Event for the Change Color Button.
        btnChangeColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //set the Stroke and Fill color.
                gc.setStroke(colors[colorIdx]);
                gc.setFill(colors[colorIdx]);

                /*Redraw the line, text, and filled rectangle in the
                 *new color. This Leaves the color of the other nodes
                 *unchanged
                 */
                gc.strokeLine(0,0,200,200);
                gc.fillText("This is drawn on the canvas ", 60,50);
                gc.fillRect(100,320,300,40);

                //change the color.
                colorIdx++;
                if(colorIdx==colors.length) colorIdx=0;
            }
        });

        //draw initial output on the canvas.
        gc.strokeLine(0,0,200,200);
        gc.strokeOval(100,100,200,200);
        gc.strokeRect(0,200,50,200);
        gc.fillOval(0,0,20,20);
        gc.fillRect(100,320,300,40);

        //set font size to 20 and draw text.
        gc.setFont(new Font(20));
        gc.fillText("This is drawn on the canvas", 60,50);

        //Add Canvas nd btton to the scene graph.
        rootNode.getChildren().addAll(myCanvas,btnChangeColor);

        //show the Stage And its Scene.
        myStage.show();
    }
}