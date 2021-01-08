package dao;

import java.util.ArrayList;
import java.util.List;
import connection.ConnectionManager;
import java.sql.*;
import java.util.*;
import model.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceDAO {
	static Connection con;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement ps;
	
	static int serviceID;
	static String svName;
	static String staffID;
	static String svDescription;
	static double svPrice;
	
	public String addService(Service serviceBean)
	{
		try 
        {
            //step 2: create connection
            con = ConnectionManager.getConnection();
            
            //step3 : create statement - using preparedStatement
            ps=con.prepareStatement("insert into service(svName,svPrice,svDescription,staffID)values(?,?,?,?)");
            ps.setString(1,serviceBean.getSvName());
            ps.setDouble(2,serviceBean.getSvPrice());
            ps.setString(3,serviceBean.getSvDescription());
            ps.setString(4,serviceBean.getStaffID());
            
            int i= ps.executeUpdate();
            
            if (i!=0)  //Just to ensure data has been inserted into the database
            return "SUCCESS"; 
        }
        catch (Exception ex) 
        {
            System.out.println("failed: An Exception has occurred! " + ex);
        }return "Oops.. Something went wrong there..!";
	}
	
	public static Service getServiceById(int serviceID) {
        Service sv = new Service();
       
        try {
        con = ConnectionManager.getConnection();
        ps=con.prepareStatement("select * from service where serviceID=?");

        ps.setInt(1, serviceID);
        ResultSet rs = ps.executeQuery();
            
        if (rs.next()) {
        sv.setServiceID(rs.getInt("serviceID"));
        sv.setSvName(rs.getString("svName"));
        sv.setSvPrice(rs.getDouble("svPrice"));
        sv.setSvDescription(rs.getString("svDescription"));
        sv.setStaffID(rs.getString("staffID"));
        }
        } catch (SQLException e) {
            System.out.println("failed: Cannot get the id " + e);
        }
        return sv;
       }
	
	public String updateService(Service serviceBean)
	{
		serviceID = serviceBean.getServiceID();
		svName = serviceBean.getSvName();
		svPrice = serviceBean.getSvPrice();
		svDescription = serviceBean.getSvDescription();
		staffID = serviceBean.getStaffID();
		try 
        {
            //step 2: create connection
            con = ConnectionManager.getConnection();
            
            //step3 : create statement - using preparedStatement
            String sqlSearch =" UPDATE service SET svName= '" + svName + "', svPrice= '" + svPrice + "', svDescription= '" + svDescription + "', staffID= '" + staffID + "' WHERE serviceID= '" + serviceID + "'";
            stmt = con.createStatement();
            stmt.executeUpdate(sqlSearch);
            
        }
        catch (Exception ex) 
        {
            System.out.println("failed: An Exception has occurred! " + ex);
        }return "Oops.. Something went wrong there..!";
	}

	
	public static List<Service> getServiceList() throws ClassNotFoundException, SQLException
	{
		
		ArrayList<Service> service = new ArrayList<>();
		
		 con = ConnectionManager.getConnection();
		 stmt = con.createStatement();
		 rs = stmt.executeQuery("select * from service");
		
		while(rs.next())
		{
			 serviceID = rs.getInt("serviceID");
			 svName = rs.getString("svName");
			 svDescription = rs.getString("svDescription");
			 svPrice = rs.getDouble("svPrice");
			 staffID = rs.getString("staffID");
			 Service s = new Service(serviceID,svName, svDescription,staffID,svPrice);
			service.add(s);
		}
		
		
		
		
		return service;
	}

}
