package cn.dataStructure.forBusRoute;

public class Station {
	private String name;
	private int parentIndex;
	private int childIndex;
	public Station(String name, int parentIndex, int childIndex) {
		//super();
		this.name = name;
		this.parentIndex = parentIndex;
		this.childIndex = childIndex;
	}
	
	public Station(String name) {
		//super();
		this(name,-1,-1);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentIndex() {
		return parentIndex;
	}
	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}
	public int getChildIndex() {
		return childIndex;
	}
	public void setChildIndex(int childIndex) {
		this.childIndex = childIndex;
	}
	
}
