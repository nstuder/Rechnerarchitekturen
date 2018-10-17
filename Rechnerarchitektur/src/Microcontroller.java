import java.io.File;

/**
 * Class for the main management of the micro controller
 * @author FlorianGrunwald, NiklasStuder
 *	
 */
public class Microcontroller {
		
	private Memory memory;
	private Input input;
	private Instructions intsructions;
	int programCounter;
	
	/**
	 * Initialize Program Counter
	 */
	Microcontroller(File ressource){
		this.memory = new Memory();
		this.input = new Input(ressource, this.memory);
		this.input.getData();
		this.intsructions = new Instructions(this.memory);
		this.programCounter = 0;
		
		memory.showProgrammMemory();
	}
	
	/**
	 * execute the next Instruction and increase the Program Counter
	 */
	void nextOperation(){
		int instruction = memory.readProgramMeomory(this.programCounter);
		
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
		
			default:
				System.out.println("Instruction not found");
			break;
		}
		
		this.programCounter++;
		System.out.println("W_REGISTER = " + this.memory.readWREG());
	}
	
	
}
