<!-- author: Ingrid Farkas; project: Time Management -->
<!-- addempl_form is shown when the URL is localhost:8080/addempl_form  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <%@ page import = "java.io.*,java.util.*" %> -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- including the meta tags, the links to the external style sheets -->	  
	<%@ include file="header.jsp"%>
	<style>
/*	 
select {
  width: 100%;
  padding: 16px 20px;
  border: none;
  border-radius: 4px;
  /* background-color: #f1f1f1; */
} */





/*
input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

input[type=submit]:hover {
  background-color: #45a049;
} */
</style>
</head>

<body>   
    <div class="content"> 
      	<!-- Top menu on small screens -->
      	<header class="w3-container w3-white w3-xlarge w3-padding-16">
        	<span class="w3-left">Time Management</span> 
      	</header> <!-- end of header -->
      	<!-- including the navigation -->
	  	<%@ include file="nav1.jsp" %>
      	<!-- including the content ( of the web page ) -->
      	<%@ include file="addempl_fcont.jsp"%> <!-- shows the New Employee form for entering the first name, last name and department of the employee -->
      	<br />
      	<!-- including the footer -->
      	<%@ include file="footer.jsp"%>
      	
	</div> 
</body> 