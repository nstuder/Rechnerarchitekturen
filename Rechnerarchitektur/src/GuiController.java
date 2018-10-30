import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuiController {

	//Microcontroller 
	private Microcontroller PIC;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="open"
    private MenuItem open; // Value injected by FXMLLoader

    @FXML // fx:id="close"
    private MenuItem close; // Value injected by FXMLLoader

    @FXML // fx:id="quit"
    private MenuItem quit; // Value injected by FXMLLoader

    @FXML // fx:id="start"
    private Button start; // Value injected by FXMLLoader

    @FXML // fx:id="stop"
    private Button stop; // Value injected by FXMLLoader

    @FXML // fx:id="step"
    private Button step; // Value injected by FXMLLoader

    @FXML // fx:id="reset"
    private Button reset; // Value injected by FXMLLoader

    @FXML // fx:id="codeTable"
    private TableView<?> codeTable; // Value injected by FXMLLoader

    @FXML // fx:id="A7"
    private CheckBox A7; // Value injected by FXMLLoader

    @FXML // fx:id="A6"
    private CheckBox A6; // Value injected by FXMLLoader

    @FXML // fx:id="A4"
    private CheckBox A4; // Value injected by FXMLLoader

    @FXML // fx:id="A5"
    private CheckBox A5; // Value injected by FXMLLoader

    @FXML // fx:id="A3"
    private CheckBox A3; // Value injected by FXMLLoader

    @FXML // fx:id="A2"
    private CheckBox A2; // Value injected by FXMLLoader

    @FXML // fx:id="A0"
    private CheckBox A0; // Value injected by FXMLLoader

    @FXML // fx:id="A1"
    private CheckBox A1; // Value injected by FXMLLoader

    @FXML // fx:id="B3"
    private CheckBox B3; // Value injected by FXMLLoader

    @FXML // fx:id="B2"
    private CheckBox B2; // Value injected by FXMLLoader

    @FXML // fx:id="B0"
    private CheckBox B0; // Value injected by FXMLLoader

    @FXML // fx:id="B1"
    private CheckBox B1; // Value injected by FXMLLoader

    @FXML // fx:id="B4"
    private CheckBox B4; // Value injected by FXMLLoader

    @FXML // fx:id="wRegister"
    private TextField wRegister; // Value injected by FXMLLoader

    @FXML // fx:id="regTable"
    private TableView<?> regTable; // Value injected by FXMLLoader

    @FXML // fx:id="runtime"
    private Label runtime; // Value injected by FXMLLoader

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void open(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setTitle("File");
		File result = fileChooser.showOpenDialog(new Stage());
		if (result != null) {
		PIC = new Microcontroller(result);
		//first.setText(this.PIC.getText().get(0));
		for(int i = 0;i < 500;i++) {
			//Lines = new TableColumn[];
		}
		// enable Buttons
		this.step.setDisable(false);
		this.start.setDisable(false);
		this.stop.setDisable(false);
		this.reset.setDisable(false);
		this.A0.setDisable(false);
		this.A1.setDisable(false);
		this.A2.setDisable(false);
		this.A3.setDisable(false);
		this.A4.setDisable(false);
		this.A5.setDisable(false);
		this.A6.setDisable(false);
		this.A7.setDisable(false);
		this.B0.setDisable(false);
		this.B1.setDisable(false);
		this.B2.setDisable(false);
		this.B3.setDisable(false);
		this.B4.setDisable(false);
		
		System.out.println(this.PIC.getText());
		} else {
		System.out.println("File not Found");
		}
    }

    @FXML
    void quit(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {
    	PIC.reset();
    	this.wRegister.setText("0x00");
    }

    @FXML
    void setPortA(ActionEvent event) {

    }

    @FXML
    void setPortB(ActionEvent event) {

    }

    @FXML
    void start(ActionEvent event) {

    }

    @FXML
    void step(ActionEvent event) {
    	PIC.nextOperation();
    	this.wRegister.setText("0x" + Integer.toHexString(PIC.getStatus(0)));
    	
    }

    @FXML
    void stop(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert open != null : "fx:id=\"open\" was not injected: check your FXML file 'gui.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'gui.fxml'.";
        assert quit != null : "fx:id=\"quit\" was not injected: check your FXML file 'gui.fxml'.";
        assert start != null : "fx:id=\"start\" was not injected: check your FXML file 'gui.fxml'.";
        assert stop != null : "fx:id=\"stop\" was not injected: check your FXML file 'gui.fxml'.";
        assert step != null : "fx:id=\"step\" was not injected: check your FXML file 'gui.fxml'.";
        assert reset != null : "fx:id=\"reset\" was not injected: check your FXML file 'gui.fxml'.";
        assert codeTable != null : "fx:id=\"codeTable\" was not injected: check your FXML file 'gui.fxml'.";
        assert A7 != null : "fx:id=\"A7\" was not injected: check your FXML file 'gui.fxml'.";
        assert A6 != null : "fx:id=\"A6\" was not injected: check your FXML file 'gui.fxml'.";
        assert A4 != null : "fx:id=\"A4\" was not injected: check your FXML file 'gui.fxml'.";
        assert A5 != null : "fx:id=\"A5\" was not injected: check your FXML file 'gui.fxml'.";
        assert A3 != null : "fx:id=\"A3\" was not injected: check your FXML file 'gui.fxml'.";
        assert A2 != null : "fx:id=\"A2\" was not injected: check your FXML file 'gui.fxml'.";
        assert A0 != null : "fx:id=\"A0\" was not injected: check your FXML file 'gui.fxml'.";
        assert A1 != null : "fx:id=\"A1\" was not injected: check your FXML file 'gui.fxml'.";
        assert B3 != null : "fx:id=\"B3\" was not injected: check your FXML file 'gui.fxml'.";
        assert B2 != null : "fx:id=\"B2\" was not injected: check your FXML file 'gui.fxml'.";
        assert B0 != null : "fx:id=\"B0\" was not injected: check your FXML file 'gui.fxml'.";
        assert B1 != null : "fx:id=\"B1\" was not injected: check your FXML file 'gui.fxml'.";
        assert B4 != null : "fx:id=\"B4\" was not injected: check your FXML file 'gui.fxml'.";
        assert wRegister != null : "fx:id=\"wRegister\" was not injected: check your FXML file 'gui.fxml'.";
        assert regTable != null : "fx:id=\"regTable\" was not injected: check your FXML file 'gui.fxml'.";
        assert runtime != null : "fx:id=\"runtime\" was not injected: check your FXML file 'gui.fxml'.";

    }
}
