package cn.dataStructure.forHuffmanTree;

public class TreeNode<T> {
	private T data;//数据
	private int parent;//父母
	private int left;//左孩子
	private int right;//右孩子
	public TreeNode(T data) {
		this(data,-1,-1,-1);
	}
	public TreeNode(T data, int parent, int left, int right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
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
	public boolean isLeaf(){
		if(this.left==-1&&this.right==-1){
			return true;
		}
		return false;
	}
}
