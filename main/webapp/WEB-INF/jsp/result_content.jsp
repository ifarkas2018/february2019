<!-- project : Time Schedule, author : Ingrid Farkas, 2019 -->
<!-- included in result.jsp -->

<script type="text/javascript">
	var home_URL="http://localhost:8080/home"; // global variable
	// shows the web page ( URL: home_URL ) in the browser
	function show_homepg() {
		window.location.href = home_URL;
	}
</script>

<!-- Contact section -->
<div class="w3-content">
	<div class="w3-row w3-margin">
	    <!-- "w3-third" class uses 33% of the parent container -->
	    <div class="w3-third">
	    	&nbsp; &nbsp; &nbsp; &nbsp;
	        <br />
	        &nbsp; &nbsp; &nbsp; &nbsp;
	        <!-- first image on the left hand side from the form -->
	        <img src="../../images/woman_on_phone.jpg" style="width:100%">
	        &nbsp; &nbsp; &nbsp; &nbsp;
	        <!-- second image on the left hand side from the form -->
	        <img src="../../images/woman_with_laptop.jpg" style="width:100%"> 
	    </div>
	
		<!-- "w3-third" class uses 66% of the parent container -->
	    <div class="w3-twothird w3-container">
	        <br/>
	        <br/>
	        <!--  w3-text-theme-m1 CSS rule which sets the color of the text ( file styles.css ) -->
	        <div class="w3-container  w3-light-grey w3-padding-32 w3-padding-large" id="show_sched_info"> 
	            <div class="w3-content w3-text" style="max-width:600px">
	            	<!--  w3-center centers the text -->
	            	<!-- page_title : the title of the page ( an attribute added to the model in MainController.java ) -->
		            <h4 class="w3-center"><b>${page_title}</b></h4>
					<br />
					<br />
					<br />
					
					<%
		            	// String.valueOf: converts the parameter to String
		                // I have added is_red ( whether the text will be in red ) to the model in the MainController.java
    					String text_red = String.valueOf(request.getAttribute("is_red")); // is the text in red
    					// if the text is not supposed to be red - don't show it in red
		                if(text_red.equals("false")) {
  					%>			
  								
  							<!-- message_shown : the message to be shown ( an attribute added to the model in MainController.java ) -->
      						<h5 class="w3-center"><b>${message_shown}</b></h5>
      				<%
      					// if the text is to be in red
						}  else {
					%>
						<!-- message_shown : the message to be shown ( an attribute added to the model in MainController.java ) -->
					    <h5 class="w3-center w3-text-red"><b>${message_shown}</b></h5>
					<%
						}
    					
						// depending on whether the user logged in, after clicking on the button one of the two URLs are shown
						String already_log = (String)(request.getAttribute("already_login"));
						// if the user is logged in , after clicking on the button, localhost:8080/home is shown
						if (already_log.equals("true")) { 
					%>
							<form action="http://localhost:8080/home" method="post"> <!-- when submitted the localhost:8080/home is shown -->
						<!-- otherwise localhost:8080 is shown -->
					<% } else { %>
							<form action="http://localhost:8080" method="post"> <!-- when submitted the localhost:8080 is shown -->
					<% }
					%>
	  					Logged in ${logged_in}
	  					<input class="w3-input w3-border" type="text" name="loggedin" value="${logged_in}"> <!-- input field containing logged_in -->
	  					<br/>
			            <div class="w3-container w3-center">
			                <!-- add the button to the page -->
			                <button class="w3-btn w3-center w3-tiny w3-padding-small w3-camo-grey">Home</button> 
			            </div>
			            <br />
			            <br />
			            <br />
			            <br />
			            <br />
			            <br />
			            <br />
		            </form>
	              	</div>
				</div>
	            <br />
	        </div>  <!-- end of class="w3-twothird w3-container" -->
	    </div> <!-- end of class="w3-row w3-margin" --> 
    </div> <!-- end of the contact section -->
</div> <!-- end of class="content" -->
