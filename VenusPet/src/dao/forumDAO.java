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
import model.Forum;

public class forumDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static int forumID, custID;
	static String forumTitle,forumDescription,staffID; 
	static Date forumDate; 

	
	//add data
    public  void addForum(Forum bean)
    {
        //get values
    	forumTitle = bean.getForumTitle();
    	forumDescription = bean.getForumDescription();
		forumDate = bean.getForumDate();
		custID = bean.getCustID();
		staffID = bean.getStaffID();
		
		System.out.println("Your title is " + forumTitle);
		System.out.println("Your description is " + forumDescription);
		System.out.println("date: " + forumDate);
		System.out.println("Your custID is " + custID);
		System.out.println("Your staffID is " + staffID);
		
		
        try 
        {
            //step 2: create connection
             con = ConnectionManager.getConnection();
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
             String fdate = dateFormat.format(forumDate);
             
            
            //step3 : create statement - using preparedStatement
            ps=con.prepareStatement("insert into forum(forumTitle,forumDescription,forumDate,custID,staffID)values(?,?,?,?,?)");
            
            ps.setString(1, forumTitle);
            ps.setString(2, forumDescription);
            ps.setDate(3, java.sql.Date.valueOf(fdate));
            ps.setInt(4,custID);
            ps.setString(5, staffID);
            
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
    public static List<Forum> getAllForum() throws ClassNotFoundException, SQLException
    {
        List<Forum> sups = new ArrayList<>();
        
        con = ConnectionManager.getConnection();
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from forum ");
        
       
        while (rs.next()) 
        {
        	 
 	        forumID=rs.getInt("forumID");
 	        forumTitle=rs.getString("forumTitle");
 	        forumDescription = rs.getString("forumDescription");
 	        forumDate=rs.getDate("forumDate");
 	        custID=rs.getInt("custID");
 	        staffID=rs.getString("staffID");
        
       
	        Forum fo = new Forum(forumID, forumTitle,forumDescription, forumDate, custID,staffID);
	        sups.add(fo);
	      
        
        }
       
        return sups;
}
    
  //select a data
    public static Forum getForumById(int forumID) {
         Forum sup = new Forum();
        
         try {
        con = ConnectionManager.getConnection();
         ps=con.prepareStatement("select * from forum where forumID=?");

         ps.setInt(1, forumID);
         ResultSet rs = ps.executeQuery();
             
         if (rs.next()) {
         sup.setForumID(rs.getInt("forumID"));
         sup.setForumTitle(rs.getString("forumTitle"));
         sup.setForumDescription(rs.getString("forumDescription"));
         sup.setForumDate(rs.getDate("forumDate"));
         sup.setCustID(rs.getInt("custID"));
         sup.setStaffID(rs.getString("staffID"));
         
         }
         } catch (SQLException e) {
             System.out.println("failed: Cannot get the id " + e);
         }
         return sup;
        }
    
	    public static int getForumId() {
	    	int forumID = 0;
	    	try {
	    	con = ConnectionManager.getConnection();
	    	ps = con.prepareStatement("select forumID from forum order by forumID desc limit 1");
	    	ResultSet rs = ps.executeQuery();
	    	if(rs.next()){
	    	forumID = rs.getInt("forumID");
	    	}
	    	}
	    	catch(SQLException e) {
	    	e.printStackTrace();
	    	}
	    	return forumID;
	    	}
    
  //update
    public void updateForum(Forum bean) {
    	forumID = bean.getForumID();
    	forumTitle = bean.getForumTitle();
    	forumDescription = bean.getForumDescription();
		custID = bean.getCustID();
		staffID = bean.getStaffID();
		

        String searchQuery = "UPDATE forum SET forumTitle='" + forumTitle + "',forumDescription='" + forumDescription + "',   custID='" + custID +"' ,  staffID='" + staffID +"' WHERE forumID= '" + forumID + "'";
        System.out.println(searchQuery);
        try {
        con = ConnectionManager.getConnection();
        stmt = con.createStatement();
        stmt.executeUpdate(searchQuery);
        } catch (SQLException e) {
            System.out.println("failed: tak boleh update data " + e);
        }
        }
    
    public void deleteForum(int forumID) {
        try {
        con = ConnectionManager.getConnection();
        ps=con.prepareStatement("delete from forum where forumID=?");
        ps.setInt(1, forumID);
        ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("failed: tak boleh delete data customer " + e);
        }
       }
 }               
