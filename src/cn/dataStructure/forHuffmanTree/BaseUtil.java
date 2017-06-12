package cn.dataStructure.forHuffmanTree;

import java.io.File;

public class BaseUtil {
	public String openFile(File file){
		String content = "";
		try {
			ReadSourceUtil readUtil = new ReadSourceUtil(file);
			content = readUtil.getData();
		} catch (Exception e) {
			// TODO: handle exception
			return "��ʧ��\n";
		}
		return content+"\n";
	}
	
	
	public String compressFile(File file){
		String oldpath= null;
		String newpath = null;
		try {
			oldpath = file.getAbsolutePath();
			newpath = oldpath.replaceAll(".txt", ".abc");
			ReadSourceUtil readUtil = new ReadSourceUtil(file);
			HuffmanTree tree = new HuffmanTree(readUtil.getElementList());
			byte[] data = ByteUtil.changeType(tree.changeData(readUtil.getData()));
			byte[] head = WriteUtil.createHead(tree.getCharList());
			WriteUtil.writeData(newpath, WriteUtil.combine(head, data));
		} catch (Exception e) {
			// TODO: handle exception
			return "ѹ��ʧ��\n";
		}
		return "ѹ���ɹ���������"+newpath+"\n";
	}
	public String decompressFile(File file){
		String content = "";
		String oldpath= null;
		String newpath = null;
		try {
			oldpath = file.getAbsolutePath();
			newpath = oldpath.replaceAll(".abc", ".txt");
			ReadCompressedUtil readUtil = new ReadCompressedUtil(file);
			HuffmanTree tree = new HuffmanTree(readUtil.getElementList());
			content=tree.decode(readUtil.getData());
		} catch (Exception e) {
			// TODO: handle exception
			return "��ѹʧ��\n";
		}
		return "��ѹ�ɹ�:\n"+content+"\n";
	}
}
