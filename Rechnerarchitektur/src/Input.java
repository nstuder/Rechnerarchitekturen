import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Input {

	BufferedReader input;
	File file;
	Memory memory;
	
	Input(String filePath, Memory memory){
		this.file = new File(filePath);
		this.memory = memory;
		
		try {
			this.input = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean getData(){
		
		String currentLine = " ";
		String subString = " ";
		String subStringCode = " ";
		int count = 0;
		
		try {
			
			currentLine = this.input.readLine();
			while(currentLine != null){
				subString = currentLine.substring(0, 1);
				if(subString.compareTo("0") == 0) {
					subStringCode = currentLine.substring(5, 9);
					this.memory.writeProgrammMemory(Integer.decode(("0x"+subStringCode)),count);
					count++;
				}		
				currentLine = this.input.readLine();
			}
			input.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
}
