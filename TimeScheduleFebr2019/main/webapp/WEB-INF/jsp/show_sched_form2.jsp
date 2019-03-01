<!-- author: Ingrid Farkas; project: Time Management -->
<!-- show_sched_form is shown when the URL is show_sched, update_sched, add_task -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- the link to the external style sheet -->	  
	<%@ include file="header.jsp"%>
</head>

<body>   
    <div class="content"> 
      	<!-- Top menu -->
      	<header class="w3-container w3-white w3-xlarge w3-padding-16"> 
        	<span class="w3-left">Time Management</span> 
      	</header> <!-- end of header -->
      	
	  	<!-- including the navigation -->
      	<%@ include file="nav1.jsp"%> 
    	   
      	<!-- including the content ( of the web page ) -->
      	<%@ include file="show_sched_fcont2.jsp"%> <!-- shows the form for entering the employee ID, first name, last name, date of the schedule -->
      	<br />
      	<!-- including the footer -->
      	<%@ include file="footer.jsp"%>
      	
	</div> 
</body> 