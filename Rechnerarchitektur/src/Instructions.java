/**
 * Class for each Instruction of the Micro Controller
 * @author FlorianGrunwald, NiklasStuder
 *
 */
public class Instructions {
	public static final int PCL = 2;
	public static final int STATUS = 3;
	Memory memory;
	/**
	 * initialize memory
	 * @param memory
	 */
	Instructions(Memory memory){
		this.memory = memory;
	}
	
	/**
	 * add a literal to the W Register
	 * @param value
	 */
	void addLW(int value) {
		int temp = this.memory.readWREG() + value;
		if(temp > 255) this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x01);
		if(temp > 127) this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x02);
		if(temp == 0) this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		this.memory.writeWREG(temp & 0xFF);
	}
	
	/**
	 * AND the W Register with a literal
	 * @param value
	 */
	void andLW(int value){
		int temp = this.memory.readWREG() & value;
		if(temp == 0) this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		this.memory.writeWREG(temp & 0xFF);
	}
	
	/**
	 * OR the W Register with a literal
	 * @param value
	 */	
	void iorLW(int value){
		int temp = this.memory.readWREG() | value;
		if(temp == 0) this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		this.memory.writeWREG(temp & 0xFF);
	}
	
	/**
	 * sub a literal to the W Register
	 * @param value
	 */
	void subLW(int value){
		int temp = this.memory.readWREG() - value;
		if (temp < 0) {
			temp ^= 0xFFFFFFFF;
			temp++;
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x01);
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x02);
		}
		if(temp > 255) this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x01);
		if(temp > 127) this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x02);
		if(temp == 0) this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		this.memory.writeWREG(temp & 0xFF);
	}
	
	/**
	 * copy a literal to the W Register
	 * @param value
	 */
	void movLW(int value){
		this.memory.writeWREG(value);
	}
	
	/**
	 * XOR the W Register with a literal
	 * @param value
	 */
	void xorLW(int value){
		int temp = this.memory.readWREG() ^ value;
		if(temp == 0) this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		this.memory.writeWREG(temp & 0xFF);
	}
}
