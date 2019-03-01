// project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.dao;

//importing the packages
import java.util.List;

import javax.sql.DataSource;

import com.timemng.sbjsp.mapper.EmpMapper1p1;
import com.timemng.sbjsp.mapper.EmpSchedTaskMapper1p1;
import com.timemng.sbjsp.mapper.LoginMapper1p1;
import com.timemng.sbjsp.model.EmpSchedTaskInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmpSchedTaskDAO1p1 extends JdbcDaoSupport {

	@Autowired
    public EmpSchedTaskDAO1p1(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    // getSchedules - retrieving the schedule for the employee on the requested date as a List of the elements of the type EmpSchedTaskInfo
    public List<EmpSchedTaskInfo1p1> getSchedules() {
    	List<EmpSchedTaskInfo1p1> list=null; // initialising the list
        
    	// BASE_SQL is the String that contains the query onto which one the WHERE clause will be added depending on the values the user entered 
    	// in the form show_sched_form.jsp 
        String sql = EmpSchedTaskMapper1p1.BASE_SQL;
        try {
        	Object[] params = new Object[] {};
        	// EmpSchedTaskMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( EmpSchedTaskInfo.java )
        	EmpSchedTaskMapper1p1 mapper = new EmpSchedTaskMapper1p1();
        	// running the query and retrieving the list of the tasks for the employee on the requested date
        	list = this.getJdbcTemplate().query(sql, params, mapper);
        } catch (Exception e) {
        	
        }
        
        return list; // return the list of the tasks for the employee on the requested date
    }
    
    // @@@@@@@@@@@@@@@@@@@@@@@@@ what if there is MORE THAN 1 TASK with the required INFO
    // findTask - finds the task with the required id
    public EmpSchedTaskInfo1p1 findTask(Long id) {
      
    	// TASK_SQL is select task_id, task_name, task_date, start_time, end_time from task
        String sql = EmpSchedTaskMapper1p1.TASK_SQL + " where task_id = ? ";
 
        Object[] params = new Object[] { id };
        // EmpSchedTaskMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( EmpSchedTaskInfo.java )
        EmpSchedTaskMapper1p1 mapper = new EmpSchedTaskMapper1p1();
        try {
        	// running the query and retrieving the task from the DB 
            EmpSchedTaskInfo1p1 empSchedTask = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return empSchedTask;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    // method updateTask updates the task in the database 
    public void updateTask(Long id, String taskName, String taskDate, String startTime, String endTime ) {
    	EmpSchedTaskInfo1p1 taskInfo = this.findTask(id);
    	
    	// removeEmpSpaces removes the empty spaces from the date of the task
    	String noEmpSpaces = removeEmpSpaces(taskDate, true);
    	// dateDB coverts the date from the format dd/mm/yyyy to the format yyyy-mm-dd
    	// sDBFormatDate is the date in the format yyyy-mm-dd
    	String sDBFormatDate = dateDB(noEmpSpaces);
    	
    	// the format of the startTime should be mm:hh:ss that is the reason I add 0 if the format is m:hh:ss 
    	if (startTime.charAt(1) == ':')
    		startTime = "0" + startTime;
    	
    	// the format of the startTime should be mm:hh:ss that is the reason I add 0 if the format is m:hh:ss 
    	if (endTime.charAt(1) == ':')
    		endTime = "0" + endTime;
    	
    	// Update to DB
        String sqlUpdate = "Update task set task_name = ?, task_date = ?, start_time = ?, end_time = ? where task_id = ?";
        this.getJdbcTemplate().update(sqlUpdate, taskName, sDBFormatDate, startTime, endTime, taskInfo.getTaskId());
    }
    
    // method addTask adds the task to the database ( to the table task )
    public int addTask( String employeeID, String taskName, String taskDate, String startTime, String endTime ) {
    	
    	// removeEmpSpaces removes the empty spaces from the date of the task
    	String noEmpSpaces = removeEmpSpaces(taskDate, true);
    	// dateDB coverts the date from the format dd/mm/yyyy to the format yyyy-mm-dd
    	// sDBFormatDate is the date in the format yyyy-mm-dd
    	String sDBFormatDate = dateDB(noEmpSpaces);
    	
    	// the format of the startTime should be mm:hh:ss that is the reason I add 0 if the format is m:hh:ss 
    	if (startTime.charAt(1) == ':')
    		startTime = "0" + startTime;
    	
    	// the format of the startTime should be mm:hh:ss that is the reason I add 0 if the format is m:hh:ss 
    	if (endTime.charAt(1) == ':')
    		endTime = "0" + endTime;
    	
    	int numRows=-1; // number of rows affected with the insert statement
	    
	 	// ADD_TASK_SQL is the String that contains the query onto which later I added the task name, task date, start time, end time depending on the data entered in the Add Task form
	 	// in the form addempl_fcont.jsp 
	    String sql = EmpSchedTaskMapper1p1.ADD_TASK_SQL;
	    try {
	     	Object[] params = new Object[] {};
	     	// EmployeeMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( EmployeeInfo.java )
	     	EmpSchedTaskMapper1p1 mapper = new EmpSchedTaskMapper1p1();
	     	// running the query of adding a new task ( schedule id, task name, task date, start time, end time )
	     	numRows = this.getJdbcTemplate().update(sql);
	     } catch (Exception e) {
	     	
	     }
	     
	  // returns the number of rows affected with the insert statement ( if an exception occured -1 is returned )
	  return numRows; 
    }
    
    // method deleteTask deletes the task from the table task
    public int deleteTask() {
    	int numRows=-1; // number of rows affected by the delete statement
    	// DEL_TASK_SQL is the String that contains the query for deletion
    	String sql = EmpSchedTaskMapper1p1.DEL_TASK_SQL;
    	try {
	     	Object[] params = new Object[] {};
	     	// EmployeeMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( EmpSchedTaskInfo.java )
	     	EmpSchedTaskMapper1p1 mapper = new EmpSchedTaskMapper1p1();
	     	// running the query of deleting a task named taskName
	     	numRows = this.getJdbcTemplate().update(sql);
	     } catch (Exception e) {
	     	
	     }
	     
	  // returns the number of rows affected with the delete statement ( if an exception occured -1 is returned )
	  return numRows; 
    }
    
    // method removeEmpSpaces removes the empty spaces in the string inString and returns the string without empty spaces
    // if single is true : removes the empty space in the string inString and returns the string without empty spaces
    // if single is false : substitutes 2 empty spaces ( with one empty space ) in the string inString 
    public String removeEmpSpaces(String inString, boolean single) {
    	
    	String newString; // the string with no empty spaces
    	
    	newString = inString; 
    	if (single == true) {
    		newString = newString.replace(" ", ""); // replacing the white space with no character   
    	} else
    		newString = newString.replace("  ", " "); // replacing 2 white spaces with one 
    	return newString;
    }
    
    // converts the date from the format dd/mm/yyyy to the format yyyy-mm-dd ( which is used by the SQL Server )
    public String dateDB(String enteredDate) {
    	String sYear; // the year
    	String sMonth; // the month
    	String sDay; // the day
    	String sDate; // the date in format yyyy-mm-dd
    	sYear = enteredDate.substring(6); // retrieving the year from the string
    	sMonth = enteredDate.substring(3, 5); // retrieving the month
    	sDay = enteredDate.substring(0, 2); // retrieving the day from  the string
    	sDate = sYear + "-" + sMonth + "-" + sDay;
    	return sDate; // return the date in the format used by the SQL server
    }
    
    // add to the SQL query the where part - where ( e.emp_id = entered value for emp_id ) and ( e.first_name = entered value for enter_f_name ) 
	// AND ( e.last_name = entered value for enter_l_name ) AND ( ta.task_date = entered value for the date )
    public void addToQueryStr(String empId, String fName, String lName, String date ) {
    	// resetBASE_SQL sets the string to its original value
    	// if the user ran the Show Schedule before then to the original BASE_SQL got changed so I have to reset it to its original value 
    	EmpSchedTaskMapper1p1.resetBASE_SQL();
    	String sql = EmpSchedTaskMapper1p1.BASE_SQL;
    	
    	// if the user entered an employee id in the Show Schedule form I am changing the SQL query to return the records where the employee id equals the entered value
    	if (!(empId.equals(null))) {
    		sql += "and ( e.emp_id='" + empId + "') ";
    	}
    	
    	// if the user entered a first name in the Show Schedule form I am changing the SQL query to return the records where the first name equals the entered value
    	if (!(fName.equals(null))) {
    		sql += "and ( e.first_name='" + fName + "') ";
    	}
    	
    	// if the user entered a first name in the Show Schedule form I am changing the SQL query to return the records where the first name equals the entered value
    	if (!(lName.equals(null))) {
    		sql += "and ( e.last_name='" + lName + "') ";
    	}
    	
    	// removeEmpSpaces removes the empty spaces from the date 
    	String noEmpSpaces = removeEmpSpaces(date, true);
    	// dateDB coverts the date from the format dd/mm/yyyy to the format yyyy-mm-dd
    	// sDBFormatDate is the date in the format yyyy-mm-dd
    	String sDBFormatDate = dateDB(noEmpSpaces);
    	
    	// if the user entered a date for the schedule in the Show Schedule form I am changing the SQL query to return the records where the date equals the entered value
    	if (!(sDBFormatDate.equals(null))) {
    		sql += "and ( ta.task_date='" + sDBFormatDate + "') ";
    	}
    	
    	sql += "order by ta.start_time ASC";
    	sql += ";";
    	
    	// PROBLEM : if id,name, date are all EMPTY - HANDLE THIS ????????????????????????????????????????????????????????
    	// updating the query string to the new query string formed above
    	EmpSchedTaskMapper1p1.updateSQL(sql);
    }
    
    
    public void addToQueryStr1(String userName, String userPassw ) {

    	boolean returnVal; // the value returned by the method
     	// resetSQL_LOGIN sets the string SQL_LOGIN to its original value
     	// if the user ran the logging in before then the original SQL_LOGIN got changed so I have to reset it to its original value 
     	LoginMapper1p1.resetSQL_LOGIN();
     	String sql = LoginMapper1p1.SQL_LOGIN;
     	
     	// if the user entered user name in the form I am changing the SQL query to return the records where the user name equals the entered value
     	if (!(userName.equals(null))) 
     		sql += "and ( user_name='" + userName + "') ";
     	
     	// if the user entered user name in the form I am changing the SQL query to return the records where the user name equals the entered value
     	if (!(userPassw.equals(null))) {
     		sql += "and ( userPassw ='" + userPassw + "') ";
     	}
     	  
     	sql += ";";
     	
     	// PROBLEM : if id,name, date are all EMPTY - HANDLE THIS ????????????????????????????????????????????????????????
        // updating the query string to the new query string formed above
     	LoginMapper1p1.updateSQL(sql);     	
    }
    
     // adds to the SQL query the schedule ID, task name, task date, start time, end time depending on the data the user entered in the Add Task form
 	 // if the user didn't enter the task name, task date, start time or end time( in the form ) then this method returns false
 	 public boolean addToQueryStr2( String schedID, String taskName, String taskDate, String startTime, String endTime ) {

 	 	boolean returnVal = false; // the value returned by the method
 	  	// if the user before added a new task then the original ADD_TASK_SQL got changed so I have to reset it to its original value 
 	  	EmpSchedTaskMapper1p1.resetADD_TASK_SQL(); 
 	  	
 	  	String sql = EmpSchedTaskMapper1p1.ADD_TASK_SQL;
 	  	
 	  	taskDate = dateDB(taskDate);
 	  	
 	  	if (!(schedID.equals(null))) {
 	  		sql += schedID + ","; // add the schedule ID to the list of values
	 	  	if (!(taskName.equals(null))) {
	 	  		sql += "'" + taskName + "',"; // add the task name to the list of values
	 	  		if (!(taskDate.equals(null))) {
	 	  			sql += "'" + taskDate + "',"; // add the task date to the list of values
	 	  			if (!(startTime.equals(null))) {
	 	  				sql += "'" + startTime + "',";  // add the start time to the list of values
	 	  				if (!(endTime.equals(null))) { 
	 	 	  				sql += "'" + endTime + "'"; // add the end time time to the list of values
	 	 	  				returnVal = true;
	 	  				}
	 	  			}
	 	  		}
	 	  	}
 	  	}
 	  	
 	  	sql += ");"; 
 	  	
 	  	
 	  	// PROBLEM : if id,name, date are all EMPTY - HANDLE THIS ????????????????????????????????????????????????????????
 	  	// updating the query string ( ADD_TASK_SQL ) to the new query string formed above
 	  	EmpSchedTaskMapper1p1.updateTaskSQL(sql); 
 	  	
 	  	return returnVal;
 	  }
 	 
 	  // adds to the sql query ( deletion ) the where clause
 	  // the sql query will delete the task named taskName ( on the date enter_tdate, which starts at enter_stime ) from the table task 
 	  // for the user with schedule ID schedID
 	  // if the user didn't enter the task name ( in the form ) then this method returns false
 	  public boolean addToQueryStrDel( String schedID, String taskName, String taskDate, String startTime ) {
 		  
 		 boolean returnVal = false; // the value returned by the method
 		 
 		 // if the user before deleted a task then the original DEL_TASK_SQL got changed so I have to reset it to its original value
 		 EmpSchedTaskMapper1p1.resetDEL_TASK_SQL();
 		 
 		taskName = removeEmpSpaces(taskName, false); // substitutes 2 empty spaces with one
 		String noEmpSpaces = removeEmpSpaces(taskDate, true); // removes the empty spaces from the date 
 		startTime = removeEmpSpaces(startTime, false); // substitutes 2 empty spaces with one
 		
 		startTime = startTime.toUpperCase(); // convert the string to upper case characters
 		int pos=-1;
 		boolean isAM = false; // is AM or am part of the start time 
 		boolean isPM = false; // is PM or pm part of the start time
 		String shortTime; // hh:mm part of the time
 		
 		pos = startTime.indexOf("AM");
 		if (pos >= 0) // if the startTime contains AM substring
 			isAM = true;
 		else {
 			pos = startTime.indexOf("PM"); // if the startTime contains PM substring
 			if (pos >= 0)
 				isPM = true;
 		}
 		
 		// if the startTime contains AM or PM
 		if (pos >= 0) {
 			shortTime = startTime.substring(0, pos-1); // hh:mm part of the time 
 			shortTime = removeEmpSpaces(shortTime, true); // removes empty spaces with none
	 		// add AM or PM to the time
	 		if (isAM)
	 			startTime = shortTime + " AM"; 
	 		else
	 			startTime = shortTime + " PM";
 		} 
 		
 		 String sql = EmpSchedTaskMapper1p1.DEL_TASK_SQL;
 		 if (!(taskName.equals(null))) {
 			 sql += "'" + taskName + "'"; // add the taskName to the where clause
 			 // returnVal = true;
 			sql += ")"; 
 			
 			if (!(taskDate.equals(null))) {
 
 		    	// dateDB coverts the date from the format dd/mm/yyyy to the format yyyy-mm-dd
 		    	// sDBFormatDate is the date in the format yyyy-mm-dd
 		    	String sDBFormatDate = dateDB(noEmpSpaces);
 	 	    	sql += " AND (task_date=";
 	 	    	sql += "'" + sDBFormatDate + "'"; // add the date of  the task to the where clause
 	 	    	sql += ")";
 	 	    	 	    	
 	 	    }
 	 		 
 			if (!(startTime.equals(null))) {
	 	 	    	sql += " AND (start_time=";
	 	 	    	sql += "'" + startTime + "'"; // add the date of  the task to the where clause
	 	 	    	sql += ")";
	 	
	 	 	}
 			
 	 	    if (!(schedID.equals(null))) {
 	 	    	sql += " AND (sched_id=";
 	 	    	sql += "'" + schedID + "'"; // add the schedule ID to the where clause
 	 	    	 sql += ")";
 	 	    	returnVal = true; 
 	 	    }
 		 } 
 	     
 		 // if the building of the SQL query was so far successful
 		 if (returnVal) {
 			 sql += ";"; 
 			 // updating the query string ( DEL_TASK_SQL ) to the new query string formed above
 			 EmpSchedTaskMapper1p1.updateDelTaskSQL(sql); 
 		 }
 		 
  	  	 return returnVal;
 	  }
}
