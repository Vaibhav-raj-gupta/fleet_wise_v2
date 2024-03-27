package model;

import java.util.Objects;

public class Issue {
	private int issueId;
    private int vehicleId;
    //many to one
    private int consignmentId;
    private int driverId;
    private String description;
    private String raisedOn;
    private String resolvedOn;
    private String remarks;
    private String status;
    
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Issue(int issueId, String description,int vehicleId, int driverId,
			String raisedOn, String resolvedOn, String remarks, String status) {
		super();
		this.issueId = issueId;
		this.description = description;
		this.vehicleId = vehicleId;
		this.driverId = driverId;
		this.raisedOn = raisedOn;
		this.resolvedOn = resolvedOn;
		this.remarks = remarks;
		this.status = status;
	}
	
	public Issue(int issueId, int consignmentId, int driverId, String description,
			String raisedOn, String resolvedOn, String remarks, String status) {
		super();
		this.issueId = issueId;
		this.consignmentId = consignmentId;
		this.driverId = driverId;
		this.description = description;
		this.raisedOn = raisedOn;
		this.resolvedOn = resolvedOn;
		this.remarks = remarks;
		this.status = status;
	}
	
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getConsignmentId() {
		return consignmentId;
	}
	public void setConsignmentId(int consignmentId) {
		this.consignmentId = consignmentId;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRaisedOn() {
		return raisedOn;
	}
	public void setRaisedOn(String raisedOn) {
		this.raisedOn = raisedOn;
	}
	public String getResolvedOn() {
		return resolvedOn;
	}
	public void setResolvedOn(String resolvedOn) {
		this.resolvedOn = resolvedOn;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", vehicleId=" + vehicleId + ", consignmentId=" + consignmentId
				+ ", driverId=" + driverId + ", description=" + description + ", raisedOn=" + raisedOn + ", resolvedOn="
				+ resolvedOn + ", remarks=" + remarks + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(consignmentId, description, driverId, issueId, raisedOn, remarks, resolvedOn, status,
				vehicleId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		return Objects.equals(consignmentId, other.consignmentId) && Objects.equals(description, other.description)
				&& Objects.equals(driverId, other.driverId) && issueId == other.issueId
				&& Objects.equals(raisedOn, other.raisedOn) && Objects.equals(remarks, other.remarks)
				&& Objects.equals(resolvedOn, other.resolvedOn) && Objects.equals(status, other.status)
				&& Objects.equals(vehicleId, other.vehicleId);
	}
    
    
}
