import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application {
	
	Microcontroller PIC;
	
	@Override
	public void start(Stage primaryStage) throws Exception { 
		BorderPane mainLayout = new BorderPane(); 
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setTitle("File");
		
		HBox menuBar = new HBox();
		
		Button file = new Button("File");
		file.setOnAction(event ->
		{
				File result = fileChooser.showOpenDialog(primaryStage);
				if (result != null) {
				PIC = new Microcontroller(result);
				} else {
				System.out.println("File not Found");
				}
		});
		
		Button start = new Button("Start");
		start.setDisable(true);
		Button stop = new Button("Stop");
		stop.setDisable(true);
		Button step = new Button("Step");
		step.setDisable(true);
		
		menuBar.getChildren().addAll(file,start,step,stop);
		mainLayout.setTop(menuBar);
		primaryStage.setTitle("MicroController PIC16F8X"); 
		primaryStage.setScene(new Scene(mainLayout, 500,300));
		primaryStage.show();
	}
	
	public static void main(String[] args) { 
		launch(args); 
	}

}
