
public class Microcontroller {
		
	private Memory memory;
	
	int programCounter;
	
	Microcontroller(){
		this.programCounter = 0;
	}
	
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
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;
		case 0x0500: //ANDWF
			
		break;		
		case 0x0500: //ANDWF
			
		break;
		
		
			default:
				
			break;
		}
		
		this.programCounter++;
	}
	
	
}
