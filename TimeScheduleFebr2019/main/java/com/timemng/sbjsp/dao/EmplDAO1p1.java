//project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.dao;

//importing the packages
import java.util.List;

import javax.sql.DataSource;

import com.timemng.sbjsp.mapper.EmpMapper1p1;
import com.timemng.sbjsp.model.LoginInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
	
@Repository
@Transactional
public class EmplDAO1p1 extends JdbcDaoSupport {

	@Autowired
	 public EmplDAO1p1(DataSource dataSource) {
	     this.setDataSource(dataSource);
	 }

	 // addEmployee - adding the new employee ( with the first name fname, last name lname working in the department ) to the database 
	 public int addEmployee(String fName, String lName, String department) {
	 	//List<EmployeeInfo> list = null; // initialising the list
	    int numRows=-1; // number of rows affected with the insert statement
	    
	 	// ADD_EMP_SQL is the String that contains the query onto which later I will add the first name, last name, department depending on the data the user entered in the Add Employee form
	 	// in the form addempl_fcont.jsp 
	     String sql = EmpMapper1p1.ADD_EMP_SQL;
	     try {
	     	Object[] params = new Object[] {};
	     	// EmployeeMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( EmployeeInfo.java )
	     	EmpMapper1p1 mapper = new EmpMapper1p1();
	     	// running the query of adding a new user with the fName ( first name ), lName 9 last name ), department
	     	// numRows = this.getJdbcTemplate().update(sql, params, mapper); // @@@@@@@@@@@@@@@ do I need here only one argument sql
	     	numRows = this.getJdbcTemplate().update(sql);
	     } catch (Exception e) {
	     	
	     }
	     
	  // returns the number of rows affected with the insert statement ( if an exception occured -1 is returned  
	  return numRows; 
	 }
	 
	 // adds to the SQL query the first name, last name, department depending on the data the user entered in the Add Employee form
	 // if the user didn't enter first name or last name ( in the form ) then this method returns false
	 public boolean addToQueryStr(String fName, String lName, String department ) {

	 	boolean returnVal; // the value returned by the method
	  	// if the user before added a new employee then the original ADD_EMP_SQL got changed so I have to reset it to its original value 
	  	EmpMapper1p1.resetADD_EMP_SQL(); 
	  	
	  	String sql = EmpMapper1p1.ADD_EMP_SQL;
	  	
	    if ((!(fName.equals(null))) && (!(lName.equals(null)))) {
	        sql += "'" + fName + "',";
	  		sql += "'" + lName + "'";
	  		if (!(department.equals(null))){
	  		    sql += ",'" + department + "'";
	  		}
	  		sql += ");"; 
	  	
	  	
	  	// if  ((!(fName.equals(null))) && (!(lName.equals(null)))) {
	  		// sql += "first_name='" + fName + "' AND ";
	  		// sql += "last_name='" + lName + "' ";
	  		// if (!(department.equals(null))){
	  			// sql += "AND department='" + department + "' ";
	  		// }
	  		// sql += ") ";
	  		// sql += "INSERT INTO employee ( first_name, last_name ";
	  		// if (!(department.equals(null))){
	  			// sql += ", department ";
	  		// }
	  		// sql += ") VALUES (";
	  		// sql += "'" + fName + "'," + "'" + lName + "'";
	  		// if (!(department.equals(null))){
	  			// sql += ",'" + department + "'";
	  		// }
	  		// sql += ");";
	  		returnVal = true;
	  	} else {
	  		returnVal = false;
	  	}
	  	
	  	// if the user entered user name in the form I am changing the SQL query to return the records where the user name equals the entered value
	  	//if (!(userName.equals(null))) 
	  		//sql += "where ( user_name='" + userName + "') ";
	  	//else
	  		//returnVal = false;
	  	
	  	// if the user entered user name in the form I am changing the SQL query to return the records where the user name equals the entered value
	  	//if (!(userPassw.equals(null))) {
	  		//sql += "and ( password ='" + userPassw + "') ";
	  		//returnVal = true;
	  	//}
	  	//else
	  		//returnVal = false;

	  	//sql += ";";
	  	
	  	
	  	// PROBLEM : if id,name, date are all EMPTY - HANDLE THIS ????????????????????????????????????????????????????????
	  	// update the SQL_LOGIN to the sql
	  	EmpMapper1p1.updateSQL(sql);
	  	
	  	return returnVal;
	  }
	 
}
