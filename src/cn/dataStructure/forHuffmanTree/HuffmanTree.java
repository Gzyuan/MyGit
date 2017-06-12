package cn.dataStructure.forHuffmanTree;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class HuffmanTree {
	private List<Element> charList = null;//保存字符的列表
	private TreeNode<Element>[] huftree = null;//huffman树
	private Map<Integer,String> codeMap = null;//保存huf编码的map
	public HuffmanTree(List<Element> charList){
		this.charList = charList;
		int n = this.charList.size();
		huftree = new TreeNode[2*n-1];
		for(int i=0;i<n;i++){
			this.huftree[i] = new TreeNode<Element>(this.charList.get(i));
		}
		
		for(int i=n;i<2*n-1;i++){
			int min1=Integer.MAX_VALUE;
			int min2=min1;
			int x1=-1,x2=-1;
			for(int j=0;j<i;j++){
				if(this.huftree[j].getParent()==-1){
					if(this.huftree[j].getData().getNum()<min1){
						min2=min1;
						x2 = x1;
						min1 = this.huftree[j].getData().getNum();
						x1 = j;
					}else if(this.huftree[j].getData().getNum()<min2){
						min2 = this.huftree[j].getData().getNum();
						x2 = j;
					}
				}
			}
				this.huftree[x1].setParent(i);
				this.huftree[x2].setParent(i);
				this.huftree[i]=new TreeNode<Element>(new Element(min1+min2), -1, x1, x2);
		}
		this.setCode();//把huf编码保存的element里面
		this.setCodeMap();//把huf编码保存到map
	}
	
	private String getCode(int i){
		int n = this.charList.size();
		char hufcode[] = new char[n];
		int child = i;
		int	parent = this.huftree[child].getParent();
		for(i=n-1;parent!=-1;i--){
			hufcode[i] = (this.huftree[parent].getLeft()==child)?'0':'1';
			child = parent;
			parent = huftree[child].getParent();
		}
		return new String(hufcode,i+1,n-1-i);
	}
	private void setCode(){
		int n = this.charList.size();
		for(int i=0;i<n;i++){
			this.charList.get(i).setCode(getCode(i));
		}
	}
	
	public boolean isEmpty(){
		return huftree == null;
	}
	
	
	
	public List<Element> getCharList() {
		return charList;
	}

	//得到huf编码
	private void setCodeMap(){
		codeMap = new HashMap<>();
		for(int i=0;i<charList.size();i++){
			codeMap.put(new Integer((int)charList.get(i).getName()),charList.get(i).getCode());
		}
	}
	
	//通过huf编码转源文件
	public String changeData(String data){
		if(codeMap==null){
			return null;
		}
		StringBuffer huffData = new StringBuffer();
		for(int i=0;i<data.length();i++){
			char temp = data.charAt(i);
			if((int)temp!=13){
				huffData.append(codeMap.get((int)temp));
			}
		}
		return huffData.toString();
	}
	
	//通过huf树解压
	public String decode(String compressed){
		if(codeMap==null){
			return null;
		}
		String text = "";
		int node = this.huftree.length-1;
		for(int i=0;i<compressed.length();i++){
			if(compressed.charAt(i)=='0'){
				node = huftree[node].getLeft();
			}else{
				node = huftree[node].getRight();
			}
			if(huftree[node].isLeaf()){
				text+=(char)huftree[node].getData().getName();
				node=this.huftree.length-1;
			}	
		}
		return text;
	}
}