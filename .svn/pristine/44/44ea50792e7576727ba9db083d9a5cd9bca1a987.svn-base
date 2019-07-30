package com.ljx.hfgsjt.entity.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XTGL_USERORG")
public class UserOrgEntity {
  @Id
  private String userid;
  private String orgid;


  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }


  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public UserOrgEntity(String userid, String orgid) {
    this.userid = userid;
    this.orgid = orgid;
  }

  public UserOrgEntity() {
  }
}
