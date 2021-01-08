package model;

public class Service {

	private int serviceID;
	private String svName, svDescription, staffID;
	private double svPrice;
	
	public Service(int serviceID, String svName, String svDescription, String staffID, double svPrice) {
		this.serviceID = serviceID;
		this.svName = svName;
		this.svDescription = svDescription;
		this.staffID = staffID;
		this.svPrice = svPrice;
	}


	public Service() {
		// TODO Auto-generated constructor stub
	}


	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public String getSvName() {
		return svName;
	}

	public void setSvName(String svName) {
		this.svName = svName;
	}

	public String getSvDescription() {
		return svDescription;
	}

	public void setSvDescription(String svDescription) {
		this.svDescription = svDescription;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public double getSvPrice() {
		return svPrice;
	}

	public void setSvPrice(double svPrice) {
		this.svPrice = svPrice;
	}
	
		
}
