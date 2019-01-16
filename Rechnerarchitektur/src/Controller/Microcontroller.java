package Controller;
import java.io.File; 

import GUI.Line;
import javafx.collections.ObservableList;

/**
 * Class for the main management of the micro controller
 * 
 * @author FlorianGrunwald, NiklasStuder
 * 
 */
public class Microcontroller {

	private Memory memory;
	private Input input;
	private Instructions intsructions;
	private int cycles;

	public int timerCount;

	/**
	 * Initialize Program Counter
	 */
	public Microcontroller(File ressource) {
		this.memory = new Memory();
		this.input = new Input(ressource, this.memory);
		this.input.getData();
		this.intsructions = new Instructions(this.memory);
		this.reset();
	}

	/**
	 * execute the next Instruction and increase the Program Counter and check for
	 * Interrupts
	 */
	public void nextOperation() {
		//System.out.println(this.memory.readRAM(2));
		int instruction = memory.readProgramMemory((this.memory.getPcHigh() << 8) + this.memory.readRAM(2));
		this.memory.write(2, this.memory.readRAM(2) + 1); // index 2 is PC

		switch (instruction & 0x3000) {
		case 0x0000: // 00 Instructions

			switch ((instruction & 0x0F00)) {
			case 0x0700: // ADDWF
				this.intsructions.addWf(instruction & 0x0FF);
				break;
			case 0x0500: // ANDWF
				this.intsructions.andWf(instruction & 0x0FF);
				break;
			case 0x0100: // CLRF //CLRW
				if ((instruction & 0x3FFF) == 0x0100) {
					// CLRW
					this.intsructions.clrW();
				} else {
					// CLRF
					this.intsructions.clrF(instruction & 0xFF);
				}
				break;
			case 0x0900: // COMF
				this.intsructions.comF(instruction & 0x0FF);
				break;
			case 0x0300: // DECF
				this.intsructions.decF(instruction & 0x0FF);
				break;
			case 0x0B00: // DECFSZ
				int temp = this.memory.readRAM(2);
				this.intsructions.decFSZ(instruction & 0x0FF);
				if (this.memory.readRAM(2) - temp > 0)
					this.incCycles();
				break;
			case 0x0A00: // INCF
				this.intsructions.incF(instruction & 0x0FF);
				break;
			case 0x0F00: // INCFSZ
				int temp2 = this.memory.readRAM(2);
				this.intsructions.incFSZ(instruction & 0x0FF);
				if (this.memory.readRAM(2) - temp2 > 0)
					this.incCycles();
				break;
			case 0x0400: // IORWF
				this.intsructions.ioWf(instruction & 0x0FF);
				break;
			case 0x0800: // MOVF
				this.intsructions.movF(instruction & 0x0FF);
				break;
			case 0x0000: // MOVWF //NOP //CLRWDT //RETFILE //RETURN //SLEEP
				switch ((instruction & 0x00FF)) {
				case 0x0000: // NOP
					this.intsructions.nop();
					break;
				case 0x0064: // CLRWDT
					this.intsructions.clrWDT();
					break;
				case 0x0009: // RETFIE
					this.intsructions.retFIE();
					this.incCycles();
					break;
				case 0x0008: // RETURN
					this.intsructions.ret();
					this.incCycles();
					break;
				case 0x0063: // SLEEP
					this.intsructions.sleep();
					break;
				default: // MOVWF
					this.intsructions.movWf(instruction & 0x0FF);
					break;
				}
				break;
			case 0x0D00: // RLF
				this.intsructions.rlF(instruction & 0x0FF);
				break;
			case 0x0C00: // RRF
				this.intsructions.rrF(instruction & 0x0FF);
				break;
			case 0x0200: // SUBWF
				this.intsructions.subWf(instruction & 0x0FF);
				break;
			case 0x0E00: // SWAPF
				this.intsructions.swapF(instruction & 0x0FF);
				break;
			case 0x0600: // XORWF
				this.intsructions.xorWf(instruction & 0x0FF);
				break;
			}
			break;

		case 0x1000: // 01 Instructions
			switch (instruction & 0x0C00) {
			case 0x0000: // BCF
				this.intsructions.bcF(instruction & 0x3FF);
				break;
			case 0x0400: // BSF
				this.intsructions.bsF(instruction & 0x3FF);
				break;
			case 0x0800: // BTFSC
				int temp = this.memory.readRAM(2);
				this.intsructions.btFSC(instruction & 0x3FF);
				if (this.memory.readRAM(2) - temp > 0)
					this.incCycles();
				break;
			case 0x0C00: // BTFSS
				int temp2 = this.memory.readRAM(2);
				this.intsructions.btFSS(instruction & 0x3FF);
				if (this.memory.readRAM(2) - temp2 > 0)
					this.incCycles();
				break;
			}
			break;
		case 0x2000: // 10 Instructions
			if ((instruction & 0x0800) > 0) {
				// GOTO
				this.intsructions.goTo(instruction & 0x07FF);
				this.incCycles();
			} else {
				// call
				this.intsructions.call(instruction & 0x07FF);
				this.incCycles();
			}
			break;
		case 0x3000: // 11 Instructions
			switch (instruction & 0x0F00) {

			case 0x0E00: // ADDLW
				this.intsructions.addLW((instruction & 0x00FF));
				break;
			case 0x0900: // ANDLW
				this.intsructions.andLW((instruction & 0x00FF));
				break;
			case 0x0800: // IORLW
				this.intsructions.iorLW((instruction & 0x00FF));
				break;
			case 0x0000: // MOVLW
				this.intsructions.movLW((instruction & 0x00FF));
				break;
			case 0x0C00: // SUBLW
				this.intsructions.subLW((instruction & 0x00FF));
				break;
			case 0x0A00: // XORLW
				this.intsructions.xorLW((instruction & 0x00FF));
				break;
			case 0x0400: // retLW
				this.intsructions.returnLw(instruction & 0x00FF);
				this.incCycles();
				break;
			}
			break;

		default:
			System.out.println("Instruction not found");
			break;
		}
		this.incCycles();
		
		// If Interrupt Global allowed
		if ((this.memory.readRAM(11) & 0x80) > 0) {
			// Timer interrupt
			if ((this.memory.readRAM(11) & 0x4) > 0 && (this.memory.readRAM(11) & 0x20) > 0) {
				this.intsructions.call(4);
				this.memory.writeRAM(11, this.memory.readRAM(11) & 0x7F);
			}
			// Port RB0 interrupt
			if ((this.memory.readRAM(11) & 0x2) > 0 && (this.memory.readRAM(11) & 0x10) > 0) {
				this.intsructions.call(4);
				this.memory.writeRAM(11, this.memory.readRAM(11) & 0x7F);
			}
			// Port RB changed interrupt
			if ((this.memory.readRAM(11) & 0x1) > 0 && (this.memory.readRAM(11) & 0x8) > 0) {
				this.intsructions.call(4);
				this.memory.writeRAM(11, this.memory.readRAM(11) & 0x7F);
			}
		}
		/* For Program 5
		System.out.print("10h: " + Integer.toHexString(this.memory.readRAM(0x10)) + ", ");
		System.out.print("11h: " + Integer.toHexString(this.memory.readRAM(0x11)) + ", ");
		System.out.print("12h: " + Integer.toHexString(this.memory.readRAM(0x12)) + ", ");
		System.out.print("13h: " + Integer.toHexString(this.memory.readRAM(0x13)) + ", ");
		System.out.print("14h: " + Integer.toHexString(this.memory.readRAM(0x14)) + ", ");
		System.out.print("15h: " + Integer.toHexString(this.memory.readRAM(0x15)) + ", ");
		System.out.print("16h: " + Integer.toHexString(this.memory.readRAM(0x16)) + ", ");
		System.out.print("17h: " + Integer.toHexString(this.memory.readRAM(0x17)) + ", ");
		System.out.print("18h: " + Integer.toHexString(this.memory.readRAM(0x18)) + ", ");
		System.out.print("19h: " + Integer.toHexString(this.memory.readRAM(0x19)) + ", ");
		System.out.print("1Ah: " + Integer.toHexString(this.memory.readRAM(0x1A)) + ", ");
		System.out.print("1Bh: " + Integer.toHexString(this.memory.readRAM(0x1B)) + ", ");
		System.out.print("1Ch: " + Integer.toHexString(this.memory.readRAM(0x1C)) + ", ");
		System.out.print("1Dh: " + Integer.toHexString(this.memory.readRAM(0x1D)) + ", ");
		System.out.print("1Eh: " + Integer.toHexString(this.memory.readRAM(0x1E)) + ", ");
		System.out.println("1Fh: " + Integer.toHexString(this.memory.readRAM(0x1F)) + ", ");
		//*/
		
		//System.out.println("Wert1: " + Integer.toHexString(this.memory.readRAM(0x0C)));
		//System.out.println("Wert2: " + Integer.toHexString(this.memory.readRAM(0x0D)));
		//System.out.println("Ergebnis: " + this.memory.readRAM(0x0E));
		//System.out.println("10h: " + Integer.toHexString(this.memory.readRAM(0x10)));
	}

