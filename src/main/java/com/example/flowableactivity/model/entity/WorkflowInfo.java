package com.example.flowableactivity.model.entity;

import java.util.Date;

public class WorkflowInfo {
	private int id;
	private String flowname;
	private String flowdesc;
	private int flowstatus;
	private Date createtime;
	private Date updatetime;
	private int organizationalid;
	private String activitykey;

	public String getActivitykey() {
		return activitykey;
	}

	public void setActivitykey(String activitykey) {
		this.activitykey = activitykey;
	}

	public int getOrganizationalid() {
		return organizationalid;
	}

	public void setOrganizationalid(int organizationalid) {
		this.organizationalid = organizationalid;
	}

	/**
	* 
	* @return 
	*/
	public int getId() {
		return id; 
	}

	/**
	* 
	* @param val 
	*/
	public void setId(int val) {
		this.id = val; 
	}

	/**
	* 
	* @return 
	*/
	public String getFlowname() {
		return flowname; 
	}

	/**
	* 
	* @param val 
	*/
	public void setFlowname(String val) {
		this.flowname = val; 
	}

	/**
	* 
	* @return 
	*/
	public String getFlowdesc() {
		return flowdesc; 
	}

	/**
	* 
	* @param val 
	*/
	public void setFlowdesc(String val) {
		this.flowdesc = val; 
	}

	/**
	* 
	* @return 
	*/
	public int getFlowstatus() {
		return flowstatus; 
	}

	/**
	* 
	* @param val 
	*/
	public void setFlowstatus(int val) {
		this.flowstatus = val; 
	}

	/**
	* 
	* @return 
	*/
	public Date getCreatetime() {
		return createtime; 
	}

	/**
	* 
	* @param val 
	*/
	public void setCreatetime(Date val) {
		this.createtime = val; 
	}

	/**
	* 
	* @return 
	*/
	public Date getUpdatetime() {
		return updatetime; 
	}

	/**
	* 
	* @param val 
	*/
	public void setUpdatetime(Date val) {
		this.updatetime = val; 
	}
}