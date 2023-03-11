package com.mycode.blog.utils;

import java.util.Date;

public class Utility {

	public static boolean isFieldEmpty(String s) {
		return s==null || s.length()==0;
	}
	
	public static boolean isEmailValid(String email) {
		return  "^(.+)@(.+)$".matches(email);  
	}
	
	
	public static String getCurrentDateTime() {
		return new Date().toString();
	}
	
}
