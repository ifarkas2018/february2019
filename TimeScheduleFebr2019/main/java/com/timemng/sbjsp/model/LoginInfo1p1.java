// project : Time Schedule, author : Ingrid Farkas, 2019 
package com.timemng.sbjsp.model;

//LoginInfo1p1 - model class ( the class representing the data of the record in the Login table )
public class LoginInfo1p1 {

	private String userName; // the user_name
    private String userPassw; // the user's password
   	
	// constructor of the class
	public LoginInfo1p1(String userName, String password) {
		super();
		this.userName = userName;
		this.userPassw = password;
	}

	// get the user name
	public String getUserName() {
		return userName;
	}
	
	// set the user name
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	// get the password
	public String getUserPassw() {
		return userPassw;
	}
	
	// set the password
	public void setUserPassw(String userPassw) {
		this.userPassw = userPassw;
	}
}
