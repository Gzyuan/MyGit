package cn.dataStructure.forHuffmanTree;

public class ByteUtil {
	//�������ַ���������ֽ�����
	public static byte[] changeType(String data){
		int size;//�ֽ����鳤��
		if((data.length()%8)==0){
			size = data.length()/8+1;//�������ַ��պ���8λ,���λ����0��
		}else{
			size = data.length()/8+2;//�������ַ�����8λ,���ȼ�һ,���λ����0��
		}
		byte[] dataGroup = new byte[size];//�����ֽ�����
		if((data.length()%8)==0){
			dataGroup[dataGroup.length-1]=(byte)0;//��0��Ϊ0
		}else{
			int num =8-(data.length()%8);//������ݲ���8λ������
			dataGroup[dataGroup.length-1]=(byte)num;//�������ֽ������ĩβ
			//�ڶ������ַ������油0
			for(int i=0;i<num;i++){
				data+="0";
			}
		}
		//ѭ����ȡ�������ַ�������ת���ֽڱ��浽�ֽ�����
		for(int i=0;i<data.length()/8;i++){
			String s = data.substring(8*i, 8*(i+1));//ÿ8λ�ͽس���
			byte num = changeData(s);
			dataGroup[i] = num ;
		}
		return dataGroup;
	}
	//ͨ���������ַ���ת���ֽ�
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
	//�ֽ�ת�ɶ������ַ���
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
