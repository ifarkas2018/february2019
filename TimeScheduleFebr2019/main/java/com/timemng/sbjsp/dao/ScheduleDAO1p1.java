// project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.dao;

//importing the packages
import java.util.List;

import javax.sql.DataSource;

import com.timemng.sbjsp.mapper.ScheduleMapper1p1;
import com.timemng.sbjsp.model.ScheduleInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ScheduleDAO1p1 extends JdbcDaoSupport {

	@Autowired
    public ScheduleDAO1p1(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    // getSchedID - retrieving the sched_id from the table schedule 
    public List<ScheduleInfo1p1> getSchedID() {
    	List<ScheduleInfo1p1> list = null; // initialising the list
        
    	// SCHED_ID_SQL is the String that contains the query onto which the WHERE clause will be added depending on the values the user entered 
    	// used in the MainController.java ( /add_task_results, method SCHED_ID_SQL ) 
        String sql = ScheduleMapper1p1.SCHED_ID_SQL;
        try {
        	Object[] params = new Object[] {};
        	// ScheduleMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( ScheduleInfo.java )
        	ScheduleMapper1p1 mapper = new ScheduleMapper1p1();
        	// running the query and retrieving the list of sched_id from the table schedule for the given emp_id
        	list = this.getJdbcTemplate().query(sql, params, mapper);
        	
        } catch (Exception e) {
        	
        }
        return list; // return the schedule ID for the certain employee
    }
    
    // add to the SQL query the WHERE clause - where ( emp_id = entered value for employeeID ) 
    public void addToQueryStr(String employeeID) {

     	// if the user ran the Add Task before then the original SCHED_ID_SQL got changed so I have to reset it to its original value 
     	ScheduleMapper1p1.resetSCHED_ID_SQL();
     	String sql = ScheduleMapper1p1.SCHED_ID_SQL;
     	
     	// I am changing the SQL query to return the records where the emp_id equals the employeeID of the user whose task is being added
     	if (!(employeeID.equals(null))) 
     		sql += "where ( emp_id='" + employeeID + "')";
  
     	sql += ";";
     	
     	
     	// PROBLEM : if id,name, date are all EMPTY - HANDLE THIS ????????????????????????????????????????????????????????
     	// update the SCHED_ID_SQL to the sql
     	ScheduleMapper1p1.updateSQL(sql);
     	
     }
    
}

