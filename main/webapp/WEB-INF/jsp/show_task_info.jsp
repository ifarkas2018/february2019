<!-- project : Time Schedule, author : Ingrid Farkas, 2019 -->
<!-- show_task_info is shown when the URL is localhost:8080/task_update - method is POST, localhost:8080/add_task_form - method is POST, localhost:8080/add_task_fget - method is GET -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- include the links to the external style sheets -->	  
	<%@ include file="header.jsp"%>
</head>

<body>   
    <div class="content"> 
      	<!-- Top menu on small screens -->
      	<header class="w3-container w3-white w3-xlarge w3-padding-16"> 
        	<span class="w3-left">Time Management</span> 
      	</header> <!-- end of header -->
	  	<!-- including the navigation -->
      	<%@ include file="nav1.jsp"%>   
      	
      	<!-- include the content of the web page -->
      	<%@ include file="show_task_form.jsp"%> <!-- shows the form with the task name, date, start time, end time --> 
      	<br />
      	
      	<!-- include the footer -->
      	<%@ include file="footer.jsp"%>
	</div> 
</body> 



