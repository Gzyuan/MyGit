package cn.dataStructure.forBusRoute;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 
 * @author gzy39
 * 用来公交车路线与图的对接，主要实现路线的回溯功能
 * 
 */
public class BaseUtil {
	private AdjListGraph adjListGraph;//图的对象
	private List<BusRoute> busList;
	private String[] stationName;
	
	public BaseUtil(){
		this.init();
	}
	//初始化图
	private void init(){
		//公交车路线的图
		String[] vertices = {"A","B","C","D","E","F","G","H","I","J"};
		stationName = vertices;
		Triple[] edges = {	new Triple(0,1,10),new Triple(0,2,25),
							new Triple(1,0,10),new Triple(1,3,30),new Triple(1,4,20),
							new Triple(2,0,25),new Triple(2,3,23),new Triple(2,4,15),
							new Triple(3,1,30),new Triple(3,2,23),new Triple(3,5,18),new Triple(3,6,19),
							new Triple(4,1,20),new Triple(4,2,15),new Triple(4,5,24),new Triple(4,6,20),
							new Triple(5,3,18),new Triple(5,4,24),new Triple(5,7,21),new Triple(5,8,14),
							new Triple(6,3,19),new Triple(6,4,20),new Triple(6,7,13),new Triple(6,8,25),
							new Triple(7,5,21),new Triple(7,6,13),new Triple(7,9,30),
							new Triple(8,5,14),new Triple(8,6,25),new Triple(8,9,26),
							new Triple(9,7,30),new Triple(9,8,26),
		};
		this.adjListGraph = new AdjListGraph(vertices, edges);
		
		
		//route数组
		Station[] route116 = new Station[10];
		Station[] route224 = new Station[11];
		Station[] route357 = new Station[12];
		Station[] route411 = new Station[13];
		
		//公交车的map
		busList = new ArrayList<BusRoute>();
		
		Station a116 = new Station("A");
		Station b116 = new Station("B");
		Station d116 = new Station("D");
		Station f116 = new Station("F");
		Station h116 = new Station("H");
		Station j116 = new Station("J");
		
		//116车班路线
		a116.setChildIndex(1);
		b116.setParentIndex(0);
		b116.setChildIndex(3);
		d116.setParentIndex(1);
		d116.setChildIndex(5);
		f116.setParentIndex(3);
		f116.setChildIndex(7);
		h116.setParentIndex(5);
		h116.setChildIndex(9);
		j116.setParentIndex(7);
		route116[0] = a116;
		route116[1] = b116;
		route116[3] = d116;
		route116[5] = f116;
		route116[7] = h116;
		route116[9] = j116;
		BusRoute bus116 = new BusRoute("116", route116);
		busList.add(bus116);
		
		Station a224 = new Station("A");
		Station c224 = new Station("C");
		Station e224 = new Station("E");
		Station g224 = new Station("G");
		Station i224 = new Station("I");
		Station j224 = new Station("J");
		
		//224班车路线
		a224.setChildIndex(2);
		c224.setParentIndex(0);
		c224.setChildIndex(4);
		e224.setParentIndex(2);
		e224.setChildIndex(6);
		g224.setParentIndex(4);
		g224.setChildIndex(8);
		i224.setParentIndex(6);
		i224.setChildIndex(9);
		j224.setParentIndex(8);
		
		route224[0] = a224;
		route224[2] = c224;
		route224[4] = e224;
		route224[6] = g224;
		route224[8] = i224;
		route224[9] = j224;
		BusRoute bus224 = new BusRoute("224", route224);
		busList.add(bus224);
		
		Station a357 = new Station("A");
		Station b357 = new Station("B");
		Station e357 = new Station("E");
		Station f357 = new Station("F");
		Station i357 = new Station("I");
		Station j357 = new Station("J");
		
		
		//357班车路线
		a357.setChildIndex(1);
		b357.setParentIndex(0);
		b357.setChildIndex(4);
		e357.setParentIndex(1);
		e357.setChildIndex(5);
		f357.setParentIndex(4);
		f357.setChildIndex(8);
		i357.setParentIndex(6);
		i357.setChildIndex(9);
		j357.setParentIndex(8);
		
		route357[0] = a357;
		route357[1] = b357;
		route357[2] = null;
		route357[3] = null;
		route357[4] = e357;
		route357[5] = f357;
		route357[6] = null;
		route357[7] = null;
		route357[8] = i357;
		route357[9] = j357;
		BusRoute bus357 = new BusRoute("357", route357);
		busList.add(bus357);
		
		Station a441 = new Station("A");
		Station c441 = new Station("C");
		Station d441 = new Station("D");
		Station g441 = new Station("G");
		Station h441 = new Station("H");
		Station j441 = new Station("J");
		
		
		//411班车路线
		a441.setChildIndex(2);
		c441.setParentIndex(0);
		c441.setChildIndex(3);
		d441.setParentIndex(2);
		d441.setChildIndex(6);
		g441.setParentIndex(3);
		g441.setChildIndex(7);
		h441.setParentIndex(6);
		h441.setChildIndex(9);
		j441.setParentIndex(7);
		
		route411[0] = a441;
		route411[2] = c441;
		route411[3] = d441;
		route411[6] = g441;
		route411[7] = h441;
		BusRoute bus411 = new BusRoute("411", route411);
		busList.add(bus411);
			
	}
	//求所有的通路
	public String getPath(String start,String end){
		if(this.adjListGraph==null){
			return null;
		}
		List<ArrayList<String>> pathList = this.adjListGraph.getPath(start, end);
		StringBuffer sb = new StringBuffer();//最终返回的字符串
		//遍历所有通路
		for(int i=0;i<pathList.size();i++){
			StringBuffer sb1 = new StringBuffer();
			ArrayList<String> route = pathList.get(i);
			sb.append("第"+(i+1)+"条路线："+start+"站出发");
			sb1.append(start);
			//遍历通路的每个节点
			for(int j=1;j<route.size();j++){
				sb1.append("-"+route.get(j));
				int first = this.changeIndex(route.get(j-1));
				int second = this.changeIndex(route.get(j));
				sb.append("乘");
				//遍历每条公交车的路线
				for(int k=0;k<this.busList.size();k++){
					BusRoute temp = this.busList.get(k);
					Station[] sTemp=temp.getRoute();
					
					if(null!=sTemp[second]&&sTemp[second].getParentIndex()==first){//回溯乘哪辆bus(从左回溯)
						sb.append(temp.getName()+"车，");
					}
					if(null!=sTemp[second]&&sTemp[second].getChildIndex()==first){//回溯乘哪辆bus(从右回溯)
						sb.append(temp.getName()+"车，");
					}
				}
				sb.append("到"+this.stationName[second]+"站,然后");
			}
			sb.append("到终点"+end+"站\n");
			sb.append(sb1.toString()+"\n");
		}
		return sb.toString();
	}
	
	
	//求最短路径,回溯过程类似于求通路
	public String getShortestPath(String start,String end){
		Stack<String> shortestPath = this.adjListGraph.getShortestPath(changeIndex(start), changeIndex(end));
		StringBuffer sb = new StringBuffer();
		String firstS = shortestPath.pop();
		sb.append("最短路线："+start+"站出发");
		while(!shortestPath.isEmpty()){
			String secondS = shortestPath.pop();
			int first = this.changeIndex(firstS);
			int second = this.changeIndex(secondS);
				sb.append("乘");
				for(int k=0;k<this.busList.size();k++){
					BusRoute temp = this.busList.get(k);
					Station[] sTemp=temp.getRoute();
					if(null!=sTemp[second]&&sTemp[second].getParentIndex()==first){
						sb.append(temp.getName()+"车，");
					}
					if(null!=sTemp[second]&&sTemp[second].getChildIndex()==first){
						sb.append(temp.getName()+"车，");
					}
				
				}
			sb.append("到"+this.stationName[second]+"站,然后");
			firstS = secondS;
		}
		sb.append("到终点"+end+"站,最短距离为"+this.adjListGraph.getShortestDistance()+"\n");
		return sb.toString();
	}
	
	
	//把站点转成对应的索引
	public int changeIndex(String s){
		for(int i=0;i<this.stationName.length;i++){
			if(this.stationName[i].equals(s)){
				return i;
			}
		}
		return -1;
	}
}
