/**
 * Class for each Instruction of the Micro Controller
 * @author FlorianGrunwald, NiklasStuder
 *
 */
public class Instructions {
	
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
		
	}
	
	/**
	 * AND the W Register with a literal
	 * @param value
	 */
	void andLW(int value){
		
	}
	
	/**
	 * OR the W Register with a literal
	 * @param value
	 */	
	void iorLW(int value){
		
	}
	
	/**
	 * sub a literal to the W Register
	 * @param value
	 */
	void subLW(){
		
	}
	
	/**
	 * goto an address
	 * @param address to jump
	 */
	void goTo(int address){
		
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
	void xorLW(){
		
	}
}
