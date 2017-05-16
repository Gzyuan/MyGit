package cn.dataStructure.forHuffmanTree;

import java.util.Arrays;

public class HuffmanTree {
	private String cherset = null;
	private TreeNode[] huffmanTree = null;
	
	
	
	
	
	
	
	public String getCherset() {
		return cherset;
	}
	public void setCherset(String cherset) {
		this.cherset = cherset;
	}
	public TreeNode[] getHuffmanTree() {
		return huffmanTree;
	}
	public void setHuffmanTree(TreeNode[] huffmanTree) {
		this.huffmanTree = huffmanTree;
	}
	@Override
	public String toString() {
		return "HuffmanTree [cherset=" + cherset + ", huffmanTree=" + Arrays.toString(huffmanTree) + "]";
	}

}
