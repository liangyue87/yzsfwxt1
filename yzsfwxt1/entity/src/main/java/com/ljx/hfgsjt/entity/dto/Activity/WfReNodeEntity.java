package com.ljx.hfgsjt.entity.dto.Activity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WF_RE_NODE")
public class WfReNodeEntity {

  private String activityid;
  @Id
  @Column(name = "pdef_id", length = 20)
  private String pdefId;
  private String nextnode;
  private String nodename;
  private String formkey;
  private String parammap;
  private String isstartnode;
  private String direction;
  private String needcheck;
  private String visible;


  public String getActivityid() {
    return activityid;
  }

  public void setActivityid(String activityid) {
    this.activityid = activityid;
  }


  public String getPdefId() {
    return pdefId;
  }

  public void setPdefId(String pdefId) {
    this.pdefId = pdefId;
  }


  public String getNextnode() {
    return nextnode;
  }

  public void setNextnode(String nextnode) {
    this.nextnode = nextnode;
  }


  public String getNodename() {
    return nodename;
  }

  public void setNodename(String nodename) {
    this.nodename = nodename;
  }


  public String getFormkey() {
    return formkey;
  }

  public void setFormkey(String formkey) {
    this.formkey = formkey;
  }


  public String getParammap() {
    return parammap;
  }

  public void setParammap(String parammap) {
    this.parammap = parammap;
  }


  public String getIsstartnode() {
    return isstartnode;
  }

  public void setIsstartnode(String isstartnode) {
    this.isstartnode = isstartnode;
  }


  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }


  public String getNeedcheck() {
    return needcheck;
  }

  public void setNeedcheck(String needcheck) {
    this.needcheck = needcheck;
  }


  public String getVisible() {
    return visible;
  }

  public void setVisible(String visible) {
    this.visible = visible;
  }

}
