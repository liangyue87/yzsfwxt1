package com.ljx.hfgsjt.entity.dto.Activity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "WF_HI_INST")
public class WfHiInstEntity {
  @Id
  private String procInstId;
  private String pdefId;
  private String parammap;
  private Date startdt;
  private String startuser;
  private Date endtime;
  private String procstatus;
  private String stopuser;
  private Date stopdt;


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


  public Date getStartdt() {
    return startdt;
  }

  public void setStartdt(Date startdt) {
    this.startdt = startdt;
  }


  public String getStartuser() {
    return startuser;
  }

  public void setStartuser(String startuser) {
    this.startuser = startuser;
  }


  public Date getEndtime() {
    return endtime;
  }

  public void setEndtime(Date endtime) {
    this.endtime = endtime;
  }


  public String getProcstatus() {
    return procstatus;
  }

  public void setProcstatus(String procstatus) {
    this.procstatus = procstatus;
  }


  public String getStopuser() {
    return stopuser;
  }

  public void setStopuser(String stopuser) {
    this.stopuser = stopuser;
  }


  public Date getStopdt() {
    return stopdt;
  }

  public void setStopdt(Date stopdt) {
    this.stopdt = stopdt;
  }

  public WfHiInstEntity() {
  }

  public WfHiInstEntity(WfRuInstEntity instEntity) {
    this.procInstId = instEntity.getProcInstId();
    this.pdefId = instEntity.getPdefId();
    this.parammap = instEntity.getParammap();
    this.startdt = instEntity.getStartdt();
    this.startuser = instEntity.getStartuser();
    this.stopuser = instEntity.getStopuser();
    this.stopdt = instEntity.getStopdt();
    this.procstatus = String.valueOf(instEntity.getProcstatus());
  }
}
