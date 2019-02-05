<!-- author: Ingrid Farkas; project: Time Management -->
<!-- navigation bar -->
<!-- w3-theme-m1 is a CSS rule from the colors.css -->
<div class="w3-bar w3-theme-m1 w3-round-xxlarge"> <!-- $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$w3-margin if it needs to be moved to right --> 
    <!-- w3-text-theme-m1 : CSS rule defined in colors.css -->
    <span class="w3-bar-item w3-text-theme-m1">H &amp; C Web Consulting</span>
    <!-- w3-text-theme-m1 is used to set the text of the link to blue -->
    <!-- w3-hover-theme-l4 when the user hovers the mouse, there is no change in the background color of the navigation bar, the text color is orange -->
    <!-- w3-mobile used to create a responsive navbar with responsive dropdown links -->
    <a href="home" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Home</a>
    
    <%@ page language="java" import="java.util.*"%>
    <!-- the user logged in as admin - the Employee link ( on the navigation bar ) is shown -->
  	
    <% 
    	
        
    	String sess_adm = (String)(request.getAttribute("is_admin")); // session variable - is the user logged in as admin
    	// String sess_adm = String.valueOf(request.getAttribute("is_admin"));
    	String sess_reg = new String(); // session variable - is the user logged in as admin
 	%>
 		
 	<%	
    	// I have added is_root to the model in the MainController.java
    	// when the URL is localhost:8080 is_root is true
    	// String is_root = String.valueOf(request.getAttribute("is_root")); 
    	
    	
    	String attribute; 
    	boolean exists_reg = false; // whether the attribute "sess_reg" was earlier added to the session
    	boolean exists_adm = false; // whether the attribute "sess_adm" was earlier added to the session
    	
    	Enumeration attributes = session.getAttributeNames();
    	while ((attributes.hasMoreElements()) && (!(exists_reg))) {
    		attribute = (String)(attributes.nextElement());
    		if (attribute.equals("sess_reg"))
    			exists_reg = true;
    	} 
    	if (exists_reg)
    		sess_reg = (String)session.getAttribute("sess_reg");
    	else {
    		// String.valueOf: converts the value of the variable logged_in to the String
    		 // I have added logged_in to the model in the MainController.java
    		sess_reg = (String)(request.getAttribute("logged_in"));
    		if (sess_reg != null)
	    		if (sess_reg.equals("true")){
	    			session.setAttribute("sess_reg", sess_reg);
	    			exists_reg = true;
	    		} 
    	} 
    	%>	
    	
    	<% 	
    	attributes = session.getAttributeNames();
    	while ((attributes.hasMoreElements()) && (!(exists_adm))) {
    		attribute = (String)(attributes.nextElement());
    		if (attribute.equals("sess_adm"))
    			exists_adm = true;
    		else
    			exists_adm = false;
    	} 
    	%>
    	<!-- <input type="text" value="<%=sess_adm%>" > -->  
    	<% 
    	if (exists_adm){ 
    		sess_adm = (String)session.getAttribute("sess_adm"); 
    	%>
    		<!-- Bla<input type="text" value="<%=sess_adm%>" > -->
    	<% 
    	} else {
    		// reading the attribute is_admin from the model ( set in the MainController.java )
        	// sess_adm  = String.valueOf(request.getAttribute("is_admin"));
    		%>
        	<!-- <input type="text" value="<%=sess_adm%>" > -->
        	<%
        	if (sess_adm != null)
	        	if (sess_adm.equals("true")){
	        		session.setAttribute("sess_adm", sess_adm);
	        		exists_adm = true;
	        	}
    	} 
    	%>	
    		
    	<%
		// if the user has logged in as an regular user then set the session variable the user logged in as regular user
    	// if (is_reg_log.equals("true")) {
  			// adding to the session the attribute sess_reg
  			// sess_adm - did the user log in as admin
  			//sess_adm = "true";
  			//session.setAttribute("sess_reg", is_reg_log);
    	// if the user has logged in as an admin then set the session variable the user logged in as admin
    	// if (is_admin.equals("true")) {
  			// adding to the session the attribute sess_adm 
  			// sess_adm - did the user log in as admin
  			//sess_adm = "true";
  			//session.setAttribute("sess_adm", sess_adm);
    	//} else if (((!(is_reg_log.equals("true"))) || (!(sess_reg.equals("true")))) && (!((is_root).equals("true")))){ // 
    		//sess_adm  = (String)session.getAttribute("sess_adm"); 
    		//session.
    	//}
    	
    	
    	if (exists_adm) { // the user logged in as admin
    %>
		    <!-- w3-text-theme-m1 is used to set the text of the link to blue -->
			<!-- w3-hover-theme-l4 when the user hovers the mouse, there is no change in the background color of the navigation bar, the text color is orange -->
			<!-- w3-mobile used to create a responsive navbar with responsive dropdown links -->
			<a href="addempl_form" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Employee</a>
	<%
    	}
	%>
	
	<% 
		// reading the attribute logged_in from the model ( set in the MainController.java )
		// String reg_log_in = String.valueOf(request.getAttribute("logged_in"));
		// if the user logged in as a regular user or as an admin show the Log Out ( navbar )  
		if (exists_reg || exists_adm) { 
	%>
		    <!-- w3-dropdown-hover is used to create hoverable drop down element -->
		    <div class="w3-dropdown-hover w3-mobile">
		        <!-- w3-theme-m1 is used to set the background color of the button, w3-text-theme-m1 is used to set the color of the text on the navigation bar entry -->
		        <!-- w3-hover-theme-l4 is used to set on the hover the background color of the button and the color of the text -->
		        <a href="#" class="w3-button w3-theme-m1 w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">&#128197;&nbsp;Schedule</a> <!-- &#128197; HTML entity for a calendar icon -->
		        <!-- w3-card-4 adds to the container a 4px bordered shadow -->
		        <!-- w3-dropdown-content is used to create responsive dropdown links -->
		        <!-- w3-theme-m1 is a CSS rule from the colors.css ( sets the color of the text and the background color ) -->
		        <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-theme-m1" style="width:8%;">
			        <!-- w3-text-theme-m1  is a CSS rule from the colors.css ( sets the color of the text to the blue ) -->
			        <!-- w3-hover-text-orange is used to set the orange color of the text when the user hovers the mouse over the link --> 
			        <a href="add_schedule" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Add Schedule</a> 
			        <a href="show_sched_form" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Show Schedule</a>
			    </div>      
			</div>
			
			<!-- ????????????????????????????????????????????????????????????????????  -->
			<!-- add here EMPLOYEE Add Employee, View Employee -->
		
			<!-- w3-dropdown-hover is used to create hoverable drop down element -->
			<div class="w3-dropdown-hover w3-mobile">
			<!-- w3-theme-m1 is used to set the background color of the button, w3-text-theme-m1 is used to set the color of the text on the navigation bar entry -->
			<!-- w3-hover-theme-l4 is used to set on the hover the background color of the button and the color of the text -->
			<a href="#" class="w3-button w3-theme-m1 w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Task</a> <!-- &#128197; HTML entity for a calendar icon -->
			<!-- w3-card-4 adds to the container a 4px bordered shadow -->
			<!-- w3-dropdown-content is used to create responsive dropdown links -->
			<!-- w3-theme-m1 is a CSS rule from the colors.css ( sets the color of the text and the background color ) -->
			<div class="w3-dropdown-content w3-bar-block w3-card-4 w3-theme-m1" style="width:8%;">
				<!-- w3-text-theme-m1  is a CSS rule from the colors.css ( sets the color of the text to the blue ) -->
				<!-- w3-hover-text-orange is used to set the orange color of the text when the user hovers the mouse over the link --> 
				<a href="task_list" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Task List</a> 
				<a href="task_add" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Add Task</a>
				<a href="task_update" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Update Task</a>
				<a href="task_delete" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-text-orange w3-hover-none w3-mobile">Delete Task</a>
			</div>      
		</div>
	<%
		}
	%>
	
	<!-- w3-text-theme-m1 is used to set the text of the link to blue -->
	<!-- w3-hover-theme-l4 when the user hovers the mouse, there is no change in the background color of the navigation bar, the text color is orange -->
	<!-- w3-mobile used to create a responsive navbar with responsive dropdown links -->
	<a href="contact_us" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile">Contact Us</a>
	
	<%
		// reading the attribute logged_in from the model ( set in the MainController.java )
		// String reg_log_in = String.valueOf(request.getAttribute("logged_in"));
		// if the user logged in as a regular user or as an admin show the Log Out ( navbar )  
		if (exists_reg || exists_adm) {
	%>
		<!-- w3-hover-theme-l4 is used to set on the hover the background color of the button and the color of the text -->
		<!-- w3-mobile used to create a responsive navbar with responsive dropdown links -->
		<!-- w3-right used to right-align a specific link -->
		<a href="login_form" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile w3-right">Log Out</a>
		
	<%
		// otherwise show Log In
		} else {
	%>
		<a href="login_form" class="w3-bar-item w3-button w3-text-theme-m1 w3-hover-theme-l4 w3-mobile w3-right">Log In</a>
	<%
		}
	%>
</div> <!-- end of class="w3-bar w3-theme-m1 w3-round-xxlarge" -->