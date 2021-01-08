<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container"><h1>Update Service</h1></div>
  <form action="UpdateServiceController" style="border:1px solid #ccc" method="post">
  <div class="container">
   
    <label for="customer ID"><b>Service ID</b></label><br>
    <input type="text" name="serviceID" value="<c:out value="${service.serviceID}"/>" readonly/>
    <br><br>
    
    <label for="name"><b>Name</b></label><br>
    <input type="text" name="svName" value="<c:out value="${service.svName}"/>" required>
    <br><br>

    <label for="phone"><b>Price</b></label><br>
    <input type="double" name="svPrice" value="<c:out value="${service.svPrice}"/>" required>
    <br><br>
    
    <label for="Email"><b>Desc</b></label><br>
    <input type="text" name="svDescription" value="<c:out value="${service.svDescription}"/>" required>
    <br><br>
     
     <label for="Password"><b>Staff ID</b></label><br>
    <input type="text" name="staffID" value="<c:out value="${service.staffID}"/>" required>
    <br><br>
    
    <br><br>
    
    <div class="clearfix">
      
        <input type="submit" value="Update" class="w3-btn w3-green w3-round-large">
    </div>
  </div>
</form>
</body>
</html>