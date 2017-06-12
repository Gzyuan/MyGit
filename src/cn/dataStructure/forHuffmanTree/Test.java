package cn.dataStructure.forHuffmanTree;

import java.io.File;
import java.util.List;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ReadSourceUtil readUtil = new ReadSourceUtil(new File("F:/test.txt"));
			List<Element> list = readUtil.getElementList();
			HuffmanTree tree = new HuffmanTree(list);
			byte[] data = ByteUtil.changeType(tree.changeData(readUtil.getData()));
			byte[] head = WriteUtil.createHead(tree.getCharList());
			WriteUtil.writeData("F:/test.abc", WriteUtil.combine(head, data));
			
			
			
			ReadCompressedUtil readUtil1 = new ReadCompressedUtil(new File("F:/test.abc"));
			HuffmanTree tree1 = new HuffmanTree(readUtil1.getElementList());
			System.out.println(tree1.decode(readUtil1.getData()));
	}

}