	private void incCycles() {
		this.cycles++;
		if ((this.memory.read(0x81) & 0x20) == 0)
			this.incTimer(false);
	}

	public synchronized void incTimer(boolean flanke) {
		if ((this.memory.read(0x81) & 0x20) > 0) {
			if ((this.memory.read(0x81) & 0x10) > 0) {
				//System.out.println("in timer");
				if (flanke == false) {
					this.timerCount++;
					//System.out.println(this.timerCount);
					if ((this.memory.read(0x81) & 0x8) > 0) {
						int temp = this.memory.readRAM(1) + 1;
						if (temp > 255)
							this.memory.writeRAM(11, this.memory.readRAM(11) | (1 << 2));
						this.memory.writeRAM(1, temp & 0xFF);
						this.timerCount = 0;
					} else if (this.timerCount == Math.pow(2, (this.memory.read(0x81) & 0x7) + 1)) {
						//System.out.println("in flanke");
						int temp = this.memory.readRAM(1) + 1;
						if (temp > 255)
							this.memory.writeRAM(11, this.memory.readRAM(11) | (1 << 2));
						this.memory.writeRAM(1, temp & 0xFF);
						this.timerCount = 0;
					}
				}
			} else {
				if (flanke == true) {
					this.timerCount++;
					//System.out.println(this.timerCount);
					if ((this.memory.read(0x81) & 0x8) > 0) {
						int temp = this.memory.readRAM(1) + 1;
						if (temp > 255)
							this.memory.writeRAM(11, this.memory.readRAM(11) | (1 << 2));
						this.memory.writeRAM(1, temp & 0xFF);
						this.timerCount = 0;
					} else if (this.timerCount == Math.pow(2, (this.memory.read(0x81) & 0x7) + 1)) {
						//System.out.println("in flanke");
						int temp = this.memory.readRAM(1) + 1;
						if (temp > 255)
							this.memory.writeRAM(11, this.memory.readRAM(11) | (1 << 2));
						this.memory.writeRAM(1, temp & 0xFF);
						this.timerCount = 0;
					}
				}
			}
		} else {
			this.timerCount++;
			if ((this.memory.read(0x81) & 0x8) > 0) {
				int temp = this.memory.readRAM(1) + 1;
				if (temp > 255)
					this.memory.writeRAM(11, this.memory.readRAM(11) | (1 << 2));
				this.memory.writeRAM(1, temp & 0xFF);
				this.timerCount = 0;
			} else if (this.timerCount == Math.pow(2, (this.memory.read(0x81) & 0x7) + 1)) {
				int temp = this.memory.readRAM(1) + 1;
				if (temp > 255)
					this.memory.writeRAM(11, this.memory.readRAM(11) | (1 << 2));
				this.memory.writeRAM(1, temp & 0xFF);
				this.timerCount = 0;
			}
		}
	}

