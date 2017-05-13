package cn.dataStructure.forHuffmanTree;

public class TreeNode {
	private int data;//数据
	private int parent;//父母
	private int left;//左孩子
	private int right;//右孩子
	public TreeNode(int data, int parent, int left, int right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	public TreeNode(int data) {
		this(data, -1, -1,-1);
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "TreeNode [data=" + data + ", parent=" + parent + ", left=" + left + ", right=" + right + "]";
	}
	
}
