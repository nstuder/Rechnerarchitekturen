
public class TestMicro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Microcontroller myMicro = new Microcontroller();
		Memory memory = new Memory();
		Input file = new Input("C:\\Users\\NiklasStuder\\eclipse-workspace3\\Rechnerarchitekturen\\src\\TPicSim1.LST", memory);
		
		file.getData();
		
		memory.showProgrammMemory();
	}

}
