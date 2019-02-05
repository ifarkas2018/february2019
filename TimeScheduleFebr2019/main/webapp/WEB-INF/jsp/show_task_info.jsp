<!-- project : Time Schedule, author : Ingrid Farkas, 2019 -->
<!-- show_task_info is shown when the URL is localhost:8080/task_update  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- include the meta tags, the links to the external style sheets -->	  
	<%@ include file="header.jsp"%>
</head>
<body>   
    <div class="content"> 
      	<!-- Top menu on small screens -->
      	<header class="w3-container w3-white w3-xlarge w3-padding-16"> 
        	<span class="w3-left">Time Management</span> 
      	</header> <!-- end of header -->
	  	<!-- including the navigation - to see the page show_task_form.jsp ( included below ) the user has to be logged in -->
	  	<!-- that is the reason the nav_max is shown -->
      	<%@ include file="nav1.jsp"%>   
      	
      	<!-- include the content of the web page -->
      	<%@ include file="show_task_form.jsp"%> <!-- shows the form with the task name, date, start time, end time which is read from the database and which can be updated --> 
      	<br />
      	<!-- include the footer -->
      	<%@ include file="footer.jsp"%>
	</div> 
</body> 



