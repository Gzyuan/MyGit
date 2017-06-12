package cn.dataStructure.forHuffmanTree;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadSourceUtil { 
	private int[] countNum = null;
	private StringBuffer data = null;
	public ReadSourceUtil(File path){
		InputStream in = null;
		this.countNum = new int[256];//ascii¬Î”–256Œª
		this.data = new StringBuffer();
		try {
			in = new FileInputStream(path);
			int i;
			while((i=in.read())!=-1){
				countNum[i]++;
				this.data.append((char)i);
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
		this.countNum[13]=0;
	}
	
	public List<Element> getElementList(){
		if(this.countNum==null){
			return null;
		}
		List<Element> list= new ArrayList<>();
		for(int i=0;i<this.countNum.length;i++){
			if(this.countNum[i]>0){
				list.add(new Element((char)i,this.countNum[i]));
			}
		}
		return list;
	}
	
	public String getData(){
		return data.toString();
	}
}
