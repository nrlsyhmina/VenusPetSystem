<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center><h2>Java Registration application using MVC and MySQL </h2></center>
    <form name="form" action="AddServiceController" method="post">
        <table align="center">
         <tr>
         <td>Service Name</td>
         <td><input type="text" name="svName" /></td>
         </tr>
         <tr>
         <td>Service Price</td>
         <td><input type="double" name="svPrice" /></td>
         </tr>
         <tr>
         <td>Service Description</td>
         <td><input type="text" name="svDescription" /></td>
         </tr>
         <tr>
         <td>Staff ID</td>
         <td><input type="text" name="staffID" /></td>
         </tr>
         
         <tr>
         <td><%=(request.getAttribute("errMessage") == null) ? ""
         : request.getAttribute("errMessage")%></td>
         </tr>
         <tr>
         <td></td>
         <td><input type="submit" value="submit" name="submit" class="btn-block btn-primary" id="submit"/></td>
         </tr>
        </table>
    </form>
</body>
</html>