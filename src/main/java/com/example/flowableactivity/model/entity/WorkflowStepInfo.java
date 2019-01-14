package com.example.flowableactivity.model.entity;

import java.util.Date;

public class WorkflowStepInfo {
	private int id;
	private String name;
	private int type;
	private String nextstepid;
	private int flowid;
	private int orders;
	private String stepdesc;
	private String auditid;
	private String auditname;
	private String conditionid;
	private Date createtime;
	private Date updatetime;
	//上一节点id
	private String perviousstepid;

	public String getConditionid() {
		return conditionid;
	}

	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}

	public String getPerviousstepid() {
		return perviousstepid;
	}

	public void setPerviousstepid(String perviousstepid) {
		this.perviousstepid = perviousstepid;
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
	public String getName() {
		return name; 
	}

	/**
	* 
	* @param val 
	*/
	public void setName(String val) {
		this.name = val; 
	}

	/**
	* （会签4，判定3，普通2，开始1，结束5）
	* @return 
	*/
	public int getType() {
		return type; 
	}

	/**
	* （会签4，判定3，普通2，开始1，结束5）
	* @param val 
	*/
	public void setType(int val) {
		this.type = val; 
	}

	/**
	* 
	* @return 
	*/
	public String getNextstepid() {
		return nextstepid; 
	}

	/**
	* 
	* @param val 
	*/
	public void setNextstepid(String val) {
		this.nextstepid = val; 
	}

	/**
	* 
	* @return 
	*/
	public int getFlowid() {
		return flowid; 
	}

	/**
	* 
	* @param val 
	*/
	public void setFlowid(int val) {
		this.flowid = val; 
	}

	/**
	* 
	* @return 
	*/
	public int getOrders() {
		return orders; 
	}

	public String getStepdesc() {
		return stepdesc;
	}

	public void setStepdesc(String stepdesc) {
		this.stepdesc = stepdesc;
	}

	/**
	* 
	* @param val 
	*/
	public void setOrders(int val) {
		this.orders = val; 
	}

	public String getAuditid() {
		return auditid;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	/**
	* 
	* @return 
	*/
	public String getAuditname() {
		return auditname; 
	}

	/**
	* 
	* @param val 
	*/
	public void setAuditname(String val) {
		this.auditname = val; 
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