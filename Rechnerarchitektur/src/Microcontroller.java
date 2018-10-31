import java.io.File;
import java.util.ArrayList;

import javafx.collections.ObservableList;

/**
 * Class for the main management of the micro controller
 * @author FlorianGrunwald, NiklasStuder
 *	
 */
public class Microcontroller {
		
	private Memory memory;
	private Input input;
	private Instructions intsructions;
	
	/**
	 * Initialize Program Counter
	 */
	Microcontroller(File ressource){
		this.memory = new Memory();
		this.input = new Input(ressource, this.memory);
		this.input.getData();
		this.intsructions = new Instructions(this.memory);
		this.reset();
		
		//memory.showProgrammMemory();
	}
	
	/**
	 * execute the next Instruction and increase the Program Counter
	 */
	void nextOperation(){
		int instruction = memory.readProgramMeomory(this.memory.readRAM(2));
		this.memory.writeRAM(2, this.memory.readRAM(2)+1);
		
		switch((instruction & 0x3F00)) {
		case 0x0700: //ADDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0100: //CLRF //CLRW
			
		break;
		case 0x0900: //COMF
			
		break;
		case 0x0300: //DECF
		
		break;
		case 0x0B00: //DECFSZ
		
		break;
		case 0x0A00: //INCF
			
		break;
		case 0x0F00: //INCFSZ
			
		break;
		case 0x0400: //IORWF
			
		break;
		case 0x0800: //MOVF
			
		break;
		case 0x0000: //MOVWF //NOP //CLRWDT //RETFILE //RETURN //SLEEP
			
		break;
		case 0x0D00: //RLF
			
		break;
		case 0x0C00: //RRF
			
		break;
		case 0x0200: //SUBWF
			
		break;
		case 0x0E00: //SWAPF
			
		break;
		case 0x0600: //XORWF
			
			
		break;
		case 0x3E00: //ADDLW
			this.intsructions.addLW((instruction & 0x00FF));
		break;
		case 0x3900: //ANDLW
			this.intsructions.andLW((instruction & 0x00FF));
		break;
		case 0x3800: //IORLW
			this.intsructions.iorLW((instruction & 0x00FF));
		break;
		case 0x3000: //MOVLW
			this.intsructions.movLW((instruction & 0x00FF));
		break;		
		case 0x3C00: //SUBLW
			this.intsructions.subLW((instruction & 0x00FF));
		break; 
		case 0x3A00: //XORLW
			this.intsructions.xorLW((instruction & 0x00FF));
		break;
		case 0x2800: //GOTO
			this.memory.writeRAM(2,(instruction & 0x07FF));
		break;
		
			default:
				System.out.println("Instruction not found");
			break;
		}
		/*System.out.println("PC = " + (this.programCounter-1));
		System.out.println("STATUS_REGISTER = " + Integer.toBinaryString(this.memory.readRAM(Instructions.STATUS)));
		System.out.println("W_REGISTER 0x= " + Integer.toHexString(this.memory.readWREG()));
		*/
	}
	
	public int getStatus(int regNumber) {
		if(regNumber != 0) {
			return this.memory.readRAM(regNumber);
		}else {
			return this.memory.readWREG();
		}
	}
	
	public ObservableList<Line> getText() {
		return this.input.getFileString();
	}
	
	public void reset() {
		this.memory.writeRAM(0, 0);
		this.memory.writeRAM(1, 0);
		this.memory.writeRAM(2, 0);
		this.memory.writeRAM(3, 0x18);
		for(int i = 4;i < 0x81;i++) this.memory.writeRAM(i, 0);
		this.memory.writeRAM(0x81, 0xFF);
		this.memory.writeRAM(0x82, 0);
		this.memory.writeRAM(0x83, 0x18);
		this.memory.writeRAM(0x84, 0);
		this.memory.writeRAM(0x85, 0x1F);
		this.memory.writeRAM(0x86, 0xFF);
		for(int i = 0x87;i < 0xD0;i++) this.memory.writeRAM(i, 0);
		this.memory.writeWREG(0);
		this.memory.writeRAM(2,0);
	}
	
	public void resetAll() {
		this.reset();
		for(int i = 4;i < 512;i++) this.memory.writeProgrammMemory(0, i);
	}
}
