package com.ljx.hfgsjt.entity.dto.Activity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WF_DF_ACTIVITY")
public class WfDfActivityEntity {

  @Id
  private String activityid;
  private String activityname;
  private String wfid;
  private String formid;
  private String bz;
  @Column(name = "check_minute")
  private String checkMinute;
  @Column(name = "finish_minute")
  private String finishMinute;
  private String delay;
  @Column(name = "yellow_finish")
  private String yellowFinish;
  @Column(name = "check_limited")
  private String checkLimited;
  @Column(name = "finish_limited")
  private String finishLimited;
  @Column(name = "default_czr")
  private String defaultCzr;
  private String bjjsgz;
  private String flag;
  private String type;


  public String getActivityid() {
    return activityid;
  }

  public void setActivityid(String activityid) {
    this.activityid = activityid;
  }


  public String getActivityname() {
    return activityname;
  }

  public void setActivityname(String activityname) {
    this.activityname = activityname;
  }


  public String getWfid() {
    return wfid;
  }

  public void setWfid(String wfid) {
    this.wfid = wfid;
  }


  public String getFormid() {
    return formid;
  }

  public void setFormid(String formid) {
    this.formid = formid;
  }


  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }


  public String getCheckMinute() {
    return checkMinute;
  }

  public void setCheckMinute(String checkMinute) {
    this.checkMinute = checkMinute;
  }


  public String getFinishMinute() {
    return finishMinute;
  }

  public void setFinishMinute(String finishMinute) {
    this.finishMinute = finishMinute;
  }


  public String getDelay() {
    return delay;
  }

  public void setDelay(String delay) {
    this.delay = delay;
  }


  public String getYellowFinish() {
    return yellowFinish;
  }

  public void setYellowFinish(String yellowFinish) {
    this.yellowFinish = yellowFinish;
  }


  public String getCheckLimited() {
    return checkLimited;
  }

  public void setCheckLimited(String checkLimited) {
    this.checkLimited = checkLimited;
  }


  public String getFinishLimited() {
    return finishLimited;
  }

  public void setFinishLimited(String finishLimited) {
    this.finishLimited = finishLimited;
  }


  public String getDefaultCzr() {
    return defaultCzr;
  }

  public void setDefaultCzr(String defaultCzr) {
    this.defaultCzr = defaultCzr;
  }


  public String getBjjsgz() {
    return bjjsgz;
  }

  public void setBjjsgz(String bjjsgz) {
    this.bjjsgz = bjjsgz;
  }


  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
