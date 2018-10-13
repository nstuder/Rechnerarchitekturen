


public class Instructions {
	
	Memory memory;
	
	Instructions(Memory memory){
		this.memory = memory;
	}
	
	
	void addLW(int address) {
		
	}
	
	void andLW(){
		
	}
	
	void iorLW(){
		
	}
	
	void subLW(){
		
	}
	
	void goTo(){
		
	}
	
	void movLW(int value){
		this.memory.writeWREG(value);
	}
	
	void xorLW(){
		
	}
}
