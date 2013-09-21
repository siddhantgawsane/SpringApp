<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
<body>
	<h1>Register</h1>
 
	<form method="post" action="<%= blobstoreService.createUploadUrl("/update")%>" enctype="multipart/form-data">
		<table>
			<tr>
				<td>UserName :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					value="<%= session.getAttribute("username") %>" readonly="readonly"
					name="uname" id="uname" /></span></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="password" id="password" /></span></td>
			</tr>
			
			<tr>
				<td>First Name :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="fname" id="fname" /></span></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="lname" id="lname" /></span></td>
			</tr>
			<tr>
				<td>Email Id :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="email" id="email" /></span></td>
			</tr>
			<tr>
				<td>Profile Picture :</td>
				<td><input type="file" name="mypic" id="mypic" /></span></td>
			</tr>
		</table>
		<input type="submit" class="save" title="Save" value="Save" />
	</form>
 
</body>
</html>