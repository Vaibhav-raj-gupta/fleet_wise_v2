package model;

import java.util.Objects;

public class Hub {
	private int hubId;
	//many to one
    private int route;
    private String hubName;
    private String address;
    private String city;
    private int pincode;
    private long contactNumber;
    private String emailAddress;
	public Hub() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Hub(int hubId, int route, String hubName, String address, String city, int pincode, long contactNumber,
			String emailAddress) {
		super();
		this.hubId = hubId;
		this.route = route;
		this.hubName = hubName;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.contactNumber = contactNumber;
		this.emailAddress = emailAddress;
	}
	public int getHubId() {
		return hubId;
	}
	public void setHubId(int hubId) {
		this.hubId = hubId;
	}
	public int getRoute() {
		return route;
	}
	public void setRoute(int route) {
		this.route = route;
	}
	public String getHubName() {
		return hubName;
	}
	public void setHubName(String hubName) {
		this.hubName = hubName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Override
	public String toString() {
		return "Hub [hubId=" + hubId + ", route=" + route + ", hubName=" + hubName + ", address=" + address + ", city="
				+ city + ", pincode=" + pincode + ", contactNumber=" + contactNumber + ", emailAddress=" + emailAddress
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, city, contactNumber, emailAddress, hubId, hubName, pincode, route);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hub other = (Hub) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& contactNumber == other.contactNumber && Objects.equals(emailAddress, other.emailAddress)
				&& hubId == other.hubId && Objects.equals(hubName, other.hubName) && pincode == other.pincode
				&& Objects.equals(route, other.route);
	}
   
    
    
}
