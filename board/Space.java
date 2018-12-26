package board;

public class Space {
	private int status;
	private int value;
	
	public Space(int status, int value) {
		this.status = status;
		this.value = value;
	}
	public Space() {
		this(Angel.STATUS_EMPTY, 0);
	}
	
	public int getStatus() {
		return status;
	}
	public int getValue() {
		return value;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
