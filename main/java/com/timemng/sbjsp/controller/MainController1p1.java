// project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.controller;

//importing the packages
import java.util.ArrayList;
import java.util.List;

import com.timemng.sbjsp.dao.EmpSchedTaskDAO1p1;
import com.timemng.sbjsp.model.EmpSchedTaskInfo1p1;
import com.timemng.sbjsp.dao.ScheduleDAO1p1;
import com.timemng.sbjsp.model.ScheduleInfo1p1;
import com.timemng.sbjsp.model.EmpNameInfo1p1;
import com.timemng.sbjsp.dao.EmpNameDAO1p1;
import com.timemng.sbjsp.model.LoginInfo1p1;
import com.timemng.sbjsp.dao.LoginDAO1p1;
import com.timemng.sbjsp.dao.EmplDAO1p1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController1p1 {

	 
	 @Autowired
	 private EmpSchedTaskDAO1p1 empSchedTaskDAO;
	 @Autowired
	 private ScheduleDAO1p1 schedDAO;
	 @Autowired
	 private LoginDAO1p1 empLoginDAO;
	 @Autowired
	 private EmplDAO1p1 empDAO;
	 private String loginID; // the logged in user's ID
	 private String empID; // the employee's ID
	 private String fName; // the employee's first name
	 private String lName; // the employee's last name
	 @Autowired
	 private EmpNameDAO1p1 empNameDAO;
	    
	// if the requested URL is localhost:8080, method is GET do 
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String index(Model model) {
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before ( and is still logged in )
        return "index1"; // return the index1.jsp
    }
    
    // if the requested URL is localhost:8080, method is GET do 
    @RequestMapping(value = { "/" }, method = RequestMethod.POST)
    public String index1(Model model) {
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before ( and is still logged in )
        return "index1"; // return the index1.jsp
    }
    
    
    // if the requested URL is localhost:8080/home, method is GET do
    // used if the user before logged in 
    @RequestMapping(value = { "home" }, method = RequestMethod.GET)
    public String indhome( Model model ) {
    	model.addAttribute("logged_in", "false"); // the user is not logged in
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
        return "index1"; // return the index1.jsp
    }
    
 
    // if the requested URL is localhost:8080/home, method is GET do
    // used if the user before logged in 
    @RequestMapping(value = { "home" }, method = RequestMethod.POST)
    public String indhome_post( Model model ) { 
    	model.addAttribute("is_admin", "false"); // @@@@@@@@@@@@@@@@@@the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
        return "index1"; // return the index1.jsp
    }
    
    // if the requested URL is localhost:8080/logform, method is GET 
    @RequestMapping(value = { "logform" }, method = RequestMethod.GET)
    public String logform(Model model) {
    	// adding the attributes to the model
    	model.addAttribute("logged_in","false"); // the user didn't log in
    	model.addAttribute("is_admin","false"); // the user didn't log in as admin 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before ( and is still logged in )
    	return "logform"; // return the logform.jsp
    }
    
    // if the requested URL is localhost:8080/login_result, method is POST ( from logfcont.jsp )
    @RequestMapping(value = "login_result", method = RequestMethod.POST) 
    // user_name is an input element in logfcont.jsp. The user entered the user_name, user_passw 
	public String login_result2(Model model, @RequestParam(value="user_name", required=true ) String user_name, // 
		@RequestParam(value="user_passw", required=true) String user_passw) {
    	try { 
        	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    		model.addAttribute("logged_in","true"); // setting the default value for whether the user logged in
    		model.addAttribute("already_login", "false"); // did the user log in before ( and is still logged in )
    		if (user_name.equals("admin")) {
    			// add to the SQL query the WHERE clause - where ( user_name = "admin" ) and ( password = entered value for password )
    			// if the user didn't enter password ( in the form ) then this method returns false
    			if (empLoginDAO.addToQueryStr( "admin", user_passw )) {
    				// list is a list of objects of type LoginInfo ( user name, password ) that match the entered values of user name and password
    				List<LoginInfo1p1> list = empLoginDAO.getLogin();
    				// is in the database a user name and the password which was entered in the form
    				if (!(list.isEmpty())) {
    				    model.addAttribute("is_admin", "true"); // the user logged in as admin
    				    LoginInfo1p1 log_info = list.get(0); // retrieving the object of type LoginInfo1p1 which contains the employee ID
    				    loginID = log_info.getEmployeeID(); // retrieving the employee ID
    				    return "index1"; // show the index1.jsp
    				}
    				else { 
    					// add this to a METHOD ???????????????????????????????????
    					model.addAttribute("is_admin", "false"); // the user didn't log in as admin
    					model.addAttribute("page_title", "Log In");
						// setting the attribute logged_in to false - the user didn't log in
						model.addAttribute("logged_in", "false");
						model.addAttribute("message_shown", "The user with the user name " + user_name + " and the entered password doesn't exist!");
						// the message should be in red ( adding it to the model )
						model.addAttribute("is_red", "true");
						return "result"; // show the result.jsp     			
    				}	
    			} else { // the user didn't enter the password
    				// add this to a METHOD ???????????????????????????????????
    				model.addAttribute("is_admin", "false"); 
					model.addAttribute("page_title", "Log In");
					// setting the attribute logged_in to false - the user didn't log in
					model.addAttribute("logged_in", "false");
					model.addAttribute("message_shown", "The user with the user name " + user_name + " and the entered password doesn't exist!");
					// the message should be in red ( adding it to the model )
					model.addAttribute("is_red", "true");
					return "result"; // show the result.jsp  
    			}
    		
			} else if (empLoginDAO.addToQueryStr( user_name, user_passw )) { // the user is logging in as regular user
					// add the rest of the query to the query
		    		// addToQueryStr returns FALSE - if the user didn't enter user name or password ( in the form ) 
					// list is a list of objects of type LoginInfo ( user name, password ) that match the entered values of user name and password
					List<LoginInfo1p1> list = empLoginDAO.getLogin();
					// is in the database a user name and the password which was entered in the form
					if (!(list.isEmpty())) {
						model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user did log in 
						model.addAttribute("is_admin", "false"); // the user is not logged in as admin
						LoginInfo1p1 log_info = list.get(0); // retrieving the object of type LoginInfo1p1 which has the employee ID
						loginID = log_info.getEmployeeID(); // retrieving the employee ID
						return "index1"; // show the index1.jsp
					} else { // the user with that user name and password doesn't exist
						// add this to a METHOD ???????????????????????????????????
						model.addAttribute("page_title", "Log In");
						// setting the attribute logged_in to false - the user didn't log in
						model.addAttribute("logged_in", "false");
						model.addAttribute("is_admin", "false"); // the user is not logged in as admin
						model.addAttribute("message_shown", "The user with the user name " + user_name + " and the entered password doesn't exist!");
						// the message should be in red ( adding it to the model )
						model.addAttribute("is_red", "true");
						return "result"; // show the result.jsp 
					}
				    
			} else { // the user didn't enter the password
				model.addAttribute("page_title", "Log In");
				model.addAttribute("message_shown", "You didn't enter the user name and the password!"); // adding the message to the model  
				model.addAttribute("is_red", "true"); // the message should be in red ( adding it to the model )
				model.addAttribute("logged_in", "false"); // set the attribute logged_in to false - the user didn't log in
				return "result"; // show the result.jsp 
			}
    	} catch  ( Exception e ) {
        	e.printStackTrace();
        	model.addAttribute("page_title", "Log In");
        	model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
        	model.addAttribute("is_red", "true"); // the message should be in red ( adding it to the model )
        	model.addAttribute("logged_in", "false"); // setting the attribute logged_in to false - the user didn't log in
        	return "result";
        }
	}
        
    // if the requested URL is localhost:8080/addempl_result, method is POST
    @RequestMapping(value = "addempl_result", method = RequestMethod.POST)
    // first_name, last_name, department is an input element in addempl_fcont.jsp
    // the user entered the first name, last name, department of the new employee to be added to the database
	public String addempl_result(Model model, @RequestParam(value="first_name", required=true ) String first_name, // 
		@RequestParam(value="last_name", required=true) String last_name, @RequestParam(value="department") String department) {
    	try {
    		// setting the model attributes ( I can access them from .jsp )
        	model.addAttribute("logged_in", "true"); // the user is logged in 
        	model.addAttribute("is_admin", "true"); // the user is logged in as admin
        	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
        	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    		// add the rest of the query to the query - method returns FALSE - if the user didn't enter first name or last name ( in the form )  
    		if (empDAO.addToQueryStr(first_name, last_name, department)) {
    			int numRows = empDAO.addEmployee( first_name, last_name, department );
		    	// addEmployee returns a positive value if the employee was added to the database
				if ( numRows > 0 ) {
					model.addAttribute("page_title", "Add Employee"); // adding the page_title variable to the model
					// setting the attribute logged_in to true - the user is logged in
					model.addAttribute("logged_in", "true");
					model.addAttribute("message_shown", "The user with the name " + first_name + " " + last_name + " was successfully added to the database!");
					// the message shouldn't be in red ( adding it to the model )
					model.addAttribute("is_red", "false");
					model.addAttribute("numRows", numRows);
					return "result"; // show the result.jsp 
				} else {
					model.addAttribute("page_title", "Add Employee"); // adding the page_title variable to the model
					model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
		        	model.addAttribute("is_red", "true"); // the message should be in red ( adding  it to the model )
		        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
					return "result"; // show the result.jsp 
				} 
			}
    		else {
    			model.addAttribute("page_title", "Add Employee"); // adding the page_title variable to the model
    			model.addAttribute("message_shown", "You didn't enter the first name and the last name of the employee and the new employee wasn't added to the database!"); // adding the message to the model
	        	model.addAttribute("is_red", "true"); // the message should be in red ( adding it to the model )
	        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
				return "result"; // show the result.jsp 
    		}
    	} catch  ( Exception e ) {
        	e.printStackTrace();
        	model.addAttribute("page_title", "Add Employee"); // adding the page_title variable to the model
        	model.addAttribute("message_shown", "A problem occured while accessing the database!"); // adding the message to the model
        	model.addAttribute("is_red", "true"); // the message should be in red ( adding it to the model )
        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
        	return "result";
        }
	}
    
    // if the requested URL is localhost:8080/addempl_form, method is GET do 
    @RequestMapping(value = { "addempl_form" }, method = RequestMethod.GET)
    public String addempl_form(Model model ) { //@@@@@@@@@@@@@@@@@@@@ , @RequestParam(value="is_admin", required=true ) String is_admin
    	// setting the model attributes ( I can access them from .jsp )
    	model.addAttribute("logged_in", "true"); // the user is not logged in
    	model.addAttribute("is_admin", "true"); // the user is logged in as admin
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before ( and is still logged in )
    	return "addempl_form";
    }
    
    // if the requested URL is localhost:8080/add_schedule, method is GET do 
    @RequestMapping(value = { "add_schedule" }, method = RequestMethod.GET)
    public String add_schedule(Model model) {
        return "add_schedule"; // return the add_schedule.jsp
    }
    
    // if the requested URL is localhost:8080/show_sched, method is GET 
    // when the user clicks on the navigation bar on Schedule, Show Schedule
    @RequestMapping(value = { "show_sched" }, method = RequestMethod.GET)
    public String show_sched(Model model) {
    	// setting the model attributes ( I can access them from .jsp )
    	model.addAttribute("logged_in", "false"); // the user is not logging in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	// show_sched_form is shown for Show Schedule and Update Schedule
    	// is_update has to be set whether it is update ( or show schedule )   
    	model.addAttribute("is_update","false"); // is it update schedule
    	model.addAttribute("is_show_sched","true"); // is it show schedule
    	model.addAttribute("is_add_task","false"); // is it add task
    	return "show_sched_form"; // return the show_sched_form.jsp
    }
    
    // if the requested URL is localhost:8080/update_sched, method is GET 
    // when the user clicks on the navigation bar on Schedule, Update Schedule
    @RequestMapping(value = { "update_sched" }, method = RequestMethod.GET)
    public String update_sched(Model model) {

    	// setting the model attributes ( I can access them from .jsp )
    	model.addAttribute("logged_in", "false"); // the user is not logging in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	// show_sched_form is shown for Show Schedule and Update Schedule
    	// is_update has to be set whether it is update ( or show schedule )
    	model.addAttribute("is_update","true"); // it is update schedule
    	model.addAttribute("is_show_sched","false"); // is it show schedule
    	model.addAttribute("is_add_task","false"); // is it add task
    	return "show_sched_form"; // return the show_sched_form.jsp
    }
    
    // if the requested URL is localhost:8080/sched_table, method is POST
    @RequestMapping(value = "sched_table", method = RequestMethod.POST)
	public String sched_table(Model model, @RequestParam(value="employee_id", required=false ) String employee_id, // 
    		@RequestParam(value="first_name", required=false ) String first_name, // 
    		@RequestParam(value="last_name", required=false) String last_name, @RequestParam(value="date", required=true) String enter_date) {
       	List<EmpNameInfo1p1> lst = new ArrayList<>(); // list of objects ( employee ID, first name, last name )
    	boolean name_entered = false; // did the user enter the employee ID, first name, last name ( if the admin is logged in )
    	
    	// setting the model attributes ( I can access them from .jsp )
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	empID = employee_id; // setting the variable empID to the value entered on the form
    	fName = first_name; // setting the variable fName to the value entered on the form
    	lName = last_name; // setting the variable lName to the value entered on the form
    
    	if ((empID==null) && (fName==null) && (lName==null)) { // if it is a regular user ( who is doing update and not show of the schedule, retrieve the name 
	    	// add to the SQL query the WHERE clause - where emp_id = loginID of the user logged in 
	    	empNameDAO.addToQueryStrName(loginID);
			// list is a list of objects of type EmpNameInfo ( employee ID, first name, last name ) where employee ID is the ID of logged in user
			lst = empNameDAO.getName(loginID); // getting the name of the employee with employee ID = loginID
    	} else { // the user is logged in as admin ( and he entered the name )
    		name_entered = true; 
    		//	determine the empID
    		if (empID.equals("")) {
    			// add to the SQL query the WHERE clause - where ( firstName = first name of the employee ) and ( lastName = last name of the employee )
    			empNameDAO.addToQueryStrID(fName, lName);
    			// list is a list of objects of type EmpNameInfo ( employee ID, first name, last name ) where (first_name = fName) and (last_name = lName) 
    			lst = empNameDAO.getEmployeeID(fName, lName); // getting the name of the employee with employee ID = loginID	
    		}
    	}
    	
    	if ((!(lst.isEmpty())) || (name_entered)) { // the employee with the first and last name is found 
    		if (!(lst.isEmpty())) { // retrieve the name
    			fName = lst.get(0).getFirstName(); // retrieving the first name of the logged in user
    			lName = lst.get(0).getLastName(); // retrieving the last name of the logged in user
    			if (name_entered) // if the user is logged as an admin and he entered the first and last name then read the employee ID
    				empID = lst.get(0).getEmployeeID(); // retrieving the employee ID
    			else
    				empID = loginID; // if the user is logged in as a regular user, he can see ONLY HIS OWN records
    		}
    		
    		// add the rest of the query to the query
			empSchedTaskDAO.addToQueryStr(empID, fName, lName, enter_date );
			// retrieve the schedule ( with tasks ) for the requested employee on the requested date
			List<EmpSchedTaskInfo1p1> list = empSchedTaskDAO.getSchedules();
			 
			// there are no tasks returned for the certain employee on the certain day
			if (list.isEmpty()) {
				model.addAttribute("page_title", "Schedule");
				model.addAttribute("message_shown", "The schedule for " + fName + " " + lName + " on " + enter_date + " doesn't exist!" );
				// the message should be in red ( adding it to the model )
				model.addAttribute("is_red", "true");
				// set here some model attributes if needed
				return "result";
			} else { // there are tasks returned for the certain employee on the certain day
				// add the schedule of the employee as the attribute to the model 
				model.addAttribute("empSchedTaskInfos", list);
				// add the first name as the attribute to the model
				model.addAttribute("enter_f_name", fName ); 
				// add the last name to the model
				model.addAttribute("enter_l_name", lName );
				// add the date ( of the schedule ) to the model
				model.addAttribute("enter_date", enter_date ); 
				// show_sched_results is shown for Show Schedule and Update Schedule
				return "show_sched_results"; // show the show_sched_results.jsp
			}
		} else { // in the database there is no first name and last name for the user logged in 
			// TEST THIS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        	model.addAttribute("page_title", "Schedule"); // adding the page_title variable to the model
        	model.addAttribute("message_shown", "For your login there is no first and second name!"); // adding the message to the model
        	model.addAttribute("is_red", "true"); // the message should be in red ( adding it to the model )
        	model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user is logged in
        	return "result";
		}  
	}
    
    
       
    // if the requested URL is localhost:8080/task_update and method is POST
    @RequestMapping(value = { "/task_update" }, method = RequestMethod.POST )
    public String task_update(Model model, @RequestParam(value="taskId", required=true) String id) {  
    	// find the task whose task_id is the argument id
    	EmpSchedTaskInfo1p1 task_info = empSchedTaskDAO.findTask(Long.valueOf(id));
    	// setting the model attributes ( I can access them from .jsp )
    	// add the object task_info as the attribute to the model
    	model.addAttribute("task_info", task_info);
    	model.addAttribute("is_add_task","false"); // it is not Add Task
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	return "show_task_info"; // show the show_task_info.jsp
    }
    
    // if the requested URL is localhost:8080/show_update_results and method is POST
    @RequestMapping(value = { "/show_update_results" }, method = RequestMethod.POST )
    public String show_update_results( Model model, @RequestParam(value="task_id", required=true) String id, @RequestParam(value="task_name", required=true ) String enter_tname, // 
        @RequestParam(value="task_date", required=true ) String enter_tdate, @RequestParam(value="start_time", required=true ) String enter_stime, //
        @RequestParam(value="end_time", required=true ) String enter_etime ) {
  
    	boolean update_succ = true;
    	try {
    		// update of the task in the DB
    		empSchedTaskDAO.updateTask(Long.valueOf(id), enter_tname, enter_tdate, enter_stime, enter_etime);
    	} catch (Exception e) {
    		// if an exception occurred during the update set the update_succ
    		update_succ = false;
    	}
    	// setting the model attributes ( I can access them from .jsp )
    	model.addAttribute("page_title", "Update Task"); // adding the page_title variable to the model
    	if (update_succ) {
    		model.addAttribute("message_shown", "The task was updated successfully !");
    		model.addAttribute("is_red", "false"); // adding to the model : the text needs to be shown in red
    	}
    	else {
    		model.addAttribute("message_shown", "The task wasn't updated successfully - an error occurred while updating the task !");
    		model.addAttribute("is_red", "true"); // adding to the model : the text needs to be shown in red
    	}
    	model.addAttribute("logged_in", "false"); // the user is now not logging in 
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_out", "false"); // the user is now not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	return "result";
    }
    
    
    @RequestMapping(value = { "/test1" }, method = RequestMethod.GET)
    public String test(Model model) {
    	return "test1";
    }
    
    @RequestMapping(value = { "/accounts1" }, method = RequestMethod.GET)
    public String accounts1(Model model) {
    	return "accounts1";
    }
    
    // if the requested URL is localhost:8080/task_list, method is GET do 
    @RequestMapping(value = { "task_list" }, method = RequestMethod.GET)
    public String task_list(Model model) {
    	
        String message = "Task List";
        model.addAttribute("message", message);
        return "task_list"; // return the task_list.jsp
    }
    
    // if the requested URL is localhost:8080/task_add, method is GET do 
    @RequestMapping(value = { "add_task" }, method = RequestMethod.GET)
    public String task_add(Model model) {
    	// setting the model attributes ( I can access them from .jsp )
    	model.addAttribute("logged_in", "false"); // the user is not logging in 
    	// model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	// show_sched_form is shown for Show Schedule and Update Schedule
    	// is_update has to be set whether it is update ( or show schedule )
    	model.addAttribute("is_update","false"); // is it update schedule
    	model.addAttribute("is_show_sched","false"); // is it show schedule
    	model.addAttribute("is_add_task","true"); // is it add task
    	return "show_sched_form"; // return the show_sched_form.jsp//String message = "Task Add";
        //return "task_add"; // return the task_add.jsp
    }
    
    // if the requested URL is localhost:8080/add_task_form, method is POST do
    // used in show_sched_fcont.jsp
    @RequestMapping(value = { "add_task_form"}, method = RequestMethod.POST )
    public String add_task_form(Model model, @RequestParam(value="employee_id", required=false ) String employee_id, // 
    		@RequestParam(value="first_name", required=false ) String fName, // 
    		@RequestParam(value="last_name", required=false) String lName) {
    	
    	List<EmpNameInfo1p1> lst = new ArrayList<>(); // list of objects ( employee ID, first name, last name )
    	boolean name_entered = false; // did the user enter the employee ID, first name, last name ( if the admin is logged in )
    	empID = employee_id; // setting the variable empID to the value entered on the form
    	
    	model.addAttribute("is_add_task","true"); // it is Add Task
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	
    	name_entered = true; // did the user enter the name in the form 
    	// determine the empID
    	if (empID.equals("")) {
    		// add to the SQL query the WHERE clause - where ( firstName = first name of the employee ) and ( lastName = last name of the employee )	
    		empNameDAO.addToQueryStrID(fName, lName);
    		// list is a list of objects of type EmpNameInfo ( employee ID, first name, last name ) where (first_name = fName) and (last_name = lName) 
    		lst = empNameDAO.getEmployeeID(fName, lName); // getting the name of the employee with employee ID	
    	}
    	
    	if ((!(lst.isEmpty())) || (name_entered)) { // the employee with the first and last name is found 
    		if (!(lst.isEmpty())) { // retrieve the name
    			if (name_entered) // if the user is logged as an admin and he entered the first and last name then read the employee ID
    				empID = lst.get(0).getEmployeeID(); // retrieving the employee ID
    			else
    				empID = loginID; // if the user is logged in as a regular user, he can see ONLY HIS OWN records
    		}
    	}	
    	return "show_task_info";
    }
    
    // if the requested URL is localhost:8080/add_task_fget, method is GET do
    // used for the regular user ( on the drop down menu : Task -> Add Task ) 
    @RequestMapping(value = { "add_task_fget"}, method = RequestMethod.GET )
    public String add_task_fget(Model model) {
    	
    	List<EmpNameInfo1p1> lst = new ArrayList<>(); // list of objects ( employee ID, first name, last name )
    	
    	model.addAttribute("is_add_task","true"); // it is Add Task
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	
    	if ((empID==null) && (fName==null) && (lName==null)) { // if it is a regular user ( who is doing update and not show of the schedule, retrieve the name 
	    	// add to the SQL query the WHERE clause - where emp_id = loginID of the user logged in 
	    	empNameDAO.addToQueryStrName(loginID);
			// list is a list of objects of type EmpNameInfo ( employee ID, first name, last name ) where employee ID is the ID of logged in user
			lst = empNameDAO.getName(loginID); // getting the name of the employee with employee ID = loginID
    	}
    	
    	if ((!(lst.isEmpty()))) { // the employee with the first and last name is found 
    		fName = lst.get(0).getFirstName(); // retrieving the first name of the logged in user
    		lName = lst.get(0).getLastName(); // retrieving the last name of the logged in user
    			empID = loginID; // if the user is logged in as a regular user, he can see ONLY HIS OWN records
    	}	
    	return "show_task_info";
    }
    
    
    // if the requested URL is localhost:8080/add_task_results and method is POST
    @RequestMapping(value = { "/add_task_results" }, method = RequestMethod.POST )
    public String add_task_results( Model model, @RequestParam(value="task_name", required=true ) String enter_tname, // 
        @RequestParam(value="task_date", required=true ) String enter_tdate, @RequestParam(value="start_time", required=true ) String enter_stime, //
        @RequestParam(value="end_time", required=true ) String enter_etime ) {
    	boolean add_succ = false; // success of the adding of the record to the database
    	String schedID = ""; // ID of the schedule ( from the table schedule ) for the employee with the ID empID
    	List<ScheduleInfo1p1> lst = new ArrayList<>(); // list of objects ( scheduleID, employeeID )
    	try {
    		// add to the SQL query the WHERE clause - where ( ( emp_id = empID )
    		schedDAO.addToQueryStr(empID);
    		// list is a list of objects of type ScheduleInfo ( sched_id ) where (( emp_id = empID) 
    		lst = schedDAO.getSchedID(); // getting the sched_id of the employee with employee ID
    		if (!(lst.isEmpty())) { // if the sched_id for the given emp_id is found
    			schedID = lst.get(0).getScheduleID(); // retrieving the schedule ID
    		}
    		
    		// adds to the SQL query the schedule ID, task name, task date, start time, end time depending on the data the user entered in the Add Task form
    		empSchedTaskDAO.addToQueryStr2(schedID, enter_tname, enter_tdate, enter_stime, enter_etime);
    		// adding the task to the DB
    		empSchedTaskDAO.addTask(schedID, enter_tname, enter_tdate, enter_stime, enter_etime);
    		add_succ = true;
    	} catch (Exception e) {
    		// if an exception occurred during adding the task set the add_succ
    		add_succ = false;
    	}
    		
    	// setting the model attributes ( I can access them from .jsp )
    	model.addAttribute("page_title", "Add Task"); // adding the page_title variable to the model
    	if (add_succ) {
    		model.addAttribute("message_shown", "The task was added successfully !");
    		model.addAttribute("is_red", "false"); // adding to the model : the text needs to be shown in red
    	}
    	else {
    		model.addAttribute("message_shown", "The task wasn't added successfully - an error occurred while adding the task !");
    		model.addAttribute("is_red", "true"); // adding to the model : the text needs to be shown in red
    	}
    	model.addAttribute("logged_in", "false"); // the user is now not logging in 
    	model.addAttribute("logged_out", "false"); // the user is now not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	return "result";
    }
    
    
    // if the requested URL is localhost:8080/task_delete, method is GET do 
    @RequestMapping(value = { "task_delete" }, method = RequestMethod.GET)
    public String task_delete(Model model) {
        String message = "Task Delete";
        model.addAttribute("message", message);
        return "task_delete"; // return the task_delete.jsp
    }
 
    // if the requested URL is localhost:8080/contact_us, method is GET do 
    @RequestMapping(value = { "contact_us" }, method = RequestMethod.GET)
    public String contact_us(Model model) {
        String message = "Contact Us";
        model.addAttribute("message", message);
        return "contact_us"; // return the contact_us.jsp
    }
    
    // if the requested URL is localhost:8080/log_in, method is GET do 
    @RequestMapping(value = { "log_in" }, method = RequestMethod.GET)
    public String log_in(Model model) {
        String message = "Log In";
        model.addAttribute("message", message);
        return "log_in"; // return the log_in.jsp
    }
    
    // if the requested URL is localhost:8080/log_out, method is GET do 
    @RequestMapping(value = { "log_out" }, method = RequestMethod.GET)
    public String log_out(Model model) {
    	// setting the attributes of the models ( I can access them from the .jsp )
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in
    	model.addAttribute("logged_out", "true"); // the user is logging out
    	model.addAttribute("already_login", "false"); // did the user log in before ( and is still logged in )
    	model.addAttribute("page_title", "Log Out"); // the title on the page
		model.addAttribute("message_shown", "You logged out successfully!");
		// the message shouldn't be in red ( adding the attribute to the model )
		model.addAttribute("is_red", "false");
		return "result"; // return the result.jsp
    }
}

