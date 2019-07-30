package com.ljx.hfgsjt.entity.dto.Activity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WF_TASK")
public class WfTaskEntity {
  @Id
  private String taskid;
  private String ptaskid;
  @Column(name = "proc_inst_id")
  private String procInstId;
  @Column(name = "c_activityid")
  private String cActivityid;
  private String iscurrent;
  private String czr;
  private java.util.Date qssj;
  @Transient
  private String greenLight;

  private java.util.Date jssj;
  private String czsm;
  private String ischeck;
  @Column(name = "check_time")
  private java.util.Date check_time;
  @Column(name = "check_memo")
  private String checkMemo;
  private java.util.Date jssx;
  @Transient
  private String yellowLight;
  @Column(name = "jssj_memo")
  private String jssjMemo;
  @Column(name = "check_user")
  private String checkUser;
  @Column(name = "js_user")
  private String jsUser;
  @Column(name = "check_sx")
  private java.util.Date check_sx;
  private String yssm;
  private String hasnew;
  private String slmx;
  @Column(name = "jssx_yl")
  private java.util.Date jssx_yl;
  @Transient
  private String redLight;
  private String refkey;
  private String refid;
  @Transient
  private String finish_limited;

  @Transient
  private String thyy;
  private String htyy;
  private String rwblr;

  public String getFinish_limited() {
    return finish_limited;
  }

  public void setFinish_limited(String finish_limited) {
    this.finish_limited = finish_limited;
  }

  public String getRedLight() {
    return redLight;
  }

  public void setRedLight(String redLight) {
    this.redLight = redLight;
  }

  public String getGreenLight() {
    return greenLight;
  }

  public void setGreenLight(String greenLight) {
    this.greenLight = greenLight;
  }

  public String getYellowLight() {
    return yellowLight;
  }

  public void setYellowLight(String yellowLight) {
    this.yellowLight = yellowLight;
  }

  public String getTaskid() {
    return taskid;
  }

  public void setTaskid(String taskid) {
    this.taskid = taskid;
  }

  public String getPtaskid() {
    return ptaskid;
  }

  public void setPtaskid(String ptaskid) {
    this.ptaskid = ptaskid;
  }

  public String getProcInstId() {
    return procInstId;
  }

  public void setProcInstId(String procInstId) {
    this.procInstId = procInstId;
  }

  public String getCActivityid() {
    return cActivityid;
  }

  public void setCActivityid(String cActivityid) {
    this.cActivityid = cActivityid;
  }

  public String getIscurrent() {
    return iscurrent;
  }

  public void setIscurrent(String iscurrent) {
    this.iscurrent = iscurrent;
  }

  public String getCzr() {
    return czr;
  }

  public void setCzr(String czr) {
    this.czr = czr;
  }

  public Date getQssj() {
    return qssj;
  }

  public void setQssj(Date qssj) {
    this.qssj = qssj;
  }

  public Date getJssj() {
    return jssj;
  }

  public void setJssj(Date jssj) {
    this.jssj = jssj;
  }

  public String getCzsm() {
    return czsm;
  }

  public void setCzsm(String czsm) {
    this.czsm = czsm;
  }

  public String getIscheck() {
    return ischeck;
  }

  public void setIscheck(String ischeck) {
    this.ischeck = ischeck;
  }

  public Date getCheck_time() {
    return check_time;
  }

  public void setCheck_time(Date check_time) {
    this.check_time = check_time;
  }

  public String getCheckMemo() {
    return checkMemo;
  }

  public void setCheckMemo(String checkMemo) {
    this.checkMemo = checkMemo;
  }

  public Date getJssx() {
    return jssx;
  }

  public void setJssx(Date jssx) {
    this.jssx = jssx;
  }

  public String getJssjMemo() {
    return jssjMemo;
  }

  public void setJssjMemo(String jssjMemo) {
    this.jssjMemo = jssjMemo;
  }

  public String getCheckUser() {
    return checkUser;
  }

  public void setCheckUser(String checkUser) {
    this.checkUser = checkUser;
  }

  public String getJsUser() {
    return jsUser;
  }

  public void setJsUser(String jsUser) {
    this.jsUser = jsUser;
  }

  public Date getCheck_sx() {
    return check_sx;
  }

  public void setCheck_sx(Date check_sx) {
    this.check_sx = check_sx;
  }

  public String getYssm() {
    return yssm;
  }

  public void setYssm(String yssm) {
    this.yssm = yssm;
  }

  public String getHasnew() {
    return hasnew;
  }

  public void setHasnew(String hasnew) {
    this.hasnew = hasnew;
  }

  public String getSlmx() {
    return slmx;
  }

  public void setSlmx(String slmx) {
    this.slmx = slmx;
  }

  public Date getJssx_yl() {
    return jssx_yl;
  }

  public void setJssx_yl(Date jssx_yl) {
    this.jssx_yl = jssx_yl;
  }

  public String getRefkey() {
    return refkey;
  }

  public void setRefkey(String refkey) {
    this.refkey = refkey;
  }

  public String getRefid() {
    return refid;
  }

  public void setRefid(String refid) {
    this.refid = refid;
  }

  public String getRwblr() {
    return rwblr;
  }

  public void setRwblr(String rwblr) {
    this.rwblr = rwblr;
  }

  public String getThyy() {
    return thyy;
  }

  public void setThyy(String thyy) {
    this.thyy = thyy;
  }

  public String getHtyy() {
    return htyy;
  }

  public void setHtyy(String htyy) {
    this.htyy = htyy;
  }
}
