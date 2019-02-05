// project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.controller;
//importing the packages
import java.util.ArrayList;
import java.util.List;

import com.timemng.sbjsp.dao.EmpSchedTaskDAO1p1;
import com.timemng.sbjsp.model.EmpSchedTaskInfo1p1;
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

	// if the requested URL is localhost:8080, method is GET do 
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String index1(Model model) {
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in 
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "false"); // did the user log in before ( and is still logged in )
        return "index1"; // return the index1.jsp
        // return "logform";
    }
    
    
    // if the requested URL is localhost:8080/home, method is GET do
    // used if the user before logged in 
    @RequestMapping(value = { "home" }, method = RequestMethod.GET)
    public String indhome( Model model ) {
    	//model.addAttribute("is_admin", "false"); // @@@@@@@@@@@@@@@@@@@@@ the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
        return "index1"; // return the index1.jsp
    }
    
 
    // if the requested URL is localhost:8080/home, method is GET do
    // used if the user before logged in 
    @RequestMapping(value = { "home" }, method = RequestMethod.POST)
    public String indhome_post( Model model ) { 
    	//model.addAttribute("is_admin", "false"); // @@@@@@@@@@@@@@@@@@the user is not logged in as admin
    	model.addAttribute("logged_in", "false"); // the user is not logged in
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false - the user is not logging out
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
        return "index1"; // return the index1.jsp
    }
        
    @Autowired
    private EmpSchedTaskDAO1p1 empSchedTaskDAO;
    @Autowired
    private LoginDAO1p1 empLoginDAO;
    @Autowired
    private EmplDAO1p1 empDAO;
       
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
    		
			} else if (empLoginDAO.addToQueryStr( user_name, user_passw )) {
					// add the rest of the query to the query
		    		// addToQueryStr returns FALSE - if the user didn't enter user name or password ( in the form ) 
					// list is a list of objects of type LoginInfo ( user name, password ) that match the entered values of user name and password
					List<LoginInfo1p1> list = empLoginDAO.getLogin();
					// is in the database a user name and the password which was entered in the form
					if (!(list.isEmpty())) {
						model.addAttribute("logged_in", "true"); // setting the attribute logged_in to true - the user did log in 
						model.addAttribute("is_admin", "false"); // the user is not logged in as admin
						return "index1"; // show the index1.jsp
					} else {
						// add this to a METHOD ???????????????????????????????????
						model.addAttribute("page_title", "Log In");
						// setting the attribute logged_in to false - the user didn't log in
						model.addAttribute("logged_in", "false");
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
    
    // if the requested URL is localhost:8080/sched_sched_form, method is GET 
    @RequestMapping(value = { "show_sched_form" }, method = RequestMethod.GET)
    public String sched_form(Model model) {
    	// setting the model attributes ( I can access them from .jsp )
    	model.addAttribute("logged_in", "false"); // the user is not logging in 
    	model.addAttribute("is_admin", "false"); // the user is not logged in as admin
    	model.addAttribute("logged_out", "false"); // setting the attribute logged_out to false 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	return "show_sched_form"; // return the show_sched_form.jsp
    }
    
    
    
    // if the requested URL is localhost:8080/show_sched_results, method is POST
    @RequestMapping(value = "show_sched_results", method = RequestMethod.POST)
    // employee_id is an input element in show_sched_fcont.jsp. The user entered the id, name of the employee whose schedule he wants to see
    // ( and the date of the schedule )
	public String show_sched_results(Model model, @RequestParam(value="employee_id", required=true ) String enter_emp_id, // 
		@RequestParam(value="first_name", required=true) String enter_f_name, @RequestParam(value="last_name", required=true) String enter_l_name, //
		@RequestParam(value="date", required=true) String enter_date) {
    	// setting the model attributes ( I can access them from .jsp )
    	model.addAttribute("logged_in", "false"); // now is the user not logging in 
    	model.addAttribute("logged_out", "false"); // now is the user not logging out 
    	model.addAttribute("already_login", "true"); // did the user log in before ( and is still logged in )
    	 
    	// add the rest of the query to the query
		empSchedTaskDAO.addToQueryStr(enter_emp_id, enter_f_name, enter_l_name, enter_date );
		// retrieve the schedule ( with tasks ) for the requested employee on the requested date
		List<EmpSchedTaskInfo1p1> list = empSchedTaskDAO.getSchedules();
		 
		// add the schedule of the employee as the attribute to the model 
		model.addAttribute("empSchedTaskInfos", list);
		// add the first name as the attribute to the model
		model.addAttribute("enter_f_name", enter_f_name ); 
		// add the last name to the model
		model.addAttribute("enter_l_name", enter_l_name );
		// add the date ( of the schedule ) to the model
		model.addAttribute("enter_date", enter_date ); 
		
		if (list.isEmpty()) {
			
			// set here some model attributes if needed
			return "result";
		} else {
			// put the 4 model.addAttributes here
			return "show_sched_results"; // show the show_sched_results.jsp
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
    	// model.addAttribute("is_login_form", "false"); // the login form doesnt'need to be shown
    	// update_succ : is the update to the DB successful
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
    @RequestMapping(value = { "task_add" }, method = RequestMethod.GET)
    public String task_add(Model model) {
        String message = "Task Add";
        return "task_add"; // return the task_add.jsp
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

