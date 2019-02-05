<!-- project : Time Schedule, author : Ingrid Farkas, 2019 -->
<!-- included in addempl_form.jsp -->
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
		                <h4 class="w3-center"><b>Add Employee</b></h4>
		                <!-- after clicking on the button addempl_result is called using method post -->
		                <form action="/addempl_result" method="post" target="_blank">
		                  	<div class="w3-section">
		                    	<label>First Name</label>
		                    	<!--  ??????????????????????????????  -->
		                    	<!--  when removing REQUIRED go to MainController, addempl_result, and in method addempl_result remove required=true 
		                    			for the first_name -->
		                    	<input class="w3-input w3-border" type="text" name="first_name" required=true> <!-- input field for entering the first name -->
		                  	</div> 
		                  	<div class="w3-section">
		                    	<label>Last Name</label>
		                    	<!--  ??????????????????????????????  -->
		                    	<!--  when removing REQUIRED go to MainController, addempl_result, and in method addempl_result remove required=true 
		                    			for the last_name -->
		                    	<input class="w3-input w3-border" type="text" name="last_name" required=nav
		                    	> <!-- input field for entering the last name -->
		                  	</div> 
		                  	<div class="w3-section">
		                    	<label>Department</label>
		                    	<!-- <input class="w3-input w3-border" type="text" name="department"> --> <!-- input field for entering the department -->
		                    	<select class="w3-input w3-border" id="department" name="department">
								    <option value="Sales">Sales</option>
								    <option value="Management">Management</option>
								    <option value="IT">IT</option>
								    <option value="Administration">Administration</option>
								    <option value="Support">Support</option>
								</select> 
		                  	</div>
		                  	<button class="w3-btn w3-camo-grey">Add Employee</button> 
		                </form>
	              	</div>
	            </div>
	            <br />
	        </div>  <!-- end of class="w3-twothird w3-container" -->
	    </div> <!-- end of class="w3-row w3-margin" --> 
    </div> <!-- end of the contact section -->
</div> <!-- end of class="content" -->
