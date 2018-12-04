package GUI;
import javafx.application.Application;  
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Read file fxml and draw interface.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui.fxml"));
			Parent root = (Parent)fxmlLoader.load();
			Scene scene = new Scene(root);
			GuiController controller = fxmlLoader.<GuiController>getController();
			Parameters path = this.getParameters();
			if(path.getRaw().size() > 0)
				controller.setFile(path.getRaw().get(0));
			scene.getStylesheets().add(getClass().getResource("default.css").toExternalForm());
			primaryStage.setTitle("MicroController PIC16F8X"); 
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
