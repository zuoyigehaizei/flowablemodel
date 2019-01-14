package com.example.flowableactivity.model;

import java.util.Date;

public class WorkflowDTO {

    private int id;

    //工作流名称
    private String flowname;

    //工作流描述
    private String flowdesc;

    //状态（0未部署，1已部署）
    private Integer status;

    //创建时间
    private Date createtime;

    //机构id
    private int organizationalid;

    //processkey
    private String activitykey;

    //版本号
    private String version;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getFlowdesc() {
        return flowdesc;
    }

    public void setFlowdesc(String flowdesc) {
        this.flowdesc = flowdesc;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getOrganizationalid() {
        return organizationalid;
    }

    public void setOrganizationalid(int organizationalid) {
        this.organizationalid = organizationalid;
    }

    public String getActivitykey() {
        return activitykey;
    }

    public void setActivitykey(String activitykey) {
        this.activitykey = activitykey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
