package com.example.flowableactivity.model.entity;

import java.util.Date;

public class WorkflowConditionInfo {
	private int id;
	private String conditinokey;
	private String conditionvalue;
	private int type;
	private Date createtime;
	private Date updatetime;
	private String conditionexpression;

	public String getConditionexpression() {
		return conditionexpression;
	}

	public void setConditionexpression(String conditionexpression) {
		this.conditionexpression = conditionexpression;
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
	public String getConditinokey() {
		return conditinokey; 
	}

	/**
	* 
	* @param val 
	*/
	public void setConditinokey(String val) {
		this.conditinokey = val; 
	}

	/**
	* 
	* @return 
	*/
	public String getConditionvalue() {
		return conditionvalue; 
	}

	/**
	* 
	* @param val 
	*/
	public void setConditionvalue(String val) {
		this.conditionvalue = val; 
	}

	/**
	* 
	* @return 
	*/
	public int getType() {
		return type; 
	}

	/**
	* 
	* @param val 
	*/
	public void setType(int val) {
		this.type = val; 
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