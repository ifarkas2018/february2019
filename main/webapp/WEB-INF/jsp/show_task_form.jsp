<!-- project : Time Schedule, author : Ingrid Farkas, 2019 -->
<!-- included in show_task_info.jsp -->
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
	
			<%
				// reading from the model variable whether the user is doing Add Task  
				String is_add_task = (String)(request.getAttribute("is_add_task"));
			%>
			<!-- "w3-third" class uses 66% of the parent container -->
	        <div class="w3-twothird w3-container">
	            <br/>
	            <br/>
	            <!--  w3-text-theme-m1 CSS rule which sets the color of the text ( file styles1.css ) -->
	            <div class="w3-container  w3-light-grey w3-padding-32 w3-padding-large" id="show_sched_info"> 
	              	<div class="w3-content w3-text" style="max-width:600px">
	              		<%
	              		if (is_add_task.equals("false")) { // it is not Add Task but Update Task
	              		%>
	              			<!-- w3-center centers the text -->
		                	<h4 class="w3-center"><b>Update Task</b></h4>
		                	<!-- after clicking on the button localhost:8080/show_update_results is called using method post -->
		                	<form action="/show_update_results" method="post" target="_blank">
		                <%	
		                } else { // it is Add Task
		                %>
		                	<!-- w3-center centers the text -->
		                	<h4 class="w3-center"><b>Add Task</b></h4>
		                	<!-- after clicking on the button localhost:8080/add_task_results is called using method post -->
		                	<form action="/add_task_results" method="post" target="_blank">
		                <%
		                }
		                %>
		                
		                <%
	              		if (is_add_task.equals("false")) { // it is not Add Task but Update Task
	              		%>
		                	<input class="w3-input w3-border" type="text" name="task_id" value="${task_info.taskId}" >
		                  	<div class="w3-section">
		                    	<label>Task Name</label>
		                    	<!--  ??????????????????????????????  -->
		                    	<!--  when removing REQUIRED go to MainController, show_schedule, and in method show_schedule remove required=true 
		                    			for the employee_id --> <!-- required=true -->
		                    	<input class="w3-input w3-border" type="text" name="task_name" value="${task_info.taskName}" > <!-- input field for entering the task name -->
		                  	</div>
		                <%	
		                } else { // it is Add Task
		                %>
		                  	<div class="w3-section">
		                    	<label>Task Name</label>
		                    	<input class="w3-input w3-border" type="text" name="task_name"> <!-- input field for entering the task name -->
		                  	</div>
		                <%
		                }
		                %> 	
		                
		                <%
	              		if (is_add_task.equals("false")) { // it is not Add Task but Update Task
	              		%>
		                  	<div class="w3-section">
		                    	<label>Date ( format dd/mm/yyyy ) </label>
		                    	<input class="w3-input w3-border" type="text" name="task_date" value="${task_info.taskDate}" required=true> <!-- input field for entering the date of the task -->
		                  	</div>
		                <%	
		                } else { // it is Add Task
		                %>  
		                	<div class="w3-section">
	                    	<label>Date ( format dd/mm/yyyy ) </label>
	                    	<input class="w3-input w3-border" type="text" name="task_date" required=true> <!-- input field for entering the date of the task -->
	                  		</div>
		                <%
		                }
		                %>
		                
		                <%
	              		if (is_add_task.equals("false")) { // it is not Add Task but Update Task
	              		%>	
		                  	<div class="w3-section">
		                    	<label>Start Time ( format hh:mm )</label>
		                    	<input class="w3-input w3-border" type="text" name="start_time" value="${task_info.taskStartTime}" required=true> <!-- input field for entering the start time -->
		                  	</div>
		                <%	
		                } else { // it is Add Task
		                %>
		                	<div class="w3-section">
		                    	<label>Start Time ( format hh:mm )</label>
		                    	<input class="w3-input w3-border" type="text" name="start_time" required=true> <!-- input field for entering the start time -->
		                  	</div>
		                <%
		                }
		                %> 
		                
		                <%
	              		if (is_add_task.equals("false")) { // it is not Add Task but Update Task
	              		%>	 
		                  	<div class="w3-section">
		                    	<label>End Time ( format hh:mm )</label>
		                    	<!--  ??????????????????????????????  -->
		                    	<!--  when removing REQUIRED go to MainController, show_schedule, and in method show_schedule remove required=true
		                    		   for the last-name -->
		                    	<input class="w3-input w3-border" type="text" name="end_time" value="${task_info.taskEndTime}" required=true> <!-- input field for entering the end time -->
		                  	</div>
		                <%	
		                } else { // it is Add Task
		                %>  
		                	<div class="w3-section">
		                    	<label>End Time ( format hh:mm )</label>
		                    	<input class="w3-input w3-border" type="text" name="end_time" required=true> <!-- input field for entering the end time -->
		                  	</div>
		                <%
		                }
		                %>	
		                  	<!-- w3-camo-grey is a CSS rule in the colors.css -->
		                  	<button class="w3-btn w3-camo-grey">Submit</button> 
		                </form>
	              	</div>
	            </div>
	            <br />
	        </div>  <!-- end of class="w3-twothird w3-container" -->
	    </div> <!-- end of class="w3-row w3-margin" --> 
    </div> <!-- end of the contact section -->
</div> <!-- end of class="content" -->
