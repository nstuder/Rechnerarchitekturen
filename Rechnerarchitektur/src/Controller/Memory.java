package Controller;
/**
 * 
 * @author FlorianGrunwald, NiklasStuder
 *
 * class for the Memory Operations 
 * contains Stack, RAM, Program Memory, Registers
 */
public class Memory {
	
	private int mainMemory[]; 
	private int stack[];
	private int programMemory[];
	private int stackPointer;
	private int w_Reg;
	private int pcHigh;
	
	/**
	 * Main Constructor 
	 * Initialize mainMemory,programmMemory,stack,stackPointer,w_Reg
	 */
	Memory(){
		mainMemory = new int[256];
		programMemory = new int[512];
		stack = new int[8];
		stackPointer = 0;
		w_Reg = 0;
		pcHigh = 0;
	}
	
	//Program Memory Operations
	/**
	 * Write a Value to the ProgrammMemory
	 * @param value the value for the Memory address 
	 * @param address the address of the Memory to write
	 */
	public void writeProgrammMemory(int value,int address) {
		programMemory[address] = value;
	}
	
	/**
	 * @param address of the Memory 
	 * @return value of the Memory at the address
	 */
	public int readProgramMemory(int address) {
		return programMemory[address];
	}
	
	//Stack Operations
	/**
	 * push a Value to the Stack
	 * if stack pointer > 7 the Pointer reset to 0
	 * @param value
	 */
	public void push(int value) {
		this.stack[this.stackPointer] = value;
		this.stackPointer++;
		this.stackPointer &= 0x7;
	}
	
	/**
	 * returns a value of the stack an decrement the stack pointer
	 * if stack pointer < 0 the value set to 7
	 * @return value of the stack at the current point
	 */
	public int pull() {
		int value = this.stack[this.stackPointer-1];
		this.stackPointer--;
		this.stackPointer &= 0x7;
		return value;
	}
	
	/**
	 * show the the content of the Program Memory
	 */
	public void showProgrammMemory(){
		for(int i = 0;i < 512;i++) {
			System.out.println(programMemory[i]);
		}
	}
	
	//W Register Operations
	/**
	 * 
	 * @return value of the W Register
	 */
	public int readWREG() {
		return this.w_Reg;
	}
	
	/**
	 * Write a value to the W Register
	 * @param value 
	 */
	public void writeWREG(int value) {
		this.w_Reg = value;
	}
	
	/**
	 * write to main Memory
	 * @param address
	 * @param value
	 */
	public void writeRAM(int address,int value) {
		if(address == 2) {
			//System.out.println("PCL ..." +  address);
			this.pcHigh = this.readRAM(0x0A);
		}
		if(address == 0) {
			this.mainMemory[this.mainMemory[4]] = value;
		}else {
			if((this.mainMemory[3] & (1<<5)) > 1) {
				if(address == 0 || address == 2 || address == 3 || address == 4 || address == 10 || address == 11) {
					this.mainMemory[address] = value;
				}else {
					this.mainMemory[address+128] = value;
				}
			}else {
				this.mainMemory[address] = value;
			}
		}
	}
	
	/**
	 * write to main Memory
	 * @param address
	 * @param value
	 */
	public void write(int address,int value) {
		this.mainMemory[address] = value;	
	}
	public int getPcHigh() {
		return pcHigh;
	}

	public void setPcHigh(int pcHigh) {
		this.pcHigh = pcHigh;
	}

	/**
	 * read from mainMemory
	 * @param address
	 * @return
	 */
	public int readRAM(int address) {
		if(address == 0) {
			return this.mainMemory[this.mainMemory[4]];
		}else {
			if((this.mainMemory[3] & (1<<5)) > 1) {
				if(address == 0 || address == 2 || address == 3 || address == 4 || address == 10 || address == 11) {
					return this.mainMemory[address];
				}else {
					return this.mainMemory[address+128];
				}
			}else {
				return this.mainMemory[address];
			}
		}
	}
	
	/**
	 * read from mainMemory
	 * @param address
	 * @return
	 */
	public int read(int address) {
		return this.mainMemory[address];
	}
}
