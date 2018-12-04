package Controller;
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
		this.memory.push((this.memory.getPcHigh() << 8) + this.memory.readRAM(PCL));
		this.memory.writeRAM(PCL,address & 0xFF);
		this.memory.setPcHigh(this.memory.read(0xA));
	}
	
	public void ret() {
		int temp = this.memory.pull();
		this.memory.setPcHigh(temp >> 8);
		this.memory.writeRAM(PCL,temp & 0xFF);
	}
	
	public void returnLw(int value) {
		this.memory.writeWREG(value);
		this.ret();
	}

	public void goTo(int value) {
		this.memory.writeRAM(PCL,value & 0xFF);
		//fehler???
		this.memory.setPcHigh(value >> 8);
	}

	public void addWf(int value) {
		int temp = this.memory.readRAM(value & 0x7F);
		temp += this.memory.readWREG();
		if(temp > 255) { //set Carry Bit 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x01);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFE);
		}
		// set DC
		if(15 < ((this.memory.readWREG() & 0x0F)+(this.memory.readRAM(value & 0x7F) & 0x0F))) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x02);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFD);
		}
		if(temp == 0) { //set Zero Bit
				this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
			}else{
				this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value  & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void andWf(int value) {
		int temp = this.memory.readWREG() & this.memory.readRAM(value  & 0x7F);
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void clrW() {
		this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		this.memory.writeWREG(0);
	}

	public void clrF(int value) {
		this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		this.memory.writeRAM(value & 0x7F,0);
	}

	public void comF(int value) {
		int temp = 0xFF ^ this.memory.readRAM(value & 0x7F);
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void decF(int value) {
		int temp = this.memory.readRAM(value  & 0x7F) -1;
		if(temp == -1) temp = 0xFF;
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void decFSZ(int value) {
		int temp = this.memory.readRAM(value  & 0x7F) -1;
		if(temp == -1) temp = 0xFF;
		if(temp == 0) this.memory.writeRAM(PCL, this.memory.readRAM(PCL)+1); 
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void incF(int value) {
		int temp = ((this.memory.readRAM(value  & 0x7F) +1) & 0xFF);
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp);
		}else {
			this.memory.writeWREG(temp);
		}
	}

	public void incFSZ(int value) {
		int temp = (this.memory.readRAM(value  & 0x7F) +1) & 0xFF;
		if(temp == 0) this.memory.writeRAM(PCL, this.memory.readRAM(PCL)+1); 
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void ioWf(int value) {
		int temp = this.memory.readRAM(value  & 0x7F) | this.memory.readWREG();
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void movF(int value) {
		int temp = this.memory.readRAM(value  & 0x7F);
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void rlF(int value) {
		int temp = this.memory.readRAM(value  & 0x7F) << 1;
		temp += this.memory.readRAM(STATUS) & 0x1;
		if(temp > 255) { //set Carry Bit 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x01);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFE);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void rrF(int value) {
		int tempCarry = this.memory.readRAM(value  & 0x7F) & 0x1;
		int temp = this.memory.readRAM(value  & 0x7F) >> 1;
		
		temp += (this.memory.readRAM(STATUS) & 0x1) << 7;
		if(tempCarry > 0) { //set Carry Bit 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x01);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFE);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void subWf(int value) {
		int temp = this.memory.readWREG();
		this.memory.writeWREG(((temp ^ 0xFF)+1) & 0xFF);
		this.addWf(value);
		if((value & 0x80) > 0) {
			this.memory.writeWREG(temp & 0xFF);
		}
	}

	public void swapF(int value) {
		int tempLower = (this.memory.readRAM(value & 0x7F) & 0xF0) >> 4;
		int tempUpper = (this.memory.readRAM(value & 0x7F) & 0x0F) << 4;
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,tempUpper + tempLower);
		}else {
			this.memory.writeWREG(tempUpper + tempLower);
		}
	}

	public void xorWf(int value) {
		int temp = this.memory.readRAM(value  & 0x7F) ^ this.memory.readWREG();
		if(temp == 0) { 
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) | 0x04);
		}else{
			this.memory.writeRAM(STATUS,this.memory.readRAM(STATUS) & 0xFB);
		}
		if((value & 0x80) > 0) {
			this.memory.writeRAM(value & 0x7F,temp & 0xFF);
		}else {
			this.memory.writeWREG(temp & 0xFF);
		}
		
	}

	public void bcF(int value) {
		int bitPosition = value >> 7;
		int address = value & 0x7F;
		this.memory.writeRAM(address,this.memory.readRAM(address) & (~(0x1 << bitPosition)));
	}

	public void bsF(int value) {
		int bitPosition = value >> 7;
		int address = value & 0x7F;
		this.memory.writeRAM(address,this.memory.readRAM(address) | (0x1 << bitPosition));
	}

	public void btFSC(int value) {
		int bitPosition = value >> 7;
		int address = value & 0x7F;
		if(0 == (this.memory.readRAM(address) & (0x1 << bitPosition))){
			this.memory.writeRAM(PCL, this.memory.readRAM(PCL)+1);
		}
	}

	public void btFSS(int value) {
		int bitPosition = value >> 7;
		int address = value & 0x7F;
		if(0 < (this.memory.readRAM(address) & (0x1 << bitPosition))){
			this.memory.writeRAM(PCL, this.memory.readRAM(PCL)+1);
		}
	}
	/**
	 * 
	 * @param value
	 */
	public void movWf(int value) {
		this.memory.writeRAM(value & 0x7F, this.memory.readWREG());
	}

	public void nop() {
		//do nothing
		
	}

	public void clrWDT() {
		// TODO Auto-generated method stub
		
	}

	public void retFIE() {
		this.memory.writeRAM(11,this.memory.readRAM(11) & 0x7F);
		this.ret();
	}

	public void sleep() {
		
		// TODO Auto-generated method stub
		
	}
	
}
