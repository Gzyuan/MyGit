package cn.dataStructure.forBusRoute;

public class BusRoute {
	private String name;//·����
	private Station[] route;//·������
	public BusRoute(String name, Station[] route) {
		//super();
		this.name = name;
		this.route = route;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Station[] getRoute() {
		return route;
	}
	public void setRoute(Station[] route) {
		this.route = route;
	}
	
	
}
