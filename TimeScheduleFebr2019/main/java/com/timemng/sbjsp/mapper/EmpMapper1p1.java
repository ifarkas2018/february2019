// project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.timemng.sbjsp.model.EmpInfo1p1; // EmployeeInfo1p1 - model class ( the class representing the data of the record in the employee table )

// EmployeeMapper - a mapper class (used for mapping corresponding to 1-1 between 1 column in the result of the query statement and 1 field in 
// the model class EmployeeInfo.java )
public class EmpMapper1p1 implements RowMapper<EmpInfo1p1> {
			
		// ADD_EMP_SQL is a SQL query to which later I will add the first name, last name, department depending on the data the user entered in the Add Employee form
		public static String ADD_EMP_SQL //
			= "INSERT INTO employee(first_name, last_name, department) VALUES(";
		
		// resetADD_EMP_SQL sets the string ADD_EMP_SQL to its original value
		public static void resetADD_EMP_SQL() {
			ADD_EMP_SQL = "INSERT INTO employee(first_name, last_name, department) VALUES(";
		}
			
		// updating the query string to the new query string formed in the class EmplDAO, method addToQueryStr
		public static void updateSQL(String sql) {
			ADD_EMP_SQL = sql; // sql - new query string
		}
		    
		@Override
		public EmpInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		        
			// mapping 1 column in the result of the query statement and 1 field in the model class EmployeeInfo.java 
		    String firstName = rs.getString("first_name");
		    String lastName = rs.getString("last_name");
		    String department = rs.getString("department");
		        
		    // create and return an object of the class EmployeeInfo ( which is the model )
		    return new EmpInfo1p1(firstName, lastName, department);
		}
}