	public boolean isBitSet(int address, int bit) {
		if ((this.memory.read(address) & (1 << bit)) > 0)
			return true;
		return false;
	}

	public void setBit(int address, int bit) {
		this.memory.write(address, (this.memory.read(address) | (1 << bit)));
	}

	public void clearBit(int address, int bit) {
		this.memory.write(address, (this.memory.read(address) & ~(1 << bit)));
	}

	public int getStatus(int regNumber) {
		if (regNumber != 0) {
			return this.memory.read(regNumber);
		} else {
			return this.memory.readWREG();
		}
	}

	public int getCycles() {
		return cycles;
	}
	
	public int getPCHigh() {
		return this.memory.getPcHigh();
	}

	public void setStatus(int regNumber, int value) {
		if (regNumber != 0) {
			this.memory.write(regNumber, value);
		}
	}

	public ObservableList<Line> getText() {
		return this.input.getFileString();
	}

	public void reset() {
		this.memory.write(0, 0);
		this.memory.write(1, 0);
		this.memory.write(2, 0);
		this.memory.write(3, 0x18);
		for (int i = 4; i < 0x81; i++)
			this.memory.write(i, 0);
		this.memory.write(0x81, 0xFF);
		this.memory.write(0x82, 0);
		this.memory.write(0x83, 0x18);
		this.memory.write(0x84, 0);
		this.memory.write(0x85, 0x1F);
		this.memory.write(0x86, 0xFF);
		for (int i = 0x87; i < 0xD0; i++)
			this.memory.write(i, 0);
		this.memory.writeWREG(0);
		this.memory.write(2, 0);
		this.cycles = 0;
		this.memory.setPcHigh(0);
		// this.timerCount = 0;
	}
}
