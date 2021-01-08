package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import connection.ConnectionManager;
import model.Customer;

public class CustomerDAO {
	
	//declarations
    int custID;
    String cName,cEmail,cNum,cPassword;
    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement ps=null;
    static Statement stmt=null;
    
    public static Customer login(Customer bean) {
		// preparing some objects for connection 		
		Statement stmt = null;
		String cEmail = bean.getcEmail();
		String cName = bean.getcName();
		String cPassword = bean.getcPassword();
		String searchQuery = "select * from customer where cEmail='" 
		+ cEmail + "' AND cPassword='" + cPassword + "'";
		//------prepared statement
		
		try {
			// connect to DB
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();
			// if user does not exist
			if (!more) {
				System.out.println("Sorry, you are not a registered user! " + "Please sign up first");
				bean.setValid(false);
			}
			// if user exists
			else if (more) {
				cName = rs.getString("cName");
				cEmail = rs.getString("cEmail");
				cPassword = rs.getString("cPassword");
				int custID = rs.getInt("custID");
				bean.setcName(cName);
				bean.setcEmail(cEmail);
				bean.setcPassword(cPassword);
				bean.setCustID(custID);
				bean.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} // some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) { }
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) { }
				stmt = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) { }
				currentCon = null;
			}
		}
		return bean;
	}
        
        
    //add data
    public void addCustomer(Customer bean)
    {
        //get values
        custID = bean.getCustID();    
        cName = bean.getcName();
        cNum = bean.getcNum();
        cEmail = bean.getcEmail();
        cPassword = bean.getcPassword();
        
        try 
        {
            //step 2: create connection
            con = ConnectionManager.getConnection();
            
            //step3 : create statement - using preparedStatement
            ps=con.prepareStatement("insert into customer(cName,cNum,cEmail,cPassword)values(?,?,?,?)");
            ps.setString(1,cName);
            ps.setString(2,cNum);
            ps.setString(3,cEmail);
            ps.setString(4, cPassword);
            
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
    
    //list data
    public static List<Customer> getAllCustomer() 
    {
        List<Customer> customerList = new ArrayList<Customer>();
        try {
        currentCon = ConnectionManager.getConnection();
        stmt = currentCon.createStatement();
        ResultSet rs = stmt.executeQuery("select * from customer order by custID DESC");
        while (rs.next()) {
        Customer customer = new Customer();
        customer.setCustID(rs.getInt("custID"));
        customer.setcName(rs.getString("cName"));
        customer.setcEmail(rs.getString("cEmail"));
        customer.setcNum(rs.getString("cNum"));
        customer.setcPassword(rs.getString("cPassword"));
        customerList.add(customer);
        }
        } catch (SQLException e) {
            System.out.println("failed: tak boleh get data all " + e);
            }
            return customerList;
    }

    //select a data
    public static Customer getCustomerById(int custID) {
         Customer sup = new Customer();
        
         try {
         currentCon = ConnectionManager.getConnection();
         ps=currentCon.prepareStatement("select * from customer where custID=?");

         ps.setInt(1, custID);
         ResultSet rs = ps.executeQuery();
             
         if (rs.next()) {
         sup.setCustID(rs.getInt("custID"));
         sup.setcName(rs.getString("cName"));
         sup.setcEmail(rs.getString("cEmail"));
         sup.setcNum(rs.getString("cNum"));
         sup.setcPassword(rs.getString("cPassword"));
         }
         } catch (SQLException e) {
             System.out.println("failed: Cannot get the id " + e);
         }
         return sup;
        }
    
    //update
    public void updateCustomer(Customer bean) {
        custID = bean.getCustID();
        cName = bean.getcName();
        cEmail = bean.getcEmail();
        cNum = bean.getcNum();
        cPassword = bean.getcPassword();
        String searchQuery = "UPDATE customer SET cName= '" + cName + "', cEmail='" + cEmail + "', cNum='" + cNum + "', cPassword='" + cPassword +"' WHERE custID= '" + custID + "'";
        try {
        currentCon = ConnectionManager.getConnection();
        stmt = currentCon.createStatement();
        stmt.executeUpdate(searchQuery);
        } catch (SQLException e) {
            System.out.println("failed: tak boleh update data " + e);
        }
        }
    
    //delete 
    public void deleteCustomer(int custID) {
     try {
     currentCon = ConnectionManager.getConnection();
     ps=currentCon.prepareStatement("delete from customer where custID=?");
     ps.setInt(1,custID);
     ps.executeUpdate();
     } catch (SQLException e) {
         System.out.println("failed: tak boleh delete data customer " + e);
     }
    }
                
}