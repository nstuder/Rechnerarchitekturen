package GUI;
import java.io.File; 
import java.net.URL;
import java.util.ResourceBundle;

import Controller.Microcontroller;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author FlorianGrunwald, NiklasStuder
 *
 */
public class GuiController {

	/*------------FXML injections-------------------------------------------*/

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;
	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="A0"
	private CheckBox A0; // Value injected by FXMLLoader
	@FXML // fx:id="A1"
	private CheckBox A1; // Value injected by FXMLLoader
	@FXML // fx:id="A2"
	private CheckBox A2; // Value injected by FXMLLoader
	@FXML // fx:id="A3"
	private CheckBox A3; // Value injected by FXMLLoader
	@FXML // fx:id="A4"
	private CheckBox A4; // Value injected by FXMLLoader

	@FXML // fx:id="B0"
	private CheckBox B0; // Value injected by FXMLLoader
	@FXML // fx:id="B1"
	private CheckBox B1; // Value injected by FXMLLoader
	@FXML // fx:id="B2"
	private CheckBox B2; // Value injected by FXMLLoader
	@FXML // fx:id="B3"
	private CheckBox B3; // Value injected by FXMLLoader
	@FXML // fx:id="B4"
	private CheckBox B4; // Value injected by FXMLLoader
	@FXML // fx:id="B5"
	private CheckBox B5; // Value injected by FXMLLoader
	@FXML // fx:id="B6"
	private CheckBox B6; // Value injected by FXMLLoader
	@FXML // fx:id="B7"
	private CheckBox B7; // Value injected by FXMLLoader

	@FXML // fx:id="regTable"
	private TableView<Register> regTable; // Value injected by FXMLLoader
	@FXML // fx:id="reg"
	private TableColumn<Register, String> reg; // Value injected by FXMLLoader
	@FXML // fx:id="hex"
	private TableColumn<Register, String> hex; // Value injected by FXMLLoader
	@FXML // fx:id="reg0"
	private TableColumn<Register, String> reg0; // Value injected by FXMLLoader
	@FXML // fx:id="reg1"
	private TableColumn<Register, String> reg1; // Value injected by FXMLLoader
	@FXML // fx:id="reg2"
	private TableColumn<Register, String> reg2; // Value injected by FXMLLoader
	@FXML // fx:id="reg3"
	private TableColumn<Register, String> reg3; // Value injected by FXMLLoader
	@FXML // fx:id="reg4"
	private TableColumn<Register, String> reg4; // Value injected by FXMLLoader
	@FXML // fx:id="reg5"
	private TableColumn<Register, String> reg5; // Value injected by FXMLLoader
	@FXML // fx:id="reg6"
	private TableColumn<Register, String> reg6; // Value injected by FXMLLoader
	@FXML // fx:id="reg7"
	private TableColumn<Register, String> reg7; // Value injected by FXMLLoader

	@FXML // fx:id="codeTable"
	private TableView<Line> codeTable; // Value injected by FXMLLoader
	@FXML // fx:id="codeLine"
	private TableColumn<Line, Integer> codeLine; // Value injected by FXMLLoader
	@FXML // fx:id="dataCode"
	private TableColumn<Line, String> dataCode; // Value injected by FXMLLoader

	@FXML // fx:id="wRegister"
	private TextField wRegister; // Value injected by FXMLLoader
	@FXML // fx:id="runSpeed"
	private Slider runSpeed; // Value injected by FXMLLoader
	@FXML // fx:id="runtime"
	private Label runtime; // Value injected by FXMLLoader

	@FXML // fx:id="step"
	private Button step; // Value injected by FXMLLoader
	@FXML // fx:id="start"
	private Button start; // Value injected by FXMLLoader
	@FXML // fx:id="stop"
	private Button stop; // Value injected by FXMLLoader
	@FXML // fx:id="reset"
	private Button reset; // Value injected by FXMLLoader

	@FXML // fx:id="quit"
	private MenuItem quit; // Value injected by FXMLLoader
	@FXML // fx:id="open"
	private MenuItem open; // Value injected by FXMLLoader
	@FXML // fx:id="close"
	private MenuItem close; // Value injected by FXMLLoader

