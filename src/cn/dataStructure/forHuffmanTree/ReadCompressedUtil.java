package cn.dataStructure.forHuffmanTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadCompressedUtil {
	private StringBuffer data = null;
	private byte[] head;
	public ReadCompressedUtil(File path){
		InputStream in = null;
		head = new byte[1024];
		this.data = new StringBuffer();
		try {
			in = new FileInputStream(path);
			int last=0;
			int i;
			int j=0;
			while((i=in.read())!=-1){
				head[j]=(byte) i;
				if(last==13&&i==10){
					break;
				}
				last = i;
				j++;
			}
			while((i=in.read())!=-1){
				this.data.append(ByteUtil.printBinary((byte)i));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getData(){
		return data.toString();
	}
	
	public List<Element> getElementList(){
		String s = null;
		try {
			s = new String(head,"iso-8859-1");
			List<Element> list= new ArrayList<>();
			String[] ss = s.split(",");
			for(int j=0;j<ss.length-1;j++){
				String[] temp = ss[j].split(" ");
				list.add(new Element((char)Byte.parseByte(temp[0]), Integer.valueOf(temp[1])));
			}	
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String getCompressed(){
		int size = this.data.length()/8;
		int num = Integer.parseInt(this.data.substring(8*(size-1), 8*size), 2);
		String compressed = this.data.substring(0, 8*(size-1)-num);
		return compressed;
	}
}
