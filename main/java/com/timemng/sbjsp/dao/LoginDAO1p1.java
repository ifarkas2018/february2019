// project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.dao;

//importing the packages
import java.util.List;

import javax.sql.DataSource;

//import com.timemng.sbjsp.mapper.EmpSchedTaskMapper;
//import org.o7planning.sbjdbc.exception.BankTransactionException;
import com.timemng.sbjsp.mapper.LoginMapper1p1;
import com.timemng.sbjsp.model.LoginInfo1p1;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LoginDAO1p1 extends JdbcDaoSupport {

	@Autowired
    public LoginDAO1p1(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    // getLogin - retrieving the list of user name and password
    public List<LoginInfo1p1> getLogin() {
    	List<LoginInfo1p1> list = null; // initialising the list
        
    	// SQL_LOGIN is the String that contains the query onto which one the WHERE clause will be added depending on the values the user entered 
    	// in the form login_fcont.jsp 
        String sql = LoginMapper1p1.SQL_LOGIN;
        try {
        	Object[] params = new Object[] {};
        	// LoginMapper is a mapping class that maps 1 column in the query statement to 1 field in the model class ( LoginInfo.java )
        	LoginMapper1p1 mapper = new LoginMapper1p1();
        	// running the query and retrieving the list of user names and passwords that match the entered user name and password
        	list = this.getJdbcTemplate().query(sql, params, mapper);
        	
        } catch (Exception e) {
        	
        }
        
        return list; // return the list of the tasks for the employee on the requested date
    }
    
    // add to the SQL query the WHERE clause - where ( user_name = entered value for user name ) and ( password = entered value for password )
    // if the user didn't enter user name or password ( in the form ) then this method returns false
    public boolean addToQueryStr(String userName, String userPassw ) {

    	boolean returnVal; // the value returned by the method
     	// if the user ran the logging in before then the original SQL_LOGIN got changed so I have to reset it to its original value 
     	LoginMapper1p1.resetSQL_LOGIN();
     	String sql = LoginMapper1p1.SQL_LOGIN;
     	
     	// if the user entered user name in the form I am changing the SQL query to return the records where the user name equals the entered value
     	if (!(userName.equals(null))) 
     		sql += "where ( user_name='" + userName + "') ";
     	else
     		returnVal = false;
     	
     	// if the user entered user name in the form I am changing the SQL query to return the records where the user name equals the entered value
     	if (!(userPassw.equals(null))) {
     		sql += "and ( password ='" + userPassw + "') ";
     		returnVal = true;
     	}
     	else
     		returnVal = false;
  
     	sql += ";";
     	
     	
     	// PROBLEM : if id,name, date are all EMPTY - HANDLE THIS ????????????????????????????????????????????????????????
     	// update the SQL_LOGIN ( LoginMapper1p1.java ) to the sql
     	LoginMapper1p1.updateSQL(sql);
     	
     	return returnVal;
     }
    
}
