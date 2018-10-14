/**
 * Class for the main management of the micro controller
 * @author FlorianGrunwald, NiklasStuder
 *	
 */
public class Microcontroller {
		
	private Memory memory;
	
	int programCounter;
	/**
	 * Initialize Program Counter
	 */
	Microcontroller(){
		this.programCounter = 0;
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
			
		/*	
		break;
		case 0x1500: //BSF
			
		break;
		case 0x1600: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;		
		case 0x0500: //ANDWF
			
		break;
		*/
			default:
				
			break;
		}
		
		this.programCounter++;
	}
	
	
}
