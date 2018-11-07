import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application {
	
	Microcontroller PIC;
	
	@Override
	public void start(Stage primaryStage) throws Exception { 
		BorderPane mainLayout = new BorderPane(); 
		TableView table = new TableView();
		TableColumn first = new TableColumn();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setTitle("File");
		//TableColumn Lines[];
		HBox menuBar = new HBox();
		
		
		
		Button file = new Button("File");
		file.setOnAction(event ->
		{
				File result = fileChooser.showOpenDialog(primaryStage);
				if (result != null) {
				PIC = new Microcontroller(result);
				//first.setText(this.PIC.getText().get(0));
				for(int i = 0;i < 500;i++) {
					//Lines = new TableColumn[];
				}
				//table.getColumns().add(first);
				System.out.println(this.PIC.getText());
				} else {
				System.out.println("File not Found");
				}
		});
		
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button step = new Button("Step");
		Button reset = new Button("Reset");
		reset.setOnAction(event -> this.PIC.resetAll());
		step.setOnAction(event -> this.nextStep());
		
		menuBar.getChildren().addAll(file,start,step,stop,reset);
		mainLayout.setTop(menuBar);
		//mainLayout.setCenter(table);
		primaryStage.setTitle("MicroController PIC16F8X"); 
		primaryStage.setScene(new Scene(mainLayout,500,300));
		primaryStage.show();
	}
	
	public static void main(String[] args) { 
		launch(args); 
	}
	
	public void nextStep() {
		PIC.nextOperation();	
	}

}
