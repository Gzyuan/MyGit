package cn.dataStructure.forHuffmanTree;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class WriteUtil {
	public static void writeData(String path,byte[] data){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			out.write(data);
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static byte[] createHead(List<Element> list){
		String head = "";
		for(int i=0;i<list.size();i++){
			head+=(byte)list.get(i).getName()+" "+list.get(i).getNum()+",";
		}
		head+="\r\n";
		try {
			return head.getBytes("iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] combine(byte[] head,byte[] data){
			byte[] newData = new byte[head.length+data.length];
			for(int i=0;i<head.length;i++){
				newData[i] = head[i];
			}
			for(int j=0;j<data.length;j++){
				newData[head.length+j] = data[j];
			}
			return newData;
	}
	
}
