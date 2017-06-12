package cn.dataStructure.forHuffmanTree;

public class ByteUtil {
	//二进制字符串处理成字节数组
	public static byte[] changeType(String data){
		int size;//字节数组长度
		if((data.length()%8)==0){
			size = data.length()/8+1;//二进制字符刚好满8位,最后位留添0数
		}else{
			size = data.length()/8+2;//二进制字符不满8位,长度加一,最后位留添0数
		}
		byte[] dataGroup = new byte[size];//创建字节数组
		if((data.length()%8)==0){
			dataGroup[dataGroup.length-1]=(byte)0;//添0数为0
		}else{
			int num =8-(data.length()%8);//算出数据不够8位的数量
			dataGroup[dataGroup.length-1]=(byte)num;//保存在字节数组的末尾
			//在二进制字符串后面补0
			for(int i=0;i<num;i++){
				data+="0";
			}
		}
		//循环截取二进制字符串，并转成字节保存到字节数组
		for(int i=0;i<data.length()/8;i++){
			String s = data.substring(8*i, 8*(i+1));//每8位就截出来
			byte num = changeData(s);
			dataGroup[i] = num ;
		}
		return dataGroup;
	}
	//通过二进制字符串转成字节
	public static Byte changeData(String data){
		String first = data.substring(0, 1);
		if(first.equals("1")){
			String s = "";
			for(int i=data.length()-1;i>=0;i--){
				if(data.charAt(i)=='1'){
					s="1"+s;
					for(int j=i-1;j>=0;j--){
						if(data.charAt(j)=='1'){
							s="0"+s;
						}else if(data.charAt(j)=='0'){
							s="1"+s;
						}
					}
					break;
				}
				s="0"+s;
			}
			return (byte) -Integer.parseInt(s, 2);
		}else if(first.equals("0")){
			return Byte.parseByte(data, 2);
		}else{
			return 0;
		}
	}
	//字节转成二进制字符串
	public static String printBinary(byte i){
		String s = "";
	      for(int j=7;j>=0;j--){
	          if(((1<<j)& i)!=0){
	              s+="1";
	              }else{
	              s+="0";
	              }
	          }
	          return s;
	   }
	
	
}
