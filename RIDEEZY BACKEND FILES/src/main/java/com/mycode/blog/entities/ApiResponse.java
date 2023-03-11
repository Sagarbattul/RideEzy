package com.mycode.blog.entities;

import java.util.List;

public class ApiResponse<T> {
	private List<T> dataList;
	public ApiResponse(List<T> dataList, String message, boolean status, int statusCode) {
		super();
		this.dataList = dataList;
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	private T data;
public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
private String message;
private boolean status;
private int statusCode;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public int getStatusCode() {
	return statusCode;
}
public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
}
public ApiResponse(String message, boolean status, int statusCode) {
	super();
	this.message = message;
	this.status = status;
	this.statusCode = statusCode;
}
public ApiResponse(T data, String message, boolean status, int statusCode) {
	super();
	this.data = data;
	this.message = message;
	this.status = status;
	this.statusCode = statusCode;
}


}
