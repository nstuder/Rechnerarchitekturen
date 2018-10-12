
public class Memory {
	
	private int mainMemory[]; 
	private int stack[];
	private int programMemory[];
	private int stackPointer;
	
	Memory(){
		mainMemory = new int[255];
		programMemory = new int[512];
		stack = new int[8];
		stackPointer = 0;
	}
	
	public boolean writeProgrammMemory(int value,int address) {
		programMemory[address] = value;
		return true;
	}
	
	public int readProgramMeomory(int address) {
		return programMemory[address];
	}
	
	public void push() {
		
	}
	
	public void pull() {
		
	}
	
	public void showProgrammMemory(){
		for(int i = 0;i < 512;i++) {
			System.out.println(programMemory[i]);
		}
	}
}
