import javafx.beans.property.SimpleStringProperty;

public class Register{
    private SimpleStringProperty name;
    private SimpleStringProperty hex;
    private SimpleStringProperty Bit0;
    private SimpleStringProperty Bit1;
    private SimpleStringProperty Bit2;
    private SimpleStringProperty Bit3;
    private SimpleStringProperty Bit4;
    private SimpleStringProperty Bit5;
    private SimpleStringProperty Bit6;
    private SimpleStringProperty Bit7;

    public Register(String name, int hexCode) {
        this.name = new SimpleStringProperty(name);
        setNewValue(hexCode);
    }

    public String isBitSet(int value, int position) {
        if((value & 1 << position) !=0) {
        	return "1";
        }else {
        	return "0";
        }
    }
    
    public String getBit0() {
		return Bit0.get();
	}

	public void setBit0(SimpleStringProperty bit0) {
		Bit0 = bit0;
	}

	public String getBit1() {
		return Bit1.get();
	}

	public void setBit1(SimpleStringProperty bit1) {
		Bit1 = bit1;
	}

	public String getBit2() {
		return Bit2.get();
	}

	public void setBit2(SimpleStringProperty bit2) {
		Bit2 = bit2;
	}

	public String getBit3() {
		return Bit3.get();
	}

	public void setBit3(SimpleStringProperty bit3) {
		Bit3 = bit3;
	}

	public String getBit4() {
		return Bit4.get();
	}

	public void setBit4(SimpleStringProperty bit4) {
		Bit4 = bit4;
	}

	public String getBit5() {
		return Bit5.get();
	}

	public void setBit5(SimpleStringProperty bit5) {
		Bit5 = bit5;
	}

	public String getBit6() {
		return Bit6.get();
	}

	public void setBit6(SimpleStringProperty bit6) {
		Bit6 = bit6;
	}

	public String getBit7() {
		return Bit7.get();
	}

	public void setBit7(SimpleStringProperty bit7) {
		Bit7 = bit7;
	}

	public String getName() {
    	return this.name.get();
    }
    
    public String getHex() {
    	return this.hex.get();
    }
    
    public void setName(String name) {
    	this.name = new SimpleStringProperty(name);
    }
    
    public void getHex(int hex) {
    	 this.hex =  new SimpleStringProperty("0x" + Integer.toHexString(hex));
    }
    
    public void setNewValue(int hexCode) {
        this.hex =  new SimpleStringProperty("0x" + Integer.toHexString(hexCode));
        this.Bit0 = new SimpleStringProperty(isBitSet(hexCode,0));
        this.Bit1 = new SimpleStringProperty(isBitSet(hexCode,1));
        this.Bit2 = new SimpleStringProperty(isBitSet(hexCode,2));
        this.Bit3 = new SimpleStringProperty(isBitSet(hexCode,3));
        this.Bit4 = new SimpleStringProperty(isBitSet(hexCode,4));
        this.Bit5 = new SimpleStringProperty(isBitSet(hexCode,5));
        this.Bit6 = new SimpleStringProperty(isBitSet(hexCode,6));
        this.Bit7 = new SimpleStringProperty(isBitSet(hexCode,7));
    }
}