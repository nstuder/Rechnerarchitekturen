package GUI;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Line {
	private SimpleIntegerProperty lineNumber;
	private SimpleStringProperty lineData;
	private SimpleIntegerProperty progamCount;
	
	public Line(int lineNumber, String lineData, int progamCount) {
		this.lineNumber = new SimpleIntegerProperty(lineNumber);
		this.lineData = new SimpleStringProperty(lineData);
		this.progamCount = new SimpleIntegerProperty(progamCount);
	}

	public int getLineNumber() {
		return lineNumber.get();
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = new SimpleIntegerProperty(lineNumber);
	}

	public String getLineData() {
		return lineData.get();
	}

	public void setLineData(String lineData) {
		this.lineData = new SimpleStringProperty(lineData);
	}

	public int getProgamCount() {
		return progamCount.get();
	}

	public void setProgamCount(int progamCount) {
		this.progamCount = new SimpleIntegerProperty(progamCount);
	}
}
