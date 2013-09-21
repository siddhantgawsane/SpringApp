<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.List" %>
	<%@ page import="com.springmvcproject.Employee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Tables</title>
</head>
<body>
	<table border="1" align="center">
		<tr>
			<th>User Name</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email Id</th>
			<th>Logins</th>
			<th>Profile Picture</th>
		</tr>

<%
	List<Employee> l = (List<Employee>)request.getAttribute("UsersList");

if(l!=null)
for(Employee u : l) 
{
%>		<tr>
			<td><%= u.getUserName() %></td>
	 		<td><%= u.getFirstName() %></td>
			<td><%= u.getLastName() %></td>
			<td><%= u.getEmailId() %></td>
			<td><%= u.getLogins() %></td> <!--  -->
			<td><img src="/serve?blob-key=<%= u.getBlobKeyString() %>" height="50" width="50"/></td>
		</tr>
<% 
} 
%>
	</table>
</body>
</html>