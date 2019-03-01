<!--  project : Time Schedule, author : Ingrid Farkas, 2019 -->
<!--  show_schedule.jsp shows the results of the request for listing the schedule for the certain EMPLOYEE on the certain DAY ( show_sched_form.jsp ) -->
<!--  show_schedule.jsp is included in show_sched_results.jsp -->

<!--  include the JSTL Core library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body>

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
	            <br/>
	            <br/>
	            <br/>
	            <br/>
	        </div>
	        
	        <!-- "w3-twothird" class uses 66% of the parent container -->
	        <div class="w3-twothird w3-container"> 
	            <!--  Contact section -->
	            <br/>
	            <br/>
	            <!--  w3-light-grey sets the background colour of the container -->
	            <div class="w3-container w3-light-grey w3-padding-32 w3-padding-large" id="show_sched_info"> 
	              	<div class="w3-content w3-text" style="max-width:600px">
	                    <h4 class="w3-center"><b>Show Schedule</b></h4> 
	 					<h6>Employee Name: ${enter_f_name} ${enter_l_name}</h6> <!-- showing the employee name -->
	 					<h6>Date of the Schedule: ${enter_date}</h6> <!-- showing the date of the schedule -->
	 					
						<form action="http://localhost:8080/home" method="post"> <!-- when submitted the localhost:8080/home is shown -->
						<!-- otherwise localhost:8080 is shown -->
	 					
							<div>
								<!-- !!!!!!!!!!!!!!!!!!!11 HIDE THE next line -->
								Id:<input type="text" name="taskId" id="taskId"><br><br>
								<br/>
								Please click on the table fist to select the task!
								<br/>
								<!-- creating the table with the tasks -->
								<table class="w3-table w3-bordered w3-centered" id="table">
									<!-- creating the table row with the headings -->
									<tr>
						            	<th>Task Id</th>
						            	<th>Task Name</th>
						            	<th>Start Time</th>
						           		<th>End Time</th>
		       						</tr>
		       						<!-- for each element of the list empSchedTaskInfos, show the id of the task, the name of the task, the start time and the end time of the task -->
		       						<!-- empSchedTaskInfos is the attribute from the MainController.java, which was added to the model -->
		       						<c:forEach items="${empSchedTaskInfos}" var="schedinfo"> 
		       	   						<tr class="w3--cell-bottom">
		               						<td>${schedinfo.taskId}</td> <!-- show the id of the task -->
			       							<td>${schedinfo.taskName}</td>	<!-- show the name of the task -->
			       							<td>${schedinfo.taskStartTime}</td> <!-- show the start time of the task -->
			       							<td>${schedinfo.taskEndTime}</td> <!-- show the end time of the task -->  
			   							</tr>
		       						</c:forEach> <!-- end of the forEach -->
		   						</table>
		    				
		    					<br />
		    					<br />
								<!-- w3-camo-grey is a CSS rule in the styles.css -->
								<button class="w3-btn w3-center w3-tiny w3-padding-small w3-camo-grey" id="theBtn">Back To Home</button> 
	    					</div>
	    				</form>
					</div> <!-- end of the class="w3-content w3-text" -->
				</div> <!-- end of the class="w3-container w3-light-grey w3-padding-32 w3-padding-large" -->
				<br />			
			</div> <!-- end of the class=""w3-twothird w3-container" -->	
			
		</div> <!-- end of the class=w3-row w3-margin" -->
	</div> <!-- end of the contact section -->
</div> <!-- end of class="w3-content" -->
</body> 
