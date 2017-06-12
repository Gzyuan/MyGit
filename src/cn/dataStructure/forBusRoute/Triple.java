package cn.dataStructure.forBusRoute;

public class Triple {
	private int start,end;
	private int value;
	private Triple next;
	public Triple(int start, int end, int value, Triple next) {
		this.start = start;
		this.end = end;
		this.value = value;
		this.next = next;
	}
	public Triple(int start, int end, int value) {
		this(start,end,value,null);
	}
	public Triple(Triple next){
		this(0,0,0,next);
	}
	public Triple() {
		this(0,0,0,null);
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Triple getNext() {
		return next;
	}
	public void setNext(Triple next) {
		this.next = next;
	}
	
	
	
}
