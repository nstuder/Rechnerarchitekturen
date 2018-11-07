import java.io.File; 
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
	}
	
	/**
	 * execute the next Instruction and increase the Program Counter
	 */
	void nextOperation(){
		int instruction = memory.readProgramMeomory(this.memory.readRAM(2));
		this.memory.writeRAM(2, this.memory.readRAM(2)+1); //index 2 is PC
		
		switch(instruction & 0x3000) {
			case 0x0000: //00 Instructions
				
				switch((instruction & 0x0F00)) {
					case 0x0700: //ADDWF
						this.intsructions.addWf(instruction & 0x0FF);
						break;
					case 0x0500: //ANDWF
						this.intsructions.andWf(instruction & 0x0FF);
						break;
					case 0x0100: //CLRF //CLRW
							if((instruction & 0x3FFF) == 0x0100) {
								//CLRW
								this.intsructions.clrW();
							} else {
								//CLRF
								this.intsructions.clrF(instruction & 0x0FF);
							}
						break;
					case 0x0900: //COMF
						this.intsructions.comF(instruction & 0x0FF);
						break;
					case 0x0300: //DECF
						this.intsructions.decF(instruction & 0x0FF);
						break;
					case 0x0B00: //DECFSZ
						this.intsructions.decFSZ(instruction & 0x0FF);
						break;
					case 0x0A00: //INCF
						this.intsructions.incF(instruction & 0x0FF);
						break;
					case 0x0F00: //INCFSZ
						this.intsructions.incFSZ(instruction & 0x0FF);
						break;
					case 0x0400: //IORWF
						this.intsructions.ioWf(instruction & 0x0FF);
						break;
					case 0x0800: //MOVF
						this.intsructions.movF(instruction & 0x0FF);
						break;
					case 0x0000: //MOVWF //NOP //CLRWDT //RETFILE //RETURN //SLEEP
						switch((instruction & 0x00FF)) {
							case 0x0000: //NOP
								this.intsructions.nop();
								break;
							case 0x0064: //CLRWDT
								this.intsructions.clrWDT();
								break;
							case 0x0009: //RETFIE
								this.intsructions.retFIE();
								break;
							case 0x0008: //RETURN
								this.intsructions.ret();
								break;
							case 0x0063: //SLEEP
								this.intsructions.sleep();
								break;
							default: //MOVWF
								this.intsructions.movWf(instruction & 0x0FF);
								break;
						}
						break;
					case 0x0D00: //RLF
						this.intsructions.rlF(instruction & 0x0FF);
						break;
					case 0x0C00: //RRF
						this.intsructions.rrF(instruction & 0x0FF);
						break;
					case 0x0200: //SUBWF
						this.intsructions.subWf(instruction & 0x0FF);
						break;
					case 0x0E00: //SWAPF
						this.intsructions.swapF(instruction & 0x0FF);
						break;
					case 0x0600: //XORWF
						this.intsructions.xorWf(instruction & 0x0FF);
						break;
				}
				break;
				
			case 0x1000: //01 Instructions
				switch(instruction & 0x0B00) {
					case 0x0000: //BCF
						this.intsructions.bcF(instruction & 0x3FF);
						break;
					case 0x0400: //BSF
						this.intsructions.bsF(instruction & 0x3FF);
						break;
					case 0x0700: //BTFSC
						this.intsructions.btFSC(instruction & 0x3FF);
						break;
					case 0x0B00: //BTFSS
						this.intsructions.btFSS(instruction & 0x3FF);
						break;
				}
				break;
			case 0x2000: //10 Instructions
				if((instruction & 0x0800) > 0) {
					//GOTO
					this.intsructions.goTo(instruction & 0x07FF);		
				}else {
					//call
					this.intsructions.call(instruction & 0x07FF);
				}
				break;
			case 0x3000: //11 Instructions
				switch(instruction & 0x0F00) {
				
					case 0x0E00: //ADDLW
						this.intsructions.addLW((instruction & 0x00FF));
					break;
					case 0x0900: //ANDLW
						this.intsructions.andLW((instruction & 0x00FF));
					break;
					case 0x0800: //IORLW
						this.intsructions.iorLW((instruction & 0x00FF));
					break;
					case 0x0000: //MOVLW
						this.intsructions.movLW((instruction & 0x00FF));
					break;		
					case 0x0C00: //SUBLW
						this.intsructions.subLW((instruction & 0x00FF));
					break; 
					case 0x0A00: //XORLW
						this.intsructions.xorLW((instruction & 0x00FF));
					break;
					case 0x0400: //retLW
						this.intsructions.returnLw(instruction & 0x00FF);
					break;
				}
				break;
				
			default:
				System.out.println("Instruction not found");
				break;
		}
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
