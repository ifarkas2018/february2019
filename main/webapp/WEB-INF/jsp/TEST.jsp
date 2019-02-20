<!-- author: Ingrid Farkas; project: Time Management -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <%@ page import = "java.io.*,java.util.*" %> -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- add: the meta tags, the links to the external style sheets -->	  
	<%@ include file="header.jsp"%>
</head>

<body>   
    <div class="content"> 
      	<!-- Top menu on small screens -->
      	<header class="w3-container w3-white w3-xlarge w3-padding-16"> 
        	<span class="w3-left">Time Management</span> 
      	</header> <!-- end of header -->
	  	
	  	<%
	  
 		// String.valueOf: converts the variable logged_in to the String
    	// I have added is_admin to the model in the MainController.java
		String is_admin  = String.valueOf(request.getAttribute("is_admin"));
	 	// I have added is_root to the model in the MainController.java
	 	// when the URL is localhost:8080 is_root is true
	  	String is_root = String.valueOf(request.getAttribute("is_root")); 

  		// if the user has logged in as an admin then show the navigation bar for the admin
    	if (is_admin.equals("true")) {
  			// adding to the session the attribute sess_adm 
  			// sess_adm - did the user log in as admin
  			String sess_adm = "true";
  			session.setAttribute("sess_adm", sess_adm);
  		
    	%>
    	<%@ include file="nav_admin.jsp"%>
    	<%  } else { // is_admin is not true
    	    if (!((is_root).equals("true"))) { // the URL is not localhost:8080
    			String sess_adm = new String();
	    		// I have added sess_admin to the session in index.jsp
	     		sess_adm  = (String)session.getAttribute("sess_adm");
	    
	    		if (sess_adm.equals("true")) {
	    	%>
	    			<!-- add: the navigation to the web page. nav_max shows the whole navigation bar   -->
		  			<%@ include file="nav_admin.jsp"%>
		    
	    	<%  } else { // the user didn't log in as admin 
    				session.setAttribute("sess_adm", "false");
					// String.valueOf: converts the variable logged_in to the String
					// I have added logged_in to the model in the MainController.java
    				String is_logged = String.valueOf(request.getAttribute("logged_in"));
    				
	    			if (is_logged.equals("true")){
    		%>
    					<!-- add: the navigation to the web page. nav_max shows the whole navigation bar   -->
      					<%@ include file="nav_max.jsp"%> 
      		<%		} else {
      		%>
      				<!-- add: the navigation to the web page. nav_min shows the Home and the Contact Us   -->
      				<%@ include file="nav_min.jsp"%>
      		<%	
      				}
    			}
	    		
	    	} else { %>
	    	
	    		<%@ include file="nav_min.jsp"%>
	    	<%
	    	}
    	}
	    %>
			
  						   
      	<!-- add: the content -->
      	<%@ include file="content.jsp"%>
      	<br />
      	<!-- add: the footer -->
      	<%@ include file="footer.jsp"%>
	</div> 
</body> 
    

FROM result.jsp

<!-- including the navigation - if the user successfully LOGGED IN the nav_max.jsp is shown otherwise nav_min.jsp is shown -->
	  	<!-- to do that I use the variable logged_in or is_admin from the model -->
	  	<!-- this variable set in the procedure login_result ( MainController.java ) -->
      	
      	<%
			String sess_adm = new String();
      	    //String sess_adm1 = new String();
			// I have added sess_admin to the session in index.jsp
			sess_adm  = (String)session.getAttribute("sess_adm");
			//sess_adm1  = (String)session.getAttribute("sess_adm1");
	  	
			// if the user has logged in as an admin then show the navigation bar for the admin
			if (sess_adm.equals("true")) {
		%> 
				<!-- add: the navigation to the web page. nav_max shows the whole navigation bar   -->
				<%@ include file="nav_admin.jsp"%>
		<%
			} else {
      	 		// String.valueOf: converts the variable logged_in to the String
		    	// I have added logged_in to the model in the MainController.java
    			String is_logged = String.valueOf(request.getAttribute("logged_in"));
    			// if the user is logged in then show the whole navigation bar
		    	if(is_logged.equals("true")) {
  		%>			
      				<!-- add: the navigation to the web page. nav_max shows the whole navigation bar   -->
      				<%@ include file="nav_max.jsp"%>
      	<%
      					
					// if the user is not logged in then show the navigation with Home and Contact Us
				}  else {
		%>
					<!-- add: the navigation to the web page. nav_min shows the Home and the Contact Us   -->
      				<%@ include file="nav_min.jsp"%>
		<%
				}
			}
      		
		%>	
		  	
	    
      	
      		
  