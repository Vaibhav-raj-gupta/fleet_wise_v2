package model;

import java.util.Objects;

public class Route {
	private int routeId;
	//one to many
    private String routeName;
    private String startPoint;
    private String destinationPoint;
	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Route(int routeId, String routeName, String startPoint, String destinationPoint) {
		super();
		this.routeId = routeId;
		this.routeName = routeName;
		this.startPoint = startPoint;
		this.destinationPoint = destinationPoint;
	}
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getDestinationPoint() {
		return destinationPoint;
	}
	public void setDestinationPoint(String destinationPoint) {
		this.destinationPoint = destinationPoint;
	}
	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", routeName=" + routeName + ", startPoint="
				+ startPoint + ", destinationPoint=" + destinationPoint + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(destinationPoint, routeId, routeName, startPoint);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		return Objects.equals(destinationPoint, other.destinationPoint)
				&& routeId == other.routeId && Objects.equals(routeName, other.routeName)
				&& Objects.equals(startPoint, other.startPoint);
	}
    
    
}
