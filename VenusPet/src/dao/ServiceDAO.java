package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import connection.ConnectionManager;
import model.Service;

public class ServiceDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;
    static Statement stmt=null;
	
	int serviceID, staffID;
	String svName, svDescription;
	double svPrice;
	
	//add Service
	public void addService(Service bean)
	{
		serviceID = bean.getServiceID();
		svName = bean.getSvName();
		svPrice = bean.getSvPrice();
		svDescription = bean.getSvDescription();
		staffID = bean.getStaffID();
		
		try 
        {
            //step 2: create connection
            con = ConnectionManager.getConnection();
            
            //step3 : create statement - using preparedStatement
            ps=con.prepareStatement("insert into service(svName,svPrice,svDescription,staffID)values(?,?,?,?)");
            ps.setString(1,svName);
            ps.setDouble(2,svPrice);
            ps.setString(3,svDescription);
            ps.setInt(4, staffID);
            
            //step 4: execute statement
            ps.executeUpdate();
            //step 5: close connection
            con.close();
        }
        catch (Exception ex) 
        {
            System.out.println("failed: An Exception has occurred! " + ex);
        }
	}
	
	static Connection currentCon = null;
	//list Service
	public static List<Service> getAllService()
	{
		List<Service> serviceList =  new ArrayList<Service>();
		 try {
		        currentCon = ConnectionManager.getConnection();
		        stmt = currentCon.createStatement();
		        ResultSet rs = stmt.executeQuery("select * from service order by serviceID DESC");
		        while (rs.next()) {
		        Service service = new Service();
		        service.setServiceID(rs.getInt("serviceID"));
		        service.setSvName(rs.getString("svName"));
		        service.setSvPrice(rs.getDouble("svPrice"));
		        service.setSvDescription(rs.getString("svDescription"));
		        service.setStaffID(rs.getInt("staffID"));
		        serviceList.add(service);
		        
		       
		   }
		       } catch (SQLException e) {
		            System.out.println("Failed: tak boleh get data all " + e);
		            }
		            return serviceList;
		
	}
	

}
