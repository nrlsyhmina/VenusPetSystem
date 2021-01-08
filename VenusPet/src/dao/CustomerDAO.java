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
        List<Customer> sups = new ArrayList<Customer>();
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
        sups.add(customer);
        }
        } catch (SQLException e) {
            System.out.println("failed: tak boleh get data all " + e);
            }
            return sups;
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