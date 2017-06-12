package cn.dataStructure.forBusRoute;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;


public class AdjListGraph {
	private Map<String,Triple> adjlist = null;
	private String[] vertices=null;
	private int shortestDistance=-1;//临时保存的
	public AdjListGraph(String[]vertices,Triple[] edges){
		this.vertices=vertices;
		adjlist = new HashMap<String,Triple>();
		int len = vertices.length;//得到点的长度
		Triple[] matrx = getMatrx(len,edges);
		for(int i=0;i<len;i++){
			Triple temp = new Triple();
			temp.setNext(matrx[i]);
			adjlist.put(vertices[i], temp);
		}
	}
	//把边数组转成单链表数组
	private Triple[] getMatrx(int len,Triple[] edges){
			Triple[] matrx = new Triple[len];
			if(edges[0].getStart()!=0){
				return edges;
			}
			int index = 1;//这是matrx第一个边的索引值加一
			for(int i=0;i<len;i++){
				matrx[i]=edges[index-1];
				for(int j=index;j<edges.length;j++){
					if(i==edges[j].getStart()){
						edges[j-1].setNext(edges[j]);//前一个边指向下一个
						index++;//积累一次
					}else{
						index++;//积累一次
						break;
					}
				}
			}
		return matrx;
	}
	
	public Map<String, Triple> getAdjlist() {
		return adjlist;
	}
	
	public Triple get(int start,int end){
		Triple temp = this.adjlist.get(this.vertices[start]);//这只是邻接表
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
	
	
	
	
	//生成路径
	public List<ArrayList<String>> getPath(String start,String end){
		//保存说有路线的集合
		List<ArrayList<String>> allPath = new ArrayList<>();
	
		Stack<String> stack = new Stack<String>();
		Map<String, Queue<String>> queueMap = new HashMap<String, Queue<String>>();
		//把开始的点进栈
		stack.push(start);
		Triple temp = this.adjlist.get(start).getNext();
		String top = this.vertices[temp.getStart()];//保留栈顶元素，指针
		String ss = "";//正在操作的元素
		while(!(stack.isEmpty())){
			Queue<String> topQueue = queueMap.get(top);
			if(topQueue==null){
				/******/
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
			ss = topQueue.poll();//队列出列
			if(null == ss){//队列已经空了
					stack.pop();//出栈
					queueMap.remove(top);//把空队列去掉
					if(!(stack.isEmpty())){
						top=stack.peek();
						}
					continue;
			}
			
			if(stack.contains(ss)){//如果是在栈里面，继续出列
				continue;//继续下一个循环
			}else{
				if(ss.equals(end)){//相等就输出
					stack.add(ss);//
					//输出
					Iterator<String> iterator = stack.iterator();
					ArrayList<String> route = new ArrayList<String>();//一条通路
					while(iterator.hasNext()){
						route.add(iterator.next());
					}
					allPath.add(route);
					stack.pop();//
					continue;//继续下一个循环
				}else{
					stack.push(ss);//不相等就入栈
				}
				
			}
			top=stack.peek();//获取栈顶元素
		}
		
		return allPath;
	}
	
	
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
	
	
	public Stack<String> getShortestPath(int startIndex,int endIndex){
		
		int n = this.vertices.length;//图的顶点数
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
	public int getShortestDistance() {
		return shortestDistance;
	}
}
