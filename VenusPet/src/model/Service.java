package model;

public class Service {
	int serviceID, staffID;
	String svName, svDescription;
	double svPrice;
	
	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
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

	public double getSvPrice() {
		return svPrice;
	}

	public void setSvPrice(double svPrice) {
		this.svPrice = svPrice;
	}
	

}
