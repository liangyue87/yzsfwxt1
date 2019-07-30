package com.ljx.hfgsjt.entity.dto.Activity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WF_RU_INST")
public class WfRuInstEntity {
  @Id
  @Column(name = "proc_inst_id")
  private String procInstId;
  @Column(name = "pdef_id")
  private String pdefId;
  private String parammap;
  private java.util.Date startdt;
  private String startuser;
  private Integer procstatus;
  private String stopuser;
  private java.util.Date stopdt;

  public Integer getProcstatus() {
    return procstatus;
  }

  public void setProcstatus(Integer procstatus) {
    this.procstatus = procstatus;
  }

  public String getStopuser() {
    return stopuser;
  }

  public void setStopuser(String stopuser) {
    this.stopuser = stopuser;
  }

  public java.util.Date getStopdt() {
    return stopdt;
  }

  public void setStopdt(java.util.Date stopdt) {
    this.stopdt = stopdt;
  }


  public String getProcInstId() {
    return procInstId;
  }

  public void setProcInstId(String procInstId) {
    this.procInstId = procInstId;
  }


  public String getPdefId() {
    return pdefId;
  }

  public void setPdefId(String pdefId) {
    this.pdefId = pdefId;
  }


  public String getParammap() {
    return parammap;
  }

  public void setParammap(String parammap) {
    this.parammap = parammap;
  }


  public java.util.Date getStartdt() {
    return startdt;
  }

  public void setStartdt(java.util.Date startdt) {
    this.startdt = startdt;
  }


  public String getStartuser() {
    return startuser;
  }

  public void setStartuser(String startuser) {
    this.startuser = startuser;
  }

  public WfRuInstEntity() {
  }

  public WfRuInstEntity(WfHiInstEntity instEntity) {
    this.procInstId = instEntity.getProcInstId();
    this.pdefId = instEntity.getPdefId();
    this.parammap = instEntity.getParammap();
    this.startdt = instEntity.getStartdt();
    this.startuser = instEntity.getStartuser();
    this.stopuser = instEntity.getStopuser();
    this.stopdt = instEntity.getStopdt();
  }




}
