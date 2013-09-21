<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.springmvcproject.Employee" %>
<html>
<body>
<% 
HttpServletResponse hsr = (HttpServletResponse) response;
hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
hsr.setHeader("Pragma", "no-cache"); // HTTP 1.0.
hsr.setHeader("Expires","0"); 
hsr.setDateHeader("Expires", -1); // Proxies.
%>
	<h1>Home Page</h1>
 <a href = "/signout">Sign Out</a> 
 <a href = "/update">Update Profile</a> 
 <a href = "/viewimages">View Images</a>
 <a href = "/viewvideos">View Videos</a>
 <a href = "/viewusers">View All Users</a> <!-- 
 <a href = "/recommend">Recommend To a Friend</a>  -->	
 <%
	if(session.getAttribute("username") == null)
	{
		response.sendRedirect("/");
	}
 %>
 		<table>
			<tr>
				<td>
					UserName :
				</td>
				<td>
						<%= session.getAttribute("username") %> 
				</td>
			</tr>
			
			<tr>
				<td>
					Name :
				</td>
				<td>
						<%=session.getAttribute("fname") %>
						<%=session.getAttribute("lname") %>
				</td>
			</tr>
			
			<tr>
				<td>
					Email Id:
				</td>
				<td>
						<%= session.getAttribute("emailid") %> 
				</td>
			</tr>
	
			<tr>
				<td>
					Logins :
				</td>
				<td>
						<%= session.getAttribute("logins") %>
				</td>
			</tr>
			
			<tr>
				<td>
					Profile Pic
				</td>
				<td>
						<img width="200" height="150" src='/serve?blob-key=<%= session.getAttribute("blob-key") %>'/>
				</td>
			</tr>
		</table> 
</body>
</html>