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
	public Instructions(Memory memory){
		this.memory = memory;
	}
	
	/**
	 * add a literal to the W Register
	 * @param value
	 */
	public void addLW(int value) {
		int temp = this.memory.readWREG();
		temp += value;
		if(temp > 255) { //set Carry Bit 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x01);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFE);
		}
		// set DC
		if(15 < ((this.memory.readWREG() & 0x0F)+(value & 0x0F))) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x02);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFD);
		}
		if(temp == 0) { //set Zero Bit
				this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
			}else{
				this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		this.memory.writeWREG(temp & 0xFF);
	}
	
	/**
	 * AND the W Register with a literal
	 * @param value
	 */
	public void andLW(int value){
		int temp = this.memory.readWREG() & value;
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		this.memory.writeWREG(temp & 0xFF);
	}
	
	/**
	 * OR the W Register with a literal
	 * @param value
	 */	
	public void iorLW(int value){
		int temp = this.memory.readWREG() | value;
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		this.memory.writeWREG(temp & 0xFF);
	}
	
	/**
	 * sub a literal to the W Register
	 * @param value
	 */
	public void subLW(int value){
		this.memory.writeWREG(((this.memory.readWREG() ^ 0xFF)+1));
		this.addLW(value);
	}
	
	/**
	 * copy a literal to the W Register
	 * @param value
	 */
	public void movLW(int value){
		this.memory.writeWREG(value);
	}
	
	/**
	 * XOR the W Register with a literal
	 * @param value
	 */
	public void xorLW(int value){
		int temp = this.memory.readWREG() ^ value;
		
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		
		this.memory.writeWREG(temp & 0xFF);
	}
	
	public void call(int address) {
		
	}
	
	public void ret() {
		
	}
	
	public void returnLw(int value) {
		
	}

	public void goTo(int value) {
		this.memory.writeRAM(PCL,value);	
	}

	public void addWf(int value) {
		
	}

	public void andWf(int value) {
		
	}

	public void clrW() {
		// TODO Auto-generated method stub
		
	}

	public void clrF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void comF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void decF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void decFSZ(int value) {
		// TODO Auto-generated method stub
		
	}

	public void incF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void incFSZ(int value) {
		// TODO Auto-generated method stub
		
	}

	public void ioWf(int value) {
		// TODO Auto-generated method stub
		
	}

	public void movF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void rlF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void rrF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void subWf(int value) {
		// TODO Auto-generated method stub
		
	}

	public void swapF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void xorWf(int value) {
		// TODO Auto-generated method stub
		
	}

	public void bcF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void bsF(int value) {
		// TODO Auto-generated method stub
		
	}

	public void btFSC(int value) {
		// TODO Auto-generated method stub
		
	}

	public void btFSS(int value) {
		// TODO Auto-generated method stub
		
	}

	public void movWf(int value) {
		// TODO Auto-generated method stub
		
	}

	public void nop() {
		// TODO Auto-generated method stub
		
	}

	public void clrWDT() {
		// TODO Auto-generated method stub
		
	}

	public void retFIE() {
		// TODO Auto-generated method stub
		
	}

	public void sleep() {
		// TODO Auto-generated method stub
		
	}
	
}
