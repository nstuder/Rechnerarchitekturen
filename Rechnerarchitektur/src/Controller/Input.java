package Controller;
import java.io.BufferedReader;  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import GUI.Line;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Class for read the Instructions of the File
 * @author FlorianGrunwald, NiklasStuder
 *	
 */
public class Input {

	private BufferedReader input;
	//private File file;
	private Memory memory;
	private ObservableList<Line> fileString = FXCollections.observableArrayList();
	/**
	 * Main Constructor initialize memory and file path and the Input stream to read from the File
	 * @param filePath path for the assembly file
	 * @param memory for the write to the Program memory
	 */
	Input(File file, Memory memory){
		//this.file = file;
		this.memory = memory;
		try {
			this.input = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * extract the data from the File and write to the Program Memory
	 */
	public void getData(){
		String currentLine = " ";
		String subString = " ";
		String subStringCode = " ";
		int codeLineCount = 0;
		int lineCount = 0;
		try {
			//read Line from File
			currentLine = this.input.readLine();
			// while Line exists
			while(currentLine != null){
				//get substring of the the 1 and 2 Character
				subString = currentLine.substring(0, 1);
				//check if Binary Line 
				if(subString.compareTo("0") == 0) {
					subStringCode = currentLine.substring(5, 9);
					//write in Program Memory
					this.memory.writeProgrammMemory(Integer.decode(("0x"+subStringCode)),codeLineCount);
					// add Code to Line List with true Code Line 
					this.fileString.add(new Line(lineCount,currentLine,codeLineCount));
					codeLineCount++;
				}else {
					// add Code to Line List with false Code Line 
					this.fileString.add(new Line(lineCount,"        " + currentLine,-1));
				}
				//get next Line
				currentLine = this.input.readLine();
				lineCount++;
			}
			input.close();
		} catch (IOException e) {
			// catch IO Exception
			e.printStackTrace();
		}
	}
	/*
	 * @return List of the File Lines
	 */
	public ObservableList<Line> getFileString() {
		return this.fileString;
	}
}
