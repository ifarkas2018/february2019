<!-- project : Time Schedule, author : Ingrid Farkas, 2019 -->
<!-- included in show_sched_form.jsp -->

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
	            <!--  w3-text-theme-m1 CSS rule which sets the color of the text ( file colors.css ) -->
	            <div class="w3-container  w3-light-grey w3-padding-32 w3-padding-large" id="show_sched_info"> 
	              	<div class="w3-content w3-text" style="max-width:600px">
	              		<!--  w3-center centers the text -->
		                <h4 class="w3-center"><b>Show Schedule</b></h4>
		                <!--  after clicking on the button show_sched_results.jsp is called using method post -->
		                <form action="/show_sched_results" method="post" target="_blank">
		                  	<div class="w3-section">
		                    	<label>Employee ID</label>
		                    	<!--  ??????????????????????????????  -->
		                    	<!--  when removing REQUIRED go to MainController, show_schedule, and in method show_schedule remove required=true 
		                    			for the employee_id -->
		                    	<input class="w3-input w3-border" type="text" name="employee_id" required=true> <!-- input field for entering the employee_id -->
		                  	</div>
		                  	<div class="w3-section">
		                    	<label>First Name</label>
		                    	<!--  ??????????????????????????????  -->
		                    	<!--  when removing REQUIRED go to MainController, show_schedule, and in method show_schedule remove required=true 
		                    		  for the employee_id -->
		                    	<input class="w3-input w3-border" type="text" name="first_name" required=true> <!-- input field for entering the first_name -->
		                  	</div>
		                  	<div class="w3-section">
		                    	<label>Last Name</label>
		                    	<!--  ??????????????????????????????  -->
		                    	<!--  when removing REQUIRED go to MainController, show_schedule, and in method show_schedule remove required=true
		                    		   for the last-name -->
		                    	<input class="w3-input w3-border" type="text" name="last_name" required=true> <!-- input field for entering the last_name -->
		                  	</div>
		                  	<div class="w3-section">
		                    	<label>Date ( format dd/mm/yyyy ) </label>
		                    	<!--  ??????????????????????????????  -->
		                    	<!--  when removing REQUIRED go to MainController, show_schedule, and in method show_schedule remove required=true 
		                    		  for the date -->
		                    	<input class="w3-input w3-border" type="text" name="date" required=true>
		                  	</div>
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
