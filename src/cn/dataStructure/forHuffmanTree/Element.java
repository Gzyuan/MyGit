package cn.dataStructure.forHuffmanTree;

public class Element {
	private char name;
	private int num;
	private String code;
	public char getName() {
		return name;
	}
	public void setName(char name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Element(char name, int num, String code) {
		super();
		this.name = name;
		this.num = num;
		this.code = code;
	}
	public Element(char name, int num) {
		super();
		this.name = name;
		this.num = num;
	}
	public Element(char name) {
		this(name,0,"");
	}
	public Element(int num) {
		this(' ',num,"");
	}
	
}

