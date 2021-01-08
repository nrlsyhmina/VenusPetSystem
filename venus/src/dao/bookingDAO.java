package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.ConnectionManager;
import model.Booking;
//import model.Customer;
//import model.Service;


public class bookingDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static int bookingID, rating, custID, serviceID;
	static double bTotalPrice;
	static String staffID; 
	static Date bDate; 
	static String bTime;
	
	//add data
    public  void addBooking(Booking bean)
    {
        //get values
    	bDate = bean.getbDate();
		bTime = bean.getbTime();
		bTotalPrice = bean.getbTotalPrice();
		rating = bean.getRating();
		serviceID = bean.getServiceID();
		custID = bean.getCustID();
		staffID = bean.getStaffID();
		
		System.out.println("Your date name is " + bDate);
		System.out.println("Your time is " + bTime);
		System.out.println("total: " + bTotalPrice);
		System.out.println("Your rate  is " + rating);
		System.out.println("Your custID is " + custID);
		System.out.println("service: " + serviceID);
		System.out.println("Your staff is " + staffID);
		
        try 
        {
            //step 2: create connection
             con = ConnectionManager.getConnection();
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
             String bdate = dateFormat.format(bDate);
             
            
            //step3 : create statement - using preparedStatement
            ps=con.prepareStatement("insert into booking(bDate,bTime,bTotalPrice,serviceID,custID)values(?,?,?,?,?)");
            
            ps.setDate(1, java.sql.Date.valueOf(bdate));
            ps.setString(2,bTime);
            ps.setDouble(3, bTotalPrice);
            ps.setInt(4,serviceID);
            ps.setInt(5, custID);
            System.out.print(ps);
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
    

    
    //list data
    public static List<Booking> getAllBooking() throws ClassNotFoundException, SQLException
    {
        List<Booking> sups = new ArrayList<>();
        
        con = ConnectionManager.getConnection();
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from booking ");
        
       
        while (rs.next()) 
        {
        	 
 	        bookingID=rs.getInt("bookingID");
 	        bDate=rs.getDate("bdate");
 	        bTime=rs.getString("bTime");
 	        bTotalPrice=rs.getDouble("bTotalPrice");
 	        rating = rs.getInt("rating");
 	        serviceID=rs.getInt("serviceID");
 	        custID=rs.getInt("custID");
 	        staffID=rs.getString("staffID");
        
       
	        Booking bo = new Booking(bookingID, bDate, bTime, bTotalPrice, rating, custID, serviceID, staffID);
	        sups.add(bo);
	      
        
        }
       
        return sups;
}
    
  //select a data
    public static Booking getBookingById(int bookingID) {
         Booking sup = new Booking();
        
         try {
        con = ConnectionManager.getConnection();
         ps=con.prepareStatement("select * from booking where bookingID=?");

         ps.setInt(1, bookingID);
         ResultSet rs = ps.executeQuery();
             
         if (rs.next()) {
         sup.setBookingID(rs.getInt("bookingID"));
         sup.setbDate(rs.getDate("bDate"));
         sup.setbTime(rs.getString("btime"));
         sup.setServiceID(rs.getInt("serviceID"));
         sup.setbTotalPrice(rs.getDouble("bTotalPrice"));
         sup.setRating(rs.getInt("rating"));
         sup.setStaffID(rs.getString("staffID"));
         }
         } catch (SQLException e) {
             System.out.println("failed: Cannot get the id " + e);
         }
         return sup;
        }
    
	    public static int getBookingId() {
	    	int bookingId = 0;
	    	try {
	    	con = ConnectionManager.getConnection();
	    	ps = con.prepareStatement("select bookingiD from booking order by bookingID desc limit 1");
	    	ResultSet rs = ps.executeQuery();
	    	if(rs.next()){
	    	bookingId = rs.getInt("bookingID");
	    	}
	    	}
	    	catch(SQLException e) {
	    	e.printStackTrace();
	    	}
	    	return bookingId;
	    	}
    
  //update
    public void updateBooking(Booking bean) {
    	bookingID = bean.getBookingID();
    	bDate = bean.getbDate();
        bTime = bean.getbTime();
        serviceID = bean.getServiceID();
        bTotalPrice = bean.getbTotalPrice();
        staffID = bean.getStaffID();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String bdate = dateFormat.format(bDate);
        
        String searchQuery = "UPDATE booking SET bDate= '" + java.sql.Date.valueOf(bdate) + "', bTime='" + bTime + "', serviceID='" + serviceID + "', bTotalPrice='" + bTotalPrice +"', staffID='" + staffID +"' WHERE bookingID= '" + bookingID + "'";
        System.out.println(searchQuery);
        try {
        con = ConnectionManager.getConnection();
        stmt = con.createStatement();
        stmt.executeUpdate(searchQuery);
        } catch (SQLException e) {
            System.out.println("failed: tak boleh update data " + e);
        }
        }
    
    public void deleteBooking(int bookingID) {
        try {
        con = ConnectionManager.getConnection();
        ps=con.prepareStatement("delete from booking where bookingID=?");
        ps.setInt(1, bookingID);
        ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("failed: tak boleh delete data customer " + e);
        }
       }
 }               
