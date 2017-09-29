package cn.dataStructure.forBusRoute;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * @author gzy39
 *ͼ�Ķ�������������ͨ·�����·��
 */

public class AdjListGraph {
	private Map<String,Triple> adjlist = null;
	private String[] vertices=null;
	private int shortestDistance=-1;//��ʱ�����
	public AdjListGraph(String[]vertices,Triple[] edges){
		this.vertices=vertices;
		adjlist = new HashMap<String,Triple>();
		int len = vertices.length;//�õ���ĳ���
		Triple[] matrx = getMatrx(len,edges);
		for(int i=0;i<len;i++){
			Triple temp = new Triple();
			temp.setNext(matrx[i]);
			adjlist.put(vertices[i], temp);
		}
	}
	//�ѱ�����ת�ɵ���������
	private Triple[] getMatrx(int len,Triple[] edges){
			Triple[] matrx = new Triple[len];
			if(edges[0].getStart()!=0){
				return edges;
			}
			int index = 1;//����matrx��һ���ߵ�����ֵ��һ
			for(int i=0;i<len;i++){
				matrx[i]=edges[index-1];
				for(int j=index;j<edges.length;j++){
					if(i==edges[j].getStart()){
						edges[j-1].setNext(edges[j]);//ǰһ����ָ����һ��
						index++;//����һ��
					}else{
						index++;//����һ��
						break;
					}
				}
			}
		return matrx;
	}
	
	//��ȡ�ߵĶ���
	public Triple get(int start,int end){
		Triple temp = this.adjlist.get(this.vertices[start]);//��ֻ���ڽӱ�
		if(!(temp.getNext()==null)){
			temp = temp.getNext();
		}
		while(!(temp.getNext()==null)){
			if(temp.getEnd()==end){
				return temp;
			}
			temp = temp.getNext();
		}
		if(temp.getEnd()==end){
			return temp;
		}
		return null;
	}
	
	
	
	
	//����·��
	public List<ArrayList<String>> getPath(String start,String end){
		
		List<ArrayList<String>> allPath = new ArrayList<>();//����˵��·�ߵļ���
		Stack<String> stack = new Stack<String>();
		Map<String, Queue<String>> queueMap = new HashMap<String, Queue<String>>();//�����������ڽӵ�Ķ���
		//�ѿ�ʼ�ĵ��ջ
		stack.push(start);
		Triple temp = this.adjlist.get(start).getNext();//��Ϊ��һ���ó��������ڽӱ�����Ҫnext���ǵ�һ����
		String top = this.vertices[temp.getStart()];//����ջ��Ԫ�أ�ָ��
		String ss = "";//���ڲ�����Ԫ��
		while(!(stack.isEmpty())){
			Queue<String> topQueue = queueMap.get(top);//�ó���top���ڽӵ�Ķ���
			if(topQueue==null){
				/***�������������top���ڽӵ�***/
					temp=this.adjlist.get(top);
					Queue<String> queue = new LinkedList<String>();
					while(!(temp.getNext()==null)){
						queue.add(this.vertices[temp.getNext().getEnd()]);
						temp = temp.getNext();
					}
					queueMap.put(top, queue);
				/*******/
					topQueue = queueMap.get(top);
			}
			ss = topQueue.poll();//���г���
			if(null == ss){//�����Ѿ�����
					stack.pop();//��ջ
					queueMap.remove(top);//�ѿն���ȥ��
					if(!(stack.isEmpty())){//��Ϊջ��ʱpeek�����ᱨ�쳣
						top=stack.peek();//��top����ָ��ջ��Ԫ��
						}
					continue;
			}
			if(stack.contains(ss)){//�������ջ���棬����ջ
				continue;//������һ��ѭ��
			}else{
				if(ss.equals(end)){//��Ⱦ����
					stack.add(ss);//
					//���
					Iterator<String> iterator = stack.iterator();//������ѭ������·��
					ArrayList<String> route = new ArrayList<String>();//һ��ͨ·
					while(iterator.hasNext()){
						route.add(iterator.next());
					}
					allPath.add(route);
					stack.pop();
					continue;//������һ��ѭ��
				}else{
					stack.push(ss);//����Ⱦ���ջ
				}
				
			}
			top=stack.peek();//��ȡջ��Ԫ��
		}
		
		return allPath;
	}
	
	//��ȡ�ߵĳ���
	public int weight(int i,int j){
		if(i==j){
			return 0;
		}
		Triple temp = get(i, j);
		if(temp==null){
			return Integer.MAX_VALUE;
		}
		return temp.getValue();
	}
	
	//���·��
	public Stack<String> getShortestPath(int startIndex,int endIndex){
		
		int n = this.vertices.length;//ͼ�Ķ�����
		int i = startIndex;
		boolean[] vset = new boolean[n];
		vset[startIndex] = true;
		int[] path = new int[n];
		int[] dist = new int[n];
		for(int j=0;j<n;j++){
			dist[j] = this.weight(i, j);
			path[j] = (j!=i&&dist[j]<Integer.MAX_VALUE)?i:-1;
		}
		for(int j=(i+1)%n;j!=i;j=(j+1)%n){
			int mindist = Integer.MAX_VALUE,min = 0;
			for(int k=0;k<n;k++){
				if(!vset[k]&&dist[k]<mindist){
					mindist = dist[k];
					min = k;
				}
			}
			if(mindist==Integer.MAX_VALUE){
				break;
			}
			
			vset[min]=true;
			for(int k=0;k<n;k++){
				if(!vset[k]&&this.weight(min, k)<Integer.MAX_VALUE&&dist[min]+this.weight(min, k)<dist[k]){
					dist[k] = dist[min]+this.weight(min, k);
					path[k] = min;
				}
			}
		}
		Stack<String> shortestPath = new Stack<String>();
		int b = endIndex;
		while(true){
			shortestPath.push(this.vertices[b]);
			b = path[b];
			if(b==startIndex){
				shortestPath.push(this.vertices[b]);
				break;
			}
		}
		this.shortestDistance = dist[endIndex];
		return shortestPath;
	}
	//��ȡ���·���ĳ���
	public int getShortestDistance() {
		return shortestDistance;
	}
}
