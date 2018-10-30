import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Class for read the Instructions of the File and decode it for the Memory
 * @author FlorianGrunwald, NiklasStuder
 *	
 */
public class Input {

	private BufferedReader input;
	private File file;
	private Memory memory;
	private ArrayList<String> fileString;
	/**
	 * Main Constructor initialize memory and file path and the Input stream to read from the File
	 * @param filePath path for the assembly file
	 * @param memory for the write to the Program memory
	 */
	Input(File file, Memory memory){
		this.file = file;
		this.memory = memory;
		
		try {
			this.input = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		int count = 0;
		int count1 = 0;
		try {
			currentLine = this.input.readLine();
			
			while(currentLine != null){
				//this.fileString.add(currentLine);
				
				subString = currentLine.substring(0, 1);
				if(subString.compareTo("0") == 0) {
					subStringCode = currentLine.substring(5, 9);
					this.memory.writeProgrammMemory(Integer.decode(("0x"+subStringCode)),count);
					count++;
				}		
				currentLine = this.input.readLine();
				count1++;
			}
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getFileString() {
		return this.fileString;
	}
}
