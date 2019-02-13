//project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//EmpNameInfo - model class ( the class representing the employee id, first and last name in the employee table )
import com.timemng.sbjsp.model.EmpNameInfo1p1; 
import org.springframework.jdbc.core.RowMapper;

// EmpNameMapper - a mapper class (used for mapping corresponding to 1-1 between relationship between 1 column in the result of the query statement and 1 field in 
// the model class EmpNameInfo.java )
public class EmpNameMapper1p1 implements RowMapper<EmpNameInfo1p1> {

		// SQL_EMP_NAME is a SQL query used to select the first and last name of the employee with employeeID
		public static String SQL_EMP_NAME // 
		= "select emp_id, first_name, last_name from employee"; 
		
		// SQL_EMP_ID is a SQL query used to select the employee ID of the employee with the given first and last name
		public static String SQL_EMP_ID // 
		= "select emp_id, first_name, last_name from employee";  

		@Override
		public EmpNameInfo1p1 mapRow(ResultSet rs, int rowNum) throws SQLException {
			// mapping 1 column in the result of the query statement and 1 field in the model class EmpNameInfo.java 
			String emp_id = rs.getString("emp_id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
				        
			// create and return an object of the class LoginInfo ( which is the model )
			return new EmpNameInfo1p1( emp_id, first_name, last_name );
		}
		
		// resetSQL_EMP_NAME sets the string SQL_EMP_NAME to its original value
		public static void resetSQL_EMP_NAME() {
			SQL_EMP_NAME = "select emp_id, first_name, last_name from employee";
		}
		
		// resetSQL_EMP_ID sets the string SQL_EMP_ID to its original value
		public static void resetSQL_EMP_ID() {
			SQL_EMP_ID = "select emp_id, first_name, last_name from employee";
		}
			
		// updating the query string to the new query string formed in the class EmpNameDAO, method addToQueryStr
		public static void updateSQL_NAME(String sql) {
			SQL_EMP_NAME = sql; // sql - new query string
		}
		
		// updating the query string to the new query string formed in the class EmpNameDAO, method addToQueryStr
		public static void updateSQL_ID(String sql) {
			SQL_EMP_NAME = sql; // sql - new query string
		}
}

