//project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.dao;

//importing the packages
import java.util.List;

import javax.sql.DataSource;

import com.timemng.sbjsp.mapper.EmpNameMapper1p1;
import com.timemng.sbjsp.model.EmpNameInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmpNameDAO1p1 extends JdbcDaoSupport {

@Autowired
public EmpNameDAO1p1(DataSource dataSource) {
	this.setDataSource(dataSource);
}

// getName - retrieving name of the employee with the certain empID ( argument )
public List<EmpNameInfo1p1> getName( String empID ) {
 	List<EmpNameInfo1p1> list = null; // initialising the list
 	
 	// SQL_EMP_NAME - the String that contains the query onto which the WHERE clause will be added depending on the value of the empID 
    String sql = EmpNameMapper1p1.SQL_EMP_NAME;
    try {
     	Object[] params = new Object[] {};
     	// EmpNameMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( EmpNameInfo.java )
     	EmpNameMapper1p1 mapper = new EmpNameMapper1p1();
     	// running the query and retrieving the list of employee name with the specified employee ID
     	list = this.getJdbcTemplate().query(sql, params, mapper);
     } catch (Exception e) {
     	
     }
     
     return list; // return the name for the employee with the certain empID
}
 
// getEmployeeID - retrieving the employee ID for the employee with the certain name ( argument )
public List<EmpNameInfo1p1> getEmployeeID( String firstName, String lastName) {
	List<EmpNameInfo1p1> list = null; // initialising the list
	
	// SQL_EMP_ID - the String that contains the query onto which the WHERE clause will be added depending on the value of the firstName, lastName  
	String sql = EmpNameMapper1p1.SQL_EMP_ID;
	try {
	   	Object[] params = new Object[] {};
	   	// EmpNameMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( EmpNameInfo.java )
	   	EmpNameMapper1p1 mapper = new EmpNameMapper1p1();
	   	// running the query and retrieving the employee ID with the certain first name and last name
	   	list = this.getJdbcTemplate().query(sql, params, mapper);
	} catch (Exception e) {
	   	
   }
   return list; // return the employee ID for the employee with the certain first name and last name
}
 
// add to the SQL query the WHERE clause - where ( emp_id = id of the employee ) 
public void addToQueryStrName(String empID ) {

  	// if other user ( regular or admin ) logged in before then the original SQL_EMP_NAME got changed so I have to reset it to its original value 
  	EmpNameMapper1p1.resetSQL_EMP_NAME();
  	String sql = EmpNameMapper1p1.SQL_EMP_NAME;
  	
  	// I am changing the SQL query to return the employee's first and last name ( for the given employee's ID ) 
  	sql += " where emp_id='" + empID + "' ";
  
  	sql += ";";
  	
  	// PROBLEM : if id,name, date are all EMPTY - HANDLE THIS ????????????????????????????????????????????????????????
  	// update the SQL_EMP_NAME to the sql
  	EmpNameMapper1p1.updateSQL_NAME(sql);
}

//add to the SQL query the WHERE clause - where ( firstName = first name of the employee ) and ( lastName = last name of the employee )
public void addToQueryStrID(String firstName, String lastName ) {

	boolean firstNameEx; // does the first name exist
	boolean lastNameEx; // does the last name exist
	
	// if the user ( admin ) logged in and queried the database for some other employee ID the original SQL_EMP_ID got changed so I have to reset it to its original value 
	EmpNameMapper1p1.resetSQL_EMP_ID();
	String sql = EmpNameMapper1p1.SQL_EMP_ID;
	
	firstNameEx = ((firstName!=null) || (!firstName.equals(""))); // does the first name exist
	lastNameEx = ((lastName!=null) || (!lastName.equals(""))); // does the last name exist
	if (firstNameEx){
		// I am changing the SQL query to return the employee's ID for the entered first and last name  
		sql += " where (first_name='" + firstName + "') ";
		
		if (lastNameEx) 
			sql += "and ";
	} else if (lastNameEx) {
		sql += " where ";
	}
		
	if (lastNameEx){
		// I am changing the SQL query to return the employee's ID for the entered last name  
		sql += " (last_name='" + lastName + "') ";
	}
	sql += ";";
	
	// PROBLEM : if id,name, date are all EMPTY - HANDLE THIS ????????????????????????????????????????????????????????
	// update the SQL_EMP_NAME to the sql
	EmpNameMapper1p1.updateSQL_EMP_ID(sql);
}
 
}