	/*------------FXML injections end-----------------------------------*/

	// Microcontroller
	private Microcontroller PIC;
	private Timeline runTime;

	// List of the Register Table
	public ObservableList<Register> regData = FXCollections.observableArrayList(new Register("TMR0", 0),
			new Register("PCL", 0), new Register("STATUS", 0), new Register("FSR", 0), new Register("PORTA", 0),
			new Register("PORTB", 0), new Register("PCLATH", 0), new Register("INTCON", 0), new Register("INDF", 0),
			new Register("OPTION", 0), new Register("TRISA", 0), new Register("TRISB", 0), new Register("EECON1", 0));

	// List for the Code Table
	public ObservableList<Line> codeData = FXCollections.observableArrayList();

	/*
	 * set a New File from comandLine as Parameter
	 * 
	 * @param String path path name of the File
	 */
	public void setFile(String path) {
		if (path != null) {
			File newFile = new File(path);
			this.startNewMicro(newFile);
		}
	}

	/*
	 * start a new Session of an Simulation
	 * @param File result LST File
	 */
	private void startNewMicro(File result) {
		PIC = new Microcontroller(result);

		this.runTime = new Timeline(new KeyFrame(Duration.millis(5), e -> step(null)));
		this.runTime.setCycleCount(Animation.INDEFINITE);
		
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
		this.B5.setDisable(false);
		this.B6.setDisable(false);
		this.B7.setDisable(false);
		this.B0.setDisable(false);
		this.B1.setDisable(false);
		this.B2.setDisable(false);
		this.B3.setDisable(false);
		this.B4.setDisable(false);

		this.codeData.addAll(PIC.getText());
		this.codeTable.refresh();
		this.reset(null);
	}

	@FXML
	void setSpeed(ActionEvent event) {
		this.runTime.setDelay(new Duration(this.runSpeed.getValue()));
		System.out.println(this.runSpeed.getValue());
	}

	@FXML
	void close(ActionEvent event) {

	}

