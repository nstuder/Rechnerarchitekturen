package GUI;
import java.io.IOException;

import javafx.application.Application;  
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*
 * @author FlorianGrunwald, NiklasStuder
 */
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
		try {
			Runtime.getRuntime().exec("C:\\Program Files (x86)\\Adobe\\Acrobat Reader DC\\Reader\\AcroRd32.exe C:\\Users\\NiklasStuder\\git\\Rechnerarchitektur\\Rechnerarchitektur\\src\\doc\\Dokumentation.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) { 
		launch(args);

	}
}
