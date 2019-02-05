// project : Time Schedule, author : Ingrid Farkas, 2019
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.timemng.sbjsp.model.EmpSchedTaskInfo1p1; // EmpSchedTaskInfo - model class ( the class representing the data of the record in the task table )
import org.springframework.jdbc.core.RowMapper;

// EmpSchedTaskMapper - a mapper class (used for mapping corresponding to 1-1 between 1 column in the result of the query statement and 1 field in 
// the model class EmpSchedTaskInfo.java )
public class EmpSchedTaskMapper1p1 implements RowMapper<EmpSchedTaskInfo1p1> {

	// BASE_SQL is a SQL query to which later I added the where clause depending on the data the user entered on the List Schedule
		public static String BASE_SQL // 
		= "select e.emp_id, s.emp_id, ta.task_id, ta.task_name, ta.task_date, ta.start_time, ta.end_time from employee e, schedule s, task ta"  //
		+ " where (e.emp_id = s.emp_id ) and (s.sched_id = ta.sched_id) ";
		
		// the begining of the SQL query
		public static String TASK_SQL //
		= "select task_id, task_name, task_date, start_time, end_time from task";
		
		// resetBASE_SQL sets the string BASE_SQL to its original value
		public static void resetBASE_SQL() {
			BASE_SQL = "select e.emp_id, s.emp_id, ta.task_id, ta.task_name, ta.task_date, ta.start_time, ta.end_time from employee e, schedule s, task ta"  //
					+ " where (e.emp_id = s.emp_id ) and (s.sched_id = ta.sched_id) ";
		}
		
		// updating the query string to the new query string formed in the class EmpSchedTaskDAO, method addToQueryStr
		 public static void updateSQL(String sql) {
			 BASE_SQL = sql; // sql - new query string
		 }
	    
	    @Override
	    public EmpSchedTaskInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
	        
	        // mapping 1 column in the result of the query statement and 1 field in the model class EmpSchedTaskInfo.java 
	    	Long taskId = rs.getLong("task_id");
	        String taskName = rs.getString("task_name");
	        String taskDate = rs.getString("task_date");
	        String taskStartTime = rs.getString("start_time");
	        String taskEndTime = rs.getString("end_time");
	        
	        // create and return an object of the class EmpSchedTaskInfo ( which is the model )
	        return new EmpSchedTaskInfo1p1(taskId, taskName, taskDate, taskStartTime, taskEndTime);
	    }
	 

}