	@FXML
	void open(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File result = fileChooser.showOpenDialog(new Stage());
		if (result != null) {
			startNewMicro(result);
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
		for (int i = 0; i < codeData.size(); i++) {
			if (codeData.get(i).getProgamCount() == this.PIC.getStatus(2))
				codeTable.getSelectionModel().select(i);
		}
	}

	@FXML
	void setPortA(ActionEvent event) {
		int portA = 0;
		if (this.A0.isSelected())
			portA += 1;
		if (this.A1.isSelected())
			portA += 2;
		if (this.A2.isSelected())
			portA += 4;
		if (this.A3.isSelected())
			portA += 8;
		if (this.A4.isSelected())
			portA += 16;
		this.PIC.setStatus(5, portA);
		regData.get(4).setNewValue(PIC.getStatus(5));
		this.regTable.refresh();

		CheckBox temp = (CheckBox) event.getSource();
		if (temp.getId().equals("A4")) {
			if (temp.isSelected()) {
				this.PIC.incTimer(true);
			} else {
				this.PIC.incTimer(false);
			}
		}
	}

	@FXML
	void setPortB(ActionEvent event) {
		int portB = 0;
		if (this.B0.isSelected())
			portB += 1;
		if (this.B1.isSelected())
			portB += 2;
		if (this.B2.isSelected())
			portB += 4;
		if (this.B3.isSelected())
			portB += 8;
		if (this.B4.isSelected())
			portB += 16;
		if (this.B5.isSelected())
			portB += 32;
		if (this.B6.isSelected())
			portB += 64;
		if (this.B7.isSelected())
			portB += 128;
		this.PIC.setStatus(6, portB);

		CheckBox temp = (CheckBox) event.getSource();
		// check for Port B0 Interrupt
		if (temp.getId().equals("B0")) {
			System.out.println("in RB0");
			if (this.PIC.isBitSet(0x81, 6) && temp.isSelected())
				this.PIC.setBit(0xB, 1);
			if (!this.PIC.isBitSet(0x81, 6) && !temp.isSelected())
				this.PIC.setBit(0xB, 1);
		}
		// check for Port B4-7 Interrupt
		if (temp.getId().equals("B4") || temp.getId().equals("B5") || temp.getId().equals("B6")
				|| temp.getId().equals("B7")) {
			if (this.PIC.isBitSet(0xB, 3))
				this.PIC.setBit(0xB, 0);
		}

		regData.get(5).setNewValue(PIC.getStatus(6));
		this.regTable.refresh();
	}

	@FXML
	void start(ActionEvent event) {
		this.runTime.play();
	}
	
	/*
	 * calculates the next step of the micro Controller
	 * @param ActionEvent event even from the Button 
	*/
	@FXML
	synchronized void step(ActionEvent event) {
		PIC.nextOperation();
		this.wRegister.setText("0x" + Integer.toHexString(PIC.getStatus(0)));
		regData.get(11).setNewValue(PIC.getStatus(0x86));// TRISB
		regData.get(10).setNewValue(PIC.getStatus(0x85));// TRISA
		regData.get(9).setNewValue(PIC.getStatus(0x81));// OPTION
		regData.get(8).setNewValue(PIC.getStatus(12));
		regData.get(7).setNewValue(PIC.getStatus(11));
		regData.get(6).setNewValue(PIC.getStatus(10));
		regData.get(5).setNewValue(PIC.getStatus(6));
		regData.get(4).setNewValue(PIC.getStatus(5));
		regData.get(3).setNewValue(PIC.getStatus(4));
		regData.get(2).setNewValue(PIC.getStatus(3));
		regData.get(1).setNewValue(PIC.getStatus(2));
		regData.get(0).setNewValue(PIC.getStatus(1));
		this.regTable.refresh();

		// PortA TRIS
		if (!this.PIC.isBitSet(0x85, 0)) {
			this.A0.setDisable(true);
		} else {
			this.A0.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x85, 1)) {
			this.A1.setDisable(true);
		} else {
			this.A1.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x85, 2)) {
			this.A2.setDisable(true);
		} else {
			this.A2.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x85, 3)) {
			this.A3.setDisable(true);
		} else {
			this.A3.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x85, 4)) {
			this.A4.setDisable(true);
		} else {
			this.A4.setDisable(false);
		}

		// PortB TRIS
		if (!this.PIC.isBitSet(0x86, 0)) {
			this.B0.setDisable(true);
		} else {
			this.B0.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x86, 1)) {
			this.B1.setDisable(true);
		} else {
			this.B1.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x86, 2)) {
			this.B2.setDisable(true);
		} else {
			this.B2.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x86, 3)) {
			this.B3.setDisable(true);
		} else {
			this.B3.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x86, 4)) {
			this.B4.setDisable(true);
		} else {
			this.B4.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x86, 5)) {
			this.B5.setDisable(true);
		} else {
			this.B5.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x86, 6)) {
			this.B6.setDisable(true);
		} else {
			this.B6.setDisable(false);
		}
		if (!this.PIC.isBitSet(0x86, 7)) {
			this.B7.setDisable(true);
		} else {
			this.B7.setDisable(false);
		}
		
		for (int i = 0; i < codeData.size(); i++) {
			if (codeData.get(i).getProgamCount() == ((this.PIC.getPCHigh() << 8) + this.PIC.getStatus(2)))
				codeTable.getSelectionModel().select(i);
		}
	}

	@FXML
	void stop(ActionEvent event) {
		this.runTime.stop();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert codeTable != null : "fx:id=\"codeTable\" was not injected: check your FXML file 'gui.fxml'.";
		assert A0 != null : "fx:id=\"A0\" was not injected: check your FXML file 'gui.fxml'.";
		assert A1 != null : "fx:id=\"A1\" was not injected: check your FXML file 'gui.fxml'.";
		assert A2 != null : "fx:id=\"A2\" was not injected: check your FXML file 'gui.fxml'.";
		assert A3 != null : "fx:id=\"A3\" was not injected: check your FXML file 'gui.fxml'.";
		assert A4 != null : "fx:id=\"A4\" was not injected: check your FXML file 'gui.fxml'.";
		assert B5 != null : "fx:id=\"A5\" was not injected: check your FXML file 'gui.fxml'.";
		assert reg5 != null : "fx:id=\"reg5\" was not injected: check your FXML file 'gui.fxml'.";
		assert B6 != null : "fx:id=\"A6\" was not injected: check your FXML file 'gui.fxml'.";
		assert reg != null : "fx:id=\"reg\" was not injected: check your FXML file 'gui.fxml'.";
		assert reg4 != null : "fx:id=\"reg4\" was not injected: check your FXML file 'gui.fxml'.";
		assert B7 != null : "fx:id=\"A7\" was not injected: check your FXML file 'gui.fxml'.";
		assert reg7 != null : "fx:id=\"reg7\" was not injected: check your FXML file 'gui.fxml'.";
		assert reg6 != null : "fx:id=\"reg6\" was not injected: check your FXML file 'gui.fxml'.";
		assert hex != null : "fx:id=\"hex\" was not injected: check your FXML file 'gui.fxml'.";
		assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'gui.fxml'.";
		assert start != null : "fx:id=\"start\" was not injected: check your FXML file 'gui.fxml'.";
		assert reg1 != null : "fx:id=\"reg1\" was not injected: check your FXML file 'gui.fxml'.";
		assert reg0 != null : "fx:id=\"reg0\" was not injected: check your FXML file 'gui.fxml'.";
		assert runtime != null : "fx:id=\"runtime\" was not injected: check your FXML file 'gui.fxml'.";
		assert reg3 != null : "fx:id=\"reg3\" was not injected: check your FXML file 'gui.fxml'.";
		assert reg2 != null : "fx:id=\"reg2\" was not injected: check your FXML file 'gui.fxml'.";
		assert B0 != null : "fx:id=\"B0\" was not injected: check your FXML file 'gui.fxml'.";
		assert B1 != null : "fx:id=\"B1\" was not injected: check your FXML file 'gui.fxml'.";
		assert B2 != null : "fx:id=\"B2\" was not injected: check your FXML file 'gui.fxml'.";
		assert B3 != null : "fx:id=\"B3\" was not injected: check your FXML file 'gui.fxml'.";
		assert regTable != null : "fx:id=\"regTable\" was not injected: check your FXML file 'gui.fxml'.";
		assert B4 != null : "fx:id=\"B4\" was not injected: check your FXML file 'gui.fxml'.";
		assert stop != null : "fx:id=\"stop\" was not injected: check your FXML file 'gui.fxml'.";
		assert reset != null : "fx:id=\"reset\" was not injected: check your FXML file 'gui.fxml'.";
		assert quit != null : "fx:id=\"quit\" was not injected: check your FXML file 'gui.fxml'.";
		assert step != null : "fx:id=\"step\" was not injected: check your FXML file 'gui.fxml'.";
		assert wRegister != null : "fx:id=\"wRegister\" was not injected: check your FXML file 'gui.fxml'.";
		assert open != null : "fx:id=\"open\" was not injected: check your FXML file 'gui.fxml'.";

		this.reg.setCellValueFactory(new PropertyValueFactory<Register, String>("name"));
		this.hex.setCellValueFactory(new PropertyValueFactory<Register, String>("hex"));
		this.reg0.setCellValueFactory(new PropertyValueFactory<Register, String>("Bit0"));
		this.reg1.setCellValueFactory(new PropertyValueFactory<Register, String>("Bit1"));
		this.reg2.setCellValueFactory(new PropertyValueFactory<Register, String>("Bit2"));
		this.reg3.setCellValueFactory(new PropertyValueFactory<Register, String>("Bit3"));
		this.reg4.setCellValueFactory(new PropertyValueFactory<Register, String>("Bit4"));
		this.reg5.setCellValueFactory(new PropertyValueFactory<Register, String>("Bit5"));
		this.reg6.setCellValueFactory(new PropertyValueFactory<Register, String>("Bit6"));
		this.reg7.setCellValueFactory(new PropertyValueFactory<Register, String>("Bit7"));

		this.regTable.setItems(regData);

		this.codeLine.setCellValueFactory(new PropertyValueFactory<Line, Integer>("lineNumber"));
		this.dataCode.setCellValueFactory(new PropertyValueFactory<Line, String>("lineData"));

		this.codeTable.setItems(codeData);
	}
}
